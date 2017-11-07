//
//  PreviewViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 11. 7..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class PreviewViewController: UIViewController {
    var imageForSegue : UIImage?
    @IBOutlet weak var previewImage: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if imageForSegue != nil{
            previewImage.image = imageForSegue
        // Do any additional setup after loading the view.
        }
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
