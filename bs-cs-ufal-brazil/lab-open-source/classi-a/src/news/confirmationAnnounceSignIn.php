<?  if (($idAnnouncer == null) && ($confOne != 1)) {  ?>
        header("Location: /");
<?   } else {  ?>
<html>
<head>
<title>Confirmação dos dados de cadastro de anúncio do usuário Classi-A</title>
</head>
<body bgcolor="#ffffff">
  <table border="0" width="529" cellspacing="1" cellpadding="0" align="center">
    <tr>
      <td width="523" colspan="2" bgcolor="#000066">
      	<b><font face="Verdana" color="#FFFFFF" size="5">Anúncio cadastrado!
        </font></b></td>
    </tr>
    <tr>
      <td width="138">
      	<img border="0" src="images/logoClassiAAnnounces.gif" width="130" height="45"></td>
      <td valign="top" width="381">
      	<font face="Verdana" size="2">
      	<p align="justify">
      	<font color="red">O anuncio abaixo foiefetuado com sucesso!</font> Verifique os dados de seu anúncio. Caso contenha erros, basta atualizá-loclicando no ícone atualizar, na sua relação de anúncios.</font></p></td>
    </tr>
 </table>
<BR>
  <table border="0" width="70%" cellspacing="1" align="center">
    <tr>
      <td width="20%" bgcolor="#000066">
          <p align="right"><b><font size="2" face="Verdana" color="#FFFFFF">
        Tipo</font></b></td>
      <center>
      <td width="80%" bgcolor="#EAEAEA">
      	&nbsp;<font size="2" face="Verdana">
      	<? echo $descKindOfAnnounce ?>&nbsp;</font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<font face="Verdana" color="#FFFFFF" size="2">
        	<b>Categoria</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">
        	<? echo $descCategory ?></font></td>
      </tr>
      
<?    if ($subcategory != 0){   ?>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<b><font face="Verdana" size="2" color="#FFFFFF">
        	Subcategoria</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">
        	<? echo $descSubcategory ?></font></td>
      </tr>
<?  }
    if ($especification != 0){
              $descEsp = ($category == 1) ? "Marca" : "Bairro"; ?>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<b><font face="Verdana" size="2" color="#FFFFFF">
        	<? echo $descEsp ?></font></b></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">
        	<? echo $descEspecification ?></font></td>
      </tr>
<?  }      ?>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<b><font face="Verdana" size="2" color="#FFFFFF">
        	Título do anúncio</font></b></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">
        	<? echo $title; ?></font></td>
      </tr>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<font face="Verdana" size="2" color="#FFFFFF">
        	<b>Descrição</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">
        	<? echo $descAnnounce ?></font></td>
      </tr>

<?   if ($price != ""){ ?>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<font face="Verdana" size="2" color="#FFFFFF">
        	<b>Preço</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<font size="2" face="Verdana">R$ <? echo $price ?></font></td>
      </tr>
<?   }    ?>
      <tr>
        <td width="20%" bgcolor="#000066" align="right">
        	<font face="Verdana" size="2" color="#FFFFFF">
        	<b>Período</b></font></td>
        <td width="80%" bgcolor="#EAEAEA">
        	&nbsp;<? echo $announcePeriod; $quant = ($announcePeriod == 1) ? " dia" : " dias"; echo $quant; ?> </td>
      </tr>
      <tr bgcolor="white">
<?
         require("announce.php");
         $user = new User($idAnnouncer);
?>
        <td width="20%" align="right" colspan=2>
          <form>
          <input type="button" value="Gerenciador de Anúncios(<? echo $user->username ?>)" onClick="javascript:location.href='announcerLogin.php?username=<? echo $user->username ?>&password=<? echo $user->password ?>'">
         </form>
        </td>
      </tr>
    </table>

</body>
</html>
<? include("saveNewAnnounce.php");

  } //from top
?>
