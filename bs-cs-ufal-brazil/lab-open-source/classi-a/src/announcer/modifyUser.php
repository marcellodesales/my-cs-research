<?
  if ((!isset($announcerID)) || (!isset($LXID))){
     header("Location: /");
  } else {
	
       require("/classia/classes/user.php");
       $user = new User($announcerID);
       $db   = new dbsql("classA");

       if ($sent == "true"){

             if ($birthRange != $user->id_birthRange)
                  $user->setint("cod_faixa_etaria",$birthRange);

             if ($profession != $user->id_work)
                  $user->setint("cod_profissao",$profession);

             if ($addressKindOfAddress != $user->address->id_kindOfAddress)
                  $user->address->setint("cod_tipo_endereco",$addressKindOfAddress);

             if ($addressComplement != $user->address->logradure)
                  $user->address->setStr("logradouro",$addressComplement);

             if ($addressNumber != $user->address->number)
                  $user->address->setInt("numero",$addressNumber);

             if ($addressZipCode != $user->address->zipCode)
                  $user->address->setStr("cep",$addressZipCode);

             if ($addressNeighborhoodCh != $user->address->id_neighborhood)
                  $user->address->setInt("cod_bairro",$addressNeighborhoodCh);

             if ($addressLocation != $user->address->location)
                  $user->address->setStr("localidade",$addressLocation);

             if ($states != $user->address->id_state)
                  $user->address->setInt("cod_estado",$states);

             if ($country != $user->address->id_country)
                  $user->address->setInt("cod_pais",$country);

             if (($password != $user->password) && ($password != "")){
                  $user->setStrUser("password",$password);
                  $passwordChanged = true;
             }

             if ($phone != $user->phone)
                  $user->setStr("fone_anunciante",$phone);

             if ($cellphone != $user->cellPhone)
                  $user->setStr("celular_anunciante",$cellphone);

             if ($userEmail != $user->email)
                  $user->setStr("email_anunciante",$userEmail);

             if ($userICQ != $user->icq)
                  $user->setStr("icq_anunciante",$userICQ);

             $message = "Suas informações pessoais foram atualizadas com sucesso!";
             session_register("message");
             header("Location: index.php?".session_name()."=".session_id());

        } else {
             include("modifyUserPage.php");
        }
  }
?>