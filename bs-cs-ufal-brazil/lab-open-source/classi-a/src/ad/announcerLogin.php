<? if (($username == null) || ($password == null)){
    header("Location: /");
   } else {
        require("/classia/classes/announcer.php");
        $secure = new Security();
        $util   = new Utility();
        if ($secure->userExist($username,$password)){
            $announcerID = $secure->getAnnouncerID($username);
            $announcer   = new Announcer($announcerID);
            $lastAccess  = $announcer->lastAccess;
            $userNameLogged = $announcer->name;
            $lastIP      = $util->getRemoteDNS($announcer->lastIp);
            $secure->updateUserIP($announcerID,getenv("REMOTE_ADDR"));
            $secure->updateUserAccess($announcerID,time());

            $linkSession = session_name()."=".session_id();
            session_register("linkSession");
	    session_register("announcerID");
            session_register("userNameLogged");
            session_register("lastAccess");
            session_register("lastIP");
            header("Location: /announcer/?".session_name()."=".session_id());
        } else {
                header("Location: /?wrongUsername=$username");
        }
   }
?>
