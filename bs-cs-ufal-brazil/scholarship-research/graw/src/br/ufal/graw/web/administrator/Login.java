package br.ufal.graw.web.administrator;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.web.administrator.Administrator;

public class Login extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// Conecta com o banco
			DatabaseLayer banco = new DatabaseLayer();

			// Recupera parametros
			String login = request.getParameterValues("login")[0];
			String senha = request.getParameterValues("password")[0];
		
			// Busca o administrador
			Vector linha = banco.query("SELECT userID FROM user WHERE login=\"" + login + "\" AND password=PASSWORD('" + senha + "') AND kindOfUserID=\"A\"");
		
			// Administrador não encontrado
			if (linha.size() == 0) {
				banco.disconnect();
				ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Login inválido!");
				return;
			}
		
			// Cria sessão
			HttpSession session = request.getSession(true);
			
			// Poe alguns atributos na sessão
			session.setAttribute("user", new Administrator((String)((Hashtable)linha.get(0)).get("userID"), banco));
			session.setAttribute("database",banco);
			
			// Login OK
			System.out.println(ServletUtility.ADMIN_MAIN_PAGE);
			response.sendRedirect(ServletUtility.ADMIN_MAIN_PAGE);

		} catch (Exception e) {
			ServletUtility.sendRedirect(response,ServletUtility.ERROR_FATAL_GENERIC_PAGE,"Banco de dados temporariamente desativado. Tente mais tarde novamente.");
		}
	}
}
