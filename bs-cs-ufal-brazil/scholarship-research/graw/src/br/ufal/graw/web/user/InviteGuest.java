/**
 * InviteGuest.java
 *
 * @author Created by Omnicore CodeGuide
 */
package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Date;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.Mail;
import br.ufal.graw.Utility;
import br.ufal.graw.Community;
import br.ufal.graw.CommunitySpecialInvitation;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.GroupException;

public class InviteGuest extends HttpServlet{
	
	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	
	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		try{
			
			User user 		= (User)(request.getSession(false).getAttribute("user"));
			Community community 	= (Community)(request.getSession(false).getAttribute("community"));
			DatabaseLayer database = (DatabaseLayer) (request.getSession(false).getAttribute("database"));
	
			String email 	= request.getParameter("email");
			String message  = request.getParameter("message");
			
			CommunitySpecialInvitation invitation = CommunitySpecialInvitation.createNew(community,
												 user,
												 email,
												 message,
												database);
			
			Mail.send("graw@tci.ufal.br",Mail.serviceID,email,"Você recebeu um convite de "+user.getName()+" para ingressar na comunidade de "+community.getTitle(),
			"Parabéns !\nVocê foi "+
			"convidado por "+user.getName()+" a participar da comunidade '"+community.getTitle()+"'\n"+
    		"Se você já é um usuário do graW, basta acessar o menu de convites e digitar a senha do convite. SENHA="+invitation.getID()+"\n"+
			"Caso voce ainda não seja usuário do graW acesse: "+ServletUtility.INVITATION_GUEST+"?communityGuestsID="+invitation.getID()+" para realizar um cadastro e acessar os recursos do graW."+
			"Para rejeitar este convite basta ignorar esta mensagem.\nATENÇÃO: Você tem 15 dias para confirmar o convite a partir da seguinte data: "+new Date()+"\n"+
					 "MENSAGEM DO CONVITE: \n"+
					 message);
			
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Usuario(s) convidado(s) com sucesso. Um email foi enviado para ele(s) comunicando o convite.",request.getHeader("Referer"));
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		 }
			
	}

}
