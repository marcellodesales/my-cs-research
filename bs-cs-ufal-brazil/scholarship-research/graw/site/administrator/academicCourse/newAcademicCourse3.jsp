<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.Department" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
String departmentID = request.getParameter("departmentID");
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (!Department.exists(departmentID,database)){
	 String message = "Código da departamento inválido = "+departmentID;
	 session.setAttribute("message",message);
     ServletUtility.sendRedirect(response,"../index.jsp");
} 
Department department = new Department(departmentID,database);
Institute institute = department.getInstitute();
%>
<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="21"><img src="../../sources/images/teste2.gif" width="770" height="100" usemap="#Map" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Cria&ccedil;&atilde;o de novo Curso Acad&ecirc;mico<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --><br>
      Preencha o formul&aacute;rio abaixo seguindo as instru&ccedil;&otilde;es. 
      Ap&oacute;s a cria&ccedil;&atilde;o do Curso, &eacute; necess&aacute;rio 
      pr&eacute;-cadastrar todos os alunos desse curso acad&ecirc;mico.<br>
      <br>
      <form name="newInstitute" action="newAcademicCourse4.jsp" method="POST">
        <table width="520" border="1" align="center" cellpadding="1" cellspacing="0">
          <tr> 
            <td colspan="2"><b>Novo Curso Acad&ecirc;mico</b></td>
          </tr>
          <tr> 
            <td colspan="2"><b><%= institute.getName() %></b></td>
          </tr>
          <tr> 
            <td colspan="2"><b><%= department.getName() %></b></td>
          </tr>
          <tr> 
            <td width="82"><b>Nome:</b></td>
            <td> 
              <input type="text" name="academicCourseName" size="70">
            </td>
          </tr>
          <tr> 
            <td width="82"><b>C&oacute;digo:</b></td>
            <td> 
              <input type="text" name="academicCourseCode" size="10">
            </td>
          </tr>
          <tr> 
            <td colspan="2"> 
              <div align="right"> 
                <input type="submit" value="Criar Novo!">
                <input type="button" name="cancel" value="Cancelar">
                <input type="hidden" name="departmentID" value="<%= departmentID %>">
              </div>
            </td>
          </tr>
        </table>
      </form>
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
  <area shape="circle" coords="630,85,11" href="../../info.jsp" alt="Informa&ccedil;&otilde;es gerais sobre o GraW" title="Informa&ccedil;&otilde;es gerais sobre o GraW">
  <area shape="circle" coords="690,85,12" href="../../signIn/signInNewUser.jsp" alt="Cadastros" title="Cadastros">
  <area shape="circle" coords="742,85,11" href="../../help.html" alt="Ajuda" title="Ajuda">
</map>
</body>
<!-- #EndTemplate --></html>
