<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<%@ page import="br.ufal.graw.web.site.SiteResource" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="br.ufal.graw.CommunitySpecialInvitation"%>
<% 
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (database == null){
database = new DatabaseLayer();
session.setAttribute("database",database);
}
String name = (String)session.getAttribute("name");
String login = (String)session.getAttribute("login");
String email = (String)session.getAttribute("email");
name = (name==null) ? "" : name;
login = (login==null) ? "" : login;
email = (email==null) ? "" : email;
%>


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
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Cadastro 
      de novo usu&aacute;rio<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> 
      <% 	String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
		%>
      <br>
      <table width="95%" border="0" align="center">
        <tr> 
          <td width="48%" valign="top">Este formul&aacute;rio &eacute; destinado 
            a usu&aacute;rios da Internet em geral, como participantes convidados 
            por usu&aacute;rios acad&ecirc;micos. Portanto, voc&ecirc; ter&aacute; 
            participa&ccedil;&atilde;o distinta dos outros usu&aacute;rios acad&ecirc;micos. 
            Voc&ecirc; s&oacute; porder&aacute; se associar em comunidades mediante 
            um convite de seus administradores. Contudo, voc&ecirc; ter&aacute; 
            acesso livre a todas as comunidades acad&ecirc;micas p&uacute;blicas 
            no ambiente, como cursos extracurriculares ou grupos de pesquisa.<br>
            <br>
            Caso precise de alguma descri&ccedil;&atilde;o do ambiente, acesse 
            a <a href="../help.html">ajuda 
            online</a>. <br>
            <br>
            As intru&ccedil;&otilde;es de acesso e sua senha ao ambiente ser&atilde;o 
            enviadas para seu email ap&oacute;s o cadastro. Qualquer d&uacute;vida 
            contate os administradores atrav&eacute;s do email <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a>.<br>
            <br>
            <a href="../index.jsp">Voltar 
            a p&aacute;gina principal</a>.</td>
          <td width="52%"> 
            <form action="<%= database.getConfiguration().getServletDir()%>br.ufal.graw.web.SignInNewUser" method="POST">
              <table width="95%" border="0" align=right>
	      	<%
		String communityGuestsID = request.getParameter("communityGuestsID");
		
		if (communityGuestsID != null ){
			CommunitySpecialInvitation communitSpecialInvitation = new CommunitySpecialInvitation(communityGuestsID,database);
			session.setAttribute("invitation",communitSpecialInvitation); %>
			<tr> 
        	          <td width="21%" id=headerSimplesFont> &nbsp;Comunidade </td>
	                </tr>
			<tr> 
	                  <td width="21%" id=corpoSimples> &nbsp;<%=communitSpecialInvitation.getCommunity().getTitle()%> </td>
	                </tr>
			<tr> 
        	          <td width="21%" id=headerSimplesFont> &nbsp;Convite enviado por: </td>
	                </tr>
			<tr> 
	                  <td width="21%" id=corpoSimples> &nbsp;<%=communitSpecialInvitation.getInviter().getName()%> </td>
	                </tr>
			
			
				<%
		}
		%>	      		      
                <tr> 
                  <td width="21%" id=headerSimplesFont> &nbsp;Nome </td>
                </tr>
                <tr> 
                  <td width="79%" id=corpoSimples>&nbsp;&nbsp; 
                    <input type="text" name="name" size="30" value="<%= name %>">
                    <br>
                    * Seu nome completo. </td>
                </tr>
                <tr> 
                  <td width="21%" id=headerSimplesFont>&nbsp;Login</td>
                </tr>
                <tr> 
                  <td width="79%" id=corpoSimples> &nbsp;&nbsp; 
                    <input type="text" name="login" size="20" value="<%= login %>">
                    <br>
                    * Escolha um login. <br>
                    ** Utilize somente letras, n&uacute;meros e sublinhado.</td>
                </tr>
                <tr> 
                  <td width="21%" id=headerSimplesFont> &nbsp;Email </td>
                </tr>
                <tr> 
                  <td width="79%" id=corpoSimples> &nbsp;&nbsp; 
                    <input type="text" name="email" maxlength="50" size="30" value="<%= email %>">
                    <br>
                    * Seu endere&ccedil;o de email.</td>
                </tr>
                <tr> 
                  <td colspan="2" id=corpoSimples> 
                    <div align="right"> 
                      <input type="submit" name="Submit" value="Cadastrar" class="bagenda">
                      <input type="reset" name="Submit2" value="Apagar" class="bagenda">
                      &nbsp;&nbsp;</div>
                  </td>
                </tr>
              </table>
            </form>
          </td>
        </tr>
      </table>
<% //remover informações desnecessária, caso usuário desita de efetuar cadastro
session.removeAttribute("name");
session.removeAttribute("login");
session.removeAttribute("email");
%>	  
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
