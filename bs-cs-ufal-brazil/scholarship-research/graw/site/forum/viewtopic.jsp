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
<%@ page import="br.ufal.graw.Forum" %>
<%@ page import="br.ufal.graw.ForumCategory" %>
<%@ page import="br.ufal.graw.Message" %>
<%@ page import="java.util.Vector" %>
<% 
	String categoryID =  request.getParameter("categoryID");
	String messageID = request.getParameter("messageID");
	Forum forum = new Forum(community.getID(),database);	
	ForumCategory forumCategory = new ForumCategory(categoryID,database);
	
	Message parentMessage  = new Message(messageID, (DatabaseLayer)session.getAttribute("database"));		
	
	if ((messageID == null) || (forum == null) || (parentMessage == null)){
		session.invalidate();
		ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sessão expirou.");
	}else{
%>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL - Forum - Tópico da mensagem: <%= parentMessage.getTitle() %></TITLE>
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
                              <div align="right"><!-- #BeginEditable "directory" --><font face="arial, helvetica, sans-serif" color=#000000 size=2><b>Meus 
                        Cursos 
                        <%  				if (community != null) {		%>
                        <font class=menuFora>&#149;</font> <a href=../community/index.jsp><%=community.getTitle() %></a> 
                        <font class=menuFora>&#149;</font> Fórum de discussão 
                        <%}  %>
                        <font class=menuFora>&#149;</font> T&oacute;pico</b></font><!-- #EndEditable --></div>
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
                      <a href="../user/config.jsp" class="Link">Pessoais</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../user/password.jsp" class="Link">Mudar senha</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../user/agenda.jsp" class="Link">Agenda</a> </td>
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
                      <a href="../user/chooseCommunity.jsp" class="Link">Acessar</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../user/editCommunity.jsp" class="Link">Buscar</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="../user/newCommunity.jsp" class="Link">Propor</a><br>
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
                      <a href="index.jsp" class="Link" >F&oacute;rum</a><br>
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
                      <a href="../user/confirmInvitation.jsp" class="Link">Confirmar</a>                       
		      &nbsp;</td>
                  </tr>
                  </tbody> 
                </table>
              </td>
              <td valign="top" width="78%"> 
                <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td><!-- #BeginEditable "information" -->                      <table width="100%" border="0" cellspacing="0" cellpadding="0" height="37">
                        <tr> 
                          <td height="42" width="60%"> 
                            <div align="left"><font face="arial, helvetica, sans-serif" color=#000000 size=2><b>Tópico 
                              do Forum - </b></font> <b><a href="getMessages.jsp?categoryID=<%= categoryID %>"><%=forumCategory.getTitle()%></a></b></div>
                          </td>
                          <td height="42" width="40%"> 
                            <table cellspacing=2 cellpadding=0 border=0 width="288">
                              <tbody> 
                              <tr> 
                                <td width="17"><a href="newtopic.jsp?categoryID=<%=forumCategory.getID()%>"> 
                                  <img alt="Clique para postar um novo tópico" src="../sources/images/forum/post.gif" border=0></a></td>
                                <td noWrap width="127">&nbsp;<a title="Clique para postar um novo tópico" href="newtopic.jsp?categoryID=<%=forumCategory.getID()%>">Postar 
                                  novo t&oacute;pico</a></td>
                                <td width="17">&nbsp;</td>
                                <td noWrap width="117">&nbsp;</td>
                              </tr>
                              </tbody> 
                            </table>
                          </td>
                        </tr>
                        <tr> 
<% 
	Vector replays = forum.getReplays(parentMessage.getID());
	int quantReplays = replays.size();
	String answer = (quantReplays > 1) ? "respostas" : "resposta";
%>
                          <td id=headerSimplesFont height="11" >&nbsp; Este t&oacute;pico 
                            contem <%= quantReplays %> <%= answer %> </td>
                          <td id=headerSimplesFont height="11" > 
                            <table cellspacing=2 cellpadding=0 border=0>
                              <tr> 
                                <td><a href="index.jsp"><img alt="Voltar para a lista dos tópicos" src='../sources/images/forum/back_to.gif' border=0 width="12" height="12"></a></td>
                                <td noWrap><font face=verdana,arial,helvetica,sans-serif size=-2>&nbsp;<a title="Voltar para a lista dos tópicos" href="">Voltar 
                                  a lista</a></font></td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>
                      <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center bgColor=#cccccc border=0>
                        <TBODY> 
                        <TR> 
                          <TD> 
                            <TABLE cellSpacing=1 cellPadding=4 width="100%" bgColor=#cccccc  border=0>
                              <TBODY> 
                              <TR id=corpoSimples> 
                                <TD vAlign=top width="19%" rowSpan=2> 
                                  <TABLE cellSpacing=0 cellPadding=0 width=104 border=0>
                                    <TBODY> 
                                    <TR> 
                                      <TD vAlign=top><font face="verdana" color=#000000 size="1"><b><%= parentMessage.getSenderFirstName()  %></b></font><FONT face=arial,helvetica,sans-serif color=#000000 size=-2><BR>
                                        <a class="Link" href="../user/info.jsp?userID=<%=parentMessage.getSenderID()%>"><img src="../sources/images/user.gif" border=0 alt="Mais informa&ccedil;&otilde;es do usuário <%=parentMessage.getSenderFirstName()%>" width="20" height="20"></a><a href="/email/?userID=<%= parentMessage.getSenderID() %>"><img alt="Enviar email para <%= parentMessage.getSenderFirstName()  %>" src="../sources/images/email.gif" border=0></a> 
                                        </FONT></TD>
                                    </TR>
                                    </TBODY> 
                                  </TABLE>
                                </TD>
                                <TD height="14"><font face="arial, helvetica, sans-serif" color=#000000 size=2><b><%=parentMessage.getTitle()%></b></font></TD>
                                <TD noWrap align=middle valign=top width="19%" height="14"> 
                                  <TABLE cellSpacing=0 cellPadding=2 border=0>
                                    <TBODY> 
                                    <TR> 
                                      <TD><A href="newtopic.jsp?ownerMessageID=<%= messageID %>&categoryID=<%=forumCategory.getID()%>"><IMG alt="Responder a essa mensagem" hspace=3 src="../sources/images/forum/reply.gif" border=0></A></TD>
                                      <TD><FONT face=arial,helvetica,sans-serif size=-1> 
                                        <A title="Responder a essa mensagem" href="newtopic.jsp?ownerMessageID=<%= messageID %>&categoryID=<%=forumCategory.getID()%>">Responder</A> 
                                        </FONT></TD>
                                    </TR>
                                    </TBODY> 
                                  </TABLE>
                                </TD>
                              </TR>
                              <TR id=corpoSimples> 
                                <TD vAlign=top colSpan=2> <font face="arial, helvetica, sans-serif" color=#000000><img src="../sources/images/forum/posticon.gif"></font><font face="arial, helvetica, sans-serif" color=#000000 size=1><%=parentMessage.getPostDate()%></font><font face="arial, helvetica, sans-serif" color=#000000><br>
                                  <%=parentMessage.getDescription()%></font></TD>
                              </TR>
                              </TBODY> 
                            </TABLE>
                          </TD>
                        </TR>
                        </TBODY> 
                      </TABLE>
                      <br>
                      <%    if (quantReplays > 0){
					for (int i=0; i < replays.size(); i++){
							String trColor = (i % 2 == 0) ? "#efefef" : "#D6E1EA";
							Message message = (Message)replays.get(i);
%>
                      <table cellspacing=1 cellpadding=4 width="100%" bgcolor=#cccccc  border=0>
                        <tbody> 
                        <tr bgcolor=<%= trColor %>> 
                          <td valign=top width="19%" rowspan=2> 
                            <table cellspacing=0 cellpadding=0 width=104 border=0>
                              <tbody> 
                              <tr> 
                                <td valign=top><font face="verdana" color=#000000 size="1"><b><%= message.getSenderFirstName() %></b></font><font face=arial,helvetica,sans-serif color=#000000 size=-2><br>
                                  <a class="Link" href="../user/info.jsp?userID=<%=message.getSenderID()%>"><img src="../sources/images/user.gif" border=0 alt="Mais informa&ccedil;&otilde;es do usuário <%=message.getSenderFirstName() %>" width="20" height="20"></a><a href="/email/?userID=<%= message.getSenderID() %>"><img alt="Enviar email para <%= message.getSenderFirstName()  %>" src="../sources/images/email.gif" border=0></a> 
                                  </font></td>
                              </tr>
                              </tbody> 
                            </table>
                          </td>
                          <td height="2"><font face="arial, helvetica, sans-serif" color=#000000 size=2><b><%=message.getTitle()%></b></font></td>
                          <td noWrap align=middle width="19%"> 
                            <table cellspacing=0 cellpadding=2 border=0>
                              <tbody> 
                              <tr> 
                                <td><a href="newtopic.jsp?ownerMessageID=<%= messageID %>&replyID=<%=message.getID()%>&categoryID=<%=forumCategory.getID()%>"><img alt="Responder a essa mensagem" hspace=3 src="../sources/images/forum/reply.gif" border=0></a></td>
                                <td><font face=arial,helvetica,sans-serif size=-1> 
                                  <a title="Responder a essa mensagem" href="newtopic.jsp?ownerMessageID=<%= messageID %>&replyID=<%=message.getID()%>&categoryID=<%=forumCategory.getID()%>">Responder</a> 
                                  </font></td>
                              </tr>
                              </tbody> 
                            </table>
                          </td>
                        </tr>
                        <tr bgcolor=<%= trColor %>> 
                          <td valign=top colspan=2> <font face="arial, helvetica, sans-serif" color=#000000><img src="../sources/images/forum/posticon.gif"></font><font face="arial, helvetica, sans-serif" color=#000000 size=1><%=message.getPostDate()%></font><font face="arial, helvetica, sans-serif" color=#000000><br>
                            <%=message.getDescription()%></font></td>
                        </tr>
                        </tbody> 
                      </table>
<%         }
      }
} %><!-- #EndEditable --></td>
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
