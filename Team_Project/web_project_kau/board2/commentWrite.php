<?php
    session_start();
?>


<!DOCTYPE html>
<html>
	 <head>
		  <title>my comment</title>
		  <meta name="author" content="team3"/>
		  <meta name="description" content="comment.php"/>
          <link rel="stylesheet" href="./css/commentWrite.css" type="text/css">
      <script src='https://www.google.com/recaptcha/api.js'></script> 

      <script type="text/javascript"> /* 서브밋 전에 리캡챠 체크 여부 를 확인합니다. */ 
      function FormSubmit() 
      { 
        if (grecaptcha.getResponse() == "")
          { alert("리캡챠를 체크해야 합니다."); 
          return false; } 
        else { return true; } } 
      </script>  

	 </head> 

        <body>
         <div id="container">
                <form name="testForm" method="post" action="./server/comment_insert.php" onsubmit="return FormSubmit();"> 
                      <table class="blueone">
                        <tr><td>USER1 <textarea name="user" id="user"></textarea></td></tr>
                        <tr><td>TITLE <textarea name="user_title" id="title"></textarea></td></tr>
                        <tr><td><textarea name="user_comment" id="comment" ></textarea></td></tr>
                        <tr><td><input type="submit" value="파일 전송" /></td></tr> 
                        <tr><td><div class="g-recaptcha" data-sitekey="6Ld7NTwUAAAAAFvOjMz2HXn6LlFms_9ff6Zx3UMp"></div> </td></tr> 
                    </table>
                    <br/> 
                    
                </form>
        </div>
      </body>
</html>
