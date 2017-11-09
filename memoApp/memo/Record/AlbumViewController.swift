import UIKit
class AlbumViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    @IBOutlet weak var imageView: UIImageView!
    
    var imageForSegue : UIImage!;
    
    //앨범에서 이미지를 가져오기 위한 함수 UIImagePickerController를 사용한다.
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        print("info : \(info)")
        
        //image Info를 받아와 UIImage로 타입캐스팅
        let image = info[UIImagePickerControllerOriginalImage] as! UIImage
        imageView.image = image;
        imageForSegue = image;
        picker.dismiss(animated: true, completion: nil);
    }
    
    //이미지를 선택하지 않고 취소했을때 처리
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
    }

    //이미지를 선택하게 되었을때의 처리
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
    
    //앨범에서 선택한 이미지를 보내기 위한 segue 준비
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "imageSegue"{
            if let destination = segue.destination as? RecordViewController{
                destination.imageForSegue = imageForSegue
            }
        }
    }
    //저장 버튼을 눌렀을 때 imageView에 있는 image를 받아와 세그로 전달할 이미지 변수로 저장
    @IBAction func saveBtn(_ sender: Any) {
        
        imageForSegue = imageView.image
        //이미지가 현재 전달 될 준비가 되어있다면
        if imageForSegue != nil{
            performSegue(withIdentifier: "imageSegue", sender: self)
        }
        
    }

}
