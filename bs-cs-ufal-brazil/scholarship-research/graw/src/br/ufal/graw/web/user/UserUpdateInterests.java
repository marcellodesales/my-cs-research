package br.ufal.graw.web.user;

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
import br.ufal.graw.web.ServletUtility;

public class UserUpdateInterests extends HttpServlet{

	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		String disciplineID;
		String param;
		Vector disciplineToInterests;
		User user;
		Enumeration disciplineIDs;
		DatabaseLayer database;

		try{
			user = (User)request.getSession(false).getAttribute("user");
			database = (DatabaseLayer)request.getSession(false).getAttribute("database");

			disciplineIDs = request.getParameterNames();
			disciplineToInterests = new Vector();

			while (disciplineIDs.hasMoreElements()){
				disciplineID = (String)disciplineIDs.nextElement();
				param =request.getParameter(disciplineID);
				if (param.equals("COURSE")){
					disciplineToInterests.add(disciplineID);
				}
			}
			
			user.setInterests(disciplineToInterests);
			
			ServletUtility.sendRedirect(response,"/course/","Interesses atualizados com sucesso!",request.getHeader("Referer"));
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Ocorreram problemas no servidor ao tentar atualizar os interesses.");
		}

	}
}
