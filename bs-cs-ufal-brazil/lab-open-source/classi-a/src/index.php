<HTML><HEAD><TITLE>Classi-A / O classificado que tem tudo. Tudo!!!</TITLE>
<META content="text/html; charset=windows-1252" http-equiv=Content-Type>
</HEAD>
<? 
 require("browser.php");

 $brw = new BrowserDetector();

 if ($brw->BROWSER == "Netscape"){
?>
<FRAMESET border=0 rows="100%,*" frameBorder="NO" frameSpacing="0" cols="*">
<? } else { ?>
<frameset rows="100%,*" frameborder= border="0" framespacing="0" cols="*">
<? }
	if (isset($newUserName)){
		$add = "?newUserName=$newUserName";
	} else $add = "";

	if (isset($wrongUsername)){
		$add = "?wrongUsername=$wrongUsername";
	}

        if (isset($announcerID)){
                $add = "?".session_name()."=".session_id();
        } else { //open banner
?>

<?        }
 ?>
  <FRAME name="main" noresize src="/ad/<? echo $add ?>" target="_self" scrolling="yes">
  <frame name="topFrame" scrolling="NO" src="top.php">
</FRAMESET></HTML>
