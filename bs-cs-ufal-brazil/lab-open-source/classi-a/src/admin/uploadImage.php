<html>
<body>

<? $uploadError ="";
		if (($image != "none") && ($image != ""))  {
	
	                        $imageInfo = getImageSize($image); 
        	                $imageType = $imageInfo[2];
                	        $imageExtention = "";
                  
                  	      if ($imageType == 0){ 
                        	   $uploadError = "<br> O tipo de imagem é desconhecido. O upload da imagem não foi aceito. <br>";                            
	                        }

                           
                        	switch($imageType) { 

	                             case 1: 
      	                       		$imageExtention = "gif"; 
            	                        break; 
                  	           case 2: 
			      	            $imageExtention = "jpg"; 
                              	      break;
	                             case 3: 
							$imageExtention = "png"; 
            	                        break; 
                       	 	} 

			        echo "A imagem eh do tipo: " . $imageExtention;
                       	 	
       	 			// Change $doc_directory to your 'DocumentRoot'.
					$doc_directory = "c:\\wwwserver\\htdocs\\classia\\admin\\";
       	 				
					$images_directory = "images\\";
     	 				$imageName  = $partnerID.".".$imageExtention;       	 				

					$savedImage = $images_directory.$imageName;
					$copy_path  = $doc_directory.$savedImage;

//					system("del ".$copy_path);

     				if (!copy($image, $copy_path)) {				
						$uploadError = "File upload failed!";
					} else { 
 						//$announce->setStr("imagem_anuncio",$imageExtention);
						?><!-- <A HREF="<? echo "/".$savedImage; ?>">Upload Complete!</A>--><?
    					      echo "<BR><A HREF=\"$PHP_SELF\"><B>Back</B></A> to the Upload Form";
					} 
} else { echo "<P>You must select a file to upload!<P>";  } 

?>
</body>
</html>