<?
include("login.php");

$login = new Login("marcello","51527981");
$loginA = $login;
session_register("loginA");
$login->setID($LXID);

echo "ID = ".$login->ID."<BR><BR>";
echo "AnnouncerID = ".$login->announcerID."<BR><BR>";

?>
<a href="login2.php?<? echo session_name() ."=".session_id(); ?>">destroy</a>
