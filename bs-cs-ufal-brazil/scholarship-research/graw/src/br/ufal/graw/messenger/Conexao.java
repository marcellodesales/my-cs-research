package br.ufal.graw.messenger;

import java.net.Socket;
import java.io.DataOutputStream;

public class Conexao extends Thread {
	
	protected Socket socket;
	protected DataOutputStream out;
	
	public Conexao(Socket socket, Runnable thread) {
		super(thread);
		this.socket = socket;
	}
}