<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page errorPage="/status/errorGeneric.jsp" %>
<%
Administrator admin 	= (Administrator) session.getAttribute("admin");

if (admin==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Por favor logue-se novamente.");
	session.invalidate();
}else{%>
<html>
<head>
<title>Adicionar Usu&aacute;rio</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript1.2">
<!--
	
	function checkMatriculation(){
		var who = window.document.formCreateNewUser.radios[0].checked;
		if (who == true){
			/* Validate the Student´s Matriculation*/
			if ( window.document.formCreateNewUser.matriculation.value.length == 12 ){
				return true;
			}else{
				window.alert("O Campo da Matrícula do estudante está incorreto. ");
				return false;
			}
		}else{
			/* Validate the Professor´s Matriculation*/
			if ( window.document.formCreateNewUser.matriculation.value.length == 7 ){
				return true;
			}else{
				window.alert("O Campo da Matrícula do professor está incorreto.");
				return false;
			}
		}
	}
	
	function checkPassword(){
		var passwd_1 =	window.document.formCreateNewUser.password.value;
		var passwd_2 =	window.document.formCreateNewUser.rePassword.value;
		
		if ((passwd_1 != passwd_2)||( (passwd_1=="") || (passwd_2=="") )){
			window.alert("A senhas digitadas estão diferentes ou vazias.");
			return false;
		}else{
			return true;
		}
	}
	
	function validateForm(){
		if (checkPassword()){
			return checkMatriculation();
		}
		return false;
	}
// -->
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
Adicionar Usu&aacute;rio<br>
<form name="formCreateNewUser"	onSubmit="return validateForm();"
										method="post"
									   	action="/servlet/br.ufal.graw.web.administrator.CreateNewUser
									   " >
  <table width="64%" border="1">
    <tr>
      <td width="26%" height="33">Matricula:</td>
      <td width="74%" colspan="2" height="33">
        <input type="text" name="matriculation" maxlength="12" size="12">
      </td>
    </tr>
    <tr>
      <td width="26%">Nome:</td>
      <td width="74%" colspan="2">
        <input type="text" name="name" maxlength="50" size="50" value=" ">
      </td>
    </tr>
    <tr>
      <td width="26%">Email:</td>
      <td width="74%" colspan="2">
        <input type="text" name="email" size="30" maxlength="30">
      </td>
    </tr>
    <tr>
      <td width="26%">Senha:</td>
      <td width="74%" colspan="2">
        <input type="password" name="password" size="10" maxlength="10">
      </td>
    </tr>
    <tr>
      <td width="26%">Redigite a senha:</td>
      <td width="74%" colspan="2">
        <input type="password" name="rePassword" size="10" maxlength="10">
      </td>
    </tr>
    <tr>
      <td>Tipo de Usu&aacute;rio:</td>
      <td>
        <input type="radio" name="radios" value="student" checked>
        Estudante </td>
      <td>
        <input type="radio" name="radios" value="professor">
        Professor </td>
    </tr>
    <tr>
      <td colspan="3">
        <div align="center">
          <input type="submit" name="Submit" value="Cadastrar">
        </div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
<%}%>
