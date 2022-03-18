<?php


 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");

    $email=trim($_POST['email']);
	$password=trim($_POST['password']);
	
	$qry="select * from register where email='$email' and password='$password'";
	$raw=mysqli_query($conn,$qry);
	$count=mysqli_num_rows($raw);
	
	if($count>0)
	   $response['message']="exist";
       //$response="exist";
   
    else
		$response['message']="failed";
	      //$response="failed";
	
	
	echo json_encode($response); //Output: message=>exist message =>failed
	//echo $response;


?>