<?
	$partnerQuantity = $secure->getPartnerQuantity();
	srand((double)microtime()*1000000);
        $randy[0] = rand(0, $partnerQuantity - 1);
        $randy[1] = rand(0, $partnerQuantity - 1);
        $randy[2] = rand(0, $partnerQuantity - 1);
        $continue = true;
        while($continue){
               if (($randy[0] != $randy[1]) && ($randy[0] != $randy[2]) && ($randy[1] != $randy[2])){
                    $continue = false;
               } else {
                     $i++;
                     $randy[0] = rand(0, $partnerQuantity - 1);
                     $randy[1] = rand(0, $partnerQuantity - 1);
                     $randy[2] = rand(0, $partnerQuantity - 1);
                     $continue = true;
               }
        }
	
	$bannerInfo1 = $secure->getPartnerLimit($randy[0]);
	$bannerInfo2 = $secure->getPartnerLimit($randy[1]);
	$bannerInfo3 = $secure->getPartnerLimit($randy[2]);

?>

<a href=# onClick="openWindow('/partner/?partnerID=<? echo $bannerInfo1["cod_associado"]; ?>','ecard',650,400); return false;" onMouseOver="return showStatus('<? echo "Sócio do Classi-A: ".$bannerInfo1["nome_associado"]; ?>')" onMouseOut="clearStatus()">

<?
                echo "<img border='0' width='120' height='60' alt='" . $bannerInfo1["nome_associado"] . "' src='/partner/images/banner/" . $bannerInfo1["cod_associado"] . "." . $bannerInfo1["banner_associado"] . "'>";
        echo "</a>";
        echo "<BR>";
        echo "<BR>";

?>

<a href=# onClick="openWindow('/partner/?partnerID=<? echo $bannerInfo2["cod_associado"]; ?>','ecard',650,400); return false;" onMouseOver="return showStatus('<? echo "Sócio do Classi-A: ".$bannerInfo2["nome_associado"]; ?>')" onMouseOut="clearStatus()">

<?
                echo "<img border='0' width='120' height='60' alt='" . $bannerInfo2["nome_associado"] . "' src='/partner/images/banner/" . $bannerInfo2["cod_associado"] . "." . $bannerInfo2["banner_associado"] . "'>";
        echo "</a>";
        echo "<BR>";
        echo "<BR>";

?>

<a href=# onClick="openWindow('/partner/?partnerID=<? echo $bannerInfo3["cod_associado"]; ?>','ecard',650,400); return false;" onMouseOver="return showStatus('<? echo "Sócio do Classi-A: ".$bannerInfo3["nome_associado"]; ?>')" onMouseOut="clearStatus()">

<?

                echo "<img border='0' width='120' height='60' alt='" . $bannerInfo3["nome_associado"] . "' src='/partner/images/banner/" . $bannerInfo3["cod_associado"] . "." . $bannerInfo3["banner_associado"] . "'>";
        echo "</a>";
        echo "<BR>";
        echo "<BR>";
?>
