<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.Lend" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>

<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
String lendID = request.getParameter("lendID");
Lend lend = null;
try{
	lend = new Lend(lendID,databaseLayer);
	
} catch (Exception e){
	session.setAttribute("message",e.getMessage());
	ServletUtility.sendRedirect(response,"index.jsp");
}
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" -->
<title>Seja Bem-Vindo ao BibliWEB - Empr&eacute;stimo de Exemplar</title>
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
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Empr&eacute;stimo 
      de Exemplar<!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> <BR>
      <BR>
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="5">Empr&eacute;stimo <b><%= lend.getID() %></b></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="136" height="24"> 
            <div align="right"><b>Sa&iacute;da:</b></div></td>
          <td width="159" height="24" id=corpoSimples>&nbsp;<%= lend.getLendDate() %> 
          </td>
          <td width="135" id=corpoSimples>
<div align="right"> 
              <%
		String arrival = (!lend.getWasReturned()) ? "Chegada Prevista" : "Chegada";
		String arrivalDate = (!lend.getWasReturned()) ? lend.getDevolutionDate() : lend.getReturnedDate();
		  %>
              <b><%= arrival %>:</b></div></td>
          <td width="185" height="24" id=corpoSimples>&nbsp;<%= arrivalDate %></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="136" height="24"> 
            <div align="right"><b> C&oacute;pia Exemplar: </b></div>
          </td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp;<a href="../exemplarcopies/exemplarcopy.jsp?copyID=<%= lend.getExemplarCopy().getID() %>"><%= lend.getExemplarCopy().getID() %></a></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="136" height="24"> 
            <div align="right"><b> 
              Exemplar: </b></div></td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp;<a href="../exemplars/exemplar.jsp?exemplarID=<%= lend.getExemplarCopy().getAbstractExemplar().getID() %>"><%= lend.getExemplarCopy().getAbstractExemplar().getTitle() %></a></td>
        </tr>		
        <tr> 
          <td id=corpoSimples height="24" width="136">
<div align="right">&nbsp;<b>Usu&aacute;rio:</b></div></td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp;<a href="../users/user.jsp?userID=<%= lend.getRenter().getID() %>"><%= lend.getRenter().getName() %></a></td>
        </tr>
        <tr> 
          <td id=corpoSimples height="24" width="136">
<div align="right"><strong>Balconista:</strong></div></td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp;<%= lend.getClerk().getName() %></td>
        </tr>
        <tr> 
          <td id=corpoSimples height="24" width="136">
<div align="right"><strong>Houve Renova&ccedil;&atilde;o?</strong></div></td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp;<% 
		   String renewal = (lend.getRenewalQuant() == 0) ? "Não Houve Renovação" : "Houveram "+lend.getRenewalQuant()+" renovações!";
		   out.println(renewal);
		  %>
          </td>
        </tr>
        <tr> 
          <td id=corpoSimples height="24" width="136">
<div align="right"><strong>Situa&ccedil;&atilde;o:</strong></div></td>
          <td height="24" colspan="3" id=corpoSimples>&nbsp; 
            <%
		  	String devolvido = lend.getWasReturned() ? "Devolvido" : "Com o usuário";
			String devolver = lend.getWasReturned() ? "" : " - <a href='returnLend.jsp?lendID="+lend.getID()+"'>Devolver Agora!</a>";
		  %>
		  <%= devolvido %> <%= devolver %>
         </td>
        </tr>
      </table>
      <div align="center"><br>
        <br>
        <a href="index.jsp">Voltar aos Empr&eacute;stimos</a></div>
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
<!-- #EndTemplate --></html>
