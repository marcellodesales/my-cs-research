<?
     if ((!isset($adID)) || (!isset($announcerID)) || (!isset($LXID))){
        header("Location: /");
     } else {

       require("/classia/classes/announce.php");
       $util = new Utility();
       $ad = new Announce($adID,$announcerID);

 ?>
<html><head><title>Atualização de Anúncio / Classi-A</title>
<script language=JavaScript1.2 src="/js/validateAnunUpd.js"></script>
<link rel="stylesheet" href="sign.css">
</head>
<body bgcolor="#ffffff" onLoad="document.forms[0].quantCarac.value='<? echo strlen($ad->description) ?>'">
<form name="main" action="updateAdInfo.php" onSubmit="return validateSignAnnounce()" method="post" ENCTYPE="multipart/form-data">

<TABLE border=0 cellPadding=0 cellSpacing=0 width=539 align="center">
  <TR>
    <TD colspan="2" width="484" bgcolor="#000080">
        <font face="Verdana" color="#FFFFFF" size="5">
         &nbsp;<b>Atualização de anúncio</b></font></TD>
  </TR>
  <TR>
    <TD valign="middle" width="151" align="center">
       <A href="http://www.classi-a.com.br/" target=_top>
       <IMG alt=Classi-A border=0 src="/common/images/logoClassiAAnnounces.gif" align="LEFT" width="130" height="45"></A>
    </td>
    <td valign="top" align="justify" width="100%">
    	<p align="justify"><font face="Verdana" size="2">Apenas modifique os dados necessário. Clique em atualizar e pronto.
      </font></p></TD>
  </TR>
</TABLE>

<br>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539" style="border: 1 solid #000000">
<TR>
  <TD bgColor=#000080 colspan=2 width="527" valign="top" height="18">
      <B><font color="#ffffff" face="Verdana" size="2">
       &nbsp;&nbsp;&nbsp;Informações do Anúncio</font></b></TD>
</TR>
<tr>
    <TD valign=middle width="133" height="1" align="right">
       <font size="2" face="Verdana"><b>Tipo de Anúncio:</b></font></TD>
    <TD vAlign=top width="384" height="1">
       <select name='kindOfAnnounce' size="1" style="background-color: #FFCC00">
       <?
          $secure = new Security();
          $get = "SELECT * FROM t_tipo_anuncio order by desc_tipo_anuncio";
          $secure->connection->query($get);
          for ($i = 0; $i < $secure->connection->affected_rows(); $i++){
                  $secure->connection->next_record();
                  $idKindOA  = $secure->connection->Record['cod_tipo_anuncio'];
                  $descrKOA  = $secure->connection->Record['desc_tipo_anuncio'];
                  if ($descrKOA != $ad->kindOfAnnounce){ // if goes from up
                            echo "<option value='$idKindOA'>$descrKOA</option>";
                  } else {
                            echo "<option value='$idKindOA' SELECTED>$descrKOA</option>";
                  }
            }
?>
      </select></TD>
</tr>
<tr>
  <TD valign=middle width="133" height="1" align="right">
      <font face="Verdana" size="2"><b>Categoria</b>:</font></TD>

  <TD vAlign=top width="384" height="1">

        <font face="Verdana" size="2">
        <b><? echo $ad->category ?></b></font>
 </TD>
</tr>
<? if ($ad->id_subcategory != 0){ ?>
<tr>
   <TD valign=middle width="133" height="1" align="right">
       <font face="Verdana" size="2"><b>Subcategoria</b>:</font></TD>

   <TD vAlign=top width="384" height="1">
        <font face="Verdana" size="2">
        <b><? echo $ad->subcategory ?></b></font>
   </TD>
</tr>
<? }
   if ($ad->especification->especification != ""){ ?>
<tr>
    <TD valign=middle align="right" width="133">
      <font face="Verdana" size="2"><b>Especificação</b></font>:</TD>

    <TD vAlign=top width="384" height="1">
        <font face="Verdana" size="2">
        <b><? echo $ad->especification->especification ?></b></font>
         </TD>
</tr>
<? } ?>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539" style="border: 1 solid #000000">
<tr>
  <TD colspan=2 bgColor=#000080 width="517" valign="top" height="1">
      <B><font color="#ffffff" face="Verdana" size="2">
      &nbsp;&nbsp;&nbsp;Características do Anúncio</font></b></TD>
</tr>
<tr>
  <TD valign=middle align="right" width="133" align="right">
      <font face="Verdana" size="2"><b>Título do Anúncio</b>:</font></TD>

  <TD vAlign=top width="385" <p align="left">
      <INPUT name="title" size=45 maxlength="50" value="<? echo $ad->title ?>">
      <font color="#FF0000" face="Verdana" size="1"><br>
      ex.: Carro Novo; Grande Casa;</font></TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
       <b><font face="Verdana" size="2">Descrição:<br>
       <input name="quantCarac" size="3" maxlength="3">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font></b></TD>

    <TD valign=middle width="385" height="1">
        <textarea name='descAnnounce' cols=45 rows=4 wrap='physical' maxlength='255' onKeyPress='if (document.forms[0].descAnnounce.value.length >= 255) {alert ("[Error]: A quantidade máxima de caracteres permitida foi atingida."); return false;} else { document.forms[0].quantCarac.value=document.forms[0].descAnnounce.value.length + 1; }'><? echo $desc = $util->getLineDescription($ad->description) ?></textarea><br>
        <font face="Verdana" size="1" color="#FF0000">Máximo de 255 caracteres</font></TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
        <font size="2" face="Verdana"><b>
        &nbsp;Pre&ccedil;o do An&uacute;ncio:</b></font></TD>

    <TD valign=middle width="385" height="1">
    <? $price = ($ad->price == 0.00) ? "Não informado" : $ad->price;  ?>
     <font size="1" face="Verdana" color="#FF0000">
     R$<input name=price size=16 value="<? echo $price ?>">
     ex. 50.00 (cinqüênta ponto zero zero)&nbsp; ou vazio caso não necessite.</font></TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
        <font size="2" face="Verdana"><b>Período do Anúncio:</b></font></TD>

    <TD valign=middle width="385" height="1" align="left">
    	<font size="2" face="Verdana"><b><? echo $ad->endDate; ?></font></b></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539" style="border: 1 solid #000000">
<TR>
  <TD colspan=2 bgColor=#000080 width="520" valign="top" height="1">
      <B><font color="#ffffff" face="Verdana" size="2">
      &nbsp;&nbsp;&nbsp;Imagem do produto anunciado</font></B></TD>
</TR>
<tr>
<? if ($ad->image != ""){  ?>

    <TD valign=middle align="right" width="130" align="right">
        <font face="Verdana" size="2"><b>Imagem atual</b>:</font></p>
    </TD>

    <TD valign=middle width="380">
        <img src='<? echo $UPLOAD_ANNOUNCE_IMAGE_DIR.$ad->id.".".$ad->image; ?>' class='subcategory'>
        <BR>
        <input type="checkbox" name="del" size="20">&nbsp;
        <font size="1" face="Verdana" color="#FF0000">Apagar esta imagem</font></TD>

<? } else {  ?>

    <TD valign=middle align="right" width="130" align="right">
        <font face="Verdana" size="2"><b>Cadastrar Imagem</b>:</font></p>
    </TD>
    <TD valign=middle width="380">
        <input type="file" name="image" size="20">&nbsp;
        <font size="1" face="Verdana" color="#FF0000">Imagem
     no formato gif 256 cores. OBS: Proibida a publicação de imagens
     eróticas, imagens difamatórias ou de natureza irreal. Caso seja
     constatado algum erro, em sua conta, a mesma será apagada sem aviso
     prévil.&nbsp;&nbsp;&nbsp;</font></TD>
<?   }   ?>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539">
<TR bgcolor="White">
        <TD align="right" width="100%">
          <br>&nbsp;<input type="submit" value="Atualizar" name="submit">
          <input type="button" value="Cancelar" name="cancel" onclick="javascript:location.href='index.php?<? echo session_name()."=".session_id(); ?>'"></TD>
</TR>
</table>

<?     require("/classia/common/commonCopyright.php");    ?>

</div>
<input type="hidden" name="adID" value="<? echo $adID ?>">
<input type="hidden" name="<? echo session_name ?>" value="<? echo session_id ?>">
<input type="hidden" name="newPrice" value="">
<input type="hidden" name="sent" value="true">
</form>
</body>
</html>
<?
      }
?>

