
              <table border="1" cellspacing="0" bordercolor="#000066" width=90%>
                <tr>
                  <td bgcolor="#000066" align="center">
                    <b><u><font size="1" face="Verdana" color="#FFFFFF">Categorias</font></u></b></td>
                </tr>
                <form name="cx4.php" onSubmit="return getDesc()" action="/news/getCategory.php">
		<tr>
		  <td align="center"><?
	include("/classia/classes/category.php");

        $categories = new Category();
?>
            <select name="idCategory">
<?
foreach($categories->category as $category){
        $id    = $category["id"];
        $desc  = $category["desc"];
        $quant = $category["quant"];

	$option = ($id == $idCategory) ? "<option SELECTED value=\"$id\">" : "\n<option value=\"$id\">";
	$alloption = $option . $desc ."</option>";
	echo "       $alloption";
}
?>
	   </td>
        </tr>
	<tr>
	  <td align="center">
	    <input type="hidden" name="descCategory" value="<? echo $descCategory ?>">
	    <input name="submit" type="image" src="/common/images/ok_login.gif" border="0"></td>
		</tr>
                </form>
	       </table>

