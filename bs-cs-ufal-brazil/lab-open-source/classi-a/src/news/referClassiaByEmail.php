<?
        include("ownedReferClassiaByEmailSender.php");
?>
<html><title>Recomendação do Classi-A por Email</title>
<body bgcolor="white" aLink="blue" link="red" text=#000000 vLink="red">
<center><img src="images/logoClassiA.gif" width="250" height="90">
<font size=3 face="verdana">
<BR><BR>Sua recomendação foi enviada com sucesso!!!<BR><BR>
</font>
<table width="100%" align="center">
<tr><td><font size="2" face="verdana">
<?
echo "<ul><li>De: $senderName <font color='red'><<a href='mailto:$senderEmail'>$senderEmail</a>></font>";
echo "<li>Para: $friendName <font color='red'><<a href='mailto:$friendEmail'>$friendEmail</a>></font>";
echo "</ul>";
?>
</td></tr>
</table>
<font size="2" face="verdana">
Origado por recomendar nosso site!
</font>
<BR><BR><BR>
<form>
<input type="button" value="Fechar Janela" onClick="window.close()">
</form>
</center>
<? include("/classia/common/commonCopyright.php");  ?>
</body>
</html>
