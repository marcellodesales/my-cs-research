/**
 * CalculatePostfixForm.java
 *
 * @author Marcello de Sales
 */

package br.ufal.tci.interpreter.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

import br.ufal.tci.interpreter.*;

public class SetRValueVariables extends HttpServlet {
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException{
		System.out.println("testando o interpretador");
		String formPage = "http://www.graw.tci.ufal.br/marcellojunior/interpreter/";
		//String formPage = "http://localhost:8080/interpreter/";
		this.session = request.getSession(true);
		Interpreter interpreter = (Interpreter)session.getAttribute("interpreter");
		
		Enumeration parameters = request.getParameterNames();
		Enumeration parameterToGet = request.getParameterNames();;
			
		System.out.println("Tem RValors? "+(interpreter.getNumberOfRValues() > 0));
		
		String rvaluesVariables[] = interpreter.getRValuesNeeded();
		
		int quantParameters = 0;
		while (parameters.hasMoreElements()){
			String x = (String)parameters.nextElement();
			System.out.println(x);
			quantParameters++;
		}
		System.out.println("Quantidade de parâmetros passados: "+quantParameters);
		//Subtract 1 cause of the submit button
		if (rvaluesVariables.length != quantParameters-1){
			String message = "A quantidade de parêmetros está incorreta!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
			
		} else {
			System.out.println("Passou agora vai calcular");
			while(parameterToGet.hasMoreElements()){
				String parameterName = (String)parameterToGet.nextElement();
				System.out.println("Parametro analizado: "+parameterName);
				for (int i=0; i<rvaluesVariables.length; i++){
					System.out.println("Parametro = "+parameterName);
					if (rvaluesVariables[i].equals(parameterName)){
						String parameterValue = request.getParameter(parameterName);
						System.out.println("Valor Digitado = "+parameterValue);
						interpreter.setRValue(parameterName,parameterValue);
						continue;
					}
				}
			}
			String message = "Valores com RValue e LValue!";
			this.session.setAttribute("hasGivenRValues",message);
			ServletUtility.sendRedirect(response,formPage);
		}
		/*
		if ((infixExpression == null) || (infixExpression.equals(""))){
			String message = "O campo da expressão infixa está incorreto!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else {
			try{
				Interpreter interpreter = new Interpreter(infixExpression);
				session.setAttribute("interpreter",interpreter);
				ServletUtility.sendRedirect(response,formPage);
			} catch (IrregularExpressionException iee){
				session.setAttribute("message",iee.getMessage());
				ServletUtility.sendRedirect(response,formPage);
			}
		 }*/
	}
}
