/**
 * ElectMonitor.java
 *
 * @author Created nonamepibic
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import br.ufal.graw.Discipline;
import br.ufal.graw.Utility;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.Professor;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.ServletUtility;

import br.ufal.graw.exception.DisciplineNotTaughtException;
import br.ufal.graw.exception.UserNotFoundException;

public class DeleteMonitor extends HttpServlet {
	
	private DatabaseLayer database;
	private HttpSession session;
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		this.session = request.getSession(false);
		String userID = request.getParameter("userID");
		String disciplineID = ((Discipline)this.session.getAttribute("community")).getID();
				
		try{
			Professor professor = (Professor)this.session.getAttribute("user");
			professor.deleteMonitor(userID,disciplineID);
			String namemonitor = AbstractUser.getRealUser(userID,(DatabaseLayer)session.getAttribute("database")).getName();
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),namemonitor+" deixa de ser monitor dessa comunidade!");
		}catch(UserNotFoundException unfe){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Aluno nao encontrado.");
		}catch (ClassCastException cce){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"So quem pode remover um monitor eh um professor.");
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.processRequest(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.processRequest(request,response);
	}
}
