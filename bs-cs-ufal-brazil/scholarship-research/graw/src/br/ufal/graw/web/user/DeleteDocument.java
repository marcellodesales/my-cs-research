package br.ufal.graw.web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.io.IOException;
import java.io.File;

import br.ufal.graw.Utility;
import br.ufal.graw.User;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Document;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.web.ServletUtility;

public class DeleteDocument extends HttpServlet{

	private DatabaseLayer database;

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
			String documentID;
			Document document;
			File fileToDelete = null;
			
			this.database = (DatabaseLayer)request.getSession(false).getAttribute("database");
			documentID = request.getParameter("documentID");
			document = new Document(documentID,this.database);
			fileToDelete = new File(document.getAddress());
			if (fileToDelete.delete()){
				Document.deleteDocument(documentID,this.database);
				Utility.log(Utility.OPERATION_FILE_LOG,"Documento ["+documentID+"], deletado por: "+((User)(request.getSession(false).getAttribute("user"))).getName());
			}else{
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Arquivo nao pôde ser deletado");
				return;
			}
			
			ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_MAIN_PAGE,"Documento apagado com sucesso!");
		}catch(ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			this.getServletContext().log("Delete-> ",rnfe);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Documento não encontrado em nossos registros.");
		}catch(Exception e){
			Utility.log(Utility.ERROR_FILE_LOG,e);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
	}
}
