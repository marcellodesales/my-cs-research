package br.ufal.graw.web.community;

/**
 */
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.CommunityException;

import br.ufal.graw.Discipline;
import br.ufal.graw.Community;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.web.ServletUtility;

public class OpenCommunity extends HttpServlet{

	private DatabaseLayer database;
	private HttpSession session;
	private Community community;
	
	private void openCommunity(String communityID, HttpServletRequest request, HttpServletResponse response){
		
		try{
			this.community = AbstractCommunity.getRealCommunity(communityID,this.database);
			this.session.setAttribute("community",this.community);
			//this.session.removeAttribute("group");
			ServletUtility.sendRedirect(response,ServletUtility.COMMUNITY_MAIN_PAGE); //mudar para community_main_page
		}catch(CommunityException ce){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,ce.getMessage());
		}
	}

	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{

		this.session  = request.getSession(true);
		this.database = (DatabaseLayer)session.getAttribute("database");
		String communityID = request.getParameter("communityID");
		if (AbstractCommunity.exists(communityID,database)){
			this.openCommunity(communityID,request,response);
		} else ServletUtility.sendRedirect(response,"","Comunidade não encontrada!");
	}
}
