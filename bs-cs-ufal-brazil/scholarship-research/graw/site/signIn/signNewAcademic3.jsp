<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.AcademicCourse" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%
String academicCourseID = request.getParameter("academicCourseID");
if (academicCourseID == null)
	academicCourseID = (String)session.getAttribute("academicCourseID");
session.setAttribute("academicCourseID",academicCourseID);
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (!AcademicCourse.exists(academicCourseID,database)){
	 String message = "Código da curso acadêmico inválido = "+academicCourseID;
	 session.setAttribute("message",message);
     ServletUtility.sendRedirect(response,"../index.jsp");
} 
AcademicCourse academicCourse = new AcademicCourse(academicCourseID,database);
String nameac = (String)session.getAttribute("nameac");
String matriculation = (String)session.getAttribute("matriculation");
String login = (String)session.getAttribute("login");
nameac = (nameac == null) ? "" : nameac;
matriculation = (matriculation == null) ? "" : matriculation;
login = (login == null) ? "" : login;
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
      usu&aacute;rio acad&ecirc;mico - <%= academicCourse.getInstitute().getName() %> - <%= academicCourse.getDepartment().getName() %> - <%= academicCourse.getName() %><!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> 
      <div align="center"><BR>
        <% 	String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<font color=red><b>"+message+"</font></b>");
				session.removeAttribute("message");
			}
		%>
        <BR>
        <table width="688" border="0">
          <tr>
            <td width="414"> 
              <form action="<%= database.getConfiguration().getServletDir()%>br.ufal.graw.web.SignInNewAcademicUser" method="POST">
        <table width="361" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr> 
            <td id=headerSimplesFont colspan="2">&nbsp;<b>Novo usu&aacute;rio acad&ecirc;mico</b></td>
          </tr>
          <tr> 
            <td id=corpoSimples colspan="2">&nbsp;<b><%= academicCourse.getInstitute().getName() %></b></td>
          </tr>
          <tr> 
            <td id=corpoSimples colspan="2" height="14">&nbsp;<b><%= academicCourse.getDepartment().getName() %></b></td>
          </tr>
          <tr> 
            <td id=corpoSimples colspan="2">&nbsp;<b><%= academicCourse.getName() %></b></td>
          </tr>
          <tr> 
            <td id=headerSimplesFont width="116">&nbsp;Nome</td>
		  </tr>
          <tr>  
			<td id=corpoSimples width="238"> 
              &nbsp;&nbsp;<input type="text" name="nameac" size="30" value="<%= nameac %>">
            </td>
          </tr>
          <tr> 
            <td id=headerSimplesFont width="116">&nbsp;Matr&iacute;cula</td>
          </tr>            
          <tr>		  
			<td id=corpoSimples width="238"> 
              &nbsp;&nbsp;<input type="text" name="matriculation" size="15" value="<%= matriculation %>">
            </td>
          </tr>
          <tr> 
            <td id=headerSimplesFont width="116">&nbsp;Nome usu&aacute;rio</td>
          </tr>
          <tr>		  
            <td id=corpoSimples width="238"> 
              &nbsp;&nbsp;<input type="text" name="login" size="20" maxlength="15" value="<%= login %>">
            </td>
          </tr>
          <tr> 
            <td id=headerSimplesFont colspan="2"> 
              <div align="right">
                        <input type='submit' name='button' value='Efetuar Cadastro' class="bagenda">
                        <input type='button' value='Cancelar' class="bagenda" onclick="javascript:history.back()">
                        &nbsp;&nbsp;</div>
            </td>
          </tr>
        </table>
      </form>
			
			  <br>
              <a href="../index.jsp">Voltar a p&aacute;gina principal</a> </td>
            <td width="264" valign="top"> 
              <p>Para concluir o cadastro, preencha o formul&aacute;rio ao lado 
                com suas informa&ccedil;&otilde;es e clique em <i>Efetuar Cadastro</i>. 
                Caso tenha algum problema ou d&uacute;vida, mande um email para 
                os administradores em <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a>.<br>
                <br>
                <b>Nome:</b> Seu nome completo;</p>
              <p><b>Matr&iacute;cula:</b> Sua matr&iacute;cula na institui&ccedil;&atilde;o 
                escolhida, sem c&oacute;digo verificador, se existir;<br>
                <i>2002G55D000V-9</i> como <i>2002G55D000V</i>.<br>
                <br>
                <b>Nome Usu&aacute;rio:</b> Um nome de usu&aacute;rio de sua escolha. 
                Dever&aacute; conter no m&aacute;ximo 15 caracteres. Lembre-se 
                que difere letras mai&uacute;sculas e min&uacute;sculas.<br>
                <br>
                Suas informa&ccedil;&otilde;es de acesso ao ambiente <b>ser&atilde;o</b> 
                <b>enviadas para seu email do curso acad&ecirc;mico</b>, juntamente 
                com sua senha provis&oacute;ria, que poder&aacute; ser mudada 
                no pr&oacute;prio ambiente, ap&oacute;s a conclus&atilde;o do 
                cadastro.</p>
            </td>
          </tr>
        </table>
      </div>
      <!-- #EndEditable --> 
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
