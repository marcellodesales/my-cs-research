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

import br.ufal.tci.interpreter.*;

public class CalculatePostfixForm extends HttpServlet {
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException{
		String formPage = "http://www.graw.tci.ufal.br/marcellojunior/interpreter/";
		//String formPage = "http://localhost:8080/interpreter/";
		this.session  = request.getSession(true);
		
		String infixExpression = request.getParameter("infixExpression");
		
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
		}
	}
}

