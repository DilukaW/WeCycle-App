<?php
    $dbHost = "localhost";
    $dbUser = "bimbiave_orisoft";
    $dbPass = "KG_hMzM;v.si9(8o";
    $dbName = "bimbiave_wecycle";

    //connecting to mysql database
    $conn = new mysqli($dbHost, $dbUser, $dbPass, $dbName);

    //if there is some error connecting to the database with die we will stop the further execution by displaying a message causing the error 
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
?>