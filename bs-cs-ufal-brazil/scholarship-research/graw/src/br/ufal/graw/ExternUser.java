/**
 *@author Project: noNamePibic - UFAL
 *@serialdata
 */
package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import br.ufal.graw.DatabaseLayer;

import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserLoginWrongPasswordException;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.PersistentInformationException;

/**
	Student is an entity composed by various disciplines which
	them itself interests or/and monitors.

*/
public class ExternUser extends AbstractUser{
	
	private DatabaseLayer database;
	private Vector result;
	
	public ExternUser(String userID, DatabaseLayer database) throws UserNotFoundException{
		super(userID,database);
	}
}
	
