<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.User" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>

<%
Administrator admin 	= (Administrator) session.getAttribute("admin");
User user 	= (User) session.getAttribute("user");
if (admin==null || user==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Sua sessão expirou, ou vc nao escolheu nenhum usuário ");
	session.invalidate();
}else{ %>

<html>
<head>
<title>Configurar Usuário</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form action="/servlet/br.ufal.graw.web.administrator.ConfigUser">
  <table  border="0">
    <tr> 
      <td> 
        <table width="50%" >
          <tr> 
            <td width="28%">Matricula:</td>
            <td width="72%"> 
              <input type="text" name="userLogin" value="<%=user.getLogin()%>">
            </td>
          </tr>
          <tr> 
            <td width="28%">Nome:</td>
            <td width="72%"> 
              <input type="text" name="userName" maxlength="40" size="40" value="<%=user.getName()%>">
            </td>
          </tr>
          <tr> 
            <td width="28%">Email::</td>
            <td width="72	%"> 
              <input type="text" name="userEmail" size="55" value="<%=user.getEmail()%>">
            </td>
          </tr>
          <tr> 
            <td width="28%">Senha:</td>
            <td width="72	%"> 
              <input type="text" name="userPassword" size="55" value="<%=user.getPassword()%>">
            </td>
          </tr>
          <tr> 
            <td colspan="2">
              <%if (user instanceof Professor){%>
              <b>Disciplinas Lecionadas:</b><br>
              <%
			Utility utility = new Utility((DatabaseLayer)session.getAttribute("database"));
	  		Discipline disciplines[] = utility.getAllDisciplines();
			for (int i=0 ; i< disciplines.length ; i++){ %>
              <input type="checkbox" name="<%=disciplines[i].getID()%>" value="DISCIPLINE"
				<%if ( ((Professor)user).teaches(disciplines[i]) ){ %>			
				  checked>
              <%}else{%>
              > 
              <%}%>
              <%=disciplines[i].getName()%> <br>
              <%}	  	
		  }%>
            </td>
          </tr>
        </table>
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