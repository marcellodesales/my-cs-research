<?  if (($username == null) && ($password == null)){
	header("Location: /");
    } else {
             require("/classia/classes/user.php");
             if ($confirmed){

		  include("ownedConfirmationUserSigninSendMail.php");	

                  $newuser = new User(0);
                  $id = $newuser->id;

                  $newuser->setStr("nome_anunciante",$announcerName);
                  $newuser->setStr("sobrenome_anunciante",$announcerSobrname);
                  $newuser->setStr("sexo_anunciante",$announcerSex);
                  $newuser->setStr("fone_anunciante",$phone);
                  $newuser->setStr("celular_anunciante",$cellphone);
                  $newuser->setStr("email_anunciante",$userEmail);
                  $newuser->setStr("icq_anunciante",$userICQ);
                  $newuser->setInt("cod_profissao",$profession);
                  $newuser->setInt("cod_faixa_etaria",$birthRange);

                  $newuser->setStrUser("login",$username);
                  $newuser->setStrUser("password",$password);
                  $newuser->setStrUser("cod_tipo_usuario",'W');
                  $newuser->setStrUser("ultimo_ip",getenv("REMOTE_ADDR"));
                  $newuser->setIntUser("ultimo_acesso",time());
                  $newuser->setIntUser("cod_chegada",$howcame);

                  $newuser->address->setStr("logradouro",$addressComplement);
                  $newuser->address->setStr("numero",$addressNumber);
                  $newuser->address->setStr("localidade",$addressLocation);
                  $newuser->address->setStr("cep",$addressZipCode);
                  $newuser->address->setInt("cod_tipo_endereco",$addressKindOfAddress);
                  $newuser->address->setInt("cod_bairro",$addressNeighborhoodCh);
                  $newuser->address->setInt("cod_estado",$states);
                  $newuser->address->setInt("cod_pais",$country);

		  include("ownedConfirmationUserSigninPublicity.php");
		  include("newUserSignInSuccessfull.php");
		  
             } else {
                     $secure = new Security();

		     $errors = "<ul>";

                     if ($secure->usernameExist($username)){
			  $errors.= "<li><font color='red'>Já existe um usuário com o nome de usuário <b>$username</b>;</font></li>";
		     }

                     if ($secure->emailExist($userEmail)){
                          $errors.= "<li><font color='red'>Já existe um usuário com o email <b>$userEmail</b>.</font></li>";
		     } 
			if ($announcerName == ""){
		
				$errors .= "<li>Seu primeiro nome deve ser digitado;</li>";
			} 

			if ($announcerSobrname == ""){
		
				$errors .= "<li>Seu sobrenome deve ser digitado;</li>";
			} 

			if ($states == ""){
		
				$errors .= "<li>Seu estado deve ser escolhido;</li>";
			}

			if ($country == ""){
		
				$errors .= "<li>Seu país deve ser escolhido;</li>";
			}

			if ($username == ""){
		
				$errors .= "<li>Seu nome de usuário deve ser digitado;</li>";
			}

			if ($password == ""){
		
				$errors .= "<li>Seu senha deve ser digitada;</li>";
			}

			if ($repassword == ""){
		
				$errors .= "<li>A confirmação da senha deve ser digitada;</li>";
			}

			if ($password != $repassword){
		
				$errors .= "<li>A confirmação da senha não está igual a senha;</li>";
			}

			if ($userEmail == ""){
				$errors .= "<li>Seu email deve ser digitado;</li>";
			}      
			$errors.= "</ul>";

		        if ($errors != "<ul></ul>"){
                              require("confirmationUserSignInError.php");
		        } else {
                      
                               include("confirmationUserSignIn.php");
                        }
             }
     }
?>
