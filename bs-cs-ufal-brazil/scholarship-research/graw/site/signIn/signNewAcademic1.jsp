<%@ page import="br.ufal.graw.web.site.SiteResource" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.Department" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
String instituteID = request.getParameter("instituteID");
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (!Institute.exists(instituteID,database)){
	 String message = "Código da instituição inválido = "+instituteID;
	 session.setAttribute("message",message);
     ServletUtility.sendRedirect(response,"../index.jsp");
} 
Institute institute = new Institute(instituteID,database);
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
      usu&aacute;rio acad&ecirc;mico - <%= institute.getName() %><!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> <br>
      Para a instituição <b><%= institute.getName() %></b> escolhida, escolha seu departamento detre aqueles abaixo 
      relacionados e clique em <i>Prosseguir</i>. Caso queira cancelar a opera&ccedil;&atilde;o 
      clique em <i>Cancelar</i>.<br>
      <br>
      Se seu departamento dessa institui&ccedil;&atilde;o ainda n&atilde;o estiver 
      cadastrado, mande um email para os administradores <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a>, 
      ou informe-se no seu departamento, para maiores esclarecimentos. <br>
      <br>
      <form action="signNewAcademic2.jsp" method="POST">
        <table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr> 
            <td id=headerSimplesFont>&nbsp;<b>Departamento de origem</b></td>
          </tr>
          <tr> 
            <td id=corpoSimples>&nbsp;&nbsp; 
              <select name='departmentID' style="background-color: #FFCC00">
                <%
		Department[] departments = SiteResource.getDepartments(institute.getID(),database);
		for (int i=0; i < departments.length; i++){ %>
                <option value='<%=departments[i].getID()%>'><%=departments[i].getName()%></option>
                <%		}
%>
              </select>
              &nbsp;&nbsp; </td>
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
      <a href="../index.jsp">Voltar a p&aacute;gina principal</a>. <!-- #EndEditable --> 
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
