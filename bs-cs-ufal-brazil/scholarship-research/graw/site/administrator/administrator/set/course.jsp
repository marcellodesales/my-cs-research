<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>

<%@ page errorPage="/status/errorGeneric.jsp" %>
<%
Administrator admin 	= (Administrator) session.getAttribute("admin");
if (admin==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Por favor logue-se novamente.");
	session.invalidate();
}else{
	DatabaseLayer database 	= (DatabaseLayer) session.getAttribute("database");
	Utility utility 		= new Utility(database);
	Discipline disciplines[];
	
	disciplines = utility.getAllDisciplines();
%>

	<html>
	<head>
	<title>Configurar Disciplina</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
	
<link rel="stylesheet" href="../../sources/style/graw.css" type="text/css">
</head>
	
	<body bgcolor="#FFFFFF" text="#000000">
	
<p>Configurar Disciplina:</p>
	

  <table width="50%" border="1" class="tabelaSimples">
    <tr> 
      <td id="headerSimples"> Disciplinas:</td>
    </tr>
	<% for (int i=0 ; i < disciplines.length ; i++){ %>
	    <tr> 
    	  <td > 
			  <a  class="Link" href="/servlet/br.ufal.graw.web.administrator.OpenDiscipline?ID=<%= disciplines[i].getID() %>"><%=disciplines[i].getCode()%> - <%=disciplines[i].getName()%></a>      
    	  </td>
	    </tr>
	<%}%>
    
  </table>
	
	<p>&nbsp; </p>
	</body>
	</html>
<%}%>