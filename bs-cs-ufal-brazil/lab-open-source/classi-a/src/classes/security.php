<?
  require("/classia/classes/dbmysql.php");

class Security{
      var $database   = "classA";
      var $connection;
      var $partnersArray;

      function Security(){
                $this->connection = new dbsql($this->database);
      }

      function personExist($id_person){
                $checkPerson = "SELECT cod_anunciante FROM t_anunciante WHERE cod_anunciante='$id_person'";
                $this->connection->query($checkPerson);
                return ($this->connection->affected_rows() == 1);
      }

      function partnerExist($id_partner){
                $checkPartner = "SELECT cod_associado FROM t_associado WHERE cod_associado='$id_partner'";
                $this->connection->query($checkPartner);
                return ($this->connection->affected_rows() == 1);
      }

      function usernameExist($userName){
                $checkUserName = "SELECT login FROM t_usuario WHERE login='$userName'";
                $this->connection->query($checkUserName);
                return ($this->connection->affected_rows() == 1);
      }

      function emailExist($email){
                $checkEmail = "SELECT email_anunciante FROM t_anunciante WHERE email_anunciante='$email'";
                $this->connection->query($checkEmail);
                return ($this->connection->affected_rows() == 1);
      }

      function userExist($username,$password){
                $get = "SELECT cod_anunciante FROM t_usuario WHERE login='$username' AND password='$password'";
                $this->connection->query($get);
                return ($this->connection->affected_rows() == 1);
      }

      function adminExist($username,$password){
                $get = "SELECT cod_anunciante FROM t_usuario WHERE cod_tipo_usuario='A' AND login='$username' AND password='$password'";
                $this->connection->query($get);
                return ($this->connection->affected_rows() == 1);
      }

      function announceImageExist($id_announce){
                $checkAnnounceImage = "SELECT imagem_anuncio FROM t_anuncio WHERE cod_anuncio='$id_announce'";
                $this->connection->query($checkAnnounceImage);
                return ($this->connection->affected_rows() == 1);
      }

      function announceExist($id_announce){
                $checkAnnounce = "SELECT cod_anuncio FROM t_anuncio WHERE cod_anuncio='$id_announce'";
                $this->connection->query($checkAnnounce);
                return ($this->connection->affected_rows() == 1);
      }

      function personAddressExist($id_announce){
                $checkAnnounce = "SELECT cod_anunciante FROM t_endereco WHERE cod_anunciante='$id_announce'";
                $this->connection->query($checkAnnounce);
                return ($this->connection->affected_rows() == 1);
      }

      function getAnnouncerID($userName){
                $get = "SELECT cod_anunciante FROM t_usuario WHERE login='$userName'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record["cod_anunciante"];
      }

      function especificationOfAnnounceExist($id_announce,$id_category){
                if ($id_category == 1){ //it's a vehicle...
                        $checkEspecif = "SELECT cod_anuncio FROM t_veiculo WHERE cod_anuncio='$id_announce'";
                        $this->connection->query($checkEspecif);
                        return ($this->connection->affected_rows() == 1);
                } else
                if ($id_category == 2){ //it's an imovel....
                        $checkEspecif = "SELECT cod_anuncio FROM t_imovel WHERE cod_anuncio='$id_announce'";
                        $this->connection->query($checkEspecif);
                        return ($this->connection->affected_rows() == 1);
                }
      }

      function getQuantAnnouncer(){
                $checkUser = "SELECT COUNT(*) FROM t_anunciante";
                $this->connection->query($checkUser);
                $this->connection->next_record();
                return $this->connection->Record["COUNT(*)"];
      }

      function getQuantAnnounce(){
                $checkUser = "SELECT COUNT(*) from t_anuncio";
                $this->connection->query($checkUser);
                $this->connection->next_record();
                return $this->connection->Record["COUNT(*)"];
      }

