<TABLE border="0" cellPadding=0 cellSpacing=0 width="100%">
  <TR>
    <TD bgColor=#FFCC00 height="5" colspan="2">
    <font size="2" face="Verdana"><IMG border=0 height=4 src="/common/images/espaco.gif" width=1></font></TD></TR>
  <TR>
    <TD bgcolor="#000066" valign="middle" height="25" width="30%">
    	<font size="2" face="Verdana" color="#FFFFFF">
    	<b>&nbsp;&nbsp;Seja bem-vindo <? echo $announcer->name ?>!</b></font></TD>

    <TD bgcolor="#000066" height="25" valign="middle"><b>
    <?  if (session_is_registered("message")){  ?>
        <font size="2" face="Verdana" color="white"><b><? echo $message ?></b></font>
<?      session_unregister("message");
        }
?>&nbsp;&nbsp;</TD>
  </TR>
</TABLE>
