<?
class dbsql {
        var $Host     = "";
        var $Database = "";
        var $User     = "";
        var $Password = "";

        var $Link_ID  = 0;
        var $Query_ID = 0;
        var $Record   = array();
        var $Row;

        var $Errno    = 0;
        var $Error    = "";

        function dbsql($db){
                $this->Host = "localhost";
                $this->User = "";
                $this->Password = "";
                $this->Database = $db;
                $this->connect();
        }

        function halt($msg) {
                printf("</td></tr></table><b>Erro de banco:</b> %s<br>\n", $msg);
                printf("<b>dbMySQL Error</b>: %s (%s)<br>\n",$this->Errno,$this->Error);
                die("Sessão Abortada!.");
        }

        function connect() {
                if ( 0 == $this->Link_ID ) {
                        $this->Link_ID = mysql_connect($this->Host, $this->User, $this->Password);

                        if (!$this->Link_ID) {
                                $this->halt("Link_ID == falso, conexão falhou!");
                        }

                        if (!mysql_query(sprintf("use %s",$this->Database),$this->Link_ID)) {
                                $this->halt("Não pôde usar banco de dados ".$this->Database);
                              }
                }
        }

        function query($Query_String) {

                $this->Query_ID = mysql_query($Query_String,$this->Link_ID);
                $this->Row   = 0;
                $this->Errno = mysql_errno();
                $this->Error = mysql_error();
                if (!$this->Query_ID) {
                        $this->halt("SQL Invalida: ".$Query_String);
                }

                return $this->Query_ID;
        }

        function next_record() {
                $this->Record = mysql_fetch_array($this->Query_ID);
                $this->Row   += 1;
                $this->Errno = mysql_errno();
                $this->Error = mysql_error();

                $stat = is_array($this->Record);
                if (!$stat) {
                        mysql_free_result($this->Query_ID);
                        $this->Query_ID = 0;
                }
                return $stat;
        }

        function seek($pos) {
                $status = mysql_data_seek($this->Query_ID, $pos);
                if ($status)
                        $this->Row = $pos;
                return;
        }

        function num_rows() {
                return mysql_num_rows($this->Query_ID);
        }

        function num_fields() {
                return mysql_num_fields($this->Query_ID);
        }

        function affected_rows() {
                return @mysql_affected_rows($this->Link_ID);
        }
}

 ?>
