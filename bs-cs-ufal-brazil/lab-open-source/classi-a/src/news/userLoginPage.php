<?
 if (!isset($idAnnouncer)){
	header("Location: /"); 
 } else {
  $secure = new Security();
  $util   = new Utility();  

  $secure->updateUserIP($announcer->id,getenv("REMOTE_ADDR"));
  $secure->updateUserAccess($announcer->id,time());

  $get = "SELECT cod_anuncio,cod_anunciante,titulo_anuncio,preco_anuncio,imagem_anuncio FROM t_anuncio where cod_anunciante='$idAnnouncer' order by quando_anuncio desc";

  $secure->connection->query($get);
  $quantAnnouncesPerAnnouncer = (($announcer->kindOfUser == "A") || ($announcer->kindOfUser == "S")) ? 1000 : 20; // quantity of announces per announcer....
  $quantAnnouncesNow = $secure->connection->affected_rows();
?>

<HTML><HEAD><TITLE>Classi-A on-Line</TITLE>
<script language=JavaScript1.2 src="../js/status.js"></script>
<script language="JavaScript1.2" src="../js/announcerUtility.js"></script>

</HEAD>

<BODY aLink=#c0c0c0 bgcolor="white" link=#000000 text=#000000 vLink=#000000 leftMargin="0" rightmargin="0" bottommargin="0" topMargin="0" marginwidth="0" marginheight="0">

<TABLE background=images/bg1.gif border=0 cellPadding=0 cellSpacing=0 width="100%">
  <TR>
    <td><font size="1" color="white" face="verdana">
	&nbsp;<b>Gerenciador de Anúncios do Classi-A</b>
    </td>
    <TD align=right class=menu2 height="20"><b><font size="1" color="white" face="verdana">
      <p align="right"><? echo $date = $util->getDate();  ?>&nbsp;</p></font></b>
    </TD></TR>
</TABLE>

<?     include("ownedUserLoginPageMenu.php");   
?>

<div align="left">
  <table border="0" cellpadding="0" cellspacing="0" width="100%" height="5%">
    <tr>
      <td width="100%" bgcolor="#FFCC00" align="center">
<?  if (isset($perfilUpdated)){  ?>      
      	<font size="1" face="Verdana" color="red">
      	<b>"Seus dados pessoais foram atualizados com sucesso!"</b></font>
<?  }   ?></td></tr>
  </table>
</div>

<TABLE border=0 cellPadding=0 cellSpacing=0 height=19 width="100%">
  <TBODY>
  <TR>
    <TD bgColor="white" vAlign=top width=134 align="center">

      <TABLE bgColor=#FFCC00 border=0 width="100%" cellPadding=0 cellSpacing=0 align="center">
              <TR>
                 <TD><font size="1" face="Verdana"><br>
		<center><B>Instruções</B></center><BR>
		Para visualizar um anúncio, clique sobre o título;<BR>
		&nbsp;<img src="images/docdelete.gif"> Apaga o anúncio em questão;<BR>
                &nbsp;<img src="images/docedit.gif"> Edita o anúncio em questão;<BR>
                &nbsp;<img src="images/foto.gif"> Indica que o anúncio em questão possui uma imagem.

                        <p><img border="0" src="images/botton_left.gif" width="134" height="9"></P>
                  </font>
                 </TD>
              </TR>
     </TABLE>

<?    require("ownedUserLoginPageHelp.php"); ?>

   </TD>

    <TD align=middle bgColor=#ffffff rowSpan=3 vAlign=top width="100%">

      <TABLE border=0 cellPadding=0 cellSpacing=0 height=8>
        <TR>
          <TD align=left><img border="0" src="images/curva_td_left.gif" width="8" height="8"></TD>
          <TD align=right width="100%"><IMG border=0 height=1 src="images/espaco.gif" width=1></TD>
          <TD align=right><IMG border=0 height=8 src="images/curva_td.gif" width=8></TD>
        </TR>
      </TABLE>

      <BR>

<?   include("ownedUserLoginPageTableAnnounces.php");    ?>

      <BR>
     
<?   include("ownedUserLoginPageUserInfor.php");  
if ($quantAnnouncesPerAnnouncer == 20){
    for ($i = 1; $i < 10 - $quantAnnouncesNow; $i++)
       echo "<BR>"; 
} else 
if ($quantAnnouncesNow < 20){
    for ($i = 1; $i < 10 - $quantAnnouncesNow; $i++)
       echo "<BR>";   
}
       include("commonCopyright.php");
?>
   </TD>
    <TD bgColor=#ffffff vAlign=top width=147>
      <TABLE border=0 cellPadding=0 cellSpacing=0 width=100% height="1">
      <tr>	
          <TD bgColor=#FFCC00 height="1" align="center">&nbsp;		
	  </TD>
      </TR>
      <TR>
         <TD bgColor=#FFCC00 height="240" align="center">

            <? include("banners.php");  ?>

           <p align="center">
           <img border="0" src="images/botton.gif" width="146" height="9" valign=bottom></p>

          </TD>
        </TR>
      </TABLE>
	
<?	include("commonSmallBusiness.php");   ?>

</TD></TR>
</TBODY>
</TABLE>
</BODY>
</html>
<? } ?>
