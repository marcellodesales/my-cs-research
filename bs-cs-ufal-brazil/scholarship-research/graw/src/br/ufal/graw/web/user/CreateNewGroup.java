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
import br.ufal.graw.Community;
import br.ufal.graw.AbstractCommunity;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.administrator.AdministratorUtility;

public class CreateNewGroup extends HttpServlet{
	
	private HttpSession session;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		try{
			this.session = request.getSession(false);
			String communityIDGroup = (String)this.session.getAttribute("communityIDGroup");
			User user              = (User)(this.session.getAttribute("user"));
			DatabaseLayer database = (DatabaseLayer)this.session.getAttribute("database");
			String communitySubcategoryID = (String)this.session.getAttribute("subcategoryID");
			
			String title 	   = request.getParameter("title");	//Nome da disciplina
			String description = request.getParameter("description");	//Descrição do curso
			String goal 	   = request.getParameter("goal"); //
			String visib       = request.getParameter("visibility"); //visibilidade é um inteiro
			int association	   = Integer.parseInt(request.getParameter("association")); //visibilidade é um inteiro
		
			boolean createAssotiatedGroup = (communityIDGroup != null);
			
			Community commGroup = null;
			int visibility = Visibility.PUBLIC;
			if (communityIDGroup != null) {
				commGroup = AbstractCommunity.getRealCommunity(communityIDGroup,database);
				visibility = ((createAssotiatedGroup) && (commGroup.getKind().equals(AbstractCommunity.EXTRA_COURSE))) ? Integer.parseInt(visib) : Visibility.PRIVATE;//o ultimo por ser disciplina
			}
		
			AdministratorUtility adm = new AdministratorUtility(database);
			System.out.println("Vai testar as coisas");
			if ((title == null) || title.equals("")){
				String message = "Favor digitar o nome do grupo!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_GROUP_PAGE);
			} else
			if ((description == null) || description.equals("")){
				String message = "Favor digitar a descrição do grupo!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_GROUP_PAGE);
			} else
			if ((goal == null) || goal.equals("")){
				String message = "Favor digitar o programa do grupo!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_GROUP_PAGE);
			} else {
				if (createAssotiatedGroup){
					adm.createGroup(title,description,visibility,
								 association,communitySubcategoryID,
								 user.getID(),Community.ALL_TOOLS,goal,
								 communityIDGroup);
				} else {
					adm.createGroup(title,description,visibility,
								 association,communitySubcategoryID,
								 user.getID(),Community.ALL_TOOLS,goal);
				}
				this.session.removeAttribute("subcategoryID");
				this.session.removeAttribute("communityIDGroup");
				String message = "Grupo proposto com sucesso! Aguarde a resposta por email!";
				this.session.setAttribute("message",message);
				ServletUtility.sendRedirect(response,ServletUtility.CREATE_COMMUNITY_PAGE);
			}
		} catch (Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
		}
	}
}
