<?php

 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");
 
    
	 $email=trim($_POST['email']);
	 $result=trim($_POST['result']);
	 $verdict=trim($_POST['verdict']);
	 
     $qry1="select * from quiz_result where email='$email'"; 
	 $raw=mysqli_query($conn,$qry1);
	 $count=mysqli_num_rows($raw);
	 
	 if($count>0)
	 {
		 //$response="exist";
		 $query="UPDATE `quiz_result` SET `result` = $result WHERE `quiz_result`.`email` = '$email'";
		 
		 $response['message']="exist";	
		 $res1=mysqli_query($conn,$query);
		 
		 if($res1==true)
			 //$response="inserted";	
             $response['message']="updated";		 
		 else
			 //$response="failed";
		     $response['message']="failed to update";
		 

	 }
	 else{
		 
		 $qry2="INSERT INTO `quiz_result` (`id`, `email`, `result`, `verdict`) VALUES (NULL, '$email', $result, '$verdict')";
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