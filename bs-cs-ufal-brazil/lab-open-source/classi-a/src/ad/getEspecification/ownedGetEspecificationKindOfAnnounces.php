      <TABLE bgColor=#FFCC00 border=0 cellPadding=0 cellSpacing=0 width=132>
        <TBODY>
              <TR>
                 <TD width="127"><BR>
                 </TD>
              </TR>
<?
  $util = new Utility();

  $kindOfAnnouncesForThisCategory = $util->getKindOfAnnouncePerCategory($idCategory);
  $goKindOA = false; // for verifying the kind of announce if it exists....
  if ($kindOfAnnouncesForThisCategory != 'N'){ //N - It doesn't have a kind of category to be written
          $goKindOA = true;
          $secure = new Security();
          $get = "SELECT * FROM t_tipo_anuncio order by desc_tipo_anuncio";
          $secure->connection->query($get);
?>
              <TR>
                   <td align="center">
                    <table width="96%" border="1" cellspacing="0" bordercolor="#000066">
                    <tr bgcolor="#000066">
                       <td align="center">
                         <b><u><font size="1" face="Verdana" color="#FFFFFF">
                         Tipos de Anuncio</font></u></b></td>
                    </tr>
                    <tr>
                      <TD width="100%" align="center">
                        <form name="kindOfAnnounce" method="post" action="getEspecification.php" onSubmit="return verifyChoice()">
                          <select name="idKindOfAnnounce">
                                  <option value='0'>Todos</option>
<?
          if (!is_array($kindOfAnnouncesForThisCategory)){
            for ($i = 0; $i < $secure->connection->affected_rows(); $i++){
                  $secure->connection->next_record();
                  $idKindOA  = $secure->connection->Record['cod_tipo_anuncio'];
                  $descrKOA  = $secure->connection->Record['desc_tipo_anuncio'];
                  if ($descrKOA != $descKindOfAnnounce){ // if goes from up
                            echo "<option value='$idKindOA'>$descrKOA</option>";
                  } else {
                            echo "<option value='$idKindOA' SELECTED>$descrKOA</option>";
                  }
            }
          } else {
                  for ($i = 0; $i < $secure->connection->affected_rows(); $i++){
                        $db->next_record();
                        $idKindOA = $secure->connection->Record['cod_tipo_anuncio'];
                        $descrKOA = $secure->connection->Record['desc_tipo_anuncio'];
                        if (in_array($idKindOA,$kindOfAnnouncesForThisCategory)){
                            if ($descrKOA != $descKindOfAnnounce){ // if goes from up
                                   echo "<option value='$idKindOA'>$descrKOA</option>";
                            } else {
                                   echo "<option value='$idKindOA' SELECTED>$descrKOA</option>";
                            }
                        }
                  }
          }
?>
                           </select>
                           <input type="hidden" name="idCategory" value="<? echo $idCategory; ?>">
                           <input type="hidden" name="descCategory" value="<? echo $descCategory;
                           //idkindofannounce is catched from the combo; //It has 'Todos' cause the combo
                           ?>">
                           <input type="hidden" name="idSubcategory" value="<? echo $idSubcategory; ?>">
                           <input type="hidden" name="descSubcategory" value="<? echo $descSubcategory; ?>">
                           <input type="hidden" name="idEspecification" value="<? echo $idEspecification; ?>">
                           <input type="hidden" name="descEspecification" value="<? echo $descEspecification; ?>">
                           <? $b = ($descKindOfAnnounce == null) ? "Todos" : $descKindOfAnnounce; ?>
                           <input type="hidden" name="descKindOfAnnounce" value="<? echo $b; ?>">
                     </TD>
                   </TR>
                   <TR>
                        <TD align="center">
                           <input type="image" border="0" src="/common/images/ok_login.gif" name="submit" width="31" height="18"></TD>
                   </TR>
                   </table>
                    </form>
                </td>
              </tr>
<?
  }
