package br.ufal.graw.web.validation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.web.ServletUtility;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */

public class ValidationServlet extends HttpServlet{
	
	public static final String UFAL = "U";
	public static final String UFAL_COMPUTER_SCIENCE = "CS";
	public static final String CAMPINA_GRANDE = "CG";
	
	private HttpSession session;
	private DatabaseLayer database;
	
	private final String SIGNIN_ACADEMIC_PAGE = ServletUtility.CONTEXT_PATH+"signIn/signNewAcademic3.jsp";
	private final String CONFIRM_ACADEMIC_PAGE = ServletUtility.CONTEXT_PATH+"signIn/confirm.jsp";
	
	public void doPost(HttpServletRequest request , HttpServletResponse response)
		throws ServletException, IOException
	{
		this.session = request.getSession(false);
		this.database = (DatabaseLayer)this.session.getAttribute("database");
		
		/** Parametros do request */
		String name  			= request.getParameter("nameac");
		String grawLogin 		= request.getParameter("login");
		String matriculation    = request.getParameter("matriculation");
		String email   			= request.getParameter("email");
		String from   			= request.getParameter("from");
		
		/* Paremetros da sessão */
		String academicCourseID = (String)this.session.getAttribute("academicCourseID");
		
		/* Inicializando a Lista de mensagens de erro */
		List errors = new ArrayList();
		
		/* Preparando os parametros para o algoritmo de validação */
		Hashtable parameters = new Hashtable();
		parameters.put("email",email);
		parameters.put("login",grawLogin);
		parameters.put("registration",matriculation);
		parameters.put("academicCourseID",academicCourseID);
		
		/* Setando para casos de erro */
		this.session.setAttribute("nameac",name);
		this.session.setAttribute("login",grawLogin);
		this.session.setAttribute("matriculation",matriculation);
		this.session.setAttribute("email",email);
		this.session.setAttribute("from",from);
						
		if (!AcademicCourse.exists(academicCourseID,this.database)){
			errors.add("Curso acadêmico inválido! = "+academicCourseID);
		} else {
			/* Iniciar processo de validação */
			
			ValidatorIF validator 	= null;
			UfalSearcher seacher 	= new UfalSearcher();
			UfalUserBean userBean 	= new UfalUserBean();
			
			if (from.equalsIgnoreCase(UFAL) ){
				validator = new UfalValidator();
				try{
					userBean  = seacher.lookupUser( matriculation );
				}catch(Exception e){System.out.println(e.getMessage());}
			}else if (from.equalsIgnoreCase(UFAL_COMPUTER_SCIENCE) ){
				validator = new UfalComputerScienceValidator();
				try{
					userBean  = seacher.lookupUser( matriculation );
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}else if (from.equalsIgnoreCase(CAMPINA_GRANDE) ){
				validator = new CampinaGrandeValidator();
				userBean.setEmail((String)parameters.get("email"));
			}
			
			
			/* Coloca o Bean do Usuário na sessão */
			this.session.setAttribute("userBean",userBean);
			
			/* Seta alguns parametros finais */
			parameters.put("user",userBean);
			/* Chama o algorítmo de validação adequado */
			Iterator iter = validator.validate(parameters).iterator();
			while (iter.hasNext()){
				errors.add(iter.next());
			}
		}
		
		this.session.setAttribute("errorMsgs",errors);
		
		if (errors.size()>0){
			ServletUtility.sendRedirect(response,SIGNIN_ACADEMIC_PAGE);
		}else{
			ServletUtility.sendRedirect(response,CONFIRM_ACADEMIC_PAGE);
		}
	}
	
	
//	private List doValidation(String from, String registration, Hashtable parameters){
//		ValidatorIF validator 	= null;
//		UfalSearcher seacher 	= new UfalSearcher();
//		UfalUserBean userBean 	= new UfalUserBean();
//
//		if (from.equalsIgnoreCase(UFAL) ){
//			validator = new UfalValidator();
//			try{
//				userBean  = seacher.lookupUser( registration );
//			}catch(Exception e){System.out.println(e.getMessage());}
//		}else if (from.equalsIgnoreCase(UFAL_COMPUTER_SCIENCE) ){
//			validator = new UfalComputerScienceValidator();
//			try{
//				userBean  = seacher.lookupUser( registration );
//			}catch(Exception e){
//				System.out.println(e.getMessage());
//			}
//		}else if (from.equalsIgnoreCase(CAMPINA_GRANDE) ){
//			validator = new CampinaGrandeValidator();
//			userBean.setEmail((String)parameters.get("email"));
//		}
//
//
//		/* Coloca o Bean do Usuário na sessão */
//		this.session.setAttribute("userBean",userBean);
//
//		/* Seta alguns parametros finais */
//		parameters.put("user",userBean);
//		/* Chama o algorítmo de validação adequado */
//		return validator.validate(parameters);
//	}
	
	
}

