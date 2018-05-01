<?php
    session_start();
    $dbServer = "localhost";
    $dbUser = "root";
    $dbPass = "111111";
    $comment_tbl="comment4";
    $dbname = "lh";
    $conn = new mysqli($dbServer, $dbUser, $dbPass, $dbname);
 
	$userId= $_SESSION['id'];
    $no= $_REQUEST['no'];

    $sql="delete from lh.$comment_tbl where id='$userId' and no='$no'";
  

    if ($conn->query($sql) === TRUE) {

    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    header('Location:../board.php?page=1');
?>
