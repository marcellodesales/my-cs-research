/**
 * CreateNewLink.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.net.MalformedURLException;
import java.net.URL;

import br.ufal.graw.User;
import br.ufal.graw.Link;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.exception.CategoryNotFoundException;

public class CreateNewLink extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		this.processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		String linkTitle, linkAddress, linkDescription, categoryID;
		//Community community;
		this.session  = request.getSession(false);
		
		linkTitle       = request.getParameter("linkTitle");
		linkAddress     = request.getParameter("linkAddress");
		linkDescription = request.getParameter("linkDescription");
		categoryID      = request.getParameter("categoryID");
		
		this.session.setAttribute("linkTitle",linkTitle);
		this.session.setAttribute("linkAddress",linkAddress);
		this.session.setAttribute("linkDescription",linkDescription);
						
		this.database = (DatabaseLayer)session.getAttribute("database");
		//community  = (Community)this.session.getAttribute("community");
				
		if ((linkTitle == null) || (linkTitle.equals(""))){
			this.session.setAttribute("message","O campo do título está vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_CREATE_NEW_PAGE+"?categoryID="+categoryID);
		} else
		if ((linkAddress == null) || (linkAddress.equals(""))){
			this.session.setAttribute("message","O campo do endereço está vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_CREATE_NEW_PAGE+"?categoryID="+categoryID);
		} else
		if ((linkDescription == null) || (linkDescription.equals(""))){
			this.session.setAttribute("message","O campo da descrição está vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.LINK_CREATE_NEW_PAGE+"?categoryID="+categoryID);
		} else
			try {
				
				URL newLinkAddress = new URL(linkAddress);
				Link newLink = Link.createNewLink(categoryID,this.database);
				newLink.setData(linkTitle,newLinkAddress.toString(),linkDescription);
				this.session.removeAttribute("linkTitle");
				this.session.removeAttribute("linkAddress");
				this.session.removeAttribute("linkDescription");
				this.session.setAttribute("message","Novo link cadastrado com sucesso!");
				ServletUtility.sendRedirect(response,ServletUtility.LINKS_MAIN_PAGE+"?categoryID="+categoryID);
				
			} catch (MalformedURLException mfurle){
				this.session.setAttribute("message","O formato do endereço está incorreto!");
				ServletUtility.sendRedirect(response,ServletUtility.LINK_CREATE_NEW_PAGE+"?categoryID="+categoryID);
			} catch (CategoryNotFoundException cnfe){
				this.session.setAttribute("message","Erro ao tentar cadastrar um novo link: Categoria inexistente!");
				ServletUtility.sendRedirect(response,ServletUtility.LINK_CATEGORY_PAGE);
			}
	}
}


