<table width="100%" border=0 cellpadding="1" cellspacing="0">
<tr bgcolor="#eaeaea">
	<td><img src="/common/images/getAnnounce.gif" align="right">
	<font face="verdana" size="1"><p align="justify">
	Se voc� recebeu algum email com indica��o de algum an�ncio, coloque o c�digo neste campo para visualiz�-lo.</font></td>
</tr>
<tr bgcolor="#000066" align="center">
        <td><b><font face="verdana" color="ffffff" size="1">
          Ver An�ncio c�digo:</font></b></td>
</tr>
<tr bgcolor="#eaeaea">
	<td align="center">
		<form name="getAnnounce" method="post" action="getAnnounce.php" onSubmit="return validateGetAnnounce()">
                <font face="Verdana" size="1" color="#000066"><b><input type="text" name="idAnnounce" size="9" maxlength="15" onMouseOver="return showStatus('C�digo do an�ncio de 15 caracteres')" onMouseOut="clearStatus()" value="<? echo $cameEmail ?>">
		<BR><input type="submit" value="Abrir"></form>
	</td>
</tr>
</table>
