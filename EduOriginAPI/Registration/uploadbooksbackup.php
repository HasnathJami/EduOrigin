<?php

 $conn=mysqli_connect("localhost","root","");
 mysqli_select_db($conn,"eduorigin");
 
      $name=trim($_POST['name']);
	  $description=trim($_POST['description']);
	  $image=trim($_POST['image']);
	  $pdf=trim($_POST['pdf']);
	  
	  $filename="IMG".rand().".jpg";
	  $pdffilename="PD".rand().".pdf";
	  file_put_contents("images/".$filename,base64_decode($image));
	  file_put_contents("pdfs/".$pdffilename,base64_decode($pdf));
	  
	               $qry="INSERT INTO `book_library_backup` (`id`, `name`, `description`, `image`, `pdf`) VALUES (NULL, '$name', '$description', '$filename', '$pdffilename')";
				   $res=mysqli_query($conn,$qry);
				   
				   if($res==true)
					   echo "File Uploaded";
				   
				   else
					   echo "File Could Not Uploaded";



?>