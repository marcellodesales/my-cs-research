/**
 * ElectMonitor.java
 *
 * @author Created nonamepibic
 */

package br.ufal.graw.web.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import br.ufal.graw.Discipline;
import br.ufal.graw.Community;
import br.ufal.graw.Course;
import br.ufal.graw.Group;
import br.ufal.graw.User;
import br.ufal.graw.Utility;
import br.ufal.graw.Professor;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.ServletUtility;

import br.ufal.graw.exception.DisciplineNotTaughtException;
import br.ufal.graw.exception.UserNotFoundException;

/**

Sessao:
user - O professor
community - Pode ser uma disciplina,  curso ou grupo.

 */

public class EditCommunity extends HttpServlet {
	
	private DatabaseLayer database;
	private HttpSession session;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		/* Parametros para comunidades */
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		/* Parametros para Cursos */
		String hours = request.getParameter("hours");
		String program = request.getParameter("program");
		String bibliography = request.getParameter("bibliography");
		
		/* Parametros para grupos */
		String goal = request.getParameter("goal");
		
		/* Parametros para Disciplinas */
		String disciplineCode = request.getParameter("disciplineCode");
				
		try{
			
			Community community = (Community)request.getSession(false).getAttribute("community");
			
			if (title==null || description==null){
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"O campo título ou descrição não pode ser vazio");
				return;
			}
			community.setTitle(title);
			community.setDescription(description);
			
			if (community instanceof Group){
				((Group)community).setGoal(goal);
			}else if (community instanceof Course){
				if (hours==null || program==null || bibliography==null){
					ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Voce não pode deixar campos vazios");
					return;
				}
				((Course)community).setHours(hours);
				((Course)community).setProgram(program);
				((Course)community).setBibliography(bibliography);
				
				if (community instanceof Discipline){
					if (disciplineCode==null){
						ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,"Voce não pode deixar campos vazios");
						return;
					}
					((Discipline)community).setCode(disciplineCode);
				}
			}
			
			ServletUtility.sendRedirect(response,ServletUtility.COMMUNITY_INFO_PAGE,"Dados alterados com sucesso.");
		
		}catch(Exception e){
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_GENERIC_PAGE,e.getMessage());
		}
		
	}
}
