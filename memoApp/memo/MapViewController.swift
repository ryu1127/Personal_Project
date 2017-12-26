//
//  FirstViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 9. 13..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit
import Firebase

class MapViewController: UIViewController, CLLocationManagerDelegate, MKMapViewDelegate {

    @IBOutlet weak var popUp: UIView!
    @IBOutlet weak var memoMap: MKMapView!
    @IBOutlet weak var trackingButton: UIButton!
    @IBAction func logoutBtn(_ sender: Any) {
        do{
            try FIRAuth.auth()?.signOut()
            self.performSegue(withIdentifier: "logout", sender: self)
        }catch let logoutError{
            print(logoutError)
        }
    }
    
    var memos = [Memo]()
    
    var startLocation : CLLocation!
    
    var myAnnotations : [MKPointAnnotation] = [MKPointAnnotation]()

    
    //위도 경도 확인을 위한 CLLocationManager 객체 인스턴스저장
    var locationManager = CLLocationManager()
    //위도 경도 세그로 날리기 위한 변수 지정
    var latiTxt : Double?
    var longiTxt : Double?
    //팝업을 띄우기위해 segue받는 변수
    var saved : Int = 0
    
    // ***** 위도 경도에 대한 라벨 현재는 비활성화 상태 연결 X ***** //
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var longitude: UILabel!

    override func viewWillAppear(_ animated: Bool) {
        DispatchQueue.main.async {
            self.fetchMemo()
        }
        latiTxt = locationManager.location?.coordinate.latitude
        longiTxt = locationManager.location?.coordinate.longitude
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(memos)
        //위치 사용 허가를 위한 요청 날리기
        locationManager.requestWhenInUseAuthorization()
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        //위치 사용 허락 받은 후 위치 추적 시작
        locationManager.startUpdatingLocation()
        
        startLocation = nil
        memoMap.delegate = self
        memoMap.showsScale = true
        
        
        //***** 이부분은 현재 사용되지 않음 *****//
//        if latiTxt != nil {
//            latitude.text = latiTxt
//        }
//        if longiTxt != nil {
//            longitude.text = longiTxt
//        }
        // *****                      ****** //
        //keep버튼을 안눌렀으면 화면 아래 팝업 위치
        if saved == 0{
            print("not saved")
            popUp.frame = CGRect(x: 0, y: 620, width: popUp.frame.size.width, height: popUp.frame.size.height)
        }
        //keep버튼을 눌렀으면 화면 위에 팝업 위치
        if saved == 1{
            print("saved")
            /*centerPopupConstraint.constant = 0
            UIView.animate(withDuration: 0.5, animations: {
                self.view.layoutIfNeeded()
            })*/
            UIView.animate(withDuration: 0.0, animations: {
                var popupFrame = self.popUp.frame
                popupFrame.origin.y = 0
                self.popUp.frame = popupFrame
                self.saved -= 1
            })
        }
        print(self.popUp.frame.origin.y)
        
    }
    //확인 버튼을 눌렀을때 팝업 화면 아래로 이동
    @IBAction func closePopup(_ sender: Any) {
        /*centerPopupConstraint.constant = 600
        UIView.animate(withDuration: 0.3, animations: {
            self.view.layoutIfNeeded()
        })*/
        UIView.animate(withDuration: 0.2, animations: {
            var popupFrame = self.popUp.frame
            popupFrame.origin.y = 620
            self.popUp.frame = popupFrame
        })
        print(self.popUp.frame.origin.y)
    }
    
    //세그 보낼 준비

    
    //Record버튼을 눌렀을 때 세그를 실행한다. ( RecordViewController로 위도 경도를 전달을 위함 )
    @IBAction func recordButton(_ sender: Any) {
        //위도 경도를 받아오는 과정
    }
    
    //위치 확인 버튼을 눌렀을 때 작동할 Action
    @IBAction func tapTrackButton(_ sender: UIButton) {
        switch memoMap.userTrackingMode{
        case .none:
            memoMap.setUserTrackingMode(.follow, animated: true)
            print("tap Button!");
            //***** 여기서부터 *****//
        case .follow:
            memoMap.setUserTrackingMode(.none, animated: true)
            
        case .followWithHeading:
            memoMap.setUserTrackingMode(.follow, animated: true)
        }
        //***** 여기까지는 무슨 코드인지 모르겠음 혹시 보면 설명좀 *****//
    }
    


    //LocationManager를 통해서 현재 위치값이 비어있다면 최근 트래킹한 위치를 startLocation에 저장
    func locationManager(manager : CLLocationManager, didUpdateLocations locations:[CLLocation]){
        let latestLocation : AnyObject = locations[locations.count-1]
        
        if startLocation==nil{
            startLocation=latestLocation as! CLLocation
        }
    }
    //위치 확인에 실패하면 오류 출력 
    func locationManager(manager: CLLocationManager, didFailWithError error: Error){
        print("error:\(error.localizedDescription)")
    }
    
    func fetchMemo(){
        let uid = FIRAuth.auth()?.currentUser?.uid
        importSnapshot(uid: uid!)
        print("fetch over!")
        memos = [Memo]()
        print(memos)
    }
    
    func importSnapshot(uid: String){
        FIRDatabase.database().reference().child("data").child(uid).observe(.childAdded, with: {(snapshot) in
            
            //import snapshot
            if let dictionary = snapshot.value as? [String:Any]{
                print("dictionary : \(dictionary)")
                let memo = Memo()
                memo.setValuesForKeys(dictionary)
                self.memos.append(memo)
                print("memo append : \(self.memos)")
                for m in self.memos {
                    print("-----\(m)-------")
                    let location = CLLocationCoordinate2D(latitude: (m.latitude as! NSString).doubleValue, longitude: (m.longitude as! NSString).doubleValue)
                    print("location : \(location)")
                    let myAnnotation : MKPointAnnotation = MKPointAnnotation()
                    myAnnotation.coordinate = CLLocationCoordinate2D(latitude: location.latitude, longitude: location.longitude)
                    myAnnotation.title = memo.title
                    self.memoMap.addAnnotation(myAnnotation)
                }
//                let location = CLLocationCoordinate2D(latitude: self.latiTxt!, longitude: self.longiTxt!)
//                print("location : \(location)")
//                let myAnnotation : MKPointAnnotation = MKPointAnnotation()
//                myAnnotation.coordinate = CLLocationCoordinate2D(latitude: location.latitude, longitude: location.longitude)
//                myAnnotation.title = memo.title
//                let queue = DispatchQueue(label: "label4")
//                queue.async {
//                    DispatchQueue.main.async {
//                        //make marker
//                        print("latiTxt : \(self.latiTxt!)")
//                        print("longiTxt : \(self.longiTxt!)")
//                        let location = CLLocationCoordinate2D(latitude: self.latiTxt!, longitude: self.longiTxt!)
//                        print("location : \(location)")
//                        let myAnnotation : MKPointAnnotation = MKPointAnnotation()
//                        myAnnotation.coordinate = CLLocationCoordinate2D(latitude: location.latitude, longitude: location.longitude)
//                        myAnnotation.title = memo.title
//
////                        self.myAnnotations.append(myAnnotation)
//                        self.memoMap.addAnnotation(myAnnotation)
//                        print("enter queue=-=-=-=-=-=-=--=-")
//                        }
//                }
                for myAnnotation in self.myAnnotations {
                    print("enter loop")
                    self.memoMap.addAnnotation(myAnnotation)
                }

                
            }
            
        }, withCancel: nil)
    }

    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

