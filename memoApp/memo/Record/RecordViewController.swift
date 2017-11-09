import UIKit

class RecordViewController: UIViewController {
    @IBOutlet weak var longitude: UILabel!
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var imageViewSegue: UIImageView!
    
    
    var lati:Double?
    var longi:Double?
    
    //segue를 통해 받은 이미지 저장할 변수
    var imageForSegue : UIImage?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let latit = lati, let longit = longi{
            latitude.text = String(format:"%.4f",latit)
            longitude.text = String(format:"%.4f",longit)
        }
        if imageForSegue != nil{
            //받은 이미지가 nil값이 아니라면 받아와서 이미지 뷰를 변환
            imageViewSegue.image = imageForSegue
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
