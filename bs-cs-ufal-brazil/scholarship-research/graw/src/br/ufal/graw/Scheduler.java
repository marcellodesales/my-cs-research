package br.ufal.graw;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Hashtable;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.User;
import br.ufal.graw.web.ServletUtility;

/**
 * Scheduler.java
 *
 * @author gr@W's Developers
 * @author Cidorvan
 * @author Marcello de Sales
 * @author Rodrigo Paes
 */
public class Scheduler {
	
	private User user;
	private JspWriter out;
	private String usuarioID;
	private int dia, mes, ano;
	private boolean professor;
	private DatabaseLayer database;
	private String url = "agenda.jsp";
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String nomeDoMes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
						  		  "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	
	// Construtor para o index
	private Vector compromissoEntrada;//added by Marcello de Sales
								  
	public Scheduler(User user, DatabaseLayer database, JspWriter out) {
		this.user     = user;
		this.database = database;
		this.out      = out;
	}

	// Construtor para a agenda
	public Scheduler(User user, DatabaseLayer database, HttpServletRequest request,
										HttpServletResponse response, JspWriter out) {
		this.user     = user;
		this.database = database;
		this.request  = request;
		this.response = response;
		this.out      = out;
	}
	
	/**
	 * Method haCompromisso
	 *
	 * @return   True se há algum compromisso para o dia em que está sendo visualizado.
	 * vem do método mostraEntrada().
	 *
	 */
	public boolean haCompromissos(){
		//  *X*
		this.usuarioID = this.user.getID();
		this.professor = this.user instanceof Professor;
		this.compromissoEntrada = this.obtemCompromissosEntrada();
		return (this.compromissoEntrada.size() > 0);
	}
		
	// Mostra todos os compromissos a partir de hoje dentro do intervalo de aviso
	public void mostraEntrada() throws IOException {
		
		Vector compromissos;
		int data[], linhas, ln = 0;
		String dia = "", ultimoDia = "";
		GregorianCalendar dataAtual, dataCompromisso;

		// Pega alguns dados do usuário
		usuarioID = user.getID();
		professor = user instanceof Professor;

		// Pega data atual e zera sua hora para calcular a diferença de dias
		dataAtual = new GregorianCalendar();
		dataAtual.set(GregorianCalendar.HOUR, 0);
		dataAtual.set(GregorianCalendar.MINUTE, 0);
		dataAtual.set(GregorianCalendar.SECOND, 0);
		dataAtual.set(GregorianCalendar.MILLISECOND, 0);

		// Obtem os compromissos. Por verificar se obtem compromissos *X*
		if (this.compromissoEntrada == null){
			compromissos = this.obtemCompromissosEntrada();
		} else {
			compromissos = this.compromissoEntrada;
		}
		
		linhas = compromissos.size();
		// Se há compromissos, mostre-os
		if (linhas > 0) {
		
			// Cabeçalho da tabela
			out.println("<table border=0 cellspacing=2 cellpadding=3>");
//			out.println("<tr>");
//			out.println("    <td class=\"titulo_red2\" colspan=3><b>Compromissos agendados</b></td>");
//			out.println("</tr>");
			out.println("<tr>");
			out.println("    <td id=headerSimples width=\"15%\"><center>Dia</center></td>");
			out.println("    <td id=headerSimples width=\"10%\"><center>Hora</center></td>");
			out.println("    <td id=headerSimples width=\"75%\">Compromisso</td>");
			out.println("</tr>");
		
			for (int i = 0; i < linhas; i++) {

				// Próxima linha da tabela
				Hashtable linha = (Hashtable) compromissos.get(i);
	
				// Converte data do banco para vetor de inteiros
				data = Utility.parseData(linha.get("data").toString());
				dia = data[2] + "/" + data[1] + "/" + data[0];
				
				// Verifica se o compromisso esta no período de aviso
				dataCompromisso = new GregorianCalendar(data[0], data[1] - 1, data[2]);
				if (dataCompromisso.getTime().getTime() / 86400000 -
					dataAtual.getTime().getTime() / 86400000 >
					Integer.parseInt(linha.get("aviso").toString())) continue;
	
				// Conta quantas linhas realmente mostrou
				ln++;
	
				// Dia com link para a agenda
				if (!ultimoDia.equals(dia)) {
					ultimoDia = dia;
					out.println("<tr>");
					out.println("    <td class=\"diaatual\"><center><a href=\"agenda.jsp?dia=" +
						data[2] + "&mes=" + (data[1] - 1) + "&ano=" + data[0] + "\" class=\"hoje\">" + dia +
						"</a></center></td>");
					out.println("    <td class=\"diaatual\">&nbsp;</td>");
					out.println("    <td class=\"diaatual\">&nbsp;</td>");
					out.println("</tr>");
				}
				
				// Hora e compromisso
				out.println("<tr>");
				out.println("    <td class=\"linha\">&nbsp;</td>");
				out.println("    <td class=\"linha\"><center>" + linha.get("hora") + "</center></td>");
				out.print("    <td class=\"linha\">");
				if (!linha.get("communityID").equals("")) {
					out.print(linha.get("title"));
					if (!linha.get("userID").equals(usuarioID))
						out.print(" (" + linha.get("name") + ")");
					out.print(":<br>");
				}
				out.println(Utility.strReplace(Utility.strReplace((String)linha.get("compromisso"),
					"\15", ""), "\12", "<br>") + "</td>");

/*
				if (linha.get("communityID").equals("")) out.println("    <td class=\"linha\">" +
					Utility.strReplace(Utility.strReplace(linha.get("compromisso").toString(),
					"\15", ""), "\12", "<br>") + "</td>");
				else if (!linha.get("userID").equals(usuarioID)) out.println("    <td class=\"linha\">" +
					linha.get("title") + " (" + linha.get("pnome") + ")<br>" +
					Utility.strReplace(Utility.strReplace(linha.get("compromisso").toString(),
					"\15", ""), "\12", "<br>") +"</td>");
				else out.println("    <td class=\"linha\">" +
					linha.get("dnome") + ":<br>" + Utility.strReplace(Utility.strReplace(
					linha.get("compromisso").toString(), "\15", ""), "\12", "<br>") +"</td>"); */
				out.println("</tr>");
			}
		}
		// Se não mostrou nenhum compromisso, mostre linha em branco
		/*if (linhas == 0) {
			out.println("<table border=0 cellspacing=2 cellpadding=3>");
			out.println("<tr>");
			out.println("    <td class=\"titulo_red2\" colspan=3><B>Não há compromissos agendados!</B></TD>");
			out.println("</tr>");
		 }*/
		// Fim da tabela
		out.println("</table>");
	}
		
