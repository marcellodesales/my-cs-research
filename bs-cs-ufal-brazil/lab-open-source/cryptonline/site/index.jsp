<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>CriptOnline - Mensagens Criptogradas com RSA</title>
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" --><!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="admin/index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" -->
      <div align="center">
        <table width="89%" border="0">
          <tr>
            <td width="61%"><img src="sources/image/splash.gif" width="402" height="377"></td>
            <td width="39%" valign=top> 
              <div align="right"><BR>
                <BR>
                <b><a href="signin.jsp">Cadastro!</a></b> </div>
              <form name="form1" method="post" action="login.jsp">
                <table width="75%" border="1" align="right" cellpadding="0" cellspacing="0">
                  <tr class="escuro"> 
                    <td><font color="#FFFFFF"><b>Email</b></font></td>
                  </tr>
                  <tr> 
                    <td> 
                      <input type="text" name="email">
                    </td>
                  </tr>
                  <tr class="escuro"> 
                    <td><b><font color="#FFFFFF">Senha</font></b></td>
                  </tr>
                  <tr> 
                    <td> 
                      <input type="password" name="password">
                    </td>
                  </tr>
                  <tr>
                    <td> 
                      <div align="right">
                        <input type="submit" name="Submit" value="Entrar">
                      </div>
                    </td>
                  </tr>
                </table>
       			<p><br>
                  <br>
                </p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
      <% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>				
                <p align="right"><font size="2">Interessado em como &eacute; realizado 
                  o algoritmo de criptografia RSA? Crie um login e envie mensagens 
                  criptografadas para seus amigos! Veja como funciona os principais 
                  algoritmos utilizados atrav&eacute;s de um log interativo!</font></p>
                <p><font size="2">* MDC Extendido Euclides<br>
                  * FI(n)<br>
                  * outros...</font></p>
              </form>			  
            </td>
          </tr>
        </table>
      </div>
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
