/**
 * Administrator.java
 *
 * @author gr@W's Developers
 */

package br.ufal.graw.web.administrator;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.graw.Community;
import br.ufal.graw.User;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.Mail;
import br.ufal.graw.web.ServletUtility;

public class Administrator {
	DatabaseLayer	banco;
	String			criterio = "";
	int				kindOfAcademic;
	
	public Administrator(String userID, DatabaseLayer banco) {
		this.banco = banco;

		// Recupera hierarquia do usuário
		Vector c = banco.query("SELECT academicID, kindOfAcademic FROM admins WHERE userID=\"" + userID + "\"");
		Hashtable l = (Hashtable)c.get(0);
		String academicID = (String)l.get("academicID");
		kindOfAcademic = Integer.parseInt((String)l.get("kindOfAcademic"));
		
		// Define critério de busca
		switch (kindOfAcademic) {
			case 1: // Curso
				criterio = "AND academicCourseID=\"" + academicID + "\" ";
				break;
			case 2: // Departamento
				criterio = "AND departmentID=\"" + academicID + "\" ";
				break;
			case 3: // Instituição
				criterio = "AND instituteID=\"" + academicID + "\" ";
				break;
		}
	}
	
	// Retorna verdadeiro se o administrador é o geral
	public boolean administradorGeral() {
		return kindOfAcademic == 4;
	}
	
	// Retorna as disciplinas pendentes de aceite
	public Vector getDisciplinasPendentes() {
		return banco.query("SELECT communityID, title FROM community " +
					"WHERE kind=\"" + Community.DISCIPLINE + "\" AND status=\"" + Community.WAITING + "\" ORDER BY title");
	}
	
	// Retorna os cursos externos pendentes de aceite
	public Vector getCursosExternosPendentes() {
		return banco.query("SELECT c.communityID, title FROM community AS c, extracourse AS ec " +
					"WHERE kind=\"" + Community.EXTRA_COURSE + "\" AND status=\"" + Community.WAITING + "\" AND c.communityID=ec.communityID " +
					"AND instituteID IS NULL AND departmentID IS NULL AND academicCourseID IS NULL " +
					"ORDER BY title");
	}
	
	// Retorna os cursos academicos pendentes de aceite
	public Vector getCursosAcademicosPendentes() {
		return banco.query("SELECT c.communityID, title FROM community AS c, extracourse AS ec " +
					"WHERE kind=\"" + Community.EXTRA_COURSE + "\" AND status=\"" + Community.WAITING + "\" AND c.communityID=ec.communityID AND " +
					(kindOfAcademic == 4 ? "instituteID IS NOT NULL AND departmentID IS NOT NULL " +
					"AND academicCourseID IS NOT NULL " : criterio) + "ORDER BY title");
	}
	
	// Retorna os grupos externos pendentes de aceite
	public Vector getGruposExternosPendentes() {
		return banco.query("SELECT communityID, title FROM community, grouping " +
					"WHERE kind=\"" + Community.GROUP + "\" AND status=\"" + Community.WAITING + "\" AND communityID=groupingID" +
					" AND courseID IS NULL ORDER BY title");
	}
	
	// Retorna os grupos pendentes de aceite ligados a uma disciplina
	public Vector getGruposDisciplinaPendentes() {
		Hashtable l1, l2;
		Vector c1, c2, v = new Vector();
		
		// Seleciona todas as disciplinas que possuem um grupo ligadas a ela
		c1 = banco.query("SELECT title, groupingID FROM community AS c, discipline AS d, grouping " +
					"WHERE c.communityID=courseID AND courseID=d.communityID " + criterio +
					"ORDER BY title");

		// Se tem algum grupo ligado
		for (int i = 0; i < c1.size(); i++) {
			l1 = (Hashtable)c1.get(i);
			c2 = banco.query("SELECT title FROM community WHERE status=\"" + Community.WAITING + "\" AND communityID=\"" +
					l1.get("groupingID") + "\"");
					
			// Cria a linha
			if (c2.size() > 0) {
				l2 = new Hashtable();
				l2.put("communityID", (String)l1.get("groupingID"));
				l2.put("gtitle", (String)((Hashtable)c2.get(0)).get("title"));
				l2.put("dtitle", (String)l1.get("title"));
				v.addElement(l2);
			}
		}

		return v;
	}
	
	// Retorna os grupos pendentes de aceite ligados a curso externo
	public Vector getGruposCursoExternoPendentes() {
		Hashtable l1, l2;
		Vector c1, c2, v = new Vector();

		// Seleciona todos os cursos externos que possuem um grupo ligado a ele
		c1 = banco.query("SELECT title, groupingID FROM community AS c, extracourse AS ec, grouping " +
				"WHERE c.communityID=courseID AND courseID=ec.communityID AND instituteID IS NULL AND " +
				"departmentID IS NULL AND academicCourseID IS NULL ORDER BY title");

		// Se tem algum grupo ligado
		for (int i = 0; i < c1.size(); i++) {
			l1 = (Hashtable)c1.get(i);
			c2 = banco.query("SELECT title FROM community WHERE status=\"" + Community.WAITING + "\" AND communityID=\"" +
					l1.get("groupingID") + "\"");
			
			// Cria linha
			if (c2.size() > 0) {
				l2 = new Hashtable();
				l2.put("communityID", (String)l1.get("groupingID"));
				l2.put("gtitle", (String)((Hashtable)c2.get(0)).get("title"));
				l2.put("ctitle", (String)l1.get("title"));
				v.addElement(l2);
			}
		}
		
		return v;
	}
	
