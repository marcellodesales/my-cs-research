<?
class Partner {
        var $database  = "classA";
        var $connection;
        var $result;
        var $id          = "";//to get these information we use a global get method
        var $name        = "";
        var $text        = "";
        var $address     = "";
        var $phones      = "";
        var $email       = "";
        var $webpage     = "";
	var $extPhoto	 = "";
	var $extBanner   = "";

       function Partner($id_partner){
          $this->connection = new dbsql($this->database);
          $secure = new Security();
          if ($id_partner != '0'){
                if (!$secure->partnerExist($id_partner)){
                        echo "<BR><BR>Partner Error: Sócio não encontrado com ID = $id_partner<BR><BR>";
                } else {
                        $this->id        = $id_partner;
                        $this->result    = $this->getPartner();

                        $this->name        = $this->result["nome_associado"];
                        $this->text        = $this->result["texto_associado"];
                        $this->address     = $this->result["endereco_associado"];
                        $this->phones      = $this->result["fones_associado"];
                        $this->email       = $this->result["email_associado"];
                        $this->webpage     = $this->result["url_associado"];
                        $this->extPhoto    = $this->result["imagem_associado"];
                        $this->extBanner   = $this->result["banner_associado"];
                }
          } else {
                 $util = new Utility();
                 $pw   = $util->getNewId();
                 $this->id = "AS$pw";
                 $this->insertNewPartner($this->id);
	  }
       }

       function insertNewPartner($id_partner){
                $ins = "INSERT INTO t_associado (cod_associado) VALUES ('$id_partner')";
                $this->connection->query($ins);
       }

       function get($field){
                $id  = $this->id;
                $get = "SELECT ".$field." FROM t_associado WHERE cod_associado = '$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
       }

       function getPartner(){
                $id  = $this->id;
                $get = "SELECT * FROM t_associado WHERE cod_associado = '$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
       }

       function setInt($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_associado set $field=$value WHERE cod_associado = '$id'";
                $this->connection->query($set);
       }

       function setPartner($name, $email, $url, $phones, $address, $description){
                $id  = $this->id;
                $set = "UPDATE t_associado set nome_associado='$name', texto_associado='$description', endereco_associado='$address', fones_associado='$phones', email_associado='$email', url_associado='$url' WHERE cod_associado = '$id'";
                $this->connection->query($set);
       }

       function setStr($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_associado set $field='$value' WHERE cod_associado = '$id'";
                $this->connection->query($set);
       }

       function getID(){
		return $this->id;
       }

       function delPartner(){
		$id  = $this->id; 
		$set = "DELETE FROM t_associado WHERE cod_associado = '$id'";
                $this->connection->query($set);
       }
}
?>
