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
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufal.graw.AbstractUser" %>
<%@ page import="br.ufal.graw.Student" %>
<%@ page import="br.ufal.graw.AbstractCommunity" %>
<%@ page import="br.ufal.graw.AbstractCourse" %>
<%@ page import="br.ufal.graw.web.SendMail" %>
<%
/* 
	Pode receber como parametros: UserID.
	Significando o destino.
	subjectID = 110 => propor Link | 101 => propor document 
	                
*/	
	String userID  =	request.getParameter("userID");
	String subjectID = request.getParameter("subjectID");
    
	String subject = "";
	boolean isToAdmin = false;
	boolean isToGrawAdministrators = false;
	if (subjectID != null){
		if (subjectID.equals("110")) {
			subject = "Proposta de um novo link para a comunidade "+community.getTitle();
			isToAdmin = true ;
		}else  if (subjectID.equals("101") ){
			subject = "Proposta de um novo documento para a comunidade "+community.getTitle();
			isToAdmin = true ;
		}else  if (subjectID.equals("111") ){
			subject = "Mensagem para os administradores do graW ";
			isToGrawAdministrators = true;
		}
	}

	boolean isUser = (userID != null) && ! isToAdmin && ! isToGrawAdministrators ;
%>
<TITLE>graW - Gradua&ccedil;&atilde;o na Web - TCI/UFAL - Envio de Email </TITLE>
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
                              <div align="right"><!-- #BeginEditable "directory" --><b>Comunica&ccedil;&atilde;o 
								&#149; Email</b><!-- #EndEditable --></div>
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
                      <a href="index.jsp" class="Link" >Email</a></td>
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
                    <td><!-- #BeginEditable "information" --><br>
                      <table class=tabelaSimples cellspacing=0 cellpadding=3 width="90%" align=center>
                        <tr> 
                          <td id=headerSimplesFont height="0">Envio de email para 
                            a membros </td>
                        </tr>
                        <tr> 
                          <td height="0">Escolha primeiramente uma pessoa no campo 
                            <b>Para</b>, digite o <b>assunto</b> e o <b>corpo</b> 
                            da mensagem. Em seguida, clique em <b>Enviar Email</b>. 
                            <br>
                            <b>Opcionamente</b> &eacute; poss&iacute;vel enviar 
                            algum <b>Anexo</b>, bastando para isso escolher o 
                            arquivo clicando em procurar. Caso não deseje enviar 
                            nenhum anexo, simplesmente deixe o campo em branco. 
                            O tamanho máximo do anexo é de: <%=SendMail.getMaxSizeInKb()%> 
                            Kb.</td>
                        </tr>
                      </table>
                      <BR>
                      <form  method="post" enctype="multipart/form-data" name="mailSender" action="<%=servletDir+"br.ufal.graw.web.SendMail"%>">
                        <table align=center id="corpoSimples"  width="90%" border="0" cellpadding="3" cellspacing="0" class="tabelaSimples">
                          <TR> 
                            <td id=headerSimplesFont colspan=2> <img src="../sources/images/email2.gif" width="30" height="11"> 
                              Email</td>
                          </tr>
                          <tr> 
                            <td id="headerSimples" width="13%">De: </td>
                            <td  id="corpoSimples" width="87%"><%=user.getName()%></td>
                          </tr>
                          <tr> 
                            <td  id="headerSimples" width="13%">Para:</td>
                            <td id="corpoSimples" width="87%"> 
                              <%
			if (isUser ){		
				User  to = AbstractUser.getRealUser(userID, database) ;	           %>
                              <input type="hidden" name="destinationID" value="<%=userID %>">
                              <%=to.getName()%> 
                              <input type="hidden" value="userID" name="type">
                              <%	
			 }else if (isToGrawAdministrators){	%>
                              <input type="hidden" name="destinationID" value="graw@tci.ufal.br">
                              Administradores do graW 
                              <input type="hidden" value="admin" name="type">
                              <%
			} else {
				Community  to = AbstractCommunity.getRealCommunity(community.getID(), database) ;
				if (isToAdmin){
 %>
                              <input type="hidden" name="destinationID" value="<%= to.getResponsible().getID() %>">
                              Responsável <%= to.getResponsible().getName() %> 
                              <input type="hidden" value="userID" name="type">
                              <%				} else {  	%>
                              <input type="hidden" name="destinationID" value="<%= to.getID() %>">
                              Toda a comunidade <%=to.getTitle()%> 
                              <input type="hidden" value="communityID" name="type">
                              <%				}
         }     %>
                              <% if (community != null){ %>
                              <a href="../community/users.jsp"> <img src="../sources/images/Users.gif" width="20" height="20" border=0 alt="Selecionar outro membro!"></a> 
                              <%}%>
                            </td>
                          </tr>
                          <tr> 
                            <td  id="headerSimples" width="13%">Assunto:</td>
                            <td id="corpoSimples"  width="87%"> 
                              <input type="text" name="subject" maxlength="50" size="45" value="<%= subject %>">
                            </td>
                          </tr>
                          <tr> 
                            <td id=headerSimples width="13%"> <b>Anexo:</b> </td>
                            <td width="87%"> 
                              <p> <img src="../sources/images/docUpload.gif" width="16" height="16" alt="Anexar Documento"> 
                                <input type="file" name="file" size="45">
                              </p>
                            </td>
                          </tr>
                          <tr> 
                            <td  id="headerSimples" colspan="2">Mensagem:</td>
                          </tr>
                          <tr> 
                            <td id="corpoSimples" colspan="2"> 
                              <div align="center"> 
                                <textarea name="mailContents" rows="8" cols="50"></textarea>
                              </div>
                            </td>
                          </tr>
                          <tr> 
                            <td id="corpoSimples" colspan="2"> 
                              <div align="center"> 
                                <input type="submit" name="Submit" value="Enviar Email" class="botao">
                                <input type="reset" name="Submit2" value="Limpar" class="botao">
                              </div>
                            </td>
                          </tr>
                        </table>
                        <div align="center"></div>
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
		  Tecnologia da Informa&ccedil;&atilde;o - UFAL   -   <a href=index.jsp?subjectID=111>graw@tci.ufal.br</a></div>
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
