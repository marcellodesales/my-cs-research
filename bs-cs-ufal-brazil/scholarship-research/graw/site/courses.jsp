<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.UseCase" %>
<%@ page import="br.ufal.graw.Community" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.Department" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.Utility" %>
<%
	DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");


if (database == null){
	database = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", database);
}		
%>
<html>
<!-- #BeginTemplate "/Templates/default.dwt" --> 
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao Gr@W!</title>
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
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Cursos 
      no graW<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> Os cursos abaixo listados 
      poder&atilde;o ser acessados por qualquer usu&aacute;rio, dependendo das 
      pol&iacute;ticas de acesso de cada um, definidas pelo respons&aacute;vel 
      das mesmas.<br>
      <br>
      <%
				UseCase useCase = UseCase.getInstance(database);
				Community[] courses = useCase.getCourses();

				for (int i = 0; i < courses.length; i++){
					Community community = courses[i];
					out.println("<BR><b>Título</b>: "+community.getTitle());
					out.println("<BR><b>Descrição</b>: "+community.getDescription());										 
					out.println("<BR><b>Responsável</b>: "+community.getResponsible().getName());
					out.println("<BR><b>Usuários</b>: "+community.getQuantUsers());
					out.println("<BR><b>Mensagens no Forum</b>: "+community.getQuantMessages());
					out.println("<BR><b>Documentos</b>: "+community.getQuantDocuments());
					out.println("<BR><b>Links</b>: "+community.getQuantLinks());
					out.println("<BR><b>Desde</b> "+Utility.getFormatedDate(community.getID(),"EEEEEE, dd 'de' MMMMMM 'de' yyyy"));									
					out.println("<BR>");
				}						

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
