//
//  AccountTableViewCell.swift
//  AccountSwift
//
//  Created by Dongheon Ryu on 2017. 10. 16..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import UIKit

class AccountTableViewCell: UITableViewCell {

    @IBOutlet weak var accountLabel: UILabel!
    @IBOutlet weak var balanceLabel: UILabel!
    
    var account: CheckingAccount!{
        didSet{
            updateAccount()
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        if(selected){
            account.aMonthHasPassed()
            updateAccount()
            //when I click it then update this function
            //why describing? not describe?
        }
        

        // Configure the view for the selected state
    }
    func updateAccount(){
        accountLabel.text = account.accountNumber
        balanceLabel.text = String(account.balance)
    }
    

}
