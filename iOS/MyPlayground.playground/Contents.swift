//: Playground - noun: a place where people can play

import UIKit
import Foundation

var str = "Hello, playground"


class Person : NSObject {
    let name: String
    //null이 들어갈 수 있으나 널 체크는 하지 않는다.
    var age: Int!
    weak var spouse: Person?
    
    init(name : String, age : Int? = nil, spouse : Person? = nil){
        self.name = name
        self.age = age
        self.spouse = spouse
    }
}

var p1 : Person? = Person(name: "Hong Gil Dong")
var p2 = Person(name: "Seong Chun Hyang")

if let p1 = p1 {

    p1.spouse = p2

    print("\(p1.name): \(p1.age ?? 0) <=> \(p1.spouse?.name ?? "Not married")")
}

