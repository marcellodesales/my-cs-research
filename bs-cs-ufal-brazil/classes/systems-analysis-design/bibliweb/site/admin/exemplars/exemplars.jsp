<%@ page import="br.ufal.bibliweb.Book" %>
<%@ page import="br.ufal.bibliweb.Exemplar" %>
<%@ page import="br.ufal.bibliweb.ExemplarType" %>
<%@ page import="br.ufal.bibliweb.AbstractExemplar" %> 
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

String exemplarID = request.getParameter("exemplarID");
Exemplar exemplar = AbstractExemplar.getRealExemplar(exemplarID,databaseLayer);

%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Visualização de Exemplar</title>
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Exemplar 
      - <%= exemplar.getTitle() %><!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --><br>
      <% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
      <br>
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="4"> <a href="../newexemplarcopies/newFormExemplarCopy.jsp?exemplarID=<%= exemplar.getID() %>">Novas 
            C&oacute;pias</a></td>
        </tr>
        <tr>
          <td id=headerSimplesFont colspan="4"><a href="../exemplarcopies/listCopies.jsp?exemplarID=<%= exemplar.getID() %>">Total 
            de C&oacute;pias</a>: <%= exemplar.getTotalQuantity() %> </td>
        </tr>
      </table>
      <BR>	  
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="4">Informa&ccedil;&otilde;es de Exemplar<b></b></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="24"> 
            <div align="right"><strong>&nbsp;Curso Acadêmico</strong></div>
          </td>
          <td id=corpoSimples colspan="3" height="24">&nbsp; <%= exemplar.getAcademicCourse().getDescription() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="24"> 
            <div align="right"><strong>&nbsp;Tipo</strong></div>
          </td>
          <td id=corpoSimples colspan="3" height="24">&nbsp; <%= exemplar.getExemplarType().getDescription() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127"> 
            <div align="right"><strong>&nbsp;T&iacute;tulo</strong></div>
          </td>
          <td id=corpoSimples colspan="3">&nbsp; <%= exemplar.getTitle() %> </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127"> 
            <div align="right"><strong>&nbsp;Editora</strong></div>
          </td>
          <td id=corpoSimples colspan="3">&nbsp; <%= exemplar.getPublishingCompany() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="24"> 
            <div align="right"><strong>&nbsp;Categoria</strong></div>
          </td>
          <td id=corpoSimples width="256" height="24">&nbsp; <%= exemplar.getCategory().getDescription() %> 
          </td>
          <td id=corpoSimples width="96" height="24"> 
            <div align="right"><strong>&nbsp;Idioma</strong></div>
          </td>
          <td id=corpoSimples width="136" height="24"> &nbsp; <%= exemplar.getLanguage().getDescription() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="30"> 
            <div align="right"><strong>&nbsp;Palavras chaves</strong></div>
          </td>
          <td id=corpoSimples colspan="3" height="30">&nbsp; <%= exemplar.getKeywords() %> 
          </td>
        </tr>
        <% 		 if (exemplar.getExemplarType().getCode().equals(ExemplarType.BOOK_CODE)){ %>
        <tr> 
          <td id=corpoSimples width="127" height="30"> 
            <div align="right"><strong>&nbsp;ISBN</strong></div>
          </td>
          <td id=corpoSimples width="256" height="30"> &nbsp; <%= ((Book)exemplar).getISBN() %> 
          </td>
          <td id=corpoSimples width="96" height="30"> 
            <div align="right"><strong>&nbsp;Volume</strong></div>
          </td>
          <td id=corpoSimples width="136" height="30"> &nbsp; <%= ((Book)exemplar).getVolume() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="30"> 
            <div align="right"><strong>&nbsp;Autores</strong></div>
          </td>
          <td id=corpoSimples width="256" height="30"> &nbsp; <%= ((Book)exemplar).getAuthors() %> 
          </td>
          <td id=corpoSimples width="96" height="30"> 
            <div align="right"><strong>&nbsp;Edi&ccedil;&atilde;o</strong></div>
          </td>
          <td id=corpoSimples width="136" height="30"> &nbsp; <%= ((Book)exemplar).getEdition() %> 
          </td>
        </tr>
        <% }// else if (exemplarType.getCode().equals(exemplarType.DISSERTATION_CODE)) { %>
        <!--        <tr> 
          <td id=corpoSimples width="127" height="30">&nbsp;Tipo da disserta&ccedil;&atilde;o</td>
          <td id=corpoSimples width="256" height="30"> &nbsp; 
            <%= exemplar.getKeywords() %>
          </td>
          <td id=corpoSimples width="96" height="30">&nbsp;Data Publica&ccedil;&atilde;o</td>
          <td id=corpoSimples width="136" height="30"> &nbsp; 
            <%= exemplar.getKeywords() %>
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="30">&nbsp;Autor</td>
          <td id=corpoSimples colspan="3" height="30"> &nbsp; 
            <%= exemplar.getKeywords() %>
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="30">&nbsp;Resumo</td>
          <td id=corpoSimples colspan="3" height="30"> &nbsp; 
            <%= exemplar.getKeywords() %>
          </td>
        </tr>
        <% //} // fim if kindExemplar %>
        <tr> 
          <td colspan="7" id=corpoSimples> 
            <div align="right"> 
              <input type="submit" value="Prosseguir" name="submit" class="bagenda">
              <input type="button" name="cancel" value="Cancelar" class="bagenda" onClick="javascript:history.back()">
              &nbsp;&nbsp;</div>
          </td>
        </tr>
		-->
      </table>
	  <center>
        <img src='http://localhost:8080/bibliweb/servlet/br.ufal.bibliweb.barcode.linear.UserBarCode?BARCODE=<%= exemplar.getID() %>&BAR_HEIGHT=1&CODE_TYPE=CODE128&X=0.05&CHECK_CHAR=Y&CHECK_CHARINTEXT=Y&CODE128_SET=0&HEIGHT=75&WIDTH=210'> 
        <br>
        <br>
        <a href="listExemplars.jsp">Voltar </a> 
      </center>
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
