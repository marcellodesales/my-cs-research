/**
 * LostPassword.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.site;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.web.Mail;
import br.ufal.graw.web.ServletUtility;

public class LostPassword extends HttpServlet{
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException, IOException{
			
		String username = request.getParameter("login");
		String email = request.getParameter("email");
		this.session = request.getSession(false);
		DatabaseLayer database = (DatabaseLayer)this.session.getAttribute("database");
		
		if ((username == null) || username.equals("")){
			ServletUtility.sendRedirect(response,"");
		} else
		if ((email == null) || email.equals("")){
			ServletUtility.sendRedirect(response,"");
		} else {
		
			if (AbstractUser.loginExists(username,database)){
				
				//Mail.send("administrador@graw.tci.ufal.br",AbstractUser.getEmail(username,database),"","");
			}
		}
	}
}

