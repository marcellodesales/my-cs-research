package br.ufal.graw.web.document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.activation.MimetypesFileTypeMap;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Document;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.web.ServletUtility;

public class Download extends HttpServlet{

	private DatabaseLayer database;

	public void doPost(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException,IOException{
		this.processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request , HttpServletResponse response){
		try{
			this.database = (DatabaseLayer)request.getSession(false).getAttribute("database");

			String documentID = request.getParameter("documentID");
			
			Document doc = new Document(documentID,this.database);
			File documentFile = new File(doc.getAddress());
			FileInputStream fileInput = new FileInputStream(documentFile);
			//RandomAccessFile raf = new RandomAccessFile(documentFile,"r");
			ServletOutputStream fileOutput = response.getOutputStream();
			MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
			int b;
			
			
			
			response.setContentType(mimetypesFileTypeMap.getContentType(documentFile));
			response.setContentLength((int)documentFile.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + documentFile.getName() + "\";");
			
			/*byte [] loader = new byte [ (int) raf.length() ];
			while ( (raf.read( loader )) > 0 ) {
              fileOutput.write( loader );
			 }*/
            
			
			while ((b=fileInput.read())!=-1){
				fileOutput.write(b);
			}
			fileInput.close();
			fileOutput.close();
			
		}catch(ResourceNotFoundException rnfe){
			rnfe.getMessage();
			this.log(this.getClass().toString(),rnfe);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Documento não encontrado.");
		}catch(FileNotFoundException fnfe){
			fnfe.getMessage();
			this.log(this.getClass().toString(),fnfe);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Documento não encontrado.");
		}catch(IOException ioe){
			ioe.getMessage();
			this.log(this.getClass().toString(),ioe);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Problemas com o arquivo no servidor.");
		}catch(Exception e){
			e.getMessage();
			this.log(this.getClass().toString(),e);
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		 }
	}

}
