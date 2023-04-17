<?php
// First, connect to the database
require_once('dbconnect.php');

try {
    if (isset($_GET['Commodities'])) {
        $COMMODITIES = filter_var($_GET['Commodities']);
        
        // Make a request to the AlphaVantage API using the symbol retrieved from the GET request
        $json = file_get_contents("https://www.alphavantage.co/query?function=".urlencode($COMMODITIES)."&interval=15min&slice=year1month1&apikey=8TBERTGE6PFZMECB");
        // Converts data from JSON to php array
        $data =  json_decode($json, true);
        // Back to JSON
        echo json_encode($data);
    }
} catch (Exception $e) {
    echo $e->getMessage();
} 

?>
