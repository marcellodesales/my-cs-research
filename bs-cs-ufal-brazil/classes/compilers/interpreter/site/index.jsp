<%@ page import="br.ufal.tci.interpreter.Interpreter" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Hashtable" %>
<%
String servletAddress = "http://www.graw.tci.ufal.br/servlet/";
String calculatePostfixFormServlet = servletAddress + "br.ufal.tci.interpreter.web.CalculatePostfixForm";
String SetRValueVariablesServlet = servletAddress + "br.ufal.tci.interpreter.web.SetRValueVariables";
%>
<html>
<head>
<title>Interpretador de expressões </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<p align="center"><b>Universidade Federal de Alagoas<br>
  Departamento de Tecnologia da Informa&ccedil;&atilde;o<br>
  Ci&ecirc;ncia da Computa&ccedil;&atilde;o<br>
  Macei&oacute;-Alagoas-Brasil</b><br>
  <br>
  <b>Trabalho de Projeto e Teoria de Linguagens</b><br>
  Interpretador e montador (pseudo-assembler)<br>
  Professor Rosfran Lins Borges<br>
  <br>
  <b><a href="mailto:marcellojunior@hotmail.com">Marcello Alves de Sales Junior</a></b><br>
  <br>
</p>
<table width="65%" border="0" align="center">
  <tr>
    <td><b>Resumo: </b><i>Implementa&ccedil;&atilde;o de uma interpretador proposto 
      pelo livro do <a href="http://info.astrian.net/jargon/terms/d/Dragon_Book.html">drag&atilde;o</a>, 
      para transforma&ccedil;&atilde;o de express&otilde;es na forma infixa para 
      p&oacute;sfixa, al&eacute;m de implementa&ccedil;&otilde;es adicionais como 
      o assembler, que mostrar&aacute; a representa&ccedil;&atilde;o da pilha 
      de mem&oacute;ria usada para calcular o valor final da express&atilde;o 
      al&eacute;m do suporte a express&otilde;es com vari&aacute;veis L-Value 
      e R-value.</i></td>
  </tr>
</table>
<% 			String message = (String)session.getAttribute("message");
			if (message != null){ 
				out.println("<center><font color=red><b>"+message+"</font></b></center>");
				session.removeAttribute("message");
			}
%>
<p>1. Entrada de dados<br>
</p>
<table width="75%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33%" height="30" valign="top">Digite uma express&atilde;o 
      na forma infixa: </td>
    <td width="67%" align=middle height="30"> 
      <form name="form1" method="post" action="<%= calculatePostfixFormServlet %>">
		<input type="text" name="infixExpression" size="70">
        <input type="submit" name="Submit" value="Calcular">
        <input type="reset" name="Submit2" value="Apagar">
      </form>	      
    </td>
  </tr>
</table>
<p>Exemplo<br>
  a) Sem agrupamento:<b> 3 + 4 * 5 </b><br>
  b) Agrupamento: <b>(((2+3)/2)+((2-4)/2)) </b><br>
  c) Vari&aacute;veis (L-Values): <b>x = (3 + 8 * (7 -5)) * 3 - 4</b><br>
  d) Vari&aacute;veis (L-Values e R-Values): <b>x = a * 5 + (b - 2)</b> <br>
  Obs: Interpretador implementado com suporte a classifica&ccedil;&atilde;o de 
  preced&ecirc;ncia de operadores.<br>
  <br>
</p>
<br>
<%
Interpreter interpreter = (Interpreter)session.getAttribute("interpreter");
if (interpreter != null){
		
		out.println("<b>Expressão Infixa:</b> "+interpreter.getInfixNotation()+"<BR>");  
	    out.println("<b>Expressão Pósfixa:</b> "+interpreter.getPostfixNotation()+"<BR>");        
		String hasGivenRValues = (String)session.getAttribute("hasGivenRValues");
		
		if ((interpreter.getNumberOfRValues() > 0) && (hasGivenRValues == null)){
			String[] rvalueVar = interpreter.getRValuesNeeded(); %>
			<form method="post" action="<%= SetRValueVariablesServlet %>">			
<table align="center">
<tr><td colspan=2>Digite o valor da variável RValue</td></tr>
<%			for (int i=0; i < rvalueVar.length; i++){   %>
	<tr><td>Variável <%= rvalueVar[i] %></td><td><input type="text" name="<%= rvalueVar[i] %>"></td></tr>
<%				
			}
%>			
<tr><td colspan=2 align=right><input type="submit" name="Submit" value="Calcular">
	        <input type="reset" name="Submit2" value="Apagar"></td></tr>
</table>
			</form>			
<%		} else {
    	    // calculate
			double resultValue = interpreter.calculate(interpreter.getPostfixNotation());
		
%>		
		<table>
			<tr><td>Montador - Assembler</td></tr>
<%			
			Vector assembler = interpreter.getAssembler();
			for (int i = 0; i < assembler.size(); i++){
				String assemblerPart = (String)assembler.get(i);
%>			<tr><td><%= assemblerPart %></td></tr>				
<%
			}
%>       </table>
<%		
			Hashtable tableIni = interpreter.getInicialTableOfSymbols();
			Hashtable tableEnd = interpreter.getEndTableOfSymbols();
			
			out.println("<BR><BR>%%%% Tabela de memória inicial de Valors %%%%");
			Enumeration colNamesTI = tableIni.keys();
			while(colNamesTI.hasMoreElements()){
				String variableName = (String)colNamesTI.nextElement();
				String variableValue = (String)tableIni.get(variableName);
				out.println("<BR>["+variableName+"] = ["+variableValue+"]");
			}
		
			out.println("<BR><BR>%%%% Tabela de memória final de Valors %%%%)");
			Enumeration colNamesFI = tableEnd.keys();
			while(colNamesFI.hasMoreElements()){
				String variableName = (String)colNamesFI.nextElement();
				String variableValue = (String)tableEnd.get(variableName);
				out.println("<BR>["+variableName+"] = ["+variableValue+"]");
			}
				
			String printValue = "";
			if (!interpreter.getLValue().equals("")){
				printValue = interpreter.getLValue() + " = " + resultValue;
			} else printValue = String.valueOf(resultValue);

        	out.println("<BR><BR>Valor da expressão é <b>"+ printValue+"</b>");
			session.removeAttribute("interpreter");
			session.removeAttribute("hasGivenRValues");
		}			
}
%>
<p align=right>
April 25, 2002
</p>
</body>

</html>
