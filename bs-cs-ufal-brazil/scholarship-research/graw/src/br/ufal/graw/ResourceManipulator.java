package br.ufal.graw;

import java.util.Vector;

/* Operações realizadas por todos os Cursos */
public interface ResourceManipulator{
	
	/** Gets the Links of the Course. */
    public Vector getLinks();
	
	public Vector getLinks(int offset, int limit);
	
	/** Gets the Documents of the Course. */
    public Vector getDocuments();
	
	public Vector getDocuments(int offset, int limit);
	
	public int getQuantMessages();
	
	public int getQuantDocuments();
	
	public int getQuantLinks();
	
	public int getQuantUsers();
}
