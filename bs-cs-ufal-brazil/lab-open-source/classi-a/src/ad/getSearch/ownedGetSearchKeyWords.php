<?
	$keys = explode(" ",$keyWords);
	
	$notKeys = array();
	$yeahKey = array();
 	
        for($i=0; $i<count($keys); $i++) {
 
              if (strlen($keys[$i]) > 20){
		    array_push($notKeys, $keys[$i]);
//                    echo "<BR>A palavra chave \"$value\" não é válida.<BR>Contém ".strlen($value)." caracteres";
		    $foundKeyError = true;
              } else { 
		array_push($yeahKey, $keys[$i]);
		if ($i == 0){
			$cond = "";
		} else {
			$cond = "";
		}

        }
	$newKeyDesc = implode(", ",$yeahKey);
        $keyWords = implode("%",$yeahKey);
//        echo "<BR>Para a consulta temos $keyWords";
?>        
