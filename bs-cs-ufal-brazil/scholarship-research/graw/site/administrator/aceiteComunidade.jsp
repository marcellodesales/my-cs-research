<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page import="br.ufal.graw.web.administrator.Administrator" %>
<%
Administrator admin = (Administrator)session.getAttribute("user");

if (admin == null) {
	session.invalidate();
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sessão expirou.");
}else{
%>
<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW - Página principal</title>
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
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Aceitar comunidade<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --><br>
      <CENTER>
        <b><font color="red" size="2" face="verdana"> 
        <% 	String message = (String)session.getAttribute("message");
		if (message != null){ 
			out.println(message);
			session.removeAttribute("message");
		}
	%>
        </font></b> 
      <table>
        <tr> 
          <td valign=top> 
            <table>
              <tr> 
                <td id=headerSimplesFont>Comunidade</td>
              </tr>
              <tr> 
                <td id=corpoSimples><%= admin.getNomeComunidade(request.getParameterValues("communityID")[0]) %></td>
              </tr>
              <tr> 
                <td id=headerSimplesFont>Descrição</td>
              </tr>
              <tr> 
                <td id=corpoSimples><%= Utility.strReplace(Utility.strReplace(admin.getDescricaoComunidade(request.getParameterValues("communityID")[0]), "\15", ""), "\12", "<br>") %></td>
              </tr>
              <tr> 
                <td id=corpoSimples> 
                  <form method="post" action="./../servlet/br.ufal.graw.web.administrator.Update">
                    <input type="hidden" name="acao" value=1>
                    <input type="hidden" name="kind" value=<%= request.getParameterValues("kind")[0]%>>
                    <input type="hidden" name="communityID" value="<%= request.getParameterValues("communityID")[0] %>">
                    <input type="checkbox" name="aceite" value=1>
                    Não aceito!<br>
                    Explicação para o não aceite da comunidade:<br>
                    &nbsp;&nbsp;
                    <textarea name="explicacao" rows=4 cols=60></textarea>
                    &nbsp;&nbsp;<br>
                    <center>
                      <input type="submit" name="Submit" value=" Atualizar " class="botao">
                    </center>
                  </form>
                </td>
            </table>
          </td>
        </tr>
      </table>
	  </center>
      <p>&nbsp;</p>
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
<%}%>