package br.ufal.graw.whiteboard;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import br.ufal.graw.Config;


public class WhiteBoard extends HttpServlet {
	
	String server;
	String socket;
	
	// Inicia o Servlet
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Ler arquivo de configuração
		Config conf = new Config();
		server = conf.getWhiteBoard("server");
		socket = conf.getWhiteBoard("socket");
	}
	
	// Monta página para abrir o applet
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String apelido = request.getParameterValues("apelido")[0];
		String salaID = request.getParameterValues("salaID")[0];
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>\n" +
					"<head><title>WhiteBoard</title></head>\n" +
					"<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>\n" +
					"<applet codebase=\"http://" + server + "\" archive=\"WhiteBoard.jar\" code=\"WBCliente\" width=640 height=200>\n" +
					"<param name=server value=\"" + server + "\">\n" +
					"<param name=porta value=\"" + socket + "\">\n" +
					"<param name=apelido value=\"" + apelido + "\">\n" +
					"<param name=salaID value=\"" + salaID + "\">\n" +
					"</applet>\n" +
					"</body>\n" +
					"</html>");		
		out.close();
	}
}