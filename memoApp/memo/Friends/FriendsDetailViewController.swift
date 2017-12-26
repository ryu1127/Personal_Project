//
//  FriendsDetailViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 12. 26..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import MapKit

class FriendsDetailViewController: UIViewController,MKMapViewDelegate, CLLocationManagerDelegate {
    
    var imageUrl : String!
    var imageTitle : String?
    var text : String?
    var latitude : String?
    var longitude : String?

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
