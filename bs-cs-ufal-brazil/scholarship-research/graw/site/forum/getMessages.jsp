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
<%@ page import="br.ufal.graw.ForumCategory" %>
<%@ page import="br.ufal.graw.Forum" %>
<%@ page import="br.ufal.graw.Message" %>
<% 
	String categoryID = request.getParameter("categoryID");		
	ForumCategory forumCategory  = new ForumCategory(categoryID,(DatabaseLayer)session.getAttribute("database"));		
	final int HOT_TOPIC = 10 ; 	// Numeros de respostas para o tópico ser considerado "quente."
 %>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL - Fórum de discursão / <%= community.getTitle() %></TITLE>
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
                        comunidades 
                        <%  				if (community != null) {		%>
                        <font class=menuFora>&#149;</font> <a href=../community/index.jsp><%= community.getTitle() %></a> 
                        <font class=menuFora>&#149;</font>Fórum de discussão 
                        <%}  %>
                        <!-- #EndEditable --></div>
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
<%@ include file="../common/moreHeader.jsp" %>
<%		 
		Message[] messages = forumCategory.getMessages(offset, limit);
		int totalrows = messages.length;
		if (endd > totalrows) { //show the last indexes
        	endd = totalrows + offset;
    	}		
 %>
                      <table cellpadding=5 width="100%" align=center border=0 height="22">
                        <tbody> 
                        <tr> 
                          <td align=middle width="53%" rowspan=2 height="26"><b> 
                            Discussão sobre <%=forumCategory.getTitle()%></b></td>
                          <td align=middle height=26 width="47%"> 
                            <table cellspacing=2 cellpadding=0 border=0 width="288">
                              <tbody> 
                              <tr> 
                                <td width="17"><a href="newtopic.jsp?categoryID=<%=forumCategory.getID()%>"> 
                                  <img alt="Clique para postar um novo tópico" src="../sources/images/forum/post.gif" border=0></a></td>
                                <td noWrap width="133">&nbsp;<a title="Clique para postar um novo tópico" href="newtopic.jsp?categoryID=<%=forumCategory.getID()%>">Postar 
                                  novo t&oacute;pico</a> </td>
                                <td width="12">&nbsp;</td>
                                <td noWrap width="117">&nbsp;</td>
                              </tr>
                              </tbody> 
                            </table>
                          </td>
                        </tr>
                        </tbody> 
                      </table>
<% 
if(messages.length > 0){
%>
                      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                        <TR> 
                          <TD> 
                            <TABLE cellSpacing=1 cellPadding=3 width="100%" border=0>
                              <TR id=headerSimplesFont> 
                                <TD width="1%">&nbsp;</TD>
                                <TD width="66%" id=headerSimplesFont><B>Assunto</B></TD>
                                <TD noWrap width="6%"  id=headerSimplesFont> 
                                  <div align="center"><B>R&eacute;plicas</B></div>
                                </TD>
                                <TD noWrap width="9%"  id=headerSimplesFont > 
                                  <div align="center"><B>Autor</B></div>
                                </TD>
                                <TD noWrap width="18%" id=headerSimplesFont> 
                                  <div align="center"><B>Quando postou</B></div>
                                </TD>
                              </TR>
<%	
			int lineOdd= 0;
			int numberOfReplays;
			for (int i=0; i < messages.length; i++){
 					    String color = ((lineOdd % 2)== 0) ? "ffffff" : "f5f5f5";
  					   lineOdd++;
//					   numberOfReplays = forum.getReplays(messages[i].getID()).size();					   	
%>
                              <TR bgColor="#<%= color %>" onMouseOver="mOvr(this, '#e0e0e0'); " onMouseOut="mOut(this, '#<%= color %>');"> 
                                <TD noWrap width="1%"> 
 <%
  						if (messages.length > HOT_TOPIC ) {	%>
                                  <img src="../sources/images/forum/hot_folder.gif">	
<%
						}else{	
%>                             <img src="../sources/images/forum/folder.gif">	
<%					}	
%>
                                </TD>
                                <TD width="66%"><a href="viewtopic.jsp?messageID=<%=messages[i].getID()%>&categoryID=<%=forumCategory.getID()%>"><b><%=messages[i].getTitle()%></b></a></TD>
                                <TD width="6%"  align="center">
                                  <div align="center"><%= Forum.getNumberReplays(messages[i].getID(),database) %></div>
                                </TD>
                                <TD noWrap width="9%" align="center"> 
                                  <div align="left">&nbsp;<a href="/user/info.jsp?userID=<%= messages[i].getSenderID()%>"><b><%= messages[i].getSenderFirstName() %></b></a></div>
                                </TD>
                                <TD noWrap width="18%" align="center">
                                  <%= messages[i].getPostDateShort() %>
                                </TD>
                              </TR>
<%     	}     %>
                              <TR> 
                                <TD width="100%" colspan=5 id=headerSimplesFont> 
                                  <%@ include file="../common/moreBottom.jsp" %>
                                </TD>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>
 <%}   else { %>
                      <br>
                      <br>
                      <br>
                      <br>
                      <center>
                        Ainda não existe mensagens nesta comunidade! Se quiseres 
                        adicionar, basta clicar em Novo Tópico. 
                      </center>
 <%     }     %>
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
