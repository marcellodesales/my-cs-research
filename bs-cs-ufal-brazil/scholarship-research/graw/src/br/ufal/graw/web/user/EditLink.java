/**
 * EditLink.java
 *
 * @author Nonamepibic
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import br.ufal.graw.Community;
import br.ufal.graw.User;
import br.ufal.graw.Utility;
import br.ufal.graw.Link;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.exception.DisciplineNotFoundException;

import br.ufal.graw.web.ServletUtility;

public class EditLink extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		this.session  = request.getSession(true);
		this.database = (DatabaseLayer)session.getAttribute("database");
		this.processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		String linkID, linkTitle, linkAddress, linkDescription, categoryID;
		linkID          = request.getParameter("linkID");
		linkTitle       = request.getParameter("linkTitle");
		linkAddress     = request.getParameter("linkAddress");
		linkDescription = request.getParameter("linkDescription");
		
		if ((linkTitle == null) || (linkTitle.equals(""))){
			this.session.setAttribute("message","O campo do título não pode ficar vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_EDIT_PAGE+"?linkID="+linkID);
		} else
		if ((linkAddress == null) || (linkAddress.equals(""))){
			this.session.setAttribute("message","O campo do endereço não pode ficar vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_EDIT_PAGE+"?linkID="+linkID);
		} else
		if ((linkDescription == null) || (linkDescription.equals(""))){
			this.session.setAttribute("message","O campo da descrição não pode ficar vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_EDIT_PAGE+"?linkID="+linkID);
		} else{
			try {
				Link usedLink = new Link(linkID,this.database);
				usedLink.setData(linkTitle,linkAddress,linkDescription);
				ServletUtility.sendRedirect(response,ServletUtility.LINKS_MAIN_PAGE+"?categoryID="+usedLink.getCategoryID());
			} catch (Exception e){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
			}
		}
	}
}

