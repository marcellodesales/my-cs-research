<html><title>Envio de dados de usuário do Classi-A</title>
<body bgcolor="white" aLink="blue" link="red" text=#000000 vLink="red">
<center><img src="images/logoClassiA.gif" width="250" height="90">
<font size=3 face="verdana">
<?
	require("security.php");
	$secure = new Security();
	
if (!$secure->usernameExist($username)){

?>

<BR><BR>Usuário <b><? echo $username ?></b> não foi achado em nosso servidor...<BR><BR>
</font>
<table width="80%" align="center">
<tr>
        <td><font size="2" face="verdana">
	   <ul>
            <li>Talvez você tenha digitado o nome de usuário errado...</li>
            <li>Lembre-se de digitar o nome de usuário com letras maiúsculas e minúsculas. (Caso sensitivo)</li></ul>
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
        $subject = "Seu Nome de Usuário e Senha - Classi-A.com.br";

	$message = "\nCaro $name, aqui estão as informações de registro que você requisitou do Classi-A."; 

	$message .= "\n\n    Seu Nome de Usuário é: $username";
	$message .= "\n    Sua Senha é: $password";

	$message .= "\n\nVocê pode se acessar o Classi-A por esse link:";
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
Origado por utilizar nossos serviços!!!
</font>
<BR><BR>
<form>
<input type="button" value="Fechar Janela" onClick="window.close()">
</form>
</center>
<? include("commonCopyright.php");  ?>
</body>
</html>