?>      <TR>
          <TD width="130" align="center">
            <font size="1" face="Verdana"><br>

            <?
            if ($idCategory == 1){
                 $fields = "desc_marca_veiculo,t_marca_veiculo.cod_marca_veiculo,";
                 $tables   = "t_anuncio,t_tipo_anuncio,t_categoria_anuncio,t_subcategoria_anuncio,t_marca_veiculo LEFT JOIN t_veiculo ON t_marca_veiculo.cod_marca_veiculo = t_veiculo.cod_marca_veiculo";
                 if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                     $booleans = "t_veiculo.cod_marca_veiculo=$idEspecification AND t_veiculo.cod_anuncio = t_anuncio.cod_anuncio AND t_anuncio.cod_tipo_anuncio = t_tipo_anuncio.cod_tipo_anuncio AND t_anuncio.cod_tipo_anuncio<>0 AND t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio AND t_anuncio.cod_categoria_anuncio=$idCategory AND t_anuncio.cod_subcategoria_anuncio=t_subcategoria_anuncio.cod_subcategoria_anuncio AND t_anuncio.cod_subcategoria_anuncio=$idSubcategory";
                 } else {
                        $booleans = "t_veiculo.cod_marca_veiculo=$idEspecification AND t_veiculo.cod_anuncio = t_anuncio.cod_anuncio AND t_anuncio.cod_tipo_anuncio = t_tipo_anuncio.cod_tipo_anuncio AND t_anuncio.cod_tipo_anuncio=$idKindOfAnnounce AND t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio AND t_anuncio.cod_categoria_anuncio=$idCategory AND t_anuncio.cod_subcategoria_anuncio=t_subcategoria_anuncio.cod_subcategoria_anuncio AND t_anuncio.cod_subcategoria_anuncio=$idSubcategory";
                 }
            } else if ($idCategory == 2){
                      $fields   = "desc_bairro,t_bairro.cod_bairro,";
                      $tables   = "t_anuncio,t_tipo_anuncio,t_categoria_anuncio,t_subcategoria_anuncio,t_bairro LEFT JOIN t_imovel ON t_bairro.cod_bairro = t_imovel.cod_bairro";
                      if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                          $booleans = "t_imovel.cod_bairro=$idEspecification AND t_imovel.cod_anuncio = t_anuncio.cod_anuncio AND t_anuncio.cod_tipo_anuncio = t_tipo_anuncio.cod_tipo_anuncio AND t_anuncio.cod_tipo_anuncio<>0 AND t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio AND t_anuncio.cod_categoria_anuncio=$idCategory AND t_anuncio.cod_subcategoria_anuncio=t_subcategoria_anuncio.cod_subcategoria_anuncio AND t_anuncio.cod_subcategoria_anuncio=$idSubcategory";
                      } else {
                            $booleans = "t_imovel.cod_bairro=$idEspecification AND t_imovel.cod_anuncio = t_anuncio.cod_anuncio AND t_anuncio.cod_tipo_anuncio = t_tipo_anuncio.cod_tipo_anuncio AND t_anuncio.cod_tipo_anuncio=$idKindOfAnnounce AND t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio AND t_anuncio.cod_categoria_anuncio=$idCategory AND t_anuncio.cod_subcategoria_anuncio=t_subcategoria_anuncio.cod_subcategoria_anuncio AND t_anuncio.cod_subcategoria_anuncio=$idSubcategory";
                      }
            }
            $fields .= "titulo_anuncio,preco_anuncio,t_anuncio.cod_anuncio,t_anuncio.cod_tipo_anuncio,imagem_anuncio";

            $query = "SELECT $fields FROM $tables WHERE $booleans";

            if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                  $stage = "idCategory=$idCategory&descCategory=$descCategory&idSubcategory=$idSubcategory&descSubcategory=$descSubcategory&idEspecification=$idEspecification&descEspecification=$descEspecification"; //it's for the indexes

            } else {
                  $stage = "idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce&idCategory=$idCategory&descCategory=$descCategory&idSubcategory=$idSubcategory&descSubcategory=$descSubcategory&idEspecification=$idEspecification&descEspecification=$descEspecification"; //it's for the indexes
            }

	    $query .= " ORDER BY quando_anuncio DESC";
	
            require("/classia/common/commonMoreHeader.php");

?>
