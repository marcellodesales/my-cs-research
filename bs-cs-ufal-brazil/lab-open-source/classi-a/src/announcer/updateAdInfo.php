<?
     if ((!isset($adID)) || (!isset($announcerID)) || (!isset($sent)) || (!isset($LXID))){
        header("Location: /");
     } else {

             require("/classia/classes/announce.php");
             $util = new Utility();
             $ad = new Announce($adID,$announcerID);

             if ($title != $ad->title)
                  $ad->setStr("titulo_anuncio",$title);

             if ($kindOfAnnounce != $ad->id_kindOfAnnounce)
                  $ad->setInt("cod_tipo_anuncio",$kindOfAnnounce);

             $ad->setStr("desc_anuncio",$util->getBRDescription($descAnnounce));

             $newPrice = ($price == "Não informado") ? 0.00 : $util->getUsPrice($price)/1;
             if ($newPrice != $ad->price){
                $ad->setInt("preco_anuncio",$newPrice);
             }
             if (isset($image)){
	        include("uploadAnnounceImage.php");

             } else
             if (isset($del)){
                include("deleteImage.php");
             }

             $message = "Anuncio '".$ad->title."' atualizado com sucesso!";
             session_register("message");
             header("Location: index.php?".session_name()."=".session_id());
        }
?>
