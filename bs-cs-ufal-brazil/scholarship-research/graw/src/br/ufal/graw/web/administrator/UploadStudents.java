/**
 * UploadStudendts.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.administrator;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.StringTokenizer;
import java.util.Vector;

import java.sql.SQLException;
	
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.User;

import br.ufal.graw.exception.AcademicCourseNotFoundException;

public class UploadStudents extends HttpServlet {
	
	private HttpSession session;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// set content-type header and access the output stream
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		// provide the form when we first enter
		
		String line = null;
		String message ="";
		this.session = req.getSession(false);
		
		String academicCID = (String)this.session.getAttribute("academicCourseID");
		String value = (String)this.session.getAttribute("userType");
		DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
		System.out.println(value);
		try{
			AcademicCourse academicCourse = new AcademicCourse(academicCID,database);
			BufferedReader reader = req.getReader();
			//academicCourse.insertAllStudents(reader);
			
			while ((line = reader.readLine()) != null)
				if (line.indexOf("Content-Type:") != -1)
					break;
			// read one more line
			reader.readLine();
			// now we are at the content of the file,
			// so we read lines until we get to the one with "-----------------------------"
			// which marks the end of the content of the file
			while ((line = reader.readLine()) != null){
				if (line.indexOf("-----------------------------") == -1){
					StringTokenizer token = new StringTokenizer(line,",");
					try {
						String username = token.nextToken().toLowerCase();
						String name     = token.nextToken();
						String matri    = token.nextToken().toUpperCase();
						
						username = username.trim();
						matri = matri.trim();
						if ( value.equals(User.STUDENT) ){
							academicCourse.insertNewAcademicStudent(username,matri);
							
						}else if ( value.equals(User.PROFESSOR) ){
							academicCourse.insertNewAcademicProfessor(username,matri);
							message+="Professores ";
						}
						
					} catch (java.util.NoSuchElementException e){
					}
				}else break;
			}
			if ( value.equals(User.STUDENT) ){
				message+="Estudantes ";
			}else{
				message+="Professores ";
			}
			this.session.removeAttribute("academicCourseID");
			message+= "do curso acadêmico "+academicCourse.getName()+" inseridos com sucesso!";
			this.session.setAttribute("message",message);
			res.sendRedirect("../administrator/index3.jsp");
			
		} catch (AcademicCourseNotFoundException acnfe){
			message = acnfe.getMessage();
			this.session.setAttribute("message",message);
			res.sendRedirect("../administrator/index3.jsp");
		} catch (Exception e){
			message = e.getMessage();
			this.session.setAttribute("message",message);
			res.sendRedirect("../administrator/index3.jsp");
		}
	}
}
