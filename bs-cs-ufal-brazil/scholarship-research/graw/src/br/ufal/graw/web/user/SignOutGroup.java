package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.Group;
import br.ufal.graw.Utility;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;

public class SignOutGroup extends HttpServlet{
	
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
			Group group  	= (Group)(request.getSession(false).getAttribute("group"));
			String userID 	= request.getParameter("userID");
			
			if ( userID != null ){
				user = AbstractUser.getRealUser(userID,(DatabaseLayer)(request.getSession(false).getAttribute("database")));
			}
			
			/* Note que se o usuraio for o administrador do grupo,
			 será excluído, alem da sua assinatura, o grupo, todas as assinaturas
			 dos outros membros e todos os outros relacionamentos do qual
			 o grupo participa
			 */
			
			user.signOutGroup(group);
			
			
			ServletUtility.sendRedirect(response,ServletUtility.GROUP_CHOOSE);
			Utility.log(Utility.OPERATION_FILE_LOG,"SignOutGroup usuário responsável: "+( (User)(request.getSession(false).getAttribute("user"))).getName()+" Excluindo: "+user.getName(),this);
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
			
	}
	

}
