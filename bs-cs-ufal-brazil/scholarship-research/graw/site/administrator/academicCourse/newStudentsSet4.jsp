<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.AcademicCourse" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
String academicCourseID = request.getParameter("academicCourseID");
session.setAttribute("academicCourseID",academicCourseID);
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (!AcademicCourse.exists(academicCourseID,database)){
	 String message = "Código da curso acadêmico inválido = "+academicCourseID;
	 session.setAttribute("message",message);
     ServletUtility.sendRedirect(response,"../index.jsp");
} 
AcademicCourse academicCourse = new AcademicCourse(academicCourseID,database);
%>
<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW - Novos Alunos</title>
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
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Novos Alunos<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --> 
      <p><br>
        Escolha a rela&ccedil;&atilde;o dos alunos no formato TXT; Cada linha 
        deve ser dividida em tr&ecirc;s partes:</p>
      <p>-------------&gt; Nome de usu&aacute;rio, Nome completo, Matr&iacute;cula<br>
        <br>
        * Nome de usu&aacute;rio: &eacute; o nome de usu&aacute;rio usado nos 
        servidores de webmail, criado pelo administrador do departamento;<br>
        * Nome completo: o nome completo do aluno;<br>
        * Matr&iacute;cula: &Eacute; a matr&iacute;cula do aluno na institui&ccedil;&atilde;o.<br>
      </p>
      <form ENCTYPE='multipart/form-data' ACTION="/servlet/br.ufal.graw.web.administrator.UploadStudents"  METHOD='post'>
        <table width="361" border="1" align="center" cellpadding="1" cellspacing="0">
          <tr> 
            <td colspan="2"><b>Rela&ccedil;&atilde;o dos Estudantes</b></td>
          </tr>
          <tr> 
            <td colspan="2"><b><%= academicCourse.getInstitute().getName() %></b></td>
          </tr>
          <tr> 
            <td colspan="2" height="14"><b><%= academicCourse.getDepartment().getName() %></b></td>
          </tr>
          <tr> 
            <td colspan="2"><b><%= academicCourse.getName() %></b></td>
          </tr>	  
          <tr> 
            <td width="257"> 
              <input type='file' name='studentFile' accept='text/html'>
            </td>
            <td width="94"> 
              <input type='submit' name='button' value='Submeter'>
            </td>
          </tr>
        </table>
      </form>
      <p> <br>
        <br>
      </p>
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
