<?

class Category{

      var $database = "classA";
      var $connection;
      var $connection2;
      var $category; //array of ["id"]["desc"] / ["id"]["quant"] / ["id"]["id"];

      function Category(){
                $this->connection = new dbsql($this->database);
                $this->connection2 = new dbsql($this->database);
		$this->getCategories();
      }

      function getQuantAnnounces($idCategory){
                $get = "select count(*) from t_anuncio where cod_categoria_anuncio='$idCategory'";
                $this->connection2->query($get);
                $this->connection2->next_record();
                return $this->connection2->Record["count(*)"];
      }

      function getCategories(){
                $get = "select * from t_categoria_anuncio order by desc_categoria_anuncio";
                $this->connection->query($get);
                while($this->connection->next_record()){

                        $cod  = $this->connection->Record["cod_categoria_anuncio"];
                        $desc = $this->connection->Record["desc_categoria_anuncio"];

                        $this->category[$cod]["id"]    = $cod;
                        $this->category[$cod]["desc"]  = $desc;
                        $this->category[$cod]["quant"] = $this->getQuantAnnounces($cod);
                }
      }
}

?>