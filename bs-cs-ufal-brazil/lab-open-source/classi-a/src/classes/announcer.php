<?
  require("/classia/classes/announce.php");
class Announcer extends User{

      var $database  = "classA";
      var $connection;
      var $announces = array();
      var $announce;

     function Announcer($id_announcer){
            $this->connection = new dbsql($this->database);
            if (!$id_announcer == '0'){
                  $secure = new Security();
                  if (!$secure->personExist($id_announcer))
                        echo "Announcer Error: Erro ao criar usuário codigo: $id_announcer";
                  else { //create a user with id_announcer
                          $this->User($id_announcer);
                          $this->getAnnounces();
                  }

            } else $this->User($id_announcer); //create a new announcer with id = 0
      }

      function getAnnounces(){
                $id  = $this->id;
                $get = "SELECT cod_anuncio FROM t_anuncio WHERE cod_anunciante = '$id'";
                $this->connection->query($get);
                $this->announces = $this->connection->Record;
      }

      function openAnnounce($id_announce){
               $id = $this->id;
               if ($this->announcerAnnounceExist($id_announce))
                      $this->announce = new Announce($id_announce,$this->id);
               else echo "<BR>Announcer Error: Anuncio Código $id_announce não pertence a este usuário código $id<BR>";
      }

      function getAnnounce($id_announce){
               return $this->announces[$id_announce];
      }

      function getQuantAnnounces(){
               return sizeof($this->announces);
      }

      function announcerAnnounceExist($id_announce){
               return (in_array($id_announce));
      }

      function deleteAnnounce($id_announce){
               $id = $this->id;
               if ($this->announcerAnnounceExist($id_announce)){
                    $get = "DELETE FROM t_anuncio WHERE cod_anuncio = '$id_announce'";
                    $this->connection->query($get);
               }
               else echo "<BR>Announcer Error: Anuncio Código $id_announce não pertence a este usuário código $id<BR>";
      }
}
?>