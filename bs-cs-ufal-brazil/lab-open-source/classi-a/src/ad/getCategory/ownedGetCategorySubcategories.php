        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td width="28%" align="center">
            	<img border="0" src="/common/images/logoClassiAAnnounces.gif" align="left"></td>

            <td width="72%" align="center">
<?
if ($idCategory != 7){ //outros...
  $get = "SELECT * FROM t_subcategoria_anuncio WHERE cod_categoria_anuncio=$idCategory ORDER BY desc_subcategoria_anuncio";
  $secure->connection->query($get);
  $quant = $secure->connection->affected_rows()+1;

  if ($quant != 1){

	$goSubEspc = true;  //to give the information result
       for ($i = 1; $i < $quant; $i++){
             $secure->connection->next_record();
             $idSubcategory  = $secure->connection->Record["cod_subcategoria_anuncio"];
             if ($idSubcategory != 0){
	         $descSubcategory = $secure->connection->Record["desc_subcategoria_anuncio"];
	     }
?>
              <b><font face="Verdana" color="#000066" size="1">[
<?           if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                $link = (isset($linkSession)) ? $linkSession."&" : "";
?>
              <a href="<? echo "getSubcategory.php?".$link."idCategory=$idCategory&idSubcategory=$idSubcategory"; ?>" class="subcategory" <? echo "onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\">" ?><? echo $descSubcategory ?></a> ]
<?           } else {  ?>
              <a href="javascript:makeChoiceSubCategory(<? echo "'$idCategory','$descCategory','$idSubcategory','$descSubcategory'"; ?>)" class="subcategory" <? echo "onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory')\" onMouseOut=\"clearStatus()\">" ?><? echo $descSubcategory ?></a> ]
<?           }
             if ($i % 3 == 0){
                   echo "<BR>";
             }
       }
  }
  if (($idCategory == 1) or ($idCategory == 4) or ($idCategory == 21))
        echo "";
  echo "<BR>";

} // if Id != 7
?>         
          </tr>
        </table>

 
