/*
 * @created 26/07/2004 21:30:46
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
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
 * Gets the current session serialized in the xml format.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 26/07/2004 21:30:46
 */
public class CurrentOpenedSessionServlet extends HttpServlet{
    
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
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
      throws IOException, ServletException {      
       
        String sessionID = request.getParameter("sessionID");
        Session session = null;
       
        try {
        
	        if (sessionID == null || sessionID.equals(""))        
		        session = MassFacade.getInstance().getCurrentOpenedSurvey();
	        else
	            session = MassFacade.getInstance().getSession(sessionID);  

            response.getWriter().println(SessionXMLPull.getInstance().pullSession(session).toString());
        } catch (PersistentObjectNotFoundException e) {
            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,e.getMessage()).toString());
        }            
    }
}
