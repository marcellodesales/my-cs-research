package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.UserNotFoundException;
import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * AbstractUser.java
 *
 * @author Marcello de Sales
 */
public abstract class AbstractUser implements User{
	
	/** O identificador do usu�rio. */
	public String ID;
	/** O CPF do usu�rio. */
	public String CPF;
	/** O nome completo do usu�rio. */
	public String name;
	/** O endere�o residencial completo do usu�rio. */
	public String homeAddress;
	/** O endere�o de trabalho completo do usu�rio. */
	public String workAddress;
	/** O telefone residencial do usu�rio. */
	public String homePhone;
	/** O telefone do trabalho do usu�rio. */
	public String workPhone;
	/** O telefone celular do usu�rio. */
	public String cellPhone;
	/** O email do usu�rio. */
	public String email;
	/** O nome de acesso (login) do usu�rio. */
	public String username;
	/** A senha de acesso (login) do usu�rio. */
	public String password;
	/** O IP do �ltimo acesso do usu�rio. */
	public String lastAccessIP;
	/** A data formatada do �ltimo acesso do usu�rio. */
	public String lastAccessDate;
	/** O endere�o completo da imagem do usu�rio. */
	public String picture;
	/** A identifica��o do grupo ao qual o usu�rio pertence. */
	public String groupID;
	/** A identifica��o do status corrente do usu�rio. */
	public String statusID;
	
	/** Camada de comunica��o com o banco de dados. */
	protected DatabaseLayer database;
	/** Todo resultado recuperado do banco de dados. */
	protected Hashtable databaseState;
	
	public AbstractUser(String exemplarID, Vector stateSet) throws UserNotFoundException{
		if (stateSet.size() != 1){
			throw new UserNotFoundException("Usu�rio n�o encontrado com identificador "+exemplarID,exemplarID);
		} else {
			this.databaseState  = (Hashtable)stateSet.firstElement();
			this.ID 		    = exemplarID;
			this.groupID        = (String)this.databaseState.get("group_id");
			this.statusID       = (String)this.databaseState.get("status_id");
			this.CPF            = (String)this.databaseState.get("cpf");
			this.name           = (String)this.databaseState.get("name");
			this.homeAddress    = (String)this.databaseState.get("home_address");
			this.workAddress    = (String)this.databaseState.get("work_address");
			this.homePhone      = (String)this.databaseState.get("home_phone");
			this.workPhone      = (String)this.databaseState.get("work_phone");
			this.cellPhone      = (String)this.databaseState.get("cell_phone");
			this.email          = (String)this.databaseState.get("email");
			String imageExt     = (String)this.databaseState.get("photo_extension");
			this.picture        = (imageExt.equals("")) ? "" : this.ID + "." +imageExt;
			this.username       = (String)this.databaseState.get("username");
			this.password       = (String)this.databaseState.get("password");
			this.lastAccessIP   = (String)this.databaseState.get("last_access_IP");
			this.lastAccessDate = (String)this.databaseState.get("last_access_Date");
		}
	}

	/** Retorna o identificador do usu�rio. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna o CPF do usu�rio. */
	public String getCPF(){
		return this.CPF;
	}
	
	/** Retorna o primeiro nome do usu�rio. */
	public String getFirstName(){
		int spacePosition = this.name.indexOf(" ");
		return (spacePosition != -1) ? this.name.substring(0,spacePosition) : this.name;
	}
	
	/** Retorna o email do usu�rio */
	public String getName(){
		return this.name;
	}
	
	/** Retorna o endere�o residencial completo do usu�rio. */
	public String getHomeAddress(){
		return this.homeAddress;
	}
	
	/** Retorna o endere�o de trabalho completo do usu�rio. */
	public String getWorkAddress(){
		return this.workAddress;
	}
	
	/** Retorna o telefone residencial do usu�rio. */
	public String getHomePhone(){
		return this.homePhone;
	}
	
	/** Retorna o telefone do trabalho do usu�rio. */
	public String getWorkPhone(){
		return this.workPhone;
	}
	