	// Determina a ação e mostra a agenda
	public void mostraAgenda() throws IOException {
		int acao;

		// Obtem data pelo parametro, caso contrário pega a atual
	    try {
			dia = Integer.parseInt(request.getParameterValues("dia")[0]);
			mes = Integer.parseInt(request.getParameterValues("mes")[0]);
			ano = Integer.parseInt(request.getParameterValues("ano")[0]);
		} catch (Exception e) {
			GregorianCalendar data = new GregorianCalendar();
			dia = data.get(GregorianCalendar.DAY_OF_MONTH);
			mes = data.get(GregorianCalendar.MONTH);
			ano = data.get(GregorianCalendar.YEAR);
		}
		
		// Pega alguns dados do usuário
		usuarioID = user.getID();
		professor = user instanceof Professor;

		// Pega a ação para definir o que fazer
		try {
			acao = Integer.parseInt(request.getParameterValues("acao")[0]);
		} catch (Exception e) {
			acao = -1;
		}

		// Executa ação
		switch (acao) {

			case 0: // HTML Alterar
				try {
					// Pega o código do compromisso
					int codigo = Integer.parseInt(request.getParameterValues("codigo")[0]);

					// Mostra HTML para alterar compromisso
					htmlEditarCompromisso(codigo);
					

				// Se algum erro, vá para a página de erro
				} catch (Exception e) {
					ServletUtility.sendRedirect(response, ServletUtility.ERROR_GENERIC_PAGE,"Erro tentando alterar compromisso!");
				}
				return;

			case 1: // Alterar
				try {
					int avisar;

					// Pega parametros para alteração de compromisso
					int codigo = Integer.parseInt(request.getParameterValues("codigo")[0]);
					String horario = request.getParameterValues("horario")[0];
					String compromisso = request.getParameterValues("compromisso")[0];
					try {
						avisar = Integer.parseInt(request.getParameterValues("avisar")[0]);
						if (avisar < 0) avisar = -avisar;
					} catch (Exception e) {
						avisar = 0;
					}

					// Altera compromisso
					alteraCompromisso(codigo, horario, compromisso, avisar);

				// Se algum erro, vá para a página de erro
				} catch (Exception e) {
					ServletUtility.sendRedirect(response, ServletUtility.ERROR_GENERIC_PAGE,"Erro alterando compromisso!");
				}
				break;

			case 2: // Incluir
				try {
					int avisar;

					// Pega os paramatros para inclusão de compromisso
					String horario = request.getParameterValues("horario")[0];
					String compromisso = request.getParameterValues("compromisso")[0];
					String disciplina = request.getParameterValues("communityID")[0];
					
					try {
						avisar = Integer.parseInt(request.getParameterValues("avisar")[0]);
						if (avisar < 0) avisar = -avisar;
					} catch (Exception e) {
						avisar = 0;
					}

					// Insere compromisso se alguma coisa for digitada
					if (!compromisso.trim().equals(""))
						insereNovoCompromisso(horario, compromisso, avisar, disciplina);

				// Se algum erro, vá para a página de erro
				} catch (Exception e) {
					ServletUtility.sendRedirect(response, ServletUtility.ERROR_GENERIC_PAGE,"Erro incluindo compromisso!");
				}
				break;

			case 3: // Excluir
				try {

					// Pega número de linhas
					int linhas = Integer.parseInt(request.getParameterValues("linhas")[0]);

					// Exclui os compromissos selecionados
					for (int i = 0; i < linhas; i++) try {
						int codigo = Integer.parseInt(request.getParameterValues("cb" + i)[0]);
						excluiCompromisso(codigo);
					} catch (Exception e) { }

				} catch (Exception e) {
					ServletUtility.sendRedirect(response, ServletUtility.ERROR_GENERIC_PAGE,"Erro excluindo compromisso(s)!");
				}
				break;
		}
		
		// Finalmente mostra a agenda
		htmlAgenda();
	}

