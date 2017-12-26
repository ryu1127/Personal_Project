//
//  HistoryDetailViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 12. 26..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class HistoryDetailViewController: UIViewController {
    
    var name : String?
    var email : String?
    var number : String?
    
    
    @IBOutlet weak var nameText: UILabel!
    @IBOutlet weak var emailText: UILabel!
    @IBOutlet weak var numberText: UILabel!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        nameText.text = name
        emailText.text = email
        numberText.text = number
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
}

