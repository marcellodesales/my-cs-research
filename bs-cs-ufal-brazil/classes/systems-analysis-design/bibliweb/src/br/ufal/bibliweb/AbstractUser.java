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
	
	/** O identificador do usuário. */
	public String ID;
	/** O CPF do usuário. */
	public String CPF;
	/** O nome completo do usuário. */
	public String name;
	/** O endereço residencial completo do usuário. */
	public String homeAddress;
	/** O endereço de trabalho completo do usuário. */
	public String workAddress;
	/** O telefone residencial do usuário. */
	public String homePhone;
	/** O telefone do trabalho do usuário. */
	public String workPhone;
	/** O telefone celular do usuário. */
	public String cellPhone;
	/** O email do usuário. */
	public String email;
	/** O nome de acesso (login) do usuário. */
	public String username;
	/** A senha de acesso (login) do usuário. */
	public String password;
	/** O IP do último acesso do usuário. */
	public String lastAccessIP;
	/** A data formatada do último acesso do usuário. */
	public String lastAccessDate;
	/** O endereço completo da imagem do usuário. */
	public String picture;
	/** A identificação do grupo ao qual o usuário pertence. */
	public String groupID;
	/** A identificação do status corrente do usuário. */
	public String statusID;
	
	/** Camada de comunicação com o banco de dados. */
	protected DatabaseLayer database;
	/** Todo resultado recuperado do banco de dados. */
	protected Hashtable databaseState;
	
	public AbstractUser(String exemplarID, Vector stateSet) throws UserNotFoundException{
		if (stateSet.size() != 1){
			throw new UserNotFoundException("Usuário não encontrado com identificador "+exemplarID,exemplarID);
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

	/** Retorna o identificador do usuário. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna o CPF do usuário. */
	public String getCPF(){
		return this.CPF;
	}
	
	/** Retorna o primeiro nome do usuário. */
	public String getFirstName(){
		int spacePosition = this.name.indexOf(" ");
		return (spacePosition != -1) ? this.name.substring(0,spacePosition) : this.name;
	}
	
	/** Retorna o email do usuário */
	public String getName(){
		return this.name;
	}
	
	/** Retorna o endereço residencial completo do usuário. */
	public String getHomeAddress(){
		return this.homeAddress;
	}
	
	/** Retorna o endereço de trabalho completo do usuário. */
	public String getWorkAddress(){
		return this.workAddress;
	}
	
	/** Retorna o telefone residencial do usuário. */
	public String getHomePhone(){
		return this.homePhone;
	}
	
	/** Retorna o telefone do trabalho do usuário. */
	public String getWorkPhone(){
		return this.workPhone;
	}
	
	/** Retorna o telefone do trabalho do usuário. */
	public String getEmail(){
		return this.email;
	}
	
	/** Retorna o telefone celular do usuário. */
	public String getCellPhone(){
		return this.cellPhone;
	}
	
	/** Retorna o nome de login do usuário. */
	public String getUsername(){
		return this.username;
	}
	
	/** Retorna o password do usuário. */
	public String getPassword(){
		return this.password;
	}
	
	/** Retorna o IP do último acesso do usuário. */
	public String getLastAccessIP(){
		return this.lastAccessIP;
	}
	
	/** Retorna a data do último acesso do usuário. */
	public String getLastAccessDate(){
		return this.lastAccessDate;
	}
	
	/** Retorna o endereço completo da imagem do usuário. */
	public String getPicture(){
		return this.picture;
	}
	
	/** Retorna a identificação do grupo ao qual o usuário pertence. */
	public String getGroupID(){
		return this.groupID;
	}
	
	/** Retorna a identificação do status ao qual o usuário pertence. */
	public String getStatusID(){
		return this.statusID;
	}
	
	/** @return Retorna o Status ao qual o usuário possui. */
	public Status getStatus(){
		Status status = null;
		try {
			status = new Status(this.statusID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return status;
	}
	
	/** @return Retorna o Group ao qual o usuário possui. */
	public Group getGroup(){
		Group group = null;
		try {
			group = new Group(this.groupID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return group;
	}
	
	/** Retorna uma instância correta do tipo de usuário. */
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
		} else throw new ResourceNotFoundException("Grupo de usuário ("+groupOfUser+") desconhecido!");
		return user;
	}
	
	/** Retorna o código do grupo do usuário. */
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
		} else throw new UserNotFoundException("Usuário não encontrado com ID="+userID+" !",userID);
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
			//AcademicUser.createNewAcademicUser(group.getID(),acc.getID(),"03059038457","1998g55d001t9","Marcello Alves de Sales Junior","Rua Gonçalves Dias, 58, Bairro do Farol, Maceió-AL","UFAL","326-2884","214-1402","82-9997-1415","marcellojunior@hotmail.com",db);
			User aluno = AbstractUser.getRealUser("1019095261310",db);
			System.out.println(aluno.getGroup().getDescription());
			//System.out.println(aluno.getEmail());
			//aluno.getStatus().printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
