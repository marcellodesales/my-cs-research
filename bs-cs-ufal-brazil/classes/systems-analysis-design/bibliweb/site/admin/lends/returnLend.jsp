<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.Lend" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>

<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

Lend lend = null;
String lendID = request.getParameter("lendID");
try{
	lend = new Lend(lendID,databaseLayer);
	Lend.returnLend(lend.getExemplarCopy(),databaseLayer);	
	ServletUtility.sendRedirect(response,"lend.jsp?lendID="+lendID);
} catch (Exception e){
	session.setAttribute("message",e.getMessage());
	ServletUtility.sendRedirect(response,"index.jsp");
}
%>