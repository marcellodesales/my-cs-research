<?
        $con = mysql_connect("localhost", .php", "51527981");
        mysql_select_db("classANews");

	$members = mysql_query("select * from Newsletter_Members WHERE email='$userEmail'", $con);

        if (mysql_num_rows($members) == 0 ) {
        
		mysql_query("INSERT INTO Newsletter_Members(email) VALUES ('$userEmail')", $con);
                mysql_query("UPDATE Newsletter_Members SET receivedPublicity='Y' WHERE email='$userEmail'", $con);
        } 

        mysql_free_result($members);
        mysql_close($con);
?>
