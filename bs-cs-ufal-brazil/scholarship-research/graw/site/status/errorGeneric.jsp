<%@ page isErrorPage="true"%>
<%
String message = "Erro desconhecido.";
if (exception==null){
	message = request.getParameter("message");	
}else{
	message = exception.getMessage();
}

if (message==null){
	message="Erro Desconhecido.";
}
%>

<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Erro.</title>
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
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Erro 
      de opera&ccedil;&atilde;o<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --><br>
		  <div align="center">
          <table width="50%" border="0">
            <tr> 
              <td id="headerSimples" class="tabelaSimples"> 
                <div align="center">A opera&ccedil;&atilde;o requisitada n&atilde;o 
                  pode ser realizada.</div>
              </td>
            </tr>
            <tr> 
              <td id="corpoSimples" class="tabelaSimples"><b>Mensagem do Erro:</b> 
                <%=message%> </td>
            </tr>
          </table>
		  
		  <br>
          <a href="javascript:history.back(1)" class="Link">Voltar</a> 
		  </div>
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
  <area shape="circle" coords="83,87,13" href="../signIn/index.jsp">
  <area shape="circle" coords="142,85,14" href="../info.jsp">
  <area shape="circle" coords="195,85,12" href="../help.html">
</map>
</body>
<!-- #EndTemplate --></html>