	// Formulário para editar compromisso
	void htmlEditarCompromisso(int codigo) throws Exception {
		String hora;
		Hashtable linha = obtemCompromisso(codigo);

		// Força uma exceção se o resultado da busca for vazia
		if (linha == null) throw new Exception();

		// Pega os campos da consulta
		String horario = linha.get("hora").toString();
		String compromisso = linha.get("compromisso").toString();
		int avisar = Integer.parseInt(linha.get("aviso").toString());

		// Mostra formulário
		out.println("<form method=\"post\" name=\"formEditar\" action=\"" + url + "\">");
		out.println("<input type=\"hidden\" name=\"acao\" value=1>");
		out.println("<input type=\"hidden\" name=\"dia\" value=" + dia + ">");
		out.println("<input type=\"hidden\" name=\"mes\" value=" + mes + ">");
		out.println("<input type=\"hidden\" name=\"ano\" value=" + ano + ">");
		out.println("<input type=\"hidden\" name=\"codigo\" value=" + codigo + ">");
		out.println("<table border=0 cellspacing=2 cellpadding=3>");
		out.println("<tr>");
		out.println("    <td class=\"titulo_red2\" colspan=3>Editar compromisso</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("    <td id=headerSimples width=\"15%\"><center>Hora</center></td>");
		out.println("    <td id=headerSimples width=\"75%\">Compromisso</td>");
		out.println("    <td id=headerSimples width=\"10%\"><center>Avisar<center></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("    <td class=\"linha\"><center><select name=\"horario\">");
		for (int i = 0; i < 24; i++) {
			hora = (i < 10 ? "0" + i : "" + i) + ":00";
			out.print("          <option" + (hora.equals(horario) ? " selected" : "") +">" + hora + "</option>");
			hora = (i < 10 ? "0" + i : "" + i) + ":30";
			out.println("<option" + (hora.equals(horario) ? " selected" : "") +">" + hora + "</option>");
		}
		out.println("        </select></center></td>");
		out.println("    <td class=\"linha\"><textarea name=\"compromisso\" cols=40 rows=3>" + compromisso + "</textarea></td>");
		out.println("    <td class=\"linha\"><center><input type=\"text\" name=\"avisar\" value=\"" + avisar + "\" size=3></center></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("    <td class=\"linha\" colspan=3><center><input type=\"submit\" value=\"Alterar\" class=\"bagenda\"></center></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<p>* Avisar - Dias para avisar com antecedência</p>");
	}

