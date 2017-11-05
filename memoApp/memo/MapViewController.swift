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
    
    var locationManager = CLLocationManager()
    
    var latiTxt : String?
    var longiTxt : String?
    
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var longitude: UILabel!
    var lati: Double?
    var longi: Double?

    
    override func viewDidLoad() {
        super.viewDidLoad()
        locationManager.requestWhenInUseAuthorization()
        locationManager.delegate = self
        locationManager.startUpdatingLocation()
        
        
        startLocation = nil
        memoMap.delegate = self
        memoMap.showsScale = true

        lati = locationManager.location?.coordinate.latitude
        longi = locationManager.location?.coordinate.longitude
        
        if latiTxt != nil {
            latitude.text = latiTxt
        }
        if longiTxt != nil {
            longitude.text = longiTxt
        }
        
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        var recordController = segue.destination as! RecordViewController
        recordController.lati = lati
        recordController.longi = longi
    }
    @IBAction func recordButton(_ sender: Any) {
        if lati != nil
        {
            performSegue(withIdentifier: "segue", sender: self)
        }
    }
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
        case .follow:
            memoMap.setUserTrackingMode(.none, animated: true)
            
        case .followWithHeading:
            memoMap.setUserTrackingMode(.follow, animated: true)
        }
    }
    
    func locationManager(manager : CLLocationManager, didUpdateLocations locations:[CLLocation]){
        let latestLocation : AnyObject = locations[locations.count-1]
        
        if startLocation==nil{
            startLocation=latestLocation as! CLLocation
        }
    }
    func locationManager(manager: CLLocationManager, didFailWithError error: Error){
        print("error:\(error.localizedDescription)")
    }

    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

