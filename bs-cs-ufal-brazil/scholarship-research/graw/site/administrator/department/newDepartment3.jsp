<%@ page import="br.ufal.graw.web.administrator.AdministratorUtility" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page import="br.ufal.graw.exception.InstituteNotFoundException" %>
<%
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
String instituteID = request.getParameter("instituteID");
String departmentName = request.getParameter("departmentName");
String departmentCode = request.getParameter("departmentCode");
String departmentWebDomain = request.getParameter("departmentWebDomain");

AdministratorUtility creator = new AdministratorUtility(database);
try {
	creator.createDepartment(instituteID,departmentCode,departmentName,departmentWebDomain);
} catch (InstituteNotFoundException infe){
	String message = infe.getMessage();
	session.setAttribute("message",message);
	ServletUtility.sendRedirect(response,"newDepartment2.jsp");
}
String message = "Departamento criado com sucesso!";
session.setAttribute("message",message);
ServletUtility.sendRedirect(response,"../index.jsp");
%>
