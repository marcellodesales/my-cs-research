<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.Group" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>
<%
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
String groupID = request.getParameter("groupID");
Group group = new Group(groupID,databaseLayer);
session.setAttribute("group",group);	
if ((group.getDescription().equals("Alunos")) || (group.getDescription().equals("Professores"))){
	ServletUtility.sendRedirect(response,"academicUser1.jsp");
} else ServletUtility.sendRedirect(response,"newUserForm.jsp");

%>
