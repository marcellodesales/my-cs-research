<? require("announce.php");
   $announce = new Announce($idAnnounce,$idAnnouncer);
 if ($sent == null){
?>
<html>
<head>
<title>Exclusão de anúncio - Classi-A</title>
</head>
<body bgcolor="#ffffff"><BR><BR><BR><BR><BR>
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066">
              <b><font face="Verdana" color="#FFFFFF" size="5">
              Excluir anúncio???</font></b></td>
    </tr>
    <tr>
      <td width="138" valign="top">
      	<img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2">
          <p align="justify"><? echo $announce->user->name ?>, você está prestes
              a apagar seu anúncio cujo título é:<BR>*<? echo $announce->title ?>.<BR>
              Você tem certeza que quer apagar este anuncio???</font></p></td>
    </tr>
    <tr>
     <td align="right" colspan="2">  <BR>
        <form action="deleteAnnounce.php" method="POST">
          <input type="hidden" name="idAnnounce" value="<? echo $idAnnounce ?>">
          <input type="hidden" name="idAnnouncer" value="<? echo $idAnnouncer ?>">
          <input type="hidden" name="sent" value="true">
          <input type="submit" value="Confirmar">&nbsp;&nbsp;<input type="button" value="Cancelar" onClick="javascript:history.back();">
        </form>
     </td>
    </tr>
    </table>
</body>
</html>
<? } else {
           if ($announce->id_category == 1){
                $del = "DELETE FROM t_veiculo WHERE cod_anuncio = '$idAnnounce'";
                $announce->connection->query($del);
           }
           if ($announce->id_category == 2){
                $del = "DELETE FROM t_imovel WHERE cod_anuncio = '$idAnnounce'";
                $announce->connection->query($del);
           }
	   if ($announce->image != ""){
		include("deleteImage.php");		
	   }
           $del = "DELETE FROM t_anuncio WHERE cod_anuncio = '$idAnnounce'";
           $announce->connection->query($del);
 ?>
<html>
<head>
<title>Exclusão de anúncio</title>
</head>
<body bgcolor="#ffffff"><BR><BR><BR><BR><BR>
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066">
              <b><font face="Verdana" color="#FFFFFF" size="5">
              Anúncio Excluído!!!</font></b></td>
    </tr>
    <tr>
      <td width="138" valign="top">
      	<img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2">
          <p align="justify"><? echo $announce->user->name ?>, seu anúncio foi excluído com sucesso!
          Agora você poderá adicionar mais anúncios, dependendo de sua quantidade.</font></p></td>
    </tr>
    <tr>
     <td align="center" colspan="2"><BR>
      <form method="POST" action="announcerLogin.php">
       <input type="hidden" name="username" value="<? echo $announce->user->username ?>">
       <input type="hidden" name="password" value="<? echo $announce->user->password ?>">
       <input type="submit" value="Gerenciador de Anúncios (<? echo $announce->user->username ?>)">
      </form>
     </td>
    </tr>
    </table>
</body>
</html>
<?   }  ?>