	// Escreve script para confirmação de exclusão de compromissos
	void htmlScript() throws IOException {
		out.println("<script>");
		out.println("function ConfirmaExclusao() {");
		out.println("    if (confirm(\"Você quer mesmo excluir este(s) compromisso(s)?\"))");
		out.println("        document.formExcluir.submit();");
		out.println("}");
		out.println("</script>");
	}

	// Mostra a agenda completa do dia
	void htmlAgenda() throws IOException {
		int linhas;
		String query;
		Vector consulta;

		// Script para confirmação de exclusão
		htmlScript();

		// Cabeçalho da tabela principal
		out.println("<table width=\"100%\" border=0 cellspacing=5 cellpadding=0>");
    	out.println("<tr>");
    	out.println("    <td class=\"titulo_red2\"><b>" + dia + " de " + nomeDoMes[mes] + " de " + ano + "</b></td>");
    	out.println("    <td>&nbsp;</td>");
    	out.println("</tr>");
		out.println("<tr>");
		
		// Obtem os compromissos
		consulta = obtemCompromissosAgenda();
		linhas = consulta.size();

		// Cabeçalho da tabela de compromissos
		out.println("    <td valign=top width=\"75%\">");
		out.println("        <form method=\"post\" name=\"formExcluir\" action=\"" + url + "\">");
		out.println("        <input type=\"hidden\" name=\"acao\" value=3>");
		out.println("        <input type=\"hidden\" name=\"dia\" value=" + dia + ">");
		out.println("        <input type=\"hidden\" name=\"mes\" value=" + mes + ">");
		out.println("        <input type=\"hidden\" name=\"ano\" value=" + ano + ">");
		out.println("        <input type=\"hidden\" name=\"linhas\" value=" + linhas + ">");
		out.println("        <table width=\"100%\" border=0 cellspacing=2 cellpadding=3>");
    	out.println("        <tr>");
    	out.println("            <td id=headerSimples width=\"5%\">&nbsp;</td>");
    	out.println("            <td id=headerSimples width=\"10%\"><center>Hora</center></td>");
    	out.println("            <td id=headerSimples width=\"75%\">Compromisso</td>");
    	out.println("            <td id=headerSimples width=\"10%\"><center>Avisar</center></td>");
    	out.println("        </tr>");

		// Se não há compromissos, mostra uma linha em branco
		if (linhas == 0) {
			out.println("        <tr>");
			out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("        </tr>");

		// Se há, mostre os compromissos
		} else {
			for (int i = 0; i < linhas; i++) {

				// Pega as linhas da consulta
				Hashtable linha = (Hashtable) consulta.get(i);

				// Início da linha
				out.println("        <tr>");
				out.print("            <td class=\"linha\">");
				
				// Pode ser excluído ou alterado
				if (linha.get("userID").equals(usuarioID)) {
					out.print("&nbsp;<input type=\"checkbox\" name=\"cb" + i + "\" value=" +
						linha.get("codigo") + "></td>");
					out.println("            <td class=\"linha\"><center><a href=\"" +
						urlDataParam(dia, mes, ano) + "&codigo=" + linha.get("codigo") + "&acao=0\">" +
						linha.get("hora") + "</a></center></td>");
				} else {
					out.println("&nbsp;</td>");
					out.println("            <td class=\"linha\"><center>" +
						linha.get("hora") + "</center></td>");
				}
				
				// Compromisso
				out.print("            <td class=\"linha\">");
				if (!linha.get("communityID").equals("")) {
					out.print(linha.get("title"));
					if (!linha.get("userID").equals(usuarioID))
						out.print(" (" + linha.get("name") + ")");
					out.print(":<br>");
				}
				out.println(Utility.strReplace(Utility.strReplace((String)linha.get("compromisso"),
					"\15", ""), "\12", "<br>") + "</td>");

				// Fim da linha
				out.println("            <td class=\"linha\"><center>" + linha.get("aviso") + "</center></td>");
				out.println("        </tr>");
			}

			// Link para exclusão de compromisso(s)
			out.println("        <tr>");
			out.println("            <td colspan=4><a href=\"\" onClick=\"ConfirmaExclusao(); return false;\"><p>Excluir compromisso(s) selecionado(s)</p></a></td>");
			out.println("        </tr>");
		}

		// Finaliza tabela de compromissos
	   	out.println("        </table>");
	   	out.println("        </form>");
    	out.println("    </td>");

		// Mostra o calendário
    	out.println("    <td valign=\"top\" width=\"25%\">");
    	htmlCalendario();
    	out.println("    </td>");

		// Finaliza tabela
    	out.println("</tr>");
    	out.println("</table>");

		// Mostra formulário para envio de novo compromisso
    	htmlNovoCompromisso();
    }

