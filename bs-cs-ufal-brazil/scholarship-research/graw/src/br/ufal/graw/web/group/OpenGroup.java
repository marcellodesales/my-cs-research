package br.ufal.graw.web.group;

/**
 */
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.GroupNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.CourseNotFoundException;

import br.ufal.graw.Group;
import br.ufal.graw.AbstractCourse;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.web.ServletUtility;

public class OpenGroup extends HttpServlet{

	private DatabaseLayer database;
	private HttpSession session;
	private Group group;

	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		try{
			this.session  = request.getSession(true);
			this.database = (DatabaseLayer)session.getAttribute("database");
			String groupID = request.getParameter("groupID");
			
			this.group = new Group(groupID,this.database);
			this.session.setAttribute("group",this.group);
			this.session.setAttribute("course",this.group.getCourse());
			
			ServletUtility.sendRedirect(response,ServletUtility.GROUP_MAIN_PAGE);
		}catch(CommunityException gnfe){ //
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,gnfe.getMessage());
		}
	}

	public void doGet(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException{
		this.processRequest(request,response);
	}
}
