//
//  AccountListTableViewController.swift
//  mobileSW_Example
//
//  Created by Dongheon Ryu on 2017. 9. 25..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import UIKit

class AccountTableViewController: UITableViewController {
//    var destination = ViewController()
    var selectedAccountNumber = "default"
    var selectedBalance=0
    
    var accounts = [CheckingAccount(accountNumber: "111-111-1111",balance: 100000,interest: 0.1),
                    CheckingAccount(accountNumber: "123-123-1234",balance: 100000,interest: 0.5),
                    CheckingAccount(accountNumber: "999-999-9999",balance: 100000,interest: 0.8)]
    
    

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    //이 함수와 밑의 함수의 이름이 같다.
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return accounts.count
    }

    // 함수와 파라미터의 레이블을 합해서 함수의 이름처럼 구성된다. 위의것과 레이블명이 다르기때문에 다른 함수로 인식하게 된다.
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var reuseIdentifier = "accountTableViewCell"
        //이렇게 AccountTableViewCell으로 타입캐스팅을 해주는 부분이다.
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath) as! AccountTableViewCell
        

        let account = accounts[indexPath.row]
        cell.accountNumber.text = account.accountNumber
        cell.balance.text = String(account.balance)
        selectedAccountNumber = account.accountNumber
        selectedBalance = account.balance
        // Configure the cell...

        return cell
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
            let destination = segue.destination as! ViewController
//            let senderCell = sender as! UITableViewCell
//            let indexPath = tableView.indexPath(for: senderCell)
            print("segue success Complete!")
    }
    

//    }
override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    let account = accounts[indexPath.row]
        print("save success")
    }
   
    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

   
    // MARK: - Navigation

//     In a storyboard-based application, you will often want to do a little preparation before navigation
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        // Get the new view controller using segue.destinationViewController.
//        // Pass the selected object to the new view controller.
//    }
  

}
