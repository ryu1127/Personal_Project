//
//  FriendsDetailViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 12. 26..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class FriendsDetailViewController: UIViewController,MKMapViewDelegate, CLLocationManagerDelegate {
    
    var imageUrl : String!
    var imageTitle : String?
    var text : String?
    var latitude : String?
    var longitude : String?
    var address : String?
    

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleText: UILabel!
    @IBOutlet weak var textField: UITextView!
    @IBOutlet weak var mapView: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let imageUrl = imageUrl {
            let url = URL(string: imageUrl)
            URLSession.shared.dataTask(with: url!, completionHandler: {(data,res,err) in
                if err != nil {
                    print(err)
                    return
                }
                let queue = DispatchQueue(label: "label3")
                queue.async {
                    DispatchQueue.main.async {
                        self.imageView.image = UIImage(data: data!)
                        self.imageView.contentMode = .scaleAspectFit
                        print("image clear")
                    }
                }
            }).resume()
        }
        titleText.text = imageTitle
        textField.text = text
        
        let location = CLLocationCoordinate2D(latitude: (latitude as! NSString).doubleValue, longitude: (longitude as! NSString).doubleValue)
        let locations = CLLocation(latitude: location.latitude, longitude: location.longitude)
        mapView.setCenter(location, animated: true)
        
        let span : MKCoordinateSpan = MKCoordinateSpanMake(0.01, 0.01)
        let region : MKCoordinateRegion = MKCoordinateRegionMake(location, span)
        
        mapView.setRegion(region, animated: true)
        
        
        let myAnnotation : MKPointAnnotation = MKPointAnnotation()
        myAnnotation.coordinate = CLLocationCoordinate2D(latitude: location.latitude, longitude: location.longitude)
        CLGeocoder().reverseGeocodeLocation(locations, completionHandler: {(placemarks, error) -> Void in
            print("\(location) 1")
            
            if error != nil {
                print("Reverse geocoder failed with error" + (error?.localizedDescription)!)
                return
            }
            
            if placemarks!.count > 0 {
                let pm = placemarks![0]
                print("\(pm.country ?? "") \(pm.locality ?? "") \(pm.subThoroughfare ?? "") \(pm.thoroughfare ?? "") 2")
                self.address = "\(pm.country ?? "") \(pm.locality ?? "") \(pm.thoroughfare ?? "") \(pm.subThoroughfare ?? "") "
                print(self.address ?? "주소 탐색불가")
                self.address = self.address ?? "주소 탐색불가"
                myAnnotation.title = self.address
                
            }
            else {
                print("Problem with the data received from geocoder")
            }
        })
            myAnnotation.title = "122"
            mapView.addAnnotation(myAnnotation)
        
        
        
        
//        let location = CLLocationCoordinate2D(latitude: (latitude! as NSString).doubleValue, longitude: (longitude! as NSString).doubleValue)
//        mapView.setCenter(location, animated: true)
//
//        let center = CLLocationCoordinate2DMake((latitude! as NSString).doubleValue, (longitude! as NSString).doubleValue)
//        let region = MKCoordinateRegionMake(center, MKCoordinateSpanMake(0.01, 0.01))
//
//        mapView.setRegion(region, animated: true)
//
//        let myAnnotaion : MKPointAnnotation = MKPointAnnotation()
//        myAnnotaion.coordinate = CLLocationCoordinate2DMake((latitude! as NSString).doubleValue, (longitude! as NSString).doubleValue)
//        myAnnotaion.title = "HERE"
//        mapView.addAnnotation(myAnnotaion)
////         Do any additional setup after loading the view.
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
