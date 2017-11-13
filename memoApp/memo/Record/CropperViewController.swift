//
//  CropperViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 11. 13..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class CropperViewController: UIViewController, UIScrollViewDelegate {
    var imageForSegue : UIImage?
    @IBOutlet var scrollView: UIScrollView! {
        didSet {
            scrollView.delegate = self
            scrollView.minimumZoomScale = 1.0
            scrollView.maximumZoomScale = 10.0
        }
    }
    @IBOutlet var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        if imageForSegue != nil{
            imageView.image = imageForSegue
        }
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
