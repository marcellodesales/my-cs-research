<table width="95%" border=0>
<tr>
	<td width="80%">
		<table width="100%" border=0 cellspacing="0" cellpadding="1">
		<tr>
			<td bgcolor="#000066" colspan="2" align="center" height="16">
				<font size="1" color="#ffffff" face="verdana"><b>
				Dados de sua conta</font></b></td>
		</tr>
		<tr>
			<td bgcolor="#EAEAEA" width="15%">
				<font size="1" face="verdana">
				<b>Usuário</b></FONT></td>
			<td bgcolor="#EAEAEA" width="60%">
				<font size="1" face="verdana">
		                <? echo $announcer->username ?></font></td>
		</tr>
		<tr>
			<td bgcolor="#ffffff">
				<font size="1" face="verdana">
				<b>Anúncios</b></FONT></td>
			<td bgcolor="#ffffff">
				<font size="1" face="verdana">
				<? echo $quantAnnouncesNow ?> / <? echo $qnat = ($quantAnnouncesPerAnnouncer == 1000) ? "Ilimitado número de " : $quantAnnouncesPerAnnouncer ?> anúncios permitidos</font></td>
		</tr>		
		<tr>
			<td bgcolor="#EAEAEA">
				<font size="1" face="verdana">	
				<b>Ultimo acesso</b></font></td>
			<td bgcolor="#EAEAEA">
				<font size="1" face="verdana">	
				<? echo $lastAccess ?></font></td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">
				<font size="1" face="verdana">
				<b>Ultimo IP</b></font></td>
			<td bgcolor="#FFFFFF">
				<font size="1" face="verdana">
                                <? echo $lastIP ?></font></td>
		</tr>
		<tr>
			<td bgcolor="#EAEAEA">
 				<font size="1" face="verdana">
				<b>IP Atual</b></font></td>
			<td bgcolor="#EAEAEA">
				<font size="1" face="verdana">
				<? 
                                 $add = $util->getRemoteDNS($announcerIP);
                                 $announcerDNS = $add . " ($REMOTE_ADDR)" ?></font></td>
		</tr>
		<tr bgcolor="#000066">
			<td colspan="2">
				&nbsp;</td>
				</tr>
		</table></td>
</tr>
</table>
