<%
	String username = (String)session.getAttribute("username");
	String email = (String)session.getAttribute("email");
	username = (username == null) ? "" : username;
	email = (email == null) ? "" : email;
%>
<html>
<!-- #BeginTemplate "/Templates/default.dwt" --> 
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Requisi&ccedil;&atilde;o de nova senha</title>
<!-- #EndEditable --> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="0" width="0"><img src="sources/images/gr101.jpg" width="306" height="100"><img src="sources/images/gr102.jpg" width="242" height="100"><img src="sources/images/gr103.jpg" width="222" height="100" usemap="#Map2" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Requisi&ccedil;&atilde;o 
      de nova senha<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> 
      <p><br>
        Para obter uma nova senha de acesso, faz-se necess&aacute;rio indicar 
        qual seu nome de usu&aacute;rio e o email que sua conta possui.</p>
      <div align="center">
        <% 	String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<font color=red><b>"+message+"</font></b>");
				session.removeAttribute("message");
			}
		%>
      </div>
      <form method="post" action="./servlet/br.ufal.graw.web.LostPassword">
        <table width="52%" border="0" cellspacing="2" cellpadding="0" align="center" height="0" color="#000000">
          <tr class="escuro"> 
            <td class="texto_cabecalho" height="21" colspan="2"> 
              <div align="center"> Nova senha</div>
            </td>
          </tr>
          <tr valign="middle" align="center" class="claro"> 
            <td colspan="2" height="0"> 
              <table width="97%" border="0" cellspacing="3" cellpadding="2" align="center" height="0">
                <tr> 
                  <td width="32%"><b>Nome de Usuário</b></td>
                  <td width="68%"> 
                    <input type="text" name="username" size="20" value="<%= username %>">
                  </td>
                </tr>
                <tr> 
                  <td width="32%"><b>email</b></td>
                  <td width="68%"> 
                    <input type="text" name="email" maxlength="70" size="30" value="<%=email %>">
                  </td>
                </tr>
                <tr> 
                  <td colspan="2"> 
                    <div align="center"> 
                      <input type="submit" name="Submit" value="Requisitar" class="botao">
                      <input type="button" value="Cancelar" class="botao" onClick="javascript:history.back()">
                      &nbsp; </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
      <p>&nbsp;</p>
<%
	session.removeAttribute("username");
	session.removeAttribute("email");	  
%>				
      <!-- #EndEditable --> </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o - UFAL</font></div>
    </td>
  </tr>
</table>
<map name="Map2"> 
  <area shape="circle" coords="25,85,12" href="/">
  <area shape="circle" coords="83,87,13" href="signIn/index.jsp">
  <area shape="circle" coords="142,85,14" href="info.jsp">
  <area shape="circle" coords="195,85,12" href="help.html">
</map>
</body>
<!-- #EndTemplate -->
</html>
