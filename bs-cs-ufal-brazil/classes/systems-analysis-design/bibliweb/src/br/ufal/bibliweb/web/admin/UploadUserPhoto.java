package br.ufal.bibliweb.web.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.ParamPart;

import br.ufal.bibliweb.DatabaseLayer;
import br.ufal.bibliweb.web.ServletUtility;
import br.ufal.bibliweb.User;
import br.ufal.bibliweb.Status;

/**
 * UploadUserPhoto.java
 *
 * @author Marcello Junior
 */
public class UploadUserPhoto extends HttpServlet{
	
	private DatabaseLayer database;
	private HttpSession session;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		this.session  = request.getSession(false);
		this.database = (DatabaseLayer)session.getAttribute("database");
		this.processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
				
		File fileToSave = null;
		/** Parser for get the file */
     	MultipartParser mp = null;
     	Part part;
      	ParamPart paramPart;
      	String name = "";
      	String value = "";
      	FilePart filePart = null;
      	String fileName = "";
		boolean fileExists = false;
		long documentSize = 0;
				
		String uploadDir = this.database.getConfiguration().getUploadDir();
		String userID = "";
		
		try{

			mp = new MultipartParser(request,(int)ServletUtility.DISK_SPACE_FOR_EACH_PHOTO);

			/** The directoty where the file will be save */
			File dir = new File(uploadDir);

			/** if it not exists, then create the directory */
			if (! dir.exists()){
				if (!dir.mkdir()){	//if can't create.
					this.session.setAttribute("message","Problemas com a área de documentos dessa disciplina. impossível criar diretório: "+dir.getAbsolutePath());
					ServletUtility.sendRedirect(response,ServletUtility.ADMIN_PAGE);
					return;
				}
			}

      		while ((part = mp.readNextPart()) != null) {
      			if (part.isParam()) {
          			// it's a parameter part
					name = part.getName();
          			paramPart = (ParamPart) part;
          			value = paramPart.getStringValue();
          			if (name.equals("userID")){ //pega o valor do identificador do usuário, o campo hidden deve acima do file
          				userID = value;
          			}
				}else if (part.isFile()) {
						String userInfoPage = ServletUtility.ADMIN_PAGE_USERINFOR+"?userID="+userID;
    	      			// it's a file part
        	  			filePart = (FilePart) part;
						fileName = filePart.getFileName();
					if (fileName!=null){
						if (filePart.getContentType().equals("image/pjpeg")){
							try {
								fileName = userID+".jpg";
								System.out.println(fileName);
								fileToSave = new File(dir+File.separator+fileName);
								
								if (!(fileExists=fileToSave.exists())){
									File filesInDir[];
       								documentSize = filePart.writeTo(fileToSave);	//Save in disk and catch its size.
									this.updateUserPhotoExt(userID,"jpg"); //atualiza dados
									/* Se o tamanho do arquivo eh maior que MAX_SIZE */
									if (documentSize > ServletUtility.DISK_SPACE_FOR_EACH_PHOTO){
										fileToSave.delete();
										this.session.setAttribute("message","O tamanho máximo do arquivo é de : "+ServletUtility.DISK_SPACE_FOR_EACH_PHOTO/1024+" Kb. No entanto o seu arquivo possui : "+documentSize/1024+" Kb.");
										ServletUtility.sendRedirect(response,userInfoPage);
										return;
       								}
								}
							} catch (Exception e){
								fileToSave.delete();
								e.printStackTrace();
								this.session.setAttribute("message","Ocorreu um erro desconhecido no momento da criação do arquivo no servidor.");
								ServletUtility.sendRedirect(response,ServletUtility.ADMIN_CREATE_USER_PAGE);
								return;
							}
						} else { // Se não for imagem!
							System.out.println("tipo de arquivo inválido");
							this.session.setAttribute("message","O arquivo deve estar no formato jpg!");
							ServletUtility.sendRedirect(response,userInfoPage);
							return;
						}
					}
				}//end if (part.isFile())
			}//end while
			if (fileExists){
				this.session.setAttribute("message","Já exixte um arquivo ou diretório com esse nome.");
				ServletUtility.sendRedirect(response,ServletUtility.ADMIN_PAGE_USERINFOR+"?userID="+userID);
				return;
			}else{
				this.session.setAttribute("message","Imagem cadastrada com sucesso! Usuário passa a ser ativo!");
				ServletUtility.sendRedirect(response,ServletUtility.ADMIN_PAGE_USERINFOR+"?userID="+userID);
			}
		}catch(IOException ioe){
			mp = null;
			fileToSave.delete();
			this.session.setAttribute("message","O arquivo excedeu o tamnaho máximo de: "+(ServletUtility.DISK_SPACE_FOR_EACH_PHOTO/(1024*1024))+"Mb.");
			ServletUtility.sendRedirect(response,ServletUtility.ADMIN_CREATE_USER_PAGE);
		}catch(Exception e){
			fileToSave.delete();
			this.session.setAttribute("message",e.getMessage());
			ServletUtility.sendRedirect(response,ServletUtility.ADMIN_CREATE_USER_PAGE);
		}
	}
	
	/** Atualiza a informação da extensão da imagem do usuário. */
	public static void updateUserPhotoExt(String userID, String photoExtension){
		try{
			new DatabaseLayer().update("UPDATE \"user\" SET photo_extension = '"+photoExtension+"' , status_id = "+Status.USER_ACTIVED+" WHERE user_id = '"+userID+"'");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		UploadUserPhoto.updateUserPhotoExt("1019095261310","jpg");
	}
}

