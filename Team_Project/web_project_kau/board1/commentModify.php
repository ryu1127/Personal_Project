<?php
    require_once('./server/comment_view.php');
    session_start();

    if(isset($_REQUEST['no'])){        
        $no=$_REQUEST['no'];
    }   
    
    if(isset($_SESSION['id'])==''){
       header('Location: ../../login.php');
    }
    $sql=$sql." where no='".$no."'";

    if ($result=$conn->query($sql)) {
  
    } else {
         echo "Error: " . $sql . "<br>" . $conn->error;
    }

    $result_row=mysqli_fetch_array($result);
?>

<!DOCTYPE html>
<html>
	 <head>
		  <title>comment view</title>
		  <meta name="author" content="team3"/>
		  <meta name="description" content="view_comment.php"/>
         <link rel="stylesheet" href="./css/commentModify1.css" type="text/css">
          <script type="text/javascript" src="./js/view_comment.js"></script>   
	 </head> 

        <body>

              <div id="container">
                <form action="./server/comment_update.php/?no=<?php echo $no ?>" method="post">

                    <table class="blueone">
                        <tr><th>no.<?=$no?> <?=$result_row['id']?>님 </th></tr>
                        <tr><td>제목 <textarea name="user_title" id="title"><?php echo $result_row['title']?></textarea></td></tr>
                        <tr><td><textarea name="user_comment" id="comment" ><?php echo $result_row['comment']?></textarea></td></tr>
                    
                  <?php 
                    if($_SESSION['id']==$result_row['id'])//내가 작성한 글
                    {?>
                        <tr><td class="btn"><input class='modify' type="submit" value="수정"></td></tr>
                    </table>
                </form> 
            
                <?php
                    }
                    else{
                        echo "<script> init(); </script>";
                    }
                ?>
            </div>
    	</body>
</html>
