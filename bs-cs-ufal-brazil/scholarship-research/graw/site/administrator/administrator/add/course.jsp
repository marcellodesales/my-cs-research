<%@ page import="br.ufal.graw.Administrator" %>
<%@ page import="br.ufal.graw.web.ServletUtility" %>
<%@ page errorPage="/status/errorGeneric.jsp" %>
<%
Administrator admin 	= (Administrator) session.getAttribute("admin");
if (admin==null){
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Por favor logue-se novamente.");
	session.invalidate();
}else{ %>
<html>
<head>
<title>Adicionar Disciplina</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript1.2">
<!--
	function checkDisciplineCode(disciplineCode){
		/*
		*	\d Matches digits 0 through 9 
		* 			/Navigator \d/ matches "Navigator 3" but not "Navigator A" 
		*	\D Matches only nonnumeric characters 
		*			/\Navigator \D/ matches "Navigator A" but not "Navigator 3" 
		*/
		var regularExpression = /\D\D-\d\d\d/ ;
		var found = disciplineCode.match(regularExpression);
		if (!found){
			window.alert("Código está incorreto.");
			return false;
		}else{
			return true;
		}	
	}
	function validateForm(){
		var disciplineCode = window.document.formCreateNewDiscipline.disciplineCode.value;
		return checkDisciplineCode(disciplineCode);			
	}
// -->
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<p>Adicionar Disciplina<br>
</p>
<div align="center">
  <form name="formCreateNewDiscipline" onSubmit="return validateForm();" 
  									   method="post"
									   action="/servlet/br.ufal.graw.web.administrator.CreateNewDiscipline" >
    <table width="50%" border="1">
      <tr> 
        <td width="28%">C&oacute;digo:</td>
        <td width="72%"> 
          <input type="text" name="disciplineCode" maxlength="6" size="6">
        </td>
      </tr>
      <tr> 
        <td width="28%">Nome:</td>
        <td width="72%"> 
          <input type="text" name="disciplineName" maxlength="40" size="40" value=" ">
        </td>
      </tr>
      <tr> 
        <td width="28%">Descri&ccedil;&atilde;o:</td>
        <td width="72%"> 
          <textarea name="disciplineDescription" rows="5" cols="55"></textarea>
        </td>
      </tr>
      <tr> 
        <td colspan="2"> 
          <div align="center">
            <input type="submit" name="Submit" value="Cadastrar">
          </div>
        </td>
      </tr>
    </table>
	</form>
</div>
<p>&nbsp; </p>
</body>
</html>
<%}%>