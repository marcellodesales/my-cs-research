        <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td width="28%" align="center">
            	<img border="0" src="/common/images/logoClassiAAnnounces.gif" align="left"></td>
            <td width="72%" align="center">
<?
  switch($idCategory){
          case 1:
                $table = "t_marca_veiculo";
                $field = "desc_marca_veiculo";
                $hasEspecification = true;
          break;
          case 2:
                $table = "t_bairro";
                $field = "desc_bairro";
                $hasEspecification = true;
          break;
  }
if ($hasEspecification){
  $get = "SELECT * FROM $table order by $field";
  $secure->connection->query($get);
  $quant = $secure->connection->affected_rows()+1;

  if ($quant != 1){
	        $goSubEspc = true;
       for ($i = 1; $i < $quant; $i++){
             $secure->connection->next_record();
             if ($idCategory == 1){
                   $idEspecification   = $secure->connection->Record["cod_marca_veiculo"];
                   $descEspecification = $secure->connection->Record["desc_marca_veiculo"];
             } else { if ($idCategory == 2){
                         $idEspecification   = $secure->connection->Record["cod_bairro"];
                         $descEspecification = $secure->connection->Record["desc_bairro"];
                    }
             }
?>
             <b><font face="Verdana" color="#000066" size="1">[
<?         $link = (isset($linkSession)) ? $linkSession."&" : "";
           if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){ ?>
              <a href="<? echo "getEspecification.php?".$link."idCategory=$idCategory&idSubcategory=$idSubcategory&idEspecification=$idEspecification"; ?>" class="subcategory" <? echo "onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory $DIR_SEPARATOR $descEspecification')\" onMouseOut=\"clearStatus()\">" ?><? echo $descEspecification ?></a> ]
<?           } else {  ?>
              <a href="javascript:makeChoiceEspecification(<? echo "'$idCategory','$descCategory','$idSubcategory','$descSubcategory','$idEspecification','$descEspecification'"; ?>)" class="subcategory" <? echo "onMouseOver=\"return showStatus('Classi-A $DIR_SEPARATOR $descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory $DIR_SEPARATOR $descEspecification')\" onMouseOut=\"clearStatus()\">" ?><? echo $descEspecification ?></a> ]
<?           }
             if ($i % 3 == 0){
                   echo "<BR>";
             }
       }
  }
}
?>         </td>
          </tr>
        </table>
