/**
 * ServletUtility.java
 *
 * @author Created by Omnicore CodeGuide
 */
package br.ufal.graw.web;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.net.URLEncoder;

public class ServletUtility{
	
	
	
	
	/** http://www.graw.tci.ufal.br */
	public static String COMPLETE_DNS = "http://localhost";
	public static String CONTEXT_PATH = COMPLETE_DNS + ":8080/graw/";
	public static final String SUCESS_GENERIC_PAGE = CONTEXT_PATH+"status/sucessGeneric.jsp";
	public static final String ERROR_GENERIC_PAGE = CONTEXT_PATH+"status/errorGeneric.jsp";
	public static final String ERROR_FATAL_GENERIC_PAGE = CONTEXT_PATH+"status/errorFatal.jsp";
	
	public static final String INVITATION_CONFIRM = CONTEXT_PATH+"servlet/br.ufal.graw.web.user.ConfirmInvitation";
	public static final String INVITATION_GUEST = CONTEXT_PATH+"signIn/externalUser.jsp";
	
	/** CONTEXT_PATH + "" */
	public static final String MAIN = COMPLETE_DNS + CONTEXT_PATH;
	/** The error page page url when a login fails. */
	//public static final String LOGIN_ERROR_PAGE    = "/error/login.jsp?message=";
	/** The error page page url when a login fails. */
//	public static final String ACCESS_ERROR_PAGE    = "/error/acess.jsp";
	
	/** Pode receber uma variavel message especificando a mensagem*/
///	public static final String STATUS_PAGE    = "/genericStatus.jsp";
	
	/** The main User page url. \n user/index.jsp*/
	public static final String USER_MAIN_PAGE  = CONTEXT_PATH+"user/index.jsp";
	public static final String ADMIN_MAIN_PAGE = CONTEXT_PATH+"administrator/index3.jsp";
	
	/** The main Student page url. */
	public static final String STUDENT_MAIN_PAGE   = CONTEXT_PATH+"student/index.jsp";
	/** The page when a student updates your profile */
//	public static final String STUDENT_UPDATEDATA_SUCESS = "/student/config/updateDataSucess.jsp";
	/** Error when a student wrong his passwd or his new passwd is diferent of confirm passwd.*/
//	public static final String STUDENT_UPDATEPASSWORD_ERROR = "/student/config/studentUpdatePasswordError.jsp";
	/** The main Professor page url. */
//	public static final String PROFESSOR_MAIN_PAGE = "/professor/index.jsp";
	/** The main Professor page url. */
//	public static final String PROFESSOR_ELECTMONITOR_SUCESS = "/discipline/monitor/index.jsp?message=";
	/** The page url when a new user is sucessfully signed. */
//	public static final String NEW_USER_PAGE       = "/signIn/newUserCreated.jsp";
	/** The error page page url when an error occored in the sign in page. */
///	public static final String NEW_USER_ERROR_PAGE = "/signIn/signNewUser.jsp?error=";
	
	/** The error page page url when an error occor on the server. */
//	public static final String ERROR_SERVER_PAGE   = "/error/server.jsp?message=";
	
	
	/** The error page url when the professor enters invalid data. */
//	public static final String UPDATE_ERROR_PAGE   = "/professor/config/config.jsp?error=";

	/** The error page url when the professor enters invalid data. */
	public static final String COURSE_MAIN_PAGE    = CONTEXT_PATH+"course/index.jsp";
	public static final String COMMUNITY_MAIN_PAGE = CONTEXT_PATH+"community/index.jsp";
	public static final String COMMUNITY_INFO_PAGE = CONTEXT_PATH+"community/information.jsp";
	public static final String GROUP_MAIN_PAGE     = CONTEXT_PATH+"group/index.jsp";
	public static final String GROUP_CHOOSE        = CONTEXT_PATH+"user/chooseGroup.jsp";
	/** When a new topic was sucessfully created. */
	public static final String FORUM_MAIN_PAGE  = CONTEXT_PATH+"forum/index.jsp";
	public static final String FORUM_MESSAGES_PAGE  = CONTEXT_PATH+"forum/getMessages.jsp";
	/** /forum/newtopic.jsp" */
	public static final String FORUM_CREATE_MESSAGE_PAGE  = CONTEXT_PATH+"forum/newtopic.jsp";
	public static final String FORUM_VIEW_TOPIC = CONTEXT_PATH+"forum/viewtopic.jsp";
	public static final String FORUM_CREATE_CATEGORY_PAGE = CONTEXT_PATH+"forum/createNewCategory.jsp";
	/** When a new link was sucessfully created. */
//	public static final String LINK_CREATED_SUCESSULLY = "/professor/link/newLinkCreated.jsp";
	/** When a link was sucessfully edited. */
//	public static final String LINK_EDITED_SUCESSULLY = "/discipline/link/index.jsp";

