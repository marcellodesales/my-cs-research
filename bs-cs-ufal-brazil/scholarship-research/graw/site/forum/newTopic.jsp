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
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sessão expirou.");
}else{

%>

<HEAD>
<!-- #BeginEditable "doctitle" --> 
<%@ page import="br.ufal.graw.Message" %>
<%@ page import="br.ufal.graw.ForumCategory" %>
<%
		String ownerMessageID = request.getParameter("ownerMessageID");
		String replyMessageID = request.getParameter("replyID");
		String categoryID = request.getParameter("categoryID");
		Message ownerMessage = null;
		Message replyMessage = null;
		if (ownerMessageID != null){
			ownerMessage = new Message(ownerMessageID, (DatabaseLayer)session.getAttribute("database"));	
			session.setAttribute("ownerMessageID", ownerMessageID);		
		}
		
		if (replyMessageID != null){
			replyMessage = new Message(replyMessageID, (DatabaseLayer)session.getAttribute("database"));			
			session.setAttribute("replyID", replyMessageID);			
		}
		
		String  replyTitle = "";   
		if (replyMessage != null){	     
                    replyTitle = "Resp: "+replyMessage.getTitle(); 
		} else if (ownerMessage != null){
		 			replyTitle = "Resp: "+ownerMessage.getTitle();
		}				
		
	ForumCategory forumCategory = new ForumCategory(categoryID, database);
	String whatsdoing;
	if (ownerMessage != null){   
			whatsdoing = "Responder mensagem - "+ forumCategory.getTitle();
   } else { 
			whatsdoing = "Enviar nova mensagem - fórum sobre "+ forumCategory.getTitle();
    }   
%>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL - <%= whatsdoing %></TITLE>
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
                              <div align="right"><!-- #BeginEditable "directory" --><b>{directory}</b><!-- #EndEditable --></div>
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
                    <td><!-- #BeginEditable "information" -->
					  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="24">
                        <tr> 
                          <td height="30" width="60%"> 
                            <div align="left"><font face="arial, helvetica, sans-serif" color=#000000 size=2><b>Tópico 
                              do Forum - <a href="index.jsp"><%= forumCategory.getTitle()%></a></b></font> </div>
                          </td>
                          <td height="30" width="40%"> 
                            <table cellspacing=2 cellpadding=0 border=0 width="288">
                              <tbody> 
                              <tr> 
                                <td width="17"><a href="getMessages.jsp?categoryID=<%= forumCategory.getID()%>"><img alt="Voltar para a lista dos tópicos" src='../sources/images/forum/back_to.gif' border=0 width="12" height="12"></a></td>
                                <td noWrap width="127">&nbsp;<font face=verdana,arial,helvetica,sans-serif size=-2><a title="Voltar para a lista dos tópicos" href="getMessages.jsp?categoryID=<%= forumCategory.getID()%>">Voltar 
                                  a lista</a></font></td>
                                <td width="17">&nbsp;</td>
                                <td noWrap width="117">&nbsp;</td>
                              </tr>
                              </tbody> 
                            </table>
                          </td>
                        </tr>
                      </table>
		<form method="post" action="../servlet/br.ufal.graw.web.user.CreateNewMessage">
                        <center>
                          <table bgcolor="a0b8c8" align="center" border="0" cellpadding="2" cellspacing="0" width="59%">
              <tr>
                <td>
                  <table bgcolor="#eeeeee" border="0" cellpadding="2" cellspacing="0" width="100%">
                    <tr> 
                      <td bgcolor="#ffffff" align="center"> 
                        <table border="0" cellspacing="6" cellpadding="6" bgcolor="ffffff" width="100%">
                          <tr> 
                            <td align="center" id=headerSimples> 
                              <div align="left"> 
                                <font face="arial" size="-1"><nobr><b> 
                                  <%
								  if (replyTitle.equals("")){        %>
								  Resposta a mensagem:  <%= replyTitle %>
                     <%     } else {    %>
                                  Nova Mensagem</b></nobr><b>
                      <%   }
			  	if (request.getParameter("error") != null) out.println(" - <font color=red>"+request.getParameter("error")+"</font>");
			  %>
                                </b></font><font face="arial" size="-1"><nobr></nobr><nobr><b> 
                                </b></nobr></font> </div>
                            </td>
                          </tr>
                          <tr  id=corpoSimples> 
                            <td valign="top" align="center"> 
                              <table width="89%" border="0">
                                <tr> 
                                  <td width="11%" id=headerSimples> 
                                    <div align="right"><b>Comunidade:</b></div>
                                  </td>
                                  <td width="10%"><%=community.getTitle()%></td>
                                </tr>
                                <tr> 
                                  <td width="21%" id=headerSimples> 
                                    <div align="right"><b>Autor:</b></div>
                                  </td>
                                  <td width="79%"><%=user.getName()%></td>
                                </tr>
                                <tr> 
                                  <td width="21%" id=headerSimples> 
                                    <div align="right"><b>T&iacute;tulo:</b></div>
                                  </td>
                                  <td width="79%"> 
                                    			  <input type="text" name="messageTitle" maxlength="255" size="58" value="<%= replyTitle %>">
                                  </td>
                                </tr>
                                <tr> 
                                  <td width="21%" id=headerSimples> 
                                    <div align="right"><b>Mensagem:</b></div>
                                  </td>
                                  <td width="79%"> 
                                    <textarea name="messageBody" rows="10" cols="50"></textarea>
                                  </td>
                                </tr>
                                <tr> 
                                  <td colspan="2"> 
                                    <div align="center"> 
                                        <input type="submit" name="Submit" value=" Enviar " class="botao">
                                        <input type="reset" value="Apagar" name="reset" class="botao">										
                                      </div>
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </center>
           <input Type="hidden" name="categoryID" value="<%= forumCategory.getID() %>">		  
		  </form>
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
