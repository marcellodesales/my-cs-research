<%@ page import="br.ufal.bibliweb.AcademicCourse" %> 
<%@ page import="br.ufal.bibliweb.Exemplar" %> 
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<% 
String academicCourseID = request.getParameter("academicCourseID");
if (academicCourseID == null){
	System.out.println("Curso acadêmico inválido!");
} 

DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
AcademicCourse academicCourse = new AcademicCourse(academicCourseID,databaseLayer);
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Rela&ccedil;&atilde;o de usu&aacute;rios do grupo <%= academicCourse.getDescription() %></title>
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Exemplares 
      do curso <%= academicCourse.getDescription() %><!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> <BR>
      <BR>
      <table width="100%" border="0">
        <tr>
          <td width="86%"> 
            <table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
              <tr> 
                <td id=headerSimplesFont>Lista de exemplares do curso <%= academicCourse.getDescription() %></td>
              </tr>
              <%
		Exemplar[] exemplars = ComponentSet.getBooks(academicCourseID,databaseLayer);
	if (exemplars.length > 0){
		for (int i=0; i < exemplars.length; i++){ %>
              <tr> 
                <td id=corpoSimples>&nbsp;&nbsp; <a href="exemplar.jsp?exemplarID=<%= exemplars[i].getID() %>"><%= exemplars[i].getID()%> 
                  - <%= exemplars[i].getTitle()%></a></td>
              </tr>
              <%		} 
 	} else {     %>
              <tr> 
                <td> 
                  <div align="center"><font color="red">&nbsp;&nbsp;Ainda não 
                    existem exemplares para o curso de <%= academicCourse.getDescription() %>!</font></div>
                </td>
              </tr>
              <%		}      %>
              <tr> 
                <td id=headerSimplesFont>&nbsp;</td>
              </tr>
            </table>
            <div align="center"><br>
              <br>
              <a href="javascript:history.back()">Voltar</a> </div>
          </td>
          <td width="14%"> 
            <div align="center"><img src="../../sources/images/bookslist.jpg" width="136" height="170"><br>
              <br>
            </div>
          </td>
        </tr>
      </table>
      <!-- #EndEditable --> 
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
