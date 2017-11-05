import UIKit
class AlbumViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    @IBOutlet weak var imageView: UIImageView!
    
    var imageForSegue : UIImage!;
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        print("info : \(info)")
        let image = info[UIImagePickerControllerOriginalImage] as! UIImage
        imageView.image = image;
        imageForSegue = image;
        picker.dismiss(animated: true, completion: nil);
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
    }

    @IBAction func pickImage(_ sender: Any) {
        let picker = UIImagePickerController()
        picker.delegate = self
        self.present(picker, animated: true, completion: nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        print("enter prepare...")
        if segue.identifier == "imageSegue"{
            if let destination = segue.destination as? RecordViewController{
                destination.imageForSegue = imageForSegue
            }
        }
    }
    @IBAction func saveBtn(_ sender: Any) {
        
        imageForSegue = imageView.image
        //이미지가 현재 전달 될 준비가 되어있다면
        if imageForSegue != nil{
            performSegue(withIdentifier: "imageSegue", sender: self)
        }
        
    }

}
