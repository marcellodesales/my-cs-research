<%@ page import="br.ufal.bibliweb.AbstractUser" %> 
<%@ page import="br.ufal.bibliweb.AcademicUser" %> 
<%@ page import="br.ufal.bibliweb.User" %> 
<%@ page import="br.ufal.bibliweb.Lend" %> 
<%@ page import="br.ufal.bibliweb.ComponentSet" %>
<%@ page import="br.ufal.bibliweb.DatabaseLayer" %>
<%@ page import="br.ufal.bibliweb.web.ServletUtility" %>
<%@ page import="br.ufal.bibliweb.exception.UserNotFoundException" %>
<% 
DatabaseLayer databaseLayer = (DatabaseLayer)session.getAttribute("database");
if (databaseLayer == null){
	databaseLayer = new DatabaseLayer(); 
	session = request.getSession(true);
	session.setAttribute("database", databaseLayer);
}

String userID = request.getParameter("userID");
User user = null;

user = AbstractUser.getRealUser(userID,databaseLayer);

boolean isAcademicUser = (user instanceof AcademicUser);
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Usuário da biblioteca BibliWEB!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td height="29" colspan="2"> 
      <p><b><img src="../../sources/images/bibliweb.jpg" width="109" height="107" align="middle"><font size="5">Bibliweb 
        - <i>Biblioteca na Web</i></font></b></p>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="21" width="652"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Usu&aacute;rio 
      da biblioteca<!-- #EndEditable --></b></font></td>
    <td height="21" width="108">
      <div align="center"><a href="../index.jsp" style="color:#ffffff"><b>P&aacute;gina 
        inicial</b></a></div>
    </td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="2"> <!-- #BeginEditable "centro" --> 
      <% 			
	  		String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<BR><center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
      <br>
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="4">Dados de usu&aacute;rio<b></b></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="130" height="24"> <div align="right">&nbsp;<b>Grupo 
              de usuário</b></div></td>
          <td id=corpoSimples colspan="2" height="24">&nbsp;<%= user.getGroup().getDescription() %> </td>
          <% 	String image;
		  		boolean userHasImage = user.getPicture().equals("");
		  	if (userHasImage){ 
		  		image = "<font size=1>Adicione a foto no campo abaixo!</a>";
			 } else {
			 	image = "<img src='/bibliweb/sources/images/users/"+user.getPicture()+"'>";
			 }
		  %>
          <td id=corpoSimples rowspan="3" height="49">&nbsp;<%= image %></td>
        </tr>
        <% if (isAcademicUser){%>
        <tr> 
          <td id=corpoSimples width="130" height="24"> <div align="right"><b>&nbsp;Curso 
              Acadêmico</b></div></td>
          <td id=corpoSimples colspan="2" height="24">&nbsp;<%= ((AcademicUser)user).getAcademicCourse().getDescription()%> </td>
        </tr>
        <% } %>
        <tr> 
          <td id=corpoSimples width="130" height="24"> <div align="right"><b>&nbsp;Nome 
              Completo</b></div></td>
          <td id=corpoSimples colspan="2" height="24">&nbsp;<%= user.getName() %> </td>
        </tr>
        <tr> 
          <td id=corpoSimples width="130" height="24"> <div align="right"><b>&nbsp;CPF</b></div></td>
          <td id=corpoSimples width="314" height="24">&nbsp;<%= user.getCPF() %></td>
          <td id=corpoSimples width="64" height="24"> <div align="center"><b>&nbsp;Matr&iacute;cula</b></div></td>
          <td id=corpoSimples width="107" height="24">&nbsp;<%= ((AcademicUser)user).getRegistration() %></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="130" height="30"> <div align="right"><b>&nbsp;Endere&ccedil;o 
              Resedencial</b></div></td>
          <td id=corpoSimples width="314" height="30">&nbsp;<%= user.getHomeAddress() %></td>
          <td id=corpoSimples width="64" height="30"> <div align="center"><b>&nbsp;Telefone 
              1 </b></div></td>
          <td id=corpoSimples width="107" height="30">&nbsp;<%= user.getHomePhone() %></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="130" height="30"> <div align="right"><b>&nbsp;Endere&ccedil;o 
              Trabalho</b></div></td>
          <td id=corpoSimples width="314" height="30">&nbsp;<%= user.getWorkAddress() %></td>
          <td id=corpoSimples width="64" height="30"> <div align="center">&nbsp;<b>Telefone 
              2 </b></div></td>
          <td id=corpoSimples width="107" height="30">&nbsp;<%= user.getWorkPhone() %></td>
        </tr>
        <tr> 
          <td id=corpoSimples width="130" height="30"> <div align="right">&nbsp;<b>Email</b></div></td>
          <td id=corpoSimples width="314" height="30">&nbsp;<%= user.getEmail() %></td>
          <td id=corpoSimples width="64" height="30"> <div align="center">&nbsp;<b>Celular</b></div></td>
          <td id=corpoSimples width="107" height="30">&nbsp;<%= user.getCellPhone() %></td>
        </tr>
        <%		  	if (userHasImage){   %>
        <tr> 
          <td colspan="7" id=headerSimplesFont> <form method="post" enctype="multipart/form-data" action="<%= ServletUtility.SERVLET_DIR %>br.ufal.bibliweb.web.admin.UploadUserPhoto">
              <div align="right"> 
                <input type="hidden" name="userID" value="<%= user.getID() %>">
                <input type="file" name="file">
                <input type="submit" name="Submit" value="Submit">
              </div>
            </form></td>
        </tr>
        <%          } %>
      </table>
      <br>
      <table width="628" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td id=headerSimplesFont colspan="4">Realizar Empr&eacute;stimo</td>
        </tr>
        <tr> 
          <td id=corpoSimples width="199"> 
            <div align="right"><b>C&oacute;digo da C&oacute;pia deExemplar</b></div>
          </td>
          <td width="422" id=corpoSimples valign=center> 
            <form name="form1" method="post" action="../lends/doLend.jsp">
              Identificador: 
              <input name="exemplarCopyID" type="text">
               <input name="renterID" type="hidden" value="<%= user.getID() %>">
               <input name="clerkID" type="hidden" value="1019667750120">
              <input type="submit" name="Submit2" value="Realizar!">
            </form></td>
        </tr>
      </table>
      <br>
      <table width="630" border="0" align="center" cellpadding="1" cellspacing="1">
        <tr> 
          <td width="626" id=headerSimplesFont>Empr&eacute;stimos Realizados</td>
        </tr>
        <%
	Lend[] lends = ComponentSet.getLends(user,databaseLayer);
	if (lends.length > 0){
		for (int i=0; i < lends.length; i++){ %>
        <tr> 
          <td id=corpoSimples>&nbsp;&nbsp; <a href="../lends/lend.jsp?lendID=<%= lends[i].getID() %>"> 
            <%
		  	String devolvido = lends[i].getWasReturned() ? "Devolvido" : "Com o usuário";
		  %>
            <%= lends[i].getID()%> - <%= lends[i].getExemplarCopy().getAbstractExemplar().getTitle()%> - <%= devolvido %></a></td>
        </tr>
        <%		} 
 	} else {     %>
        <tr> 
          <td> <div align="center"><font color="red">&nbsp;&nbsp;Ainda não existem 
              Empréstimos para o usu&aacute;rio especificado!</font></div></td>
        </tr>
        <%		}      %>
        <tr> 
          <td id=headerSimplesFont>&nbsp;</td>
        </tr>
      </table>
      <BR>
      <center>
        <img src='http://localhost:8080/bibliweb/servlet/br.ufal.bibliweb.barcode.linear.UserBarCode?BARCODE=<%= user.getID() %>&BAR_HEIGHT=1&CODE_TYPE=CODE128&X=0.05&CHECK_CHAR=Y&CHECK_CHARINTEXT=Y&CODE128_SET=0&HEIGHT=75&WIDTH=210'>
        <BR>
        <br>
        <a href="listUsers.jsp">Lista de Usuários</a> 
      </center>
      <!-- #EndEditable --> </td>
  </tr>
  <tr class="escuro"> 
    <td height="11" align="left" valign="top" colspan="2"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o / Banco 
        de Dados II - UFAL</font></div>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
