//
//  HistoryTableViewController.swift
//  memo
//
//  Created by 이효중 on 2017. 10. 16..
//  Copyright © 2017년 이효중. All rights reserved.
//

import UIKit
import Firebase

class FriendsViewController: UITableViewController {
    
    var memos = [Memo]()
    
    override func viewDidLoad() {
        fetchMemo()
        super.viewDidLoad()
    }
    
    //import memo list from firebase
    func fetchMemo(){
        let uid = FIRAuth.auth()?.currentUser?.uid
        FIRDatabase.database().reference().child("data").child(uid!).observe(.childAdded, with: {(snapshot) in
            
            //import snapshot
            if let dictionary = snapshot.value as? [String:Any]{
                print("dictionary : \(dictionary)")
                let memo = Memo()
                memo.setValuesForKeys(dictionary)
                self.memos.append(memo)
                
                let queue = DispatchQueue(label: "label2")
                queue.async {
                    DispatchQueue.main.async {
                        self.tableView.reloadData()
                    }
                }
            }
            
        }, withCancel: nil)
        print("successfully ending")
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return memos.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "FriendsCell", for: indexPath) as! CustomTableViewCell
        
        let memo = memos[indexPath.row]
        print("memo : \(memo)")
        
        if let imageUrl = memo.url {
            let url = URL(string: imageUrl)
            URLSession.shared.dataTask(with: url!, completionHandler: {(data,res,err) in
                if err != nil {
                    print(err)
                    return
                }
                let queue = DispatchQueue(label: "label3")
                queue.async {
                    DispatchQueue.main.async {
                        cell.imageView?.image = UIImage(data: data!)
                        print(UIImage(data: data!))
                        cell.imageView?.contentMode = .scaleAspectFill
                        print("image clear")
                    }
                }
                }).resume()
        }
        cell.dateText.text = memo.time
        cell.titleText.text = memo.title
        print("one cell finished")
        
        
        
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
    
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        func tableView(_ tableView: UITableView, didSelectRowAt: IndexPath){
            var index = tableView.indexPathForSelectedRow?.row
        }
        
        if segue.identifier == "detailMemo" {
            
            var destination = segue.destination as! FriendsDetailViewController
            destination.imageTitle = memos[(self.tableView.indexPathForSelectedRow?.row)!].title!
            destination.imageUrl = memos[(self.tableView.indexPathForSelectedRow?.row)!].url!
            destination.text = memos[(self.tableView.indexPathForSelectedRow?.row)!].text!
            destination.latitude = memos[(self.tableView.indexPathForSelectedRow?.row)!].latitude!
            destination.longitude = memos[(self.tableView.indexPathForSelectedRow?.row)!].longitude!

        }
    }
    
    
}




