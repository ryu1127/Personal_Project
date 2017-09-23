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

class MapViewController: UIViewController, CLLocationManagerDelegate, MKMapViewDelegate {

    @IBOutlet weak var memoMap: MKMapView!
    @IBOutlet weak var trackingButton: UIButton!
    var startLocation : CLLocation! //처음 시작 위치
    var locationManager = CLLocationManager()
    
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var longitude: UILabel!
    //    var latitude: UILabel! //위도
//    var longitude:UILabel! //경도
//    var horizontalAccuracy :UILabel! // 이것도 뭔지 모르겠따.
//    var altitude : UILabel! //자세인가?
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        locationManager.requestWhenInUseAuthorization()
        locationManager.delegate = self
        locationManager.startUpdatingLocation() // location 갱신기능
        startLocation = nil // 기본 값으로 일단 초기화
        memoMap.delegate = self
        memoMap.showsScale = true
    }
    
    //탭버튼 누르면 적용
    @IBAction func tapTrackButton(_ sender: UIButton) {
        switch memoMap.userTrackingMode{
        case .none:
            memoMap.setUserTrackingMode(.follow, animated: true)
            
            //탭버튼을 눌렀을때 활성화
            //location값 입력시 작동하여 화면의 라벨에 표시
            if var lati = locationManager.location?.coordinate.latitude{
                print(lati)
                latitude.text = String(format:"%.4f",lati);
            }
            if var longi = locationManager.location?.coordinate.longitude{
                print(longi)
                longitude.text = String(format:"%.4f",longi);
            }
            
            
            print("tap Button!");
            
            //trackingButton.image = UIImage(named: "locationicon2-02")
        //이것도 주석 좀 ------
        case .follow:
            memoMap.setUserTrackingMode(.none, animated: true)
            
            //trackingButton.image = UIImage(named: "locationicon1-02")
        //이건 뭐하는 기능인지 ---- 주석 필요
        case .followWithHeading:
            memoMap.setUserTrackingMode(.follow, animated: true)
        }
    }
    
    //location 업데이트 하는 함수라고 생각하면 될듯 ( 추가 해야할지 안해야할지 아직 기능 정확히 파악 못함 )
    func locationManager(manager : CLLocationManager, didUpdateLocations locations:[CLLocation]){
        let latestLocation : AnyObject = locations[locations.count-1]
        
        //처음 시작 주소가 nil 일때
        if startLocation==nil{
            startLocation=latestLocation as! CLLocation
        }
    }
        //에러 처리
    func locationManager(manager: CLLocationManager, didFailWithError error: Error){
        print("error:\(error.localizedDescription)")
    }

    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

