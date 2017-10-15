//
//  SecondViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 9. 13..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class HistoryViewController: UIViewController {

    let journals = [Journal(latitude:0.0,longitude:0.0,name:"Journal1",contents:"content comes     here"),
                    Journal(latitude:0.0,longitude:0.0,name:"Journal2",contents:"content comes here"),
                    Journal(latitude:0.0,longitude:0.0,name:"Journal3",contents:"content comes here")]
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

