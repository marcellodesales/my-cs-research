/**
	Responsible for process request of user set data
*/

package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.User;

import br.ufal.graw.web.ServletUtility;

public class UserUpdatePersonalInformation extends HttpServlet{
	
	private final String CONFIG_PAGE = ServletUtility.CONTEXT_PATH+"user/config.jsp";
	
	HttpSession session;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		String name,email;
		try{
			this.session = request.getSession(false);
			User user = (User)this.session.getAttribute("user");
			name  = request.getParameter("name");
			email = request.getParameter("email");
			if ((name == null) || name.equals("")){
				String message = "Seu nome não pode ser vazio!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,this.CONFIG_PAGE);
			} else
			if ((email == null) || email.equals("")){
				String message = "Seu email não pode ser vazio!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,this.CONFIG_PAGE);
			}else {
				user.setData(name,email);
				String message = "Informações Pessoais atualizadas com sucesso!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,this.CONFIG_PAGE);
			}
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Não foi possível atualizar as informações pessoais.");
		}
	}
}
