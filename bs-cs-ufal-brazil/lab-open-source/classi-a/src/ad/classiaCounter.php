<? 

$counterFile = 'counter.dat';

    if (File_Exists($counterFile)){ 

        $file = FOpen($counterFile,rw); 
        $acc  = FGets($file,6); 
        $acc  = $acc+1; 
        FClose($file); 

    } else $acc=1; 
     
    $file= FOpen($counterFile,w); 
    FputS ($file,$acc); 
    FClose($file); 

?>
