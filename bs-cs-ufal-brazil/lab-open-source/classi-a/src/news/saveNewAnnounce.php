<?  if (($idAnnouncer == null) && ($especification == null)){  
        header("Location: /");
    } else {  
 	    $announce = new Announce('0',$idAnnouncer);

  	    $idAn = $announce->id;

   	    $announce->setStr("periodo_anuncio",$announcePeriod);
            $announce->setStr("ip_anunciante_anuncio",getenv("REMOTE_ADDR"));
     	    $announce->setStr("titulo_anuncio",$title);
     	    $announce->setStr("desc_anuncio",$descAnnounce);

     	    $util = new Utility();
     	    $dateEnd  = $util->getFutureDbDate($announcePeriod);
     	    $newPrice = $util->getUsPrice($price)/1;

     	    $announce->setStr("data_end_anuncio",$dateEnd);
    	    $announce->setInt("preco_anuncio",$newPrice);
	    $announce->setInt("cod_tipo_anuncio",$kindOfAnnounce);
	    $announce->setInt("cod_categoria_anuncio",$category);

	    if ($subcategory != ""){
	        $announce->setInt("cod_subcategoria_anuncio",$subcategory);
	    }

	    if (($category == 1) || ($category == 2)) {
	          $espec = new EspecificationOfAnnounce($idAn,$category);
	          $espec->set($especification);
	    }
	    
	    if (($image != "none") && ($category != 20)){	
 		include("uploadAnnounceImage.php");   	    
     	    	$announce->setStr("imagem_anuncio",$imageExtention);
 	    	
     	    }
}
?>
