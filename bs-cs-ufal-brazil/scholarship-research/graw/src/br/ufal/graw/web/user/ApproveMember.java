/**
 * ApproveMember.java
 *
 * @author Membros do graW
 */

package br.ufal.graw.web.user;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Vector;

import br.ufal.graw.User;
import br.ufal.graw.Community;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Discipline;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.Mail;


/**
Servlet que recebe como parametros:
 communityID
 userID
Alem dos atributos de sessao:
	user - O usuario responsavel por chamar esse servlet
	database
 */
public class ApproveMember extends HttpServlet{

	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		String communityID, userID;
		User user;
		DatabaseLayer database;

		try{
			user = (User)request.getSession(false).getAttribute("user");
			database = (DatabaseLayer)request.getSession(false).getAttribute("database");
			communityID = request.getParameter("communityID");
			userID = request.getParameter("userID");
			User member = AbstractUser.getRealUser(userID,database);
			Community community = AbstractCommunity.getRealCommunity(communityID,database);
			
			user.acceptMember(AbstractCommunity.getRealCommunity(communityID,database),member);
			Mail.send("graw@tci.ufal.br",Mail.serviceID,member.getEmail(),"Aprovação de associação","Parabéns, sua associação na comunidade "+community.getTitle()+" foi autorizada por: "+user.getFirstName()+".");
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Interesses atualizados com sucesso!");
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Ocorreram problemas no servidor ao tentar atualizar os interesses.");
		}

	}
}
