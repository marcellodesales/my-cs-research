<?
  require("/classia/classes/person.php");

class User extends Person {
      var $database   = "classA";
      var $connection;
      var $result;
      //to get these information we use a global get method
      var $username   = "";
      var $password   = "";
      var $whenArrived= "";
      var $lastAccess = "";
      var $lastIp     = "";
      //to get these information we use a particular get method
      var $kindOfUser = "";
      //get id's of int values
      var $id_howArrived = "";

     function User($id_user){
          //whatever the id_user is, a person is created...
          $this->connection = new dbsql($this->database);
          $this->Person($id_user);
          if ($id_user != '0'){
                  $secure = new Security();
                  if ($secure->personExist($id_user)){
                       $this->result        = $this->getUserInfo();
                       $this->username      = $this->result["login"];
                       $this->password      = $this->result["password"];
                       $this->kindOfUser    = $this->result["cod_tipo_usuario"];
                       $this->lastIp        = $this->result["ultimo_ip"];
                       $this->id_howArrived = $this->result["cod_chegada"];
                       $this->howArrived    = $this->result["desc_chegada"];

                       $util = new Utility();
                       $this->whenArrived   = $util->getPrintableTimeStamp($this->result["quando_chegou"]);
                       $this->lastAccess    = $util->getPrintableTimeStamp($this->result["ultimo_acesso"]);

                  } // the else statement isn't necessary cause person will treat this
          } else {
                  $when = time();
                  $this->insertNewUser($this->id,$when);
          }
     }

     function insertNewUser($id_user,$when){
                $get = "INSERT INTO t_usuario (cod_anunciante,quando_chegou) VALUES ('$id_user',$when)";
                $this->connection->query($get);
     }

     function setIntUser($field,$value){
              $id  = $this->id;
              $set = "UPDATE t_usuario set $field=$value WHERE cod_anunciante = '$id'";
              $this->connection->query($set);
     }

     function setStrUser($field,$value){
              $id  = $this->id;
              $set = "UPDATE t_usuario set $field='$value' WHERE cod_anunciante = '$id'";
              $this->connection->query($set);
     }

     function getUser($field){
              $id  = $this->id;
              $get = "SELECT ".$field." FROM t_usuario WHERE cod_anunciante = '$id'";
              $this->connection->query($get);
              $this->connection->next_record();
              return $this->connection->Record;
     }

     function getUserInfo(){
              $id  = $this->id;
              $get = "SELECT t_usuario.*,desc_chegada FROM t_usuario,t_chegada_usuario WHERE t_usuario.cod_chegada=t_chegada_usuario.cod_chegada AND cod_anunciante='$id'";
              $this->connection->query($get);
              $this->connection->next_record();
              return $this->connection->Record;
     }

     function getHowArrived(){
              $id_arrival = $this->id_howArrived;

              $get = "SELECT desc_chegada FROM t_chegada_usuario WHERE cod_chegada = $id_arrival";
              $this->connection->query($get);
              $this->connection->next_record();
              return $this->connection->Record['desc_chegada'];
    }
}
?>