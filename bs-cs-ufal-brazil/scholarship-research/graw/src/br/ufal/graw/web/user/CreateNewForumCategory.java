/**
 * CreateNewLinkCategory.java
 *
 * @author GraW's Development Group
 */

package br.ufal.graw.web.user;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.CategoryAlreadyExistsException;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.Community;
import br.ufal.graw.ForumCategory;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.User;

public class CreateNewForumCategory extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;
	
	private void createForumCategory(String title, String description, HttpServletResponse response){
		
		this.session.setAttribute("title",title);
		
		if ((title == null) || title.equals("")){
			String message = "O campo do título está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,ServletUtility.FORUM_CREATE_CATEGORY_PAGE);
		} else {
			try{
				User creator = (User)this.session.getAttribute("user");
				System.out.println(creator != null);
				Object community = session.getAttribute("community");
				ForumCategory forumCategory = ForumCategory.createNewForumCategory(((Community)community).getID(),title,creator,database);
				this.session.removeAttribute("title");
				ServletUtility.sendRedirect(response,ServletUtility.FORUM_MESSAGES_PAGE+"?categoryID="+forumCategory.getID());
				
			} catch(CommunityNotFoundException cnfe){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"A sessão expirou!");
			} catch(CategoryAlreadyExistsException caee){
				this.session.setAttribute("message",caee.getMessage());
				ServletUtility.sendRedirect(response,ServletUtility.FORUM_CREATE_CATEGORY_PAGE);
			}
		}
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		this.session  = request.getSession(false);
		this.database = (DatabaseLayer)session.getAttribute("database");
		String title       = request.getParameter("title");
		String description = request.getParameter("description");
		this.createForumCategory(title,description,response);
	}
}

