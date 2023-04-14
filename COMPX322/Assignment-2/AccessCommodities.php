<?php
    //first connect to the database
    require_once('dbconnect.php');

    // Create SQL query and send it
    $query = "select * from `commodities`";
    $result = $con->query($query);
    if($result != FALSE){
        $data = array();
        while($row = $result->fetch(PDO::FETCH_ASSOC)){
            $data[] = $row;
        }
    }

    echo json_encode($data);
?>
