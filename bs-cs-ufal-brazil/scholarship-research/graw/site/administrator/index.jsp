<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="21"><img src="../sources/images/teste2.gif" width="770" height="100" usemap="#Map" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Login<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --> 
	<center>
        <form method="post" action="./../servlet/br.ufal.graw.web.administrator.Login">
		<table border="0" cellspacing="2" cellpadding="0" align="center" height="0" color="#000000" dwcopytype="CopyTableRow">
                    <tr class="escuro"> 
                      <td class="texto_cabecalho" height="21" colspan="2"> 
                        <div align="center">Acesse o graW!</div>
                      </td>
                    </tr>
                    <tr valign="middle" align="center" class="claro"> 
                      <td colspan="2" height="0"> 
                        <table width="85%" border="0" cellspacing="3" cellpadding="2" align="center" height="0">
                          <tr> 
                            <td><b>Usuário</b></td>
                            <td> 
                              
                          <input type="text" name="login" size="20" value="">
                            </td>
                          </tr>
                          <tr> 
                            <td><b>Senha</b></td>
                            <td> 
                              
                          <input type="password" name="password" maxlength="25" size="20" value="">
                            </td>
                          </tr>
                          <tr> 
                            <td colspan="2"> 
                              <div align="center"> 
                                <input type="submit" name="Submit" value="Entrar" class="botao">
                                &nbsp; </div>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
        </form>
	</center>
      <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"><font color="#FFFFFF">® 2002. Todos 
      os direitos reservados - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o 
      - UFAL</font></td>
  </tr>
</table>
<map name="Map"> 
<%// String content = request.getServletContext().getRealPath("/")+"graw";
 //if (!content.equals()) %>
  <area shape="circle" coords="573,86,11" alt="Página principal" title="Informa&ccedil;&otilde;es gerais sobre o GraW" href="/"> 
  <area shape="circle" coords="630,85,11" href="../info.jsp" alt="Informa&ccedil;&otilde;es gerais sobre o GraW" title="Informa&ccedil;&otilde;es gerais sobre o GraW">
  <area shape="circle" coords="690,85,12" href="../signIn/signInNewUser.jsp" alt="Cadastros" title="Cadastros">
  <area shape="circle" coords="742,85,11" href="../help.html" alt="Ajuda" title="Ajuda">
</map>
</body>
<!-- #EndTemplate --></html>
