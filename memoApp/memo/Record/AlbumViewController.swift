//
//  AlbumViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 4..
//  Copyright © 2017년 이효중. All rights reserved.
//


import UIKit
class AlbumViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    //UIImagePickerControllerDelegate, UINavigationControllerDelegate
    //두개는 밑에서 picker 인스턴스를 사용할때 필요하므로 같이 상속받은 것이다.
    //여기에 하지 않을 거면 picker.delegate = self as! UI~~~, UI~~~ 이런식으로 작성
    
    //이미지 뷰어를 통해 만들어 두었다.
    @IBOutlet weak var imageView: UIImageView!
    

    
    

    
    @IBAction func pickImage(_ sender: Any) {
        let picker = UIImagePickerController()
        picker.delegate = self
        self.present(picker, animated: true, completion: nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()

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
