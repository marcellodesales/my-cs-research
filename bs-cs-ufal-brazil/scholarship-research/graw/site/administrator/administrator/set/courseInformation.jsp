<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>

<%@ page errorPage="/status/errorGeneric.jsp" %>

<%
Administrator admin 	= (Administrator) session.getAttribute("admin");
Discipline discipline 	= (Discipline) session.getAttribute("discipline");
if (admin==null || discipline==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE);
	session.invalidate();
}else{ %>

<html>
<head>
<title>Configurar Disciplina</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form action="/servlet/br.ufal.graw.web.administrator.ConfigDiscipline">
  <table width="50%" border="1">
    <tr> 
      <td width="28%">C&oacute;digo:</td>
      <td width="72%">
        <input type="text" name="disciplineCode" value="<%=discipline.getCode()%>">
      </td>
    </tr>
    <tr> 
      <td width="28%">Nome:</td>
      <td width="72%"> 
        <input type="text" name="disciplineName" maxlength="40" size="40" value="<%=discipline.getName()%>">
      </td>
    </tr>
    <tr> 
      <td width="28%">Descri&ccedil;&atilde;o:</td>
      <td width="72%"> 
        <textarea name="disciplineDescription" rows="5" cols="55"><%=discipline.getDescription()%></textarea>
      </td>
    </tr>
    <tr> 
      <td colspan="2"> 
        <div align="center"> 
          <input type="submit" name="Submit" value="Atualizar">
        </div>
      </td>
    </tr>
  </table>
	</form>
</body>
</html>
<%}%>