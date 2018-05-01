<?php
    $dbServer = "localhost";
    $dbUser = "root";
    $dbPass = "111111";
    $comment_tbl="comment2";
    $dbname = "lh";
    $conn = new mysqli($dbServer, $dbUser, $dbPass, $dbname);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    } 
    $sql = "SELECT * FROM lh.comment";
?>
