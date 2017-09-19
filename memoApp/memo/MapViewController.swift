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
    var locationManager = CLLocationManager()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        locationManager.requestWhenInUseAuthorization()
        locationManager.delegate = self
        memoMap.delegate = self
        memoMap.showsScale = true
    }
    @IBAction func tapTrackButton(_ sender: UIButton) {
        switch memoMap.userTrackingMode{
        case .none:
            memoMap.setUserTrackingMode(.follow, animated: true)
            //trackingButton.image = UIImage(named: "locationicon2-02")
        case .follow:
            memoMap.setUserTrackingMode(.none, animated: true)
            //trackingButton.image = UIImage(named: "locationicon1-02")
        case .followWithHeading:
            memoMap.setUserTrackingMode(.follow, animated: true)
        }
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

