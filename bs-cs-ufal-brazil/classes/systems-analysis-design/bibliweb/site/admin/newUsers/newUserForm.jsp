<%@ page import="br.ufal.bibliweb.Group" %> 
<%@ page import="br.ufal.bibliweb.AcademicCourse" %> 
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>
<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

Group group = (Group)session.getAttribute("group");
String academicCourseID = request.getParameter("academicCourseID");
boolean isAcademicUser = (academicCourseID != null); //se é para criar um usuário academico
AcademicCourse academicCourse = null;
if (isAcademicUser){
	try{
		academicCourse = new AcademicCourse(academicCourseID,databaseLayer);
		session.setAttribute("academicCourse",academicCourse);
	} catch (Exception e){
		session.setAttribute("message",e.getMessage());
		ServletUtility.sendRedirect(response,"academicUser1.jsp");
	}
}
%>

<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao BibliWEB!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td height="29" colspan="2"> 
      <p><b><img src="../../sources/images/bibliweb.jpg" width="109" height="107" align="middle"><font size="5">Bibliweb 
        - <i>Biblioteca na Web</i></font></b></p>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Formul&aacute;rio 
      de cadastro usu&aacute;rio acad&ecirc;mico<!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> <br>
      Preecha o formul&aacute;rio abaixo com os dados do usu&aacute;rio acad&ecirc;mico. 
      <br>
<% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
      <form action="<%= ServletUtility.SERVLET_DIR %>br.ufal.bibliweb.web.admin.CreateNewUser" method="POST">
        <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr> 
            <td id=headerSimplesFont colspan="4">Preencha o formul&aacute;rio 
              abaixo com os dados do usu&aacute;rio<b></b></td>
          </tr>
          <tr> 
            <td id=corpoSimples width="164" height="24">&nbsp;Grupo de usuário</td>
            <td id=corpoSimples colspan="3" height="24">&nbsp;<%= group.getDescription() %> </td>
          </tr>
          <% if (isAcademicUser){ %>
          <tr> 
            <td id=corpoSimples width="164" height="24">&nbsp;Curso Acadêmico</td>
            <td id=corpoSimples colspan="3" height="24">&nbsp;<%= academicCourse.getDescription() %> </td>
          </tr>
          <% } %>
          <tr> 
            <td id=corpoSimples width="164" height="24">&nbsp;CPF</td>
            <td id=corpoSimples width="232" height="24">&nbsp; 
              <input type="text" name="CPF" size="20" maxlength="11">
            </td>
            <td id=corpoSimples width="83" height="24">&nbsp;Matr&iacute;cula</td>
            <td id=corpoSimples width="136" height="24"> 
              <input type="text" name="registration" size="15" maxlength="13">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="164">&nbsp;Nome Completo</td>
            <td id=corpoSimples colspan="3">&nbsp; 
              <input type="text" name="name" size="40" maxlength="60">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="164" height="30">&nbsp;Endere&ccedil;o 
              Resedencial</td>
            <td id=corpoSimples width="232" height="30">&nbsp; 
              <input type="text" name="homeAddress" size="40" maxlength="70">
            </td>
            <td id=corpoSimples width="83" height="30">&nbsp;Telefone</td>
            <td id=corpoSimples width="136" height="30"> 
              <input type="text" name="homePhone" size="20" maxlength="12">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="164" height="30">&nbsp;Endere&ccedil;o 
              Trabalho</td>
            <td id=corpoSimples width="232" height="30">&nbsp; 
              <input type="text" name="workAddress" size="40" maxlength="70">
            </td>
            <td id=corpoSimples width="83" height="30">&nbsp;Telefone</td>
            <td id=corpoSimples width="136" height="30"> 
              <input type="text" name="workPhone" size="20" maxlength="12">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="164" height="30">&nbsp;Email</td>
            <td id=corpoSimples width="232" height="30">&nbsp; 
              <input type="text" name="email" size="40" maxlength="70">
            </td>
            <td id=corpoSimples width="83" height="30">&nbsp;Celular</td>
            <td id=corpoSimples width="136" height="30"> 
              <input type="text" name="cellPhone" size="20" maxlength="12">
            </td>
          </tr>
          <tr> 
            <td colspan="7" id=corpoSimples> 
              <div align="right"> 
                <input type="submit" value="Prosseguir" name="submit" class="bagenda">
                <input type="button" name="cancel" value="Cancelar" class="bagenda" onClick="javascript:history.back()">
                &nbsp;&nbsp;</div>
            </td>
          </tr>
        </table>
      </form>
      <br>
      <a href="../index.jsp">Voltar a p&aacute;gina principal</a>. <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="11" align="left" valign="top" colspan="2"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o / Banco 
        de Dados II - UFAL</font></div>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
