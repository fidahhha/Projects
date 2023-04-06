
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
            $latlon = explode(',', $row['lon_lat']);
            $lat = trim($latlon[0]);
            $lon = trim($latlon[1]);
            echo '<div class="weather-info" event-id="' . $row["id"] . '" lon_lat="' . $row["lon_lat"] . '"></div>';
            echo '<h3>' . $row["name"] . '</h3>';
            echo '<button onclick="getWeather(' . $row["lon"] . ', ' . $row["lat"] . ')">Get Weather</button>';
            echo "<br>";
        }
    }
?> 