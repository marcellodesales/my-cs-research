package br.ufal.graw.whiteboard;

import java.net.Socket;
import java.io.DataOutputStream;

public class Conexao extends Thread {
	
	protected String salaID;	
	protected Socket socket;
	protected String apelido;
	protected DataOutputStream out;
	
	// Constructor
	public Conexao(Socket socket, Runnable thread) {
		super(thread);
		this.socket = socket;
	}

}