package br.ufal.graw.messenger;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import br.ufal.graw.*;

public class MessengerServer implements Runnable {
	
	int porta, salaArquivo = -1;
	DatabaseLayer db;
	Hashtable conexoes;
	
	public MessengerServer() {
		conexoes = new Hashtable();
		
		Config conf = new Config();
		porta = Integer.parseInt(conf.getMessenger("socket"));
		db = new DatabaseLayer();
	}
	
	public static void main(String args[]) {
		MessengerServer mServer = new MessengerServer();
		mServer.listen();
	}

	void listen() {
		Conexao con;
		
		try {
			ServerSocket sSocket = new ServerSocket(porta);		

			while (true) {
				System.out.println("Aguardando usuario...");
				con = new Conexao(sSocket.accept(), this);
				con.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		DataInputStream in;
		String login, password, userID;
		Conexao con = (Conexao) Thread.currentThread(), con2;
		
		try {
			in = new DataInputStream(new BufferedInputStream(con.socket.getInputStream()));
			con.out = new DataOutputStream(new BufferedOutputStream(con.socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			login = in.readLine();
			password = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
			
		try {
			Vector consulta = db.query("SELECT userID FROM user WHERE login='" + login +
				"' AND password=PASSWORD('" + password + "')");

			// Login falhou
			if (consulta.size() == 0) {
				con.out.writeInt(0);
				con.out.flush();
				System.out.println("Login inválido!!!");

			// Login aceito
			} else {
				con.out.writeInt(-1);
				con.out.flush();
				System.out.println("Login aceito!!! usuario: " + login);
				
				con2 = (Conexao)conexoes.get(login);
				if (con2 != null) {
					con2.out.writeByte(0xFF);
					con2.out.flush();
					con2.join();
				}

				conexoes.put(login, con);
				userID = ((Hashtable)consulta.get(0)).get("userID").toString();
				
				broadcastUsuarioStatus(userID, 0x02);
				unicastCommunity(con.out, userID);
				unicastContatos(con.out, userID);
				unicastPermissoes(con.out, userID);
				
				try {
					while (true) switch (in.readUnsignedByte()) {
						case 0x01:
							addContato(in, con.out, userID);
							break;
							
						case 0x02:
							updateContato(in, userID);
							break;
							
						case 0x03:
							addContatoSemPermissao(in, userID);
							break;
							
						case 0x04:
							deleteContato(in, con.out, userID);
							break;
							
						case 0x05:
							searchContato(in, con.out);
							break;
							
						case 0x06:
							recebeMensagem(in, userID);
							break;
							
						case 0x07:
							AvisoTransmissaoArquivo(in, userID);
							break;
							
/////////////////////////////////////////////////////////////////////////////////
// Arquivo
/////////////////////////////////////////////////////////////////////////////////
						case 0x10:
							RequisitaSalaArquivo(in, con.out, userID);
							break;
					}
				} catch (Exception e) {
					System.out.println("Usuario \"" + login + "\" saiu do messenger");
				}
				
				conexoes.remove(login);
				broadcastUsuarioStatus(userID, 0x00);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		try {
			con.out.close();
			in.close();
			con.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// (1h) Manda um unicast com as comunidades que este usuário se interessa
	void unicastCommunity(DataOutputStream out, String userID) throws IOException {
		int i;
		Vector consulta;
		Hashtable linha;
		String comunidade;
		
		// Seleciona as comunidades
		consulta = db.query("SELECT c.communityID, kind, title FROM community AS c, " + 
			"communityinterests AS i WHERE i.userID='" + userID + "' AND i.communityID=c.communityID AND " +
			"i.status='A'");

		// Transmite as comunidades
		for (i = 0; i < consulta.size(); i++) {
			linha = (Hashtable)consulta.get(i);
			comunidade = linha.get("title").toString();
			out.writeByte(0x1);
			out.writeLong(Long.parseLong(linha.get("communityID").toString()));
			out.writeBytes(linha.get("kind").toString());
			out.writeByte(comunidade.length());
			out.writeBytes(comunidade);
		}
		out.flush();
	}
	
	// (02h) Envia dados de um contato
	void sendContato(DataOutputStream out, String userID, int status, String nome, String login) throws IOException {
		out.writeByte(0x2);
		out.writeLong(Long.parseLong(userID));
		out.writeByte(status);				
		out.writeByte(nome.length());
		out.writeBytes(nome);
		out.writeByte(login.length());
		out.writeBytes(login);
	}

	// (2h) Manda unicast com os contatos deste usuário e se está online
	void unicastContatos(DataOutputStream out, String userID) throws IOException {
		int i;
		Conexao con;
		Vector consulta;
		Hashtable linha;
		String usuario, login;
		
		// Seleciona os contatos
		consulta = db.query("SELECT u.userID, login, name, c.status FROM user AS u, contatos as c " +
			"WHERE c.userID='" + userID + "' AND contatoID=u.userID");

		// Transmite os contatos
		synchronized (conexoes) {
			for (i = 0; i < consulta.size(); i++) {
				linha = (Hashtable)consulta.get(i);
				usuario = linha.get("name").toString();
				login = linha.get("login").toString();
				con = (Conexao) conexoes.get(login);
				sendContato(out, linha.get("userID").toString(),
					con != null && linha.get("status").equals("A") ? 2 : 0,	usuario, login);
			}
		}
		out.flush();
	}
	
	// (03h) Envia status de um usuário
	void sendUsuarioStatus(DataOutputStream out, String userID, int status) throws IOException {
		out.writeByte(0x03);
		out.writeLong(Long.parseLong(userID));
		out.writeByte(status);
	}

	// (03h) Manda broadcast dizendo o novo status deste usuário
	void broadcastUsuarioStatus(String userID, int status) throws IOException {
		int i;
		Conexao con;
		Vector consulta;
		Hashtable linha;
		String usuario;
		
		// Seleciona os usuários de contatos
		consulta = db.query("SELECT login FROM user AS u, contatos AS c WHERE u.userID=c.userID AND " +
			"contatoID='" + userID + "' AND c.status='A'");

		// Manda mensagem de que este usuário se logou...
		synchronized (conexoes) {
			for (i = 0; i < consulta.size(); i++) {
				con = (Conexao) conexoes.get(((Hashtable)consulta.get(i)).get("login").toString());
				if (con != null) {
					sendUsuarioStatus(con.out, userID, status);
					con.out.flush();
				}
			}
		}
	}
	
	void unicastPermissoes(DataOutputStream out, String userID) throws IOException {
		int i;
		Conexao con;
		Vector consulta, consulta2;
		Hashtable linha;
		
		// Seleciona os contatos pendentes
		consulta = db.query("SELECT u.userID, name, login FROM user AS u, contatos AS c WHERE " +
			"u.userID=c.userID AND contatoID='" + userID +"' AND status='W'");

		// Transmite os contatos
		for (i = 0; i < consulta.size(); i++) {
			linha = (Hashtable)consulta.get(i);
			consulta2 = db.query("SELECT * FROM contatos WHERE userID='" + userID + "' AND contatoID='" +
				linha.get("userID").toString() + "'");
			con = (Conexao) conexoes.get(linha.get("login").toString());
			sendContato(out, linha.get("userID").toString(), (con != null? 2 : 0) | (consulta2.size() > 0 ? 64 : 0) | 128,
				linha.get("name").toString(), linha.get("login").toString());
		}
		out.flush();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// (01h/04h) Adiciona contato
	void addContato(DataInputStream in, DataOutputStream out, String userID) throws IOException {
		Conexao con;
		String login = in.readLine();
		Vector consulta = db.query("SELECT userID, name FROM user WHERE login='" + login + "'"), consulta2;

		out.writeByte(0x04);
		if (consulta.size() > 0) {
			String contatoID = ((Hashtable)consulta.get(0)).get("userID").toString();
			String nome = ((Hashtable)consulta.get(0)).get("name").toString();

			consulta = db.query("SELECT * FROM contatos WHERE userID='" + userID + "' AND contatoID='" + 
				contatoID + "'");

			if (consulta.size() == 0) {
				try {
					db.update("INSERT INTO contatos VALUES ('" + userID + "', '" + contatoID + "', 'W')");
					out.writeByte(0x01);
					sendContato(out, contatoID, 0, nome, login);
					
					con = (Conexao) conexoes.get(login);
					if (con != null) {
						consulta = db.query("SELECT name, login FROM user WHERE userID='" + userID + "'");						
						consulta2 = db.query("SELECT * FROM contatos WHERE userID='" + contatoID +
							"' AND contatoID='" + userID + "'");
						sendContato(con.out, userID, 2 | (consulta2.size() > 0 ? 64 : 0) | 128,
							((Hashtable)consulta.get(0)).get("name").toString(),
							((Hashtable)consulta.get(0)).get("login").toString());
						con.out.flush();
					}
				} catch (SQLException e) {
					out.writeByte(0x02);
				}
			} else out.writeByte(0x01);
		} else out.writeByte(0x00);
		out.flush();			
	}
	
	// (02h) Muda status do contato
	void updateContato(DataInputStream in, String userID) throws IOException {
		Conexao con;
		int status = in.readUnsignedByte();
		String contatoID = String.valueOf(in.readLong());
		
		try {
			db.update("UPDATE contatos SET status='" + (status == 1 ? "A" : "X") + "' WHERE userID='" +
				contatoID + "' AND contatoID='" + userID + "'");
			
			Vector consulta = db.query("SELECT login FROM user WHERE userID='" + contatoID + "'");
			if (consulta.size() > 0) {
				con = (Conexao) conexoes.get(((Hashtable)consulta.get(0)).get("login").toString());					
				if (con != null) {
					sendUsuarioStatus(con.out, userID, status == 1 ? 2 : 0);
					con.out.flush();
				}
			}
		} catch (SQLException e) { }
	}
	
	// (03h) Adiciona contato sem permissão
	void addContatoSemPermissao(DataInputStream in, String userID) throws IOException {
		try {
			db.update("INSERT INTO contatos VALUES ('" + userID + "', '" + in.readLong() + "', 'A')");
		} catch (SQLException e) { }
	}
	
	// (04h/05h) Remove contato
	void deleteContato(DataInputStream in, DataOutputStream out, String userID) throws IOException {
		long contatoID = in.readLong();
		
		try {
			db.update("DELETE FROM contatos WHERE userID='" + userID + "' AND contatoID='" + contatoID + "'");
			out.writeByte(0x05);
			out.writeLong(contatoID);
			out.flush();			
		} catch (SQLException e) { }
	}
	
	// (05h/04h,06h) Procura por um contato
	void searchContato(DataInputStream in, DataOutputStream out) throws IOException {
		int i;
		Hashtable tupla;
		String nome = in.readLine();
		String sobrenome = in.readLine();
		String login;
		
		Vector consulta = db.query("SELECT name, login FROM user WHERE name LIKE '" + nome + " %" +
			sobrenome + "' ORDER BY name");
			
		out.writeByte(0x04);
		if (consulta.size() == 0) out.writeByte(0x00);
		else if (consulta.size() > 20) out.writeByte(0x02);
		else {
			out.writeByte(0x01);
			
			for (i = 0; i < consulta.size(); i++) {
				tupla = (Hashtable)consulta.get(i);
				out.writeByte(0x06);
				login = tupla.get("login").toString();
				nome = tupla.get("name").toString();				
				out.writeByte(login.length());
				out.writeBytes(login);
				out.writeByte(nome.length());
				out.writeBytes(nome);
			}
			
			out.writeShort(0x0401);
		}
		out.flush();
	}
	
	// (06h/07h) Recebe mensagem de um cliente e transmite para outro
	void recebeMensagem(DataInputStream in, String userID) throws IOException {
		String login = in.readLine();
		String mess = in.readLine();
		
		Conexao con = (Conexao)conexoes.get(login);
		if (con != null) {
			con.out.writeByte(0x07);
			con.out.writeLong(Long.parseLong(userID));
			con.out.writeByte(mess.length());
			con.out.writeBytes(mess);
			con.out.flush();
		}
	}
	
	void AvisoTransmissaoArquivo(DataInputStream in, String userID) throws IOException {
		String login = in.readLine();
		
		Vector consulta = db.query("SELECT name FROM user WHERE userID='" + userID + "'");
		String nome = ((Hashtable)consulta.get(0)).get("name").toString();
		
		Conexao con = (Conexao)conexoes.get(login);
		con.out.writeByte(0x08);
		con.out.writeByte(nome.length());
		con.out.writeBytes(nome);
		con.out.flush();
	}
	
/////////////////////////////////////////////////////////////////////////////////
// Arquivo
/////////////////////////////////////////////////////////////////////////////////

	void RequisitaSalaArquivo(DataInputStream in, DataOutputStream out, String userID) throws IOException {
		String login = in.readLine();
		int nFileSize = in.readInt();
		String szFileName = in.readLine();

		Conexao con = (Conexao)conexoes.get(login);
		SalaArquivo sala = new SalaArquivo(++salaArquivo, out, con.out);
		System.out.println(salaArquivo);		
		
		out.writeByte(0x10);
		out.writeInt(salaArquivo);
		out.flush();
		
		Vector consulta = db.query("SELECT name FROM user WHERE userID='" + userID +"'");
		String nome = ((Hashtable)consulta.get(0)).get("name").toString();
		
		con.out.writeByte(0x11);
		con.out.writeInt(salaArquivo);
		con.out.writeByte(nome.length());
		con.out.writeBytes(nome);
		con.out.writeInt(nFileSize);
		con.out.writeBytes(szFileName);
		con.out.flush();
	}
}

