    <table width="100%" cellspacing="0" cellpadding="1" border="0" bordercolor="black" align="center">
        <tr>
          <td width="50%" height="30" bgcolor="<?echo $color; ?>" valign="middle">&nbsp;
		<img src="/common/images/announce.gif">
<?if ($adImage != ""){ ?>
            &nbsp;<a href="javascript:openAnnounceImage('<? echo $adID.".".$adImage; ?>','<? echo $adTitle ?>')" onMouseOver="return showStatus('Imagem do anúncio -> <? echo $adTitle ?>')" onMouseOut="clearStatus()">
            <img border="0" src="/common/images/foto.gif"></a>&nbsp;
<?} ?> 	    <font face="Verdana" size="2">
            <a href="getAd.php?adID=<? echo $adID; ?>" class="subcategory" onMouseOver="return showStatus('<? echo $adTitle ?>')" onMouseOut="clearStatus()">
            <? echo $adTitle; ?>&nbsp;</font></a></td>

          <td width="25%" bgcolor="<?echo $color; ?>" valign="middle" align="center">
              <font face="Verdana" size="2">
	      &nbsp;<?echo $descKindOA ?></font></td>

<?   $adPrice = ($adPrice == 0.00) ? "Não informado" : $adPrice; ?>

          <td width="25%" bgcolor="<?echo $color; ?>" valign="middle" align="center">
            <font face="Verdana" size="2">&nbsp;<?echo $adPrice; ?></font></td>
        </tr>
      </table>
