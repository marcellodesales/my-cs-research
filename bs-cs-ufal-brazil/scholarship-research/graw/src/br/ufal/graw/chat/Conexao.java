package br.ufal.graw.chat;
import java.io.*;
import java.net.*;
import java.util.*;

public class Conexao extends Thread {

	protected String salaID;	
//	protected String ipporta;
	protected String apelido;
	protected PrintStream out;
	protected BufferedReader in;
	protected Socket serverSocket;
	
	// Constructor
	public Conexao(Runnable serverObject, Socket serverSocket) {
		super(serverObject);

		// conseguindo o ip e a porta
/*		System.out.println("!");
		String inet = serverSocket.getInetAddress().toString();
		System.out.println("!!");
		String ip=inet.substring(inet.indexOf("/")+1);	
		System.out.println("!!!");
		ipporta=ip+":"+serverSocket.getPort();	
		System.out.println("!!!!");
		System.out.println("endereco ip do socket: "+ipporta); */

		//termino do ip e porta
		this.serverSocket = serverSocket;

		// Cria escrita e leitura do socket
		try{
			out = new PrintStream(serverSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		} catch(Exception e) {
			System.out.println("erro no stream do connection "+ e.getMessage());
		}
	}

	// Define apelido
	protected void setApelido(String apelido) {
		this.apelido = apelido;	
	}
	
	// Recupera apelido
	protected String getApelido() {
		return apelido;	
	}
	
	// Define sala
	protected void setSalaID(String salaID) {	
		this.salaID = salaID;
	}
	
	// Recupera sala
	protected String getSalaID() {
		return salaID;
	}
}