<?
  $descKOA = ($descKindOfAnnounce == null) ? "Todos os tipos de anuncio" : $descKindOfAnnounce;
  $descKOA = ($descKindOfAnnounce == "Todos") ? "Todos os tipos de anuncio" : $descKOA;

  if ($totalrows != 0){
        $onlyOne = ($totalrows == 1);
        $quant   = $onlyOne ? "<b>Anúncio</b> encontrado" : "<b>Anúncios</b> encontrados";
?>
        <table width="95%">
        <tr>
                <td width="50%"><font face="verdana" size="2">
                       <? echo $quant.": ".$totalrows; ?><Br>
                       Mostrando anúncios de <b><? echo $begin ?></b> a <b><? echo $end ?><b/>
                </td>

                <td width="50%"><font face="verdana" size="1">
<? if ($goKindOA){  ?>
                       <p align="justify"><img src="/common/images/info.gif" align="right">
			Você pode refinar sua pesquisa escolhendo o tipo de anúncio no quadro ao lado. 

<? } 
   if ($goSubEspc){
                   echo "Também pode escolher uma subcategoria acima.";
   } 
?>
                   &nbsp; </font></p></td>
        </tr>
        </table>
<?
  } else {

          switch($PHP_SELF) {

                    case "getCategory.php":
                           $dirAnnounces = "$descKOA $DIR_SEPARATOR $descCategory";
                           break;
                    case "getSubcategory.php":
                           $dirAnnounces = "$descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory";
                           break;
                    case "getEspecification.php":
                           $dirAnnounces = "$descKOA $DIR_SEPARATOR $descCategory $DIR_SEPARATOR $descSubcategory $DIR_SEPARATOR $descEspecification";
                           break;
         }
?><BR>
        <table width="80%">
        <tr>
                <td><font face="verdana" size="2"><? echo $actualDir2 ?>
		<p align="justify">Não existe anúncios pertencentes a esta procura. Caso queira anúnciar, cadastre-se e anuncie totalmente grátis!<BR><BR>
                <b>Quer falar conosco?</b><BR>
                Se você tiver alguma dúvida, sugestão ou reclamação sobre o Classi-A, mande um e-mail para <BR><a href="mailto:webmaster@classi-a.com.br" class="subcategory"><font color="red">webmaster@classi-a.com.br</font></a>.
		<BR><BR>
                O Classi-A agradece.</font></p></td>
        </tr>
        </table>
<?
  }
?>
