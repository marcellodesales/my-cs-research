<html>
<title>Envio de an�ncio do Classi-A por email</title>
<head>
<script language=JavaScript1.2 src="/js/validateClassiaAnnounce.js"></script>
</head>
<?  $color= (isset($sent)) ? "white" : "#FFCC00";  ?>
<body bgcolor="<? echo $color ?>">
<center>
<?

if (isset($sent)){
	$newMessage = "Ol� $friendName,\n\nSeu amigo $senderName <$senderEmail> achou um an�ncio de seu interesse no Classi-A!\n\n";
        if ($body != ""){
                $newMessage.= "Veja seu recado\n$body\n\n";
        }
        $newMessage.= "Para ver este an�ncio clique <a href='http://www.classi-a.com.br/ad/getAd.php?adID=$adID'>aqui</a>, ou preencha o formul�rio 'Ver Anuncio' na p�gina principal com este c�digo $adID\n\n";
        $newMessage.= "Classi-A � um site de an�ncios online onde qualquer pessoa pode an�nciar o que quiser inteiramente de gra�a, inclu�ndo images!\n";
        $newMessage.= "Tamb�m possu�mos o '<a href='http://www.classi-a.com.br/site/business.php'>Cart�o Eletr�nico Classi-A</a>' onde voc� anuncia sua empresa personalizadamente!\n";
        $newMessage.= "Ou visite o site no endere�o http://www.classi-a.com.br";
	$subject = "$friendName - An�ncio Online de seu interesse no Classi-A!";
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
		echo "<font size='2' color='red' face='Verdana'>An�ncio n�o encontrado com o c�digo <b>$announceId</b></font>";
	}

   } else {
	echo "<font size='2' color='red' face='Verdana'>Faltam par�metros para uso desta p�gina!</font>";
   }

}

?>
</center>
</body>
</html>
