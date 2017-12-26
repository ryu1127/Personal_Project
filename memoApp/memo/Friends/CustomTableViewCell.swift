//
//  CustomTableViewCell.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 12. 26..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class CustomTableViewCell: UITableViewCell {
    


    @IBOutlet weak var memoImageView: UIImageView!
    @IBOutlet weak var titleText: UILabel!
    @IBOutlet weak var dateText: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        print("start!!")
        memoImageView.leftAnchor.constraint(equalTo: self.leftAnchor, constant: 8).isActive = true
        memoImageView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
//        memoImageView.widthAnchor.constraint(equalToConstant: 40).isActive = true
//        memoImageView.heightAnchor.constraint(equalToConstant: 40).isActive = true
        memoImageView.translatesAutoresizingMaskIntoConstraints = true
        memoImageView.image = UIImage(named: "mapicon-2")
        print("here!!")
        
        
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