	public static final String DOCUMENT_MAIN_PAGE = CONTEXT_PATH+"document/index.jsp";
	public static final String DOCUMENTS_MAIN_PAGE    = CONTEXT_PATH+"document/getDocuments.jsp";
	/** /link/getLinks.jsp" */
	public static final String LINKS_MAIN_PAGE    = CONTEXT_PATH+"link/getLinks.jsp";
	/** /link/index.jsp" */
	public static final String LINK_CATEGORY_PAGE = CONTEXT_PATH+"link/index.jsp";
	public static final String DOCUMENT_CATEGORY_PAGE = CONTEXT_PATH+"document/index.jsp";
	/** /link/createNewCategory.jsp" */
	public static final String LINK_CREATE_CATEGORY_PAGE = CONTEXT_PATH+"link/createNewCategory.jsp";
	/** /link/createNewLink.jsp" */
	public static final String LINK_CREATE_NEW_PAGE = CONTEXT_PATH+"link/createNewLink.jsp";
	/** /link/editLink.jsp" */
	public static final String LINK_EDIT_PAGE = CONTEXT_PATH+"link/editLink.jsp";
	
	public static final String DOCUMENT_CREATE_CATEGORY_PAGE = CONTEXT_PATH+"document/createNewCategory.jsp";
	
	public static final String DOCUMENT_CREATE_NEW_PAGE = CONTEXT_PATH+"document/createNewDocument.jsp";
	/** /link/editLink.jsp" */
	public static final String DOCUMENT_EDIT_PAGE = CONTEXT_PATH+"document/editDocument.jsp";
	
	/** When a new document was sucessfully created. */
//	public static final String DOCUMENT_CREATED_SUCESSULLY = "/professor/document/newDocumentCreated.jsp";
	/** When a new document was failed created. */
//	public static final String DOCUMENT_CREATION_FAILED = "/professor/document/createNewDocument.jsp?error=";
	
//	public static final String MAIL_SUCESS = "/tools/email/sucess.jsp";
	
	public static final String ADMINISTRATOR_MAIN_PAGE  = CONTEXT_PATH+"administrator/config.jsp";
	public static final String ADMINISTRATOR_DISCIPLINE = CONTEXT_PATH+"administrator/set/disciplineInformation.jsp";
	public static final String ADMINISTRATOR_USER       = CONTEXT_PATH+"administrator/set/userInformation.jsp";
//	public static final String ADMINISTRATOR_LOGIN_FAILED = "/admin/index.jsp";
	
	public static final String CREATE_DISCIPLINE_PAGE = CONTEXT_PATH+"user/newDiscipline3.jsp";
	public static final String CREATE_COURSE_PAGE     = CONTEXT_PATH+"user/newCourse3.jsp";
	public static final String CREATE_GROUP_PAGE      = CONTEXT_PATH+"user/newGroup2.jsp";
	public static final String CREATE_COMMUNITY_PAGE  = CONTEXT_PATH+"user/newCommunity.jsp";

	//public static final String UPLOAD_SUCESS   = "/discipline/upload/uploadSucess.jsp";
	//public static final String UPLOAD_FAILED   = "/discipline/upload/uploadFailed.jsp";

	public static final long DISK_SPACE_FOR_EACH_DISCIPLINE = 15*1024*1024; //15MB
	
	public static void sendRedirect(HttpServletResponse response, String page, String message){
		try{
			page = ServletUtility.removeParameter("message",page);
			String caracter = page.indexOf("?")==-1 ? "?" : "&";
			if (message != null){
				message = URLEncoder.encode(message);
				page += caracter+"message="+message;
			}
			response.sendRedirect(page);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
	public static void sendRedirect(HttpServletResponse response, String page, String message, String from){
		try{
			page = ServletUtility.removeParameter("message",page);
			String caracter = page.indexOf("?")==-1 ? "?" : "&";
			
			if (message != null){
				message = URLEncoder.encode(message);
				page += caracter+"message="+message;
			}
			if (from != null){
				from = URLEncoder.encode(from);
				page = ServletUtility.removeParameter("from",page);
				page += "&from="+from;
			}
			response.sendRedirect(page);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
	/** Remove da url os parametros que tenham o nome de parameterName */
	private static String removeParameter(String parameterName, String url){
		int start = url.indexOf(parameterName);
		int end ;
		
		while (start!=-1) {
			if (start==-1){ // A url nao contem o parametro
				return url;
			}
		
			/* Procurar proximo separador */
			end = url.indexOf("&",start);
			if (end==-1) { //Nao existem mais parametros
				end = url.length()-1;
			}else{ //Existem mais parametros
				start++; //Necessário para pegar a ?
			}
			url = url.substring(0,start-1)+url.substring(end+1,url.length());
			start = url.indexOf(parameterName);
		}
		return url;
	}

	public static void sendRedirect(HttpServletResponse response, String page){
		try{
			response.sendRedirect(page);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
	public static void main(String args[]){
		try{
			ServletUtility su = new ServletUtility();
			System.out.println(su.removeParameter("message","http://localhost?message=\"oi pessoal\""));
		}catch(Exception ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
}

