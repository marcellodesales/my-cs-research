/**
	ServidorChat.java
	Classe que vai ser o servidor de uma sala sem moderador
*/

package br.ufal.graw.chat;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.StringTokenizer;
import br.ufal.graw.Config;

// Servidor de sala nao moderada
public class ServidorChat extends UnicastRemoteObject implements Runnable, RemNaoModerado {

	String serverName;
	private String server;
	private String caminho;
	protected int portaSocket, portaRMI;
	protected static Hashtable conexoes = new Hashtable();
	
	// Construtor
	public ServidorChat(String serverName) throws RemoteException {

		Config conf = new Config();
		server = conf.getChat("server");
		caminho = conf.getChat("html");
		portaSocket = Integer.parseInt(conf.getChat("socket"));
		portaRMI = Integer.parseInt(conf.getChat("rmi"));
		this.serverName = serverName;

		// Inicia servidor RMI
		try {
			LocateRegistry.createRegistry(this.portaRMI);
			Naming.rebind("rmi://" + this.server + ":" + this.portaRMI + "/RemNaoModerado", this);
		} catch (RemoteException re) {
			System.out.println("Exceção remota: " + re.getMessage());
		} catch (MalformedURLException mfe) {
			System.out.println("URL errada: " + mfe.getMessage());
		}
	}
	
	// Função inicial, cria o servidor
	public static void main(String[] args) {

		// Inicia RMI
		try{
			ServidorChat echoServer = new ServidorChat("Servidor Chat Graw");
			echoServer.listen();
		} catch (RemoteException e) {
			System.err.println("Erro iniciando servidor!"+e.getMessage());
		}
	}
		
	// Método remoto para envio mensagem em broadcast
	public void sendMessageBroadcast(String message, String salaID){
		try {
			broadcast(message + "\n", salaID);
		} catch (Exception e) {
			System.out.println("Mensagem não pode ser enviada em broadcast: " + e.getMessage());
		}
	}
	
	// Método remoto para envio de mensagem em unicast
	public void sendMessageUnicast(String origem, String message, String destinatario, boolean dois, String salaID) {
		try {
			unicast(origem, message, destinatario, dois, salaID);
		} catch (Exception e) {
			System.out.println("Mensagem nao pode ser enviada em unicast: " + e.getMessage());
		}
	}
	
	// Método remoto para recuperar todos os usuários de uma sala
	public Vector getUsuarios(String salaID){
		String apelido;
		Conexao conexao;
		Vector usuarios = new Vector();

		try {
			Enumeration e = conexoes.keys();

			// Varre todos os usuários do chat
			while (e.hasMoreElements()) {
				apelido = (String) e.nextElement();
				conexao = (Conexao) conexoes.get(apelido);

				// Se este usuário pertence a sala, ponha-o na lista
				if (conexao.getSalaID().equals(salaID)) usuarios.addElement(apelido);
			}
		} catch (Exception e) {
			System.out.println("Erro recuperando lista de usuários da sala \"" + salaID +"\": " +
				e.getMessage());
		}

		// Retorna os usuários da sala especificada
		return usuarios;
	}
	
	// Recupera o número de usuários em uma sala
	public int getUsersOnRoom(String salaID) {
		int num = 0;
		Conexao con;
		
		try {
			Enumeration e = conexoes.keys();
			
			// Varre todos os usuários do chat
			while (e.hasMoreElements()) {
				con = (Conexao) conexoes.get(e.nextElement());
				
				// Incrementa se usuário na sala
				if (con.getSalaID().equals(salaID)) num++;
			}
		} catch (Exception e) {
			System.out.println("Erro recuperando lista de usuários da sala \"" + salaID +"\": " +
				e.getMessage());
		}
		
		System.out.println("Usuários na sala: " + num);
		return num;
	}
	
	// Procura usuario em todas as salas
	public boolean usuarioExistente(String apelido) {
		Conexao con = (Conexao) conexoes.get(apelido);
		
		return (con != null);
	}
	
	// Método remoto chamando quando um usuário saia da sala
	public void sair(String apelido) {
		try{
			Conexao con = (Conexao) conexoes.get(apelido);
			if (con != null) {
				String salaID = con.getSalaID();
				con.in.close();
				con.out.close();
				con.serverSocket.close();
				conexoes.remove(apelido);
				broadcast("<p class=\"msg_cinza\"><b>" + apelido + "</b>: saiu da sala</p>\n", salaID);
				System.err.println("Usuário \"" + apelido + "\" removido da sala \"" + salaID + "\"");
			}
		} catch (Exception e) {
			System.out.println("Erro removendo usuário: " + e.getMessage());
		}
	}
	