	/** Retorna o telefone do trabalho do usu�rio. */
	public String getEmail(){
		return this.email;
	}
	
	/** Retorna o telefone celular do usu�rio. */
	public String getCellPhone(){
		return this.cellPhone;
	}
	
	/** Retorna o nome de login do usu�rio. */
	public String getUsername(){
		return this.username;
	}
	
	/** Retorna o password do usu�rio. */
	public String getPassword(){
		return this.password;
	}
	
	/** Retorna o IP do �ltimo acesso do usu�rio. */
	public String getLastAccessIP(){
		return this.lastAccessIP;
	}
	
	/** Retorna a data do �ltimo acesso do usu�rio. */
	public String getLastAccessDate(){
		return this.lastAccessDate;
	}
	
	/** Retorna o endere�o completo da imagem do usu�rio. */
	public String getPicture(){
		return this.picture;
	}
	
	/** Retorna a identifica��o do grupo ao qual o usu�rio pertence. */
	public String getGroupID(){
		return this.groupID;
	}
	
	/** Retorna a identifica��o do status ao qual o usu�rio pertence. */
	public String getStatusID(){
		return this.statusID;
	}
	
	/** @return Retorna o Status ao qual o usu�rio possui. */
	public Status getStatus(){
		Status status = null;
		try {
			status = new Status(this.statusID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return status;
	}
	
	/** @return Retorna o Group ao qual o usu�rio possui. */
	public Group getGroup(){
		Group group = null;
		try {
			group = new Group(this.groupID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return group;
	}
	
	/** Retorna uma inst�ncia correta do tipo de usu�rio. */
	public static User getRealUser(String userID, DatabaseLayer database) throws UserNotFoundException, ResourceNotFoundException{
		User user;
		String groupOfUser = AbstractUser.getGroupCode(userID,database);
		if ((groupOfUser.equals(Group.STUDENT_CODE)) || (groupOfUser.equals(Group.PROFESSOR_CODE))){
			user = new AcademicUser(userID,database);
		} else if (groupOfUser.equals(Group.CLERK_CODE)){
			user = null;
//				user = new Clerk(userID,database);
		} else if (groupOfUser.equals(Group.ADMIN_CODE)){
			user = null;
			//user = new Administrator(userID,database);
		} else throw new ResourceNotFoundException("Grupo de usu�rio ("+groupOfUser+") desconhecido!");
		return user;
	}
	
	/** Retorna o c�digo do grupo do usu�rio. */
	private static String getGroupCode(String userID, DatabaseLayer database) throws UserNotFoundException{
		Vector result = database.query("SELECT group_id FROM \"user\" WHERE user_id='"+userID+"'");
		String groupCode = "";
		if (result.size() == 1){
			String groupId = (String)((Hashtable)result.get(0)).get("group_id");
			try{
				Group group = new Group(groupId,database);
				groupCode = group.getCode();
			}catch (Exception e){
				e.printStackTrace();
			}
			return groupCode;
		} else throw new UserNotFoundException("Usu�rio n�o encontrado com ID="+userID+" !",userID);
	}
	
	public static void main(String args[]){
		DatabaseLayer db = new DatabaseLayer();
		//GregorianCalendar cal = new GregorianCalendar();
		//System.out.println(cal.getTimeInMillis());
		//Date today = new Date(cal.getTimeInMillis());
		try{
			//Group group = new Group("36",db);
			//group.printAll();
			//AcademicCourse acc = new AcademicCourse("32",db);
			//AcademicUser.createNewAcademicUser(group.getID(),acc.getID(),"03059038457","1998g55d001t9","Marcello Alves de Sales Junior","Rua Gon�alves Dias, 58, Bairro do Farol, Macei�-AL","UFAL","326-2884","214-1402","82-9997-1415","marcellojunior@hotmail.com",db);
			User aluno = AbstractUser.getRealUser("1019095261310",db);
			System.out.println(aluno.getGroup().getDescription());
			//System.out.println(aluno.getEmail());
			//aluno.getStatus().printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
