<%@ page import="br.ufal.graw.Administrator "%>
<%@ page import="br.ufal.graw.web.ServletUtility "%>
<% 
Administrator admin = (Administrator) session.getAttribute("admin");
if (admin==null){
	session.invalidate();
	ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Você não está logado, ou sua sessão expirou.");	
}else{ 
%>	
	<html>
	<head>	
	<title>Ferramentas de Configura&ccedil;&atilde;o do Administrador</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script language="JavaScript">
		<!--
			/*
			* Go To Page selected in formMenu.
			* Programmed by: Rodrigo Paes - noNamePibic.
			*/
			function goToPage(){
				var index		= window.document.formMenu.menuOperation.selectedIndex;
				var operation 	= window.document.formMenu.menuOperation.options[index].value;
				index		   	= window.document.formMenu.menuOperand.selectedIndex;
				var operand 	= window.document.formMenu.menuOperand.options[index].value;
				var address = "./"+operation+"/"+operand+".jsp";				
				window.open(address,"center");
			}
		// -->
	</script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000">	
	<form name="formMenu" method="post" onSubmit="	goToPage();
													return false;">
	  <table width="41%" border="1">
	    <tr> 
	      <td><b><i>Op&ccedil;&otilde;es:</i></b></td>
	      <td> 
	        <select name="menuOperation">
          <option value="add">Cadastrar</option>
          <option value="del" selected>Deletar</option>          
		  <option value="set" selected>Configurar</option>
        </select>
	      </td>
	      <td> 
	        <select name="menuOperand">	          
	          <option value="user">Usuário</option>
	          <option value="course">Curso</option>
	        </select>
	      </td>
	      <td> 
	        <input type="submit" name="Submit" value="      Ir       ">
	      </td>
	    </tr>
	  </table>
	</form>
	</body>
	</html>
<%}%>