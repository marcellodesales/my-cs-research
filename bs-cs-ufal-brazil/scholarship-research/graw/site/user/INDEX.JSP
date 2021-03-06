<HTML>
<!-- #BeginTemplate "/Templates/user.dwt" --> 
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
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL</TITLE>
<%@ page import="br.ufal.graw.UserState" %>
<%@ page import="br.ufal.graw.UserCommunityState" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="br.ufal.graw.Category" %>

<%
	UserState userState = new UserState(user,request.getRemoteAddr());
	String lastIPAddress = (String)session.getAttribute("lastIPAddress");
	String lastAccessTime = (String)session.getAttribute("lastAccessTime");
	
	if (lastIPAddress == null){
		lastIPAddress = userState.getUserLastAccessIPAddress();
		session.setAttribute("lastIPAddress",lastIPAddress);
	}
	if (lastAccessTime == null){
		lastAccessTime = userState.getUserLastAccessTime();
		session.setAttribute("lastAccessTime",lastAccessTime);
	}

%>
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
                              <div align="right"><!-- #BeginEditable "directory" --><b>P�gina inicial de usu�rio</b><!-- #EndEditable --></div>
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
                    <td id=corpoSimples height="0"> &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="config.jsp" class="Link">Pessoais</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="password.jsp" class="Link">Mudar 
                      senha</a><br>
                      &nbsp;<img src="../sources/images/topic.gif" width="8" height="8"> 
                      <a href="agenda.jsp" class="Link">Agenda</a> 
                    </td>
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
                      <a href="../link/index.jsp" class="Link">Links 
                      </a></td>
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
                    <td><!-- #BeginEditable "information" --> 
                      <BR>
                      <table width="95%" border="0" align="center">
                        <tr> 
                          <td width="76%"  valign=top> 
                            <table class=tabelaSimples cellspacing=0 cellpadding=3 width="95%" align=center>
                              <tr> 
                                <td id=headerSimplesFont height="0"><img src="../sources/images/home.gif" width="16" height="16"> 
                                  Sua p&aacute;gina inicial</td>
                              </tr>
                              <tr> 
                                <td height="0" valign=top><b><%=user.getFirstName() %></b>, 
                                  Esta &eacute; sua p&aacute;gina inicial. Seu 
                                  ponto inicial no graW. Sempre acesse sua p&aacute;gina 
                                  inicial para ter informa&ccedil;&otilde;es de 
                                  atualiza&ccedil;&otilde;es de suas comunidades.</td>
                              </tr>
                            </table>
                            <br>
                            <table width="95%" border="0" align="center" cellpadding="1" cellspacing="0">
                              <tr> 
                                <td  id=headerSimplesFont width="6%">&nbsp;<img src="../sources/images/new.gif" width="16" height="16"></td>
                                <td  id=headerSimplesFont width="89%"> Minhas 
                                  Comunidades</td>
                                <td  id=headerSimplesFont width="5%"><a href="chooseCommunity.jsp"><img src="../sources/images/plus.gif" width="11" height="11" alt="Minhas Comunidades!" border="0"></a></td>
                              </tr>
                              <%
	if (userState.isNewUser()){   
%>
                              <tr>
                                <td id=corpoSimples colspan=3> <b>&nbsp;</b><%=user.getFirstName() %>, 
                                  � hora de procurar uma comunidade de interesse! 
                                  No menu Comunidades, clique em Buscar para achar 
                                  achar alguma de seu interesse! </td>
                              </tr>
                              <%
	}  else {
	if (userState.hasChangesOnCommunities()){
	
		UserCommunityState[] userCommunityState = userState.getUserCommunityStates();
		String trColor = "#efefef";
		for (int i = 0; i < userCommunityState.length; i++){
				
	  		if (userCommunityState[i].communityHasChanged()){ 
					trColor = (trColor.equals("#D6E1EA")) ?  "#efefef" : "#D6E1EA";
%>
                              <tr bgcolor=<%= trColor %>> 
                                <td colspan="3"><b><a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ userCommunityState[i].getCommunity().getID() %>"><img src="../sources/images/right.gif" border=0>&nbsp;<%= userCommunityState[i].getCommunity().getTitle()%></a></b><br>
                                  &nbsp; 
                                  <%	
				if (userCommunityState[i].thereIsNewDocuments()){
					Hashtable changedDocumentCat = userCommunityState[i].getListDocumentCategories();
					Enumeration keys = changedDocumentCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedDocumentCat.get(category);
								String news = (quantity.equals("1")) ? "documento novo" : "novos documentos";
%>
                                  <img src="../sources/images/Doc2.gif" width="20" height="20"> 
                                  <b><%= category.getTitle() %></b>: <%= quantity %> 
                                  <%= news %>.<br>
                                  &nbsp; 
                                  <%										
					}
				}

				if (userCommunityState[i].thereIsNewLinks()){
					Hashtable changedLinksCat = userCommunityState[i].getListLinkCategories();
					Enumeration keys = changedLinksCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedLinksCat.get(category);
								String news = (quantity.equals("1")) ? "link novo" : "novos links";
%>
                                  <img src="../sources/images/Doc2.gif" width="20" height="20"> 
                                  <b><%= category.getTitle() %></b>: <%= quantity %> 
                                  <%= news %>.<br>
                                  &nbsp; 
                                  <%										
					}
  				}

				if (userCommunityState[i].thereIsNewForumMessages()){
					Hashtable changedForumCat = userCommunityState[i].getListForumCategories();
					Enumeration keys = changedForumCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedForumCat.get(category);
								String news = (quantity.equals("1")) ? "mensagem nova" : "novas mensagens";
%>
                                  <img src="../sources/images/forum/folder.gif" width="16" height="16"> 
                                  <b><%= category.getTitle() %></b>: <%= quantity %> 
                                  <%= news %> no forum.<br>
                                  &nbsp; 
                                  <%	
					}
				}
											
				if (userCommunityState[i].thereIsNewUsers()){
				String news = (userCommunityState[i].getQuantityNewUsers() == 1) ? "usu�rio novo" : "novos usu�rios";
%>
                                  <img src="../sources/images/Users.gif" width="20" height="20"> 
                                  <%= userCommunityState[i].getQuantityNewUsers() %> 
                                  <%= news %>. 
                                  <%										
				}					 		  			    
%>
                                </td>
                              </tr>
                              <%		}
		}  						  
	} else { 
	
%>
                              <tr> 
                                <td id=corpoSimples colspan=3>&nbsp;<b><%=user.getFirstName() %>, 
                                  N�o h� atualiza��es em suas comunidades!</b> 
                                </td>
                              </tr>
                              <%
    }
}
%>
			</table><br>
            <table width="95%" border="0" align="center" cellpadding="1" cellspacing="0">
		 <%@ page import="br.ufal.graw.Scheduler" %>
