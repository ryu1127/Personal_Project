//
//  RecordViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 9. 14..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit

class RecordViewController: UIViewController {
    @IBOutlet weak var longitude: UILabel!
    @IBOutlet weak var latitude: UILabel!
    var lati:Double?
    var longi:Double?  //MapView에서 넘어오는 위도경도값 받는 변수(lati,longi)
    
    let imagePicker = UIImagePickerController()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let latit = lati, let longit = longi{
            latitude.text = String(format:"%.4f",latit)
            longitude.text = String(format:"%.4f",longit)//라벨에 위도 경도값 출력
        }//lati 옵셔널 바인딩,longi 옵셔널 바인딩
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
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
