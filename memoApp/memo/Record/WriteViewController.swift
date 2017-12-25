//
//  WriteViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 12..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class WriteViewController: UIViewController, UITextViewDelegate{
    var imageForSegue : UIImage?
    
    @IBOutlet weak var textView: UITextView!
    var originalFrame:CGRect?
    @IBOutlet weak var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        if imageForSegue != nil{
            imageView.image = imageForSegue
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}
