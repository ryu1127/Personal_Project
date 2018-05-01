//
//  Account.swift
//  AccountSwift
//
//  Created by Dongheon Ryu on 2017. 10. 16..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import Foundation

class Account : CustomStringConvertible{
    
    init(accountNumber:String, balance:Int=0) {
        self.accountNumber = accountNumber
        self.balance = balance
    }
    
    let accountNumber : String
    public internal(set) var balance : Int{
//        set(newBalance){
//            balance = newBalance
//        }

        didSet{
            print("balance change : \(oldValue) -> \(balance)")
        }
        
        
    }
    
    func aMonthHasPassed(){
        fatalError("It must have been overridden.")
    }
    
    //프로퍼티가 함수처럼 사용될 수 있다는 것을 알 수 있다.
    public var description : String{
        return accountNumber + ": \(balance)"
    }
    
}
