<%@ page import="br.ufal.graw.web.administrator.AdministratorUtility" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
String instituteName = request.getParameter("instituteName");
String instituteAbbre = request.getParameter("instituteAbbreviation");
String stateID = request.getParameter("stateID");
String countryID = request.getParameter("countryID");

AdministratorUtility creator = new AdministratorUtility(database);
creator.createInstitute(instituteName,instituteAbbre,Integer.parseInt(stateID),Integer.parseInt(countryID));
String message = "Instituto Criado com sucesso!";
session.setAttribute("message",message);
ServletUtility.sendRedirect(response,"../index.jsp");
%>