	// Mostra um calendário de uma determinada data
	void htmlCalendario() throws IOException {
		GregorianCalendar dias;
		int i = 0, j, t, semanas = 0;
		String urlMesAnterior, urlMesPosterior, haCompromisso;

		// Mês anterior. GregorianCalendar bugento em outubro!
		dias = new GregorianCalendar(ano, mes, dia);
		dias.add(GregorianCalendar.MONTH, -1);
		if (dia <= 28 && dia != dias.get(GregorianCalendar.DAY_OF_MONTH))
			dias.add(GregorianCalendar.DAY_OF_MONTH, 1);
		urlMesAnterior = urlDataParam(dias);

		// Mês posterior. GregorianCalendar bugento em outubro!
		dias = new GregorianCalendar(ano, mes, dia);
		dias.add(GregorianCalendar.MONTH, 1);
		if (dia <= 28 && dia != dias.get(GregorianCalendar.DAY_OF_MONTH))
			dias.add(GregorianCalendar.DAY_OF_MONTH, 1);
		urlMesPosterior = urlDataParam(dias);

		// Cria tabela
		out.println("        <table border=0 cellpadding=3 cellspacing=2>");
		out.println("        <tr>");
		out.println("            <td id=headerSimples colspan=5>" + nomeDoMes[mes] +
			" / " + ano + "</td>");
		out.println("            <td id=headerSimples align=right><a class=\"navmes\" href=\"" +
			urlMesAnterior + "\"><center>««</center></a></td>");
		out.println("            <td id=headerSimples align=right><a class=\"navmes\" href=\"" +
			urlMesPosterior + "\"><center>»»</center></a></td>");
		out.println("        </tr>");

		// Título da semana
		out.println("        <tr>");
		out.println("            <td class=\"titulo2\">Dom</td>");
		out.println("            <td class=\"titulo2\">Seg</td>");
		out.println("            <td class=\"titulo2\">Ter</td>");
		out.println("            <td class=\"titulo2\">Qua</td>");
		out.println("            <td class=\"titulo2\">Qui</td>");
		out.println("            <td class=\"titulo2\">Sex</td>");
		out.println("            <td class=\"titulo2\">Sab</td>");
		out.println("        </tr>");

		// Primeiro dia e semana do mês
		dias = new GregorianCalendar(ano, mes, 1);
		t = dias.get(GregorianCalendar.DAY_OF_WEEK) - 1;
		if (t > 0) {
			semanas++;
			out.println("        <tr>");
			for (i = 0; i < t; i++) out.println("            <td class=\"linha\">&nbsp;</td>");
		}

		// Ponhe todos os dias do mês
		t = dias.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		for (j = 1; j <= t; j++) {

			// Inicia nova semana
			if (i % 7 == 0) {
				semanas++;
				out.println("        <tr>");
			}

			// Ponhe o dia com estilo do compromisso no dia
			if (j == dia) out.println("            <td class=\"diaatual\"><font color=\"#000000\">" + j + "</font></td>");
			else {
				haCompromisso = haCompromisso(j, mes, ano);
				out.println("            <td class=\"" + haCompromisso + "\"><a href=\"" +
					urlDataParam(j, mes, ano) + "\" class=\"" + haCompromisso + "\">" + j + "</a></td>");
			}

			// Finaliza a semana
			if (i++ % 7 == 6) out.println("        </tr>");
		}

		// Termina a última linha da semana
		if (i % 7 != 0) {
			while (i++ % 7 != 0) out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("        </tr>");
		}

		// Força a tabela ter seis linhas
		if (semanas < 6) {
			out.println("        <tr>");
			for (i = 0; i < 7; i++) out.println("            <td class=\"linha\">&nbsp;</td>");
			out.println("        </tr>");
		}

		// Termina tabela
		out.println("        </table>");
	}

