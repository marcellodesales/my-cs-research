<?  if ((!isset($announcerID)) || (!isset($LXID)) || ($especification == null)){
        header("Location: /");
    } else {
            require("/classia/classes/announce.php");

            $util = new Utility();
 	    $ad   = new Announce('0',$announcerID);

  	    $idAn = $ad->id;

   	    $ad->setStr("periodo_anuncio",$announcePeriod);
            $ad->setStr("ip_anunciante_anuncio",getenv("REMOTE_ADDR"));
     	    $ad->setStr("titulo_anuncio",$title);
     	    $ad->setStr("desc_anuncio",$util->getBRDescription($descAnnounce));

     	    $util = new Utility();
     	    $dateEnd  = $util->getFutureDbDate($announcePeriod);
     	    $newPrice = $util->getUsPrice($price)/1;

     	    $ad->setStr("data_end_anuncio",$dateEnd);
    	    $ad->setInt("preco_anuncio",$newPrice);
	    $ad->setInt("cod_tipo_anuncio",$kindOfAnnounce);
	    $ad->setInt("cod_categoria_anuncio",$category);

	    if ($subcategory != ""){
	        $ad->setInt("cod_subcategoria_anuncio",$subcategory);
	    }

	    if (($category == 1) || ($category == 2)) {
	          $espec = new EspecificationOfAnnounce($idAn,$category);
	          $espec->set($especification);
            }

	    if (($image != "none") && ($category != 20)){
                include("uploadAnnounceImage.php");
                $ad->setStr("imagem_anuncio",$imageExtention);
     	    }

            $message = "Novo anúncio '".$title."' foi criado com sucesso!";
            session_register("message");
            header("Location: index.php?".session_name()."=".session_id());
    }
?>
