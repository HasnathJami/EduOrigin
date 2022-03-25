<?php

 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");
 
     $id=trim($_POST['id']);
	 $title=trim($_POST['title']);
	 $question=trim($_POST['question']);
	 $option_1=trim($_POST['option_1']);
	 $option_2=trim($_POST['option_2']);
	 $option_3=trim($_POST['option_3']);
	 $option_4=trim($_POST['option_4']);
	 $correct_answer=trim($_POST['correct_answer']);
	 
	 //$qry1="select * from register where email='$email' "; 
	 //$raw=mysqli_query($conn,$qry1);
	// $count=mysqli_num_rows($raw);
	 
	// if($count>0)
	// {
	//	 $response="exist";
	// }
	 //else{
		 
		 $qry2="INSERT INTO `quiz` (`id`, `title`, `question`, `option_1`, `option_2`, `option_3`, `option_4`, `correct_answer`) VALUES (NULL, '$title', '$question', '$option_1', '$option_2', '$option_3', '$option_4', '$correct_answer')";
		 $res=mysqli_query($conn,$qry2);
		 
		 if($res==true)
			 //$response="inserted";	
             $response['message']="inserted";		 
		 else
			 //$response="failed";
		     $response['message']="failed";
		 
	// }
	 
	 
	// echo $response;
	echo json_encode($response);




?>