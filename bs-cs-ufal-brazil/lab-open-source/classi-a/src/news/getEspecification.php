<?
  if (($idCategory == null) and ($descCategory == null) and ($idSubCategory == null) and ($descSubCategory == null) and ($idEspecification == null) and ($descEspecification == null)){ 
        header("Location: /");
  } else {  
?> 

<HTML><HEAD><TITLE>Classi-A on-Line</TITLE>
<META content="text/html; charset=windows-1252" http-equiv=Content-Type>
<link rel="stylesheet" href="classia.css">
<base target="_self">
<script language=JavaScript1.2 src="../js/announceChoices.js"></script>
<script language=JavaScript1.2 src="../js/status.js"></script>
<script language=JavaScript1.2 src="../js/announceUtility.js"></script>
</HEAD>

<BODY aLink=#c0c0c0 bgcolor="white" link=#000000 text=#000000 vLink=#000000 leftMargin="0" rightmargin="0" bottommargin="0" topMargin="0" marginwidth="0" marginheight="0">
<?
	include("ownedGetEspecificationDir.php");
	require("commonMainTitle.php"); //return the main title with the options...
?>

<div align="left">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td width="100%" bgcolor="#FFCC00" height=0><font size="1" face="Verdana">&nbsp;</font></td></tr>
  </table>
</div>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=19 width="100%">
  <TBODY>
  <TR>
    <TD bgColor="white" vAlign=top width=124 align="center">
   
<?    include("ownedGetEspecificationKindOfAnnounces.php");  // gets the kinds of announce for each idEspecification...
   
      include("bannersPerCategory.php"); 				
				
?>   
          <p><img border="0" src="images/botton_left.gif" width="134" height="9"></P>
      </TD></TR>
      </TBODY></TABLE>
 </TD>
    <TD align=middle bgColor=#ffffff rowSpan=3 vAlign=top width="100%">
      <TABLE border=0 cellPadding=0 cellSpacing=0 height=8>
        <TBODY>
        <TR>
          <TD align=left><img border="0" src="images/curva_td_left.gif" width="8" height="8"></TD>
          <TD align=right width="100%"><IMG border=0 height=1 src="images/espaco.gif" width=1></TD>
          <TD align=right><IMG border=0 height=8 src="images/curva_td.gif" width=8></TD></TR>
      </TBODY></TABLE>

      <div align="center"><center>

        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td width="28%" align="center"><img border="0" src="images/logoClassiAAnnounces.gif" align="left"></td>
            <td width="72%" align="center">
           </td>
          </tr>
        </table>
        </center>
      </div>

<?   include("commonGetResultDescription.php"); ?>

      </font><BR>
      <div align="center"><center>
<?

  $query2 = $query ." LIMIT $offset, $limit";
  $result = mysql_query($query2,$db);
  $lines  = mysql_num_rows($result);

  if ($lines != 0){

	include("commonGetAnnounce.php");

  }
?><font face='verdana' size=2>

<? if ($totalrows != 0){
     for ($i=0; $i < 9-$totalrows; $i++)
        echo "<BR>";
   } else {
                echo "<BR><BR>";
   }
?>

   <a href="javascript:history.back()" class='subcategory' onMouseOver="return showStatus('Voltar a página anterior')" onMouseOut="clearStatus()">
   <b>Voltar</b></font></a>

<?     require("commonCopyright.php");    ?>      

   </TD>
    <TD bgColor=#ffffff vAlign=top width=147>
      <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% height="1">
          <TD bgColor=#FFCC00 height="1" align="center"><BR>
          
		<? require("commonHowLogin.php"); ?>
		
	  </TD>
      </TR>
      <TR>
         <TD bgColor=#FFCC00 align="center">
	   
             <p align="center">
             <img border="0" src="images/botton.gif" width="146" height="9" valign=bottom></p>
          </TD>
        </TR>
      </TBODY></TABLE>
      
<? 
     require("commonSmallBusiness.php"); 	
    // include("commonEmailRefer.php");       
 ?>         
      
</TD></TR>
</TBODY>
</TABLE>
</BODY>
</html>
<?  }   ?>
