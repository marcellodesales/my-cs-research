<?
class SiteDescription{

      var $database = "classA";
      var $connection;

      function SiteDescription(){
                $this->connection = new dbsql($this->database);
      }

      function getCategoryDescription($categoryID){
                $get = "select desc_categoria_anuncio from t_categoria_anuncio where cod_categoria_anuncio='$categoryID'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record["desc_categoria_anuncio"];
      }

      function getSubcategoryDescription($subcategoryID){
                $get = "select desc_subcategoria_anuncio from t_subcategoria_anuncio where cod_subcategoria_anuncio='$subcategoryID'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record["desc_subcategoria_anuncio"];
      }

      function getEspecificationDescription($categoryID,$especificationID){
           if ($categoryID == "1"){
                $field = "desc_marca_veiculo";
                $table = "t_marca_veiculo";
                $condition = "cod_marca_veiculo='$especificationID'";
           } else
           if($categoryID == "2"){
                $field = "desc_bairro";
                $table = "t_bairro";
                $condition = "cod_bairro='$especificationID'";
           }
           $get = "select $field from $table where $condition";
           $this->connection->query($get);
           $this->connection->next_record();
           return $this->connection->Record[$field];
      }
}
?>