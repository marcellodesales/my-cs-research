package br.ufal.graw.chat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.util.Vector;

import br.ufal.graw.Config;

public class EnviaMensagem {
	
	RemNaoModerado objetoRemoto;
	
	// Conecta com o servidor RMI
	public EnviaMensagem() {
		try{
			Config conf = new Config();
			String portarmi = conf.getChat("rmi");
			String server = conf.getChat("server");
			objetoRemoto = (RemNaoModerado) Naming.lookup("rmi://" + server + ":" + portarmi + "/RemNaoModerado");
		} catch(RemoteException re) {
			System.err.println("erro no RMI");
		} catch (NotBoundException nbe) { }
		catch (MalformedURLException e) { }
	}
	
	// Manda mensagem unicast remotamente
	void mandarMensagemUnicast(String apelidoOrigem, String mensagem, String apelidoDestino, boolean dois, String salaID) throws RemoteException {
		objetoRemoto.sendMessageUnicast(apelidoOrigem, mensagem, apelidoDestino, dois, salaID);
	}
	
	// Manda mensagem broadcast remotamente
	void mandarMensagemBroadcast(String mensagem, String salaID) throws RemoteException {
		objetoRemoto.sendMessageBroadcast(mensagem, salaID);
	}
	
	// Recupera os apelidos dos usuários de uma sala
	Vector consegueUsuarios(String salaID) throws RemoteException {
		try {
			return objetoRemoto.getUsuarios(salaID);
		} catch (Exception e) {
			return null;
		}
	}
	
	// Recupera o número de usuários de um sala
	public int getUsersOnRoom(String salaID) throws RemoteException {
		try {
			return objetoRemoto.getUsersOnRoom(salaID);
		} catch (Exception e) {
			return 0;
		}
	}
	
	// Verifica se o usuário está na sala
	boolean usuarioExistente(String apelido) throws RemoteException {
		return objetoRemoto.usuarioExistente(apelido);
	}
	
	// Remove um usuário de uma sala
	void sair(String apel) throws RemoteException {
		objetoRemoto.sair(apel);
	}
	
	// Fecha um browse de um usuário
	void fecharBrowse(String apelido) throws RemoteException {
		objetoRemoto.fecharBrowse(apelido);
	}
}
