      <TABLE bgColor=#FFCC00 border=0 cellPadding=0 cellSpacing=0 width=132>
        <TBODY>
              <TR>
                 <TD width="127"><BR>
                 </TD>
              </TR>
<?
  $secure = new Security();
  $util = new Utility();

  $kindOfAnnouncesForThisCategory = $util->getKindOfAnnouncePerCategory($idCategory);
  $goKindOA = false; // for verifying the kind of announce if it exists....
  if ($kindOfAnnouncesForThisCategory != 'N'){ //N - It doesn't have a kind of category to be written
          $goKindOA = true;
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
                         <form name="kindOfAnnounce" method="post" action="getCategory.php" onSubmit="return verifyChoice()">
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
                        $secure->connection->next_record();
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
            <font size="1" face="Verdana">

            <?
            $fields = "titulo_anuncio,preco_anuncio,cod_anuncio,imagem_anuncio";
            if ($goKindOA){
                $fields = "titulo_anuncio,preco_anuncio,cod_anuncio,imagem_anuncio,cod_tipo_anuncio";
            }
            if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                $query = "SELECT $fields FROM t_anuncio WHERE cod_categoria_anuncio=$idCategory";
            } else {
                $query = "SELECT $fields FROM t_anuncio WHERE cod_tipo_anuncio=$idKindOfAnnounce AND cod_categoria_anuncio=$idCategory";
            }
	    
	    $query .= " ORDER BY quando_anuncio DESC";

            if (($idKindOfAnnounce == null) or ($idKindOfAnnounce == 0)){
                  $stage = "idCategory=$idCategory&descCategory=$descCategory"; //it's for the indexes
            } else {
                  $stage = "idCategory=$idCategory&descCategory=$descCategory&idKindOfAnnounce=$idKindOfAnnounce&descKindOfAnnounce=$descKindOfAnnounce"; //it's for the indexes
            }
		if ($idCategory == 20){
			$stage .= "&higher=";
		}
            require("/classia/common/commonMoreHeader.php");
?>
