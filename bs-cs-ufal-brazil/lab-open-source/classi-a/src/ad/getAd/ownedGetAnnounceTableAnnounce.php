<table width="80%" border="1">
<tr>
     <td>
      <table width="100%" cellspacing="0" cellpadding="1" border="0" align="center">
        <tr>
          <td width="100%" bgcolor="#EAEAEA" colspan="2" valign="middle" height="19">
<?      if (!$ad->image == null){ ?>
            &nbsp;<a href="javascript:openAnnounceImage('<? echo $ad->id.'.'.$ad->image; ?>','<? echo $ad->title ?>')"  onMouseOver="return showStatus('Imagem do anúncio -> <? echo $ad->title ?>')" onMouseOut="clearStatus()">
            <img border="0" src="/common/images/foto.gif"></a>
<?      } ?>
            &nbsp;<font face="Verdana" size="2"><? echo $ad->title; ?></font></td>
        </tr>

        <tr bgcolor="white">
          <td width="100%" colspan="2" height="17">
		<font face="Verdana" size="2">
          <p align="justify">&nbsp;<? echo $ad->description ?></p></font></td>
        </tr>

<?   if (!$ad->especification == ""){
           if ($ad->id_category == 1) $espcific = "Marca";
           else if ($ad->id_category == 2) $espcific = "Bairro";
?>
       <tr bgcolor="#EAEAEA">
          <td width="20%" align="right" valign="top" height="16">
             <font face="Verdana" size="2"><? echo $espcific; ?>:</font></td>
          <td width="80%" valign="top" height="16">
             <font face="Verdana" size="2">
             &nbsp;<? echo $ad->especification->especification; ?></font></td>
        </tr>
     
<?   $firstColor=true;
     }
?>
        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "white" : "#EAEAEA"; ?>">
          <td width="20%" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Preço:</font></td>
          <td width="80%" valign="top" height="16">
          	<font face="Verdana" size="2">&nbsp;<? echo $price = ($ad->price != 0.00) ? "R$ ".$ad->price : "Não informado"; ?></font></td>
	</tr>

        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "#EAEAEA" : "white"; ?>">
          <td width="20%" align="right" valign="top" height="16">
          	<font face="Verdana" size="2">Data/Hora:</font></td>
          <td width="80%" valign="top" height="16">
          	<font face="Verdana" size="2">&nbsp;<? echo $ad->whenAnnounced ?></font></td>
        </tr>
      </table>
      
       <table width="100%" cellspacing="0" cellpadding="1" border="0" align="center">
        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "white" : "#EAEAEA"; ?>">
<?
	$user = ($ad->user->username == "Classi-A") ? "Páginas Amarelas" : $ad->user->name." (".$ad->user->username.")";
?>
          <td width="100%" colspan="2" valign="middle">
            &nbsp;<font face="Verdana" size="2"><? echo $user ?></font></td>
        </tr>

<?  if ($ad->user->username != "Classi-A"){   ?>        
        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "#EAEAEA" : "white"; ?>">
          <td width="20%" align="right" valign="top">
          	<font face="Verdana" size="2">Telefone:</font></td>
          <td width="80%" valign="top">
          	<font face="Verdana" size="2">&nbsp;<? echo $phone = ($ad->user->phone != "") ? $ad->user->phone : "Não Informado" ?></font></td>
        </tr>

        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "white" : "#EAEAEA"; ?>">
          <td width="20%" align="right" valign="top">
          	<font face="Verdana" size="2">Celular:</font></td>
          <td width="80%" valign="top">
          	<font face="Verdana" size="2">&nbsp;<? echo $cell = ($ad->user->cellPhone != "") ? $ad->user->cellPhone : "Não informado"; ?></font></td>
        </tr>

        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "#EAEAEA" : "white"; ?>">
          <td width="20%" align="right" valign="top">
          	<font face="Verdana" size="2">Email:</font></td>
          <td width="80%" valign="top">
          	<font face="Verdana" size="2">&nbsp;
          	<a href="mailto:<? echo $ad->user->email ?>" class="subcategory"><? echo $ad->user->email ?></a></font></td>
        </tr>
<?   }  ?>
<?      if ($ad->user->icq != ""){     ?>
        <tr bgcolor="<? echo $col = (isset($firstColor)) ? "white" : "#EAEAEA"; ?>">
          <td width="20%" align="right" valign="top">
          	<font face="Verdana" size="2">Icq:</font></td>
          <td width="80%" valign="top">
		<a href="javascript:openClassiaPager('<? echo $ad->user->name ?>','<? echo $ad->user->icq ?>','<? echo $ad->title ?>')" onMouseOver="return showStatus('Mandar uma mensagem pelo pager do ICQ')" onMouseOut="clearStatus()">
              <img src="http://wwp.icq.com/scripts/online.dll?icq=<? echo $ad->user->icq; ?>&img=9"></a></td>
        </tr>
<?      }     ?>

       </table>
    </td>
</tr>
</table>
