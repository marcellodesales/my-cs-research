<?  if ($confirmed == null){
        header("Location: /");
    } else {   $passed = true;
?>
<html><head><title>Cadastro de Anunciantes - Classi-A</title>
<script language=JavaScript1.2 src="../js/status.js"></script>
<script language=JavaScript1.2 src="../js/validateLoginCad.js"></script>
<link rel="stylesheet" href="announcersignin.css">
</head>
<body bgcolor="#ffffff" onLoad="document.forms[0].announcerName.focus();">
<form action="confirmationUserSignInFinal.php" onSubmit="return validadeSignUser()">
<div align="center">
  <center>
<TABLE border=0 cellPadding=0 cellSpacing=1 width=65%>
  <TBODY>
  <TR>
    <TD colspan="2" width="459" bgcolor="#000066">
        <font face="Verdana" color="#FFFFFF" size="5">
         &nbsp;<b>Cadastro de usuário</b></font></TD>
  </TR>
  <TR>
    <TD valign="top">
       <A href="http://www.classi-a.com.br/" target=_top>
       <IMG alt=Classi-A border=0 src="images/logoClassiAAnnounces.gif" align="LEFT"></A>
    </td>
    <td valign="top"><p align="justify"><font face="Verdana" size="2">
      Preencha cuidadosamente o formulário abaixo. Suas informações são
       protegidas de&nbsp; acordo com o contrato de uso, que você concordou. Caso desista,
clique abaixo em Cancelar.<br><font color="FF0000">* Campos obrigatórios.<BR><BR>
  </TR>
</TBODY></TABLE>

  </center>
</div>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD bgColor=#000066 colspan=3 width="454" valign="top" height="18"><B><font color="#ffffff" face="Verdana" size="2">&nbsp;&nbsp;&nbsp;Informações
    Pessoais&nbsp;</font></B></TD>
</TR>
<tr>
    <TD valign=middle width="107" height="1" align="right">
        <p align="right">
            <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Nome</b>:</font></p>
    </TD>
    <TD vAlign=top width="337" height="1" colspan="2">
      <INPUT name="announcerName" size=22 maxlength="20"></TD>
</tr>
<tr>
    <TD valign=middle width="107" height="1" align="right">
         <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b> Sobrenome</b>:</font></TD>
    <TD vAlign=top width="337" height="1" colspan="2">
                <INPUT name="announcerSobrname" size=32 maxlength="30"></TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
             <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Sexo</b>:</font></TD>
        <TD vAlign=top width="202" height="1">
             <font face="Verdana" size="2">
             <input CHECKED name="announcerSex" type="radio" value="M"><b>Masculino</b>&nbsp;</font></TD>       <TD vAlign=top width="125" height="1">
             <font face="Verdana" size="2">
             <input name="announcerSex" type="radio" value="F"><b>Feminino</b></font></TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
              <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Faixa Etária</b>:</font></TD>
         <TD vAlign=top width="337" height="1" colspan="2">
     <select name='birthRange' style="background-color: #FFCC00">
             <option value='1'>Até 10 anos</option>
             <option value='2'>10 - 15 anos</option>
             <option value='3'>15 - 20 anos</option>
             <option value='4'>20 - 30 anos</option>
             <option value='5'>30 - 40 anos</option>
             <option value='6'>40 - 50 anos</option>
             <option value='7'>Mais de 50 anos</option>
     </select></TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Profissão</b></font>:</TD>
         <TD vAlign=top width="337" height="1" colspan="2">
     <select name='profession' style="background-color: #FFCC00">
             <option value='3'>acadêmico/professor</option>
             <option value='14'>aposentado</option>
             <option value='12'>autônomo/empresário</option>
             <option value='9'>comerciante/artesão</option>
             <option value='13'>desempregado, à procura de trabalho</option>
             <option value='7'>eclesiástico/administrativo</option>
             <option value='1'>executivo/gerente</option>
             <option value='4'>informática/engenheiro</option>
             <option value='15'>outra</option>
             <option value='2'>profissional liberal (médico, advogado, etc.)</option>
             <option value='11'>estudante/secundarista</option>
             <option value='6'>serviço/atendimento ao cliente</option>
             <option value='5'>técnico/engenheiro</option>
             <option value='10'>estudante/universitário</option>
             <option value='8'>vendas/marketing</option>
     </select></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<tr>
  <TD colspan=4 bgColor=#000066 width="511" valign="top" height="1">
          <B><font color="#ffffff" face="Verdana" size="2">
        &nbsp;&nbsp;&nbsp;Informações Residenciais</font></B></TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Endereço</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
        <select name="addressKindOfAddress" style="background-color: #FFCC00">
               <option value='1'>Rua</option>
               <option value='2'>Avenida</option>
               <option value='3'>Travessa</option>
               <option value='4'>Praça</option>
               <option value='5'>Alameda</option>
               <option value='6'>Outro</option>
          </select><INPUT name=addressComplement size=35>
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Número/Apto</b>:</font></TD>
    <TD valign=middle width="113" height="1">
        <INPUT name="addressNumber" size=14>
</TD>
    <TD vAlign=middle width="95" height="1" align="right">
      <font face="Verdana" size="2"><b>CEP</b>:</font></TD>
    <TD vAlign=middle width="155" height="1">
        <INPUT name=addressZipCode size=16 maxLength="12">
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Bairro</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
      <select name='addressNeighborhoodCh' style="background-color: #FFCC00">
          <option value="0">Escolha</option>
 <option value='9'>Barro Duro</option>
<option value='17'>Bebedouro</option>
<option value='5'>Benedito Bentes</option>
<option value='16'>Centro</option>
<option value='13'>Cruz das Almas</option>
<option value='1'>Farol</option>
<option value='11'>Jacintinho</option>
<option value='18'>Jaraguá</option>
<option value='4'>Jatiúca</option>
<option value='15'>Outro</option>
<option value='14'>Pajuçara</option>
<option value='6'>Poço</option>
<option value='3'>Ponta Verde</option>
<option value='8'>Pontal</option>
<option value='10'>Serraria</option>
<option value='12'>Stela Mares</option>
<option value='2'>Tabuleiro dos Martins</option>
<option value='7'>Trapiche</option>
        </select>
      <br>
<font face="Verdana" size="1" color="#FF0000">
        Escolha a opção "Outro" caso não more em Maceió, Alagoas, Brasil.</font>
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Cidade</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
        <input name="addressLocation" size=24>
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Estado</b>:</font></TD>
    <TD valign=middle width="113" height="1">
      <select name='states' style="background-color: #FFCC00">
<option value='1'>Acre</option><option value='2' SELECTED>Alagoas</option>
<option value='3'>Amapá</option><option value='4'>Amazonas</option><option value='5'>Bahia</option><option value='6'>Ceará</option><option value='7'>Distrito
Federal</option><option value='8'>Espírito Santo</option><option value='9'>Goiás</option><option value='10'>Maranhão</option><option value='11'>Mato
Grosso</option><option value='12'>Mato Grosso do Sul</option><option value='13'>Minas
Gerais</option><option value='14'>Pará</option><option value='15'>Paraíba</option><option value='16'>Paraná</option><option value='17'>Pernambuco</option><option value='18'>Piauí</option><option value='19'>Rio
de Janeiro</option><option value='20'>Rio Grande do Norte</option><option value='21'>Rio
Grande do Sul</option><option value='22'>Rondônia</option><option value='23'>Roraima</option><option value='24'>Santa
Catarina</option><option value='25'>São Paulo</option><option value='26'>Sergipe</option><option value='27'>Tocantins</option>        </select>
</TD>
    <TD vAlign=middle width="95" height="1">
        <p align="right"><font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Pais</b>:</font>
</TD>
    <TD vAlign=middle width="155" height="1">
        <select name="country" style="background-color: #FFCC00">
 <option value='1'>Brasil</option>
<option value='2'>Exterior</option>
        </select>
</TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD colspan=2 bgColor=#000066 width="445" valign="top" height="1">
  <B><font color="#ffffff" face="Verdana" size="2">&nbsp;&nbsp;&nbsp;Informações
  do Usuário Classi-A</font></B></TD>
</TR>
<tr>
    <TD valign=middle width="114" align="right">
        <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>usuário</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=username size=15 maxlength="16"> <font face="Verdana" size="1" color="#FF0000">4 a 8 caracteres.</font></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
     <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Senha</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=password size=15 maxlength="16" type="password"> <font face="Verdana" size="1" color="#FF0000">4 a 16 caracteres.&nbsp;</font></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Confirmação</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=repassword size=15 type="password" maxlength="8"
        <font face="Verdana" size="1" color="#FF0000"> <font face="Verdana" size="1" color="#FF0000">Difere maiúsculas e minúsculas.</font></TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        <font face="Verdana" size="2"><b>Telefone</b></font>:</TD>
         <TD vAlign=middle width="337" height="1">
        <INPUT name="phone" size=15> 
	<font face="Verdana" size="1" color="#FF0000">&nbsp;xx-000-0000 ou xx-0000-0000</font>
  </TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        	<font face="Verdana" size="2"><b>Celular</b></font>:</TD>
         <TD vAlign=middle width="337" height="1">
       		 <INPUT name="cellphone" size=15>
		<font face="Verdana" size="1" color="#FF0000">&nbsp;xx-000-0000 ou xx-0000-0000</font>
  </TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font color="#ff0000">* <font face="Verdana" size="2" color="#000000"><b>Email</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=userEmail size="43" maxlength="50"></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
       <font face="Verdana" size="2"><b>ICQ</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=userICQ size="15" maxlength="50"> <font face="Verdana" size="1" color="#FF0000">Seu
        número de ICQ&nbsp;</font></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD bgColor=#EFEFEF valign="middle" height="1">
  <b><font face="Verdana" size="2" color="#000000">
   Como você nos conheceu?</font>&nbsp;&nbsp;</b><select name='howcame' style="background-color: #FFCC00">
 <option value='5'>Evento ou Outdoor</option>
<option value='6'>Indicação de um amigo</option>
<option value='3'>Jornal ou revista</option>
<option value='7'>Outro</option>
<option value='1'>Publicidade na Internet</option>
<option value='4'>Rádio ou TV</option>
<option value='2'>Sites de busca</option>
     </select>
  </TD>
</TR>
<TR bgcolor="White">
        <TD align="right"><BR>
                <input type="submit" value="Cadastrar">
                <input type="reset" value="Apagar">
                <input type="button" value="Cancelar" onClick="javascript:location.href='/index.php'" target="_top"></TD>
</TR>
</table>
<?     require("/classia/common/commonCopyright.php");    ?>
<input type="hidden" name="descBirthRange" value="">
<input type="hidden" name="descProfession" value="">
<input type="hidden" name="descKindOfAddress" value="">
<input type="hidden" name="descNeighborhood" value="">
<input type="hidden" name="descState" value="">
<input type="hidden" name="descCountry" value="">
<input type="hidden" name="descHowCame" value="">
</form>
</body></html>
<? } ?>
