<?
	include("/classia/classes/category.php");

$categories = new Category();

?>
        <form name="cx4.php" onSubmit="return getDesc()" action="/ad/getCategory.php">
            <select name="idCategory">
<?
foreach($categories->category as $category){
        $id    = $category["id"];
        $desc  = $category["desc"];

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
	    <input name="submit" type="image" src="/common/images/ok_login.gif" border="0">
