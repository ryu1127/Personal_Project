import UIKit
import CoreLocation

class RecordViewController: UIViewController, UIScrollViewDelegate, CLLocationManagerDelegate {
    @IBOutlet weak var latitude: UILabel!
    

    @IBAction func imageBtn(_ sender: Any) {
        if(page == 0){
            let nextVC = self.storyboard?.instantiateViewController(withIdentifier: "Camera")
            nextVC?.modalTransitionStyle = .crossDissolve
            present(nextVC!, animated: true, completion: nil)
        }
        if(page == 1){
            let nextVC = self.storyboard?.instantiateViewController(withIdentifier: "Album")
            nextVC?.modalTransitionStyle = .crossDissolve
            present(nextVC!, animated: true, completion: nil)
        }
    }//버튼 목적지 page에 따라 변환
    
    
    @IBOutlet weak var scrollView: UIScrollView!
    
    var imageArray: [UIImage] = [
        UIImage(named:"1")!,
        UIImage(named:"0")!]
    @IBOutlet weak var pageControl: UIPageControl!
    
    var locationManager = CLLocationManager()
    
    
    var page:Int = 0
    var address : String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        scrollView.delegate = self
        
        //위도경도값 받아오기
        let lati:Double = (locationManager.location?.coordinate.latitude)!
        let longi:Double = (locationManager.location?.coordinate.longitude)!
        
        let longit :CLLocationDegrees = longi
        let latit :CLLocationDegrees = lati
        
        print(lati,longi)
        
        let location = CLLocation(latitude: latit, longitude: longit) //changed!!!
        print(location)
        
        //위도경도값을 주소로 변환하는 과정
        CLGeocoder().reverseGeocodeLocation(location, completionHandler: {(placemarks, error) -> Void in
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
                self.latitude.text = self.address ?? "주소 탐색불가"
            }
            else {
                print("Problem with the data received from geocoder")
            }
        })
        
        for i in 0..<imageArray.count{
            let imageView = UIImageView()
            imageView.image = imageArray[i]
            let xPosition = self.view.frame.width * CGFloat(i)
            imageView.frame = CGRect(x: xPosition + self.scrollView.frame.width/3, y: self.scrollView.frame.height/3, width: 125, height: 98)
            scrollView.contentSize.width = scrollView.frame.width * CGFloat(i+1)
            scrollView.addSubview(imageView)
        }//스크롤뷰에 이미지 표시

       /* if imageForSegue != nil{
            //받은 이미지가 nil값이 아니라면 받아와서 이미지 뷰를 변환
            imageViewSegue.image = imageForSegue
        }*/
    }
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        print(scrollView.contentOffset)
        pageControl.currentPage = Int(scrollView.contentOffset.x / CGFloat(100))
        if(Int(scrollView.contentOffset.x / CGFloat(100)) != 0){
            page = 1
        }
        else{page = 0}
    }//pageControl 스크롤뷰 x축 위치에 따라 변환

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
