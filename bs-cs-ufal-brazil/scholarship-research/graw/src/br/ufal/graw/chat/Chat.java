package br.ufal.graw.chat;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.rmi.*;
import br.ufal.graw.Config;

/**
 *  <code>Chat</code>
 * Classe responsavel por mostrar e gerenciar um cliente de bate-papo
 *
 * @version 0.01
 * @author Fábio José Rodrigues Pinheiro
 * @since   0.01
*/
public class Chat extends HttpServlet {
	
	private String caminho;			// Caminho das páginas
	private String portaRMI;		// Porta do Servidor RMI
	private String serverChat;		// Servidor rodando o chat
	private String portaSocket;		// Porta do socket do chat
	private String caminhoServlet;	// Caminho dos servlets do chat

	// Incia parametros
	public void init (ServletConfig config) throws ServletException {
		super.init(config);

		Config conf = new Config();
		caminho = conf.getChat("html");
		caminhoServlet = conf.getChat("servlet");
		serverChat = conf.getChat("server");
		portaSocket = conf.getChat("socket");
		portaRMI = conf.getChat("rmi");
	}
	
	/**Imprime a interface do chat*/
	void printFormEntrada(PrintWriter out, String apelido, Vector usuarios, String salaID){
		String html =
			"<html>\n" +
			"<head>\n" +
			"<title>Bate-papo - GraW</title>\n" +
			"</head>\n" +
//			"<frameset rows=\"65,*,65\" framespacing=0 onunload=\"window.open('" + caminhoServlet + "Chat?acao=sair&apelido=" + apelido + "')\">\n" +
			"<frameset rows=\"78,200,*,60\" framespacing=0>\n" +
			"    <frame name=topo src=\"" + caminhoServlet + "br.ufal.graw.chat.Topo\" scrolling=no noresize>\n" +
			"    <frame name=whiteboard src=\"" + caminhoServlet + "br.ufal.graw.whiteboard.WhiteBoard?apelido=" + apelido + "&salaID=" + salaID + "\" scrolling=no noresize>\n" +
			"    <frame name=mensagens src=\"http://" + serverChat + ":" + portaSocket + "/" +
				apelido + "/" + salaID + "\" scrolling=auto>\n" +
			"    <frame name=base src=\"" + caminhoServlet + "br.ufal.graw.chat.Base?usuario=" + apelido;
		
		for (int i = 0; i < usuarios.size(); i++) html += "&usuario=" + (String) usuarios.get(i);
		
		html +=
			"\" scrolling=no noresize>\n" +
			"</frameset>\n" +
			"</html>";
		
		out.println(html);
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	// Recebe requisição e executa a ação apropiada
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Recupera ação
		String acao = request.getParameterValues("acao")[0];

		// Executa ação apropiada
		if (acao.equals("escrever")) escrever(request, out);
		else if (acao.equals("enviaapelido")) enviaApelido(request, out);
//		else if (acao.equals("sair")) sair(request, out);
		
		// Finaliza servlet
		out.close();
	}

	// Envia apelido quando o usuário entra no chat, inicia o cliente
	public void enviaApelido(HttpServletRequest request, PrintWriter out) {
		
		// Recupera parametros
		String apelido = request.getParameterValues("apelido")[0];
		String salaID = request.getParameterValues("salaID")[0];
		String salaName = request.getParameterValues("salaName")[0];

		// Inicia chat para este usuário
		try {
			EnviaMensagem enviaMsg = new EnviaMensagem();
			Vector usuarios = enviaMsg.consegueUsuarios(salaID);
			
			if (usuarios == null) {
				out.println(
					"<html>\n" +
					"<head>\n" +
					"<title>Bate-papo - GraW</title>\n" +
					"</head>\n" +
					"<body>\n" +
					"<h1>Servidor temporariamente fora do ar</h1>" +
					"</body>\n" +
					"</html>");
					
				return;
			}

			// Verifica se o usuário já está na sala
//			boolean achouUsuario = false;
//			for (int i = 0; i < usuarios.size(); i++)
//				if (usuarios.get(i).equals(apelido)) {
//					achouUsuario = true;
//					break;
//				}
	
			// Se usuário já estiver em alguma sala, remova-o e o ponha na nova
			if (enviaMsg.usuarioExistente(apelido)) {
				enviaMsg.fecharBrowse(apelido);
				enviaMsg.sair(apelido);
			}

			// Cria sessão
			HttpSession session = request.getSession(true);

			// Prepara sessão para este usuário
			session.setAttribute("apelido", apelido);
			session.setAttribute("salaID", salaID);
			session.setAttribute("salaName", salaName);
			session.setAttribute("objetoremoto", enviaMsg);

			// Monta frame do chat
			printFormEntrada(out, apelido, usuarios, salaID);
 			
		} catch (RemoteException e) { }
	}
	
