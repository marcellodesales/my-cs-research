<html>
<head>
<title>Administração Classi-A / Cadastro de sócio</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<h2>Cadastro de novo sócio</h2>
<form name="form1" method="get" action="signNewPartnerStep1.php">
  <table border=0 bgcolor="#000000" align="center" width="75%">
<tr>
<td>
        <table align="center" bgcolor="#ffffff" width="100%" border="0" height="29">
          <tr> 
            <td height="44" width="11%">Nome:</td>
            <td height="44" width="36%"> 
              <input type="text" name="partnerName" size="40" maxlength="30">
            </td>
            <td height="44" width="12%"> 
              <div align="right">E-mail:</div>
            </td>
            <td height="44" width="41%"> 
              <input type="text" name="partnerEmail" size="40" maxlength="30">
            </td>
          </tr>
          <tr> 
            <td height="44" width="11%">URL</td>
            <td height="44" width="36%"> 
              <input type="text" name="partnerURL" value="http://www." size="40" maxlength="100">
            </td>
            <td height="44" width="12%"> 
              <div align="right">Fones:</div>
            </td>
            <td height="44" width="41%"> 
              <input type="text" name="partnerPhones" size="40" maxlength="20">
            </td>
          </tr>
          <tr> 
            <td height="44" width="11%">Endere&ccedil;o:</td>
            <td height="44" colspan="3"> 
              <input type="text" name="partnerAddress" size="80" maxlength="100">
            </td>
          </tr>
          <tr> 
            <td height="29" colspan="4">Descri&ccedil;&atilde;o</td>
          </tr>
          <tr valign="top"> 
            <td height="177" colspan="4"> 
              <div align="center"> 
                <textarea name="partnerDescription" rows="10" cols="90"></textarea>
              </div>
            </td>
          </tr>
          <tr valign="top"> 
            <td height="27" colspan="3">&nbsp;</td>
            <td height="27" width="41%"> 
              <div align="right"> 
                <input type="submit" name="Submit" value="Cadastrar">
                <input type="reset" name="Submit2" value="Apagar">
              </div>
            </td>
          </tr>
        </table>
</td>
</tr>
</table>
</form>
</body>
</html>
