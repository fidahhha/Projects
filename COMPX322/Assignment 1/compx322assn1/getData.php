<?php
	//first connect to the database 
    require_once('dbconnect.php');  //replace with path and name for your connection script

    //create the sql query and send it
    $query = "select * from `events`";
    $result = $con->query($query);
    
    //if we get data back display it using a table
    if($result != FALSE) 
    {
        while($row = $result->fetch()) 
        {     
            echo '<div class="event-title" event-id="'.$row["name"].'">' .$row["name"]. '</div>';
            echo "<br>";
        
        }
        // Creates a home button to reload the page and event details when clicked
        echo '<input class="HomeBTN" type="button" value="Home" onclick="window.location.reload(true)" style="margin-bottom: 30px;">';

    }