      <table width="95%" cellspacing="0" cellpadding="1" border="0" bordercolor="black" align="center">
	<tr bgcolor="#000066">
          
<?  if ($quantAnnouncesNow == 0){   ?>
            <td width="100%" valign="middle" align="center">
               <font size="1" face="Verdana" color="#FFFFFF"><b>Atenção</b></font>
            </td>
	  </tr>
          <tr bgcolor="#EAEAEA">
            <td width="100%" valign="middle" align="center" colspan="4">
               <font size="1" face="Verdana" color="red"><b>Não existem anúncios cadastrados. Clique em 'Adicionar Anúncio'.</b></font>
            </td>

<? } else {  ?>

            <td width="62" valign="middle" align="center">
               <font size="1" face="Verdana" color="#FFFFFF"><b>Excluir</b></font>
            </td>
            <td width="60" align="center" valign="middle">
                <font size="1" face="Verdana" color="#FFFFFF"><b>Modificar</b></font>
            </td>
            <td width="381" align="center" valign="middle">
                <font size="1" face="Verdana" color="#FFFFFF"><b>Título do Anúncio</b></font>
            </td>
          </tr>
<?
	for ($i = 0; $i < $quantAnnouncesNow; $i++){
           $color = ($i % 2 == 0) ? "#EAEAEA" : "White";
           $secure->connection->next_record();
           $adID    = $secure->connection->Record['cod_anuncio'];
           $titleAd = $secure->connection->Record['titulo_anuncio'];
	   $image   = $secure->connection->Record['imagem_anuncio'];
?>
          <tr bgcolor="<? echo $color ?>" height="21">
            <td width="62" valign="middle" align="center">
                <a href="deleteAd.php?adID=<? echo $adID ?>&<? echo session_name()."=".session_id(); ?>" onMouseOver="return showStatus('Apaga o anúncio <? echo "-> $titleAd"; ?>')" onMouseOut="clearStatus()"><img src="/common/images/docdelete.gif" border="0"></a>
            </td>
            <td width="60" valign="middle" align="center">
                <a href="updateAd.php?adID=<? echo $adID ?>&<? echo session_name()."=".session_id(); ?>" onMouseOver="return showStatus('Modifica os dados do anúncio <? echo "-> $titleAd"; ?>')" onMouseOut="clearStatus()"><img src="/common/images/docedit.gif" border="0"></a>
            </td>
            <td width="290" valign="middle">
                <font face="Verdana" size="1">&nbsp;<? if ($image != ""){ ?><img src="/common/images/foto.gif" border='0'><?}?>&nbsp;<a class="subcategory" href="javascript:openAnnounce('<? echo $adID ?>','<? echo session_id()
                        ?>')" onMouseOver="return showStatus('Visualiza o anúncio <? echo "-> $titleAd"; ?> ')" onMouseOut="clearStatus()"><? echo $titleAd ?></a></font></td>

          </tr>
<?     }
   }
?>
          <tr bgcolor="#000066">
              <td colspan="4">&nbsp;</td>
          </tr>
      </table>

