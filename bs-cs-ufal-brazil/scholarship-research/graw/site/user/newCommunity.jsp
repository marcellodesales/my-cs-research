<HTML><!-- #BeginTemplate "/Templates/user.dwt" -->
<%@ page import="br.ufal.graw.User" %>
<%@ page import="br.ufal.graw.ExternUser" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.Community" %>
<%@ page import="br.ufal.graw.Course" %>
<%@ page import="br.ufal.graw.Group" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page import="br.ufal.graw.Utility" %>
<%@ page errorPage="../status/errorFatal.jsp" %>
<% 
User user = (User)session.getAttribute("user");
Community community = (Community)session.getAttribute("community");
DatabaseLayer database = (DatabaseLayer) session.getAttribute("database");
String servletDir = database.getConfiguration().getServletDir();
if (user == null) {
	session.invalidate();
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sessão expirou.");
}else{

%>

<HEAD>
<!-- #BeginEditable "doctitle" --> 
<%@ page import="br.ufal.graw.AcademicUser" %>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL - Propor uma nova comunidade</TITLE>
<!-- #EndEditable -->
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<link rel="stylesheet" href="../sources/style/graw.css" type="text/css">
<script language="javascript" src="../sources/script/table.js"></script>
<BODY link=#000000 bgColor=white leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<CENTER>
  <DIV align=center><br>
    <TABLE class="tabelaMaior" cellSpacing=0 cellPadding=0 width=748 border="0" height="0">
      <TR> 
        <TD class=menuFora height=0 valign="top"> 
          <table cellspacing=0 cellpadding=0 width=760 border=0 align="left" height="0">
            <tbody> 
            <tr> 
              <td class=menuFora height=0 colspan=4 valign="baseline"> 
                <table class="tabelaSimples2" cellspacing=0 cellpadding=0 width=100% border=0>
                  <tr> 
                    <td  valign=center align=middle height=18 width="328" rowspan="2"><img src="../sources/images/gr01.jpg" width="340" height="100"></td>
                    <td  valign=center align=middle height=65 background="../sources/images/gr03.jpg"> 
                      <div  id="textoBlue" align="right"> 
                        <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td height="0"> 
                              <div align="right"><!-- #BeginEditable "directory" -->Minhas 
                        Comunidades * Propor nova comunidade<!-- #EndEditable --></div>
                            </td>
                          </tr>
                          <tr> 
                            <td><b>Seja Bem Vindo <%=user.getFirstName() %>! </b></td>
                          </tr>
                        </table>
                        &nbsp; </div>                      
                    </td>
                  </tr>
                  <tr> 
                    <td  valign=center align=middle height=0><img src="../sources/images/gr04.jpg" width="420" height="36" usemap="#Map2" border="0"></td>
                  </tr>
                  <tbody> 
                  <tr> 
                    <td id=header valign=center align=middle height=7 colspan="2"> 
                      <div align="right"><font color="#FF0000"><b> 
                        <%
		      		String msg = request.getParameter("message") ;
				if (msg!=null){	%>
                        <%=msg%> 
                        <%
				}
		      		%>
                        </b></font> </div>
                    </td>
                  </tr>
                  </tbody> 
                </table>
                
              </td>
            </tr>
          </table>
        </TD>
      </TR>
      <TBODY> 
      <TR> 
        <TD class=menuFora valign="top" height=0> 
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="22%" height="0" valign="top"> 
                <table class=tabelaSimples cellspacing=1 cellpadding=3 width="100%" 
      border=0>
                  <tbody> 
                  <tr> 
                    <td id=headerSimples> Minhas Informa&ccedil;&otilde;es</td>
                  </tr>
                  <tr> 
                    <td id=corpoSimples height="0"> 
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="config.jsp" class="Link">Pessoais</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="password.jsp" class="Link">Mudar senha</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="agenda.jsp" class="Link">Agenda</a> </td>
                  </tr>
                  </tbody> 
                </table>
                <table class=tabelaSimples cellspacing=1 cellpadding=3 width="100%">
                  <tbody> 
                  <tr> 
                    <td id=headerSimples>Comunidades</td>
                  </tr>
                  <tr> 
                    <td id=corpoSimples height="0">&nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="chooseCommunity.jsp" class="Link">Acessar</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="editCommunity.jsp" class="Link">Buscar</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="newCommunity.jsp" class="Link">Propor</a><br>
                    </td>
                  </tr>
                  </tbody> 
                </table>
		<%
			if ( community!=null ){		%>
                <table class=tabelaSimples cellspacing=1 cellpadding=3 width="100%">
                  <tbody> 
                  <tr> 
                    <td id=headerSimples>Comunica&ccedil;&atilde;o </td>
                  </tr>
                  <tr> 
                    <td id=corpoSimples height="0">&nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../forum/index.jsp" class="Link" >F&oacute;rum</a><br>
                      <%
					  String salaID = community.getID();
					  String name =  community.getTitle() ;
					  name = Utility.strReplace(name," ","%20");
					  %>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a class="Link" href="#" onclick="window.open('<%=( (br.ufal.graw.Config)session.getAttribute("conf")).getChat("servlet")%>br.ufal.graw.chat.Chat?acao=enviaapelido&apelido=<%= user.getLogin() %>&salaID=<%= salaID %>&salaName=<%= name %>','grawChat','width=640,height=480,resize=no'); return false;">Chat</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../email/index.jsp" class="Link" >Email</a></td>
                  </tr>
                  </tbody> 
                </table>
                <table class=tabelaSimples cellspacing=1 cellpadding=3 width="100%">
                  <tbody> 
                  <tr> 
                    <td id=headerSimples> Pedag&oacute;gico</td>
                  </tr>
                  <tr> 
                    <td id=corpoSimples height="0">&nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../document/index.jsp" class="Link">Documentos</a> 
                      <br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../link/index.jsp" class="Link">Links </a></td>
                  </tr>
                  </tbody> 
                </table>
				<% } %>
		<table class=tabelaSimples cellspacing=1 cellpadding=3 width="100%">
                  <tbody> 
                  <tr> 
                    <td id=headerSimples> Convites</td>
                  </tr>
                  <tr> 
                    <td id=corpoSimples height="0">&nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="confirmInvitation.jsp" class="Link">Confirmar</a>                       
		      &nbsp;</td>
                  </tr>
                  </tbody> 
                </table>
              </td>
              <td valign="top" width="78%"> 
                <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td><!-- #BeginEditable "information" --> <BR>
                      <table class=tabelaSimples cellspacing=0 cellpadding=3 width="90%" align=center>
                        <tr> 
                          <td id=headerSimplesFont height="0">&nbsp;Proposta de 
                            novas comunidades</td>
                        </tr>
                        <tr> 
                          <td height="0" valign=top>Os tipos de comunidades que 
                            voc&ecirc; pode propor est&atilde;o abaixo. Voc&ecirc; 
                            ser&aacute; o &uacute;nico respons&aacute;vel por 
                            essa comunidade. Aqui voc&ecirc; faz apenas uma proposta 
                            de uma nova, que ser&aacute; analisada por nossos 
                            consultores. Caso seja aprovada, sua comunidade ser&aacute; 
                            liberada e visualizada no menu de busca.</td>
                        </tr>
                      </table>

                      <BR>
                      <% 	String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center>   <font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
		%>
                      <table width="90%" border="0" align="center">
                        <%
			  if (user instanceof Professor ) {	  %>
                        <tr> 
                          <td id=headerSimplesFont>Disciplina Acad&ecirc;mica</td>
                        </tr>
                        <tr> 
                          <td id=corpoSimples>A disciplina acad&ecirc;mica &eacute; 
                            a disciplina no qual voc&ecirc; professor ser&aacute; 
                            respons&aacute;vel no ambiente GraW. Portanto, n&atilde;o 
                            precisa ser necess&aacute;riamente a disciplina em 
                            que voc&ecirc; d&aacute; aulas presenciais. Voc&ecirc; 
                            dever&aacute; estar munido com todas as informa&ccedil;&otilde;es 
                            reais. 
                            <form method="post" action="newDiscipline1.jsp">
                              <p align=right> 
                                <input type="submit" value="Propor Disciplina">
                              </p>
                            </form>
                          </td>
                        </tr>
                        <%			}			%>
                        <tr> 
                          <td id=headerSimplesFont>Curso</td>
                        </tr>
                        <tr> 
                          <td id=corpoSimples>Os cursos extracurriculares s&atilde;o 
                            aqueles que d&atilde;o apoio aos cursos acad&ecirc;micos. 
                            Eles podem ser: <b>Acad&ecirc;micos</b>, no qual contem 
                            um conte&uacute;do acad&ecirc;mico, podendo ser compartilhado 
                            com outras institui&ccedil;&otilde;es. J&aacute; os 
                            <b>Livres</b> s&atilde;o aqueles onde qualquer usu&aacute;rio, 
                            seja ele acad&ecirc;mico ou geral, pode ter acesso.<BR>
                            <form method="post" action="newCourse1.jsp">
                              <%
			  if (user instanceof AcademicUser) {   %>
                              <input type="radio" name="createCourse" value="academic">
                              Curso Acad&ecirc;mico 
                              <%    }   %>
                              <input type="radio" name="createCourse" value="free" checked>
                              Curso Livre 
                              <input type="submit" value="Propor Curso">
                            </form>
                          </td>
                        </tr>
                        <tr> 
                          <td id=headerSimplesFont>Grupos</td>
                        </tr>
                        <tr> 
                          <td id=corpoSimples>Os grupos s&atilde;o caracterizados 
                            por ter apenas usu&aacute;rios com interesses &uacute;nicos, 
                            para discuss&otilde;es e debates. Eles tamb&eacute;m 
                            podem estar associados a uma disciplina de uma institui&ccedil;&atilde;o, 
                            usado para realiza&ccedil;&atilde;o de tarefas etc. 
                            <br>
                            <form method="post" action="newGroup1.1.jsp">
                              <input type="submit" value="Grupo Geral">
                            </form>
                            <%	  if (user instanceof  AcademicUser) {    %>
                            <form method="post" action="newGroup1.jsp">
                              <input type="submit" value="Grupo de uma disciplina/curso">
                            </form>
                            <%    }   %>
                          </td>
                        </tr>
                        <tr> 
                      </table>
                      <!-- #EndEditable --></td>
                  </tr>
                </table>
          <BR>
              </td>
            </tr>
          </table>
          
        </TD>
      </TR>
      <TR> 
        <TD class=menuFora height=0> 
          <table  class="tabelaSimples2" cellspacing=0 cellpadding=0 width=100% border=0>
            <tbody> 
            <tr> 
              <td id=header valign=top align=middle height=0> 
	      		<div align="right"> <%
				if (community instanceof Group){
					Course course = ((Group)community).getCourse() ;
					if (course!=null){	%>
						<a  href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ course.getID() %>"><%=course.getTitle()%></a> <FONT class=menuFora> &#149;</FONT>	<%
					}
				}	
				if (community != null){	%>				  
					<a href=../community/index.jsp><%=community.getTitle()  %></a>		<%
				 }%>
			</div>
					
           	    <div align="center"><br>
		  ® 2001. Todos os direitos reservados - TCI - Departamento de 
		  Tecnologia da Informa&ccedil;&atilde;o - UFAL   -   <a href=../email/index.jsp?subjectID=111>graw@tci.ufal.br</a></div>
              </td>
            </tr>
            </tbody> 
          </table>
        </TD>
      </TR>
      </TBODY> 
    </TABLE>
    
  </DIV>
</CENTER>
<map name="Map2"> 
  <area shape="circle" coords="80,23,12" href="../help/information.jsp">
  <area shape="circle" coords="149,22,13" href="../help/comunication.jsp">
  <area shape="circle" coords="203,21,11" href="../help/pedagogic.jsp">
  <area shape="circle" coords="260,22,11" href="../help/course.jsp">
  <area shape="circle" coords="316,21,11" href="../help/group.jsp">
  <area shape="circle" coords="368,21,10" href="../help/index.jsp">
  <area shape="circle" coords="409,20,9" href="../servlet/br.ufal.graw.web.Logout">
</map>
</BODY>
<% } %>	  
<!-- #EndTemplate --></HTML>
