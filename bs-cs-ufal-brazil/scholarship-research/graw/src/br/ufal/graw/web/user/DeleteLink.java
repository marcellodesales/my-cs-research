package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import br.ufal.graw.Utility;
import br.ufal.graw.Link;
import br.ufal.graw.User;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.web.ServletUtility;

public class DeleteLink extends HttpServlet{

	private DatabaseLayer database;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		String linkID = "";
		try{
			this.database = (DatabaseLayer)request.getSession(false).getAttribute("database");
			linkID = request.getParameter("linkID");
			Link link = new Link(linkID,database);
			Link.deleteLink(link.getID(),this.database);
			request.getSession(false).setAttribute("message","Link removido com sucesso!");
			ServletUtility.sendRedirect(response,ServletUtility.LINKS_MAIN_PAGE+"?categoryID="+link.getCategoryID());
			
		}catch(ResourceNotFoundException rnfe){
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
			String message = "Erro ao tentar apagar link ID"+linkID;
			request.getSession(false).setAttribute("message",message);
			ServletUtility.sendRedirect(response,ServletUtility.LINKS_MAIN_PAGE);
		}
	}
}
