<?	
        $address  = $friendEmail;
        $subject = "$senderName está recomendando o Classi-A para você!";

$message = "<html><body bgcolor=white><table align=center><tr><td width=70%><center><img src='http://www.classi-a.com.br/news/images/logoClassiA.gif'></center>";
        $message .= "<BR><font face=Verdana size=2>$friendName, seu amigo \"$senderName\" <a href='mailto:$senderEmail'><$senderEmail ></a> está recomendando o <a href='http://www.classia.com.br'>Classi-A</a> para você!";
        $message .= "<BR><BR>Você gostaria de anunciar online inteiramente de graça????<BR>O que você procura chegou!!! <a href='http://www.classi-a.com.br'>http://www.classia.com.br</a>";
        $message .= "<BR>O classificado que tem tudo. Tudo!!!<BR>";
        $message .= "Conheça o Classi-A e nunca mais queira saber de outro classificado.";
        $message .= "<ul>";
        $message .= "<li>Totalmente GRÁTIS;</li>";
        $message .= "<li>Sistema de pesquisa rápida e refinada;</li>";
        $message .= "<li>Fácil inclusão de imagem, totalmente GRÁTIS;</li>";
        $message .= "<li>Dividido em várias categorias para sua navegação;</li>";
        $message .= "<li>Sistema de páginas amarelas;</li>";
        $message .= "<li>Interação dos usuários através do ICQ;</li>";
        $message .= "<li>Você mesmo controla seus anúncios por sua conta de acesso;</li>";
        $message .= "<li>Imóveis distribuído por bairros de Maceió-Alagoas;</li>";
        $message .= "<li><font color=red>Novidade: Cartão Eletrônico para a sua empresa!</font></li>";
        $message .= "</ul>";
        $message .= "<b>Conheça agora mesmo: <a href='http://www.classia.com.br'>http://www.classi.com.br</a>";
        $message .= "<BR><BR>";
        $message .= "<p align=right>O Classi-A agradece</p></font></td></tr></table></body></html>";

        $extra = "From: Administrador Classi-A<admin@classia.com.br>";
        //$extra .= "\nReturn-Path: <me_and_myself@internet.com>";
        $extra .= "\nContent-Type: text/html";
        mail ($address,$subject,$message,$extra);        
?>
