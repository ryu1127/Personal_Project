//
//  HistoryTableViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 16..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import Firebase

class HistoryTableViewController: UITableViewController {
    let journals = [Journal(latitude:0.0,longitude:0.0,name:"Journal1",contents:"first content"),
                    Journal(latitude:0.0,longitude:0.0,name:"Journal2",contents:"second content"),
                    Journal(latitude:0.0,longitude:0.0,name:"Journal3",contents:"third content")]
    
    
    var users = [User]()
    
    override func viewDidLoad() {
        fetchUser()
        super.viewDidLoad()
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        

        
        
    }
    
    func fetchUser(){
        FIRDatabase.database().reference().child("users").observe(.childAdded, with: {(snapshot) in
            print("enter to fetchUser")
            if let dictionary = snapshot.value as? [String:Any]{
                print("dictionary : \(dictionary)")
                let user = User()
                user.setValuesForKeys(dictionary)
                self.users.append(user)
                
//                user.name = dictionary["name"] as! String
//                print("user : \(user)")
//                print("name : \(user.name)")
                
                let queue = DispatchQueue(label: "label")
                queue.async {
                    DispatchQueue.main.async {
                        self.tableView.reloadData()
                    }
                }
            }
            
//                print(snapshot)
            }, withCancel: nil)
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
        return users.count
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "HistoryCell", for: indexPath) as! HistoryViewCell

        let user = users[indexPath.row]
        
        cell.nameLabel.text = user.name!
        
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
