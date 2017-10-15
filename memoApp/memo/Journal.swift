//
//  Journal.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 16..
//  Copyright © 2017년 이효중. All rights reserved.
//

import Foundation
class Journal : CustomStringConvertible{
    var description: String{
        return name + " at \(latitude),\(longitude) " + "'"+contents+"'"
    }
    
    init(latitude: Double,
         longitude: Double,
         name: String,
         contents: String) {
        self.name = name
        self.contents = contents
        self.latitude = latitude
        self.longitude = longitude
    }
    let latitude : Double
    let longitude : Double
    public internal(set) var contents : String {
        didSet{
            print("Contents Changed")
        }
    }
    public internal(set) var name : String {
        didSet{
            print("Name Changed")
        }
    }
}
