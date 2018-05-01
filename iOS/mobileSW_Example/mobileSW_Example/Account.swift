//
//  Account.swift
//  mobileSW_Example
//
//  Created by Dongheon Ryu on 2017. 9. 25..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import Foundation

class Account{
    let accountNumber: String
    var balance : Int
    
    //초기화
    init(accountNumber: String, balance : Int = 0){
        self.accountNumber = accountNumber
        self.balance = balance
    }
    
    func aMonthHasPassed(){
        assert(false,"It must be overriden.")
    }
    
}

class CheckingAccount:Account{
    private let interest : Double
    
    init(accountNumber:String,balance:Int=0, interest:Double){
        self.interest = interest
        super.init(accountNumber: accountNumber,balance: balance)
    }
    
    override func aMonthHasPassed() {
        balance = Int((1+interest)*Double(balance))
    }
    
    func withdraw(amount:Int) -> Bool{
        if(amount<=balance){
            balance-=amount
            return true
        }else{
            return false
        }
    }
    func deposit(amount:Int){
        balance += amount
    }
            
}
