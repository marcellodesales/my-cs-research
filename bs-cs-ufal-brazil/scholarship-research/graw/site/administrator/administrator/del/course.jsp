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
	<title>Excluir Disciplina</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript1.2">
	<!--
		function confirmation(){
			return window.confirm("Deseja realmente excluir essa disciplina? ")
		}
	// -->
	</script>	
	</head>
	
	<body bgcolor="#FFFFFF" text="#000000">
	
<p>Excluir Disciplina:</p>
	
<form name="formDeleteDiscipline" method="post" onSubmit="return confirmation();"
												action="/servlet/br.ufal.graw.web.administrator.DeleteDiscipline">
  <table width="50%" border="1">
    <tr> 
      <td> Disciplinas:</td>
    </tr>
    <tr> 
      <td> 
        <select name="disciplineCode">
          <% for (int i=0 ; i < disciplines.length ; i++){ %>
          <option value="<%=disciplines[i].getCode()%>"><%=disciplines[i].getCode()+" - "+disciplines[i].getName()%></option>
          <%}%>
        </select>
      </td>
    </tr>
    <tr> 
      <td> 
        <input type="submit" name="submitDiscipline" value="Excluir Disciplina">
      </td>
    </tr>
  </table>
	</form>
	<p>&nbsp; </p>
	</body>
	</html>
<%}%>