<TABLE background=/common/images/bg1.gif border=0 cellPadding=0 cellSpacing=0 width="100%" height="30">
  <TR>
    <TD width="1%" valign="middle">
        &nbsp;&nbsp;<img src="/common/images/brasil.gif" vspace="0" hspace="0">
    </TD>
    <TD width="30%" valign="middle">
        <b><font color="#FFFFFF" face="Verdana" size="1">
        &nbsp;&nbsp;Total de Anúncios: <? echo $secure->getQuantAnnounce(); ?> </font></b></TD>

    <TD vAlign=middle width="372" align="center">
        <font color="#FFFFFF" size="2" face="Verdana">
        <b>Classificados de Alagoas</b></font></TD>

    <TD width="30%" valign="middle" align="right">
        <b><font color="#FFFFFF" face="Verdana" size="1"><? 
      	require("/classia/classes/utility.php");
      	$util = new Utility();  
      	$date = $util->getDate(); 
      	echo $date 
?></font></b>&nbsp;&nbsp;</TD>
  </TR>
</TABLE>
