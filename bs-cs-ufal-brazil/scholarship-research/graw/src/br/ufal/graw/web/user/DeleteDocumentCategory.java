/**
 * DeleteDocumentCategory.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.user;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.ResourceCategory;
import br.ufal.graw.Category;
import br.ufal.graw.DatabaseLayer;

public class DeleteDocumentCategory extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;
	
	private void deleteDocumentCategory(String categoryID, HttpServletResponse response){
		
		if ((categoryID == null) || categoryID.equals("")){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE);
		} else {
			try{
				System.out.println("1");
				Category category = new Category(categoryID,database);
				System.out.println("2");
				ResourceCategory.deleteCategory(categoryID,database);
				System.out.println("3");
				this.session.setAttribute("message","A pasta \""+category.getTitle()+"\" apagada com sucesso!");
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CATEGORY_PAGE);
				
			} catch(CategoryNotFoundException caee){
				this.session.setAttribute("message",caee.getMessage());
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CREATE_CATEGORY_PAGE);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.session  = request.getSession(false);
		this.database = (DatabaseLayer)session.getAttribute("database");
		String categoryID  = request.getParameter("categoryID");
		this.deleteDocumentCategory(categoryID,response);
	}
	
	
}

