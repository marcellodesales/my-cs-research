<?
  if ($idAnnounce == null){
        header("Location: /");
  } else {  
?> 
<HTML><TITLE>Classi-A on-Line - Informação de Anúncio</TITLE>
<head>
<link rel="stylesheet" href="/common/style/classia.css">
<base target="_self">
<script language=JavaScript1.2 src="/js/status.js"></script>
<script language=JavaScript1.2 src="/js/announceUtility.js"></script>
<script language=JavaScript1.2 src="/js/validateClassiaAnnounce.js"></script>
</HEAD>
<BODY bgcolor="#ffffff" aLink=#c0c0c0 bottomMargin=0 leftMargin=0 link=#000000 rightMargin=0 text=#000000 topMargin=0 vLink=#000000 marginheight="0" marginwidth="0">

<?      require("ownedGetAnnounceDir.php");
	require("/classia/common/commonMainTitle.php"); //return the main title with the options...
?>

<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
<TR>
    <TD bgcolor="#FFCC00" height=0><font size="1" face="Verdana">&nbsp;</font></TD></TR>
</TABLE>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=19 width="100%">
<TR>
    <TD bgColor="White" vAlign=top width=124 align="center">

      <TABLE bgColor=#ffcc00 border=0 cellPadding=0 cellSpacing=0 width=132>
        <TR>
          <TD width=130 align="center">       
	<BR>

<?
	require("/classia/common/banners/banners.php");
 ?>                        
            <P>
            <IMG border=0 height=9 src="images/botton_left.gif" width=134></P></TD></TR>
      </TABLE>
<?
        if ($announce->user->icq != ""){
                require("ownedGetAnnounceRefer.php");
        }
?>
   </TD>
    <TD align=middle bgColor=#ffffff rowSpan=3 vAlign=top width=100%>

      <TABLE border=0 cellPadding=0 cellSpacing=0 height=8>
        <TR>
          <TD align=left><IMG border=0 height=8 src="images/curva_td_left.gif" width=8></TD>
          <TD align=right width="100%"><IMG border=0 height=1 src="images/espaco.gif" width=1></TD>
          <TD align=right><IMG border=0 height=8 src="images/curva_td.gif" width=8></TD>
        </TR>
      </TABLE>

      <DIV align=center>
      <TABLE border=0 cellPadding=0 cellSpacing=0 width="100%">
        <TR>
          <TD align=middle width="28%">
                <IMG align=left border=0 src="images/logoClassiAAnnounces.gif"></TD>
          <TD align=middle width="72%">

<?  if ($announceDoesntExist){ ?>
                <font color="red" face="verdana" size="3"><b>Anúncio não encontrado!</b></TD></TR>
      </TABLE></DIV>

      <BR>
<?
        require("ownedGetAnnounceIdNotFound.php");
    } else {
?>
                <font color="red" face="verdana" size="3"><b>Informação sobre anúncio
		<BR></font></b></TD></TR>
      </TABLE></DIV>

      <BR>
<?
	include("ownedGetAnnounceTableAnnounce.php");   
    }
?>

<BR><BR><font face="verdana" size="2">
   <a href="javascript:history.back()" class='subcategory' onMouseOver="return showStatus('Voltar a página anterior')" onMouseOut="clearStatus()">
   <b>Voltar</b></font></a>

<?   require("commonCopyright.php");  ?>
    </TD>

    <TD bgColor=#ffffff vAlign=top width=147>
      <TABLE border=0 cellPadding=0 cellSpacing=0 height=1 width="100%">
        <TR>
          <TD bgColor=#ffcc00 height=1 align="center"><BR>
          
		<? require("commonHowLogin.php"); ?>
		
	  </TD>
      </TR>
      <TR>
         <TD bgColor=#FFCC00 align="center">
	   
            <p align="center">
            <IMG border=0 height=9 src="images/botton.gif" width=146 valign="bottom"></P>
	</TD></TR></TABLE>
	
<?
    if (!$announceDoesntExist){

	if ($announce->user->icq != ""){
		require("ownedGetAnnounceIcqDoubt.php");
	} else {
		require("ownedGetAnnounceRefer.php");
	}
   }
?>   	
	
</TD></TR></TABLE>
</BODY></HTML>
<? } ?>
