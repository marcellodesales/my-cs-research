<%@ page isErrorPage="true"%>
<%@ page import="br.ufal.graw.web.Mail" %>
<%@ page import="br.ufal.graw.User" %>

<%
	User user = null;
try{
	String message, userNameID = "Não há usuário logado"; 

	
	message = request.getParameter("message");
	if (message==null) message = "Erro não previsto";
	try{
		user = ((User)session.getAttribute("user"));
		userNameID = ". Usuário: "+user.getName()+ ". ID='"+user.getID()+"'";
	}catch(Exception e){
	}
	message+=userNameID;
	if (user!=null){
		if (exception==null){
			Mail.log(message);
		}else{
			Mail.log(exception,message);
		}	
	}
}catch(Exception e){
	e.printStackTrace();
}
%>
<html><!-- #BeginTemplate "/Templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Erro</title>
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
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" --><!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" -->
		  <br>
		  <div align="center">
          
        <table width="50%" border="0">
	  <tr> 
	    <td id="headerSimples" rowspan="2" ><img src="../sources/images/boneco.jpg" width="138" height="150"></td>
	    <td id="headerSimples" > 
	      <div align="center"><%if (user!=null){%>Ocorreu um erro no nosso servidor!<%}else{%>A sua sessão expirou, ou você não está logado.<%}%><br>
		Sugerimos que voc&ecirc; se logue novamente no sistema.</div>
	    </td>
	  </tr>
	  <tr> 
	    <td id="corpoSimples" > 
	      <div align="center"><b><%if (user!=null){%>Estaremos trabalhando para corrigir esse 
		erro o quanto antes.<%}%></b></div>
	    </td>
	  </tr>
	</table>
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
