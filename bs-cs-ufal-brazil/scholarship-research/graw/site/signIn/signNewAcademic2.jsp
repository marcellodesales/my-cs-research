<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.Department" %>
<%@ page import="br.ufal.graw.AcademicCourse" %>
<%@ page import="br.ufal.graw.web.site.SiteResource" %>
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
<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao Gr@W!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="0" width="0"><img src="../sources/images/gr101.jpg" width="306" height="100"><img src="../sources/images/gr102.jpg" width="242" height="100"><img src="../sources/images/gr103.jpg" width="222" height="100" usemap="#Map2" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Novo 
      usu&aacute;rio acad&ecirc;mico - <%= institute.getName() %> - <%= department.getName() %><!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> <br>
      Para o departamento <b><%= department.getName() %></b>, da instituição <b><%= institute.getName() %></b>, escolha seu curso acad&ecirc;mico detre aqueles abaixo 
      relacionados e clique em <i>Prosseguir</i>. Caso queira cancelar a opera&ccedil;&atilde;o 
      clique em <i>Cancelar</i>.<br>
      <br>
      Se seu curso acad&ecirc;mico pertencente a essa relação intituição/departamento 
      ainda n&atilde;o estiver cadastrado, mande um email para os administradores 
      em <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a>, ou informe-se 
      na secretaria de seu curso, para maiores esclarecimentos. <br>
      <br>
      <form name="newStudents" action="signNewAcademic3.jsp" method="POST">
        <table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr> 
            <td colspan="4" id=headerSimplesFont>&nbsp;<b>Curso Acad&ecirc;mico 
              de origem</b></td>
          </tr>
          <tr> 
            <td colspan="4" id=corpoSimples>&nbsp;&nbsp; 
              <select name='academicCourseID' style="background-color: #FFCC00">
                <%
		AcademicCourse[] academicc = SiteResource.getAcademicCourses(departmentID,database);
		for (int i=0; i < academicc.length; i++){ %>
                <option value='<%=academicc[i].getID()%>'><%=academicc[i].getName()%></option>
                <%		}
%>
              </select>
            </td>
          </tr>
          <tr> 
            <td colspan="4" id=corpoSimples> 
              <div align="right"> 
                <input type="submit" value="Prosseguir" name="submit" class="bagenda">
                <input type="button" name="cancel" value="Cancelar" class="bagenda" onclick="javascript:history.back()">
                &nbsp;&nbsp;</div>
            </td>
          </tr>
        </table>
      </form>
      <br>
      <a href="../index.jsp">Voltar a p&aacute;gina principal</a> <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o - UFAL</font></div>
    </td>
  </tr>
</table>
<map name="Map2"> 
  <area shape="circle" coords="25,85,12" href="/">
  <area shape="circle" coords="83,87,13" href="index.jsp">
  <area shape="circle" coords="142,85,14" href="../info.jsp">
  <area shape="circle" coords="195,85,12" href="../help.html">
</map>
</body>
<!-- #EndTemplate --></html>
