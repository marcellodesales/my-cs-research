/**
 * CreateNewDiscipline.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import br.ufal.graw.User;
import br.ufal.graw.Professor;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.Visibility;
import br.ufal.graw.Association;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.administrator.AdministratorUtility;

public class CreateNewDiscipline extends HttpServlet{
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		try{
			this.session = request.getSession(false);
			User user              = (User)(this.session.getAttribute("user"));
			DatabaseLayer database = (DatabaseLayer)this.session.getAttribute("database");
			String communitySubcategoryID = (String)this.session.getAttribute("subcategoryID");
			
			String title 		   = request.getParameter("title");	//Nome da disciplina
			String disciplineCode  = request.getParameter("disciplineCode"); //Código
			String description     = request.getParameter("description");	//Descrição do curso
			String program 	 	   = request.getParameter("program"); //Programa do curso
			String bibliography    = request.getParameter("bibliography");	//Referencias bibliográficas
			String hours 	   	   = request.getParameter("hours"); //Carga Horária
			
			Professor prof = (Professor)user;
			
			AdministratorUtility adm = new AdministratorUtility(database);
			
			if ((title == null) || title.equals("")){
				String message = "Favor digitar o nome da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else
			if ((disciplineCode == null) || disciplineCode.equals("")){
				String message = "Favor digitar o código da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else
			if ((description == null) || description.equals("")){
				String message = "Favor digitar a descrição da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else
			if ((program == null) || program.equals("")){
				String message = "Favor digitar o programa da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else
			if ((bibliography == null) || bibliography.equals("")){
				String message = "Favor digitar as referêcias da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else
			if ((hours == null) || hours.equals("")){
				String message = "Favor digitar a carga horária da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_DISCIPLINE_PAGE);
			} else {
						
				adm.createDiscipline(title,description,Visibility.INTRA_INSTITUTION,
								 Association.OPENED,communitySubcategoryID,
								 user.getID(),program,hours,bibliography,
								 disciplineCode,prof.getAcademicCourseID());
				
				String message = "Disciplina proposta com sucesso! Aguarde a resposta por email!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COMMUNITY_PAGE);
			}
		}catch(Exception e){

			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
		}
	}
}

