<html>
<head>
<title>Cadastro efetuado com sucesso!</title>
</head>
<body bgcolor="#ffffff"><BR><BR><BR><BR><BR>
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066">
              <b><font face="Verdana" color="#FFFFFF" size="5">Atualização efetuado com sucesso!</font></b></td>
    </tr>
    <tr>
      <td width="138" valign="top"><img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2"><p align="justify">Seu anúncio foi atualizado com
      sucesso!!! Verifique como ficou seu anúncio clicando sobre seu título na página do gerênciador de anúncios Classi-A!
      Boa sorte!
<? if ($uploadError != ""){   ?>
         <BR>
         <font color="red" size="2"><b>OBS:<? echo $uploadError ?></font>
<? } ?></p></font></td>    
    </tr>
 </table>
 <center><BR><BR>
 <form method="POST" action="announcerLogin.php">
       <input type="hidden" name="username" value="<? echo $username ?>">
       <input type="hidden" name="password" value="<? echo $password ?>">
       <input type="submit" value="Gerenciador de Anúncios (<? echo $username ?>)">
 </form>
 </center>

</body>

</html>
