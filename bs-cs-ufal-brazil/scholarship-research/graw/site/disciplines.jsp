<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.UseCase" %>
<%@ page import="br.ufal.graw.Community" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.Department" %>
<%
System.out.println("1");
	DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
	System.out.println("2");

if (database == null){
System.out.println("3");
	database = new DatabaseLayer(); 
	System.out.println("4");
	session = request.getSession(true);
	session.setAttribute("database", database);
	System.out.println("5");
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
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Disciplinas 
      no graW<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" -->As disciplinas s&atilde;o 
      de acesso restrito a alunos e professores dos departamentos das institui&ccedil;&otilde;es 
      presentes no graW. Portanto somente ter&aacute; acesso aqueles usu&aacute;rios 
      que estivem devidamente matriculados em suas institui&ccedil;&otilde;es 
      no ano corrente.<br>
      <br>
      <%
				UseCase useCase = UseCase.getInstance(database);
				Community[] comm = useCase.getDisciplines();
				
				for (int i = 0; i < comm.length; i++){
					Community community = comm[i];
					out.println("<BR><b>T�tulo</b>: "+community.getTitle());
					out.println("<BR><b>Descri��o</b>: "+community.getDescription());										 
					out.println("<BR><b>Respons�vel</b>: "+community.getResponsible().getName());
					out.println("<BR><b>Institui��o</b>: "+new Institute(((Discipline)community).getInstituteID(),database).getName());					
					out.println("<BR><b>Departamento</b>: "+new Department(((Discipline)community).getDepartmentID(),database).getName());
					out.println("<BR><b>Usu�rios</b>: "+community.getQuantUsers());
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
      <div align="center"><font color="#FFFFFF">� 2002. Todos os direitos reservados 
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
