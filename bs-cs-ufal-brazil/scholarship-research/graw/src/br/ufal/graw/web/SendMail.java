package br.ufal.graw.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.File;

import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.ParamPart;

import br.ufal.graw.Community;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AbstractCourse;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.MailException;
import br.ufal.graw.web.Mail;

public class SendMail extends HttpServlet{
	
	private String ATTACHMENTS_DIR = "attachments";
	/** Tamanho maximo do attachment */
	private static int MAX_SIZE = 1024*512; // 512 K

	public static int getMaxSizeInKb(){
		return MAX_SIZE/1024;
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		
			User sender 		= (User)request.getSession(false).getAttribute("user");
			DatabaseLayer database = (DatabaseLayer) request.getSession(false).getAttribute("database");
			Community community = (Community) request.getSession(false).getAttribute("community");
		
			String kindOfID 	= request.getParameter("type");
			String ID 			= request.getParameter("destinationID");
			String subject 		= request.getParameter("subject");
			String mailContents = request.getParameter("mailContents");
			
			File fileToSave = null;
			/** Parser for get the file */
     		MultipartParser mp = null;
     		Part part;
      		ParamPart paramPart;
			String name,value;
			FilePart filePart =null;
      		String fileName = "";
			boolean fileExists = false;
			long documentSize = 0;
			try{

				mp = new MultipartParser(request, (int)ServletUtility.DISK_SPACE_FOR_EACH_DISCIPLINE);


				/** The directoty where the file will save */
				String baseDir = "c:/desenv/graw/arq";
				
				File dir = new File(baseDir,this.ATTACHMENTS_DIR);
				System.out.println(dir.getAbsolutePath());
				/** if it not exists, then create the directory */
				if (! dir.exists()){
					if (!dir.mkdir()){	//if can't create.
						ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Problemas com o attachment no lado servidor. Experimente nao enviar o attachment.");
						Mail.log("Problemas com o attachment no lado servidor. Impossível criar o diretorio: "+dir.getAbsolutePath());
						return;
					}
				}

      			while ((part = mp.readNextPart()) != null) {
	      			if (part.isParam()) {
    	      			// it's a parameter part
        	  			name = part.getName();
          				paramPart = (ParamPart) part;
          				value = paramPart.getStringValue();
	          			if (name.equals("type")){
    	      				kindOfID = value;
        	  			}else if (name.equals("destinationID")){
          					ID = value;
          				}else if (name.equals("subject")){
							subject      = value;
						}else if (name.equals("mailContents")){
							mailContents      = value;
						}
	        		}else if (part.isFile()) {
    	      			// it's a file part
        	  			filePart = (FilePart) part;
          				fileName = filePart.getFileName();
						if (fileName!=null){
							try {
								fileToSave = new File(dir+File.separator+fileName);
						
								if (!(fileExists=fileToSave.exists())){
									File filesInDir[];
       								documentSize = filePart.writeTo(dir);	//Save in disk and catch its size.
								
									/* Se o tamanho do arquivo eh maior que MAX_SIZE */
	       							if (documentSize > MAX_SIZE ){
	       								fileToSave.delete();
										ServletUtility.sendRedirect(response,request.getHeader("Referer"),"O tamanho máximo do arquivo é de : "+MAX_SIZE/1024+" Kb. No entanto o seu arquivo possui : "+documentSize/1024+" Kb.");
										return;
       								}
								}
							} catch (Exception e){
								fileToSave.delete();
								ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Ocorreu um erro desconhecido no momento da criação do arquivo no servidor.");
								User user = (User)request.getSession(false).getAttribute("user");
								Mail.log(e,"ID="+user.getID()+", usuario="+user.getName()+", email="+user.getEmail());
								return;
							}
						}
					}//end if (part.isFile())
				}//end while
			
				String attachments[];
				if (fileName==null){
					attachments = new String[]{};
				}else{
					attachments = new String[]{fileToSave.getAbsolutePath()};
				}
				if (community!=null){
					try{
						subject+=" - Comunidade: "+community.getTitle();
					}catch(Exception e){
					}
				}
				
				
				if (kindOfID.equals("userID")){
					Mail.send(sender,AbstractUser.getRealUser(ID,database),subject,mailContents,attachments);
				}else if (kindOfID.equals("communityID")){
					Mail.send(sender,AbstractCommunity.getRealCommunity(ID,database),subject,mailContents,attachments);
				}else if (kindOfID.equals("admin")){
					Mail.send(sender,ID,subject,mailContents,attachments);
				}else{
					ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Tipo de destino desconhecido");
					return;
				}
				
				/* Depois de ter enviado o attachment apaga-o */
				if (fileToSave!=null){
					fileToSave.delete();
				}
				ServletUtility.sendRedirect(response,request.getHeader("Referer"),"Email enviado com sucesso!");
			}catch(MailException me){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Problemas com o servidor de E-mail.");
				me.printStackTrace();
			}catch(Exception e){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
				User user = (User)request.getSession(false).getAttribute("user");
				Mail.log(e,"ID="+user.getID()+", usuario="+user.getName()+", email="+user.getEmail());
			}
	}
}
	
