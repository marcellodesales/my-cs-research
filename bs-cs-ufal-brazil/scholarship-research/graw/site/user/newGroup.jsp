<HTML><!-- #BeginTemplate "/templates/user.dwt" -->
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
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sess�o expirou.");
}else{

%>

<HEAD>
<!-- #BeginEditable "doctitle" --> 
<%@ page import="java.util.Enumeration" %>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL</TITLE>
<!-- #EndEditable -->
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<link rel="stylesheet" href="../sources/style/graw.css" type="text/css">
<script language="javascript" src="../sources/script/table.js"></script>
<BODY link=#000000 bgColor=white leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<CENTER>
  <DIV align=center><br>
    <TABLE class="tabelaMaior" cellSpacing=0 cellPadding=0 width=748 border="0" height="0">
      <TR> 
        <TD class=menuFora height=84 valign="top"> 
          <table cellspacing=0 cellpadding=0 width=760 border=0 align="left" height="0">
            <tbody> 
            <tr> 
              <td class=menuFora height=0 colspan=4> 
                <table class="tabelaSimples2" cellspacing=0 cellpadding=0 width=100% border=0>
                  <tr> 
                    <td  valign=center align=middle height=18 width="328" rowspan="3"><img src="../sources/images/titulo2.gif" width="328" height="100"></td>
                    <td  valign=center align=middle height=32 colspan="2">
                      <div  id="textoBlue" align="right"><!-- #BeginEditable "directory" --> Meus Grupos <FONT class=menuFora> 
                &#149; Criar</FONT> <!-- #EndEditable -->&nbsp;&nbsp;
                      </div>
                    </td>
                  </tr>
                  <tr> 
                    <td  valign=center align=middle height=33 width="256">Seja 
                      Bem Vindo <%=user.getFirstName() %>! </td>
                    <td  valign=center align=middle height=33 width="186">
                      <div align="right">
                      </div>
                    </td>
                  </tr>
                  <tr> 
                    <td  valign=center align=middle height=9 colspan="2"><img src="../sources/images/titulo5.gif" width="432" height="35" usemap="#Map" border="0"></td>
                  </tr>
                  <tbody> 
                  <tr> 
                    <td id=header valign=center align=middle height=7 colspan="3"> 
                      <div align="right"></div>
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
                      <a class="Link" href="#" onclick="window.open('<%=( (br.ufal.graw.Config)session.getAttribute("conf")).getChat("servlet")%>br.ufal.graw.chat.Chat?acao=enviaapelido&apelido=<%= user.getLogin() %>&salaID=<%= salaID %>&salaName=<%= name %>','grawChat','width=640,height=480'); return false;">Chat</a><br>
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
              </td>
              <td valign="top" width="78%"> 
                <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td><!-- #BeginEditable "information" -->
		
		<div align=center>
			<form action="../servlet/br.ufal.graw.web.user.CreateNewGroup" method="post">
              <br>
              <table width="80%" border="0">
                <tr> 
                  <td id=headerSimples>Curso</td>
                </tr>
                <tr> 
                  <td id=corpoSimples> 				  	
                    <select name="courseID">
						<%						
						Enumeration courses = user.getCourses();
						Course courseItem;
						while (courses.hasMoreElements() ){
							courseItem = (Course)courses.nextElement(); %>							
                      		<option value="<%=courseItem.getID()%>"><%=courseItem.getName()%></option>	<%
						} %>
                    </select>
                    *Curso ao qual esse grupo ir&aacute; pertencer.</td>
                </tr>
                <tr> 
                  <td id=headerSimples>Nome do grupo</td>
                </tr>
                <tr> 
                  <td id=corpoSimples> 
                    <input type="text" name="name" size="30">
                    *Escolha um nome para esse grupo</td>
                </tr>
                <tr> 
                  <td id=headerSimples>Objetivos</td>
                </tr>
                <tr> 
                  <td id=corpoSimples> 
                    <textarea name="goal" cols="45" rows="5"></textarea>
                    <br>
                    *Descreva os objetivos desse grupo.</td>
                </tr>
                <tr> 
                  <td id=headerSimples>Visibilidade</td>
                </tr>
                <tr> 
                  <td id=corpoSimples> 
                    <input type="radio" name="visible" value="true" checked>
                    Todos desse curso podem enviar email para os membros desse 
                    grupo. <br>
                    <input type="radio" name="visible" value="false">
                    Apenas membros desse grupo podem enviar email para o grupo.</td>
                </tr>
                <tr>
                  <td id=corpoSimples>
                    <div align="center">
                      <input type="submit" name="Submit" value="Cadastrar Grupo" class="botao">
                    </div>
                  </td>
                </tr>
              </table>
		  </form>
		  </div>
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
			  <%
			  if (community != null){	%>
				  <div align="right">
    	              <a href=../community/index.jsp><%=community.getTitle()  %></a>                 
				</div><%
			 }%>
					
           	    <div align="center"><br>
                  � 2001. Todos os direitos reservados - TCI - Departamento de 
                  Tecnologia da Informa&ccedil;&atilde;o - UFAL </div>
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
<map name="Map"> 
  <area shape="circle" coords="94,19,11" href="../help/information.jsp" alt="Ajuda sobre: Minhas Informacoes" title="Ajuda sobre: Minhas Informacoes">
  <area shape="circle" coords="207,20,12" href="../help/pedagogic.jsp" alt="Ajuda sobre: Pedag&oacute;gico" title="Ajuda sobre: Pedag&oacute;gico" >
  <area shape="circle" coords="265,21,12" href="../help/course.jsp" alt="Ajuda sobre: Cursos" title="Ajuda sobre: Cursos">
  <area shape="circle" coords="328,20,12" href="../help/group.jsp" alt="Ajuda sobre:  Grupos" title="Ajuda sobre:  Grupos">
  <area shape="circle" coords="378,20,12" href="../help/index.jsp" alt="Ajuda do graW" title="Ajuda do graW">
  <area shape="circle" coords="420,20,10" href="../servlet/br.ufal.graw.web.Logout" alt="Sair do graW" title="Sair do graW">
  <area shape="circle" coords="151,18,12" href="../help/comunication.jsp" alt="Ajuda sobre: Comunica&ccedil;&atilde;o" title="Ajuda sobre: Comunica&ccedil;&atilde;o">
</map>
</BODY>
<% } %>	  
<!-- #EndTemplate --></HTML>
