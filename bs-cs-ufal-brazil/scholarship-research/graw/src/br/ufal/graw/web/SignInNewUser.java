package br.ufal.graw.web;

/**
This Class validate the login and after that it create instances of Student , PROFESSOR or Administrator
 */
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Vector;
import java.util.Hashtable;
import java.io.IOException;

import br.ufal.graw.AbstractUser;
import br.ufal.graw.AcademicUser;
import br.ufal.graw.Department;
import br.ufal.graw.Utility;
import br.ufal.graw.Administrator;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.CommunitySpecialInvitation;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.MailException;

import br.ufal.graw.web.administrator.AdministratorUtility;
import br.ufal.graw.web.ServletUtility;

public class SignInNewUser extends HttpServlet{
	
	/* By convention there are three types of login: Student, PROFESSOR and Adminitrator */
	
	private DatabaseLayer database;
	private Vector result;
	private HttpSession session;
	private final String SIGNIN_PAGE = ServletUtility.CONTEXT_PATH+"signIn/externalUser.jsp";
	private final String SIGNIN_ACADEMIC_PAGE = ServletUtility.CONTEXT_PATH+"signIn/signNewAcademic3.jsp";
		
	/**
	 @return true if the login the is valid. false otherwise
	 */
	
	/**
		Aceita como login apenas números, letras e o caracter _
	 */
	private boolean loginIsValid(String login){
		int length = login.length()	;
		char c;
		for (int i=0 ; i < length ; i++){
			c = login.charAt(i);
			if ( (!Character.isLetterOrDigit(c)) && (!(c=='_')) ){
				return false;
			}
		}
		return true;
	}
	
	/*private void createNewAcademicUser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		try{
			String userID,matriculation,login,email=null,userName,domain,name;
			Hashtable hash;
			matriculation   = request.getParameter("matriculation");
			login 			= request.getParameter("login");
			name 			= request.getParameter("name");
			
			login = login.trim();
			if (! this.loginIsValid(login)){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"O login que você digitou contém caracters inválidos");
				return;
			}
			this.database = new DatabaseLayer();
			Administrator admin = new Administrator("0",this.database);
			Utility utility = new Utility(this.database);
			
			/* Verifica se é um estudante
			this.result = this.database.query("SELECT userID,userName,departmentID from student WHERE matriculation='"+matriculation+"'");
						
			if (result.size() != 0 ){
				hash = (Hashtable)result.firstElement() ;
				userID = (String)hash.get("userID");
				if  (userID != null && (!userID.equals("")) ) {
					/* Estudante já foi cadastrado
					ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Já existe um estudante com essa matricula cadastrado.");
					return;
				}else{
					userName = (String)hash.get("userName");
					domain = new Department((String)hash.get("departmentID"),this.database).getWebDomain();
									
					email = userName+"@"+domain;
					admin.createNewStudent(login,name,email,userName);
				}
			}else{
				/* Verifica se é um professor
				this.result = this.database.query("SELECT userID,userName,departmentID from professor WHERE matriculation='"+matriculation+"'");
						
				if (result.size() != 0 ){
					hash = (Hashtable)result.firstElement() ;
					userID = (String)hash.get("userID");
					if ( userID != null ){
						/* Professor já foi cadastrado
						ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Já existe um professor com essa matricula cadastrado.");
						return;
					}else{
						userName = (String)hash.get("userName");
						domain = new Department((String)hash.get("departmentID"),this.database).getWebDomain();
						email = userName+"@"+domain;
						admin.createNewProfessor(login,name,email,userName);
					}
				}else{
					ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Usuário desconhecido. Somente membros do departamento podem se associar");
					return;
				}
				
			}
			ServletUtility.sendRedirect(response,ServletUtility.SUCESS_GENERIC_PAGE,"Usuário cadastrado com sucesso!. Sua senha foi enviada para: "+email,request.getHeader("Referer"));
		}catch(MailException me){
			ServletUtility.sendRedirect(response,ServletUtility.SUCESS_GENERIC_PAGE,"Usuário cadastrado com sucesso, mas nao foi possível enviar o email com a senha temporária, contate o administrador.",request.getHeader("Referer"));
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
	}*/
	
	private String createNewUser(String name, String login, String email, HttpServletResponse res){
		AdministratorUtility a = new AdministratorUtility(this.database);
		String ID=null;
		try {
			ID = a.createNewExternalUser(login,name,email);
			this.session.removeAttribute("name");
			this.session.removeAttribute("login");
			this.session.removeAttribute("email");
			String message = "Você foi cadastrado com sucesso! Verifique as primeiras instruções de acesso ao ambiente no seu email "+email+".";
			this.session.setAttribute("message",message);
		} catch (Exception e){
			e.printStackTrace();
		}
		return ID;
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String login = req.getParameter("login");
		String email = req.getParameter("email");
		
		this.session = req.getSession(false);
		String academicCourseID = (String)this.session.getAttribute("academicCourseID");
		this.database = (DatabaseLayer)this.session.getAttribute("database");
		
		this.session.setAttribute("name",name);
		this.session.setAttribute("login",login);
		this.session.setAttribute("email",email);
		
		if ((name == null) || name.equals("")){
			String message = "Favor digitar seu nome!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(res,this.SIGNIN_PAGE);
		} else
		if ((login == null) || login.equals("")){
			String message = "Favor digitar seu nome de usuário!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(res,this.SIGNIN_PAGE);
		} else
		if ((email == null) || email.equals("")){
			String message = "Favor digitar seu email!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(res,this.SIGNIN_PAGE);
		} else {
			login = login.trim();
			if (AbstractUser.loginExists(login,this.database)){
				String message = "Nome de usuário existente! ("+login+")";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_PAGE);
			} else
			if (!this.loginIsValid(login)){
				String message = "Nome de usuário não é válido! ("+login+")";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_PAGE);
			} else{
				String ID = this.createNewUser(name,login,email,res);
				CommunitySpecialInvitation specialInvitation = (CommunitySpecialInvitation) this.session.getAttribute("invitation");
				if (specialInvitation!=null){
					try{
						specialInvitation.setGuestID(ID);
					}catch(PersistentInformationException e){
						ServletUtility.sendRedirect(res, ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
						return;
					}
				}
				ServletUtility.sendRedirect(res,ServletUtility.CONTEXT_PATH);
				
			}
		}
	}
}

