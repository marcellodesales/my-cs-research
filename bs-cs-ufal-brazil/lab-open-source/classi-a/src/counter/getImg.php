<?
    header("Content-Type:image/gif");
    $img = "images/$myposition.gif";
    $myimg = fopen($img,"r");
    $conteudoImg = fread ($myimg,filesize("$img"));
    print $conteudoImg;
    fclose ($myimg);
?>