	// Formulário para novo compromisso
    void htmlNovoCompromisso() throws IOException {
		out.println("<form method=\"post\" name=\"formIncluir\" action=\"" + url + "\">");
		out.println("<input type=\"hidden\" name=\"acao\" value=2>");
		out.println("<input type=\"hidden\" name=\"dia\" value=" + dia + ">");
		out.println("<input type=\"hidden\" name=\"mes\" value=" + mes + ">");
		out.println("<input type=\"hidden\" name=\"ano\" value=" + ano + ">");

		out.println("<table width=\"100%\" border=0 cellspacing=5 cellpadding=0>");
		out.println("<tr>");
		out.println("    <td class=\"titulo_red2\"><b>Novo compromisso</b></td>");
		out.println("    <td>&nbsp;</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=2>");

		out.println("<table border=0 cellspacing=2 cellpadding=3>");
		out.println("<tr>");
		out.println("    <td id=headerSimples><center>Hora</center></td>");
		out.println("    <td id=headerSimples>Compromisso</td>");
		out.println("    <td id=headerSimples><center>Avisar</center></td>");
		out.println("    <td id=headerSimples>Comunidade</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("    <td class=\"linha\"><select name=\"horario\">");
		for (int i = 0; i < 24; i++) {
			out.print("        <option>" + (i < 10 ? "0" + i : "" + i) + ":00</option>");
			out.println("<option>" + (i < 10 ? "0" + i : "" + i) + ":30</option>");
		}
		out.println("        </select></td>");
		out.println("    <td class=\"linha\"><textarea name=\"compromisso\" cols=30 rows=3></textarea></td>");
		out.println("    <td class=\"linha\"><center><input type=\"text\" name=\"avisar\" size=3></center></td>");
		out.println("    <td class=\"linha\"><select name=\"communityID\">");
		out.println("        <option value=\"\">Nenhuma (pessoal)</option>");
		Vector disciplinas = obtemComunidades();
		for (int i = 0; i < disciplinas.size(); i++) {
			Hashtable linha = (Hashtable) disciplinas.get(i);
			out.println("        <option value=\"" + linha.get("communityID") + "\">" + linha.get("title") + "</option>");
		}
		out.println("        </select>");
		out.println("    </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("    <td class=\"linha\" colspan=4><center><input type=\"submit\" value=\"Enviar\" class=\"bagenda\"></center></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<p>* Avisar - Dias para avisar com antecedência</p>");
	}

	// Converte data para formato do banco ('aaaa-mm-dd')
	String convData(int dia, int mes, int ano) {
		return new String("'" + ano + "-" + (mes + 1) + "-" + dia + "'");
	}

	// Converte uma data para um parametro de url
	String urlDataParam(int dia, int mes, int ano) {
		return new String(url + "?dia=" + dia + "&mes=" + mes + "&ano=" + ano);
	}

	// Converte uma data para um parametro de url
	String urlDataParam(GregorianCalendar data) {
		return new String(url + "?dia=" + data.get(GregorianCalendar.DAY_OF_MONTH) +
			"&mes=" + data.get(GregorianCalendar.MONTH) +
			"&ano=" + data.get(GregorianCalendar.YEAR));
	}

	// Obtem o compromisso do código
	Hashtable obtemCompromisso(int codigo) {
		Vector consulta = new Vector();
		String query = "SELECT * FROM scheduler WHERE codigo=" + codigo + " AND userID='" + usuarioID + "'";

		// Executa query
		consulta = database.query(query);

		// Retorna primeira linha da consulta
		if (consulta.size() > 0) return (Hashtable) consulta.get(0);

		// Se não houver resultado na consulta, retorna null
		return null;
	}

	// Altera compromisso
	void alteraCompromisso(int codigo, String horario, String compromisso, int avisar) throws SQLException {
		String query = "UPDATE scheduler SET hora='" + horario + "', compromisso='" +
			Utility.strReplace(compromisso, "'", "''") + "', aviso=" + avisar + " WHERE codigo=" + codigo;

		// Executa query
		database.update(query);
	}

