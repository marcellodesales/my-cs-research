<?
require("/classia/classes/security.php");
class Address{
      var $database      = "classA";
      var $connection;
      var $id_person     = "";
      //to get these information we use a particular get method
      var $kindOfAddress = "";
      var $neighborhood  = "";
      var $country       = "";
      var $state         = "";
      //to get these information we use a global get method
      var $logradure     = "";
      var $number        = "";
      var $location      = "";
      var $zipCode       = "";
      //to get id's from int values....
      var $id_state         = "";
      var $id_neighborhood  = "";
      var $id_country       = "";
      var $id_kindOfAddress = "";
      var $result;

      function Address($id_person){
                $this->connection = new dbsql($this->database);
                $secure = new Security();
                if ($secure->personAddressExist($id_person)){
                       $this->id_person = $id_person;
                       $this->result    = $this->getAddress();

                       $this->number    = $this->result["numero"];
                       $this->location  = $this->result["localidade"];
                       $this->zipCode   = $this->result["cep"];
                       $this->logradure = $this->result["logradouro"];

                       $this->id_state         = $this->result["cod_estado"];
                       $this->id_neighborhood  = $this->result["cod_bairro"];
                       $this->id_country       = $this->result["cod_pais"];
                       $this->id_kindOfAddress = $this->result["cod_tipo_endereco"];

                       $this->kindOfAddress = $this->result["desc_tipo_endereco"];
                       $this->neighborhood  = $this->result["desc_bairro"];
                       $this->state         = $this->result["desc_estado"];
                       $this->country       = $this->result["desc_pais"];

                } else {
                       $this->id_person = $id_person;
                       $this->insertNewAddress($id_person);
                }
      }

      function insertNewAddress($id_person){
                $ins = "INSERT INTO t_endereco (cod_anunciante) VALUES ('$id_person')";
                $this->connection->query($ins);
      }

      function get($field){
                $id  = $this->id_person;
                $get = "SELECT ".$field." FROM t_endereco WHERE cod_anunciante = '$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
      }

      function getAddress(){
                $id  = $this->id_person;
                $get = "SELECT t_endereco.*,desc_tipo_endereco,desc_bairro,desc_estado,desc_pais FROM t_endereco,t_tipo_endereco,t_bairro,t_estado,t_pais WHERE t_endereco.cod_tipo_endereco = t_tipo_endereco.cod_tipo_endereco AND t_endereco.cod_bairro = t_bairro.cod_bairro AND t_endereco.cod_estado = t_estado.cod_estado AND t_endereco.cod_pais = t_pais.cod_pais AND cod_anunciante='$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
      }

      function setInt($field,$value){
              $id  = $this->id_person;
              $set = "UPDATE t_endereco SET $field=$value WHERE cod_anunciante = '$id'";
              $this->connection->query($set);
      }

      function setStr($field,$value){
              $id  = $this->id_person;
              $set = "UPDATE t_endereco set $field='$value' WHERE cod_anunciante = '$id'";
              $this->connection->query($set);
      }

      function getNeighborhood(){
                $id_Neighborhood = $this->id_neighborhood;

                $get = "SELECT desc_bairro FROM t_bairro WHERE cod_bairro = $id_Neighborhood";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_bairro'];
      }

      function getKindOfAddress(){
                $id_KindOfAddress = $this->id_kindOfAddress;

                $get = "SELECT desc_tipo_endereco FROM t_tipo_endereco WHERE cod_tipo_endereco=$id_KindOfAddress";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_tipo_endereco'];
      }

      function getState(){
                $id_state = $this->id_state;

                $get = "SELECT desc_estado FROM t_estado WHERE cod_estado = $id_state";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record["desc_estado"];
      }

      function getCountry(){
                $id_country = $this->id_country;

                $get = "SELECT desc_pais FROM t_pais WHERE cod_pais = $id_country";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record["desc_pais"];
      }
}

?>