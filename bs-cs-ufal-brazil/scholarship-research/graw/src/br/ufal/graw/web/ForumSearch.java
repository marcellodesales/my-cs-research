package br.ufal.graw.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import br.ufal.graw.Searcher;
import br.ufal.graw.Critery;
import br.ufal.graw.Message;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Community;

/**
 *
 * @author Marcello de Sales
 */
public class ForumSearch extends HttpServlet{

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
			DatabaseLayer database = (DatabaseLayer) request.getSession(false).getAttribute("database");
			String communityID = ((Community)request.getSession(false).getAttribute("community")).getID();
			String words[] = request.getParameterValues("w");
			String query;
			
			query = Searcher.mountQuery("messageid","message",communityID,new String[]{"description","title"},words);
			database.query(query);
			
			
			
			//ServletUtility.sendRedirect(response,ServletUtility.SUCESS_GENERIC_PAGE,"Resultado da busca.",request.getHeader("Referer"));
		}catch(NullPointerException npe){
			ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Primeiro selecione alguma comunidade");
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
	}
	
	public static void main(String args[]){
		String  query;
		java.util.Vector result;
		DatabaseLayer database = new DatabaseLayer();
		query = Searcher.mountQuery("messageid","message","1016044335989",new String[]{"description","title"},new String[]{"como"});
		result = database.query(query);
		Searcher.printResult(result);
		Object[] o = Searcher.getObjects(result,"messageid",database,Message.class);
		Message[] messages = new Message[o.length];
		for (int i=0 ; i< o.length ; i++){
			messages[i] = (Message)o[i];
		}
		
	}
}

