package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.Document;
import br.ufal.graw.Utility;
import br.ufal.graw.User;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.ServletUtility;

public class EditDocument extends HttpServlet{

	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		String documentID,documentTitle,documentDescription;
		Document document;
		DatabaseLayer database;

		try{
			database = (DatabaseLayer) request.getSession(false).getAttribute("database");
			documentID = request.getParameter("documentID");
			documentTitle = request.getParameter("documentTitle");
			documentDescription = request.getParameter("documentDescription");

			document = new Document(documentID,database);
			document.setData(documentTitle,documentDescription);

			ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_MAIN_PAGE);
			
		}catch(ResourceNotFoundException e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Documento nao encontrado em nossos registros.");
		}
	}
}
