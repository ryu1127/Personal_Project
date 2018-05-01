//
//  CheckingAccount.swift
//  AccountSwift
//
//  Created by Dongheon Ryu on 2017. 10. 16..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import Foundation

class CheckingAccount : Account{
    init(accountNumber : String, balance : Int, interest: Double){
        self.interest = interest
        super.init(accountNumber : accountNumber,balance : balance)
    }
    
    private let interest : Double
    
    override func aMonthHasPassed() {
        balance = Int((1+interest) * Double(balance))
    }
    
    func withDraw(amount : Int) -> Bool{
        if (amount <= balance){
            balance -= amount
            return true
        }
        else{
            return false
        }
    }
    
    func deposit(amount:Int){
        balance += amount
    }
    
}
