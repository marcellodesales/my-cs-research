package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Vector;

import br.ufal.graw.User;
import br.ufal.graw.web.ServletUtility;

public class UserChangePassword extends HttpServlet{
	
	HttpSession session;
	private final String PASSWORD_PAGE = ServletUtility.CONTEXT_PATH+"user/password.jsp";
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		String password,newPassword,confirmNewPassword;
		User user;
		this.session = request.getSession(false);
		try{
			user = (User)this.session.getAttribute("user");
			password    = request.getParameter("password");
			newPassword = request.getParameter("newPassword");
			confirmNewPassword = request.getParameter("confirmNewPassword");
			
			if (user.isPassword(password)){
				if (newPassword.equals(confirmNewPassword)){
					user.setPassword(newPassword);
					String message = "Sua senha foi alterada com sucesso!";
					this.session.setAttribute("message",message);
					ServletUtility.sendRedirect(response,this.PASSWORD_PAGE);
				}else{
					String message = "A nova senha não confere!";
					this.session.setAttribute("message",message);
					ServletUtility.sendRedirect(response,this.PASSWORD_PAGE);
				}
			}else{//Password Incorreto
				String message = "A senha atual está incorreta!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,this.PASSWORD_PAGE);
			}

		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}

	}
}
