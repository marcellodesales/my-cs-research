/*
 * @created 31/07/2004 17:27:47
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
import br.ufpe.cin.stp.mass.model.session.SessionClosedException;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;
import br.ufpe.cin.stp.mass.xmlpull.ResponseXMLPull;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 17:27:47
 */
public class AnswerSessionServlet extends HttpServlet{

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
		response.setContentType("text/xml");
    
        String sessionID = request.getParameter("sessionID");
        String senderID = request.getHeader("User-Agent"); 
        
        String[] questionItemsID = request.getParameterValues("itemID");
        
        StringBuffer requiredFilds = new StringBuffer();
        boolean error = false;
        if (sessionID == null || sessionID.equals("")){
            requiredFilds.append("[sessionID] ");
            error = true;
        }
        if (senderID == null || senderID.equals("")){
            requiredFilds.append("[senderID] ");
            error = true;
        }
        if (questionItemsID == null || questionItemsID[0].equals("")){
            requiredFilds.append("[itemID[]] ");
            error = true;
        }   
        if (error)
            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,"Answer Failed: Required Fields - "+requiredFilds).toString());
        else {
	        try {
	            MassFacade.getInstance().answerSession(sessionID,senderID,questionItemsID);
	            System.out.println();
	            System.out.println("---------- Vote Incoming ---------");
	            System.out.println("Vote for session: "+sessionID);
	            System.out.println("Vote From: "+senderID);
	            System.out.println("Location: "+request.getRemoteAddr());
	            
	    		response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(true,"Answer Successfully Processed!").toString());
	            
	        } catch (PersistentObjectNotFoundException e) {
	            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,e.getMessage()).toString());
	        } catch (SessionClosedException e) {
	            response.getWriter().println(ResponseXMLPull.getInstance().pullResponse(false,e.getMessage()).toString());
	        }
        }
    }
}
