<? include("/classia/conf/conf.php");
   require("/classia/classes/announce.php");
   include("/classia/classes/SiteDescription.php");
   $siteDescr = new SiteDescription();
   $descCategory    = $siteDescr->getCategoryDescription($idCategory);
   $descSubcategory = $siteDescr->getSubcategoryDescription($idSubcategory);
   $descEspecification = $siteDescr->getEspecificationDescription($idSubcategory, $idEspecification);
?>
<TABLE background=/common/images/bg1.gif border=0 cellPadding=0 cellSpacing=0 width="100%" height="32">
<TR>
      <td width="65%">
      <? $link  = (isset($linkSession)) ? "?".$linkSession : ""; ?>
      <? $linkD = (isset($linkSession)) ? $linkSession."&" : ""; ?>
          <b>&nbsp;&nbsp;<a href="/<? echo $link ?>" class="menu_top" onMouseOver="return showStatus('Página Inicial!')" onMouseOut="clearStatus()">
          <font size="2" face="Arial" color="#ffffff"><u>Classi-A</u></a>
<?
        $descKOA = ($descKindOfAnnounce == null) ? "Todos os tipos de anuncio" : $descKindOfAnnounce;
        $descKOA = ($descKindOfAnnounce == "Todos") ? "Todos os tipos de anuncio" : $descKOA;
        if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                $dir =  "<font color='#ffffff'>$DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a href='getCategory.php?".$linkD."idCategory=$idCategory' class='menu_top'  onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descCategory</u></a> $DIR_SEPARATOR <a href='getSubcategory.php?".$linkD."idCategory=$idCategory&idSubcategory=$idSubcategory' class='menu_top' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descSubcategory</u></a><font color='#ffffff'> $DIR_SEPARATOR $descEspecification";
                $actualDir =  "Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a href='getCategory.php?".$linkD."idCategory=$idCategory' class='subcategory'  onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><u>$descCategory</u></a> $DIR_SEPARATOR <a href='getSubcategory.php?".$linkD."idCategory=$idCategory&idSubcategory=$idSubcategory' class='subcategory' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\"><u>$descSubcategory</u></a> $DIR_SEPARATOR $descEspecification";

        } else {
                $dir = "<font color='#ffffff'>$DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a href='getCategory.php?".$linkD."idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce&idCategory=$idCategory' class='menu_top' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descCategory</u></a> $DIR_SEPARATOR <a href='getSubcategory.php?".$linkD."idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce&idCategory=$idCategory&idSubcategory=$idSubcategory' class='menu_top' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>$descSubcategory</u></a> <font color='#ffffff'>$DIR_SEPARATOR $descEspecification";
                $actualDir = "Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR <a href='getCategory.php?".$linkD."idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce&idCategory=$idCategory' class='subcategory' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory')\" onMouseOut=\"clearStatus()\"><u>$descCategory</u></a> $DIR_SEPARATOR <a href='getSubcategory.php?".$linkD."idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce&idCategory=$idCategory&idSubcategory=$idSubcategory' class='subcategory' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\"><u>$descSubcategory</u></a> $DIR_SEPARATOR $descEspecification";
        }
	echo $dir;
?>
    </b></font></td>
    <TD align=right class=menu2 width="35%" height="1">
      &nbsp;</TD>
</TR>
</TABLE>
