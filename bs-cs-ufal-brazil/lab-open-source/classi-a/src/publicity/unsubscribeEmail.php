<HTML>
<TITLE>Remoção de email do Classi-A</TITLE>
<BODY bgColor=WHITE topmargin=0 leftmargin=0>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td bgColor=LightGrey><font size="3" face="Arial, Helvetica, sans-serif"><b>Seu email
      foi removido da lista de emails do Classi-A!</b></font></td>
  </tr>
</table>
<BR>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<?
        $con = mysql_connect("localhost", "root", "51527981");
        mysql_select_db("classANews");
         
        // Check if already a member of this newsletter
        $members = mysql_query("select * from Newsletter_Members WHERE email='$email'", $con);

        if (mysql_num_rows($members) == 0 ) {

            echo "<tr><td><FONT FACE=verdana SIZE=2>O email <? echo $email ?> não está presente em nenhuma lista do Classi-A.</FONT></td></tr>";

        } else {

            mysql_query("DELETE FROM Newsletter_Members WHERE email='$email'", $con);
            echo "<td><FONT FACE=verdana SIZE=2>O email <? echo $email; ?> foi removido da lista do Classi-A com sucesso!</FONT></td></tr>";
        }
        mysql_free_result($members);
        mysql_close($con);
?>
<tr><td align=center><BR><BR><font face="Verdana"><b>Obrigado pela atenção</td></tr></table>
<br>
</b><font size="2"><a href="http://www.classi-a.com.br/">http://www.classi-a.com.br</a></font></font></p>

</BODY>
</HTML>

