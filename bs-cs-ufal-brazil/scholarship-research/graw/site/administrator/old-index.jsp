<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%

//DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
DatabaseLayer database = new DatabaseLayer();
session.setAttribute("database",database);
%>
<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW - Página principal</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="21"><img src="../sources/images/teste2.gif" width="770" height="100" usemap="#Map" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->P&aacute;gina principal<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --><br>
	<CENTER><b><font color="red" size="2" face="verdana">
	<% 	String message = (String)session.getAttribute("message");
		if (message != null){ 
			out.println(message);
			session.removeAttribute("message");
		}
	%></font></b>
	</CENTER>
	 <BR>
      <table width="201" border="1" cellpadding="1" cellspacing="0">
        <tr> 
          <td> 
            <div align="center"><b>Acad&ecirc;micos - Inserir Novo(a)</b></div>
          </td>
        </tr>
        <tr> 
          <td><a href="institute/newInstitute.jsp">Istitui&ccedil;&atilde;o</a></td>
        </tr>
        <tr> 
          <td><a href="department/newDepartment.jsp">Departamento</a></td>
        </tr>
        <tr> 
          <td><a href="academicCourse/newAcademicCourse.jsp">Curso Acad&ecirc;mico</a></td>
        </tr>
        <tr> 
          <td><a href="academicCourse/newStudents.jsp">Alunos</a></td>
        </tr>
        <tr>
          <td><a href="newDiscipline.jsp">Disciplinas</a></td>
        </tr>
      </table>
      <br>
      <table width="202" border="1" cellpadding="1" cellspacing="0">
        <tr> 
          <td> 
            <div align="center"><b>Criar novos Cursos</b></div>
          </td>
        </tr>
        <tr> 
          <td><a href="newExtraCourse.jsp">Extracurriculares</a></td>
        </tr>
        <tr> 
          <td><a href="newDepartment.jsp">Grupos de discuss&atilde;o</a></td>
        </tr>
        <tr> 
          <td>&nbsp;</td>
        </tr>
        <tr> 
          <td>&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"><font color="#FFFFFF">® 2002. Todos 
      os direitos reservados - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o 
      - UFAL</font></td>
  </tr>
</table>
<map name="Map"> 
<%// String content = request.getServletContext().getRealPath("/")+"graw";
 //if (!content.equals()) %>
  <area shape="circle" coords="573,86,11" alt="Página principal" title="Informa&ccedil;&otilde;es gerais sobre o GraW" href="/"> 
  <area shape="circle" coords="630,85,11" href="../info.jsp" alt="Informa&ccedil;&otilde;es gerais sobre o GraW" title="Informa&ccedil;&otilde;es gerais sobre o GraW">
  <area shape="circle" coords="690,85,12" href="../signIn/signInNewUser.jsp" alt="Cadastros" title="Cadastros">
  <area shape="circle" coords="742,85,11" href="../help.html" alt="Ajuda" title="Ajuda">
</map>
</body>
<!-- #EndTemplate --></html>
