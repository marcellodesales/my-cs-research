/*
 * @created 02/08/2004 21:19:13
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpe.cin.stp.mass.model.MassFacade;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;
import br.ufpe.cin.stp.mass.xmlpull.ResponseXMLPull;
import br.ufpe.cin.stp.mass.xmlpull.SessionXMLPull;
/**
 * @created 02/08/2004 21:19:13
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class CreateNewSessionServlet extends HttpServlet {

    /**
     * Respond to a GET request for the content produced by
     * this servlet.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are producing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        response.setContentType("text/xml");
        
        try {
            Session session = MassFacade.getInstance().getCurrentOpenedSurvey();
            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,"New Session Failed: Session Already Created!").toString());
            
        } catch (PersistentObjectNotFoundException e) {
            
	        String surveyTitle = request.getParameter("surveyTitle");
	        String questionTitle = request.getParameter("questionTitle");
	        String[] items = request.getParameterValues("items");
	        
	        StringBuffer requiredFilds = new StringBuffer();
	        boolean error = false;
	        if (surveyTitle == null || surveyTitle.equals("")){
	            requiredFilds.append("[surveyTitle] ");
	            error = true;
	        }
	        if (questionTitle == null || questionTitle.equals("")){
	            requiredFilds.append("[questionTitle] ");
	            error = true;
	        }
	        if (items == null){
	            requiredFilds.append("[items[]]");
	            error = true;
	        }   
	        if (error)
	            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,"New Session Failed: Required Fields - "+requiredFilds).toString());
	        else {        
	            Session session = MassFacade.getInstance().createNewSurvey(surveyTitle,questionTitle,items);
	            response.getWriter().println(SessionXMLPull.getInstance().pullSession(session).toString());
	        }
        }
    }
}
