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
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.Student" %>
<%@ page import="br.ufal.graw.chat.EnviaMensagem" %>
<%
	/* Remove o contexto do grupo e entra no contexto do curso.	*/
	session.removeAttribute("group");
	group=null;
	
	/* Verifica permissoes de acesso */
	boolean masterPermission = false;
	
	if (user instanceof Professor){
		if ( ((Professor)user).teaches(course) ){
			masterPermission = true;
		}
	}
	
	EnviaMensagem chat = new EnviaMensagem("5001","192.168.10.14");	
	
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
                              <div align="right"><!-- #BeginEditable "directory" -->Meus 
                        Cursos <FONT class=menuFora> &#149;</FONT> <a href=../course/information.jsp><%=course.getName() %> <img src="../sources/images/inform.gif" alt="Informa&ccedil;&otilde;es" border="0"></a> 
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
          <br>
          <table width="97%" border="0">
            <tr> 
                          <td width="61%" valign="top" height="275"> 
                            <p>Abaixo segue um resumo do que se passa por este 
                              curso. Qualquer d&uacute;vida voc&ecirc; pode contactar 
                              o professor ou o respons&aacute;vel.<br>
                            </p>
                            <table width="66%" border="0" cellspacing="0" cellpadding="0" align="center">
                              <tr> 
                                <td id=headerSimplesFont>&nbsp; <img src="../sources/images/Users.gif" width="16" height="16"> 
                                  Participa&ccedil;&atilde;o</td>
                              </tr>
                              <tr> 
                                <td height="14"> 
                                  <ul>
                                    <li>Alunos inseridos:<a href=../email/><%=course.getQuantStudent() %></a> 
                                    </li>
                                  </ul>
                                </td>
                              </tr>
                              <tr> 
                                <td id=headerSimplesFont height="13">&nbsp; <img src="../sources/images/document.gif" width="16" height="16"> 
                                  Pedag&oacute;gico </td>
                              </tr>
                              <tr> 
                                <td height="16"> 
                                  <ul>
                                    <li>Documentos: <a href=../document/>
                                      <%=course.getQuantDocuments()%>
                                      </a></li>
                                    <li>Links: <a href=../link/>
                                      <%=course.getQuantLinks() %>
                                      </a></li>
                                  </ul>
                                </td>
                              </tr>
                              <tr> 
                                <td id=headerSimplesFont height="16">&nbsp; Comunica&ccedil;&atilde;o</td>
                              </tr>
                              <tr> 
                                <td height="16"> 
                                  <ul>
                                    <li>Mensagens no forum:<a href=../forum/><%=course.getQuantMessages()%></a> 
                                    </li>
                                    <li>Usu&aacute;rios no chat: <%=chat.getUsersOnRoom(course.getID())%></li>
                                  </ul>
                                </td>
                              </tr>
                            </table>
                            <p><br>
                            </p>
                            </td>
                          <td valign="top" width="39%" height="275"> 
                            <div align=center>
			  		    <table class="tabelaSimples">
                          <%
						/*	Mostra os grupos dessa disciplina que o usuário  faz parte. */
						Enumeration userGroups = user.getGroups(course);
						if (userGroups.hasMoreElements()){	%>
                          <TR> 
                            <TD id=headerSimplesFont colspan=3 align=middle width="86%"> 
                              Meus grupos desse curso. </TD>
                          </TR>
                          <%
							Group groupItem;
							while ( userGroups.hasMoreElements() ){
								groupItem =  (Group)userGroups.nextElement() ;	%>
                          <tr > 
                            <td id=corpoSimples colspan=2  width="86%"> 
                              <div align="right"> <font face="arial, helvetica, sans-serif" size=2> 
                                <a href="../servlet/br.ufal.graw.web.group.OpenGroup?groupID=<%=groupItem.getID() %>" > 
                                <%= groupItem.getName() %> </a> </font> </div>
                            </td>
                            <td id=corpoSimples> <a href=../email/index.jsp?groupID=<%=groupItem.getID() %>><img src="../sources/images/email.gif" border=0 width="20" height="20" alt="Enviar Email para o grupo"> 
                              </a> </td>
                          </tr>
                          <%
							 }	// End While 							 
						} // END IF
						
						Enumeration allGroups = course.getVisibleGroups();
							if ( allGroups.hasMoreElements() ){	%>
                          <TR> 
                            <TD id=headerSimplesFont colspan=3  align=middle width="86%"> 
                              <B> Grupos visíveis desse curso. </B> </TD>
                          </TR>
                          <%								
								Group groupItem;
								while ( allGroups.hasMoreElements() ){
									groupItem =  (Group)allGroups.nextElement() ;	%>
                          <tr > 
                            <td id=corpoSimples  colspan=2 width="86%"> 
                              <div align="right"><font face="arial, helvetica, sans-serif" size=2> 
                                <%= groupItem.getName() %> </font> </div>
                            </td>
                            <td id=corpoSimples> <a href=../email/index.jsp?groupID=<%=groupItem.getID() %>><img src="../sources/images/email.gif"  border=0 width="20" height="20" alt="Enviar Email para o grupo"> 
                              </a> </td>
                          </tr>
                          <%
								 }	// End While
							} 							
							Vector monitors = course.getMonitors();
							if ( monitors.size() > 0 ){
								User monitor;	%>
                          <tr> 
                            <td id=headerSimplesFont colspan=3 > Monitores </td>
                          </tr>
                          <%
								for (int i=0 ; i< monitors.size() ; i++){
									monitor = (User)monitors.get(i);	%>
                          <tr> 
                            <td id=corpoSimples> <%=monitor.getName()%> </td>
                            <td id=corpoSimples> <a href=../email/index.jsp?userID=<%=monitor.getID() %>> 
                              <img src="../sources/images/email.gif" border=0 width="20" height="20" alt="Enviar Email para o monitor <%=monitor.getName()%>"> 
                              </a> </td>
                            <td id=corpoSimples> 
                              <%					
                        if (masterPermission){		%>
                              <a href=../servlet/br.ufal.graw.web.user.DeleteMonitor?userID=<%=monitor.getID() %> > 
                              <img src="../sources/images/deleteUser.gif" border=0 width="20" height="20" alt="Remover este monitor"></a> 

<%	 			  }	%>
                            </td>
                          </tr>
 <%
						}
					}							
						
					Vector professors = course.getProfessors();
							if ( professors.size() > 0 ){
								User professor;	%>
                          <tr> 
                            <td id=headerSimplesFont colspan=3 > Professores</td>
                          </tr>
								
                          <%
								for (int i=0 ; i< professors.size() ; i++){
									professor = (User)professors.get(i);	%>
                          <tr> 
                            <td id=corpoSimples> <%=professor.getName()%> </td>
                            <td id=corpoSimples colspan=2> <a href=../email/index.jsp?userID=<%=professor.getID() %>> 
                              <img src="../sources/images/email.gif" border=0 width="20" height="20" alt="Enviar Email para o professor <%=professor.getName()%>"> 
                              </a> </td>
                          </tr>
                          <%
								}
							}
							
							%>						  						  
                          <tr> 
                            <td id=headerSimplesFont colspan="3"><a href="information.jsp">Mais 
                              informa&ccedil;&otilde;es</a></td>
                          </tr>
                        </table>
					</div>
			  </td>
            </tr>
			<%			
			if (masterPermission){
					Vector students = course.getStudents();
					Student student;	
					if (students.size() > monitors.size() ) {%>
						<tr >											              
			              <td id=headerSimples colspan="2"> Eleja aqui os monitores desse 
	        		       curso. </td>
						</tr>
						<tr>
							<td id=corpoSimples colspan="2">							
				                <form action="../servlet/br.ufal.graw.web.user.ElectMonitor" method="POST">
	            			      <select name="userID"><%
										for (int i=0 ; i< students.size() ; i++){
											student = (Student)students.get(i);
											if ( ! student.isMonitor(course)){	%>
												<option value="<%=student.getID()%>">
													<%=student.getName()%> - <%=student.getMatriculation() %>
												</option>	<%
											}
										}	%>
									</select>
									<br><br>
									<div align="left">
										<input type="submit" value="Eleger" class="botao">
									</div>
								</form>
							</td>
						</tr>	<%
					}				
			 }	%>
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
