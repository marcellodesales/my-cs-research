<%@ page import="br.ufal.graw.web.site.SiteResource" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<%@ page import="br.ufal.graw.Institute" %>
<% 
DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
if (database == null){
database = new DatabaseLayer();
session.setAttribute("database",database);
}

%>
<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao Gr@W! - Cadastro de novo usuário</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="0" width="0"><img src="../sources/images/gr101.jpg" width="306" height="100"><img src="../sources/images/gr102.jpg" width="242" height="100"><img src="../sources/images/gr103.jpg" width="222" height="100" usemap="#Map2" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Cadastro 
      de novo usu&aacute;rio<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" -->
      <table width="95%" border="0" align="center">
        <tr> 
          <td width="45%" valign="top"><br>
            <font size="3"><b>Usu&aacute;rios Acad&ecirc;micos</b></font><br>
            <br>
            Bem vindo ao cadastro do ambiente graW. Esta p&aacute;gina de cadastro 
            destina-se a usu&aacute;rios acad&ecirc;micos que possuam suas respectivas 
            institui&ccedil;&otilde;es cadastradas. Se sua institui&ccedil;&atilde;o 
            ainda n&atilde;o est&aacute; cadastrada, contate os administradores 
            do graw atrav&eacute;s do email <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a> 
            para maiores esclarecimentos. Vale a pena alertar que <i>o graW encontra-se 
            em fase de teste</i>. Se sua institui&ccedil;&atilde;o est&aacute; 
            cadastrada e seu departamento ou curso acad&ecirc;mico n&atilde;o, 
            contate o administrador pelo email acima. <br>
            <br>
            Escolha sua institui&ccedil;&atilde;o no menu abaixo e siga as instru&ccedil;&otilde;es. 
            Se voc&ecirc; tiver alguma d&uacute;vida, veja nossa <a href="../help.html">p&aacute;gina 
            de ajuda</a>.<br>
            <br>
			<form action="signNewAcademic1.jsp" method="POST">
            <select name='instituteID' style="background-color: #FFCC00">
              <%
		Institute[] institutes = SiteResource.getInstitutes(database);
		for (int i=0; i < institutes.length; i++){ %>
              <option value='<%=institutes[i].getID()%>'><%=institutes[i].getName()%></option>
              <%		}
%>
            </select>
              <input type="submit" name="envia" value="Seguir" class="bagenda">
            </form>			
          </td>
          <td width="55%" valign="top"> <BR>
            <img src="../sources/images/studentsonlab.jpg" align=right><font size="3"><b>Usuários 
            em geral</b></font><br>
            <br>
            Além da participação de usuários acadêmicos, o graW permite a interação 
            de usuários não inseridos em instituições, como convidados e interessados 
            em qualquer comunidade acadêmica pública oferecida pelos próprois 
            usuários. Portanto, caso você seja um usuário não acadêmico, você 
            poderá utilizar outro formulário de cadastro, <a href="externalUser.jsp">clicando 
            aqui</a>.<br>
            <br>
            Para maiores esclarecimentos, estaremos sempre a sua disposi&ccedil;&atilde;o 
            no email <a href="mailto:graw@tci.ufal.br">graw@tci.ufal.br</a>.<br>
            <br>

          </td>
        </tr>
      </table>
      <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o - UFAL</font></div>
    </td>
  </tr>
</table>
<map name="Map2"> 
  <area shape="circle" coords="25,85,12" href="/">
  <area shape="circle" coords="83,87,13" href="index.jsp">
  <area shape="circle" coords="142,85,14" href="../info.jsp">
  <area shape="circle" coords="195,85,12" href="../help.html">
</map>
</body>
<!-- #EndTemplate --></html>
