<?	
        $address  = $friendEmail;
        $subject = "$senderName est� recomendando o Classi-A para voc�!";

$message = "<html><body bgcolor=white><table align=center><tr><td width=70%><center><img src='http://www.classi-a.com.br/news/images/logoClassiA.gif'></center>";
        $message .= "<BR><font face=Verdana size=2>$friendName, seu amigo \"$senderName\" <a href='mailto:$senderEmail'><$senderEmail ></a> est� recomendando o <a href='http://www.classia.com.br'>Classi-A</a> para voc�!";
        $message .= "<BR><BR>Voc� gostaria de anunciar online inteiramente de gra�a????<BR>O que voc� procura chegou!!! <a href='http://www.classi-a.com.br'>http://www.classia.com.br</a>";
        $message .= "<BR>O classificado que tem tudo. Tudo!!!<BR>";
        $message .= "Conhe�a o Classi-A e nunca mais queira saber de outro classificado.";
        $message .= "<ul>";
        $message .= "<li>Totalmente GR�TIS;</li>";
        $message .= "<li>Sistema de pesquisa r�pida e refinada;</li>";
        $message .= "<li>F�cil inclus�o de imagem, totalmente GR�TIS;</li>";
        $message .= "<li>Dividido em v�rias categorias para sua navega��o;</li>";
        $message .= "<li>Sistema de p�ginas amarelas;</li>";
        $message .= "<li>Intera��o dos usu�rios atrav�s do ICQ;</li>";
        $message .= "<li>Voc� mesmo controla seus an�ncios por sua conta de acesso;</li>";
        $message .= "<li>Im�veis distribu�do por bairros de Macei�-Alagoas;</li>";
        $message .= "<li><font color=red>Novidade: Cart�o Eletr�nico para a sua empresa!</font></li>";
        $message .= "</ul>";
        $message .= "<b>Conhe�a agora mesmo: <a href='http://www.classia.com.br'>http://www.classi.com.br</a>";
        $message .= "<BR><BR>";
        $message .= "<p align=right>O Classi-A agradece</p></font></td></tr></table></body></html>";

        $extra = "From: Administrador Classi-A<admin@classia.com.br>";
        //$extra .= "\nReturn-Path: <me_and_myself@internet.com>";
        $extra .= "\nContent-Type: text/html";
        mail ($address,$subject,$message,$extra);        
?>
