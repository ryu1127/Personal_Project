<?php 
    session_start();
    $dbServer = "localhost";
    $dbUser = "root";
    $dbPass = "111111";
    $comment_tbl="comment4";
    $dbname = "lh";
    $conn = new mysqli($dbServer, $dbUser, $dbPass, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    } 

    
    //프라이머리키 재정렬
    $sql="ALTER TABLE lh.$comment_tbl AUTO_INCREMENT=1";
    $conn->query($sql);
    $sql="SET @COUNT = 0";
    $conn->query($sql);
    $sql="update lh.$comment_tbl SET lh.$comment_tbl.no = @COUNT:=@COUNT+1";
    $conn->query($sql);
    
    $page_size=8;//한페이지당 8개의 댓글을 불러온다.
    if ($result=$conn->query("SELECT count(*) FROM lh.comment")) {

    } else {
       
         echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $result_row=mysqli_fetch_row($result);
    $total_row = $result_row[0];//현재 저장된 댓글의 총 갯수

    if($total_row<=0) $total_row=0;
   
    $total_page = floor(($total_row-1) /$page_size)+1; 
    
    $sql = "SELECT * FROM lh.comment";
 
?>