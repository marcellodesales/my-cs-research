<? include("/classia/conf/conf.php"); ?>

<TABLE background="/common/images/bg1.gif" border=0 cellPadding=0 cellSpacing=0 height=32 width="100%">
<TR>
      <td width="100%">
          <b>&nbsp;&nbsp;
          <? $linkD = (isset($linkSession)) ? "?".$linkSession : "";
             $link  = (isset($linkSession)) ? $linkSession."&" : "";
          ?>
          <a href="/<? echo $linkD ?>" target="_top" class="menu_top" onMouseOver="return showStatus('Página Inicial!')" onMouseOut="clearStatus()">
          <font size="2" face="Arial" color="#ffffff"><u>Classi-A</u></a>
<?
  require("/classia/classes/announce.php");
  $secure = new Security();

if ($secure->announceExist($adID)){

  $get = "SELECT cod_anunciante FROM t_anuncio WHERE cod_anuncio = '$adID'";
  $secure->connection->query($get);
  $secure->connection->next_record();
  $announcerID = $secure->connection->Record["cod_anunciante"];

  $ad = new Announce($adID,$announcerID);
  $idCategory = $ad->id_category;

  echo "<font color='#ffffff'>$DIR_SEPARATOR ".$ad->kindOfAnnounce ." $DIR_SEPARATOR <a class='menu_top' href='getCategory.php?".$link."idCategory=".$ad->id_category."' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $ad->kindOfAnnounce $DIR_SEPARATOR $ad->category')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>". $ad->category."</u></a>";

  if ((!$ad->subcategory == "") && (!$ad->id_subcategory == 0))
  	echo "<font color='#ffffff'> $DIR_SEPARATOR <a class='menu_top' href='getSubcategory.php?".$link."idCategory=".$ad->id_category."&idSubcategory=".$ad->id_subcategory."' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $ad->kindOfAnnounce $DIR_SEPARATOR $ad->category $DIR_SEPARATOR $ad->subcategory')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>". $ad->subcategory."</u></a>";

  if (!$ad->especification == "")
  	echo "<font color='#ffffff'> $DIR_SEPARATOR <a class='menu_top' href='getEspecification.php?".$link."idCategory=".$ad->id_category."&idSubcategory=".$ad->id_subcategory."&idEspecification=".$ad->especification->id_especification."' onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $ad->kindOfAnnounce $DIR_SEPARATOR $ad->category $DIR_SEPARATOR $ad->subcategory $ad->especification->especification')\" onMouseOut=\"clearStatus()\"><font color='#ffffff'><u>".$ad->especification->especification."</u></a>";
  echo " ".$DIR_SEPARATOR." ".$ad->title;
} else {
		echo "$DIR_SEPARATOR Anúncio com Id $adID inexistente!";
		$adDoesntExist = true;
}
?>
    </b></font></td>
</TR></TABLE>
