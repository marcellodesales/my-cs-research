package br.ufal.graw.web.community;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Vector;

import br.ufal.graw.User;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Discipline;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.web.ServletUtility;

/**
Servlet responsavel por Desassociar o usuario de uma comunidade.
 */
public class Disassociate extends HttpServlet{

	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		String communityID;
		User user;
		DatabaseLayer database;

		try{
			user = (User)request.getSession(false).getAttribute("user");
			database = (DatabaseLayer)request.getSession(false).getAttribute("database");

			communityID = request.getParameter("communityID");
			
			user.removeAssociation(AbstractCommunity.getRealCommunity(communityID,database));
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Interesses atualizados com sucesso!");
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Ocorreram problemas no servidor ao tentar atualizar os interesses.");
		}

	}
}
