 
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.ExemplarCopy" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
String exemplarCopyID = request.getParameter("copyID");
ExemplarCopy exemplarCopy = new ExemplarCopy(exemplarCopyID,databaseLayer);

%>
<html>
<!-- #BeginTemplate "/Templates/default.dwt" --> 
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->C&oacute;pia 
      de Exemplar<!-- #EndEditable --></b></font></td>
    <td height="21" width="108"> 
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> <BR>
      <% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
      <BR>
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="4">Preencha o formul&aacute;rio dos 
            dados da C&oacute;pia do exemplar<b></b></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127">&nbsp;C&oacute;digo</td>
          <td id=corpoSimples colspan="3">&nbsp;<%= exemplarCopy.getID() %> </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127">&nbsp;Exemplar</td>
          <td id=corpoSimples colspan="3">&nbsp;<a href="../exemplars/exemplar.jsp?exemplarID=<%= exemplarCopy.getAbstractExemplar().getID() %>"><%= exemplarCopy.getAbstractExemplar().getTitle() %></a></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127" height="24">&nbsp;Local F&iacute;sico</td>
          <td id=corpoSimples width="176" height="24">&nbsp;<%= exemplarCopy.getPhysicalPlace().getDescription() %> 
          </td>
          <td id=corpoSimples width="154" height="24">&nbsp;Data Aquisi&ccedil;&atilde;o</td>
          <td id=corpoSimples width="158" height="24">&nbsp;<%= exemplarCopy.getAcquisitionDate() %> 
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="127">&nbsp;Status</td>
          <td id=corpoSimples colspan="3">&nbsp;<%= exemplarCopy.getStatus().getDescription() %> 
          </td>
        </tr>
      </table>
      <!-- #EndEditable --> </td>
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
<!-- #EndTemplate -->
</html>
