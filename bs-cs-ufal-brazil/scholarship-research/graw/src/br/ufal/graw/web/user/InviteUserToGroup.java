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
import br.ufal.graw.Group;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.GroupException;

public class InviteUserToGroup extends HttpServlet{
	
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
			Group group 	= (Group)(request.getSession(false).getAttribute("group"));
			DatabaseLayer database = (DatabaseLayer) (request.getSession(false).getAttribute("database"));
			Enumeration userIDs;
			String userID,paramName;
			
			if ( ! group.isAdministrator(user) ){	// Nao eh administrador
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Você não administra esse grupo.");
				return;
			}
			
			userIDs = request.getParameterNames();

			while (userIDs.hasMoreElements()){
				paramName = (String)userIDs.nextElement();
				userID =request.getParameter(paramName);
				
				if (paramName.equals("userID")){
					
					User invited = AbstractUser.getRealUser(userID,database);
					String invitationID = user.inviteUserToGroup( invited, group );
					Mail.send(user,invited,"Convite do grupo "+group.getName(),"Parabéns "+invited.getName()+"!\nVocê foi "+
								  "convidado por "+user.getName()+" a participar do grupo '"+group.getName()+"',\nque tem por objetivo: "+group.getGoal()+
								  ".\nPara aceitar o convite basta acessar o seguinte endereço:\n"+ServletUtility.INVITATION_CONFIRM+"?invitationID="+invitationID+"\n"+
							 		"Para rejeitar basta ignorar esta mensagem.\nATENÇÃO: Você tem 15 dias para confirmar a partir da seguinte data: "+new Date());
					
				}
			}
			
			/* Atualiza os membros do grupo na sessao. */
			request.getSession(false).setAttribute("group", new Group(group.getID(),database) );
			
			ServletUtility.sendRedirect(response,ServletUtility.SUCESS_GENERIC_PAGE,"Usuário(s) convidado(s) com sucesso. Um email foi enviado para ele(s) comunicando o convite.",request.getHeader("Referer"));
			Utility.log(Utility.OPERATION_FILE_LOG,"Usuários convidados para o grupo: "+group.getID()+" Responsável: "+((User)request.getSession(false).getAttribute("user")).getID(),this);
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		 }
			
	}
	

}
