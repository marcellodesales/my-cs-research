<%@ page import="br.ufal.graw.web.site.SiteResource" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Department" %>
<%@ page import="br.ufal.graw.Community" %>
<%@ page import="br.ufal.graw.Institute" %>
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufal.graw.UseCase" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page errorPage="status/errorFatal.jsp" %>

<%
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer();
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}
String servletDir = databaseLayer.getConfiguration().getServletDir();
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" -->
<title>Seja Bem-Vindo ao Gr@W!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr>
    <td class="texto_cabecalho" height="0" width="0"><img src="sources/images/gr101.jpg" width="306" height="100"><img src="sources/images/gr102.jpg" width="242" height="100"><img src="sources/images/gr103.jpg" width="222" height="100" usemap="#Map2" border="0"></td>
  </tr>
  <tr class="escuro">
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" --><p align="right"><%= Utility.getTime() %></p><!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top">
    <td height="276"> <!-- #BeginEditable "centro" -->
          <table width="100%" border="0">
            <tr>
              <td width="504" height="0" align="left" valign="top">
                <div align="center">
                  <center>
                    <table cellspacing="0" cellpadding="5" width="100%" border="0" align="center" height="0">
                      <tbody>
                      <tr valign=top>
                        <td width="1"></td>
                        
                    <td bgcolor=#88bbbb width="17">&nbsp;</td>
                        <td colspan=2></td>
                        <td bgcolor=#cc3333 colspan=2 rowspan=2>
                          <p><font color="#FFCFCE" face="Arial, Helvetica, sans-serif" size="2"><b><a class="servicos" href="graw.html">graW
                            Serviços</a></b></font><b><font color="#FFCFCE" face="Arial, Helvetica, sans-serif" size="2"><br>
                            </font></b><font face="Arial, Helvetica, sans-serif" size="2" color="#FFFFFF">Obtenha
                            todos os recursos para estudos, incluíndo links, documentos
                            e refer&ecirc;ncias bibliogr&aacute;ficas oferecidos
                            pelos professores.</font></p>
                        </td>
                        
                    <td bgcolor=#ddaaaa width="2">&nbsp;</td>
                        
                    <td width="67"></td>
                      </tr>
                      <tr valign=top>
                        <td bgcolor=#99cccc width="1">&nbsp;</td>
                        <td bgcolor=#006666 colspan=2 rowspan=2>
                          <p><b><font face="Arial, Helvetica, sans-serif" size="2" color="#8CBABD"><a class="disciplinas" href="graw.html">graW
                            Cursos</a><br>
                        </font></b><font face="Arial, Helvetica, sans-serif" size="2" color="#FFFFFF">Acompanhe
                        <i>on-line</i> todo o conte&uacute;do de disciplinas de
                        sua institui&ccedil;&atilde;o.&nbsp;</font></p>
                        </td>
                        
                    <td width="20">&nbsp;</td>
                        <td colspan=2 rowspan=2>&nbsp;</td>
                      </tr>
                      <tr valign=top>
                        <td width="1"></td>
                        <td class="centro" bgcolor=#254D78 colspan=3 rowspan=3 height="0">
                          
                      <p align="center"><font size="+2" face="Arial, Helvetica, sans-serif" color="#FFFFFF"><b>graW
			Comunidades Acad&ecirc;micas</b></font></p>
                          <p align="center"><font color="#A5C5E2" face="Arial, Helvetica, sans-serif" size="3"><b>Graduação
                            na WEB</b></font></p>
                        </td>
                      </tr>
                      <tr valign=top>
                        <td colspan=2></td>
                        
                    <td bgcolor=#77aaaa width="112">&nbsp;</td>
                        <td bgcolor=#B9D3EC colspan=2 rowspan=2>&nbsp;</td>
                      </tr>
                      <tr valign=top>
                        <td width="1">&nbsp;</td>
                        <td bgcolor=#FFCF63 colspan=2 rowspan=4 height="0">
                          <p align="left"><b><font face="Arial, Helvetica, sans-serif" size="2" color="#BDD3EF"><b><font color="#FF0000"><a class="grupos" href="graw.html">graW
                            Grupos</a></font></b></font><font face="Arial, Helvetica, sans-serif" size="2" color="#FF0000"><br>
                        </font></b><font face="Arial, Helvetica, sans-serif" size="2" color="#000000">Organize
                        seus pr&oacute;prios grupos de estudo.</font></p>
                        </td>
                      </tr>
                      <tr valign=top>
                        <td bgcolor=#FFE3AD width="1" rowspan="3">&nbsp;</td>
                        
                    <td rowspan=3 width="20" height="0">
                      <p>&nbsp;</p>
                        </td>
                        
                    <td width="78" bgcolor=#BDD3EF></td>
                        <td colspan=3 bgcolor=#357CC4 rowspan=3 height="0"><b><font face="Arial, Helvetica, sans-serif" size="2"><a class="ferramentas" href="graw.html">graW
                          Ferramentas</a></font><br>
                          </b><font face="Arial, Helvetica, sans-serif" size="2">Intera&ccedil;&atilde;o
                          dos alunos e professores, atrav&eacute;s de chat, lista
                          de discuss&atilde;o, agenda e f&oacute;rum.</font></td>
                      </tr>
                      <tr valign=top>
                        
                    <td width="78" bgcolor="#BDD3EF">&nbsp;</td>
                      </tr>
                      <tr valign=top>
                        
                    <td width="78" height="10">&nbsp;</td>
                      </tr>
                      </tbody>
                    </table>