	// Insere novo compromisso
	void insereNovoCompromisso(String horario, String compromisso, int avisar, String disciplina) throws SQLException {
		String query = "INSERT INTO scheduler (userID, data, hora, compromisso, aviso, communityID) VALUES ('" +
			usuarioID + "', " + convData(dia, mes, ano) + ", '" + horario +
			"', '" + Utility.strReplace(compromisso, "'", "''") + "', " + avisar + ", '" + disciplina + "')";

		// Executa query
		database.update(query);
	}

	// Exclui compromisso
	void excluiCompromisso(int codigo) throws SQLException {
		String query = "DELETE FROM scheduler WHERE codigo=" + codigo + " AND userID='" + usuarioID + "'";

		// Executa query
		database.update(query);
	}

	// Obtem os compromissos a partir de hoje
	Vector obtemCompromissosEntrada() {
		int i;
		String query;
		Vector consulta;
		
		// Faz consulta parcial
		if (professor) query = "SELECT communityID FROM teaches WHERE userID=\"" + usuarioID + "\"";
		else query = "SELECT communityID FROM communityinterests WHERE userID=\"" + usuarioID + "\"";
		consulta = database.query(query);
		
		// Monta início da consulta
		query = "SELECT s.userID, data, hora, compromisso, aviso, s.communityID, title, name " +
			"FROM scheduler AS s, community AS c, user AS u WHERE data>=NOW() AND (" +
			"(s.userID=\"" + usuarioID + "\"  AND s.communityID=\"\") OR " +
			"(s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")";
		
		// Complementa consulta
		if (consulta.size() > 0) {
			query += " OR (s.communityID=c.communityID AND c.status=\"" + Community.ACTIVED + "\" AND s.userID=u.userID AND (";
			for (i = 0; i < consulta.size(); i++) {
				query += "c.communityID=\"" + (String)((Hashtable)consulta.get(i)).get("communityID") + "\"";
				if (i != consulta.size() - 1) query += " OR ";
			}
			query += "))";
		}

		// Termina a consulta
		query += ") GROUP BY codigo ORDER BY data, hora, codigo";
		
/*		if (professor)
			query = "SELECT s.userID, data, hora, compromisso, aviso, s.communityID, " +
			"title, name FROM scheduler AS s, community AS c, teaches AS t, user AS u WHERE " +
			"data>=NOW() AND ((s.userID=\"" + usuarioID + "\"  AND s.communityID=\"\") OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\") OR (" +
			"s.communityID=c.communityID AND c.communityID=t.communityID AND t.userID=\"" + usuarioID + "\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")) " +
			"GROUP BY codigo ORDER BY data, hora, codigo";
//			query = "SELECT data, hora, compromisso, aviso, s.communityID, title AS dnome FROM scheduler AS s, " +
//				"teaches AS t, community AS c WHERE data>=NOW() AND ((s.userID='" + usuarioID +
//				"' AND s.communityID=\"\") OR (s.communityID=t.communityID AND s.communityID=c.communityID AND s.userID=t.userID " +
//				"AND status='A')) GROUP BY codigo ORDER BY data, hora, codigo";
		else
			query = "SELECT s.userID, data, hora, compromisso, aviso, s.communityID, " +
			"title, name FROM scheduler AS s, community AS c, communityinterests AS i, user AS u WHERE " +
			"data>=NOW() AND ((s.userID=\"" + usuarioID + "\" AND s.communityID=\"\") OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\") OR (" +
			"s.communityID=c.communityID AND c.communityID=i.communityID AND i.userID=\"" + usuarioID + "\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")) " +
			"GROUP BY codigo ORDER BY data, hora, codigo";
//			query = "SELECT data, hora, compromisso, aviso, s.communityID, name AS pnome, title AS dnome " +
//				"FROM scheduler AS s, user AS u, communityinterests AS i, community AS c WHERE data>=NOW() AND (" +
//				"s.userID='" + usuarioID + "' OR (s.communityID=i.communityID AND i.userID='" +
//				usuarioID + "' AND s.communityID=c.communityID AND s.userID=u.userID AND kindOfUserID='" +
//				User.PROFESSOR + "' AND i.status='A' AND c.status='A')) GROUP BY codigo ORDER BY data, hora, codigo";
*/
		return database.query(query);
	}

	// Obtem os compromissos
	Vector obtemCompromissosAgenda() {
		int i;
		String query;
		Vector consulta;
		
		// Faz consulta parcial
		if (professor) query = "SELECT communityID FROM teaches WHERE userID=\"" + usuarioID + "\"";
		else query = "SELECT communityID FROM communityinterests WHERE userID=\"" + usuarioID + "\"";
		consulta = database.query(query);
		
		// Monta início da consulta
		query = "SELECT codigo, s.userID, data, hora, compromisso, aviso, s.communityID, title, name " +
			"FROM scheduler AS s, community AS c, user AS u WHERE data=" + convData(dia, mes, ano) + " AND (" +
			"(s.userID=\"" + usuarioID + "\"  AND s.communityID=\"\") OR " +
			"(s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")";
		
		// Complementa consulta
		if (consulta.size() > 0) {
			query += " OR (s.communityID=c.communityID AND c.status=\"" + Community.ACTIVED + "\" AND s.userID=u.userID AND (";
			for (i = 0; i < consulta.size(); i++) {
				query += "c.communityID=\"" + (String)((Hashtable)consulta.get(i)).get("communityID") + "\"";
				if (i != consulta.size() - 1) query += " OR ";
			}
			query += "))";
		}

		// Termina a consulta
		query += ") GROUP BY codigo ORDER BY data, hora, codigo";

