/**
 * CreateNewTopic.java
 *
 * @author GraW
 */

package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.graw.Message;
import br.ufal.graw.Forum;
import br.ufal.graw.ForumCategory;
import br.ufal.graw.Community;
import br.ufal.graw.User;
import br.ufal.graw.web.Mail;
import br.ufal.graw.Utility;
import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.exception.MessageNotFoundException;
import br.ufal.graw.exception.DisciplineNotFoundException;

import br.ufal.graw.web.ServletUtility;

public class CreateNewMessage extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;
	//private String errorPage = "/nonamepibic/discipline/forum/newtopic.jsp?error=";

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		this.session  = request.getSession(true);
		this.database = (DatabaseLayer)session.getAttribute("database");
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		String messageTitle, messageBody, senderID, categoryID, ownerMessageID, replyID;
		boolean reply  = false;
		messageTitle   = request.getParameter("messageTitle");
		messageBody    = request.getParameter("messageBody");
		ownerMessageID = (String)session.getAttribute("ownerMessageID");
		replyID        = (String)session.getAttribute("replyID");
		ownerMessageID = (ownerMessageID == null) ? "" : ownerMessageID;
		categoryID     = request.getParameter("categoryID");
		
		this.session.setAttribute("messageTitle",messageTitle);
		this.session.setAttribute("messageBody",messageBody);
		
		if (replyID != null){
			System.out.println("Entrou no reply");
			try {
				String title = (new Message(replyID,database)).getTitle();
				messageBody += "<REPLY_LINE><REPLY_LINE>Respondendo a: <REPLY_LINE>"+title;
				
			} catch (MessageNotFoundException mnfe){
				this.session.setAttribute("message","A mensagem de resposta não existe ("+replyID+")");
				ServletUtility.sendRedirect(response,ServletUtility.FORUM_MAIN_PAGE);
			}
		}
		
		if ((messageTitle == null) || (messageTitle.equals(""))){
			this.session.setAttribute("message","O campo do título está vazio.");
			ServletUtility.sendRedirect(response,ServletUtility.FORUM_CREATE_MESSAGE_PAGE+"?categoryID="+categoryID);
		} else
		if ((messageBody == null) || (messageBody.equals(""))){
			this.session.setAttribute("message","O corpo da mensagem não foi digitado.");
			ServletUtility.sendRedirect(response,ServletUtility.FORUM_CREATE_MESSAGE_PAGE+"?categoryID="+categoryID);
		} else {
			try{
				Community community = (Community)this.session.getAttribute("community");
				Forum forum = new Forum(community.getID(),this.database);
				User user = (User)this.session.getAttribute("user");
				ForumCategory forumCategory = new ForumCategory(categoryID,database);
				Message newMessage = null;
				if (!ownerMessageID.equals("") && (Message.exists(ownerMessageID,database))){	// Reply
					newMessage = forum.putNewReplay(ownerMessageID,messageTitle,messageBody,user,forumCategory);
					reply = true;
					/* Envia email para o dono da mensagem */
					try{
						Message fatherMessage = new Message(ownerMessageID,database);
						User ownerUser = fatherMessage.getSender();
						Mail.send("graw@tci.ufal.br",Mail.serviceID,ownerUser.getEmail(),"Mensagem no fórum respondida.","Sua mensagem sobre '"+fatherMessage.getTitle()+"' foi respondida.\nPara visualizar a resposta acesse www.graw.tci.ufal.br e acesse o fórum de discussão da comunidade : "+community.getTitle());
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{	//Tópico Principal
					newMessage = forum.putNewMessage(messageTitle,messageBody,user,forumCategory);
				}
				this.session.removeAttribute("ownerMessageID");
				this.session.removeAttribute("replyID");
				if (reply){
					ServletUtility.sendRedirect(response,ServletUtility.FORUM_VIEW_TOPIC+"?messageID="+ownerMessageID+"&categoryID="+categoryID);
				}else{
					ServletUtility.sendRedirect(response,ServletUtility.FORUM_MESSAGES_PAGE+"?categoryID="+categoryID);
				}
			}catch(Exception e){
				Utility.log(Utility.ERROR_FILE_LOG,e);
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
			}
		}
	}
}

