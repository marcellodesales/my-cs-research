<?  if (($username == null) && ($password == null)){  
        header("Location: /");
    } else {  
?>
<html>
<head>
<title>Confirmação dos dados de cadastro do usuário Classi-A</title>
</head>
<body bgcolor="#ffffff">
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066"><b><font face="Verdana" color="#FFFFFF" size="5">Confirmação
        dos dados de cadastro</font></b></td>
    </tr>
    <tr>
      <td width="138"><img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381"><font face="Verdana" size="2"><p align="justify">Verifique seus
        dados e caso haja algum erro, clique abaixo em Voltar. Caso estejam
        corretos, confirme seu cadastro clicando em Confirmar.</font></p></td>
    </tr>
 </table>
<BR>
  <table border="0" width="70%" cellspacing="1" align="center">
    <tr>
      <td width="20%" bgcolor="#000066">
        <p align="right"><b><font size="2" face="Verdana" color="#FFFFFF">Nome</font></b></td>
      <center>
      <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $announcerName ?>&nbsp;</font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" color="#FFFFFF" size="2"><b>Sobrenome</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $announcerSobrname ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><b><font face="Verdana" size="2" color="#FFFFFF">Sexo</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($announcerSex == "F") echo "Feminino"; else echo "Masculino"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><b><font face="Verdana" size="2" color="#FFFFFF">Faixa
          Etária</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $descBirthRange; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><b><font face="Verdana" size="2" color="#FFFFFF">Profissão</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $descProfession; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Endereço</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><?   $address = ($addressComplement != "") ? $descKindOfAddress." ".$addressComplement : "Não informado"; 
     echo $address; 
?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Número/Apto</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($addressNumber != "")
       echo $addressNumber;
   else echo "Não Informado";
?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Bairro</b></font></td>
        <td width="80%" bgcolor="#EAEAEA"><font size="2" face="Verdana">&nbsp;<?     $neighbor = ($addressNeighborhoodCh == 0) ? "Não informado" : $addressNeighborhood;
       echo $neighbor;
 ?></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>CEP</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($addressZipCode != "") echo $addressZipCode; else echo "Não informado"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Cidade</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($addressLocation != "") echo $addressLocation; else echo "Não informado"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Estado</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $descState ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Pais</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $descCountry ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Nome/usuário</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $username ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Senha</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $password ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Telefone</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($phone != "") echo $phone; else echo "Não informado"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Celular</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($cellphone != "") echo $cellphone; else echo "Não informado"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>Email</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $userEmail ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><font face="Verdana" size="2" color="#FFFFFF"><b>ICQ</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? if ($userICQ != "") echo $userICQ; else echo "Não informado"; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right"><b><font face="Verdana" size="2" color="#FFFFFF">Nos
          conheceu</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">&nbsp;<font size="2" face="Verdana"><? echo $descHowCame ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#FFFFFF" align="right" colspan="2"><BR>
          <form action="<? echo $PHP_SELF; ?>" method="post">
            <input type="hidden" name="announcerName" value="<? echo $announcerName; ?>">
            <input type="hidden" name="announcerSobrname" value="<? echo $announcerSobrname; ?>">
            <input type="hidden" name="announcerSex" value="<? echo $announcerSex; ?>">
            <input type="hidden" name="birthRange" value="<? echo $birthRange; ?>">
            <input type="hidden" name="profession" value="<? echo $profession; ?>">
            <input type="hidden" name="addressKindOfAddress" value="<? echo $addressKindOfAddress; ?>">
            <input type="hidden" name="addressComplement" value="<? echo $addressComplement; ?>">
            <input type="hidden" name="addressNumber" value="<? echo $addressNumber; ?>">
            <input type="hidden" name="addressZipCode" value="<? echo $addressZipCode; ?>">
            <input type="hidden" name="addressNeighborhoodCh" value="<? echo $addressNeighborhoodCh; ?>">
            <input type="hidden" name="addressNeighborhood" value="<? echo ; ?>">
            <input type="hidden" name="addressLocation" value="<? echo $addressLocation; ?>">
            <input type="hidden" name="states" value="<? echo $states; ?>">
            <input type="hidden" name="country" value="<? echo $country; ?>">
            <input type="hidden" name="username" value="<? echo $username; ?>">
            <input type="hidden" name="password" value="<? echo $password; ?>">
            <input type="hidden" name="phone" value="<? echo $phone; ?>">
            <input type="hidden" name="cellphone" value="<? echo $cellphone; ?>">
            <input type="hidden" name="userEmail" value="<? echo $userEmail; ?>">
            <input type="hidden" name="userICQ" value="<? echo $userICQ; ?>">
            <input type="hidden" name="howcame" value="<? echo $howcame; ?>">
            <input type="hidden" name="confirmed" value="true">
            <input type="submit" value="Confirmar">&nbsp;&nbsp;
            <input type="button" value="Voltar" onClick="javascript:history.back()">
	    </form>
		 <form action="/" target="_top" method="post">
            <input type="submit" value="Cancelar">
         </form></td>
     </tr>
    </table>
</body>
</html>
<?  } ?>
