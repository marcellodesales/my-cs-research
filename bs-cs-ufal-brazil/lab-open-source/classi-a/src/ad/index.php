<?
/* if ((!isset($announcerID)) || (!isset($LXID))){
         header("Location: /");
 } else {
*/
?>
<HTML><HEAD><TITLE>Classi-A Online - Aqui tem tudo!!!</TITLE>
<META content="text/html; charset=windows-1252" http-equiv=Content-Type>
<base target="_self">
<link rel="stylesheet" href="/common/style/classia.css">
<script language=JavaScript1.2 src="/js/validateLogin.js"></script>
<script language=JavaScript1.2 src="/js/status.js"></script>
<script language=JavaScript1.2 src="/js/announceUtility.js"></script>
<SCRIPT language=JavaScript type=text/javascript src="/js/arrows.js"></SCRIPT>

<script language="JavaScript">
<!--

  function mClk(src) {
    if(event.srcElement.tagName=='TD'){
          src.children.tags('A')[0].click();
    }
  }

var tmp_name;
for (var i = 1; i <= 5; i++) {
   tmp_name = "maint_" + i;
   ContentArrowInit("ff9900",tmp_name);
}

// -->

</script>

<?
      require("/classia/classes/security.php");
      $secure = new Security();
?>
</HEAD>
<BODY aLink=#c0c0c0 bgcolor=#FFCC00 link=#000000 text=#000000 vLink=#000000 leftMargin="0" rightmargin="0" bottomMargin="0" topMargin="0" marginwidth="0" marginheight="0">
<?
	require("ownedIndexTop.php");
	require("/classia/common/commonMainTitle.php");
?>

<div align="left">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td width="100%" align="center" height=0><font size="1" face="Verdana">&nbsp;</font></td>
    </tr>
  </table>
</div>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=19 width="100%">
 <tr>
    <TD bgcolor="white" vAlign=top align="CENTER">

      <TABLE bgColor="#FFCC00" border=0 cellPadding=0 cellSpacing=0 width=120>
       <tr bgcolor="#000068">
          <TD>
            <TABLE bgcolor="#000066" border=0 cellPadding=2 cellSpacing=1 width=150 bordercolorlight="#000068" bordercolordark="#000068">

<?         include("ownedIndexCategories.php");    ?> 

	 	<tr>
		<TD>
                  <p align="center"><u>
                  <b><font color="#FFFFFF" face="Verdana" size="1">
                  Outros</font></b></u></p></TD>
              </TR>
           <TR bgcolor="#FFCC00" onmouseover="mOvr(this,'#ffffff');" onmouseout="mOut(this,'#FFCC00');">
                <TD onClick=mClk(this)><a href="/site/contact.php" class=linkMenu onMouseOver="return showStatus('Fale com os responsáveis do Classi-A')" onMouseOut="clearStatus()">
                	<font size="1" face="verdana"><b>Contato</b></a></font></TD>
              </TR>
           <TR bgcolor="#FFCC00" onmouseover="mOvr(this,'#ffffff');" onmouseout="mOut(this,'#FFCC00');">
                <TD onClick=mClk(this)><a href="/site/business.php" class=linkMenu onMouseOver="return showStatus('Gostaria de ver o cartão eletrônico Classi-A de sua empresa?')" onMouseOut="clearStatus()">
                	<font size="1" face="verdana"><b>Publicidade</b></a></font></TD>
              </TR>
             <TR bgcolor="#FFCC00" onmouseover="mOvr(this,'#ffffff');" onmouseout="mOut(this,'#FFCC00');">
                <TD onClick=mClk(this)><a href="/site/development.php" class=linkMenu onMouseOver="return showStatus('Quem desenvolveu o Classi-A???')" onMouseOut="clearStatus()">
                	<font size="1" face="verdana"><b>Desenvolvimento</b></a></font></TD>
              </TR>
              </TABLE>

           </TD>
        </TR>
       </TABLE>

	<BR>
<?
//       include("ownedIndexMaceioWeather.php");
       include("/classia/counter/ownedCounterNumber.php");
?>

   </TD>

    <TD align=middle bgColor=#ffffff rowSpan=3 vAlign=top width="100%" align="center">

      <TABLE border=0 cellPadding=0 cellSpacing=0 height=8>
        <TR>
          <TD align=left></TD>
          <TD align=right width="100%">
             <IMG border=0 height=1 src="/common/images/espaco.gif" width=1></TD>
          <TD align=right>
             <IMG border=0 height=8 src="/common/images/curva_td.gif" width=8></TD>
        </TR>
     </TABLE>

      <div align="center">
<?
      require("ownedIndexApresentation.php");

    if ($wrongUsername != null){    ?>
      <BR>
      <font color="red" face="verdana" size="1">
      <b>A combinação deste login não existe!!!<BR>(<? echo $wrongUsername ?> : ********)</b></font>
<?   }   ?>

<?   require("ownedIndexLastAds.php");    ?>

    </div><BR>
     <center><img src="/common/images/classiaBanner.gif" border="0"></center>
<BR>
<?
     require("classiaCounter.php");
     require("/classia/news/ownedIndexLastestNews.php");
     require("/classia/common/commonCopyright.php"); 	
?>
      
  </TD>
  <TD bgColor=#ffffff vAlign=top width=120>
      
      <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% height="1">
        <TR>
        
<?        require("ownedIndexLoginFields.php");    ?>
              
        </TR>
        <TR>
          <TD bgColor=#FFCC00 height="240">
           <P align="center"><BR>
           <? include("/classia/common/banners/banners.php"); ?>
            <p align="center"><img border="0" src="/common/images/botton.gif" width="144" height="9"></p>
          </TD>
         </TR>
       <tr>
		<td align="center"><BR>
<?		include("ownedIndexGetAdField.php");     ?>
                <BR>
<?		//include("ownedIndexMaceioWeather.php");
?>

             	</td>
 	</tr>

       </TABLE>
  </TD>
  </TR>
  </TABLE>
</BODY>
</html>
<?
// }
  ?>
