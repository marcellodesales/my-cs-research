<?
    // Begin Prev/Next Links
    // Don't display PREV link if on first page
    if ($offset!=0) {
        $prevoffset=$offset-$limit;
        echo "<a href=\"$PHP_SELF?$stage&offset=$prevoffset\" onMouseOver=\"window.status='Os $limit anúncios anteriores'; return true;\" onMouseOut=\"window.status=''; return true;\" class=\"subcategory\">Anterior</a>&nbsp;&nbsp;";
    }

    // Calculate total number of pages in result
    $pages = intval($totalrows/$limit);

    // $pages now contains total number of pages needed unless there is a remainder from division
    if ($totalrows % $limit) {
        // has remainder so add one page
        $pages++;
    }

    // Now loop through the pages to create numbered links
    // ex. 1 2 3 4 5 NEXT
    for ($i=1; $i<=$pages; $i++) {
        // Check if on current page
        if (($offset/$limit) == ($i-1)) {
            // $i is equal to current page, so don't display a link
            echo "[<font color='red'>&nbsp;$i&nbsp;</font>] ";
        } else {
            // $i is NOT the current page, so display a link to page $i
            $newoffset=$limit*($i-1);
            echo  "<a href=\"$PHP_SELF?$stage&offset=$newoffset\" onMouseOver=\"window.status='Pagina $i'; return true;\" onMouseOut=\"window.status=''; return true;\" class=\"subcategory\">$i</a>&nbsp;&nbsp;";
        }
    }

    // Check to see if current page is last page
    if (!((($offset/$limit)+1)==$pages) && $pages!=1) {
        // Not on the last page yet, so display a NEXT Link
        $newoffset=$offset+$limit;
        echo   "<a href=\"$PHP_SELF?$stage&offset=$newoffset\" onMouseOver=\"window.status='Os próximos $limit anúncios'; return true;\" onMouseOut=\"window.status=''; return true;\" class=\"subcategory\">Próximo</a>";
    }
?>