<%
			Scheduler scheduler = new Scheduler(user, (DatabaseLayer) session.getAttribute("database"), out);
			boolean haCompromissos = scheduler.haCompromissos();
			String metting = (haCompromissos) ? "H� compromissos agendados!" : "N�o H� compromissos.";
%>			
                              <tr> 
                                <td  id=headerSimplesFont width="6%">&nbsp;<img src="../sources/images/agenda.gif" width="16" height="16"></td>
                                <td  id=headerSimplesFont width="89%">&nbsp;<%= metting %></td>
                                <td  id=headerSimplesFont width="5%"><a href="/user/agenda.jsp"><img src="../sources/images/plus.gif" width="11" height="11" alt="Minha Agenda Pessoal" border="0"></a></td>
                              </tr>
<% if (haCompromissos){	%>
							<tr><td colspan=3 >
<%		 scheduler.mostraEntrada();    %>
							</td>							  
							</tr>
<%     }    %>														
                            </table>							  

						  </td>
                          <td width="24%" valign=top>
                            <table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" height="14">
                              <tr> 
                                <td  id=headerSimplesFont width="23%" height="18">&nbsp;<img src="../sources/images/caution.gif"></td>
                                <td  id=headerSimplesFont width="77%" height="18"> 
                                  Lembre-se!</td>
                              </tr>
                              <tr>
                                <td id=corpoSimples colspan=3><b><%=user.getFirstName() %></b>, 
                                  sempre clique no (X) na barra acima quando deixar 
                                  o graW, para que suas informa��es sejam salvas 
                                  com sucesso!<br>
                                  <br>
                                  <b><a href="config.jsp"><img src="../sources/images/update.gif" width="20" height="20" border="0" alt="Minhas Informa&ccedil;&otilde;es Pessoais"></a>Sempre 
                                  atualize o endere&ccedil;o de seu email!!!</b></td>
							</tr>															  
							</table>
<!--                           <p>&nbsp;</p>
                            <p>Ultimo acesso em <%= lastAccessTime %><br>
                              IP: </p><%= lastIPAddress %></td>
  -->                      </tr>
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
                <div align="right"> 
                  <%
				if (community instanceof Group){
					Course course = ((Group)community).getCourse() ;
					if (course!=null){	%>
                  <a  href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ course.getID() %>"><%=course.getTitle()%></a> 
                  <FONT class=menuFora> &#149;</FONT> 
                  <%
					}
				}	
				if (community != null){	%>
                  <a href=../community/index.jsp><%=community.getTitle()  %></a>	
                  <%
				 }%>
                </div>
                <div align="center"><br>
                  � 2001. Todos os direitos reservados - TCI - Departamento de 
                  Tecnologia da Informa&ccedil;&atilde;o - UFAL - <a href=../email/index.jsp?subjectID=111>graw@tci.ufal.br</a></div>
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
<!-- #EndTemplate -->
</HTML>
