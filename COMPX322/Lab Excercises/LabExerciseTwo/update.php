<?php
	//first connect to the database 
    require_once('../../dbconnect.php'); //replace this with the path and name to your connect script

    $fn = $_POST["fn"];    //get hold of the data that was sent from the form
    $ln = $_POST["ln"];
    $phone = $_POST["ph"];
    $city = $_POST["city"];
    $email = $_POST["em"];
    $id = $_POST["id"];

    //create the sql query
    $sql = "insert into customer (Surname, Givenname, City, Email, Phone, CustomerID) values ('$ln','$fn','$city','$email','$phone','$id')";

    echo"<!DOCTYPE html>";
    echo"<html>";
    echo"<head>";
    echo"  <meta charset='UTF-8'>";
    echo"    <title>Updated Customer data</title>";
    echo"</head>";
    echo"<body>";

    //run the query and check the result
    if ($con->query($sql)) {
        echo "Record updated successfully";
    } else {
        echo "Error updating record because : " . $con->errorCode();
}
    //add a button to allow user to view updated data
    echo"<form action='getData_1.php' method='GET'>";
    echo "<input type='submit' value='Show'>";
    echo"      </form>";