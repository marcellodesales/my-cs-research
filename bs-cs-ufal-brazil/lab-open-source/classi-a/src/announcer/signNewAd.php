<?   if ((!isset($announcerID)) || (!isset($LXID))){
        header("Location: /");
     } else {  
if (isset($announcerID) && isset($price)){
	include("saveNewAD.php");
} else {

?>


<html><head><title>Cadastro de Anuncios - Classi-A</title>
<script language=JavaScript1.2 src="/js/validateAnunCad.js"></script>
<script language=JavaScript1.2 src="/js/distributeValues.js"></script>
<link rel="stylesheet" href="/common/style/sign.css">
</head>
<body bgcolor="#ffffff" onLoad="fillSelectFromArray(document.main.category, forCategory[0]);  fillSelectFromArray(document.main.subcategory, clearAll[0]); fillSelectFromArray(document.main.especification, clearAll[0]);">

<form method="post" name="main" onSubmit="return validateSignAnnounce()" action="<? echo $PHP_SELF ?>" ENCTYPE="multipart/form-data">
<TABLE border=0 cellPadding=0 cellSpacing=0 width=539 align="center">
  <TR>
    <TD colspan="2" width="484" bgcolor="#000080">
        <font face="Verdana" color="#FFFFFF" size="5">
         &nbsp;<b>Cadastro de anúncio</b></font></TD>
  </TR>
  <TR>
    <TD valign="middle" width="151" align="center">
       <p align="center">
       <A href="http://www.classi-a.com.br/" target=_top>
       <center><IMG alt=Classi-A border=0 src="/common/images/logoClassiAAnnounces.gif" align="LEFT" width="130" height="45"></A></center>
       </p>
    </td>
    <td valign="top" align="justify" width="100%">
	<p align="justify"><font face="Verdana" size="2">
	Caro anunciante, preencha cuidadosamente o formulário abaixo para que
      nenhuma informação seja divulgada de forma incorreta ou despretensiosa
      pois isso é de sua responsabilidade.
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
             <option value='0'>[Selecione]</option>
             <option value='2'>Aluguel</option>
             <option value='6'>Compra</option>
             <option value='5'>Outros</option>
             <option value='4'>Troca</option>
             <option value='1'>Venda</option>
             <option value='3'>Venda ou Troca</option>
             </select></TD>
</tr>
<tr>
  <TD valign=middle width="133" height="1" align="right">
      <font face="Verdana" size="2"><b>Categoria</b>:</font></TD>
  <TD vAlign=top width="384" height="1">
      <select size="1" name="category" onChange="fillSelectFromArray(document.main.subcategory, clearAll[0]); fillSelectFromArray(document.main.especification, clearAll[0]); fillSelectFromArray(document.main.subcategory, ((this.selectedIndex == -1) ? null : forSubcategory[this.selectedIndex-1]));" style="background-color: #FFCC00">
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
      </select>
 </TD>
</tr>
<tr>
   <TD valign=middle width="133" height="1" align="right">
       <font face="Verdana" size="2"><b>Subcategoria</b>:</font></TD>
   <TD vAlign=top width="384" height="1">
       <select size="1" name="subcategory" onChange="erase()" style="background-color: #FFCC00">
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
           <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
       </select>
   </TD>
</tr>
<tr>
    <TD valign=middle align="right" width="133">
      <font face="Verdana" size="2"><b>Especificação</b></font>:</TD>
    <TD vAlign=top width="384" height="1">
      <select size="1" name="especification" style="background-color: #FFCC00">
            <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
            <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
            <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
            <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
            <OPTION>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
      </select>
         </TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539" style="border: 1 solid #000000">
<tr>
  <TD colspan=2 bgColor=#000080 width="517" valign="top" height="1">
      <B><font color="#ffffff" face="Verdana" size="2">
      &nbsp;&nbsp;&nbsp;Características do Anúncio</font></b></TD>
</tr>
<tr>
  <TD valign=middle align="right" width="133" align="right">
      <font face="Verdana" size="2"><b>Título do Anúncio</b>:</font>
  </TD>
  <TD vAlign=top width="385" <p align="left">
      <INPUT name="title" size=45 maxlength="50">
      <font color="#FF0000" face="Verdana" size="1">
      ex.: Carro Novo; Grande Casa;</font>
  </TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
       <b><font face="Verdana" size="2">Descrição:<br><input name="quantCarac" size="3" maxlength="3" disabled>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </font></b></TD>
    <TD valign=middle width="385" height="1">
        <textarea name='descAnnounce' cols=45 rows=4 wrap='physical' maxlength='255' onKeyPress='if (document.forms[0].descAnnounce.value.length >= 255) {alert ("[Error]: A quantidade máxima de caracteres permitida foi atingida."); return false;} else { document.forms[0].quantCarac.value=document.forms[0].descAnnounce.value.length + 1; }'></textarea><br>
        <font face="Verdana" size="1" color="#FF0000">Máximo de 255 caracteres</font></TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
        <font size="2" face="Verdana"><b>
        &nbsp;Pre&ccedil;o do An&uacute;ncio:</b></font></TD>
    <TD valign=middle width="385" height="1">
     <font size="1" face="Verdana" color="#FF0000">R$<input name=price size=16>
     ex. 50,00.&nbsp; ou vazio caso não necessite.</font></TD>
</tr>
<tr>
    <TD valign=middle width="133" height="1" align="right">
        <font size="2" face="Verdana"><b>Período do Anúncio:</b></font></TD>
    <TD valign=middle width="385" height="1" align="left">
        <select name="announcePeriod" size="1" style="background-color: #FFCC00">
               <OPTION value="1">1 dia</OPTION>
               <OPTION value="3">3 dias</OPTION>
               <OPTION value="5">5 dias</OPTION>
               <OPTION value="10">10 dias</OPTION>
        </select><font size="1" face="Verdana" color="#FF0000">Tempo disponível na página.</font></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539" style="border: 1 solid #000000">
<TR>
  <TD colspan=2 bgColor=#000080 width="520" valign="top" height="1">
      <B><font color="#ffffff" face="Verdana" size="2">
      &nbsp;&nbsp;&nbsp;Imagem do produto anunciado</font></B></TD>
</TR>
<tr>
    <TD valign=middle align="right" width="130" align="right">
        <font face="Verdana" size="2"><b>&nbsp;Caminho</b>:</font></p>
    </TD>
    <TD valign=middle width="380">
        <input type="file" name="image" size="20">&nbsp;
        <font size="1" face="Verdana" color="#FF0000">Imagem
     com no máximo 50Kb serão permitidas. Proibida a publicação de imagens
     eróticas, imagens difamatórias ou de natureza irreal. Caso seja
     constatado algum erro, em sua conta, a mesma será apagada sem aviso
     prévil.&nbsp;&nbsp;&nbsp;</font></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="539">
<TR bgcolor="#ffffff">
        <TD align="right" width="100%">
          <br>&nbsp;
          <input type="submit" value="Cadastrar" name="submit">
          <input type="button" value="Cancelar" onClick="javascript:location.href='index.php?<? echo session_name()."=".session_id(); ?>'">
          <input type="hidden" name="<? echo session_name() ?>" value="<? echo session_id() ?>">
	  <? session_register("confOne");
             $confOne = 1;
          ?>
	 </form>
	</TD>
       </TR>
</table>
</div>
<? include("/classia/common/commonCopyright.php"); ?>
</body>
</html>
<? } 
}
?>
