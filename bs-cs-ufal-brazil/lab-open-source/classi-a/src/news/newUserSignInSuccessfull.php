<html>
<head>
<title>Cadastro efetuado com sucesso! - Classi-A</title>
</head>
<body bgcolor="#ffffff"><BR><BR><BR>
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066">
              <b><font face="Verdana" color="#FFFFFF" size="5">
              Cadastro efetuado com sucesso!</font></b></td>
    </tr>
    <tr>
      <td width="138" valign="top">
      	<img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381">
      	<font face="Verdana" size="2"><p align="justify">
      	Muito obrigado
      <b><? echo $announcerName ?></b> por ter se cadastrado no Classi-A! As instruções iniciais para o uso inicial dos serviços do Classi-A foram enviadas para <? echo $userEmail ?>. Caso tenha problemas, mande um email para <a href="webmaster@classi-a.com.br">webmaster@classi-a.com.br</a>. Clique no botão abaixo para ir a página principal.</font></p></td>
    </tr>
 </table>
 <center>
 <BR><BR>
 <form method="Post" action="/" target="_top">
 	<input type="hidden" name="newUserName" value="<? echo $username ?>">
 	<input type="submit" value="Página Inicial">
 </form>
 </center>

<?  require("/classia/common/commonCopyright.php");   ?>

</body>
</html>
