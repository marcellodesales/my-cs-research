<?   if ((!isset($adID)) || (!isset($LXID)) || (!isset($announcerID))){
        header("Location: /");
     } else {
        require("/classia/classes/announce.php");
        $ad = new Announce($adID,$announcerID);
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
      	<img border="0" src="/common/images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2">
          <p align="justify"><? echo $ad->user->name ?>, você está prestes
              a apagar seu anúncio cujo título é:<BR>*<? echo $ad->title ?>.<BR>
              Você tem certeza que quer apagar este anuncio???</font></p></td>
    </tr>
    <tr>
     <td align="right" colspan="2">  <BR>
        <form action="<? echo $PHP_SELF ?>" method="POST">
          <input type="hidden" name="adID" value="<? echo $adID ?>">
          <input type="hidden" name="announcerID" value="<? echo $announcerID ?>">
          <input type="hidden" name="sent" value="true">
          <input type="submit" value="Confirmar">&nbsp;&nbsp;<input type="button" value="Cancelar" onClick="javascript:location.href='index.php?<? echo session_name()."=".session_id(); ?>'">
        </form>
     </td>
    </tr>
    </table>
</body>
</html>
<? } else {
           $title = $ad->title;
           if ($ad->id_category == 1){
                $del = "DELETE FROM t_veiculo WHERE cod_anuncio = '$adID'";
                $ad->connection->query($del);
           }
           if ($ad->id_category == 2){
                $del = "DELETE FROM t_imovel WHERE cod_anuncio = '$adID'";
                $ad->connection->query($del);
           }
	   if ($ad->image != ""){
		include("deleteImage.php");
	   }
           $del = "DELETE FROM t_anuncio WHERE cod_anuncio = '$adID'";
           $ad->connection->query($del);

           $message = "O anúncio '$title' foi excluído com sucesso!";
           session_register("message");
           header("Location: index.php?".session_name()."=".session_id());
   }

  }
?>
