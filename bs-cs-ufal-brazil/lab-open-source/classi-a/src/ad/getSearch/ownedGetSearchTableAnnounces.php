   <table width="95%" cellspacing="0" cellpadding="1" border="0" align="center" bgcolor="white">
      <tr>
        <td width="50%" valign="top">
          <font face="Verdana" size="2" color="#000066">
          <b>&nbsp;&nbsp;Anuncio</b></font></td>

        <td width="25%" valign="top" align="center">
          <font face="Verdana" size="2" color="#000066">
          <b>&nbsp;Tipo</font></b></td>
<?
     if (($idCategory != 6) && ($idCategory != 12)){   ?>
        <td width="25%" valign="top" align="center">
          <font face="Verdana" size="2" color="#000066">
          <b>&nbsp;Preço R$</font></b></td>
      </tr>
 <?  } ?>
   </table>

   <table width="95%" border="1" cellspacing="0" cellpadding="0" bordercolor="#EAEAEA" align="center">
   <tr>
   	<td>
<?
       for ($i=0; $i<$lines; $i++){

             $color = ($i % 2 == 0) ? "#EAEAEA" :  "white";
             $check = mysql_data_seek($result, $i);
             if ($check){
                  $adID         = mysql_result($result,$i,"cod_anuncio");
                  $adPrice      = $util->getBRPrice(mysql_result($result,$i,"preco_anuncio"));
                  $adTitle      = mysql_result($result,$i,"titulo_anuncio");
                  $adImage      = mysql_result($result,$i,"imagem_anuncio");
                  $adIdKindOfAd = mysql_result($result,$i,"cod_tipo_anuncio");

                  $get = "SELECT desc_tipo_anuncio from t_tipo_anuncio WHERE cod_tipo_anuncio=$adIdKindOfAd";
                  $secure->connection->query($get);
                  $secure->connection->next_record();
                  $descKindOA = $secure->connection->Record["desc_tipo_anuncio"];

	  	  include("/classia/ad/getSearch/ownedGetSearchAnnounces.php");
             }
       }
?>
       </td>
   </tr>
   <tr>
<?     $color = ($color == "#EAEAEA") ? "white" : "#EAEAEA";
       echo "<td bgcolor='$color' align='center'>";
       echo "<B><font size=2 face='Verdana' color=black>";
       require("/classia/common/commonMoreFoot.php");
?></font></b></td>
  </tr>
  </table>
