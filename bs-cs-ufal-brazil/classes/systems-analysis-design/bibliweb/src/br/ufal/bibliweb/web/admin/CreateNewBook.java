package br.ufal.bibliweb.web.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufal.bibliweb.DatabaseLayer;
import br.ufal.bibliweb.ExemplarType;
import br.ufal.bibliweb.AcademicCourse;
import br.ufal.bibliweb.Book;
import br.ufal.bibliweb.web.ServletUtility;

/**
 * CreateNewUser.java
 *
 * @author Marcello Junior
 */
public class CreateNewBook extends HttpServlet{
	
	private HttpSession session;
	private DatabaseLayer database;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException{
		this.session  = request.getSession(false);
		
		this.database = (DatabaseLayer)session.getAttribute("database");
		String exemplarTypeID = ((ExemplarType)session.getAttribute("exemplarType")).getID();
		String academicCourseID = ((AcademicCourse)session.getAttribute("academicCourse")).getID();
		String formPage = ServletUtility.ADMIN_CREATE_EXEMPLAR_PAGE+"newFormExemplar.jsp?exemplarTypeID="+exemplarTypeID;
		
		String categoryID      = request.getParameter("categoryID");
		String languageID      = request.getParameter("languageID");
		String physicalPlaceID = request.getParameter("physicalPlaceID");
		String title           = request.getParameter("title");
		String keywords        = request.getParameter("keywords");
		String acquisitionDate = request.getParameter("acquisitionDate");
		String quantity		   = request.getParameter("quantity");
		
		String ISBN  		   = request.getParameter("ISBN");
		String volume		   = request.getParameter("volume");
		String authors		   = request.getParameter("authors");
		String edition		   = request.getParameter("edition");

		if ((title == null) || (title.equals(""))){
			String message = "O campo do título está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((keywords == null) || (keywords.equals(""))){
			String message = "O campo das palavras chaves está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((acquisitionDate == null) || (acquisitionDate.equals(""))){
			String message = "O campo da data de aquisição está vazio!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((quantity == null)|| (quantity.equals(""))){
			String message = "A quantidade de livros não foi especificada!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((ISBN == null)|| (ISBN.equals(""))){
			String message = "O campo do ISBN deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((volume == null)|| (volume.equals(""))){
			String message = "O campo do volume deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((authors == null)|| (authors.equals(""))){
			String message = "O campo dos autores deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else
		if ((edition == null)|| (edition.equals(""))){
			String message = "O campo da edição do livro deve ser informado!";
			this.session.setAttribute("message",message);
			ServletUtility.sendRedirect(response,formPage);
		} else {
			try{
				String newBookID = Book.createNewBook(physicalPlaceID,categoryID,languageID,ISBN,title,edition,keywords,volume,authors,acquisitionDate,Integer.parseInt(quantity),this.database);
				session.removeAttribute("exemplarType");
				session.removeAttribute("academicCourse");
				session.setAttribute("message","Livro cadastrado com sucesso!");
				ServletUtility.sendRedirect(response,ServletUtility.ADMIN_PAGE_EXEMPLARINFOR+"?exemplarID="+newBookID);
			}catch(NumberFormatException nfe){
				this.session.setAttribute("message","O campo da quantidade deve possuir um numeral inteiro!");
				ServletUtility.sendRedirect(response,formPage);
			}catch(Exception e){
				this.session.setAttribute("message",e.getMessage());
				ServletUtility.sendRedirect(response,formPage);
			}
		}
	}
}

