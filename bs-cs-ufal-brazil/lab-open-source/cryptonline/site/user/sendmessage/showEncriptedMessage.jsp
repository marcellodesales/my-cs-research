 
<%@ page import="br.ufal.rsacripto.UseCase" %>
<%@ page import="br.ufal.rsacripto.User" %>
<%@ page import="java.util.Iterator" %>
<%
try{
	User user = (User)session.getAttribute("user");	
	if (user == null){
		throw new Exception("Sessão inválida");
	}
	Iterator it = (Iterator)session.getAttribute("sendMessageLog");	
	String chriptedMessage = (String)session.getAttribute("chriptedMessage");
%>
<html><!-- #BeginTemplate "/Templates/user.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>CriptOnline - Mensagens criptografadas com RSA</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="../../sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="../../sources/style/graw.css" type="text/css">
</head>
<body leftmargin="0" topmargin="0" background="../../sources/image/background.jpg">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td height="48" colspan="3"> 
      <p><b><font size="5">CriptOnline - <font size="4">Mensagens criptografadas 
        com <img src="../../sources/image/rsa.gif" width="100" height="43" align="absmiddle"></font></font></b></p>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="19" width="238"><font color=white size=2 face=verdana><b><%= user.getFirstName() %> 
      <%= user.getLastName() %></b></font></td>
    <td height="19" width="252"><font color=white size=1 face=verdana><b>Chave 
      p&uacute;blica: N=<%= user.getPublicKeyN() %> e E=<%= user.getPublicKeyE() %></b></font></td>
    <td height="19" width="266"><font color=white size=1 face=verdana><b>Chave 
      privada: N=<%= user.getPublicKeyN() %> e D=<%= user.getPrivateKeyD() %></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276" colspan="3"> <br>
      <table width="90%" border="0" align="center">
        <tr> 
          <td width="28%" valign=top><br>
            <a href="../generatekeys/generateNewRSA.jsp"><img src="../../sources/image/keys.gif" width="64" height="49" border="0" alt="Gerar novas chaves!"></a> 
            Chaves de Acesso<br>
            <br>
            <a href="sendMessage.jsp"><img src="../../sources/image/mailbinary.gif" width="60" height="35" border="0"></a> 
            Enviar Mensagem<br>
            <br>
            <a href="../receivemessage/receivemessage.jsp"><img src="../../sources/image/binarymail.gif" width="60" height="35" border="0"></a> 
            Receber Mensagem</td>
          <td width="72%" valign="top"><!-- #BeginEditable "userContent" -->
            <div align="center"><i><b><font size="2">Mensagem Criptografada Enviada!</font></b></i><br>
              <textarea name="textfield" cols="70" rows="7"><%= chriptedMessage %></textarea>
              <br>
              <i><b><font size="2">Calculos realizados!</font></b></i> <br>
              <textarea name="textfield" cols="70" rows="10">
              <%
				while (it.hasNext()){
					String logLine = (String)it.next();
					out.println(logLine);
				}
			%>			  
			  </textarea>			  
			</div>
            <!-- #EndEditable --></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr class="escuro"> 
    <td height="11" align="left" valign="top" colspan="3"> 
      <div align="center"><font color="#FFFFFF">® 2002. Marcello Junior - TCI 
        / Departamento de Tecnologia da Informa&ccedil;&atilde;o / UFAL</font></div>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
<%
} catch (Exception e){
	out.println("Erro!");
}
%>