	// Método remoto chamando quando um usuário saia da sala
	public void sair(Conexao con){
		
		try{
			if (con!=null) {
				String apelido = con.getApelido();
				String salaID = con.getSalaID();
				con.in.close();
				con.out.close();
				con.serverSocket.close();
				conexoes.remove(apelido);
				broadcast("<p class=\"msg_cinza\"><b>" + apelido + "</b>: saiu da sala</p>\n", salaID);
				System.err.println("Usuário \"" + apelido + "\" removido da sala \"" + salaID + "\"");
			}
		} catch (Exception e) {
			System.out.println("Erro removendo usuário: " + e.getMessage());
		}
	}

	// Loop principal, espera um usuário e cria sua conecção com o chat
	public void listen() {
		Conexao con;
		Socket server;

		// Incia socket para aguardar usuários
		try {
			ServerSocket listener = new ServerSocket(this.portaSocket);
			while (true) {

				// Espera usuário
				System.out.println("Servidor OK (esperando usuário)");
				server = listener.accept();

				// Cria conexão para este usuário
				con = new Conexao(this, server);
				con.start();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	// Acrescenta usuário em uma sala de chat
	public void run() {
		String apelido, salaID;
		Conexao con = (Conexao) Thread.currentThread();

		try{
			// Ler parametro passado pelo browse para recuperar apelido e sala
			StringTokenizer st = new StringTokenizer(con.in.readLine());
			st.nextToken();
			st = new StringTokenizer(st.nextToken().substring(1), "/");
			apelido = st.nextToken();
			salaID = st.nextToken();

			// Define apelido e sala para este usuário
			con.setApelido(apelido);
			con.setSalaID(salaID);

			// Coloca-o na lista de conexões
			conexoes.put(apelido, con);//coloca no vector

			System.out.println("Usuário \"" + apelido + "\" entrou na sala \"" + salaID + "\"");

			// Envia cabeçalho para o usuário
			con.out.println(
				"HTTP/1.1 200 OK\n" +
				"Server:" + serverName + "\n" +
				"Content-Type:text/html\n\n" +
				"<html>\n" +
				"<head>\n" +
				"<link href=\"" + caminho + "sources/style/chat.css\" rel=stylesheet type=\"text/css\">\n" +
				"</head>\n"+
				"<body bgcolor=#FFFFFF topmargin=0 marginheight=0>\n"+
				"<!-- Esse é o WebChat do Gr@W. Todos os Direitos Reservados................-->\n");

			// Avisa que este usuário entrou no chat
			broadcast("<p class =\"msg_cinza\"><b>" + apelido + "</b>: Entrou na sala</p>\n", salaID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Manda mensagem em broadcast para os usuários de uma determinada sala
	public void broadcast(String mensagem, String salaID) throws Exception{
		Conexao conexao;

		// Rola para cima a mensagem quando enviada
		mensagem += "<script>window.scrollBy(0,1000000)</script>";

		// Faz um loop sicronizado
		synchronized (conexoes) {

			// Varre todos os usuários do chat
			Enumeration e = conexoes.keys();
			while (e.hasMoreElements()) {
				conexao = (Conexao) conexoes.get(e.nextElement());
				
				// Verifica se este usuário está nesta sala
				if (!conexao.getSalaID().equals(salaID)) continue;
		
				// Manda mensagem para cada usuário da sala
				conexao.out.println(mensagem);
				conexao.out.flush();
				if (conexao.out.checkError()) sair(conexao);
			}
		}
	}
	
	// Manda mensagem em unicast para um usuário de uma determinada sala
	public void unicast(String origem, String mensagem, String destinatario, boolean dois, String salaID) throws Exception {
		Conexao conexao1, conexao2;

		// Rola para cima a mensagem quando enviada
		mensagem += "<script>window.scrollBy(0,1000000)</script>";

		// Manda mensagem para usuário de origem
		conexao1 = (Conexao) conexoes.get(origem);
		if (conexao1 != null && conexao1.getSalaID().equals(salaID)) {
			conexao1.out.println(mensagem);
			conexao1.out.flush();
			if (conexao1.out.checkError()) sair(conexao1);
		}

		// Manda mensagem para usuário de destino
		if (dois) {
			conexao2 = (Conexao) conexoes.get(destinatario);
			if (conexao2 != null && conexao2.getSalaID().equals(salaID) && !origem.equals(destinatario)) {
				conexao2.out.println(mensagem);
				conexao2.out.flush();
				if (conexao2.out.checkError()) sair(conexao2);
			}
		}
	}
	
	// Fecha o browse do usuário
	public void fecharBrowse(String apelido) throws RemoteException {
		Conexao con = (Conexao) conexoes.get(apelido);
		
		con.out.println(
			"<script>\n" +
			"  alert(\"Você saiu do chat porque entrou em outro local!\");\n" +
			"  top.window.close();\n" +
			"</script>\n");
		con.out.flush();
	}
	
}
