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
	<title>Excluir Usu&aacute;rio</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript1.2">
	<!--
		function confirmation(){
			return window.confirm("Deseja realmente excluir essa usuário? ")
		}
	// -->
	</script>
	</head>
	
	<body bgcolor="#FFFFFF" text="#000000">
	<p>Excluir Usu&aacute;rio:</p>
	
<form name="formDeleteUser" method="post" 	onSubmit="return confirmation();"
											action="/servlet/br.ufal.graw.web.administrator.DeleteUser">
  <table width="50%" border="1">
    <tr> 
      <td> Estudantes:</td>
      <td> Professores:</td>
    </tr>
    <tr> 
      <td> 
        <select name="selectStudent">
			<% for (int i=0 ; i < students.length ; i++){ %>
  	        	<option value="<%=students[i].getLogin()%>"><%=students[i].getLogin()+" - "+students[i].getName()%></option>
			<%}%>
        </select>
      </td>
      <td> 
        <select name="selectProfessor">
			<% for (int i=0 ; i < professors.length ; i++){ %>
  	        	<option value="<%=professors[i].getLogin()%>"><%=professors[i].getLogin()+" - "+professors[i].getName()%></option>
			<%}%>		
        </select>
      </td>
    </tr>
    <tr>
      <td>
        <input type="submit" name="submitStudent" value="Excluir Estudante">
      </td>
      <td>
        <input type="submit" name="submitProfessor" value="Excluir Professor">
      </td>
    </tr>
  </table>
	</form>
	<p>&nbsp; </p>
	</body>
	</html>
<%}%>