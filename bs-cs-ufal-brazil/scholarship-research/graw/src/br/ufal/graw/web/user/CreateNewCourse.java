package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import br.ufal.graw.User;
import br.ufal.graw.AcademicUser;
import br.ufal.graw.Professor;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.Visibility;
import br.ufal.graw.Association;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.administrator.AdministratorUtility;

public class CreateNewCourse extends HttpServlet{
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		try{
			this.session = request.getSession(false);
			
			Boolean createAcademic = (Boolean)session.getAttribute("createAcademicCourse");
			boolean createAcademicCourse = (createAcademic != null) ? createAcademic.booleanValue() : false;
			
			User user              = (User)(this.session.getAttribute("user"));
			DatabaseLayer database = (DatabaseLayer)this.session.getAttribute("database");
			String communitySubcategoryID = (String)this.session.getAttribute("subcategoryID");
			
			String title 		   = request.getParameter("title");	//Nome da disciplina
			String description     = request.getParameter("description");	//Descrição do curso
			String program 	 	   = request.getParameter("program"); //Programa do curso
			String bibliography    = request.getParameter("bibliography");	//Referencias bibliográficas
			String hours 	   	   = request.getParameter("hours"); //Carga Horária
			String visib           = request.getParameter("visibility"); //visibilidade é um inteiro
			
			int visibility 	   	   = (createAcademicCourse) ? Integer.parseInt(visib) : 0;
			int association	   	   = Integer.parseInt(request.getParameter("association")); //visibilidade é um inteiro
			
			AdministratorUtility adm = new AdministratorUtility(database);
			if ((title == null) || title.equals("")){
				String message = "Favor digitar o nome da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COURSE_PAGE);
			} else
			if ((description == null) || description.equals("")){
				String message = "Favor digitar a descrição da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COURSE_PAGE);
			} else
			if ((program == null) || program.equals("")){
				String message = "Favor digitar o programa da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COURSE_PAGE);
			} else
			if ((bibliography == null) || bibliography.equals("")){
				String message = "Favor digitar as referêcias da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COURSE_PAGE);
			} else
			if ((hours == null) || hours.equals("")){
				String message = "Favor digitar a carga horária da disciplina!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COURSE_PAGE);
			} else {
				if (createAcademicCourse){
					System.out.println("academico");
					AcademicUser academicUser= (AcademicUser)user;
					adm.createExtraCourse(title,description,visibility,
								 association,communitySubcategoryID,
								 user.getID(),program,hours,bibliography,
										  academicUser.getAcademicCourseID());
				} else {
					System.out.println("publico");
					adm.createExtraCourse(title,description,Visibility.PUBLIC,
								 association,communitySubcategoryID,
								 user.getID(),program,hours,bibliography);
				}
				this.session.removeAttribute("subcategoryID");
				this.session.removeAttribute("createAcademicCourse");
				String message = "Curso proposto com sucesso! Aguarde a resposta por email!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COMMUNITY_PAGE);
			}
		}catch(Exception e){
			e.printStackTrace();
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
			//Utility.log(Utility.ERROR_FILE_LOG,e);
		}
	}
	

}
