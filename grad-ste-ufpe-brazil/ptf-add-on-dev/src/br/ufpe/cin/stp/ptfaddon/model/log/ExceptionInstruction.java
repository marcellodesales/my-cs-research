/*
 * Created on 02/06/2004 10:18:16
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.ptfaddon.model.log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ExceptionInstruction is a kind of Instruction that has a list
 * (java.util.List) of the exception's lines.
 *
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 02/06/2004
 */
public class ExceptionInstruction extends Instruction {

	/**
	 * <code>exceptionList</code> is the list of the exception steps.
	 */
	private List exceptionList = new LinkedList();

	/**
	 * Creates a new ExceptionInstruction with the contents being the
	 * message that describes the exception.
	 * @param content is the description of the exception message.
	 */
	public ExceptionInstruction(String content){
		super(content);
		this.exceptionList.add(content);
	}

	/**
	 * Adds the list other exception's lines, containing 'at...' that
	 * indicates the classes.
	 * (08/06/2004 02:12:48)
	 * @param content is the line of the exception.
	 */
	public void add(String content){
		this.exceptionList.add(content);
	}

	/**
	 * (08/06/2004 02:14:05)
	 * @return the rest of lines of the exception.
	 */
	public Iterator getExceptionContent(){
		return this.exceptionList.iterator();
	}

    public List getExceptionContentList(){
       return this.exceptionList;
    }
}
