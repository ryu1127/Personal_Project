//
//  CameraViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 9. 23..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import AVFoundation

//AVCapturePhotoCaptureDelegate는 카메라 화면을 위한 상속
class CameraViewController : UIViewController, AVCapturePhotoCaptureDelegate {
    //카메라 화면이 보여지는 뷰
    @IBOutlet weak var cameraView: UIView!
    var captureSession : AVCaptureSession!
    var stillImageOutput : AVCapturePhotoOutput?
    var previewLayer : AVCaptureVideoPreviewLayer?
    
    @IBAction func pushBtn(_ sender: Any) {
        //플래시와 카메라 관련 설정 가져오기
        let settingsForMonitoring = AVCapturePhotoSettings()
        settingsForMonitoring.flashMode = .auto
        settingsForMonitoring.isAutoStillImageStabilizationEnabled = true
        settingsForMonitoring.isHighResolutionPhotoEnabled = false
        
        //촬영 셔터를 눌렀을때
        print("pushed success camera btn!")
        stillImageOutput?.capturePhoto(with: settingsForMonitoring, delegate: self)
        //실행결과 이 부분을 들어오지 못함 아마도 NSGenericException과 NSException이 뜨는걸로 보아 이미지 연결이 되지 않아 문제가 발생하는 듯함.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        captureSession = AVCaptureSession()
        stillImageOutput = AVCapturePhotoOutput()
        
        captureSession.sessionPreset = AVCaptureSession.Preset.hd1920x1080 // 해상도 설정
        
        var defaultVideoDevice: AVCaptureDevice?
        var input : AVCaptureDeviceInput!
        
        if let device = AVCaptureDevice.default(.builtInWideAngleCamera, for: AVMediaType.video, position: .back){
            defaultVideoDevice = device
        }
//        let input = try AVCaptureDeviceInput(device: defaultVideoDevice!)
//
        
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
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
