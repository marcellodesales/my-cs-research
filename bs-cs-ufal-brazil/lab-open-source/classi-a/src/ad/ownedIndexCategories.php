              <TR>
                <TD bgColor=#000066 class=menu2 align="center">
                    <font size="1" face="Verdana,Arial" color="#FFFFFF"><b><u>Categorias</u></b></font>
                </TD>
              </TR>
<?
        include("/classia/classes/category.php");

        $categories = new Category();

        foreach($categories->category as $category){
                $id    = $category["id"];
                $desc  = $category["desc"];
                $quant = $category["quant"];

                $link  = "getCategory.php?";  //linkSession is registered on session, if exists
                $link .= (isset($announcerID)) ? $linkSession."&" : "";
                $link .= "idCategory=".$id;
                $mouse = "return showStatus('".$desc."')";

echo"           <TR bgcolor=\"#FFCC00\" onmouseover=\"mOvr(this,'#ffffff'); $mouse\" onmouseout=\"mOut(this,'#FFCC00'); clearStatus();\">
                        <TD onClick=mClk(this)><a href=\"$link\" class=linkMenu onmouseover=\"$mouse\" onmouseout=\"clearStatus();\"><font size=\"1\" face=\"Verdana,Arial\"><b>$desc</b>($quant)</a></font></TD>
                </TR>\n";
        }
?>