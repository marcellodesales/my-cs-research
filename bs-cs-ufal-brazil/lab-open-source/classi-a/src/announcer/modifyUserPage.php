<?   if ((!isset($announcerID)) || (!isset($LXID))){
        header("Location: /");
     } else {  ?>

<html><head><title>Atualização de dados do anunciante / Classi-A</title>
<link rel="stylesheet" href="/common/style/sign.css">
</head>
<body bgcolor="#ffffff">
<form action="<? echo $PHP_SELF ?>" method="post">
<div align="center">
  <center>
<TABLE border=0 cellPadding=0 cellSpacing=1 width=65%>
  <TBODY>
  <TR>
    <TD colspan="2" width="459" bgcolor="#000080">
        <font face="Verdana" color="#FFFFFF" size="5">
         &nbsp;<b>Modificação de Perfil</b></font></TD>
  </TR>
  <TR>
    <TD valign="top">
       <A href="http://www.classi-a.com.br/" target=_top>
       <IMG alt=Classi-A border=0 src="/common/images/logoClassiAAnnounces.gif" align="LEFT"></A>
    </td>
    <td valign="top"><p align="justify"><font face="Verdana" size="2">
      Preencha cuidadosamente o formulário abaixo. Suas informações são
       protegidas de&nbsp; acordo com o contrato de uso, que você concordou.<br><BR></font></p></TD>
  </TR>
</TBODY></TABLE>

  </center>
</div>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD bgColor=#000080 colspan=3 width="454" valign="top" height="18"><B><font color="#ffffff" face="Verdana" size="2">&nbsp;&nbsp;&nbsp;Informações
    Pessoais&nbsp;</font></B></TD>
</TR>
<tr>
    <TD valign=middle width="107" height="1" align="right">
        <p align="right">
            <font face="Verdana" size="2"><b>Nome</b>:</font></p>
    </TD>
    <TD vAlign=top width="337" height="1" colspan="2">
      <font face="Verdana" size="2"><b><? echo $user->name." ".$user->subName; ?></font></b></TD>
</tr>
<tr>
        <TD valign=middle width="107" height="1" align="right">
                 <font face="Verdana" size="2"><b>Sexo</b>:</font></TD>
        <TD vAlign=top width="202" height="1">
                <font face="Verdana" size="2"><b><? echo $sexo = ($user->sex == 'M')?'Masculino':'Feminino' ; ?></b></font>
        </TD>

</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
                 <font face="Verdana" size="2"><b>Faixa Etária</b>:</font></TD>
         <TD vAlign=top width="337" height="1" colspan="2">
     <select name='birthRange' size="1" style="background-color: #FFCC00">
       <?
          $get = "SELECT * FROM t_faixa_etaria order by desc_faixa_etaria";
          $db->query($get);
          for ($i = 0; $i < $db->affected_rows(); $i++){
                  $db->next_record();
                  $idFE  = $db->Record['cod_faixa_etaria'];
                  $descrFE  = $db->Record['desc_faixa_etaria'];
                  if ($idFE != $user->id_birthRange){ // if goes from up
                         echo "<option value='$idFE'>$descrFE</option>";
                  } else {
                         echo "<option value='$idFE' SELECTED>$descrFE</option>";
                  }
            }
      ?>
     </select>
        </TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        <font face="Verdana" size="2"><b>Profissão</b></font>:</TD>
         <TD vAlign=top width="337" height="1" colspan="2">
     <select name='profession' size="1" style="background-color: #FFCC00">
       <?
          $get = "SELECT * FROM t_profissao order by desc_profissao";
          $db->query($get);
          for ($i = 0; $i < $db->affected_rows(); $i++){
                  $db->next_record();
                  $idWK  = $db->Record['cod_profissao'];
                  $descrWK  = $db->Record['desc_profissao'];
                  if ($idWK != $user->id_work){ // if goes from up
                            echo "<option value='$idWK'>$descrWK</option>";
                  } else {
                            echo "<option value='$idWK' SELECTED>$descrWK</option>";
                  }
            }
      ?>
     </select></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<tr>
  <TD colspan=4 bgColor=#000080 width="511" valign="top" height="1">
  <B><font color="#ffffff" face="Verdana" size="2">&nbsp;&nbsp;&nbsp;Informações
  Residenciais</font></B></TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Endereço</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
        <select name="addressKindOfAddress" style="background-color: #FFCC00">
       <?
          $get = "SELECT * FROM t_tipo_endereco order by desc_tipo_endereco";
          $db->query($get);
          for ($i = 0; $i < $db->affected_rows(); $i++){
                  $db->next_record();
                  $idAddress  = $db->Record['cod_tipo_endereco'];
                  $descrAddress  = $db->Record['desc_tipo_endereco'];
                  if ($idAddress != $user->address->id_kindOfAddress){ // if goes from up
                            echo "<option value='$idAddress'>$descrAddress</option>";
                  } else {
                            echo "<option value='$idAddress' SELECTED>$descrAddress</option>";
                  }
            }
      ?>
       </select>

        <INPUT name=addressComplement size=35 value="<? echo $user->address->logradure ?>">

</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Número/Apto</b>:</font></TD>
    <TD valign=middle width="113" height="1">
        <INPUT name="addressNumber" size=14 value="<? echo $user->address->number; ?>">
</TD>
    <TD vAlign=middle width="95" height="1" align="right">
       <font face="Verdana" size="2"><b>CEP</b>:</font></TD>
    <TD vAlign=middle width="155" height="1">
        <INPUT name=addressZipCode size=16 maxLength="12" value="<? echo $user->address->zipCode ?>">
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2">
        <b>Bairro</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
      <select name='addressNeighborhoodCh' style="background-color: #FFCC00">
      <?
          $get = "SELECT * FROM t_bairro order by desc_bairro";
          $db->query($get);
          for ($i = 0; $i < $db->affected_rows(); $i++){
                  $db->next_record();
                  $idNeighborhood  = $db->Record['cod_bairro'];
                  $descrNeighborhood = $db->Record['desc_bairro'];
                  if ($idNeighborhood != $user->address->id_neighborhood){ // if goes from up
                            echo "<option value='$idNeighborhood'>$descrNeighborhood</option>";
                  } else {
                            echo "<option value='$idNeighborhood' SELECTED>$descrNeighborhood</option>";
                  }
            }
      ?>
        </select>
      <br>
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Cidade</b>:</font></TD>
    <TD valign=middle width="383" height="1" colspan="3">
        <input name="addressLocation" size=24 value="<? echo $user->address->location ?>">
</TD>
</tr>
<tr>
    <TD valign=middle width="120" height="1" align="right">
        <font face="Verdana" size="2"><b>Estado</b>:</font></TD>
    <TD valign=middle width="113" height="1">
      <select name='states' style="background-color: #FFCC00">
      <?
          $get = "SELECT * FROM t_estado order by desc_estado";
          $db->query($get);
          for ($i = 0; $i < $db->affected_rows(); $i++){
                  $db->next_record();
                  $idState = $db->Record['cod_estado'];
                  $descrState = $db->Record['desc_estado'];
                  if ($idState != $user->address->id_state){ // if goes from up
                            echo "<option value='$idState'>$descrState</option>";
                  } else {
                            echo "<option value='$idState' SELECTED>$descrState</option>";
                  }
            }
      ?>
      </select>
</TD>
    <TD vAlign=middle width="95" height="1">
        <p align="right"><font face="Verdana" size="2"><b>Pais</b>:</font>
</TD>
    <TD vAlign=middle width="155" height="1">
        <select name="country" style="background-color: #FFCC00">

        <? if ($user->address->id_country == 1){
                echo "<option value='1' SELECTED>Brasil</option>";
                echo "<option value='2'>Exterior</option>";
          } else {
                echo "<option value='1'>Brasil</option>";
                echo "<option value='2' SELECTED>Exterior</option>";
             }
        ?>

        </select>
</TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD colspan=2 bgColor=#000080 width="445" valign="top" height="1">
  <B><font color="#ffffff" face="Verdana" size="2">&nbsp;&nbsp;&nbsp;Informações
  do Usuário Classi-A</font></B></TD>
</TR>
<tr>
    <TD valign=middle width="114" align="right">
        <font face="Verdana" size="2"><b>usuário</b>:</font></TD>
    <TD valign=middle width="330">
        <font face="Verdana" size="2"><b><? echo $user->username; ?></b></font></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font face="Verdana" size="2"><b>Nova Senha</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=password size=15 maxlength="8" type="password" value="">
         <font face="Verdana" size="1" color="#FF0000">
         4 a 8 caracteres.&nbsp;</font></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font face="Verdana" size="2"><b>Confirme Senha</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=repassword size=15 type="password" maxlength="8"
        <font face="Verdana" size="1" color="#FF0000"> <font face="Verdana" size="1" color="#FF0000">Difere maiúsculas e
        minúsculas.</font></TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        <font face="Verdana" size="2"><b>Telefone</b></font>:</TD>
         <TD vAlign=middle width="337" height="1">
        <INPUT name="phone" size=15 value="<? echo $user->phone ?>"> <font face="Verdana" size="1" color="#FF0000">Comercial/Residencial</font>
  </TD>
</tr>
<tr>
         <TD valign=middle width="107" height="1" align="right">
        <font face="Verdana" size="2"><b>Celular</b></font>:</TD>
         <TD vAlign=middle width="337" height="1">
        <INPUT name="cellphone" size=15 value="<? echo $user->cellPhone ?>"><font face="Verdana" size="1" color="#FF0000">&nbsp;
        xx-000-0000 ou xx-0000-0000</font>
  </TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font face="Verdana" size="2"><b>Email</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=userEmail size="43" maxlength="50" value="<? echo $user->email ?>"></TD>
</tr>
<tr>
    <TD valign=middle width="114" align="right">
        <font face="Verdana" size="2"><b>ICQ</b>:</font></TD>
    <TD valign=middle width="330">
        <INPUT name=userICQ size="15" maxlength="50" value="<? echo $user->icq ?>"> <font face="Verdana" size="1" color="#FF0000">Seu
        número de ICQ&nbsp;</font></TD>
</tr>
</table>

<TABLE align=center bgColor=#efefef border=0 cellPadding=4 cellSpacing=0 width="65%">
<TR>
  <TD bgColor=#EFEFEF width="454" valign="middle" height="1">
  <b><font face="Verdana" size="2">Como você
  nos conheceu:&nbsp;&nbsp; <? echo $user->howArrived ?></b></font>
  </TD>
</TR>
<TR bgcolor="White">
        <TD align="right" width="454"><BR>
                <input type="submit" value="Atualizar">
		<input type="hidden" name="sent" value="true">
		<input type="button" value="Cancelar" onClick="javascript:location.href='index.php?<? echo session_name()."=".session_id(); ?>'">
		</form>

        </TD>
</TR>
</table>
</body></html>
<? } ?>
