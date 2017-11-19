//
//  LoginViewController.swift
//  memo
//
//  Created by Dongheon Ryu on 2017. 11. 10..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import Firebase
import GoogleSignIn
import FBSDKLoginKit

class LoginViewController: UIViewController/* , GIDSignInUIDelegate*/, FBSDKLoginButtonDelegate{
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        
        
//***** Google Sign In *****//
        
//        //add google sign in button
//        let googleButton = GIDSignInButton()
//        googleButton.frame = googleBtn.frame
//        view.addSubview(googleButton)
//
//
//
//        GIDSignIn.sharedInstance().uiDelegate = self
        
//***** end of Google Sign In *****//
        
        
        
//***** Facebook Log In *****//
        let loginButton = FBSDKLoginButton()
        view.addSubview(loginButton)
        //frame's are obselete, please use constraints instead because its 2016 after all
        loginButton.frame = CGRect(x: 16, y: 50, width: view.frame.width - 32, height: 50)
        loginButton.delegate = self
        print("success button make")
        
        
//***** End of Facebook Log In *****//
        
        
    }
    func loginButtonDidLogOut(_ loginButton: FBSDKLoginButton!) {
        print("Did log out of facebook....")
    }
    func loginButton(_ loginButton: FBSDKLoginButton!, didCompleteWith result: FBSDKLoginManagerLoginResult!, error: Error!) {
        if error != nil{
            print(error)
            return
        }
        performSegue(withIdentifier: "facebookLogin", sender: self)
        print("Successfully logged in with facebook...")
        
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

