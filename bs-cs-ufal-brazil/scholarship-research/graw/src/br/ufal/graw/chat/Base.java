package br.ufal.graw.chat;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import br.ufal.graw.Config;

/**
 * Um objeto <code>Login</code>
 * Verifica se a sessao corrente é válida e apresenta a tela para login no sistema.
 *
 * @version 0.01
 * @author Fábio José Rodrigues Pinheiro
 * @since   0.01
*/
public class Base extends HttpServlet{

	private String caminho;				// Caminho das páginas, figuras e estilo
	private String caminhoServlet;		// Caminho dos servlets
	private PrintWriter out;
	private String usuarios;
	private String salaName;
	private String apelido;
	private String salaID;
	private boolean reserv;
	private String cor, tipo, apelidoDestino;

	// Inicia servlet, recupera os caminhos necessários
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Caminhos
		Config conf = new Config();
		caminho = conf.getChat("html");
		caminhoServlet = conf.getChat("servlet");
	}

	// Construtor
	public Base() { }

	// Define alguns atributos
	public void setValues(PrintWriter out, HttpServletRequest request, Vector usuarios, String caminho, String caminhoServlet, String salaName, String apelido, String salaID) {
		
		// recupera opções anteriores
		reserv = false;
		try {
			String tmp = request.getParameterValues("reserv")[0];
			reserv = true;
		} catch (Exception e) { }
		cor = request.getParameterValues("cor")[0];
		tipo = request.getParameterValues("tipomesg")[0];
		apelidoDestino = request.getParameterValues("usuarios")[0];		

		// Define atributos
		this.out = out;
		this.usuarios = getUsuarios(usuarios);
		this.caminho = caminho;
		this.caminhoServlet = caminhoServlet;
		this.salaName = salaName;
		this.apelido = apelido;
		this.salaID = salaID;
	}
	
	// Método chamando apenas quando o usuário entra no chat
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Recupera nome da sala
		HttpSession session = request.getSession(true);
		salaName = (String) session.getAttribute("salaName");
		apelido = (String) session.getAttribute("apelido");
		salaID = (String) session.getAttribute("salaID");

		// Incia escrita do servlet
		response.setContentType("text/html");
		out = response.getWriter();
		
		// Recupera usuarios da sala
		usuarios = getUsuarios(request.getParameterValues("usuario"));
		
		// Inicia variáveis
		reserv = false;
		cor = ""; tipo = ""; apelidoDestino = "";
		
		// Mostra formulário
		printForm();
		
		// Finaliza servlet
		out.close();
	}
	
	// Escreve conteúdo da página
	public void printForm() {

		out.println(
			"<html>\n" +
			"<head>\n" +
			"<link href=\"" + caminho + "sources/style/chat.css\" rel=stylesheet type=\"text/css\">\n" +
			"</head>\n" +
			"<body class=\"bodybase\" marginheight=0 marginwidth=0 topmargin=0 leftmargin=0 onLoad=\"document.formbase.mensagem.focus()\">\n" +
			"<form action=\"" + caminhoServlet + "br.ufal.graw.chat.Chat\" name=\"formbase\" method=\"post\">\n" +
			"<input type=\"hidden\" name=\"acao\" value=\"escrever\">\n" +
			"<table border=0 cellpadding=0 cellspacing=0 width=640>\n" +
			"<tr>\n" +
			"    <td colspan=2 class=\"formenvia\">\n" +
			"        <table border=0 cellpadding=0 cellspacing=0>\n" +
			"        <tr>\n" +
			"            <td colspan=3><font class=\"texto\">Digite sua mensagem abaixo</font></td>\n" +
			"            <td colspan=3><font class=\"texto\"><div align=\"right\">" + salaName + "</div></font></td>\n" +
			"        </tr>\n" +
			"        <tr>\n" +
			"            <td colspan=6>\n" +
			"                <input type=\"text\" size=80 name=\"mensagem\" class=\"campos\">&nbsp;&nbsp;&nbsp;&nbsp;\n" +
//			"                <a href=\"#\" onclick=\"window.open('" + caminhoServlet + "br.ufal.graw.whiteboard.WhiteBoard?apelido=" + apelido + "&salaID=" + salaID + "', 'WhiteBoard', 'width=320,height=240');\">WhiteBoard</a>\n" +
			"            </td>\n" +
			"        </tr>\n"+
			"        <tr>\n" +
			"            <td>\n" +
			"                <font class=\"campos\"><select size=\"1\" name=\"cor\" class=\"campos\">\n" +
			"                <option value=\"msg_preta\">Cor do texto</option>\n" +
			"                <option value=\"msg_preta\">---------</option>\n" +
			"                <option value=\"msg_azul\"" + (cor.equals("msg_azul") ? " selected" : "") + ">Azul</option>\n" +
			"                <option value=\"msg_vermelha\"" + (cor.equals("msg_vermelha") ? " selected" : "") + ">Vermelho</option>\n" +
			"                <option value=\"msg_laranja\"" + (cor.equals("msg_laranja") ? " selected" : "") + ">Laranja</option>\n" +
			"                <option value=\"msg_verde\"" + (cor.equals("msg_verde") ? " selected" : "") + ">Verde</option>\n" +
			"                </select></font>\n" +
			"            </td>\n" +
			"            <td><input type=\"checkbox\" name=\"reserv\" value=\"reservadamente\"" + (reserv ? " checked" : "") + "></td>\n" +
//			"            <td><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho+"imagens/altform.gif\"></td>\n" +
			"            <td><p class=\"texto\">Reservadamente</p></td>\n" +
//			"            <td><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho+"imagens/altform.gif\"></td>\n" +
			"            <td>\n" +
			"                <font class=\"campos\"><select size=\"1\" name=\"tipomesg\" class=\"campos\">\n" +
			"                <option value=\"fala para\"" + (tipo.equals("fala para") ? " selected" : "") + ">Fala para</option>\n" +
			"                <option value=\"xinga\"" + (tipo.equals("xinga") ? " selected" : "") + ">Xinga</option>\n" +
			"                <option value=\"grita com\"" + (tipo.equals("grita com") ? " selected" : "") + ">Grita com</option>\n" +
			"                <option value=\"sussurra para\"" + (tipo.equals("sussurra para") ? " selected" : "") + ">Sussurra para</option>\n" +
			"                </select></font>\n" +
			"            </td>\n" +
//			"            <td><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho+"imagens/altform.gif\"></td>\n" +
			"            <td>\n" +
			"                <font class=\"campos\"><select size=\"1\" name=\"usuarios\" class=\"campos\">\n" +
			"                <option value=\"Todos\">Todos</option>\n" + usuarios +
			"                </select></font>\n" +
			"            </td>\n" +			
//			"            <td><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho + "imagens/altform.gif\"><img src=\"" + caminho+"imagens/altform.gif\"></td>\n" +
			"            <td><font class=\"campos\"><input type=\"submit\" value=\"Escrever\" maxlength=1000  class=\"campos\"></font></td>\n" +
			"        </tr>\n" +
			"        </table>\n" +
			"    </td>\n" +
			"</tr>\n" +
			"</table>\n" +
			"</form>\n" +
			"</body>\n" +
			"</html>\n");
	}

	// Constrói lista de usuários
	String getUsuarios(String[] usuarios){
		String resultado = "";

		// Monta lista de usuários 
		for (int i = 0; i < usuarios.length; i++)
			resultado += "                <option value=\"" + usuarios[i] + "\">" + usuarios[i] + "</option>\n";

	 	return resultado;
	} 

	// Constrói lista de usuários
	String getUsuarios(Vector usuarios){
		String resultado = "";

		// Monta lista de usuários 
		for (int i = 0; i < usuarios.size(); i++)
			resultado += "                <option value=\"" + usuarios.get(i) + "\"" + (usuarios.get(i).equals(apelidoDestino) ? " selected" : "") + ">" + usuarios.get(i) + "</option>\n";

	 	return resultado;
	} 

}