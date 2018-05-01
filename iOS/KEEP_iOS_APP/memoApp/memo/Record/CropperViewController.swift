//
//  CropperViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 11. 13..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import CoreLocation
class CropperViewController: UIViewController, UIScrollViewDelegate, CLLocationManagerDelegate {
    var imageForSegue : UIImage?
    var image : UIImage?
    var lati : Double?
    var longi : Double?
    var locationManager = CLLocationManager()
    
    @IBOutlet var scrollView: UIScrollView! {
        didSet {
            scrollView.delegate = self
            scrollView.minimumZoomScale = 0.5
            scrollView.maximumZoomScale = 5.0
        }
    }
    @IBOutlet var imageView: UIImageView!

    @IBOutlet weak var CropAreaView: CropAreaView!{
        didSet {
            self.CropAreaView.layer.borderWidth = 2
            self.CropAreaView.layer.borderColor = UIColor.init(red:222/255.0, green:225/255.0, blue:227/255.0, alpha: 0.7).cgColor
        }
    }
    //scrollView, CropAreaView, imageView 들의 관계로 잘라낼 부분 위치 정함
    var cropArea:CGRect{
        get{
            let factor = imageView.image!.size.width/view.frame.width
            let scale = 1/scrollView.zoomScale
            let imageFrame = imageView.imageFrame()
            let x = (scrollView.contentOffset.x + CropAreaView.frame.origin.x - imageFrame.origin.x) * scale * factor
            let y = (scrollView.contentOffset.y + CropAreaView.frame.origin.y - imageFrame.origin.y) * scale * factor
            let width = CropAreaView.frame.size.width * scale * factor
            let height = CropAreaView.frame.size.height * scale * factor
            return CGRect(x: x, y: y, width: width, height: height)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if imageForSegue != nil{
            imageView.image = imageForSegue
        }
        // Do any additional setup after loading the view.
    }
    
    func viewForZooming (in scrollView : UIScrollView) -> UIView?  {
        return imageView
    }
    
    @IBAction func cropBtn(_ sender: Any) {
        //cropArea를 croppedCGImage로 이미지 만듬
        let croppedCGImage = imageView.image?.cgImage?.cropping(to: cropArea)
        let croppedImage = UIImage(cgImage: croppedCGImage!)
        image = croppedImage
        imageView.image = croppedImage
        scrollView.zoomScale = 1
        
        lati = locationManager.location?.coordinate.latitude
        longi = locationManager.location?.coordinate.longitude
        print(lati,longi)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "imageSegue"{
            if let destination = segue.destination as? WriteViewController{
                destination.imageForSegue = image
            }
        }
    }
}


extension UIImageView{
    func imageFrame()->CGRect{
        let imageViewSize = self.frame.size
        guard let imageSize = self.image?.size else{return CGRect.zero}
        let imageRatio = imageSize.width / imageSize.height
        let imageViewRatio = imageViewSize.width / imageViewSize.height
        if imageRatio < imageViewRatio {
            let scaleFactor = imageViewSize.height / imageSize.height
            let width = imageSize.width * scaleFactor
            let topLeftX = (imageViewSize.width - width) * 0.5
            return CGRect(x: topLeftX, y: 0, width: width, height: imageViewSize.height)
        }else{
            let scalFactor = imageViewSize.width / imageSize.width
            let height = imageSize.height * scalFactor
            let topLeftY = (imageViewSize.height - height) * 0.5
            return CGRect(x: 0, y: topLeftY, width: imageViewSize.width, height: height)
        }
    }
}

class CropAreaView: UIView {
    override func point(inside point: CGPoint, with event:   UIEvent?) -> Bool {
        return false
    }
}

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */


