
<?

	require ("ownedIndexGetLastAds.php");

?>

<!BR>

<TABLE cellSpacing=0 cellPadding=5 width=97% border=0 align="center">
  <TBODY>
  <TR vAlign=top>
    <TD align=right width=100%>
        <DIV align=center></DIV>
        <TABLE cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD><IMG src="/partner/images/left_corner_top_EAEAEA.gif"
            border=0><BR></TD>
            <TD align=middle bgColor=#eaeaea><FONT face=verdana,arial
            color=#000000 size=2><b>5 &Uacute;ltimos an&uacute;ncios</b></FONT></TD>
          <TD><IMG src="/partner/images/right_corner_top_EAEAEA.gif"
            border=0><BR></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR vAlign=center bgColor=#eaeaea>
          <TD align=left width="100%" colSpan=3>
            <TABLE cellSpacing=5 cellPadding=5 width="100%" border=0>
              <TBODY>
              <TR vAlign=top align=left bgColor=#eaeaea>
                  <TD align=left width="100%" bgcolor="#eaeaea">
                    <TABLE width="100%" align=center border=0 cellpadding="0" cellspacing="0">
                      <TBODY> 
<?
  $link = (isset($linkSession)) ? $linkSession."&" : "";
  if ($secure->connection->affected_rows() != 0){
  $util = new Utility();
       for ($i = 0; $i < $quant; $i++){
           $color = ($i % 2 == 0) ? "White" : "#EAEAEA";
           $secure->connection->next_record();
           $adID    = $secure->connection->Record['cod_anuncio'];
           $adTitle = $secure->connection->Record['titulo_anuncio'];
           $adImage = $secure->connection->Record['imagem_anuncio'];
           $adCategory  = $secure->connection->Record['desc_categoria_anuncio'];
           $adWhen      = $util->getPrintableTimeStamp($secure->connection->Record['quando_anuncio']);
           $titleWHifen = $util->getClearedHifen($adTitle);
           $imageIndex = $i+1;
?>
                      <TR bgcolor="<? echo $color; ?>"> 
                        <TD width="1%" height="0" align="center" valign="middle">
				<a onMouseOver="imageOn('maint_<? echo $imageIndex; ?>'); return showStatus('Visualiza o anuncio -> <? echo $titleWHifen ?>'); " onMouseOut="imageOff('maint_<? echo $imageIndex; ?>'); clearStatus(); " href="getAd.php?<? echo $link ?>adID=<? echo $adID ?>"><img height=8 alt="Abrir Anuncio" src="/common/images/a_ff9900.gif" width=9 border=0 name=maint_<? echo $imageIndex; ?>></a>
			</TD>
			<TD width="3%" height="0" align="left" valign="middle">
			<? if ($adImage != ""){ ?>
				<a href="javascript:openAnnounceImage('<? echo $adID.'.'.$adImage ?>','<? echo $titleWHifen ?>')" onMouseOver="return showStatus('Imagem do anúncio -> <? echo $titleWHifen ?>')" onMouseOut="clearStatus()"><img border="0" src="/common/images/foto.gif"></a>
			<? } else { echo "&nbsp;"; }  ?>
			</TD>
                        <TD height="0"><font face="Verdana, Arial, Helvetica, sans-serif" size="2"><a class=subcategory href="getAd.php?<? echo $link ?>adID=<? echo $adID ?>" onMouseOver="imageOn('maint_<? echo $imageIndex; ?>'); return showStatus('Visualiza o anuncio -> <? echo $titleWHifen ?>'); " onMouseOut="clearStatus(); imageOff('maint_<? echo $imageIndex; ?>');"><? echo $adTitle ?></font></a></TD>
                        <TD rowspan=2 align="right"><font face="Verdana" size="2"><? echo $adCategory ?>&nbsp;&nbsp;&nbsp;</font></TD>
                      <TR bgcolor="<? echo $color; ?>">
                        <TD width="4%" height="0" valign="middle">&nbsp;</TD>
                        <TD>&nbsp;</TD>
                        <TD width="70%" height="0"><font face="Verdana, Arial, Helvetica, sans-serif" size="1"><font face="Verdana" size="1"><? echo $adWhen ?></font></TD>
<?
	}
  }
?>
                </table></table></table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td><img src="/partner/images/left_corner_bottom_EAEAEA.gif" height=5 border="0"></td>
				<td align="center" width="100%" bgcolor="#EAEAEA">
				<td><img src="/partner/images/right_corner_bottom_EAEAEA.gif" height=5 border="0"></td>
			</tr>
		</table>
	</table>
