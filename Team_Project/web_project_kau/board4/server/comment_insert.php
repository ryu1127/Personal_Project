<?php


    if(isset($_POST['g-recaptcha-response']))
        { $captcha=$_POST['g-recaptcha-response']; } 
    if(!$captcha) { echo '등록폼에 리캡챠를 확인하세요.'; } 
    $secretKey = "6Ld7NTwUAAAAAH2aHdGdRBnPjKwEqjAf1nbEQCGc"; 
    $ip = $_SERVER['REMOTE_ADDR']; $response = file_get_contents("https://www.google.com/recaptcha/api/siteverify?secret=".$secretKey."&response=".$captcha."&remoteip=".$ip); $responseKeys = json_decode($response,true); 
    if(intval($responseKeys["success"]) !== 1) 
        { echo ("<script language='JavaScript'>
        window.alert('verification ERROR!!')
        window.location.href='../board.php?page=1';
        </script>"); } 
    else { echo '검증을 통과 했습니다.'; } 



    function errorPrint($error)
    {
            switch ($error) {
            case UPLOAD_ERR_INI_SIZE:
                $response = 'The uploaded file exceeds the upload_max_filesize directive in php.ini.';
                   echo($response);
                break;
            case UPLOAD_ERR_PARTIAL:
                $response = 'The uploaded file was only partially uploaded.';
                echo($response);
                break;
            case UPLOAD_ERR_NO_FILE:
                $response = 'No file was uploaded.';
                echo($response);
                break;
            case UPLOAD_ERR_NO_TMP_DIR:
                $response = 'Missing a temporary folder. Introduced in PHP 4.3.10 and PHP 5.0.3.';
                echo($response);
                break;
            case UPLOAD_ERR_CANT_WRITE:
                $response = 'Failed to write file to disk. Introduced in PHP 5.1.0.';
                echo($response);
                break;
           } 
    }

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


    // if(False and isset($_SESSION['id'])==''){//정상적이지 않은 접근시
               
    //            header('Location: ../../../login.php');
    // }
    if($_POST['user_title']=='' || $_POST['user_comment']==''){
        
        echo ("<script language='JavaScript'>
        window.alert('입력 양식을 모드 채워주세요')
        window.location.href='../board.php?page=1';
        </script>");
    }
    else if( strlen($_POST['user_title'])<3 || strlen($_POST['user_comment'])<3 ){
       
        echo ("<script language='JavaScript'>
        window.alert('min lengt 3')
        window.location.href='../board.php?page=1';
        </script>");
    }
    else{
        $title= $_POST["user_title"];
        $comment= $_POST["user_comment"];
        $userId= $_POST["user"];
        $_SESSION['id'] = $userId;
        $date = date('Y-m-d H:i:s', time());
        $view=0;

        $sql="INSERT INTO lh.comment(`no`,`id`,`date`,`title`,`comment`,`view`,`image`) VALUES ( NULL , '".$userId."', '".$date."','".$title."','".$comment."',".$view.",'".$imageblob."')";
         
        if ($conn->query($sql) === TRUE) {

        } 
        else {
            echo "Error: " . $sql . "<br>" . $conn->error;
         }
         $conn->close();
          header('Location: ../board.php?page=1');
            
            
        
    }
?>
