<?php
    require_once('./server/user_comment.php');

    if(isset($_REQUEST['page'])){ 
        $current_page=$_REQUEST['page']-1;
        $start=$current_page++*$page_size;
        $sql=$sql." ORDER BY no DESC LIMIT 8 offset ".$start;       
    }
    else{
        $current_page=0;
    }   
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>자유게시판</title>
	<link rel="stylesheet" href="./css/board1.css" type="text/css">
</head>
<body onresize="parent.resizeTo(500,400)" onload="parent.resizeTo(500,400)">
    
    <div id="container">
    
        <div id="boardView">
                
                <div id="write">
                    <a href="./commentWrite.php">글쓰기</a>
                </div>
            
                <table class="table">
                    <tr><th>no</th><th>작성시간</th><th>아이디</th><th>제목</th><th>조회</th><th>수정</th>
                    <th>삭제</th>
                    </tr>
                
                    <?php
 
                            if ($result=$conn->query($sql)) {
  
                            } else {
                                 echo "Error: " . $sql . "<br>" . $conn->error;
                            }

                      
                            while($row= mysqli_fetch_array($result)){?>
                    <tr>
                        <td><span>no:<?php echo substr($row['no'],0,10)?></span></td>
                        <td><span><?php echo substr($row['date'],0,10)?></span></td>
                        <td><span><?php echo $row['id']?></span></td>
                        <td><span id="boardContent"><?php echo"<a href='".htmlspecialchars("./commentModify.php?no=".urlencode($row['no']))."'>".substr($row['title'],0,15)."</a>"?></span></td>
                        <td><span id="boardHit"><?php echo $row['view']?></span></td>
                
                    <?php
                          if(1 or $row['id']===$_SESSION['id']){
                               echo "<td><span><a href='".htmlspecialchars("./commentModify.php?no=".urlencode($row['no']))."'>수정</a></span></td>";
                               echo "<td><span><a href='".htmlspecialchars("./server/comment_delete.php?no=".urlencode($row['no']))."'>삭제</a></span></td>";?>                
                        </tr><br>
                    <?php     }
                        }    
                    ?>
                    </table>
                
                    <div class='page'>
                    
                    <?php
                        
                        if(!(1>( ($current_page-1)/8))){
                          echo "<a class='L_no' href='".htmlspecialchars("./board.php?page=".urlencode( $current_page-7))."'> < </a>";
                        }
                        $start=(int)(($current_page-1)/8);//8개로나눔
                        $start=$start*8+1;
                        $end=$start+8;
                        $i=$start;
                        
                        for($i;$i<$end;$i++){
                            if($i>$total_page){
                                break;
                            }
                         echo "<a class='no' href='".htmlspecialchars("./board.php?page=".urlencode($i))."'>".$i."</a>";
                        }
                         if($i<$total_page){
                            echo "<a class='R_no' href='".htmlspecialchars("./board.php?page=".urlencode($end))."'>  > </a>";
                         }
                    ?>
                        
                    </div>
            
		</div>
    </div>
</body>
</html>