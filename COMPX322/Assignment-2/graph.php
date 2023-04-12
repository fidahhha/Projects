<?php
// First, connect to the database
require_once('dbconnect.php');

try {
    if (isset($_POST['symbol'])) {
        $symbol = $_POST['symbol'];

        // Make a request to the AlphaVantage API using the symbol retrieved from the POST request
        $data = file_get_contents("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY_EXTENDED&symbol=$symbol&interval=15min&slice=year1month1&apikey=8TBERTGE6PFZMECB");
        var_dump($data);
        // Process the data here
        // ...
    }
} catch (Exception $e) {
    echo $e->getMessage();
}

json_encode("Content-Type": "application/json");

?>
