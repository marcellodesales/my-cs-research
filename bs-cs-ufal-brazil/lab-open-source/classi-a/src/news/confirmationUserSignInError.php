<html>
<head>
<title>Erro de cadastro de usuário Classi-A</title>
</head>
<body bgcolor="#ffffff">
<center>
<BR><BR>
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066"><b>
	<font face="Verdana" color="#FFFFFF" size="5">Erro em seu cadastro!</font></b></td>
    </tr>
    <tr>
      <td width="138" valign="top"><img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2"><p align="justify">
	Os seguintes erros ocorreram ao se efetuar seu cadastro:<BR><BR>
	<? echo $errors ?></font></p></td>
    <tr>
	<td colspan="2"><font face="Verdana" size="2"><p align="justify">
	Se você quiser clique em "Voltar" e corrija os errors. Se quiser desistir do cadastro, clique em "Cancelar"</font></p></td>
    </tr>
 </table>

<BR>

<form>
<input type="button" value="Voltar" onClick="javascript:history.back()"> 
&nbsp;&nbsp;<input type="button" value="Cancelar" onClick="javascript:document.href='/'" target="_top">
</form>

<?  require("commonCopyright.php");     ?>
</center>
</body>
</html>
