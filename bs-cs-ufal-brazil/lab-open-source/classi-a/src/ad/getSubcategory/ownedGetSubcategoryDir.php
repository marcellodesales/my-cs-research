<? include("/classia/conf/conf.php");
   require("/classia/classes/announce.php");
   include("/classia/classes/SiteDescription.php");
   $siteDescr = new SiteDescription();
   $descCategory    = $siteDescr->getCategoryDescription($idCategory);
   $descSubcategory = $siteDescr->getSubcategoryDescription($idSubcategory);
?>
<TABLE background="/common/images/bg1.gif" border=0 cellPadding=0 cellSpacing=0 width="100%" height="32">
<TR>
    <td width="65%">
         <b>&nbsp;&nbsp;
         <? $linkD = (isset($linkSession)) ? "?".$linkSession : ""; ?>
         <? $link = (isset($linkSession)) ? $linkSession."&" : ""; ?>
         <a href="/<? echo $linkD ?>" target="_top" class="menu_top" onMouseOver="return showStatus('Página Inicial!')" onMouseOut="clearStatus()">
          <font size="2" face="Arial" color="#ffffff"><u>Classi-A</u></a> <?

        $descKOA = ($descKindOfAnnounce == null) ? "Todos os tipos de anuncio" : $descKindOfAnnounce;
        $descKOA = ($descKindOfAnnounce == "Todos") ? "Todos os tipos de anuncio" : $descKOA;
        if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){

              $dir = "<font color='#ffffff'>$DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a class='menu_top' href='getCategory.php?".$link."idCategory=$idCategory' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descCategory</u></a><font color='#ffffff'> $DIR_SEPARATOR $descSubcategory";

              $actualDir = "Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a class='subcategory' href='getCategory.php?".$link."idCategory=$idCategory' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><u>$descCategory</u></a> $DIR_SEPARATOR $descSubcategory";

        } else $dir = "<font color='#ffffff'>$DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a class='menu_top' href='getCategory.php?".$link."idCategory=$idCategory&idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descCategory</u></a><font color='#ffffff'> $DIR_SEPARATOR $descSubcategory";

	      $actualDir = "Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a class='subcategory' href='getCategory.php?".$link."idCategory=$idCategory&idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><u>$descCategory</u></a> $DIR_SEPARATOR $descSubcategory";

	echo $dir;
?>
    </b></font></td>
    <TD align=right class=menu2 width="35%" height="1">
      &nbsp;</TD>
</TR>
</TABLE>
