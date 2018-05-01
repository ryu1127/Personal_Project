<?php
    session_start();
    $dbServer = "localhost";
    $dbUser = "root";
    $dbPass = "111111";
    $comment_tbl="comment3";
    $conn=mysqli_connect($dbServer,$dbUser,$dbPass) or die("comment_update.php error");
    $no=$_REQUEST['no'];

    if(isset($_SESSION['id'])==''){//정상적이지 않은 접근
        header('Location: ../../../login.php');
    }
    else if( strlen($_POST['user_title'])<5 || strlen($_POST['user_comment'])<5 ){
        
        echo ("<script language='JavaScript'>
        window.alert('제목이나 내용은 5글자 이상 작성 해주세요')
        window.location.href='../../commentModify.php?no=$no';
        </script>");
    }
    else{
        $title= $_POST["user_title"];
        $comment= $_POST["user_comment"];
        $userId= $_SESSION['id'];
        $date = date('Y-m-d H:i:s', time());
        $sql="update lh.$comment_tbl set title='".$title."' , comment='".$comment."' where  no=".$no; 
        if ($conn->query($sql) === TRUE) {
    
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
 
        header('Location: ../../board.php?page=1');
    }
?>
