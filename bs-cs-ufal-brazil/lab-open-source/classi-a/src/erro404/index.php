<?
$url = "http://$SERVER_NAME$REQUEST_URI";
?>
<html>
<head>
<title>Erro 404 - P�gina n�o encontrada... <? echo $url ?> Classi-A</title>
</head>

<body bgcolor="white">
<BR>
  <table border="0" width="60%" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td bgcolor="#000066">
      	<b><font face="Verdana" color="#FFFFFF" size="5">&nbsp;
      	Erro:P�gina n�o encontrada...</font></b></td>
    </tr>
    <tr>
      <td><BR><font face="Verdana" size="2" color=red><center><b><? echo $url ?></center></b></font><BR>
	<a href="/" target="_top">
	<img border="0" src="/erro404/images/logoClassiAAnnounces.gif" align="left"></a>
      	<p align="justify"><font face="Verdana" size="2">
      	A p�gina que voc� procura n�o existe nos servidoresdo Classi-a.&nbsp;<br>
        Por favor, verifique se voc� digitou o endere�o corretamente e tente outra vez.<BR>Caso algum link de nossa p�gina resultou neste erro, anote o link e avise-nos pelo email <a href="mailto:webmaster@classi-a.com.br">webmaster@classi-a.com.br</a>.<BR>Se voc� quiser, v� a p�gina principal clicando na imagem ao lado.</P></td>
    </tr>
    <tr>
      <td>&nbsp;
        <p align="right"><font face="Verdana" size="2">Obrigado por nos visitar. O Classi-A agradece!</td>
    </tr>
 </table>
<BR><BR><BR><BR>
<?     require("/classia/common/commonCopyright.php");    ?>

</body>
</html>
