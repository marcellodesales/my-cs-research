package br.ufal.graw.web;

/**
This Class validate the login and after that it create instances of Student , PROFESSOR or Administrator
 */
import java.sql.SQLException;

import java.util.Vector;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserLoginWrongPasswordException;

import br.ufal.graw.Utility;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.Student;
import br.ufal.graw.Professor;
import br.ufal.graw.ExternUser;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.web.ServletUtility;

public class Login extends HttpServlet{

	private HttpSession session;
	private DatabaseLayer database;
	
	private void validateLogin(String login, String password, HttpServletRequest request, HttpServletResponse response){
		try{
			login = login.trim();

			if (((login != null)&&(!login.equals(""))) && ((password != null)&&(!password.equals("")))){
								
				this.session.setAttribute("conf",database.getConfiguration());

				String userID = AbstractUser.loginExists(database,login);
				if (userID.equals("")){
					this.session.setAttribute("message","Nome de usuário não encontrado!");
					ServletUtility.sendRedirect(response,ServletUtility.MAIN);
				} else
				if (!AbstractUser.loginIsCorrect(login,password,database)){
					this.session.setAttribute("message","Sua senha está incorreta!");
					ServletUtility.sendRedirect(response,ServletUtility.MAIN);
				} else {
					User user = AbstractUser.getRealUser(userID,database);
					this.session.setAttribute("user",user);
					ServletUtility.sendRedirect(response,ServletUtility.USER_MAIN_PAGE);
				}
				
			} else {
				this.session.setAttribute("message","Está faltando dados para realizar o login!");
				ServletUtility.sendRedirect(response,ServletUtility.MAIN);
			}
		}catch(UserNotFoundException unfe){
			this.session.setAttribute("message","Erro ao tentar localizar usuário existente!");
			ServletUtility.sendRedirect(response,ServletUtility.MAIN);
			Utility.log(Utility.ERROR_FILE_LOG,unfe);
		}catch(NullPointerException npe){
			this.session.setAttribute("message","Erro interno no servidor! Tente novamente mais tarde.");
			ServletUtility.sendRedirect(response,ServletUtility.MAIN);
			Utility.log(Utility.ERROR_FILE_LOG,npe);
		}
	}

	public void doPost(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException{
		String login,password;
		login    = request.getParameter("login");
		password = request.getParameter("password");
		this.session = request.getSession(false);
		if (this.session==null){
			this.session = request.getSession(true);
			this.session.setAttribute("database", database);
		}else{
			this.database = (DatabaseLayer)this.session.getAttribute("database");
			if (this.database == null){
				this.database = new DatabaseLayer();
				this.session = request.getSession(true);
				this.session.setAttribute("database", database);
			}
		}
		this.validateLogin(login,password,request,response);
	}
}
