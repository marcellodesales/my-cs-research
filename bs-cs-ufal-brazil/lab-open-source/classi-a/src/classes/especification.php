<?
class EspecificationOfAnnounce{
      var $database       = "classA";
      var $connection;
      var $especification = "";

      var $id_announce       = "";
      var $id_category       = "";
      var $id_especification = "";

      function EspecificationOfAnnounce($id_announce,$id_category){
              $this->connection = new dbsql($this->database);
              $secure = new Security();
              if (!$secure->announceExist($id_announce)){
                     echo "Erro de especicaзгo do anuncio: Anuncio cуdigo $id_announce nгo existente";
              } else { // VERIFICAR ESTE PONTO, COMO PEGAMOS A CATEGORIA DO ANUNCIO....
                      if ($secure->especificationOfAnnounceExist($id_announce,$id_category)){//if cod anuncio is in especification kind....
                           $this->id_announce = $id_announce;
                           $this->id_category = $id_category;
                           if ($id_category == 1){
                                //search for a brand of vehicle....
                                $this->id_especification = $this->get();
                                $this->especification = $this->getEspecification();
                           } else {
                                   if ($id_category == 2){
                                        //search for the neighborhood of imovel...
                                        $this->id_especification = $this->get();
                                        $this->especification = $this->getEspecification();

                                   } else echo "Erro ao criar especificaзгo de anuncio cуdigo $id_announce";
                           }
                      } else {
                             $this->id_announce       = $id_announce;
                             $this->id_category       = $id_category;
                             $this->insertNewEspecification();
                      }
              }
      }

      function insertNewEspecification(){
               $id_announce = $this->id_announce;
               if ($this->id_category == 1){
                     $set = "INSERT INTO t_veiculo (cod_anuncio) VALUES ('$id_announce')";
                     $this->connection->query($set);
               } else if ($this->id_category == 2){
                           $set = "INSERT INTO t_imovel (cod_anuncio) VALUES ('$id_announce')";
                           $this->connection->query($set);
                       } else echo "Especification Error: Erro ao tentar criar novo codigo de especificaзгo para o anuncio '$id_announce' onde a categoria foi setada como ";

      }

      function set($value){
               $id_announce = $this->id_announce;
               $id_cate = $this->id_category;
               if ($this->id_category == 1){
                     $set = "UPDATE t_veiculo SET cod_marca_veiculo=$value WHERE cod_anuncio='$id_announce'";
                     $this->connection->query($set);
                     $this->id_especification = $value;
                     $this->especification    = $this->getEspecification();
               } else if ($this->id_category == 2){
                           $set = "UPDATE t_imovel SET cod_bairro=$value WHERE cod_anuncio='$id_announce'";
                           $this->connection->query($set);
                      } else echo "Erro ao tentar setar codigo de especificaзгo para o anuncio '$id_announce'";
      }

      function get(){
               $id_announce = $this->id_announce;
               if ($this->id_category == 1){
                     $get = "SELECT cod_marca_veiculo FROM t_veiculo WHERE cod_anuncio = '$id_announce'";
                     $this->connection->query($get);
                     $this->connection->next_record();
                     return $this->connection->Record['cod_marca_veiculo'];
               } else if ($this->id_category == 2){
                           $get = "SELECT cod_bairro FROM t_imovel WHERE cod_anuncio = '$id_announce'";
                           $this->connection->query($get);
                           $this->connection->next_record();
                           return $this->connection->Record['cod_bairro'];
                      } else echo "Erro ao tentar pegar codigo de especificaзгo pertencente ao anuncio '$id_announce'";
      }

      function getEspecification(){
               $id_especification = $this->get();
               if ($this->id_category == 1){
                    $get = "SELECT desc_marca_veiculo FROM t_marca_veiculo WHERE cod_marca_veiculo = $id_especification";
                    $this->connection->query($get);
                    $this->connection->next_record();
                    return $this->connection->Record['desc_marca_veiculo'];
               } else  if ($this->id_category == 2){
                            $get = "SELECT desc_bairro FROM t_bairro WHERE cod_bairro = $id_especification";
                            $this->connection->query($get);
                            $this->connection->next_record();
                            return $this->connection->Record['desc_bairro'];
               } else {
                       $a = $this->id_category;
                       echo "Erro ao tentar pegar especificaзгo cуdigo $a";
               }
      }
}


?>