        <tr>
          <td width="50%" height="30" bgcolor="<?echo $color; ?>" valign="middle">
	    	&nbsp;<img src="/common/images/announce.gif">
<?if ($adImage != ""){ ?>
            <a href="javascript:openAnnounceImage('<? echo $adID.".".$adImage; ?>','<? echo $titleWHifen ?>')" onMouseOver="return showStatus('Imagem do anúncio -> <? echo $titleWHifen ?>')" onMouseOut="clearStatus()">
            <img border="0" src="/common/images/foto.gif"></a>&nbsp;
<?} ?>      <font face="Verdana" size="2">
            <a href="getAd.php?<? echo $linkSession ?>&adID=<? echo $adID; ?>" class="subcategory" onMouseOver="return showStatus('Visualiza o anúncio -> <? echo $titleWHifen ?>')" onMouseOut="clearStatus()">
            <? echo $adTitle; ?>&nbsp;</font></a></td>
<?  if (($goKindOA) && ($descKOA == "Todos os tipos de anuncio")){
	//Here when verifying if there is an kind of Ad....
?>        <td width="25%" bgcolor="<?echo $color; ?>" valign="middle" align="center">
            <font face="Verdana" size="2">&nbsp;<?echo $descKindOA; ?></font></td>
<?  }
    $adPrice = ($adPrice == 0.00) ? "Não informado" : $adPrice; ?>

          <td width="25%" bgcolor="<?echo $color; ?>" valign="middle" align="right">
            <font face="Verdana" size="2">&nbsp;<?echo $adPrice; ?></font></td>
        </tr>
