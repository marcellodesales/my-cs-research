package br.ufal.graw.web.administrator;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import br.ufal.graw.web.administrator.Administrator;
import br.ufal.graw.Community;
import br.ufal.graw.exception.MailException;
import br.ufal.graw.User;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.Mail;

public class Update extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		Administrator admin = (Administrator)session.getAttribute("user");
		int acao = Integer.parseInt((String)request.getParameterValues("acao")[0]);
		String communityID = request.getParameter("communityID");
		DatabaseLayer database = (DatabaseLayer)session.getAttribute("database");
		User communityCreator = null;
		Community community = null;
		try {
			community = AbstractCommunity.getRealCommunity(communityID,database);
			communityCreator = community.getResponsible();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		switch (acao) {
			case 1: {// Dar aceite de novas comunidades
				try{
					Mail.send("graw@tci.ufal.br",
						  Mail.serviceID,
						  communityCreator.getEmail(),
						  "graW - Sua comunidade foi aceita!",
							  "\n"+ communityCreator.getFirstName() +",\nParabéns!!! Sua proposta de comunidade foi aceita!\n Título da comunidade: "+community.getTitle());
				} catch (Exception e){
					e.printStackTrace();
				}
				admin.aceiteComunidade(request, response);
				break;
			}
			case 2: // Atribui uma disciplina a um professor
				admin.atribuiDisciplina(request, response);
				break;
		}
	}
}
