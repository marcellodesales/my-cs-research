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
<%@ page import="br.ufal.graw.Student" %>
<%@ page import="br.ufal.graw.Professor" %>
<%@ page import="br.ufal.graw.Course "%>
<%@ page import="br.ufal.graw.ExtraCourse "%>
<%@ page import="br.ufal.graw.Discipline "%>
<%@ page import="br.ufal.graw.exception.CourseNotFoundException "%>
<%
boolean canSendEmail = false;
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
                    <td><!-- #BeginEditable "information" --> Clique 
          no nome de um dos cursos para acessar seus servi�os e informa��es. <br>
          <BR>
          <center>
            <TABLE class=tabelaSimples cellSpacing=0 cellPadding=3 width="61%">
              <TBODY> 
             
              <%  
			  	String	 color;
				Enumeration courses = null;
				Course courseItem;
				Vector disciplines = new Vector();
				Vector  extraCourses = new Vector();
				
				courses = user.getCourses();
				while (courses.hasMoreElements()){			
						courseItem= (Course)courses.nextElement();
						if (courseItem instanceof ExtraCourse){
							extraCourses.add(courseItem);
						}else if (courseItem instanceof Discipline){
							disciplines.add(courseItem);
						}else{
							throw new CourseNotFoundException("Curso de origem desconhecida.");
						}
				}
				%>
				<!--	Imprime primeiro os cursos extra curriculares  -->
				<TR> 
                	
                <TD id=headerSimples height="0"><b>Cursos Extra-Curricular:</b></TD>
    	        </TR>
				<%
				if ( extraCourses.size() > 0 ){
					for (int i=0 ;  i < extraCourses.size() ; i++){
						courseItem = (Course)extraCourses.get(i) ;
						color = ((i % 2)== 0) ? "ffffff" : "f5f5f5";	%>
						
						<TR onmouseover="mOvr(this, '#e0e0e0'); " onmouseout="mOut(this, '#<%= color %>'); "  bgColor=#<%= color %>>
		    	            <TD height="0">&nbsp;<img src="../sources/images/seta.gif" width="11" height="11"> 
        			          <a  class="Link" href="../servlet/br.ufal.graw.web.course.OpenCourse?courseID=<%= courseItem.getID() %>"><%=courseItem.getName()%></a>
							  	<%
								canSendEmail = false;
							  	if (user instanceof Professor){
									if (	((Professor)user).teaches(courseItem)	){	
										canSendEmail = true;%>
										<img src="../sources/images/teaches.gif" >	<%									
									}	
								}else if (user instanceof Student){									
									if (	((Student)user).isMonitor(courseItem)	){
										canSendEmail = true;		%>
										<img src="../sources/images/monitor.gif" >	<%
									}
								}
								if (canSendEmail ){ %>
								<a href="../email/index.jsp?courseID=<%=courseItem.getID()%>">
								<img src="../sources/images/email.gif" border=0 ></a>
								<%
								}	%>
							</TD>
		              </TR>	<%
					}
				}else{	%>
					<TR >
		    	            <TD id=corpoSimples >
							Voc� n�o est� associado a nenhum curso Extra-Curricular
							</TD>
		              </TR>	<%
				}	%>
				
				<!--	Agora imprime as disciplinas  -->
				<TR> 
                	<TD id=headerSimples height="0"><b>Disciplinas:</b></TD>
    	        </TR>
				<%
				if ( disciplines.size() > 0 ){
					for (int i=0 ;  i < disciplines.size() ; i++){
						courseItem = (Course)disciplines.get(i) ;
						color = ((i % 2)== 0) ? "ffffff" : "f5f5f5";	%>
						
						<TR onmouseover="mOvr(this, '#e0e0e0'); " onmouseout="mOut(this, '#<%= color %>'); "  bgColor=#<%= color %>>
		    	            <TD height="0">&nbsp;<img src="../sources/images/seta.gif" width="11" height="11"> 
        			          <a  class="Link" href="../servlet/br.ufal.graw.web.course.OpenCourse?courseID=<%= courseItem.getID() %>"><%=courseItem.getName()%></a>
							  	<%
								canSendEmail = false;
							  	if (user instanceof Professor){
									if (	((Professor)user).teaches(courseItem)	){
										canSendEmail = true;	%>
										<img src="../sources/images/teaches.gif" >	<%									
									}	
								}else if (user instanceof Student){									
									if (	((Student)user).isMonitor(courseItem)	){	
										canSendEmail = true;	%>
										<img src="../sources/images/monitor.gif" >	<%
									}
								}
								
								if (canSendEmail ){ %>
								<a href="../email/index.jsp?courseID=<%=courseItem.getID()%>"  >
								<img src="../sources/images/email.gif" border=0 ></a>
								<%
								}	%>
							</TD>
		              </TR>	<%
					}
				}else{	%>
					<TR >
		    	            <TD id=corpoSimples >
							Voc� n�o est� associado a nenhuma disciplina.
							</TD>
		              </TR>	<%
				}	%>
				<tr>
				<td>&nbsp;</td>
				</tr>
				<tr>
				<td id=headerSimples>Legendas</td>
				</tr>
								<tr>
				<td><img src="../sources/images/teaches.gif" > - Leciona<br>
				<img src="../sources/images/monitor.gif" > - Monitora
				</td>
				</tr>

              </TBODY> 
            </TABLE>
          </center>
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
