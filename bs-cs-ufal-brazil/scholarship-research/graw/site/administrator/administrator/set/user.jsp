<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.Student" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>

<%@ page errorPage="/status/errorGeneric.jsp" %>

<%
Administrator admin 	= (Administrator) session.getAttribute("admin");
if (admin==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Por favor logue-se outra vez.");
	session.invalidate();
}else{
	DatabaseLayer database 	= (DatabaseLayer) session.getAttribute("database");
	Utility utility 		= new Utility(database);
	Student students[];
	Professor professors[];
	
	students = utility.getAllStudents();
	professors = utility.getAllProfessors();
%>

	<html>
	<head>
	<title>Configurar Usu&aacute;rio</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	
<link rel="stylesheet" href="../../sources/style/graw.css" type="text/css">
</head>
	
	<body bgcolor="#FFFFFF" text="#000000">
	
<p>Configurar Usu&aacute;rio:</p>
	<table width="50%" border="1" class="tabelaSimples">
    <tr> 
      <td id="headerSimples"> Disciplinas:</td>
    </tr>
	<tr> 
      <td id="corpoSimples"> Estudantes:</td>
    </tr>
	<% for (int i=0 ; i < students.length ; i++){ %>
	    <tr> 
    	  <td > 
			  <a  class="Link" href="/servlet/br.ufal.graw.web.administrator.OpenUser?ID=<%= students[i].getID() %>&kindOfUser=student"><%=students[i].getLogin()%> - <%=students[i].getName()%></a>      
    	  </td>
	    </tr>
	<%}%>
	<tr> 	
      <td id="corpoSimples"> Professores:</td>
    </tr>
	<% for (int i=0 ; i < professors.length ; i++){ %>
	    <tr> 
    	  <td > 
			  <a  class="Link" href="/servlet/br.ufal.graw.web.administrator.OpenUser?ID=<%= professors[i].getID() %>&kindOfUser=professor"><%=professors[i].getLogin()%> - <%=professors[i].getName()%></a>      
    	  </td>
	    </tr>
	<%}%>
	
    
  </table>
	
<p>&nbsp; </p>
	</body>
	</html>
<%}%>