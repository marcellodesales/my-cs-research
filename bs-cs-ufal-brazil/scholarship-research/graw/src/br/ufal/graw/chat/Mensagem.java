package br.ufal.graw.chat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import br.ufal.graw.Utility;

public class Mensagem {

	String cor;
	String caminho;
	String tipomesg;
	String mensagem;
	String msgAlterada;
	String caminhoCaras;
	String apelidoOrigem;
	String apelidoDestino;
	boolean reservadamente;
	boolean broadcast = true;
	
	// Construtor
	public Mensagem(HttpServletRequest request, String apelidoOrigem, boolean reservadamente,
		String apelidoDestino, String caminho) {
			
		// Seta variáveis
		this.caminho = caminho;
		this.caminhoCaras = this.caminho + "imagens/caras/";
		this.apelidoOrigem = apelidoOrigem;
		this.apelidoDestino = apelidoDestino;
		this.reservadamente = reservadamente;

		// Recupera parametros
		mensagem = request.getParameterValues("mensagem")[0];
		cor = request.getParameterValues("cor")[0];
		tipomesg = request.getParameterValues("tipomesg")[0];
		
		// Substitui caracters especiais
		mensagem = Utility.strReplace(mensagem, "<", "&lt;");
		mensagem = Utility.strReplace(mensagem, ">", "&gt;");

		// Ponhe Figurinhas gays
		mensagem = Utility.getEmotionedText(mensagem);
	}

	// Constrói a mensagem
	public void construirMensagem() {
		
		// Monta mensagem em broadcast ou unicast
		if (reservadamente)
			if (apelidoDestino.equals("Todos"))
				if (tipomesg.equals("fala para"))
					msgAlterada = "<p class=\"" + cor + "\"><b>" + apelidoOrigem + "</b>: " + mensagem;	

				else
					msgAlterada = "<p class=\"" + cor + "\"><b>" + apelidoOrigem + " " + tipomesg + " " +
						apelidoDestino + "</b>: " + mensagem;	

			else {
				msgAlterada = "<p class=\"" + cor + "\"><b>" + apelidoOrigem + "<u> reservadamente </u>" +
					tipomesg + " " + apelidoDestino + "</b>: " + mensagem;
				broadcast = false;
			}

		// Se não for reservadamente
		else if (apelidoDestino.equals("Todos") && tipomesg.equals("fala para"))
			msgAlterada = "<p class=\"" + cor + "\"><b>" + apelidoOrigem + "</b>: " + mensagem;

		else
			msgAlterada = "<p class=\"" + cor + "\"><b>" + apelidoOrigem + " " + tipomesg + " " +
				apelidoDestino + "</b>: " + mensagem;
	
		// Colocando ou nao a imagem
//		if (!humor.equals("Humor"))
//			msgAlterada += "<br><img src=\"" + caminhoCaras + humor + ".gif\" border=0>"; 

		msgAlterada += "</p>";
	}

	// Recupera a mensagem já formatada
	String getMensagem() {
		return msgAlterada;
	}
	
	// Recupera mensagem original
	String getMensagemOriginal() {
		return mensagem;
	}

	// Verifica se a mensagem vai ser enviada em broadcast
	boolean getBroadcast(){
		return broadcast;
	}

}