/*		if (professor)
			query = "SELECT codigo, s.userID, hora, compromisso, aviso, s.communityID, " +
			"title, name FROM scheduler AS s, community AS c, teaches AS t, user AS u WHERE " +
			"data=" + convData(dia, mes, ano) + " AND ((s.userID=\"" + usuarioID + "\"  AND s.communityID=\"\") OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\") OR (" +
			"s.communityID=c.communityID AND c.communityID=t.communityID AND t.userID=\"" + usuarioID + "\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")) " +
			"GROUP BY codigo ORDER BY hora, codigo";
		else
			query = "SELECT codigo, s.userID, hora, compromisso, aviso, s.communityID, " +
			"title, name FROM scheduler AS s, community AS c, communityinterests AS i, user AS u WHERE " +
			"data=" + convData(dia, mes, ano) + " AND ((s.userID=\"" + usuarioID + "\" AND s.communityID=\"\") OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\") OR (" +
			"s.communityID=c.communityID AND c.communityID=i.communityID AND i.userID=\"" + usuarioID + "\" AND s.userID=u.userID AND c.status=\"" + Community.ACTIVED + "\")) " +
			"GROUP BY codigo ORDER BY hora, codigo";
*/
		return database.query(query);
	}

	// Verifica se há algum compromisso no dia e retorna seu estilo
	String haCompromisso(int dia, int mes, int ano) {
		String query;

		if (professor) {
			query = "SELECT * FROM scheduler AS s, community AS c, teaches AS t WHERE " +
			"data=" + convData(dia, mes, ano) + " AND (s.userID=\"" + usuarioID + "\" OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\") OR (" +
			"s.communityID=t.communityID AND t.userID=\"" + usuarioID + "\")) GROUP BY codigo";
		} else {
			query = "SELECT * FROM scheduler AS s, community AS c, communityinterests AS i WHERE " +
			"data=" + convData(dia, mes, ano) + " AND (s.userID=\"" + usuarioID + "\" OR ("+
			"s.communityID=c.communityID AND c.userID=\"" + usuarioID +"\") OR (" +
			"s.communityID=i.communityID AND i.userID=\"" + usuarioID + "\")) GROUP BY codigo";
		}
			
		return (database.query(query).isEmpty() ? "semcomp" : "comcomp");
	}

	// Obtem a lista de disciplinas do professor
	Vector obtemComunidades() {
		String query = "SELECT c.communityID, title FROM community AS c, teaches AS t WHERE status='A' AND " +
			"(c.userID = \"" + usuarioID + "\" OR (t.userID='" + usuarioID + "' AND " +
			"c.communityID=t.communityID)) GROUP BY c.communityID ORDER BY title";

		return database.query(query);
	}

}
