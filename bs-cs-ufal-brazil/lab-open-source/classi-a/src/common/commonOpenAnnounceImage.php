<html>
<title><? echo $title ?></title>
<body bgcolor=white>
<center>

<font face="verdana" size="2"><? echo $title ?></font>
<BR><BR>
<img src="/images/<? echo $idImage ?>">
<BR>
<form>
	<input type="button" value="Fechar janela" onclick="window.close()">
</form>

<? require("commonCopyright.php");   ?>

</center>
</body>
</html>
