<?php
// first connect to the database 
require_once('dbconnect.php');

// get the event name from the AJAX request
$eventName = $_POST['name'];
// prepare the sql query with a parameterized statement
$query = $con->prepare("SELECT name, category, month, day, time, cost, location, tagged FROM `events` WHERE name = ?");

// Bind the value of $eventName to the first parameter of the prepared statement i.e. defined once and used multiple times
$query->bindParam(1, $eventName);

// retrieving data from the database 
if ($query->execute() && $query->rowCount() > 0) {
    $row = $query->fetch();
    $event = array(
        "name" => $row["name"],
        "category" => $row["category"],
        "month" => $row["month"],
        "day" => $row["day"],
        "time" => $row["time"],
        "cost" => $row["cost"],
        "location" => $row["location"],
        "tagged" => $row["tagged"]
                    
    );
    // returning JSON response
    echo json_encode($event);
} else {
    // indicating the data will be in JSON format
    header('Content-Type: application/json');
    echo "No event found with ID $eventName";
}

?>
