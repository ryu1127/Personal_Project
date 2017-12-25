//
//  RegisterViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 12. 24..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import Firebase

class RegisterViewController: UIViewController {

    //user name
    @IBOutlet weak var nameTextField: UITextField!
    //email
    @IBOutlet weak var emailTextField: UITextField!
    //password
    @IBOutlet weak var passwordTextField: UITextField!
    //phone number
    @IBOutlet weak var numberTextField: UITextField!
    //PIN for find user info
    @IBOutlet weak var pinTextField: UITextField!
    //register Button
    @IBOutlet weak var registerBtn: UIButton!
    let ref = FIRDatabase.database().reference(fromURL: "https://keep-c4c6e.firebaseio.com/")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        registerBtn.addTarget(self, action: #selector(handleRegister), for: .touchUpInside)

        // Do any additional setup after loading the view.
    }
    
    func handleRegister(){
        guard let email = emailTextField.text, let password = passwordTextField.text, let name = nameTextField.text, let number = numberTextField.text, let pin = pinTextField.text else {
            print("Form is not valid")
            return
        }
        FIRAuth.auth()?.createUser(withEmail: email, password: password, completion: {(user:FIRUser?,error) in
            if error != nil{
                print(error)
                let failAlertController = UIAlertController(title: "Fail", message: "Form is not Valid", preferredStyle: UIAlertControllerStyle.alert)
                let failAction = UIAlertAction(title: "확인", style: UIAlertActionStyle.default, handler : nil)
                failAlertController.addAction(failAction)
                self.present(failAlertController,animated: true,completion: nil)
                return
            }
            
            guard let uid = user?.uid else{
                return
            }
            
            //Save user info in database
            let usersReference = self.ref.child("users").child(uid)
            let values = ["name":name, "email":email,"number":number, "pin":pin]
            usersReference.updateChildValues(values, withCompletionBlock: {(err,ref) in
                if err != nil {
                    print(err)
                }
                let successAlertController = UIAlertController(title: "Success", message: "성공적으로 가입되었습니다.", preferredStyle: UIAlertControllerStyle.alert)
                let okAction = UIAlertAction(title: "확인", style: UIAlertActionStyle.default, handler : nil)
                successAlertController.addAction(okAction)
                self.present(successAlertController,animated: true,completion: nil)
                print("saved user successfully into Firebase DB")
            })
        })
    }
    
    //Keyboard end
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        self.view.endEditing(true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
