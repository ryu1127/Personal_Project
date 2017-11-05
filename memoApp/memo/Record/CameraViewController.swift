//
//  CameraViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 11. 1..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import Photos
import AVFoundation

class CameraViewController: UIViewController, AVCapturePhotoCaptureDelegate{

    @IBOutlet weak var previewView: UIView!
    var session = AVCaptureSession()
    var photoOutput = AVCapturePhotoOutput()
    let notification = NotificationCenter.default
    
    func capture(_captureOutput : AVCapturePhotoOutput,
                 didFinishProcessingPhotoSampleBuffer photoSampleBuffer: CMSampleBuffer?,
                 previewPhotoSampleBuffer : CMSampleBuffer?,
                 resolvedSettings : AVCaptureResolvedPhotoSettings,
                 bracketSettings : AVCaptureBracketedStillImageSettings?,
                 error : Error?) {
        let photoData = AVCapturePhotoOutput.jpegPhotoDataRepresentation(
            forJPEGSampleBuffer: photoSampleBuffer!,
            previewPhotoSampleBuffer: previewPhotoSampleBuffer)
        if let data = photoData {
            if let stillImage = UIImage(data: data) {
                UIImageWriteToSavedPhotosAlbum(stillImage,self,nil,nil)
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
    @IBAction func takePhoto(_ sender: Any) {
        let captureSetting = AVCapturePhotoSettings()
        captureSetting.flashMode = .auto
        captureSetting.isAutoStillImageStabilizationEnabled = true
        captureSetting.isHighResolutionPhotoEnabled = false
        photoOutput.capturePhoto(with: captureSetting, delegate: self)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        if session.isRunning{
            return
        }
        setupInputOutput()
        setPreviewLayer()
        session.startRunning()

        // Do any additional setup after loading the view.
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
