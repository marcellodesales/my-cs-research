<?
        $address  = $userEmail;
        $subject  = "$announcerName, seja bem vindo ao Classi-A!";
	
	$message  = "Olá $announcerName, é um prazer tê-lo como usuário do Classi-A!\n";
        $message .= "Aproveite nossos serviços, com a certeza de ter o melhor da internet em Alagoas.";
        $message .= "\nSeus dados de acesso estão listados abaixo. Você precisará destas informações em ordem de acessar sua conta de anúncios e anunciar! Guarde este email como referência futura.\n";	
        $message .= "Você tem direito a 20 (vinte) anúncios. Caso queira ser nosso anunciante especial com um número ilimitado de anúncios, contate-nos por admin@classi-a.com.br.";	

        $message .= "\n\n### Dados de acesso ###\n";	
        $message .= "Seu Nome de Usuário: $username\nSua Senha: $password";

        $message .= "\n\n### Acessando sua conta ###\n";
	$message .= "Entre com seus dados de acesso em \"Login\" localizado no canto superior direito da tela do Classi-A. Ao clicar em \"OK\", você estará em sua conta de anúncios.\n"; 
	$message .= "OBS: Lembre-se de digitar seu Nome de Usuário e sua Senha como aqui estão. São caso sensitivo, ou seja, difere letras maiúsculas e minúsculas.";

        $message .= "\n\n### Adicionando anúncios ###\n";
        $message .= "Quando em sua conta de anúncios, clique em \"Adicionar Anúncio\", e siga as instruções na página seguinte.\n";
        $message .= "OBS: Seja claro e objetivo em seus anúncios, pricipalmente nos títulos dos mesmos. Pois é por eles que nosso sistema de busca procura por anúncios.";

        $message .= "\n\n### Indiquenos o Classi-A por email ###\n";
        $message .= "Se você quiser que seus amigos e familiares saibam que você tem anúncios cadastrados no Classi-A, indique-nos preenchendo um formulário na página principal do Classi-A, localizado no canto inferior esquerdo.";

        $message .= "\n\n### Esquecimento da senha de acesso e perda deste email###\n";
        $message .= "Caso você venha a esquecer sua Senha e não possua mais este email, digite seu Nome de Usuário no formulário \"você esqueceu sua senha?\", localizado no canto inferior direito da página principal do Classi-A, para recebê-la por email.";
	
        $message .= "\n\nMais informações sobre sua conta de anúncios você no item \"Dúvidas\" no menu principal.\n\n";
        $message .= "Caso tenha alguma dúvida, envie um email para duvidas@classi-a.com.br. Estamos a seu dispor!";

        $message .= "\n\n### Vantagens do Classi-A ###";
        $message .= "\n* É totalmente GRÁTIS;";
        $message .= "\n* Sistema de pesquisa rápida e refinada;";
        $message .= "\n* Fácil inclusão de imagem, totalmente GRÁTIS;";
        $message .= "\n* Várias categorias para uma fácil navegação;";
        $message .= "\n* Sistema de páginas amarelas;";
        $message .= "\n* Interação dos usuários através do ICQ;";
        $message .= "\n* Você mesmo controla seus anúncios por sua conta de anúncios;";
        $message .= "\n* Imóveis distribuído por bairros de Maceió-Alagoas;";
        $message .= "\n* Os banners mais baratos do mercado;";
        $message .= "\n* E muito mais!.\n\n";

        $message .= "Para acessar o Classi-A, visite http://www.classi-a.com.br\n\n";
        $message .= "O Classi-A agradece!";

        $extra = "From: Administração do Classi-A<admin@classi-a.com.br>";

        mail ($address,$subject,$message,$extra);
?>
