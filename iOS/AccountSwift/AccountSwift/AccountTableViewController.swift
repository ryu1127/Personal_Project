//
//  TableViewController.swift
//  AccountSwift
//
//  Created by Dongheon Ryu on 2017. 10. 16..
//  Copyright © 2017년 Dongheon Ryu. All rights reserved.
//

import UIKit

class AccountTableViewController: UITableViewController {
    
    let accounts = [CheckingAccount(accountNumber : "123-456-7890",
                                    balance : 0,
                                    interest : 0.01),
                    CheckingAccount(accountNumber : "123-123-1234",
                                    balance : 100000,
                                    interest : 0.01),
                    CheckingAccount(accountNumber : "111-111-1111",
                                    balance : 10000,
                                    interest : 0.02)]
    
//    override func loadView() {
//        //뷰를 로딩하는 기능인데 iOS코딩하는 경우에는 짜는 경우가 별로 없다.
//        //뷰와 관련된 작업들을 자동으로 변환이 되기 떄문에 실제로 사용하는 경우는 많지 않다.
//        // 스토리 보드에서 자동으로 작업이 가능하기 때문이다.
//
//    }
    
    

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

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return accounts.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "AccountCell", for: indexPath) as! AccountTableViewCell

        cell.account = accounts[indexPath.row]
        cell.accountLabel.text = String(describing : accounts[indexPath.row].accountNumber)
        cell.balanceLabel.text = String(accounts[indexPath.row].balance)
        // Configure the cell...

        return cell
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

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
