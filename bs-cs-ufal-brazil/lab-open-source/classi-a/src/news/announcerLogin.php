<? if (($username == null) || ($password == null)){
    header("Location: /");
   } else {
        require("announcer.php");
        $secure = new Security();
        $util   = new Utility();
        if ($secure->userExist($username,$password)){
            $announcerID = $secure->getAnnouncerID($username);
            $announcer   = new Announcer($announcerID);
            $lastAccess  = $announcer->lastAccess;
            $lastIP      = $util->getRemoteDNS($announcer->lastIp);
            $secure->updateUserIP($announcerID,getenv("REMOTE_ADDR"));
            $secure->updateUserAccess($announcerID,time());

            $announcerIP =  getenv('REMOTE_HOST');
            $add = $util->getRemoteDNS($ip);
            $announcerDNS = $add . " ($REMOTE_ADDR)";

            $hasLoggedIn = true;
	    session_register("announcerID");
            session_register("lastAccess");
            session_register("lastIP");
            session_register("hasLoggedIn");
            session_register("announcerIP");
	    session_register("announcerDNS");
            header("Location: /announcer/?".session_name()."=".session_id());
        } else {
?>
        	<form name="wrong" method="POST" action="/index.php" target="_top">
        	   <input type="hidden" name="username" value="<? echo $username ?>">
        	</form>
        	<script>document.wrong.submit()</script>
<?
        }
   }
?>