	// Retorna os grupos pendentes de aceite ligados a curso academico
	public Vector getGruposCursoAcademicoPendentes() {
		Hashtable l1, l2;
		Vector c1, c2, v = new Vector();

		// Seleciona todos os cursos academicos que possuem um grupo ligado a ele
		c1 = banco.query("SELECT title, groupingID FROM community AS c, extracourse AS ec, grouping " +
				"WHERE c.communityID=courseID AND courseID=ec.communityID AND " +
				(kindOfAcademic == 4 ? "instituteID IS NOT NULL AND departmentID IS NOT NULL " +
				"AND academicCourseID IS NOT NULL " : criterio) + "ORDER BY title");

		// Se tem algum grupo
		for (int i = 0; i < c1.size(); i++) {
			l1 = (Hashtable)c1.get(i);
			c2 = banco.query("SELECT title FROM community WHERE status=\"" + Community.WAITING + "\" AND communityID=\"" +
					l1.get("groupingID") + "\"");

			// Cria  linha
			if (c2.size() > 0) {
				l2 = new Hashtable();
				l2.put("communityID", (String)l1.get("groupingID"));
				l2.put("gtitle", (String)((Hashtable)c2.get(0)).get("title"));
				l2.put("ctitle", (String)l1.get("title"));
				v.addElement(l2);
			}
		}
		
		return v;
	}
	
	// Retorna o nome da comunidade
	public String getNomeComunidade(String communityID) {
		Vector c = banco.query("SELECT title FROM community WHERE communityID=\"" + communityID + "\"");
		return (String)((Hashtable)c.get(0)).get("title");
	}

	// Retorna a descrição da comunidade
	public String getDescricaoComunidade(String communityID) {
		Vector c = banco.query("SELECT description FROM community WHERE communityID=\"" + communityID + "\"");
		return (String)((Hashtable)c.get(0)).get("description");
	}

	public void aceiteComunidade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean nao_aceite = false;
		String communityID = request.getParameterValues("communityID")[0];
		String explicacao = "";
		int kind = Integer.parseInt(request.getParameterValues("kind")[0]);
		
		DatabaseLayer database = (DatabaseLayer)request.getSession(false).getAttribute("database");
		User communityCreator = null;
		Community community = null;
		try {
			community = AbstractCommunity.getRealCommunity(communityID,database);
			communityCreator = community.getResponsible();
		} catch (Exception e){
			e.printStackTrace();
		}
				
		try {
			nao_aceite = request.getParameterValues("aceite")[0].equals("1");
			explicacao = request.getParameterValues("explicacao")[0];
		} catch (Exception e) {};
		
		String titleEmail = "",bodyEmail = "";
		
		if (!nao_aceite) {
			try {
				titleEmail = "graW - Sua comunidade foi aceita!";
				bodyEmail  = "\n"+ communityCreator.getFirstName() +",\nParabéns!!! Sua proposta de comunidade foi aceita!\n\nTítulo da comunidade: "+community.getTitle()+"\n\nAgora sua comunidade será acessível no ambiente. Basta ir no menu Comunidade->Buscar e localizar sua comunidade nos diretórios existentes.";
				banco.update("UPDATE community SET status=\"" + Community.ACTIVED + "\" WHERE communityID=\"" + communityID + "\"");
			} catch (Exception e) { }

		} else try {
				titleEmail = "graW - Sua comunidade foi recusada.";
				bodyEmail  = "\n"+ communityCreator.getFirstName() +",\nA comunidade proposta por você não foi aceita\n\nComunidade: "+community.getTitle()+"\nParecer: '"+explicacao+"'\n\nNão esqueça que você poderá propor quantas comunidades quiser... \n\nO graW agradece.\n\n graW - Graduação na Web\nDúvidas: graw@tci.ufal.br";
			switch (kind) {
				case 1: // Disciplinas
					banco.update("DELETE FROM discipline WHERE communityID=\"" + communityID +"\"");
					banco.update("DELETE FROM course WHERE communityID=\"" + communityID +"\"");
					break;
				case 2: // Cursos
					banco.update("DELETE FROM extracourse WHERE communityID=\"" + communityID +"\"");
					banco.update("DELETE FROM course WHERE communityID=\"" + communityID +"\"");
					break;
				case 3: // Grupos
					banco.update("DELETE FROM grouping WHERE groupingID=\"" + communityID +"\"");
			}

			banco.update("DELETE FROM community WHERE communityID=\"" + communityID +"\"");
			
			Mail.send("graw@tci.ufal.br",
					  Mail.serviceID,
					  communityCreator.getEmail(),
					  titleEmail,
					  bodyEmail);
		} catch (Exception e) { }

		response.sendRedirect(ServletUtility.ADMIN_MAIN_PAGE);
	}
	
	// Retorna uma lista de professores
	public Vector getProfessores() {
		return banco.query("SELECT userID, name FROM user WHERE kindOfUserID=\"P\"");
		
	}
	
	// Retorna uma lista de disciplinas
	public Vector getDisciplinas() {
		return banco.query("SELECT communityID, title FROM community WHERE kind=\"" + Community.DISCIPLINE +
						   "\" AND status=\"" + Community.ACTIVED + "\"");
	}
	
	// Atribui uma disciplina a um professor
	public void atribuiDisciplina(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userID = request.getParameterValues("professorID")[0];
		String communityID = request.getParameterValues("disciplineID")[0];
		try {
			banco.update("INSERT INTO teaches VALUES (\"" + userID + "\", \"" + communityID + "\")");
		} catch (Exception e) { }

		response.sendRedirect(ServletUtility.ADMIN_MAIN_PAGE);
	}
}
