package br.ufal.graw.whiteboard;

import java.io.*;
import java.net.*;
import java.util.*;
import java.rmi.Naming;
import br.ufal.graw.Config;
import br.ufal.graw.chat.RemNaoModerado;

public class WBServidor implements Runnable {
	
	int porta;
	RemNaoModerado rmi;
	protected static Vector usuarios = new Vector();
	protected static Hashtable salas = new Hashtable();	
	
	// Construtor
	public WBServidor() {
		int i = 0;

		porta = 5005;
		
		// Parametros de configura��o
		Config conf = new Config();
		String portarmi = conf.getChat("rmi");
		String server = conf.getChat("server");

		// Faz conex�o com o servidor do chat
		while (i++ < 60) try{
			Thread.sleep(500);
			rmi = (RemNaoModerado) Naming.lookup("rmi://" + server + ":" + portarmi + "/RemNaoModerado");
			break;
		} catch(Exception re) { }
		if (rmi == null) System.out.println("N�o foi poss�vel se conectar com o servidor de chat !!!");
	}
	
	// Inicia servidor
	public static void main(String[] args) {
		WBServidor servidor = new WBServidor();
		servidor.listen();
	}

	// Aguarda usu�rio e cria uma conex�o para ele
	public void listen() {
		Conexao con;
		Socket socket;
		
		try {
			// Cria socket
			ServerSocket ss = new ServerSocket(porta);
			while (true) {

				// Espere usu�rio
				System.out.println("Esperando usu�rio...");
				socket = ss.accept();
				
				// Cria conex�o
				con = new Conexao(socket, this);
				con.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Define atributos do usu�rio em segundo plano
	// Recebe e retransmite as mensagens de cada usu�rio
	public void run() {
		Vector sala;
		String texto = "";
		Rascunho rascunho;
		DataInputStream in;
		int tool, rgb, xyi, xyf = 0;
		Conexao con = (Conexao) Thread.currentThread(), con2;

		// Cria escrita e leitura do socket
		try {
			con.out = new DataOutputStream(new BufferedOutputStream(con.socket.getOutputStream()));
			in = new DataInputStream(new BufferedInputStream(con.socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("Erro criando stream: " + e.getMessage());
			return;
		}

		try {
			// Recupera apelido e sala do usu�rio
			con.apelido = in.readLine();
			con.salaID = in.readLine();
			sala = (Vector) salas.get(con.salaID);

			// Primeiro usu�rio a entrar na sala (inicia a sala)
			if (sala == null) {
				sala = new Vector();
				salas.put(con.salaID, sala);
				System.out.println("Nova sala criada para o whiteboard(" + con.salaID + ")");

			// Passa a todo o rascunho da sala para o usu�rio que acabou de entrar
			} else synchronized (sala) {
				for (int i = 0; i < sala.size(); i++) {
					rascunho = (Rascunho) sala.get(i);
					con.out.writeByte(rascunho.tool);
					con.out.writeInt(rascunho.rgb);
					con.out.writeInt(rascunho.xyi);
					if (rascunho.tool != 7) con.out.writeInt(rascunho.xyf);
					else con.out.writeBytes(rascunho.texto + "\n");
				}
				con.out.flush();
			}
			
			// Adiciona usu�rio na lista
			usuarios.addElement(con);
			System.out.println("Novo usu�rio \"" + con.apelido + "\" na sala \"" + con.salaID + "\" do wb");
		} catch (Exception e) {			
			System.out.println("Erro adicionando usu�rio \"" + con.apelido + "\" na sala \"" + con.salaID + "\"");
			e.printStackTrace();
			return;
		}
			
		// Aguarda mensagem e a transmiste para todos
		try {
			while (true) {
				tool = in.readUnsignedByte();

				// �ltimo usu�rio a sair da sala (finaliza sala)
				if (tool == 0xFF) {
					removeUsuario(con.apelido, con.salaID, sala);
					break;
				}
				rgb = in.readInt();
				xyi = in.readInt();
				if (tool < 7) xyf = in.readInt();
				else texto = in.readLine();
				
				// limpa ou adiciona rascunho a esta sala
				if (tool == 0) sala.clear();
				else sala.addElement(new Rascunho(tool, rgb, xyi, xyf, texto));
			
				// Manda o broadcast
				if (tool < 7) broadcast(con.apelido, con.salaID, tool, rgb, xyi, xyf);
				else if (tool == 7) broadcast(con.apelido, con.salaID, rgb, xyi, texto);
			}
		} catch (Exception e) {
			System.err.println("Erro lendo mensagem de \"" + con.apelido + "\" na sala \"" + con.salaID + "\"");
			e.printStackTrace();
			removeUsuario(con.apelido, con.salaID, sala);
		}
		
		// Fecha socket
		try {
			in.close();
			con.out.close();
			con.socket.close();
		} catch (Exception e) { }
	}
	
	public synchronized boolean UsuariosNaSala(String salaID) {
		Enumeration e;

		// Enumera todos os usu�rios
		synchronized (usuarios) {
			e = usuarios.elements();
			while (e.hasMoreElements())
				if (((Conexao)e.nextElement()).salaID.equals(salaID)) return true;
		}
		
		return false;
	}

	// Faz broadcast de coordenadas para a sala exceto para quem mandou a mensagem
	public synchronized void broadcast(String apelido, String salaID, int tool, int rgb, int xyi, int xyf) {
		Conexao con;

		// Faz um loop sincronizado em usuarios
		synchronized (usuarios) {

			Enumeration e = usuarios.elements();
			while (e.hasMoreElements()) {

				// Recupera conex�o
				con = (Conexao) e.nextElement();
	
				// Se n�o faz parte da sala ou � o mesmo usu�rio, v� para o pr�ximo
				if (!con.salaID.equals(salaID) || con.apelido.equals(apelido)) continue;
	
				// Envia mensagem
				try {
					con.out.writeByte(tool);
					con.out.writeInt(rgb);
					con.out.writeInt(xyi);
					con.out.writeInt(xyf);
					con.out.flush();
				} catch (IOException ee) { }
			}
		}
	}

	// Faz broadcast de texto para a sala exceto para quem mandou a mensagem
	public synchronized void broadcast(String apelido, String salaID, int rgb, int xyi, String texto) {
		Conexao con;

		// Faz um loop sincronizado em usuarios
		synchronized (usuarios) {

			Enumeration e = usuarios.elements();
			while (e.hasMoreElements()) {

				// Recupera conex�o
				con = (Conexao) e.nextElement();
	
				// Se n�o faz parte da sala ou � o mesmo usu�rio, v� para o pr�ximo
				if (!con.salaID.equals(salaID) || con.apelido.equals(apelido)) continue;
	
				// Envia mensagem
				try {
					con.out.writeByte(7);
					con.out.writeInt(rgb);
					con.out.writeInt(xyi);
					con.out.writeBytes(texto + "\n");
					con.out.flush();
				} catch (IOException ee) { }
			}
		}
	}

	// Remove usu�rio da lista
	public void removeUsuario(String apelido, String salaID, Vector sala) {
		Conexao con;
		int i, size = usuarios.size();
		
		// Procura usu�rio e remove-o
		for (i = 0; i < size; i++) {
			con = (Conexao) usuarios.get(i);
			
			// Se encontrou, remova-o e saia
			if (con.salaID.equals(salaID) && con.apelido.equals(apelido)) {
				usuarios.remove(i);
				System.out.println("Usu�rio \"" + apelido + "\" removido da sala \"" + salaID + "\" do wb");

				// Remove o usu�rio do chat
				try {
					if (rmi != null) rmi.sair(apelido); 
				} catch (Exception e) { }

				// Se n�o h� mais usu�rios neste sala, remova-a tamb�m
				if (!UsuariosNaSala(salaID)) {
					sala.clear();
					salas.remove(salaID);
					System.out.println("Sala do whiteboard finalizada(" + con.salaID +")");
				}
				return;
			}
		}
		
		// Se n�o encontrou
		System.err.println("Erro removendo usuario \"" + apelido + "\" da sala \"" + salaID + "\"");
	}
}