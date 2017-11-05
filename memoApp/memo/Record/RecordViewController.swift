import UIKit

class RecordViewController: UIViewController {
    @IBOutlet weak var longitude: UILabel!
    @IBOutlet weak var latitude: UILabel!
    @IBOutlet weak var imageViewSegue: UIImageView!
    
    
    var lati:Double?
    var longi:Double?
    
    let imagePicker = UIImagePickerController()
    var imageForSegue : UIImage?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let latit = lati, let longit = longi{
            latitude.text = String(format:"%.4f",latit)
            longitude.text = String(format:"%.4f",longit)
        }
        if imageForSegue != nil{
            imageViewSegue.image = imageForSegue
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