	// Envia mensagem em broadcast ou unicast
	public void escrever(HttpServletRequest request, PrintWriter out) {

		// Verifica se a sessão é válida
		HttpSession session = request.getSession(false);
		if (null == session) {
			// Lombinhas quando a sessão expirrar
			return;
		}

		// Verifica se é uma mensagem reservada
		boolean reservadamente = false;
		try {
			String tmp = request.getParameterValues("reserv")[0];
			reservadamente = true;
		} catch (Exception e) { }

		// Recupera atributos
		String salaID = (String) session.getAttribute("salaID");
		String salaName = (String) session.getAttribute("salaName");
		String apelidoOrigem = (String) session.getAttribute("apelido");
		String apelidoDestino = request.getParameterValues("usuarios")[0];

		// Constrói mensagem
		Mensagem msg = new Mensagem(request, apelidoOrigem, reservadamente, apelidoDestino, caminho);
		msg.construirMensagem();
		String mes = msg.getMensagem();

		// Recupera instância do RMI
		try{
			EnviaMensagem env = (EnviaMensagem) session.getAttribute("objetoremoto");
			if (env != null) {

				// Verifica se o usuário ainda está na sala
				Vector usuarios = env.consegueUsuarios(salaID);
				if (usuarios.contains(apelidoOrigem)) {

					// Manda mensagem apenas se alguma coisa foi digitada
					if (!msg.getMensagemOriginal().equals(""))

						// Envia mensagem em broadcast ou unicast
						if (msg.getBroadcast()) env.mandarMensagemBroadcast(mes, salaID);
						else if (usuarios.contains(apelidoDestino)) env.mandarMensagemUnicast(apelidoOrigem, mes, apelidoDestino, true, salaID);
						else env.mandarMensagemUnicast(apelidoOrigem, "<p class=\"usuario1\"><b>" + apelidoDestino + "</b> Já saiu da sala </p>", "", false, salaID);

					// Monta base do chat
					Base base = new Base();
					base.setValues(out, request, usuarios, caminho, caminhoServlet, salaName, apelidoOrigem, salaID);
					base.printForm();

				//response.sendRedirect(caminho+"topo.html");
				} // else {response.sendRedirect(caminhoservlet+"Salas?apelido="+apelidoorigem);}
			}else System.err.println("env foi null da classe Chat");
//		} catch (MensagemException e) {
//			System.err.println(e.getMessage()+" na acao escrever mensagem exception");
		} catch (Exception ep) {
			System.err.println(ep.getMessage()+" na acao escrever ");
			ep.printStackTrace();
		}
	}
	
	// Remove usuário da sala
/*	public void sair(HttpServletRequest request, PrintWriter out) {

		// Verifica se a sessão é válida
		HttpSession session = request.getSession(false);
		if (null == session) {
			// Lombinhas quando a sessão expirrar
			return;
		}

		try{
			// Recupera atributos e remove usuários
			String apelido = (String) session.getAttribute("apelido");
			EnviaMensagem env = (EnviaMensagem) session.getAttribute("objetoremoto");
			env.sair(apelido);

			// Fecha a janela do browser
			out.println("<html>");
			out.println("<body onLoad=\"window.close()\"></body>");
			out.println("</html>");

			// Termina a sessão deste usuário
//			session.invalidate();

		} catch (Exception e) {
			System.err.println("Erro removendo usuário: " + e.getMessage());
		}
	} */

}