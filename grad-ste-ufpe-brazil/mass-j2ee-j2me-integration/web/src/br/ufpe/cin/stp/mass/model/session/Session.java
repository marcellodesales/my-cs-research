package br.ufpe.cin.stp.mass.model.session;

import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * Session is the main information about the mass questions.
 * @author Marcello Sales Jr.
 * @version 1.0
 * @created 24-jul-2004 18:18:45
 */
public interface Session {

	/**
	 * The questions of the application
	 */
	public Iterator getQuestions();

	/**
	 * The results of the session
	 */
	public Iterator getAnswers();
	
	/**
	 * @created 01/07/2004 22:47:27
	 * @return the number of subimited answers.
	 */
	public int numberOfAnswers();
	
	/**
	 * @param answer is the user's anwers.
	 * @throws SessionClosedException if the session is already closed.
	 * @created 31/07/2004 18:00:49
	 */
	public void addAnswer(Answer answer) throws SessionClosedException;

	/**
	 * The start day of the session
	 */
	public GregorianCalendar getTime();

	/**
	 * Verifies if the session is already closed and it won't accept answers at all.
	 */
	public boolean isOpened();
	
	/**
	 * @created 01/07/2004 23:47:53
	 * Closes a session, making it impossible to post answers.
	 */
	public void closeSession();
	
	/**
	 * @created 01/07/2004 22:44:23
	 * @return the identification of a given session.
	 */
	public String getID();
	
	/**
	 * @created 01/07/2004 22:44:34
	 * @return the title of the given session.
	 */
	public String getTitle();
}

