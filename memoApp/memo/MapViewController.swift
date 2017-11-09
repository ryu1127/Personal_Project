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
    var startLocation : CLLocation!
    
    //위도 경도 확인을 위한 CLLocationManager 객체 인스턴스저장
    var locationManager = CLLocationManager()
    //위도 경도 세그로 날리기 위한 변수 지정
    var latiTxt : String?
    var longiTxt : String?
    
    // ***** 위도 경도에 대한 라벨 현재는 비활성화 상태 연결 X ***** //
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var longitude: UILabel!
    
    //위도, 경도 변수
    var lati: Double?
    var longi: Double?

    
    override func viewDidLoad() {
        super.viewDidLoad()
        //위치 사용 허가를 위한 요청 날리기
        locationManager.requestWhenInUseAuthorization()
        locationManager.delegate = self
        //위치 사용 허락 받은 후 위치 추적 시작
        locationManager.startUpdatingLocation()
        
        
        startLocation = nil
        memoMap.delegate = self
        memoMap.showsScale = true
        
        //위도 경도를 받아오는 과정
        lati = locationManager.location?.coordinate.latitude
        longi = locationManager.location?.coordinate.longitude
        
        //***** 이부분은 현재 사용되지 않음 *****//
//        if latiTxt != nil {
//            latitude.text = latiTxt
//        }
//        if longiTxt != nil {
//            longitude.text = longiTxt
//        }
        // *****                      ****** //
        
    }
    //세그 보낼 준비
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        var recordController = segue.destination as! RecordViewController
        recordController.lati = lati
        recordController.longi = longi
    }
    
    //Record버튼을 눌렀을 때 세그를 실행한다. ( RecordViewController로 위도 경도를 전달을 위함 )
    @IBAction func recordButton(_ sender: Any) {
        if lati != nil
        {
            performSegue(withIdentifier: "segue", sender: self)
        }
    }
    
    //위치 확인 버튼을 눌렀을 때 작동할 Action
    @IBAction func tapTrackButton(_ sender: UIButton) {
        switch memoMap.userTrackingMode{
        case .none:
            memoMap.setUserTrackingMode(.follow, animated: true)
            if lati != nil{
                latiTxt = String(format:"%.4f",lati!);
            }
            if longi != nil{
                longiTxt = String(format:"%.4f",longi!);
            }
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

    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

