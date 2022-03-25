<?php

 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");
 
     $name=trim($_POST['name']);
	 $email=trim($_POST['email']);
	 $password=trim($_POST['password']);
	 
	 $qry1="select * from register where email='$email' "; 
	 $raw=mysqli_query($conn,$qry1);
	 $count=mysqli_num_rows($raw);
	 
	 if($count>0)
	 {
		 $response="exist";
	 }
	 else{
		 
		 $qry2="INSERT INTO `register` (`id`, `name`, `email`, `password`) VALUES (NULL, '$name', '$email', '$password')";
		 $res=mysqli_query($conn,$qry2);
		 
		 if($res==true)
			 //$response="inserted";	
             $response['message']="inserted";		 
		 else
			 //$response="failed";
		     $response['message']="failed";
		 
	 }
	 
	 
	// echo $response;
	echo json_encode($response);




?>