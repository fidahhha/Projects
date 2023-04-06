<?php

// Home Connect
try{
	$con = new PDO('mysql:host=localhost; dbname=322', 'root', '');
	} catch (PDOException $e) {
	echo "New Database connection error " . $e->getMessage();
	}

// Lab Connect
//    try{
   	
//    	$con = new PDO('mysql:host=learn-mysql.cms.waikato.ac.nz;dbname=322','322','my212527sql');
//    	} catch (PDOException $e) {
//    		echo "Database connection error " . $e->getMessage();
//    	}
   