/**
 * CreateNewDocument.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.ParamPart;

import br.ufal.graw.Config;
import br.ufal.graw.User;
import br.ufal.graw.Community;
import br.ufal.graw.Group;
import br.ufal.graw.Document;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;

import br.ufal.graw.web.ServletUtility;

import br.ufal.graw.exception.DisciplineNotFoundException;


public class CreateNewDocument extends HttpServlet{

	private DatabaseLayer database;
	private HttpSession session;


	public void doPost(HttpServletRequest request, HttpServletResponse response){
		this.session  = request.getSession(false);
		this.database = (DatabaseLayer)session.getAttribute("database");
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		String documentTitle, documentAddress, documentDescription, communityID, categoryID;
		File fileToSave = null;
		/** Parser for get the file */
     	MultipartParser mp = null;
     	Part part;
      	ParamPart paramPart;
      	String name = "";
      	String value = "";
      	FilePart filePart =null;
      	String fileName = "";
		boolean fileExists = false;
		long documentSize = 0;
		boolean group = true;

		documentTitle       = "";
		documentAddress     = "";
		documentDescription = "";
		categoryID = "";
		String uploadDir = ((Config) session.getAttribute("conf")).getUploadDir() ;
		
		communityID  = ((Community)this.session.getAttribute("community")).getID();
		
		
		try{

			mp = new MultipartParser(request, (int)ServletUtility.DISK_SPACE_FOR_EACH_DISCIPLINE);


			/** The directoty where the file will save */
			File dir = new File(Document.getAddressBase(communityID,database));
			/*
			if (group){	// Coloca os arquivos do grupo em subdiretorios do curso.
				
				/* Verifica se o diretorio do curso ja existe.*/
			/*
				if ( ! dir.exists() ){
					/* Se nao existir entao crie-o */
			/*
					if (!dir.mkdir()){	//if can't create.
						ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Problemas com a �rea de documentos dessa disciplina. Imposs�vel criar diret�rio. ");
						return;
					}
				}
				/* Diret�rio do grupo */
			/*
				dir = new File(uploadDir+courseID+File.separator+groupID+File.separator);
			 }*/

			/** if it not exists, then create the directory */
			if (! dir.exists()){
				if (!dir.mkdir()){	//if can't create.
					ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Problemas com a �rea de documentos dessa disciplina. imposs�vel criar diret�rio: "+dir.getAbsolutePath());
					return;
				}
			}

      		while ((part = mp.readNextPart()) != null) {
      			if (part.isParam()) {
          			// it's a parameter part
          			name = part.getName();
          			paramPart = (ParamPart) part;
          			value = paramPart.getStringValue();
          			if (name.equals("documentTitle")){
          				documentTitle = value;
          			}else if (name.equals("documentDescription")){
          				documentDescription = value;
          			}else if (name.equals("categoryID")){
						categoryID      = value;
						System.out.println(categoryID);
					}
        		}else if (part.isFile()) {
          			// it's a file part
          			filePart = (FilePart) part;
          			fileName = filePart.getFileName();
					try {
						fileToSave = new File(dir+File.separator+fileName);
						documentAddress = fileToSave.getAbsolutePath();
						if (!(fileExists=fileToSave.exists())){
							File filesInDir[];
       						long dirSize=0;
       						documentSize = filePart.writeTo(dir);	//Save in disk and catch its size.

							/* Look for diretory's size */
							filesInDir = dir.listFiles();
       						for (int i=0 ; i < filesInDir.length ; i++){
       							dirSize+= filesInDir[i].length();
       						}
							/* If directory is full */
       						if (dirSize > ServletUtility.DISK_SPACE_FOR_EACH_DISCIPLINE){
       							fileToSave.delete();
	   							ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"N�o existe espa�o dispon�vel para esse arquivo. Espa�o Livre = "+(ServletUtility.DISK_SPACE_FOR_EACH_DISCIPLINE-(dirSize-documentSize))/(1024)+" Kb");
								return;
       						}
						}
					} catch (Exception e){
						fileToSave.delete();
						ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE);
						return;
					}
				}//end if (part.isFile())
			}//end while
			if (fileExists){
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CREATE_NEW_PAGE+"?categoryID="+categoryID,"J� exixte um arquivo ou diret�rio com esse nome.");
				return;
			}else if (documentTitle.equals("")){
				fileToSave.delete();
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CREATE_NEW_PAGE+"?categoryID="+categoryID,"O campo do t�tulo est� vazio.");
			}else if ( documentAddress.equals("") || (fileName==null) ){
				fileToSave.delete();
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CREATE_NEW_PAGE+"?categoryID="+categoryID,"O campo do arquivo est� vazio.");
			}else if (documentDescription.equals("")){
				fileToSave.delete();
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENT_CREATE_NEW_PAGE+"?categoryID="+categoryID,"O campo da descri��o est� vazio.");
			}else if (communityID == null){
				fileToSave.delete();
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE);
			}else{
				
				Document newDocument ;
				System.out.println(categoryID);
       			newDocument = Document.createNewDocument(categoryID,this.database);
				
				newDocument.setData(documentTitle,fileName,documentSize,documentDescription);
				
				ServletUtility.sendRedirect(response,ServletUtility.DOCUMENTS_MAIN_PAGE+"?categoryID="+categoryID);
				
			}
		}catch(IOException ioe){
			mp = null;
			fileToSave.delete();
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"O arquivo excedeu o tamnaho m�ximo de: "+(ServletUtility.DISK_SPACE_FOR_EACH_DISCIPLINE/(1024*1024))+"Mb.");
		}catch(Exception e){
			fileToSave.delete();
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
	}
}

