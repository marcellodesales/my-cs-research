package br.ufal.graw.web.community;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Vector;

import br.ufal.graw.User;
import br.ufal.graw.Association;
import br.ufal.graw.Community;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Discipline;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.Mail;

/**
Servlet responsavel por associar o usuario a uma comunidade.
 */
public class Associate extends HttpServlet{

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
			Community community = AbstractCommunity.getRealCommunity(communityID,database);
			User responsible = community.getResponsible();
			user.requestAssociation(community);
			if (community.getAssociationType() == Association.SEMI_OPENED ){
				Mail.send("graw@tci.ufal.br",Mail.serviceID,responsible.getEmail(),"Membro pendente para a sua autorização","O usuário "+user.getName()+", com email:"+user.getEmail()+" está esperando pela sua autorização para ingressar na comunidade: "+community.getTitle()+".\nPara autoriza-lo ou rejeita-lo acesse o graW e entre na página da comunidade ("+community.getTitle()+") , pois lá existe uma opção para aprova-lo ou rejeita-lo.");
			}
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Associação requisitada!");
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Ocorreram problemas no servidor ao tentar atualizar os interesses.");
		}

	}
}
