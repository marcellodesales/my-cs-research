<?
  if ($keyWords == null){ 
        header("Location: /");
  } else {  ?> 

<HTML><HEAD><TITLE>Classi-A on-Line</TITLE>
<META content="text/html; charset=windows-1252" http-equiv=Content-Type>
<link rel="stylesheet" href="/common/style/classia.css">
<base target="_self">
<script language=JavaScript1.2 src="/js/announceChoices.js"></script>
<script language=JavaScript1.2 src="/js/status.js"></script>
<script language=JavaScript1.2 src="/js/announceUtility.js"></script>
</HEAD>


<BODY aLink=#c0c0c0 bgcolor="white" link=#000000 text=#000000 vLink=#000000 leftMargin="0" rightmargin="0" bottommargin="0" topMargin="0" marginwidth="0" marginheight="0">

<?
//	include("/classia/ad/getSearch/ownedGetSearchKeyWords.php"); //gets the key of the search
	include("/classia/ad/getSearch/ownedGetSearchDir.php");// gets the directory information...
	require("/classia/common/commonMainTitle.php"); //return the main title with the options...
 ?>
<div align="left">
  <table border="0" cellpadding="0" cellspacing="0" width="100%" height="1">
    <tr>
      <td width="100%" bgcolor="#FFCC00"><font size=1>&nbsp;&nbsp;&nbsp;</font></td></tr>
  </table>
</div>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=19 width="100%">
  <TR>
    <TD bgColor="white" vAlign=top width=124 align="center">
<center>
<? include("/classia/ad/getSearch/ownedGetSearchLeft.php"); // gets the kinds of announce for each idCategory...
   include("/classia/ad/getSearch/ownedGetSearchQueries.php");
   include("/classia/common/commonMoreHeader.php");

   include("/classia/common/banners/banners.php");

?>  </center>
      <p><img border="0" src="/common/images/botton_left.gif" width="134" height="9"></P></TD>
  </TR>
</TABLE></TD>

    <TD align=middle bgColor=#ffffff rowSpan=3 vAlign=top width="100%">
      <TABLE border=0 cellPadding=0 cellSpacing=0 height=8>
        <TBODY>
        <TR>
          <TD align=left><img border="0" src="/common/images/curva_td_left.gif" width="8" height="8"></TD>
          <TD align=right width="100%"><IMG border=0 height=1 src="/common/images/espaco.gif" width=1></TD>
          <TD align=right><IMG border=0 height=8 src="/common/images/curva_td.gif" width=8></TD></TR>
      </TBODY></TABLE>
      <div align="center"><center>

<?  include("/classia/ad/getSearch/ownedGetSearchResult.php"); //returns the subcategories of this category....
?>
        </center>
      </div><BR>
      <font face="verdana" size="2">
<?

  if ($totalrows != 0){
	$onlyOne = ($totalrows == 1);
	$quant   = $onlyOne ? "<b>Anúncio</b> relacionado" : "<b>Anúncios</b> relacionados";
?>
    <p align="left">&nbsp;&nbsp;<? echo $quant.": ".$totalrows; ?><Br>
     &nbsp;&nbsp;Mostrando anúncios de <b><? echo $begin ?></b> a <b><? echo $end ?><b/>.</p>
<?
  } else {
?>
	<table width="80%">
	<tr>
		<td><font face="verdana" size="2"><p align="justify">A palavra "<B><? echo $keyWords ?></b>" não foi encontrada nos títulos dos anúncios cadastrados no Classi-A, isso pode ter acontecido por erro de grafia ou porque realmente não existe. Por favor, verifique se ela está escrita corretamente e efetue novamente a pesquisa.<BR><BR>
		<b>Quer falar conosco?</b><BR>
		Se você tiver alguma dúvida, sugestão ou reclamação sobre o Classi-A, mande um e-mail para
		<a href="mailto: webmaster@classi-a.com.br" class="subcategory">webmaster@classi-a.com.br</a>.<BR><BR>
		O Classi-A agradece.</font></p></td>
	</tr>
	</table>
<?
  }
?>
      </font>
      <div align="center"><center>
<?
  $query2 = "SELECT $fields FROM t_anuncio WHERE titulo_anuncio LIKE '%$keyWords%' LIMIT $offset, $limit";
  $result = mysql_query($query2,$db);
  $lines  = mysql_num_rows($result);

  if ($lines != 0){

	include("/classia/ad/getSearch/ownedGetSearchTableAnnounces.php");
  }

?>
<font face='verdana' size=2>

<? if ($totalrows != 0){
     for ($i=0; $i < 9-$totalrows; $i++)
	echo "<BR>";
   } else {
	echo "<BR><BR>";
   }

?>
   <a href="javascript:history.back()" class='subcategory' onMouseOver="return showStatus('Voltar a página anterior')" onMouseOut="clearStatus()">
   <b>Voltar</b></font></a>

<?     require("/classia/common/commonCopyright.php");    ?>

   </TD>
    <TD bgColor=#ffffff vAlign=top width=147>
      <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% height="1">
      <tr>	
          <TD bgColor=#FFCC00 height="1" align="center"><BR>
          
		<? require("/classia/common/commonHowLogin.php"); ?></TD>
      </TR>
      <TR>
         <TD bgColor=white align="center">
           <p align="center">
           <img border="0" src="/common/images/botton.gif" width="146" height="9" valign=bottom></p></TD>
        </TR>
      </TABLE>

<?//      include("/classia/common/commonEmailRefer.php");
 ?>
</TD></TR>
</TBODY>
</TABLE>
</BODY>
</html>
<?  }  ?>
