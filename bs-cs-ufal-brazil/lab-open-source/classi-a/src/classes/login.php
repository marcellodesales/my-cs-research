<?
require("announcer.php");

class Login {

        var $ID          = ""; //The session ID
        var $announcerID = ""; //The announcer that has logged in

        function Login($username, $password){

                $hasLogin = false;
                $secure = new Security();

                if ($secure->userExist($username, $password)){
                        $annID     = $secure->getAnnouncerID($username);
                        $announcer = new Announcer($annID);
                        $this->announcerID = $announcer->id;
                        $hasLogin = true;
                 }
                 return $hasLogin;
        }

        function setID($sessionID){
                $this->ID = $sessionID;
        }
}