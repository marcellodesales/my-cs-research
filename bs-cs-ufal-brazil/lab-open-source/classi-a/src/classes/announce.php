<?
  require("/classia/classes/conf.php");
  require("/classia/classes/user.php");
  require("/classia/classes/especification.php");
class Announce{

      var $database          = "classA";
      var $connection;
      var $result;
      var $id                = "";
      var $id_announcer      = "";
      var $id_kindOfAnnounce = "";
      var $id_category       = "";
      var $id_subcategory    = "";

      var $kindOfAnnounce = "";
      var $category       = "";
      var $subcategory    = "";

      var $period      = "";
      var $price       = "";
      var $startDate   = "";
      var $endDate     = "";
      var $description = "";
      var $image       = "";
      var $title       = "";
      var $ipAnnouncer = "";
      var $whenAnnounced = "";

      var $especification;
      var $user;

      function Announce($id_announce,$id_announcer){
              $this->connection = new dbsql($this->database);
              $secure = new Security();
              if (!$secure->personExist($id_announcer)){
                     echo "Announce Error: Erro ao criar usuário codigo: $id_announcer";
              } else {
                      if ($id_announce != '0'){
                            if (!$secure->announceExist($id_announce)){
                                    echo "Annouce Error: Erro ao criar anuncio codigo $id_announce";
                            } else {  //Create annouce with the user $id_user
                                 $this->id           = $id_announce;
                                 $this->id_announcer = $id_announcer;

                                 $this->result            = $this->getAnnounce();
                                 $this->id_kindOfAnnounce = $this->result["cod_tipo_anuncio"];
                                 $this->id_category       = $this->result["cod_categoria_anuncio"];
                                 $this->id_subcategory    = $this->result["cod_subcategoria_anuncio"];
                                 $this->period            = $this->result["periodo_anuncio"];
                                 $this->price             = ereg_replace('\.',",",$this->result["preco_anuncio"]);
                                 $this->description       = $this->result["desc_anuncio"];
                                 $this->image             = $this->result["imagem_anuncio"];
                                 $this->title             = $this->result["titulo_anuncio"];
                                 $this->ipAnnouncer       = $this->result["ip_anunciante_anuncio"];
                                 $this->kindOfAnnounce    = $this->result["desc_tipo_anuncio"];
                                 $this->category          = $this->result["desc_categoria_anuncio"];
                                 $this->subcategory       = $this->result["desc_subcategoria_anuncio"];

                                 $util = new Utility();
                                 $this->whenAnnounced = $util->getPrintableTimeStamp($this->result["quando_anuncio"]);
                                 $this->endDate       = $util->getPrintableDate($this->result["data_end_anuncio"]);
                                 $this->startDate     = $util->getPrintableDate($this->result["data_ini_anuncio"]);

                                 if (($this->id_category == 1) || ($this->id_category == 2)) {
                                       $this->especification = new EspecificationOfAnnounce($this->id,$this->id_category);
                                 }
                                 $this->user = new User($this->id_announcer);
                            }
                      } else { //Create a new announce
                              $util = new Utility();
                              $pw   = $util->getNewId();
                              $this->id = "AO$pw";
                              $when = time();
                              $this->insertNewAnnounce($this->id,$when);
                              $this->setStr("cod_anunciante",$id_announcer);

                              $util = new Utility();
                              $dateIni = $util->getDbDate();
                              $this->setStr("data_ini_anuncio",$dateIni);
                      }
              }
      }

      function insertNewAnnounce($id_announce,$when){
                $ins = "INSERT INTO t_anuncio (cod_anuncio,quando_anuncio) VALUES ('$id_announce',$when)";
                $this->connection->query($ins);
      }

      function setInt($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_anuncio set $field=$value WHERE cod_anuncio = '$id'";
                $this->connection->query($set);
      }

      function setStr($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_anuncio set $field='$value' WHERE cod_anuncio = '$id'";
                $this->connection->query($set);
      }

      function get($field){
                $id  = $this->id;
                $get = "SELECT ".$field." FROM t_anuncio WHERE cod_anuncio = '$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
      }

// If this method returns true(1), the announce has a subcategory, otherwise the announce doesn't has a subcategory and the method returns false(0)
      function subCategoryExist(){
		$id  = $this->id;
		$get = "SELECT count(*) quant FROM t_anuncio WHERE t_anuncio.cod_anuncio='$id' AND t_anuncio.cod_subcategoria_anuncio <> 0";
		$this->connection->query($get);
		$this->connection->next_record();
		$exist = ($this->connection->Record["quant"] == 1) ? true : false;
		return $exist;
      }

      function getAnnounce(){
                $id  = $this->id;
		if ($this->subCategoryExist()) {
                	$get = "SELECT t_anuncio.*,desc_tipo_anuncio,desc_categoria_anuncio,desc_subcategoria_anuncio FROM t_anuncio,t_tipo_anuncio,t_categoria_anuncio,t_subcategoria_anuncio WHERE t_anuncio.cod_tipo_anuncio=t_tipo_anuncio.cod_tipo_anuncio AND  t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio AND t_anuncio.cod_subcategoria_anuncio=t_subcategoria_anuncio.cod_subcategoria_anuncio AND cod_anuncio='$id'";
		} else {
			$get = "SELECT t_anuncio.*,desc_tipo_anuncio,desc_categoria_anuncio FROM t_anuncio,t_tipo_anuncio,t_categoria_anuncio WHERE t_anuncio.cod_anuncio='$id' AND t_anuncio.cod_subcategoria_anuncio=0 AND t_anuncio.cod_tipo_anuncio=t_tipo_anuncio.cod_tipo_anuncio AND t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio";
		}
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
      }

      function getKindOfAnnounce(){
                $id_kindOfAnnounce = $this->id_kindOfAnnounce;

                $get = "SELECT desc_tipo_anuncio FROM t_tipo_anuncio WHERE cod_tipo_anuncio = $id_kindOfAnnounce";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_tipo_anuncio'];
      }

      function getCategoryOfAnnounce(){
                $id_CategoryOfAnnounce = $this->id_category;

                $get = "SELECT desc_categoria_anuncio FROM t_categoria_anuncio WHERE cod_categoria_anuncio = $id_CategoryOfAnnounce";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_categoria_anuncio'];
      }

      function getSubcategoryOfAnnounce(){
                $id_SubcategoryOfAnnounce = $this->id_subcategory;

                $get = "SELECT desc_subcategoria_anuncio FROM t_subcategoria_anuncio WHERE cod_subcategoria_anuncio = $id_SubcategoryOfAnnounce";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_subcategoria_anuncio'];
      }

}

?>
