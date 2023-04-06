<?php
    //first connect to the database
    require_once('dbconnect.php');

    // Create SQL query and send it
    $query = "select * from `commodities`";
    $result = $con->query($query);
    if($result != FALSE){
        while($row = $result->fetch()){
            echo "id: " .$row['id']."<br>";
            echo "name: " .$row['name']."<br>";
            echo "information: " .$row['information']."<br>";
            echo "code: " .$row['code']."<br>";
            echo "<br>";
        }
    }
?>
