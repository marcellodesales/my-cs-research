<TABLE border=0 cellPadding=0 cellSpacing=0 width="100%" height="1" align="center">
  <TR>
    <TD bgColor=#FFCC00 height="5" width="708">
    	<IMG border=0 height=4 src="/common/images/espaco.gif" width=1></TD> </TR>
  <TR>
    <TD bgcolor="#000066" height=1>
        <table bgcolor="#000066" border="0" cellpadding="0" cellspacing="0" width="100%" height="25">
          <tr>
            <td width="13%" align="center" valign="center"><b><img src="/common/images/cadastro.gif" width=20 height=20>
             <!a href="/site/signin.php" class="menu_top" onMouseOver="return showStatus('Seja nosso anunciante prefencial!')" onMouseOut="clearStatus()">
             <font size="2" face="Verdana" color="#FFFFFF">Cadastro</font></b></a></td>

            <td width="13%" align="center"><b><img border=0 src="/common/images/refer.gif" width=20 height=20><a href="javascript:openWindow('/common/commonReferFriends.php', 'friends', 450, 350)" onMouseOver="return showStatus('Indique-nos a seus amigos!!!')" onMouseOut="clearStatus()" class="menu_top"><font size="2" face="Verdana" color="#FFFFFF">Indique</font></b></a></td>
            <td width="13%" align="center"><b><img src="/common/images/ajuda.gif" width=20 height=20><a href="/site/help.php" onMouseOver="return showStatus('Tire todas suas dúvidas aqui!')" onMouseOut="clearStatus()" class="menu_top"><font size="2" face="Verdana" color="#FFFFFF">Dúvidas</font></b></a></td>
            <td width="17%" align="center"><b><img src="/common/images/maos.gif" height=20><a href="/site/business.php" onMouseOver="return showStatus('Gostaria de ter o cartão virtual de sua empresa?!')" onMouseOut="clearStatus()" class="menu_top"><font size="2" face="Verdana" color="#FFFFFF">Publicidade</font></b></a></td>
      <? if (isset($linkSession)){    ?>
            <td width="17%" align="center"><b><img src="/common/images/announcerManager.gif" height=20><a href="/announcer/?<? echo $linkSession ?>" onMouseOver="return showStatus('Meu gerenciador de anúncios')" onMouseOut="clearStatus()" class="menu_top"><font size="2" face="Verdana" color="#FFFFFF">Meus Anúncios</font></b></a></td>
      <? } ?>
         <form name="search" method="post" action="/ad/getSearch.php" onSubmit="return validateKeyWords()">
            <td width="<? echo $nextWidth ?>" align="right"><img src="/common/images/busca.gif" width=20 height=20>
            	<b><font color="#FFFFFF" face="Verdana" size="2">Pesquisa</font></b>
                <input name="keyWords" value="<? echo $keyWords ?>" size="13" onMouseOver="return showStatus('Procure o que você precisa!')" onMouseOut="clearStatus()">&nbsp;
                <input type="image" border="0" src="/common/images/ok3.gif" width="19" height="11">&nbsp;&nbsp;</td>
         </form>
	</tr>
        </table>
    </TD>
  </TR>
</TABLE>