<%
UseCase useCase = UseCase.getInstance(databaseLayer);
Community[] community = useCase.getDisciplines();
int quantDisci = community.length;
community = useCase.getGroups();
int quantGroups = community.length;
community = useCase.getCourses();
int quantCourses = community.length;
%>
                    <br>
                <b>Rela&ccedil;&atilde;o das comunidades!!!</b><br>
                <a href="disciplines.jsp">Disciplinas</a>: <%= quantDisci %> <br>
                <a href="courses.jsp">Cursos</a>: <%= quantCourses %> <br>
                <a href="groups.jsp">Grupos de Pesquisa</a>: <%= quantGroups %>
                <br>
                  </center>
                </div>
              </td>
              <td width="266" valign="top" align="right" height="0">
                <form method=post action="<%=servletDir%>br.ufal.graw.web.Login?returnPage=<%=ServletUtility.RETURN_PAGE_GRAW%>" autocomplete=off>
                  <table width="70%" border="0" cellspacing="2" cellpadding="0" align="center" height="0" color="#000000">
                    <tr class="escuro">
                      <td class="texto_cabecalho" height="21" colspan="2">
                        <div align="center">Acesse o graW!</div>
                      </td>
                    </tr>
                    <tr valign="middle" align="center" class="claro">
                      <td colspan="2" height="0">
                        <table width="85%" border="0" cellspacing="3" cellpadding="2" align="center" height="0">
                          <tr>
                            <td><b>Usuário</b></td>
                            <td>
                              
                          <input type="text" name="login" size="20" value="">
                            </td>
                          </tr>
                          <tr>
                            <td><b>Senha</b></td>
                            <td>
                              
                          <input type="password" name="password" maxlength="25" size="20" value="">
                            </td>
                          </tr>
                          <tr>
                            <td colspan="2">
                              <div align="center">
                                <input type="submit" name="Submit" value="Entrar" class="botao">
                                &nbsp; </div>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </form>
            <div align="center"> <a href="../signIn/index.jsp">[ Cadastro ]</a>
              <a href="lostPassword.jsp">[ Esqueci a senha ]</a></div>
                
            <div align="center"> <br>
              <% 	String message = (String)session.getAttribute("message");
			if (message != null){
				out.println("<BR><font color=red><b>"+message+"</font></b>");
				session.removeAttribute("message");
			}
		%>
              <br>
		<BR>
		<a href="http://www.tci.ufal.br/" target="_blank"><img src="http://www.tci.ufal.br/sbie2000/imagens/tci6.jpg" border=0 alt="Departamento de Tecnologia da Informação - Ciência da Computação"></a><BR><BR>
		<a href="http://www.ufal.br/" target="_blank"><img src="http://www.ufal.br/img/logotipoUfal.gif" border=0 alt="Universidade Federal de Alagoas"></a> &nbsp;&nbsp;
		<a href="http://www.cnpq.br/" target="_blank"><img src="sources/images/cnpq.gif" border=0 alt="Conselho Nacional de Desenvolvimento Científico e Tecnológico"></a>
            </div>
            </tr>
          </table>
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
  <area shape="circle" coords="83,87,13" href="signIn/index.jsp">
  <area shape="circle" coords="142,85,14" href="info.jsp">
  <area shape="circle" coords="195,85,12" href="help.html">
</map>
</body>
<!-- #EndTemplate --></html>
