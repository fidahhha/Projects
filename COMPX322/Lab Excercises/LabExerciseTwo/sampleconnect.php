<?php

	//replace username with your username e.g. xyz12  in both places
	// and password with your password
   
   try{
   	
   	$con = new PDO('mysql:host=learn-mysql.cms.waikato.ac.nz;dbname=fa125','fa125','my212527sql');
   	} catch (PDOException $e) {
   		echo "Database connection error " . $e->getMessage();
   	}
   
