<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.User" %>
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
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->P&aacute;gina principal<!-- #EndEditable --></b></font></td>
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
      </CENTER>
      <table>
        <tr>
          <td valign=top>
		    
	    <table width="201" border="1" cellpadding="1" cellspacing="0">
	      <tr> 
		<td> 
		  <div align="center"><b>Acad&ecirc;micos - Inserir Novo(a)</b></div>
		</td>
	      </tr>
	      <tr> 
		<td><a href="institute/newInstitute.jsp">Istitui&ccedil;&atilde;o</a></td>
	      </tr>
	      <tr> 
		<td><a href="department/newDepartment.jsp">Departamento</a></td>
	      </tr>
	      <tr> 
		<td><a href="academicCourse/newAcademicCourse.jsp">Curso Acad&ecirc;mico</a></td>
	      </tr>
	      <tr> 
		<td><a href="academicCourse/newStudents.jsp?userType=<%=User.STUDENT%>">Cadastrar Alunos</a></td>
	      </tr>
	      <tr>
		<td><a href="academicCourse/newStudents.jsp?userType=<%=User.PROFESSOR%>">Cadastrar Professores</a></td>
	      </tr>
	      <tr>
		<td><a href="definirDisciplina.jsp">Definir disciplinas para os professores</a></td>
	      </tr>
	    </table>
            <br>
          </td>
          <td valign=top>
	        <h1>Novas comunidades</h1>
<% Vector v; Hashtable l;
v = admin.getDisciplinasPendentes();
if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			  <td id=headerSimplesFont>Disciplinas</td>
			</tr>
<%	for (int i = 0; i < v.size(); i++) {
		l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=1&communityID=<%= l.get("communityID") %>"><%=l.get("title")%></a></td>
			  </tr>
<%	} %>
			</table>
<%}
if (admin.administradorGeral()) {
	v = admin.getCursosExternosPendentes();
	if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			  <td id=headerSimplesFont>Cursos externos</td>
			</tr>
<%		for (int i = 0; i < v.size(); i++) {
			l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=2&communityID=<%= l.get("communityID") %>"><%=l.get("title")%></a></td>
			  </tr>
<%		} %>
			</table>
<%	}
}
v = admin.getCursosAcademicosPendentes();
if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			    <td id=headerSimplesFont>Cursos acad&ecirc;micos</td>
			</tr>
<%	for (int i = 0; i < v.size(); i++) {
		l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=2&communityID=<%= l.get("communityID") %>"><%=l.get("title")%></a></td>
			  </tr>
<%	} %>
			</table>
<%}
if (admin.administradorGeral()) {
	v = admin.getGruposExternosPendentes();
	if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			    <td id=headerSimplesFont>Grupos externos</td>
			</tr>
<%		for (int i = 0; i < v.size(); i++) {
			l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=3&communityID=<%= l.get("communityID") %>"><%=l.get("title")%></a></td>
			  </tr>
<%		} %>
			</table>
<%	}
}
v = admin.getGruposDisciplinaPendentes();
if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			  <td id=headerSimplesFont>Grupos ligados a uma discplina</td>
			</tr>
<%	for (int i = 0; i < v.size(); i++) {
		l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=3&communityID=<%= l.get("communityID") %>"><%=l.get("gtitle")%> - <%= l.get("dtitle") %></a></td>
			  </tr>
<%	} %>
			</table>
<%}
if (admin.administradorGeral()) {
	v = admin.getGruposCursoExternoPendentes();
	if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			    <td id=headerSimplesFont>Grupos ligados a um curso externo</td>
			</tr>
<%		for (int i = 0; i < v.size(); i++) {
			l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=3&communityID=<%= l.get("communityID") %>"><%=l.get("gtitle")%> - <%= l.get("ctitle") %></a></td>
			  </tr>
<%		} %>
			</table>
<%	}
}
v = admin.getGruposCursoAcademicoPendentes();
if (v.size() > 0) {%>
			<table width="100%">
			<tr>
			    <td id=headerSimplesFont>Grupos ligados a um curso acad&ecirc;mico</td>
			</tr>
<%	for (int i = 0; i < v.size(); i++) {
		l = (Hashtable)v.get(i);%>
			  <tr>
			    <td id=corpoSimples><a href="aceiteComunidade.jsp?kind=3&communityID=<%= l.get("communityID") %>"><%=l.get("gtitle")%> - <%= l.get("ctitle") %></a></td>
			  </tr>
<%	} %>
			</table>
<%} %>
	  	  </td>
        </tr>
      </table>
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