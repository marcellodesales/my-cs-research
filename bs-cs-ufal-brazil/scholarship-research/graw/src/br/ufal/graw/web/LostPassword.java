/**
 * LostPassword.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.Mail;
import br.ufal.graw.User;
import br.ufal.graw.Utility;
import br.ufal.graw.web.ServletUtility;

public class LostPassword extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		try{
			final String LOST_PASSWORD_PAGE = "/lostPassword.jsp";
			DatabaseLayer database = (DatabaseLayer)request.getSession(false).getAttribute("database");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			
			request.getSession(false).setAttribute("username",username);
			request.getSession(false).setAttribute("email",email);
			
			if ((username == null) || (username.equals(""))){
				request.getSession(false).setAttribute("message","O campo do nome de usuário está vazio.");
				ServletUtility.sendRedirect(response,LOST_PASSWORD_PAGE);
			} else
			if ((email == null) || (email.equals(""))){
				request.getSession(false).setAttribute("message","O campo do email está vazio.");
				ServletUtility.sendRedirect(response,LOST_PASSWORD_PAGE);
			} else {

				String userID = AbstractUser.exists(username,email,database);
				if (!userID.equals("")){
					email = email.trim();
					username = username.trim();
					
					User user = AbstractUser.getRealUser(userID,database);
					String newPassword = Utility.getNewID();
					user.setPassword(newPassword);
					
					String message = "Olá "+user.getFirstName()+",\n\n";
					message += "Aqui está sua nova senha de acesso ao graW!\n\n";
					message += "Nome de Usuário: "+user.getLogin();
					message += "\nNova Senha: "+newPassword;
					message += "\n\ngraW - Graduação na Web";
					message += "\ngraw@tci.ufal.br";
					Mail.send("graw@tci.ufal.br","Administração graW",email,"Nova senha de acesso ao graW",message);
					
					request.getSession(false).setAttribute("message","Sua nova senha foi enviada para "+email+"!");
					request.getSession(false).removeAttribute("username");
					request.getSession(false).removeAttribute("email");
					
					ServletUtility.sendRedirect(response,ServletUtility.MAIN);
				} else {
					request.getSession(false).setAttribute("message","Os dados passados estão incorretos! Não existe nenhum usuário "+username+" com email "+email+".");
					ServletUtility.sendRedirect(response,LOST_PASSWORD_PAGE);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			request.getSession(false).setAttribute("message","Erro interno no servidor ao tentar mudar senha!");
			ServletUtility.sendRedirect(response,ServletUtility.MAIN);
		}
	}
}

