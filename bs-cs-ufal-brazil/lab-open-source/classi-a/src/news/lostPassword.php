<html><title>Envio de dados de usu�rio do Classi-A</title>
<body bgcolor="white" aLink="blue" link="red" text=#000000 vLink="red">
<center><img src="images/logoClassiA.gif" width="250" height="90">
<font size=3 face="verdana">
<?
	require("security.php");
	$secure = new Security();
	
if (!$secure->usernameExist($username)){

?>

<BR><BR>Usu�rio <b><? echo $username ?></b> n�o foi achado em nosso servidor...<BR><BR>
</font>
<table width="80%" align="center">
<tr>
        <td><font size="2" face="verdana">
	   <ul>
            <li>Talvez voc� tenha digitado o nome de usu�rio errado...</li>
            <li>Lembre-se de digitar o nome de usu�rio com letras mai�sculas e min�sculas. (Caso sensitivo)</li></ul>
        </td>
</tr>
</table>

<?
} else {
	$id       = $secure->getAnnouncerID($username);
	$name     = $secure->getAnnouncerFullName($id);
	$email    = $secure->getAnnouncerEmail($id);
	$password = $secure->getUserPassword($id);
	
        $adress  = $email;
        $subject = "Seu Nome de Usu�rio e Senha - Classi-A.com.br";

	$message = "\nCaro $name, aqui est�o as informa��es de registro que voc� requisitou do Classi-A."; 

	$message .= "\n\n    Seu Nome de Usu�rio �: $username";
	$message .= "\n    Sua Senha �: $password";

	$message .= "\n\nVoc� pode se acessar o Classi-A por esse link:";
	$message .= "\nhttp://www.classi-a.com.br/";

	$message .= "\n\nMuito obrigado!!";

        $extra = "From: Webmaster do Classi-A<webmaster@classi-a.com.br>";
        //$extra .= "\nReturn-Path: <me_and_myself@internet.com>";
        //$extra .= "\nContent-Type: text/html";
        mail ($adress,$subject,$message,$extra);
	
	echo "<BR><BR><BR>Seus dados foram enviados para seu email!!!<BR><BR><BR></font>";
}
?>
<font size="2" face="verdana">
Origado por utilizar nossos servi�os!!!
</font>
<BR><BR>
<form>
<input type="button" value="Fechar Janela" onClick="window.close()">
</form>
</center>
<? include("commonCopyright.php");  ?>
</body>
</html>
