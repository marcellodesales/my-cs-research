<?
        $address  = $userEmail;
        $subject  = "$announcerName, seja bem vindo ao Classi-A!";
	
	$message  = "Ol� $announcerName, � um prazer t�-lo como usu�rio do Classi-A!\n";
        $message .= "Aproveite nossos servi�os, com a certeza de ter o melhor da internet em Alagoas.";
        $message .= "\nSeus dados de acesso est�o listados abaixo. Voc� precisar� destas informa��es em ordem de acessar sua conta de an�ncios e anunciar! Guarde este email como refer�ncia futura.\n";	
        $message .= "Voc� tem direito a 20 (vinte) an�ncios. Caso queira ser nosso anunciante especial com um n�mero ilimitado de an�ncios, contate-nos por admin@classi-a.com.br.";	

        $message .= "\n\n### Dados de acesso ###\n";	
        $message .= "Seu Nome de Usu�rio: $username\nSua Senha: $password";

        $message .= "\n\n### Acessando sua conta ###\n";
	$message .= "Entre com seus dados de acesso em \"Login\" localizado no canto superior direito da tela do Classi-A. Ao clicar em \"OK\", voc� estar� em sua conta de an�ncios.\n"; 
	$message .= "OBS: Lembre-se de digitar seu Nome de Usu�rio e sua Senha como aqui est�o. S�o caso sensitivo, ou seja, difere letras mai�sculas e min�sculas.";

        $message .= "\n\n### Adicionando an�ncios ###\n";
        $message .= "Quando em sua conta de an�ncios, clique em \"Adicionar An�ncio\", e siga as instru��es na p�gina seguinte.\n";
        $message .= "OBS: Seja claro e objetivo em seus an�ncios, pricipalmente nos t�tulos dos mesmos. Pois � por eles que nosso sistema de busca procura por an�ncios.";

        $message .= "\n\n### Indiquenos o Classi-A por email ###\n";
        $message .= "Se voc� quiser que seus amigos e familiares saibam que voc� tem an�ncios cadastrados no Classi-A, indique-nos preenchendo um formul�rio na p�gina principal do Classi-A, localizado no canto inferior esquerdo.";

        $message .= "\n\n### Esquecimento da senha de acesso e perda deste email###\n";
        $message .= "Caso voc� venha a esquecer sua Senha e n�o possua mais este email, digite seu Nome de Usu�rio no formul�rio \"voc� esqueceu sua senha?\", localizado no canto inferior direito da p�gina principal do Classi-A, para receb�-la por email.";
	
        $message .= "\n\nMais informa��es sobre sua conta de an�ncios voc� no item \"D�vidas\" no menu principal.\n\n";
        $message .= "Caso tenha alguma d�vida, envie um email para duvidas@classi-a.com.br. Estamos a seu dispor!";

        $message .= "\n\n### Vantagens do Classi-A ###";
        $message .= "\n* � totalmente GR�TIS;";
        $message .= "\n* Sistema de pesquisa r�pida e refinada;";
        $message .= "\n* F�cil inclus�o de imagem, totalmente GR�TIS;";
        $message .= "\n* V�rias categorias para uma f�cil navega��o;";
        $message .= "\n* Sistema de p�ginas amarelas;";
        $message .= "\n* Intera��o dos usu�rios atrav�s do ICQ;";
        $message .= "\n* Voc� mesmo controla seus an�ncios por sua conta de an�ncios;";
        $message .= "\n* Im�veis distribu�do por bairros de Macei�-Alagoas;";
        $message .= "\n* Os banners mais baratos do mercado;";
        $message .= "\n* E muito mais!.\n\n";

        $message .= "Para acessar o Classi-A, visite http://www.classi-a.com.br\n\n";
        $message .= "O Classi-A agradece!";

        $extra = "From: Administra��o do Classi-A<admin@classi-a.com.br>";

        mail ($address,$subject,$message,$extra);
?>
