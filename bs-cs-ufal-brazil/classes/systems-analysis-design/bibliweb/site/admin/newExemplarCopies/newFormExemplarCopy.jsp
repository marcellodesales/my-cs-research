<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.Exemplar" %>
<%@ page import="br.ufal.bibliweb.Utility" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.AbstractExemplar" %>
<%@ page import="br.ufal.bibliweb.PhysicalPlace" %> 
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>
<%@ page import="java.sql.Date" %>

<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

String exemplarID = request.getParameter("exemplarID");
Exemplar exemplar = AbstractExemplar.getRealExemplar(exemplarID,databaseLayer);

String servletAddress = ServletUtility.SERVLET_DIR + "br.ufal.bibliweb.web.admin.CreateNewExemplarCopy";

Date today = new Date(new Long(Utility.getNewOID()).longValue());
String dateParts[] = Utility.getDateParts(today.toString(),"-");
String dateToday = dateParts[2] +"/"+ dateParts[1] +"/"+ dateParts[0]; 

%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao BibliWEB! - Cadastro de C&oacute;pias de Exemplares</title>
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
              dos dados da C&oacute;pia do exemplar<b></b></td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127">&nbsp;Exemplar</td>
            <td id=corpoSimples colspan="3">&nbsp;<%= exemplar.getTitle() %> </td>
          </tr>
          <tr> 
            <td id=corpoSimples width="127" height="24">&nbsp;Local F&iacute;sico</td>
            <td id=corpoSimples width="176" height="24"> &nbsp; 
              <select name='physicalPlaceID' style="background-color: #FFCC00">
                <%
		PhysicalPlace[] physicalPlaces = ComponentSet.getPhysicalPlaces(databaseLayer);
		for (int i=0; i < physicalPlaces.length; i++){ %>
                <option value='<%= physicalPlaces[i].getID() %>'><%= physicalPlaces[i].getDescription() %></option>
                <%		}
%>
              </select>
            </td>
            <td id=corpoSimples width="154" height="24">&nbsp;Data Aquisi&ccedil;&atilde;o</td>
            <td id=corpoSimples width="158" height="24"> &nbsp; 
              <input type="text" name="acquisitionDate" size="15" maxlength="10" value="<%= dateToday %>">
            </td>
          </tr>
          <tr> 
            <td colspan="7" id=corpoSimples> 
              <div align="right"> 
			  	<input type="hidden" name="exemplarID" value="<%= exemplar.getID() %>">
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
