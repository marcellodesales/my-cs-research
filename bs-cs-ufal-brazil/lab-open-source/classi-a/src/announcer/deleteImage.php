<?
	if (($adID == "") || ($announcerID == "") || ($LXID == "")){
		header("Location: /");
	} else {
		$image = $ad->id.".".$ad->image;
		passthru("rm -f /classia/images/$image");
		$ad->setStr("imagem_anuncio","");
        }
?>