<?php
  require("/classia/classes/utility.php");
  require("/classia/classes/address.php");

class Person {
        var $database  = "classA";
        var $connection;
        var $result;
        var $id        = "";//to get these information we use a global get method
        var $name      = "";
        var $subName   = "";
        var $sex       = "";
        var $phone     = "";
        var $cellPhone = "";
        var $email     = "";
        var $icq       = "";
        var $work      = "";
        var $birthRange= "";
        var $address; //address is an object...

        var $id_work             = "";//to get id's of int values...
        var $id_birthRange       = "";

        function Person($id_person){
          $this->connection = new dbsql($this->database);
          $secure = new Security();
          if ($id_person != '0'){
                if (!$secure->personExist($id_person)){
                        echo "<BR><BR>Person Error: Pessoa não encontrada com ID = $id_person<BR><BR>";
                } else {
                        $this->id        = $id_person;
                        $this->result    = $this->getPerson();

                        $this->name      = $this->result["nome_anunciante"];
                        $this->subName   = $this->result["sobrenome_anunciante"];
                        $this->sex       = $this->result["sexo_anunciante"];
                        $this->phone     = $this->result["fone_anunciante"];
                        $this->cellPhone = $this->result["celular_anunciante"];
                        $this->email     = $this->result["email_anunciante"];
                        $this->icq       = $this->result["icq_anunciante"];
                        $this->id_work   = $this->result["cod_profissao"];
                        $this->id_birthRange = $this->result["cod_faixa_etaria"];

                        $this->work      = $this->result["desc_profissao"];
                        $this->birthRange= $this->result["desc_faixa_etaria"];
                        $this->address   = new Address($this->id);
                }
          } else {
                $util = new Utility();
                $pw   = $util->getNewId();
                $this->id = "AE$pw";
                $this->insertNewPerson($this->id);
                $this->address = new Address($this->id);
          }
        }

        function insertNewPerson($id_person){
                $ins = "INSERT INTO t_anunciante (cod_anunciante) VALUES ('$id_person')";
                $this->connection->query($ins);
        }

       function get($field){
                $id  = $this->id;
                $get = "SELECT ".$field." FROM t_anunciante WHERE cod_anunciante = '$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
        }

       function getPerson(){
                $id  = $this->id;
                $get = "SELECT t_anunciante.*,desc_profissao,desc_faixa_etaria from t_anunciante,t_profissao,t_faixa_etaria WHERE t_anunciante.cod_faixa_etaria=t_faixa_etaria.cod_faixa_etaria AND t_anunciante.cod_profissao=t_profissao.cod_profissao AND cod_anunciante='$id'";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record;
        }

       function getPersonBirthRange(){
                $idBR = $this->id_birthRange;

                $get  = "SELECT desc_faixa_etaria FROM t_faixa_etaria WHERE cod_faixa_etaria = $idBR";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_faixa_etaria'];
        }

        function getPersonWork(){
                $idP = $this->id_work;

                $get = "SELECT desc_profissao FROM t_profissao WHERE cod_profissao = $idP";
                $this->connection->query($get);
                $this->connection->next_record();
                return $this->connection->Record['desc_profissao'];
        }

        function setInt($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_anunciante set $field=$value WHERE cod_anunciante = '$id'";
                $this->connection->query($set);
        }

        function setStr($field,$value){
                $id  = $this->id;
                $set = "UPDATE t_anunciante set $field='$value' WHERE cod_anunciante = '$id'";
                $this->connection->query($set);
        }
}
?>