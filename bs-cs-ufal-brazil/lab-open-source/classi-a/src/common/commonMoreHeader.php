<?
    //    If $offset is set below zero (invalid) or empty, set to zero
    if (empty($offset) || $offset < 0) {
        $offset=0;
    }

    //    Connect to database
    $db = mysql_connect("localhost",.php","51527981");
    mysql_select_db("classA");

    //    Set $limit.  $limit = Max number of results per 'page'
    //    Set $totalrows = total number of rows that unlimited query would return
    //        (total number of records to display across all pages)
    $limit = 15;
    
    $numresults = mysql_query($query,$db);
    $totalrows  = mysql_num_rows($numresults);

    // Set $begin and $end to record range of the current page
    $begin =($offset+1);
    $end = ($begin+($limit-1));
    if ($end > $totalrows) {
        $end = $totalrows;
    }
?>
