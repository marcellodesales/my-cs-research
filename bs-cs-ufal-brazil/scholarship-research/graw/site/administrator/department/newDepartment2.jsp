<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
String instituteID = request.getParameter("instituteID");
if (!Institute.exists(instituteID,database)) {
     ServletUtility.sendRedirect(response,"../index.jsp","Código da instituição inválido = "+instituteID);
} 
Institute institute = new Institute(instituteID,database);
%>
<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW - Criação de novo Departamento</title>
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
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Cria&ccedil;&atilde;o de novo Departamento<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --> 
      <p align="center"><br>
        Lembre-se que, ap&oacute;s a cria&ccedil;&atilde;o do novo departamento, 
        o pr&oacute;ximo passo &eacute; associar um novo administrador a esse 
        departamento. Para isso, escolha em &quot;associar administrador&quot;. 
        Abaixo tamb&eacute;m segue a rela&ccedil;&atilde;o de departamentos existenntes 
        nessa institui&ccedil;&atilde;o. Confira para n&atilde;o repetir.<br>
		  </p>
		<center><% 	String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println(message);
				session.removeAttribute("message");
			}
		%></center>
      <form name="newInstitute" action="newDepartment3.jsp" method="POST">
        <table width="520" border="1" align="center" cellpadding="1" cellspacing="0">
          <tr> 
            <td colspan="4"><b>Novo departamento</b></td>
          </tr>
          <tr> 
            <td colspan="4"><b><%= institute.getName() %></b></td>
          </tr>
          <tr> 
            <td width="82"><b>Nome:</b></td>
            <td colspan="3"> 
              <input type="text" name="departmentName" size="70">
            </td>
          </tr>
          <tr> 
            <td width="82"><b>C&oacute;digo:</b></td>
            <td width="80"> 
              <input type="text" name="departmentCode" size="10">
            </td>
            <td width="141"><b>Web Domain:</b></td>
            <td width="210">&nbsp; 
              <input type="text" name="departmentWebDomain" size="40">
            </td>
          </tr>
          <tr> 
            <td colspan="4"> 
              <div align="right"> 
                <input type="submit" value="Criar Nova!">
                <input type="button" name="cancel" value="Cancelar">
                <input type="hidden" name="instituteID" value="<%= institute.getID() %>">
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
