package br.ufal.graw.chat;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface RemNaoModerado extends Remote {

	public void sendMessageBroadcast(String mes, String salaID) throws RemoteException;
	public void sendMessageUnicast(String origem, String message, String destino, boolean dois, String salaID) throws RemoteException;
	public void sair(String apelido) throws RemoteException;
	public Vector getUsuarios(String salaID) throws RemoteException;
	public int getUsersOnRoom(String salaID) throws RemoteException;
	public void fecharBrowse(String apelido) throws RemoteException;
	public boolean usuarioExistente(String apelido) throws RemoteException;
}
