<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW</title>
<%session.setAttribute("userType",request.getParameter("userType") ); %>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="21"><img src="../../sources/images/teste2.gif" width="770" height="100" usemap="#Map" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Pr&eacute; Cadastro de Estudantes<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --> 
      <p><br>
      </p>
      <p><a href="newStudentsSet.jsp">Conjunto de Alunos</a><br>
        Conjunto de alunos no formato XML ou TXT;<br>
        <br>
        [TXT]<br>
        Nome Usu&aacute;rio WebMail, matr&iacute;cula</p>
      <p>[ XML ]<br>
        &lt;Departamento&gt;<br>
        &lt;student id=Matr&iacute;cula&gt;<br>
        &lt;name&gt;Nome&lt;/name&gt;<br>
        &lt;username&gt;nome de usu&aacute;rio do imp&lt;/username&gt;<br>
        &lt;/student&gt;<br>
        ....<br>
        ....<br>
        &lt;/Departamento&gt; <br>
        <br>
        Novo Aluno<br>
        Novo Professor</p>
      <p><br>
        <br>
        <br>
      </p>
      <!-- #EndEditable --> 
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"><font color="#FFFFFF">® 2002. Todos 
      os direitos reservados - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o 
      - UFAL</font></td>
  </tr>
</table>
<map name="Map"> 
<%// String content = request.getServletContext().getRealPath("/")+"graw";
 //if (!content.equals()) %>
  <area shape="circle" coords="573,86,11" alt="Página principal" title="Informa&ccedil;&otilde;es gerais sobre o GraW" href="/"> 
  <area shape="circle" coords="630,85,11" href="../../info.jsp" alt="Informa&ccedil;&otilde;es gerais sobre o GraW" title="Informa&ccedil;&otilde;es gerais sobre o GraW">
  <area shape="circle" coords="690,85,12" href="../../signIn/signInNewUser.jsp" alt="Cadastros" title="Cadastros">
  <area shape="circle" coords="742,85,11" href="../../help.html" alt="Ajuda" title="Ajuda">
</map>
</body>
<!-- #EndTemplate --></html>
