package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Enumeration;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.Group;
import br.ufal.graw.Utility;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.CommunitySpecialInvitation;
import br.ufal.graw.exception.ResourceNotFoundException;

public class ConfirmInvitation extends HttpServlet{
	
	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}
	
	/*public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	 }*/

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		try{
			
			CommunitySpecialInvitation invitation = (CommunitySpecialInvitation)request.getSession(false).getAttribute("invitation");
			DatabaseLayer database = (DatabaseLayer)request.getSession(false).getAttribute("database");
			User user = (User)request.getSession(false).getAttribute("user");
			boolean confirmed;
			if (invitation.getGuestID()!=null){
				if (invitation.getGuestID().trim().equals("")){
					confirmed = false;
				}else{
					confirmed = true;
				}
			}else{
				confirmed = false;
			}
			if (!confirmed){
				invitation.setGuestID(user.getID());
				ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Confirmação efetuada com sucesso. Para acessar essa comunidade entre no sistema e acesse-a atraves do menu de comunidades.");
			}else{
				ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Esse convite ja foi confirmado antes por: "+invitation.getGuest().getName()+", qualquer dúvida entre em contato com graw@tci.ufal.br ");
			}
		}catch(Exception e){
			e.printStackTrace();
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		 }
			
	}
	

}