      function getQuantSubCategory($id_subCategory){
                $checkUser = "SELECT cod_subcategoria_anuncio FROM t_anuncio where cod_subcategoria_anuncio=$id_subCategory";
                $this->connection->query($checkUser);
                return $this->connection->affected_rows();
      }

      function getUserLogin($id){
                $getUser = "SELECT login from t_usuario where cod_anunciante='$id'";
                $this->connection->query($getUser);
                $this->connection->next_record();
                return $this->connection->Record["login"];
      }

      function getUserPassword($id){
                $getUser = "SELECT password from t_usuario where cod_anunciante='$id'";
                $this->connection->query($getUser);
                $this->connection->next_record();
                return $this->connection->Record["password"];
      }

      function getAnnouncerName($id){
                $getUser = "SELECT nome_anunciante from t_anunciante where cod_anunciante='$id'";
                $this->connection->query($getUser);
                $this->connection->next_record();
                return $this->connection->Record["nome_anunciante"];
      }

      function getAnnouncerEmail($id){
                $getUser = "SELECT email_anunciante from t_anunciante where cod_anunciante='$id'";
                $this->connection->query($getUser);
                $this->connection->next_record();
                return $this->connection->Record["email_anunciante"];
      }

      function getAnnouncerFullName($id){
                $getUser = "SELECT nome_anunciante,sobrenome_anunciante from t_anunciante where cod_anunciante='$id'";
                $this->connection->query($getUser);
                $this->connection->next_record();
                return $this->connection->Record["nome_anunciante"]." ".$this->connection->Record["sobrenome_anunciante"];
      }

      function getQuantCategory(){
                $checkUser = "SELECT cod_categoria FROM t_categoria_anuncio";
                $this->connection->query($checkUser);
                return $this->connection->affected_rows();
      }

      function updateUserIP($idUser,$ip){
                $set = "UPDATE t_usuario set ultimo_ip='$ip' WHERE cod_anunciante='$idUser'";
                $this->connection->query($set);
      }

      function updateUserAccess($idUser,$time){
                $set = "UPDATE t_usuario set ultimo_acesso='$time' WHERE cod_anunciante='$idUser'";
                $this->connection->query($set);
      }

      function getPartnerQuantity(){
                $get = "SELECT count(*) quantPartner FROM t_associado";
                $this->connection->query($get);
		$this->connection->next_record();
		return $this->connection->Record["quantPartner"];	
      }

      function getPartnerLimit($position){
               $get = "SELECT cod_associado, nome_associado, banner_associado FROM t_associado LIMIT $position, 1";
               $this->connection->query($get);
               $this->connection->next_record();
               return $this->connection->Record;
      }

      function getAllPartners(){
	       $get = "SELECT cod_associado, nome_associado FROM t_associado order by nome_associado";
               $this->connection->query($get);
               while($this->connection->next_record()){
	       		$cod  = $this->connection->Record["cod_associado"];
                        $name = $this->connection->Record["nome_associado"];

                        $this->partnersArray[$cod]["cod_associado"]  = $cod;
                        $this->partnersArray[$cod]["nome_associado"]  = $name;
                }
      }

      function deleteAnnounce($idAnnounce){
           if ($this->announcerOwnAnnounce($idAnnouncer,$idAnnounce)){
                if ($announce->id_category == 1){
                     $del = "DELETE FROM t_veiculo WHERE cod_anuncio = '$idAnnounce'";
                     $announce->connection->query($del);
                }
                if ($announce->id_category == 2){
                     $del = "DELETE FROM t_imovel WHERE cod_anuncio = '$idAnnounce'";
                     $announce->connection->query($del);
                }

                if ($announce->image != ""){
                    $cmd = "rm /classia/images/".$idAnnounce.".".$announce->image;
                    passthru($cmd);
                }

                $del = "DELETE FROM t_anuncio WHERE cod_anuncio = '$idAnnounce'";
                $announce->connection->query($del);
           } else echo "Security Error: Não foi possível deletar anúncio: Anúncio $idAnnounce não pertence a $idAnnounce.";
      }      
}
?>
