<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>
<%@ page import="br.ufal.bibliweb.User" %>
<%@ page import="br.ufal.bibliweb.AbstractUser" %>
<%@ page import="br.ufal.bibliweb.Lend" %>
<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
String userID = request.getParameter("userID");
User user = null;
try{
	user = AbstractUser.getRealUser(userID,databaseLayer);
	
} catch (Exception e){
	session.setAttribute("message",e.getMessage());
	ServletUtility.sendRedirect(response,"index.jsp");
}
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" -->
<title>Seja Bem-Vindo ao BibliWEB - Empréstimos de Exemplares!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td height="29" colspan="2"> 
      <p><b><img src="../../sources/images/bibliweb.jpg" width="109" height="107" align="middle"><font size="5">Bibliweb 
        - <i>Biblioteca na Web</i></font></b></p>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Empr&eacute;stimos 
      N&atilde;o Devolvidos - Usu&aacute;rio <%= user.getName() %><!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> <BR>
      <BR>
      <table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont>Empr&eacute;stimos: <%= user.getName() %></td>
        </tr>
        <%
	Lend[] lends = ComponentSet.getLendsNotReturned(user,databaseLayer);
	if (lends.length > 0){
		for (int i=0; i < lends.length; i++){ %>
        <tr> 
          <td id=corpoSimples>&nbsp;&nbsp; <a href="lend.jsp?lendID=<%= lends[i].getID() %>"> 
            <%= lends[i].getID()%> - <%= lends[i].getExemplarCopy().getAbstractExemplar().getTitle()%> (<%= lends[i].getExemplarCopy().getAbstractExemplar().getID()%>)</a></td>
        </tr>
        <%		} 
 	} else {     %>
        <tr> 
          <td> <div align="center"><font color="red">&nbsp;&nbsp;Ainda não existem 
              Empréstimos para o usu&aacute;rio especificado!</font></div></td>
        </tr>
        <%		}      %>
        <tr> 
          <td id=headerSimplesFont>&nbsp;</td>
        </tr>
      </table>
      <br>
      <center>
        <a href="index.jsp">Voltar</a>
      </center>
      <!-- #EndEditable --> </td>
  </tr>
  <tr class="escuro"> 
    <td height="11" align="left" valign="top" colspan="2"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o / Banco 
        de Dados II - UFAL</font></div>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
