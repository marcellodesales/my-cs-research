/**
 * SignInNewAcademicUser.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Vector;
import java.util.Hashtable;
import java.io.IOException;

import br.ufal.graw.AcademicUser;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.Department;
import br.ufal.graw.Utility;
import br.ufal.graw.Administrator;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.MailException;

import br.ufal.graw.web.administrator.AdministratorUtility;
import br.ufal.graw.web.ServletUtility;

public class SignInNewAcademicUser extends HttpServlet{
	
	private DatabaseLayer database;
	private Vector result;
	private HttpSession session;
	private final String SIGNIN_ACADEMIC_PAGE = ServletUtility.CONTEXT_PATH+"signIn/signNewAcademic3.jsp";
	
	private void createNewAcademicUser(String name, String login, String matriculation, String academicCourseID, HttpServletResponse res){
		AdministratorUtility a = new AdministratorUtility(this.database);
		try {
			String academicUserEmail = "";
			if (AcademicUser.isProfessor(matriculation,this.database)){
				a.signNewProfessor(login,name,matriculation);
			} else
			if (AcademicUser.isStudent(matriculation,this.database)){
				a.signNewStudent(login,name,matriculation);
			} else {
				String message = "Ocorreu algum erro na sua identificação! Tente novamente!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			}
			this.session.removeAttribute("nameac");
			this.session.removeAttribute("login");
			this.session.removeAttribute("academicCourseID");
			this.session.removeAttribute("matriculation");
			String message = "Você foi cadastrado com sucesso! Verifique as primeiras instruções de acesso ao ambiente no seu email "+AcademicUser.getAcademicUserEmail(matriculation,academicCourseID,database)+".";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(res,ServletUtility.CONTEXT_PATH);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		this.session = req.getSession(false);
		String name  = req.getParameter("nameac");
		String login = req.getParameter("login");
		String matriculation    = req.getParameter("matriculation");
		String academicCourseID = (String)this.session.getAttribute("academicCourseID");
		this.database = (DatabaseLayer)this.session.getAttribute("database");
		
		this.session.setAttribute("nameac",name);
		this.session.setAttribute("login",login);
		this.session.setAttribute("matriculation",matriculation);
		
		if (!AcademicCourse.exists(academicCourseID,this.database)){
			String message = "Curso acadêmico inválido! = "+academicCourseID;
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
		} else {
			if ((name == null) || name.equals("")){
				String message = "Favor digitar seu nome acadêmico!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			} else
			if ((matriculation == null) || matriculation.equals("")){
				String message = "Favor digitar sua matrícula!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			} else
			if ((login == null) || login.equals("")){
				String message = "Favor digitar sua nome de usuário!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			}else
			if (!AcademicUser.matriculationExists(matriculation,academicCourseID,this.database)){
				String message = "Matrícula inexistente para esse curso acadêmico! ("+matriculation+")";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			} else
			if (AbstractUser.loginExists(login,this.database)){
				String message = "Nome de usuário já existente! = "+login;
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			} else
			if (AcademicUser.alreadySigned(matriculation,academicCourseID,this.database)){
				String message = "Já existe um usuário cadastrado com essa matrícula! = "+matriculation;
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(res,this.SIGNIN_ACADEMIC_PAGE);
			} else this.createNewAcademicUser(name,login,matriculation,academicCourseID,res);
		}
	}
	
}
