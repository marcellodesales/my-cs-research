<%
		String n = request.getParameter("n");
		String e = request.getParameter("e");
		String d = request.getParameter("d");
		String email = request.getParameter("email");
		email = (email != null) ? email : "";		
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>CriptOnline - Mensagens criptografadas com RSA</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="sources/style/graw.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0" background="sources/image/background.jpg">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td height="48" colspan="2"> 
      <p><b><font size="5">CriptOnline - <font size="4">Mensagens criptografadas 
        com <img src="sources/image/rsa.gif" width="100" height="43" align="absmiddle"></font></font></b></p>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Cadastro 
      de novo usu&aacute;rio!<!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="admin/index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> 
      <p>&nbsp;</p>
      <form name="form1" method="post" action="signin2.jsp">
        <table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td colspan="4"><b>&nbsp;Novo usuário!</b></td>
          </tr>
          <tr> 
            <td width="17%">Primeiro nome: </td>
            <td width="29%"> 
              <input type="text" name="firstName">
            </td>
            <td width="17%">&Uacute;ltimo nome: </td>
            <td width="37%"> 
              <input type="text" name="lastName">
            </td>
          </tr>
          <tr> 
            <td width="17%">Email</td>
            <td colspan="3"> 
              <input type="text" name="email" value="<%= email %>" size="45">
            </td>
          </tr>
          <tr> 
            <td width="17%">Senha</td>
            <td width="29%"> 
              <input type="password" name="password1">
            </td>
            <td width="17%">Confirma Senha</td>
            <td width="37%">
              <input type="password" name="password2">
            </td>
          </tr>
          <tr> 
            <td colspan="4"> 
              <div align="right">
                <input type="submit" name="Submit" value="Cadastrar-se!">
                <input type="reset" value="Apagar tudo!">
		<%
			if (n != null && e != null && d != null){%>
				<input type='hidden' name='n' value="<%= n %>">
				<input type='hidden' name='e' value="<%= e %>">
				<input type='hidden' name='d' value="<%= d %>">
		<%	}	%>				
              </div>
            </td>
          </tr>
          <tr> 
            <td colspan="4">&nbsp;</td>
          </tr>
        </table>
            </form>	  
      <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="11" align="left" valign="top" colspan="2"> 
      <div align="center"><font color="#FFFFFF">® 2002. Marcello Junior - TCI 
        / Departamento de Tecnologia da Informa&ccedil;&atilde;o / UFAL</font></div>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
