<html>
<title>Pager Classi-A / ICQ</title>
<head>
<script language=JavaScript1.2 src="../js/validateClassiaIcqPager.js"></script>
</head>
<body bgcolor="white" aLink="blue" link="red" text=#000000 vLink="red">

<TABLE BGCOLOR="white" BORDER=1 align="center">
<tr><td>
<TABLE BGCOLOR="white" BORDER=0 CELLSPACING=0 CELLPADDING=1>
<FORM name="icqPager" ACTION="<? echo $PHP_SELF ?>" METHOD=post onSubmit="return validateClassiaIcqPager()">
<TR>
	<TD bgcolor="#eaeaea" colspan="2"><b><FONT SIZE=2 FACE="verdana">
	Mensagem para <? echo $announcerName ?> - <? echo $announcerICQ ?></b></TD>
</tr>
<TR>
	<TD colspan="2"><b><FONT SIZE=2 FACE="verdana">
	Referente ao anúncio "<? echo $announceTitle ?>"</b></TD>
</tr>
<TR bgcolor="#eaeaea">
	<TD><FONT SIZE=2 FACE="verdana"><b>Assunto:</b></TD>
	<TD><INPUT TYPE=text name="subject" SIZE=25></TD>
</tr>
<TR>
	<TD><FONT SIZE=2 FACE=verdana><b>Mensagem:</b></TD>
	<TD><TEXTAREA NAME="message" COLS=25 ROWS=8 WRAP=virtual></TEXTAREA></TD>
</tr>
<TR bgcolor="#eaeaea">
	<TD><FONT SIZE=2 FACE="verdana"><b>Seu nome:</b></TD>
	<TD><INPUT TYPE=text NAME=interestedName SIZE=25></TD>
</tr>
<TR>
	<TD><FONT SIZE=2 FACE="verdana"><b>Seu email:</b></TD>
	<TD><INPUT TYPE=text NAME=interestedEmail SIZE=25></TD>
</tr>
<TR bgcolor="#eaeaea">
	<TD colspan="2" align="center"> 
		<INPUT TYPE=submit NAME=submit VALUE="Envie a mensagem"></td>
</tr>
<INPUT TYPE=hidden NAME=announcerName VALUE="<? echo $announcerName ?>">
<INPUT TYPE=hidden NAME=announcerICQ VALUE="<? echo $announcerICQ ?>">
<INPUT TYPE=hidden NAME=announceTitle VALUE="<? echo $announceTitle ?>">
</FORM>
</TABLE>
</td>
</tr>
</table>
</body>
</html>
