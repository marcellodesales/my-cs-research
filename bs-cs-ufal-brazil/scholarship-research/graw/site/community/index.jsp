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
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.Student" %>
<%@ page import="br.ufal.graw.Discipline" %>
<%@ page import="br.ufal.graw.chat.EnviaMensagem" %>
<%
	EnviaMensagem chat = new EnviaMensagem();	
%>
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
                              <div align="right"><!-- #BeginEditable "directory" --><b>Minhas 
								comunidades <FONT class=menuFora> &#149;</FONT>
								<%
			if (community instanceof Group){
				Course course = ((Group)community).getCourse() ;
				if (course!=null){	%>
								<a  href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ course.getID() %>"><%=course.getTitle()%></a> 
								<FONT class=menuFora> &#149;</FONT> 
								<%
				}
			}
			%>
								<a href=../community/information.jsp><%=community.getTitle() %> 
								<img src="../sources/images/inform.gif" alt="Informa&ccedil;&otilde;es" border="0"></a> 
								</b><!-- #EndEditable --></div>
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
                      <%
			boolean masterPermission = false;
			boolean isTeacher = false;
			boolean isDiscipline = false;
			if (user.isResponsible(community) ){
				masterPermission = true;
			}
					
			if (  community instanceof Discipline ){	// Links de uma disciplina
				isDiscipline = true;
				if (user instanceof Professor ) { 					
					if ( ((Professor)user).isTeacher((Discipline)community) ){
						masterPermission = true;
						isTeacher = true;
					}
				}else if (user instanceof Student ){					
					if (  ((Student ) user).isMonitor((Discipline) community) ){
						masterPermission = true;
					}
				}
			}	
			String communityTitle = (community instanceof Discipline) ? ((Discipline)community).getCode() + " - "+community.getTitle() : community.getTitle();
			%>
                      <br>
                      <table class=tabelaSimples cellspacing=0 cellpadding=3 width="90%" align=center>
                        <tr> 
                          <td id=headerSimplesFont height="0">&nbsp;<%= communityTitle %></td>
                        </tr>
                        <tr> 
                          <td height="0" valign=top><b><a href=../community/information.jsp><img src="../sources/images/inform.gif" alt="Informa&ccedil;&otilde;es" border="0"></a></b> 
                            Para maiores informa&ccedil;&otilde;es da comunidade. 
                            Abaixo segue um resumo do que se passa por este curso. 
                            Qualquer d&uacute;vida voc&ecirc; pode contactar os 
                            respons&aacute;veis. </td>
                        </tr>
                      </table>
                      <br>
                      <table width="90%" border="0" align="center">
                        <tr> 
                          <td width="94%" valign="top" height="275"> 
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                              <tr> 
                                <td id=headerSimplesFont colspan="4">&nbsp; <img src="../sources/images/Users.gif" width="16" height="16"> 
                                  Participa&ccedil;&atilde;o</td>
                              </tr>
                              <tr> 
                                <td height="14" colspan="4"> 
                                  <ul>
                                    <li>Usu�rios participantes:<a href="users.jsp"><%=community.getQuantUsers() %></a>	
                                    </li>
                                  </ul>
                                </td>
                              </tr>
                              <tr> 
                                <td id=headerSimplesFont height="13" colspan="4">&nbsp; 
                                  <img src="../sources/images/document.gif" width="16" height="16"> 
                                  Pedag&oacute;gico </td>
                              </tr>
                              <tr> 
                                <td height="16" colspan="4"> 
                                  <ul>
                                    <li>Documentos: <a href=../document/> <%=community.getQuantDocuments()%> 
                                      </a></li>
                                    <li>Links: <a href=../link/> <%=community.getQuantLinks() %> 
                                      </a></li>
                                  </ul>
                                </td>
                              </tr>
                              <tr> 
                                <td id=headerSimplesFont height="16" colspan="4">&nbsp; 
                                  Comunica&ccedil;&atilde;o </td>
                              </tr>
                              <tr> 
                                <td height="16" colspan="4"> 
                                  <ul>
                                    <li>Mensagens no forum:<a href=../forum/><%=community.getQuantMessages()%></a> 
                                    </li>
                                    <li>Usu&aacute;rios no chat: <a class="Link" href="#" onclick="window.open('<%=( (br.ufal.graw.Config)session.getAttribute("conf")).getChat("servlet")%>br.ufal.graw.chat.Chat?acao=enviaapelido&apelido=<%= user.getLogin() %>&salaID=<%= community.getID() %>&salaName=<%=community.getTitle() %>','grawChat','width=640,height=480'); return false;"><%=chat.getUsersOnRoom(community.getID())%></a></li>
                                    <li><a href="/email/">Enviar email para a 
                                      comunidade</a></li>
                                  </ul>
                                </td>
                              </tr>
                              <% 
							if ( masterPermission ){	  %>
                              <tr> 
                                <td id="headerSimplesFont" height="16" colspan="4"> 
                                  Esperando por autoriza&ccedil;&atilde;o </td>
                              </tr>
                              <%
								Vector users = community.getWaitingMembers();
								int quant = users.size();
								User pendentUser;
								for (int i=0 ; i< quant ; i++){
									pendentUser = (User)users.get(i) ;	 %>
                              <tr border="1"> 
                                <td ><a href="../user/info.jsp?userID=<%= pendentUser.getID() %>"><%=pendentUser.getName()%></a></td>
                                <td ><a href="../email/index.jsp?userID=<%=pendentUser.getID()%>"><img src="../sources/images/email.gif" width="16" height="16" border="0" alt="Enviar email"></a></td>
                                <td ><a class="Link" href="<%=servletDir+"br.ufal.graw.web.user.ApproveMember"%>?communityID=<%=community.getID()%>&userID=<%=pendentUser.getID()%>">Aprovar</a></td>
                                <td ><a class="Link" href="<%=servletDir+"br.ufal.graw.web.user.DisapproveMember"%>?communityID=<%=community.getID()%>&userID=<%=pendentUser.getID()%>">Rejeitar</a></td>
                              </tr>
                              <%
								} //end for								
								if (quant==0){ %>
                              <tr border="1" colspan="4"> 
                                <td >Nenhuma pend�ncia</td>
                              </tr>
                              <%
								}	%>
                              <%
								
							} // end if	
							
							if (isDiscipline){	%>
                              <tr> 
                                <td  id="headerSimplesFont" Font height="16" colspan="4">	
                                  Monitores atuais: </td>
                              </tr>
                              <tr> 
                                <td  height="16" colspan="4"> 
                                  <%
										Discipline discipline = (Discipline) community; 
										Vector students = discipline.getMonitors();
										Student student;		
										for (int i=0 ; i< students.size() ; i++ ){
											student = (Student) students.get(i); %>
                                  <a class="Link" href="../user/info.jsp?userID=<%=student.getID()%>"><img src="../sources/images/user.gif" border=0 alt="Mais informa&ccedil;&otilde;es do monitor <%=student.getFirstName()%>" width="20" height="20"></a><%=student.getName()%> 
                                  <%
											if (isTeacher){	%>
                                  <a class="Link" href="<%=servletDir+"br.ufal.graw.web.user.DeleteMonitor?userID="+student.getID()%>">[ 
                                  Eliminar ]</a> 
                                  <%
											}	%>
                                  <br>
                                  <%								
										}							
										if (students.size()==0){ %>
                                  Disciplina sem monitores. Para eleger algum, 
                                  acesse a lista de usu&aacute;rios e clique no 
                                  &iacute;cone correspondente. 
                                  <%									
										}	%>
                                </td>
                              </tr>
                              <%
							} // Fim do isDiscipline	%>
                            </table>
                          </td>
                          <td valign="top"> 
                            <% if  (masterPermission ) {%>
                            <table  class=tabelaSimples width="100%" >
                              <form  method="post" action="<%=servletDir+"br.ufal.graw.web.user.InviteGuest"%>">
                                <tr> 
                                  <td id="headerSimplesFont" height="16" colspan="4"> 
                                    Convide aqui:</td>
                                </tr>
                                <tr> 
                                  <td colspan=2> Ao convidar algu&eacute;m voc&ecirc; 
                                    estar&aacute; dando automaticamente permiss&otilde;es 
                                    totais ao seu convidado nessa comunidade. 
                                  </td>
                                </tr>
                                <tr> 
                                  <td  height="16" colspan="1"> Email:</td>
                                  <td  height="16" colspan="3"> 
                                    <input type="text" name="email">
                                  </td>
                                </tr>
                                <tr> 
                                  <td  height="16" colspan="1"> Mensagem de convite:</td>
                                  <td  height="16" colspan="3"> 
                                    <textarea name="message"></textarea>
                                  </td>
                                </tr>
                                <tr> 
                                  <td  height="16" colspan="4"> 
                                    <div align="center"> 
                                      <input type="submit" name="Submit" value="Convidar">
                                    </div>
                                  </td>
                                </tr>
                              </form>
                            </table>
                            <%}%>
                          </td>
                        </tr>
                      </table>
                      <br>
					  <%
					  	/* Se for um curso, ent�o � poss�vel que ele tenha grupos subordinados a essa comunidade. */
						if ( (community instanceof  Course)  && (masterPermission) ){ 
							Course course = (Course)community;
							Vector groups = course.getGroups();
							%>
						
                      <table width="90%" border="0" cellspacing="1" align="center">
                        <tr> 
                          <td id=headerSimples>Grupos Subordinados</td>
                        </tr>
                        <tr> 
                          <td>Aqui est&atilde;o listados os grupos que est&atilde;o 
                            vinculados a essa comunidade.<br>
                            Obs.: Voc&ecirc; pode monitor&aacute;-los.</td>
                        </tr>
						<%if (groups.size() == 0 ){%>
                        <tr> 
                          <td> 
                            <ul>
                              <li>At&eacute; o presente momento n&atilde;o existe 
                                nenhum grupo subordinado a essa disciplina</li>
                            </ul>
                          </td>
                        </tr>
						<%}else{%>
							<tr>
							  <td>
								<ul>
									<%
									for (int i =0 ; i< groups.size() ; i++){ 
										Group group = (Group)groups.get(i); %>
									  <li><a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ group.getID() %>"><%=group.getTitle()%></a></li>
									 <%} //end for%>
								</ul>
							  </td>							  
							</tr>
						<%} // end else%>
                      </table>							
						<%} // end if%>

                      <p align="center">&nbsp;</p>
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
					<a href=index.jsp><%=community.getTitle()  %></a>		<%
				 }%>
			</div>
					
           	    <div align="center"><br>
		  � 2001. Todos os direitos reservados - TCI - Departamento de 
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
