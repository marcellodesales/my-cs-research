package br.ufal.graw.messenger;

import java.io.DataOutputStream;

public class SalaArquivo {
	
	protected int ID;
	protected DataOutputStream remetente, destinatario;
	
	public SalaArquivo(int ID, DataOutputStream remetente, DataOutputStream destinatario) {
		this.ID = ID;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}
}