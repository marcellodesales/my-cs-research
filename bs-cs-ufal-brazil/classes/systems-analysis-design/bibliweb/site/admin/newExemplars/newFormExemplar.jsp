<%@ page import="br.ufal.bibliweb.ExemplarType" %>  
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.AcademicCourse" %> 
<%@ page import="br.ufal.bibliweb.Language" %>
<%@ page import="br.ufal.bibliweb.PhysicalPlace" %> 
<%@ page import="br.ufal.bibliweb.Category" %> 
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>

<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

String exemplarTypeID = request.getParameter("exemplarTypeID");
ExemplarType exemplarType = new ExemplarType(exemplarTypeID,databaseLayer);
session.setAttribute("exemplarType",exemplarType);

AcademicCourse academicCourse = (AcademicCourse)session.getAttribute("academicCourse");

String servletAddress = "";
if (exemplarType.getCode().equals(exemplarType.BOOK_CODE)){
	servletAddress = ServletUtility.SERVLET_DIR + "br.ufal.bibliweb.web.admin.CreateNewBook";
} else 
if (exemplarType.getCode().equals(exemplarType.DISSERTATION_CODE)) { 
	servletAddress = ServletUtility.SERVLET_DIR + "br.ufal.bibliweb.web.admin.CreateNewDissertation";
}
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao BibliWEB! </title>
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" --><!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --><BR>
      <% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
      <BR>
      <form action="<%= servletAddress %>" method="POST">
        <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr> 
            <td id=headerSimplesFont colspan="4">Preencha o formul&aacute;rio 
              dos dados do exemplar<b></b></td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="24">&nbsp;Curso Acadêmico</td>
            <td id=corpoSimples colspan="3" height="24">&nbsp;<%= academicCourse.getDescription() %></td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="24">&nbsp;Tipo</td>
            <td id=corpoSimples colspan="3" height="24">&nbsp;<%= exemplarType.getDescription() %></td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="24">&nbsp;Categoria</td>
            <td id=corpoSimples width="212" height="24"> &nbsp; 
              <select name='categoryID' style="background-color: #FFCC00">
                <%
		Category[] cats = ComponentSet.getCategories(academicCourse.getID(),databaseLayer);
		for (int i=0; i < cats.length; i++){ %>
                <option value='<%= cats[i].getID()%>'><%= cats[i].getDescription() %></option>
                <%		}
%>
              </select>
            </td>
            <td id=corpoSimples width="118" height="24">&nbsp;Idioma</td>
            <td id=corpoSimples width="158" height="24"> &nbsp; 
              <select name='languageID' style="background-color: #FFCC00">
                <%		Language[] languages = ComponentSet.getLanguages(databaseLayer);
		for (int i=0; i < languages.length; i++){ %>
                <option value='<%=languages[i].getID()%>'><%=languages[i].getDescription()%></option>
                <%		}
%>
              </select>
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127">&nbsp;T&iacute;tulo</td>
            <td id=corpoSimples colspan="3">&nbsp; 
              <input type="text" name="title" size="50" maxlength="70">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127">&nbsp;Editora</td>
            <td id=corpoSimples width="212">&nbsp; 
              <input type="text" name="publishingCompany" size="30" maxlength="70">
            </td>
            <td id=corpoSimples width="118">&nbsp;Palavras chaves</td>
            <td id=corpoSimples width="158"> 
              <input type="text" name="keywords" size="25" maxlength="70">
            </td>
          </tr>
          
          <% 		   if (exemplarType.getCode().equals(exemplarType.BOOK_CODE)){ %>
          <tr> 
            <td id=corpoSimples width="127" height="30">&nbsp;ISBN</td>
            <td id=corpoSimples width="212" height="30"> &nbsp; 
              <input type="text" name="ISBN" size="20" maxlength="10">
            </td>
            <td id=corpoSimples width="118" height="30">&nbsp;Volume</td>
            <td id=corpoSimples width="158" height="30"> &nbsp; 
              <input type="text" name="volume" size="10" maxlength="10">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="30">&nbsp;Autores</td>
            <td id=corpoSimples width="212" height="30"> &nbsp; 
              <input type="text" name="authors" size="20" maxlength="150">
            </td>
            <td id=corpoSimples width="118" height="30">&nbsp;Edi&ccedil;&atilde;o</td>
            <td id=corpoSimples width="158" height="30"> &nbsp; 
              <input type="text" name="edition" size="10" maxlength="20">
            </td>
          </tr>
          <% } else if (exemplarType.getCode().equals(exemplarType.DISSERTATION_CODE)) { %>
          <tr> 
            <td id=corpoSimples width="127" height="30">&nbsp;Tipo da disserta&ccedil;&atilde;o</td>
            <td id=corpoSimples width="212" height="30"> &nbsp; 
              <input type="text" name="dissertationKind" size="20" maxlength="20">
            </td>
            <td id=corpoSimples width="118" height="30">&nbsp;Data Publica&ccedil;&atilde;o</td>
            <td id=corpoSimples width="158" height="30"> &nbsp; 
              <input type="text" name="datePublished" size="15" maxlength="10">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="30">&nbsp;Autor</td>
            <td id=corpoSimples colspan="3" height="30"> &nbsp; 
              <input type="text" name="author" size="40" maxlength="150">
            </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="30">&nbsp;Resumo</td>
            <td id=corpoSimples colspan="3" height="30"> &nbsp; 
              <textarea name="abstract" cols="50"></textarea>
            </td>
          </tr>
          <% } // fim if kindExemplar %>
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
      <BR>
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
