package br.ufal.graw.web.validation;

import br.ufal.graw.web.validation.reitoriaUfal.DatabaseAcess;

import java.util.Vector;
import java.util.Hashtable;

/**
 * Essa classe é usada para realizar buscas na base de dados da UFAL.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class UfalSearcher{
	
	Vector result = new Vector();
	
	/**
	 * Procura um usuário na base da ufal a partir do número da sua matrícula.
	 */
	public UfalUserBean lookupUser(String registration) throws Exception{
		DatabaseAcess database 	= new DatabaseAcess();
		UfalUserBean user 		= null;
		result = database.query("select * from UsuariosUfal WHERE matricula LIKE '"+registration+"' ORDER BY nome ASC");
		if (result.size()>0){
			Hashtable data = (Hashtable)result.firstElement();
			user = new UfalUserBean();
			user.setCpf((String)data.get("cpf"));
			user.setName((String)data.get("nome"));
			user.setRegistration((String)data.get("matricula"));
			user.setKindOfUser((String)data.get("tipoUsuario"));
			user.setStatus((String)data.get("status"));
			database.disconnect();
			return user;
		}else{
			database.disconnect();
			throw new Exception("Usuário não encontrado");
		}
		
	}
	
	
	/**
	 *
	 */
	public static void main(String[] args){
		UfalSearcher s = new UfalSearcher();
		try{
			UfalUserBean user = s.lookupUser("1998G55D004R");
			System.out.println(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

