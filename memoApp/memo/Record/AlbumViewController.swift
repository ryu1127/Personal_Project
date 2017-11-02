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
    
    //이미지를 선택했을때 작동하는 함수 부분
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        print("info : \(info)")
        //이미지 정보를 UIImage 형태로 받아와서 image에 저장
        let image = info[UIImagePickerControllerOriginalImage] as! UIImage
        //이미지 뷰어에 실제로 이미지를 저장하는 과정. 여기서 저장한 변수를 통해 다른 세그로 날릴 수 있을 것 같다.
        imageView.image = image;
        print("Success image Save...");
        picker.dismiss(animated: true, completion: nil);
        print("dismiss success");
    }
    
    //아무것도 선택하지 않고 캔슬 했을 때 어떻게 처리할 지에 대한 함수 부분이다.
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
        print("dismiss success at DidCancel");
        //위의 것과 똑같은데 같은 기능을 하는건지 이 함수가 따로 필요없는 건지는 아직 확인하지 못하였음.
        
    }

    //이미지 선택
    @IBAction func pickImage(_ sender: Any) {
        //이미지 선택을 위해 picker 인스턴스 생성
        let picker = UIImagePickerController()
        
        picker.delegate = self
        //이 present부분에서 앨범에 대한 정보를 picker에 담아온후 그것을 띄워준다.
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
