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
<html>
<!-- #BeginTemplate "/Templates/administration.dwt" --> 
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW - Página principal</title>
<!-- #EndEditable --> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="21"><img src="../sources/images/teste2.gif" width="770" height="100" usemap="#Map" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online 
      - <!-- #BeginEditable "title" -->Definir disciplina para os professores<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --><br>
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
          <td valign=top> Selecione o professor e a disciplina que pertença a 
            ele. 
            <form method="post" action="./../servlet/br.ufal.graw.web.administrator.Update">
              <input type="hidden" name="acao" value=2>
              <table width="100%">
                <tr> 
                  <td id=headerSimplesFont>Professor</td>
                </tr>
                <tr> 
                  <td id=corpoSimples>
                    <select name="professorID">
                      <%
Vector professores = admin.getProfessores();
for (int i = 0; i < professores.size(); i++) {
	Hashtable linha = (Hashtable) professores.get(i); %>
                      <option value="<%= linha.get("userID") %>"><%= linha.get("name") %></option>
                      <%}%>
                    </select>
                  </td>
                </tr>
                <tr> 
                  <td id=headerSimplesFont>Disciplina</td>
                </tr>
                <tr> 
                  <td id=corpoSimples>
                    <select name="disciplineID">
                      <%
Vector disciplinas = admin.getDisciplinas();
for (int i = 0; i < disciplinas.size(); i++) {
	Hashtable linha2 = (Hashtable) disciplinas.get(i); %>
                      <option value="<%= linha2.get("communityID") %>"><%= linha2.get("title") %></option>
                      <%}%>
                    </select>
                  </td>
                </tr>
                <tr> 
                  <td id=headerSimplesFont> 
                    <center>
                      <input type="submit" name="Submit" value=" Atualizar " class="botao">
                    </center>
                  </td>
              </table>
            </form>
          </td>
        </tr>
      </table>
	  </center>
      <p>&nbsp;</p>
      <!-- #EndEditable --> </td>
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
  <area shape="circle" coords="573,86,11" alt="Página principal" title="Informa&ccedil;&otilde;es gerais sobre o GraW" href="/graw/">
  <area shape="circle" coords="630,85,11" href="../info.jsp" alt="Informa&ccedil;&otilde;es gerais sobre o GraW" title="Informa&ccedil;&otilde;es gerais sobre o GraW">
  <area shape="circle" coords="690,85,12" href="../signIn/signInNewUser.jsp" alt="Cadastros" title="Cadastros">
  <area shape="circle" coords="742,85,11" href="../help.html" alt="Ajuda" title="Ajuda">
</map>
</body>
<!-- #EndTemplate -->
</html>
<%}%>