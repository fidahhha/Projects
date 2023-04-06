
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <title>Customer data</title>
    </head>
    <body>
<?php
	//first connect to the database 
    require_once('sampleconnect.php');  //replace with path and name for your connection script

    //create the sql query and send it
    $query = "select * from `customer`";
    $result = $con->query($query);
    
    //if we get data back display it using a table
    if($result != FALSE) 
    {
        echo "<table><tr><th>CustomerID</th><th>Surname</th><th>Givenname</th>";
        echo "<th>Phone</th><th>Email</th></tr>";
        while($row = $result->fetch()) 
        {
            echo "<tr>";
            echo "<td>".$row['CustomerID']."</td>";
            echo "<td>".$row['Surname']."</td>";
            echo "<td>".$row['Givenname']."</td>";
          
            echo "<td>".$row['Phone']."</td>";
            echo "<td>".$row['Email']."</td>";
            echo "</tr>";
        }
        echo "</table>";
    } 
    else
    	die("Error in database query");
   
?>
</body>
</html>
