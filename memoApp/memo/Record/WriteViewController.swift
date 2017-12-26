//
//  WriteViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 12..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import CoreLocation
import Firebase
import MapKit

class WriteViewController: UIViewController, UITextViewDelegate{
    var imageForSegue : UIImage?
    var saved : Int?
    var longitude : String!
    var latitude : String!
    var imageUrl : String?
    
    let ref = FIRDatabase.database().reference(fromURL: "https://keep-c4c6e.firebaseio.com/")
    let uid = FIRAuth.auth()?.currentUser?.uid
    
    @IBOutlet weak var titleTextField: UITextField!
    
    
    @IBAction func kept(_ sender: Any) {
        saved = 1
        
        let title = titleTextField.text!
        let text = textView.text!
        let image = imageView.image
        
        //upload image and text, gps
        uploadDataToFirebase(title: title, text: text, image: image!)
        

    }
    
    @IBOutlet weak var textView: UITextView!
    var originalFrame:CGRect?
    @IBOutlet weak var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        textView.layer.borderWidth = 0.5
        textView.layer.borderColor = UIColor.gray.cgColor
        imageView.layer.cornerRadius = 4
        textView.layer.cornerRadius = 4
        
        if imageForSegue != nil{
            imageView.image = imageForSegue
        }
    }
    //탭바로 keep버튼 눌렀다는(saved = 1) 값 보냄
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "popupSegue"{
            let tabCtrl = segue.destination as! UITabBarController
            let destination = tabCtrl.viewControllers![0] as! MapViewController
            destination.saved = self.saved!
        }
    }
    var startLocation : CLLocation!
    
    func uploadDataToFirebase(title: String, text: String, image: UIImage){
        
        let imageName = title
        
        //image Save to FIR Storage
        let storageRef = FIRStorage.storage().reference().child(uid!).child("\(imageName).png")
        if let uploadData = UIImagePNGRepresentation(imageForSegue!) {
            storageRef.put(uploadData, metadata: nil, completion: {(metadata, err) in
                if err != nil {
                    print(err)
                    return
                }
                self.imageUrl = metadata?.downloadURL()?.absoluteString
                print("imageUrl : \(self.imageUrl)")
                let userRef = self.ref.child("data").child(self.uid!)
                userRef.child(imageName).child("url").setValue(self.imageUrl!)
                print("finishing uploading!")
                
            })
            
            
            //위도 경도 확인을 위한 CLLocationManager 객체 인스턴스저장
            var locationManager = CLLocationManager()
            //위도 경도 세그로 날리기 위한 변수 지정
            var lati : Double?
            var longi : Double?
            
            locationManager.requestWhenInUseAuthorization()
            locationManager.delegate = self as? CLLocationManagerDelegate
            locationManager.startUpdatingLocation()
            
            
            startLocation = nil
            
            latitude = locationManager.location?.coordinate.latitude
            longitude = locationManager.location?.coordinate.longitude

            print(latitude, longitude)
            
            //Database에 이미지 이름을 저장하여 불러올때 같이 불러옴. 텍스트는 여기에 함께 저장.
            //경로는 /data/uid/imageName/내용, 텍스트, 경도, 위도, 이미지
            let userRef = ref.child("data").child(uid!)
            //현재 시간 구하기
            let now = NSDate()
            let dataFormatter = DateFormatter()
            dataFormatter.dateFormat = "yyyy-MM-dd HH:mm"
            dataFormatter.locale = Locale(identifier: "ko_KR")
            let time = dataFormatter.string(from: now as Date)
            let values = ["title":title,"text":text ,"longitude":longitude as! String ?? "37.0","latitude":latitude as! String ?? "126.0","time":time ,"url":imageUrl ?? "default"] as [String : Any]
            userRef.child("\(imageName)").updateChildValues(values, withCompletionBlock: {(err,ref) in
                if err != nil {
                    print(err)
                }
                let successAlertController = UIAlertController(title: "Success", message: "성공적으로 저장되었습니다.", preferredStyle: UIAlertControllerStyle.alert)
                print("===== value : \(values)")
                let okAction = UIAlertAction(title: "확인", style: UIAlertActionStyle.default, handler : {(err) in
                    //segue 실행
                    self.performSegue(withIdentifier: "popupSegue", sender: self)
                })
                successAlertController.addAction(okAction)
                self.present(successAlertController,animated: true,completion: nil)
            })
            
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}
