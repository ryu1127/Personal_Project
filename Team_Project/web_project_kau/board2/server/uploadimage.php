<?php
 		
if (isset($_FILES['file'])) { 
	echo(22);
 		$name     = $_FILES['file']['name']; //file:상품 이미지
        $tmpName  = $_FILES['file']['tmp_name'];
        $error    = $_FILES['file']['error'];
        $size     = $_FILES['file']['size'];
        echo($name);
        echo(11);
}
else{
	echo(444);
}
?>