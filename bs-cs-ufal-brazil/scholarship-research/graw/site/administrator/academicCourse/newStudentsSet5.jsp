<%@ page import="br.ufal.graw.web.administrator.AdministratorUtility" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page import="br.ufal.graw.exception.DepartmentNotFoundException" %>
<%
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
String departmentID = request.getParameter("departmentID");
String academicCourseName = request.getParameter("academicCourseName");
String academicCourseCode = request.getParameter("academicCourseCode");

AdministratorUtility creator = new AdministratorUtility(database);
try{
	creator.createAcademicCourse(departmentID,academicCourseName,academicCourseCode);
} catch (DepartmentNotFoundException dnfe){
	String message = dnfe.getMessage();
	session.setAttribute("message",message);
	ServletUtility.sendRedirect(response,"../index.jsp");	
}
String message = "Curso Acadêmico criado com sucesso!";
session.setAttribute("message",message);
ServletUtility.sendRedirect(response,"../admiinstrator/index.jsp");
%>
