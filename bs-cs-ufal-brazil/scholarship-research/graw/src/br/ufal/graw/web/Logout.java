/**
 * Logout.java
 *
 * @author graW's Developers
 */

package br.ufal.graw.web;

import br.ufal.graw.web.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet{
	
	public void doGet(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException{
		processRequest(request,response);
			
	}
	public void doPost(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException{
			processRequest(request,response);
	}
	
	public void processRequest(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException{
			try{
				request.getSession(false).invalidate();
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			ServletUtility.sendRedirect(response,ServletUtility.MAIN);
	}
}

