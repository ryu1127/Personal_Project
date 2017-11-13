import UIKit
import AVFoundation
import QuartzCore

class CameraViewController: UIViewController, AVCapturePhotoCaptureDelegate {

    @IBOutlet weak var previewView: UIView!
    @IBOutlet weak var capturedImage: UIImageView!
    var session = AVCaptureSession()
    var photoOutput = AVCapturePhotoOutput()
    let notification = NotificationCenter.default
    
    var segueImage : UIImage?
    //캡쳐델리게이트 설정
    func photoOutput(_ output: AVCapturePhotoOutput,
                     didFinishProcessingPhoto photoSampleBuffer: CMSampleBuffer?,
                     previewPhoto previewPhotoSampleBuffer: CMSampleBuffer?,
                     resolvedSettings: AVCaptureResolvedPhotoSettings,
                     bracketSettings: AVCaptureBracketedStillImageSettings?,
                     error: Error?) {
        let photoData = AVCapturePhotoOutput.jpegPhotoDataRepresentation(
            forJPEGSampleBuffer: photoSampleBuffer!,
            previewPhotoSampleBuffer: previewPhotoSampleBuffer)
        if let data = photoData {
            if let stillImage = UIImage(data: data) {
                //이미지를 앨범에 저장
                UIImageWriteToSavedPhotosAlbum(stillImage,self,nil,nil)
                //세그로 이미지를 전달하기 위해서 stillImage를 받아와서 segueImage에 저장
                segueImage = stillImage
                capturedImage.image = stillImage
            }
        }
    }
    //세그 전달을 위한 prepare 작업
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        //captureSegue는 촬영한 후 뒤로가기 버튼을 눌렀을때 작동할 함수
        // ***** 임시 함수로써 데이터 베이스 연동 후엔 데이터로써 전달 *****//
        
        // ***** 여기 사진 편집 등 팝업창 관련해서 개발 해야함 *****//
        if segue.identifier == "captureSegue"{
            //destination은 recordViewController이다.
            if let destination = segue.destination as? RecordViewController{
                destination.imageForSegue = segueImage
            }
        }
    }
    func setupInputOutput(){
        session.sessionPreset = AVCaptureSession.Preset.photo
        do {
            let device = AVCaptureDevice.default(AVCaptureDevice.DeviceType.builtInWideAngleCamera,
                                                 for:AVMediaType.video,
                                                 position: .back)
            let input = try AVCaptureDeviceInput(device: device!)
            if session.canAddInput(input){
                session.addInput(input)
            } else {
                print("세션에 입력을 추가할 수 없습니다.")
                return
            }
        } catch let error as NSError {
            print("카메라가 없습니다 \(error)")
            return
        }
        if session.canAddOutput(photoOutput){
            session.addOutput(photoOutput)
        } else{
            print("세션에 출력을 추가할 수 없습니다.")
            return
        }
    }
    func setPreviewLayer(){
        let previewLayer = AVCaptureVideoPreviewLayer(session: session)
        let videoLayer = previewLayer
        videoLayer.frame = view.bounds
        videoLayer.masksToBounds = true
        videoLayer.videoGravity = AVLayerVideoGravity.resizeAspectFill
        previewView.layer.addSublayer(videoLayer)
    }
    //이미지 캡쳐 설정
    @IBAction func takePhoto(_ sender: Any) {
        let captureSetting = AVCapturePhotoSettings()
        captureSetting.flashMode = .auto
        captureSetting.isAutoStillImageStabilizationEnabled = true
        captureSetting.isHighResolutionPhotoEnabled = false
        photoOutput.capturePhoto(with: captureSetting, delegate: self as! AVCapturePhotoCaptureDelegate)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        capturedImage.layer.cornerRadius=10
        capturedImage.layer.masksToBounds = true
        capturedImage.layer.borderWidth=0.0
        if session.isRunning{
            return
        }
        setupInputOutput()
        setPreviewLayer()
        session.startRunning()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

}
