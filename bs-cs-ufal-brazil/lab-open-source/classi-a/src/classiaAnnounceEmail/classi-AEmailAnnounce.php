<html>
<title>Envio de anúncio do Classi-A por email</title>
<head>
<script language=JavaScript1.2 src="/js/validateClassiaAnnounce.js"></script>
</head>
<?  $color= (isset($sent)) ? "white" : "#FFCC00";  ?>
<body bgcolor="<? echo $color ?>">
<center>
<?

if (isset($sent)){
	$newMessage = "Olá $friendName,\n\nSeu amigo $senderName <$senderEmail> achou um anúncio de seu interesse no Classi-A!\n\n";
        if ($body != ""){
                $newMessage.= "Veja seu recado\n$body\n\n";
        }
        $newMessage.= "Para ver este anúncio clique <a href='http://www.classi-a.com.br/ad/getAd.php?adID=$adID'>aqui</a>, ou preencha o formulário 'Ver Anuncio' na página principal com este código $adID\n\n";
        $newMessage.= "Classi-A é um site de anúncios online onde qualquer pessoa pode anúnciar o que quiser inteiramente de graça, incluíndo images!\n";
        $newMessage.= "Também possuímos o '<a href='http://www.classi-a.com.br/site/business.php'>Cartão Eletrônico Classi-A</a>' onde você anuncia sua empresa personalizadamente!\n";
        $newMessage.= "Ou visite o site no endereço http://www.classi-a.com.br";
	$subject = "$friendName - Anúncio Online de seu interesse no Classi-A!";
	$rest    = "From: Classi-A Webmaster <webmaster@classi-a.com.br>";

	mail("$friendName <$friendEmail>", $subject, $newMessage, $rest);

	include("sucessfullClassi-AEmailAnnounceSent.php");
} else {

    if (isset($adID)){

        include("/classia/classes/dbmysql.php");
        include("/classia/classes/utility.php");

	$db = new dbsql("classA");

	$get = "SELECT titulo_anuncio,desc_anuncio,quando_anuncio FROM t_anuncio WHERE cod_anuncio='$adID'";
	$db->query($get);

	if ($db->affected_rows() == 1){

		$util = new Utility();
		$db->next_record();

		$adTitle       = $db->Record["titulo_anuncio"];
		$adDescription = $db->Record["desc_anuncio"];
		$dateOfAd      = $util->getPrintableTimeStamp($db->Record["quando_anuncio"]);

		require("classi-AFormEmailAnnounce.php");
	} else {
		echo "<font size='2' color='red' face='Verdana'>Anúncio não encontrado com o código <b>$announceId</b></font>";
	}

   } else {
	echo "<font size='2' color='red' face='Verdana'>Faltam parâmetros para uso desta página!</font>";
   }

}

?>
</center>
</body>
</html>
