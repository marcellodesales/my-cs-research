<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.AbstractUser" %>
<%@ page import="br.ufal.bibliweb.AbstractExemplar" %>
<%@ page import="br.ufal.bibliweb.ExemplarCopy" %>
<%@ page import="br.ufal.bibliweb.Lend" %>
<%@ page import="br.ufal.bibliweb.Exemplar" %>
<%@ page import="br.ufal.bibliweb.User" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>

<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

String exemplarCopyID = request.getParameter("exemplarCopyID");
String renterID = request.getParameter("renterID");
String clerkID = request.getParameter("clerkID");

Exemplar exemplar = null;
try{
	User renter = AbstractUser.getRealUser(renterID,databaseLayer);
	User clerk = AbstractUser.getRealUser(clerkID,databaseLayer);

	ExemplarCopy exemplarCopy = new ExemplarCopy(exemplarCopyID,databaseLayer);
	
	String newLendID = Lend.createNewLend(exemplarCopy.getID(),renter.getID(),clerk.getID(),databaseLayer);
	ServletUtility.sendRedirect(response,"lend.jsp?lendID="+newLendID);
	
} catch (Exception e){
	session.setAttribute("message",e.getMessage());
	ServletUtility.sendRedirect(response,"index.jsp");
}
%>