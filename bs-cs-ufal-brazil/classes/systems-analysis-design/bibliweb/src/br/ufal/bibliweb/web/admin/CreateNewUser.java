package br.ufal.bibliweb.web.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufal.bibliweb.DatabaseLayer;
import br.ufal.bibliweb.AcademicUser;
import br.ufal.bibliweb.AcademicCourse;
import br.ufal.bibliweb.Group;
import br.ufal.bibliweb.web.ServletUtility;

/**
 * CreateNewUser.java
 *
 * @author Marcello Junior
 */
public class CreateNewUser extends HttpServlet{
	
	private HttpSession session;
	private DatabaseLayer database;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException{
		String formPage = ServletUtility.ADMIN_CREATE_USER_PAGE+"newUserForm.jsp";
		this.session  = request.getSession(false);
		
		this.database = (DatabaseLayer)session.getAttribute("database");
		String groupID = ((Group)session.getAttribute("group")).getID();
		String academicCourseID = ((AcademicCourse)session.getAttribute("academicCourse")).getID();
		
		String CPF          = request.getParameter("CPF");
		String registration = request.getParameter("registration");
		String name         = request.getParameter("name");
		String homeAddress  = request.getParameter("homeAddress");
		String workAddress  = request.getParameter("workAddress");
		String homePhone    = request.getParameter("homePhone");
		String workPhone    = request.getParameter("workPhone");
		String cellPhone    = request.getParameter("cellPhone");
		String email        = request.getParameter("email");
		
		if ((CPF == null) || (CPF.equals(""))){
			String message = "O campo do CPF está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((registration == null) || (registration.equals(""))){
			String message = "O campo da matrícula está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((name == null) || (name.equals(""))){
			String message = "O campo do nome está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if (((homeAddress == null)|| (homeAddress.equals(""))) && ((workAddress == null) || (workAddress.equals("")))){
			String message = "Ao menos um endereço deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if (((homeAddress == null) || (homeAddress.equals(""))) && ((workPhone == null) || (workPhone.equals(""))) && ((cellPhone == null) || (homeAddress.equals("")))){
			String message = "Ao menos um telefone deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else {
			
			try{
				String newUserID = AcademicUser.createNewAcademicUser(groupID,academicCourseID,CPF,registration,name,homeAddress,workAddress,homePhone,workPhone,cellPhone,email,this.database);
				session.removeAttribute("group");
				session.removeAttribute("academicCourse");
				session.setAttribute("message","Usuário cadastrado com sucesso!");
				ServletUtility.sendRedirect(response,ServletUtility.ADMIN_PAGE_USERINFOR+"?userID="+newUserID);
			}catch(Exception e){
				this.session.setAttribute("message",e.getMessage());
				ServletUtility.sendRedirect(response,formPage);
			}
		}
	}
}

