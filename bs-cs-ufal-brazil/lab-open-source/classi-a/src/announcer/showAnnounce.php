<? if ((!isset($announcerID)) || (!isset($LXID))){ ?>
        <script>window.close()</script>
<?   } else {
        require("/classia/classes/announce.php");
        $announce = new Announce($idAnnounce,$announcerID);
?>
<html><title><? echo $announce->title ?></title>

<BODY aLink=#c0c0c0 bgcolor="#FFCC00" link=#000000 text=#000000 vLink=#000000 leftMargin="0"  bottomMargin="0" topMargin="0" marginwidth="0" marginheight="0" rightmargin="0">

<BR>
    <table width="100%" cellspacing="0" cellpadding="1" border="1" bordercolor="black" align="center" bordercolorlight="black" bordercolordark="black">
        <tr bgcolor="#EAEAEA">
          <td width="100%" valign="middle" height="19" colspan="2">
            &nbsp;<a href="javascript:window.close()"><img src="/common/images/close.gif" border="0"></a><font face="Verdana" size="2"><? echo $announce->title ?></font></td>
        </tr>
        <tr>
          <td width="100%" colspan="2" height="17" bgcolor="white">
          	<p align="justify">&nbsp;<? echo $announce->description ?></p></td>
        </tr>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Tipo/Anúncio</font></td>
          <td width="75%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->kindOfAnnounce ?></font></td>
        </tr>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Categoria</font></td>
          <td width="75%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->category; ?></font></td>
        </tr>
<?  if (!$announce->id_subcategory == 0){ ?>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Subcategoria</font></td>
          <td width="75%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->subcategory; ?></font></td>
        </tr>
<?  }
    if (!$announce->especification == ""){
           if ($announce->id_category == 1) $espcific = "Marca";
           else if ($announce->id_category == 2) $espcific = "Bairro";
?>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2"><? echo $espcific; ?>:</font></td>
          <td width="75%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->especification->especification; ?></font></td>
        </tr>
<?   }
     if ($announce->price != 0.00){  ?>
        <tr>
          <td width="20%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Preço:</font></td>
          <td width="80%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;R$ <? echo $announce->price; ?></font></td>
        </tr>
<?   }   ?>
        <tr>
          <td width="20%" bgcolor="#EAEAEA" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Data/Hora:</font></td>
          <td width="80%" valign="top" height="16" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->whenAnnounced ?></font></td>
        </tr>
      </table>
      
       <table width="100%" cellspacing="0" cellpadding="1" border="1" bordercolor="black" align="center">
        <tr>
          <td width="100%" bgcolor="#EAEAEA" colspan="2" valign="middle">
            &nbsp;<font face="Verdana" size="2">
            	<? echo $announce->user->name; ?> (<? echo $announce->user->username; ?>)</font></td>
        </tr>
<?   if ($announce->user->phone != ""){  ?>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top">
          	<font face="Verdana" size="2">Telefone:</font></td>
          <td width="75%" valign="top" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->user->phone; ?></font></td>
        </tr>
<?   }
     if ($announce->user->cellPhone != ""){  ?>
        <tr>
          <td width="25%" bgcolor="#EAEAEA" align="right" valign="top">
          	<font face="Verdana" size="2">Celular:</font></td>
          <td width="75%" valign="top" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->user->cellPhone; ?></font></td>
        </tr>
<?   }   ?>
        <tr>
          <td width="20%" bgcolor="#EAEAEA" align="right" valign="top">
          	<font face="Verdana" size="2">Email:</font></td>
          <td width="80%" valign="top" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;
          	<a href="mailto:<? echo $announce->user->email; ?>" class="subcategory">
          	<? echo $announce->user->email; ?></a></font></td>
        </tr>
<?      if ($announce->user->icq != ""){ ?>
        <tr>
          <td width="20%" bgcolor="#EAEAEA" align="right" valign="top">
          	<font face="Verdana" size="2">Icq:</font></td>
          <td width="80%" valign="top" bgcolor="white">
          	<font face="Verdana" size="2">&nbsp;<? echo $announce->user->icq; ?></font></td>
        </tr>
<?      } 
      if ($announce->image != ""){ ?>
	<tr>
         <td width="20%" bgcolor="#EAEAEA" align="right" valign="top">
          	<font face="Verdana" size="2">Imagem:</font></td>
         <td width="80%" valign="top" bgcolor="white">
          	<img src='<? echo $UPLOAD_ANNOUNCE_IMAGE_DIR.$announce->id.".".$announce->image; ?>' class='subcategory'></td>
        </tr>
<?    } ?>
       </table>
</body>
</html>
<? } ?>