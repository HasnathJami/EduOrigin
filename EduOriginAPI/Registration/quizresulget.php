<?php

 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");
 
 $email=trim($_POST['email']);
 
 $qry="select result from quiz_result where email='$email'";
 
 $raw=mysqli_query($conn,$qry);
 
 $data=mysqli_fetch_row($raw);
 

 
 print(json_encode($data));


?>