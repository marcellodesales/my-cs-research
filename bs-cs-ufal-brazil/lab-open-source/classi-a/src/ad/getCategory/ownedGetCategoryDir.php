<? include("/classia/conf/conf.php");
   require("/classia/classes/announce.php");
   include("/classia/classes/SiteDescription.php");

   $siteDescr = new SiteDescription();
   $descCategory = $siteDescr->getCategoryDescription($idCategory);
?>
<TABLE background=/common/images/bg1.gif border=0 cellPadding=0 cellSpacing=0 width="100%" height="32">
  <TR>
    <td width="65%">
        <b>&nbsp;&nbsp;
        <? $link = (isset($linkSession)) ? "?".$linkSession : ""; ?>
        <a href="/<? echo $link ?>" target="_top" class="menu_top" onMouseOver="return showStatus('Página Inicial!')" onMouseOut="clearStatus()">
        <font size="2" face="Arial" color="#ffffff"><u>Classi-A</u></a>
<?
        $descKOA = ($descKindOfAnnounce == null) ? "Todos os tipos de anuncio" : $descKindOfAnnounce;
        $descKOA = ($descKindOfAnnounce == "Todos") ? "Todos os tipos de anuncio" : $descKOA;
        $dir = "<font color='#ffffff'>$DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory";
        $actualDir = "Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory"; //necessary for commonGetResult.php
	echo $dir;
?>	</font></b></td>
    <TD width="35%" align=right class=menu2 height="1">
      &nbsp;</TD>
  </TR>
</TABLE>
