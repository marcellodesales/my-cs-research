      <TABLE bgColor=#FFCC00 border=0 width="100%" cellPadding=0 cellSpacing=0 align="center">
        <TR>
           <TD><br><b><font size="1" face="Verdana">
        &nbsp;&nbsp;<img src="/common/images/gohome.gif"><a href="/?<? echo session_name()."=".session_id() ?>" class="menu_top" onMouseOver="return showStatus('Continuar a procura de anúncios!')" onMouseOut="clearStatus()">
          Página Principal</a><BR>

        &nbsp;&nbsp;<img src="/common/images/docnew.gif">
<?   if (($quantAnnouncesNow < $quantAnnouncesPerAnnouncer) && ($quantAnnouncesNow != $quantAnnouncesPerAnnouncer)){  ?>
        <a href="signNewAd.php?<? echo session_name()."=".session_id() ?>" class="menu_top" onMouseOver="return showStatus('Adicione novo anúncio!')" onMouseOut="clearStatus()">
<?   }  ?>Adicionar Anúncio</a><BR>

        &nbsp;&nbsp;<img src="/common/images/updPerfil.gif"><a href="modifyUser.php?<? echo session_name()."=".session_id() ?>" class="menu_top" onMouseOver="return showStatus('Modifique suas informações pessoais!')" onMouseOut="clearStatus()">
          Modificar Perfil</a><BR>

        &nbsp;&nbsp;<img src="/common/images/logout.gif"><a href="/logout.php?<? echo session_name()."=".session_id() ?>" class="menu_top" target="_top" onMouseOver="return showStatus('Sair!')" onMouseOut="clearStatus()">
          Sair</a></font>
          </b>
               <p><img border="0" src="/common/images/botton_left.gif" width="134" height="9"></P></font></TD>
        </TR>
     </TABLE>