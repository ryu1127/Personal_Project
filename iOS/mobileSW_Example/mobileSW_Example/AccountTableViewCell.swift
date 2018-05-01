//
//  AccountTableViewCell.swift
//  mobileSW_Example
//
//  Created by Dongheon Ryu on 2017. 9. 25..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import UIKit

class AccountTableViewCell: UITableViewCell {
    @IBOutlet weak var accountNumber: UILabel!
    @IBOutlet weak var balance: UILabel!
    //!는 NULL값을 갖지 않게 해주는 부분이다.
    
    
    
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
