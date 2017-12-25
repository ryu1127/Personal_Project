import UIKit
import CoreLocation

class RecordViewController: UIViewController, UIScrollViewDelegate {
    @IBOutlet weak var longitude: UILabel!
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var imageViewSegue: UIImageView!
    

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
    var lati:Double?
    var longi:Double?
    var page:Int = 0
    
    //segue를 통해 받은 이미지 저장할 변수
    //var imageForSegue : UIImage?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        scrollView.delegate = self
        
        for i in 0..<imageArray.count{
            let imageView = UIImageView()
            imageView.image = imageArray[i]
            let xPosition = self.view.frame.width * CGFloat(i)
            imageView.frame = CGRect(x: xPosition + self.scrollView.frame.width/3, y: self.scrollView.frame.height/3, width: 125, height: 98)
            scrollView.contentSize.width = scrollView.frame.width * CGFloat(i+1)
            scrollView.addSubview(imageView)
        }//스크롤뷰에 이미지 표시

        if let latit = lati, let longit = longi{
            latitude.text = String(format:"%.4f",latit)
            longitude.text = String(format:"%.4f",longit)
        }
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
