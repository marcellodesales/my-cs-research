<? $uploadError ="";
        if (($image != "none") && ($image != ""))  {

	        if ($image_size <= 51200){
                        $imageInfo = getImageSize($image);
                        $imageType = $imageInfo[2];
                        $imageExtention = "";

                        if ($imageType == 0){
                           $uploadError = "<br> O tipo de imagem é desconhecido. O upload da imagem não foi aceito. <br>";
                        } else {

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

                             // Change $doc_directory to your 'DocumentRoot'.
                             $doc_directory = "/classia/";
                             $images_directory = "images/";
                             $imageName  = $ad->id.".".$imageExtention;

                             $savedImage = $images_directory.$imageName;
                             $copy_path  = $doc_directory.$savedImage;

                             passthru("rm ".$copy_path);

                             if (!copy($image, $copy_path)) {
                                $uploadError = "File upload failed!";
                             } else {
                                $ad->setStr("imagem_anuncio",$imageExtention);
                             }
                        }
                } else {
                     $uploadError = "Sua imagem possui mais de 50Kbytes. O Upload de imagem não foi aceito. Tente novamente modificando seu anúncio.";
                }

        } else {
                //echo "<P>You must select a file to upload!<P>";
        }
?>
