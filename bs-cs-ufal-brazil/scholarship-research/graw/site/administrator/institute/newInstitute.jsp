<html><!-- #BeginTemplate "/Templates/administration.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Administração Online GraW</title>
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
    <td height="21"><font color=white size=2 face=verdana><b>Administração Online - <!-- #BeginEditable "title" -->Cria&ccedil;&atilde;o de nova Institui&ccedil;&atilde;o<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276">
          <!-- #BeginEditable "centro" --> 
      <p><br>
        Preencha o formul&aacute;rio abaixo das informa&ccedil;&otilde;es da Institui&ccedil;&atilde;o. 
        Ap&oacute;s a cria&ccedil;&atilde;o &eacute; necess&aacute;rio associar 
        um administrador. Este deve ser escolhido pelo administrador geral do 
        graW. </p>
		
      <form name="newInstitute" action="newInstitute2.jsp" method="POST">
        <table width="520" border="1" align="center" cellpadding="1" cellspacing="0">
        <tr> 
          <td colspan="4"><b>Nova Institui&ccedil;&atilde;o</b></td>
        </tr>
        <tr> 
          <td width="85"><b>Nome:</b></td>
          <td colspan="3">
              <input type="text" name="instituteName" size="70">
          </td>
        </tr>
        <tr> 
          <td width="85"><b>Abrevia&ccedil;&atilde;o:</b></td>
          <td width="137"> 
              <input type="text" name="instituteAbbreviation" size="10">
          </td>
          <td width="56"><b>Estado:</b></td>
          <td width="266">&nbsp;  
              <select name='stateID' style="background-color: #FFCC00">
                <option value='1'>Acre</option>
	<option value='2' SELECTED>Alagoas</option>
	<option value='3'>Amapá</option>
	<option value='4'>Amazonas</option>
	<option value='5'>Bahia</option>
	<option value='6'>Ceará</option>
	<option value='7'>Distrito Federal</option>
	<option value='8'>Espírito Santo</option>
	<option value='9'>Goiás</option>
	<option value='10'>Maranhão</option>
	<option value='11'>Mato Grosso</option>
	<option value='12'>Mato Grosso do Sul</option>
	<option value='13'>Minas Gerais</option>
	<option value='14'>Pará</option>
	<option value='15'>Paraíba</option>
	<option value='16'>Paraná</option>
	<option value='17'>Pernambuco</option>
	<option value='18'>Piauí</option>
	<option value='19'>Rio de Janeiro</option>
	<option value='20'>Rio Grande do Norte</option>
	<option value='21'>Rio Grande do Sul</option>
	<option value='22'>Rondônia</option>
	<option value='23'>Roraima</option>
	<option value='24'>Santa Catarina</option>
	<option value='25'>São Paulo</option>
	<option value='26'>Sergipe</option>
	<option value='27'>Tocantins</option>
</select>		  
		  </td>
        </tr>
        <tr> 
          <td width="85"><b>Pa&iacute;s:</b></td>
            <td colspan="3">
              <select name='countryID' style="background-color: #FFCC00">
                <option value="1" SELECTED>Brasil</option>
                <option value="2">Exterior</option>
              </select>
            </td>
        </tr>
        <tr> 
          <td colspan="4"> 
            <div align="right">
              <input type="submit" value="Criar Nova!">
              <input type="button" name="cancel" value="Cancelar">
            </div>
          </td>
        </tr>
      </table>
	  </form>
      <p>&nbsp; </p>
      <p>&nbsp;</p>
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
