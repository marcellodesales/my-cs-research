package br.ufal.graw.chat;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import br.ufal.graw.Config;

public class Topo extends HttpServlet {
	
	String caminho;				// Caminho das páginas e figuras
	String caminhoServlet;		// Caminho do servlet
	
	// Inicia o servlet
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Caminhos
		Config conf = new Config();
		caminho = conf.getChat("html");
//		caminhoServlet = conf.getChat("servlet");
	}
	
	// Recebe apelido e monta a página
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Incia escrita do servlet
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Escreve conteúdo da página
		out.println(
			"<html>\n" +
			"<head>\n" +			
//			"<link href=\"" + caminho + "style.css\" rel=stylesheet type=\"text/css\">\n" +
//			"<script>var sair = false;</script>\n" +
			"</head>\n" +

			"<body " + /* onUnload=\"if (!sair) window.open('" + caminhoServlet + "br.ufal.graw.chat.Chat?acao=sair')\" */ "class=\"bodytopo\" marginheight=0 marginwidth=0 topmargin=0 leftmargin=0>\n" +

//			"<body onunload=\"alert('Hi')\" class=\"bodytopo\" marginheight=0 marginwidth=0 topmargin=0 leftmargin=0>\n" +
//			"<body class=\"bodytopo\" marginheight=0 marginwidth=0 topmargin=0 leftmargin=0>\n" +
			"<img border=0 src=\"" + caminho + "sources/images/topo_chat.jpg\"" + /* usemap=\"#topo_chat\" */ ">\n" +
//			"<map name=\"topo_chat\">\n" +
//			"    <area shape=\"rect\" coords=\"589,53,639,73\" onClick=\"sair = true\" href=\"" + caminhoServlet + "br.ufal.graw.chat.Chat?acao=sair\" title=\"Sair da sala\" target=\"_parent\">\n" +
//			"</map>\n" + 

//			"<table border=0 cellpadding=0 cellspacing=0 width=\"100%\">\n" +
//			"<tr>\n" + 
//			"    <td align=\"left\"><img src=\"" + caminho + "imagens/chat1.gif\"><img src=\"" +
//				caminho + "imagens/chat2.gif\"><img src=\"" + caminho + "imagens/chat3.gif\"><img src=\"" +
//				caminho + "imagens/chat4.gif\"><img src=\"" + caminho + "imagens/chat5.gif\"></td>\n" +
//			"    <td align=\"right\">\n" +
//			"        <a onClick=\"sair = true\" href=\"" + caminhoServlet + "br.ufal.graw.chat.Chat?acao=sair\" target=\"_parent\"><img border=0 src=\"" +
//				caminho + "imagens/bt_sair.gif\" alt=\"Sair da sala\"></a>&nbsp;&nbsp;&nbsp;\n" +
//			"    </td>\n" +
//			"</tr>\n" +
//			"</table>\n" +


			"</body>\n" +
			"</html>\n");
		
		// Finaliza servlet
		out.close();
	}
}