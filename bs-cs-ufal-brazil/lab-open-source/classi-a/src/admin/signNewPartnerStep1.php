<?
require("partner.php");
$newPartner = new Partner("0");
$newPartner->setPartner($partnerName,$partnerEmail,$partnerURL,$partnerPhones,$partnerAddress,$partnerDescription);
?>
<html>
<head>
<title>Administra&ccedil;&atilde;o Classi-A / Cadastro passo 2: Upload das imagens do estabelecimento.</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<? echo $imagem1 ?>
<h2>Cadastro passo 2: Upload das imagens do estabelecimento. </h2>
<form name="form1" method="post" action="uploadImage.php">
  <table width="75%" border="0" align="center">
    <tr> 
      <td width="42%"> 
        <div align="right">Imagem:</div>
      </td>
      <td width="58%"> 
        <input type="file" name="image" size="50">
      </td>
    </tr>
    <tr> 
      <td width="42%"> 
        <div align="right">Banner:</div>
      </td>
      <td width="58%"> 
        <input type="file" name="file2" size="50">
      </td>
    </tr>
    <tr> 
      <td width="42%">&nbsp;</td>
      <td width="58%"> 
        <div align="right"> 
          <input type="submit" name="Submit" value="Finalizar">
          <input type="submit" name="Apagar" value="Apagar">
		  <input type=hidden name=partnerID value="<? echo $newPartner->getID() ?>">
        </div>
      </td>
    </tr>
  </table>
<p>&nbsp;</p></form>
</body>
</html>
