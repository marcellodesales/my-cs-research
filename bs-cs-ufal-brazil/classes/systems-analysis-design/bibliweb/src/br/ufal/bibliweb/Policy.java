package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * Policy.java
 *
 * @author Marcello de Sales
 * Criado em 17/04/2002
 */
public class Policy{
	
	/** Identificador da política. */
	private String ID;
	/** Identificador do grupo. */
	private String groupID;
	/** Número máximo de dias que o livro pode ser locado. */
	private int maxDaysRent;
	/** Número máximo de exemplares que podem ser alugados. */
	private int limitRent;
	/** Número máximo de exemplares que podem ser renovados. */
	private int limitRenovation;
	/** Número máximo de reservas que podem ser feitas. */
	private int limitReservation;
	/** Valor da multa aplicada quando ocorre um atraso na devolução do exemplar. */
	private String fineValue;
	/** Faixa de IP permitida. */
	private String rangeIPAddress;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public Policy(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM policy WHERE policy_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Política não encontrada com ID= "+ID+" !");
		} else {
			this.database = database;
			Hashtable policyState = (Hashtable)result.firstElement();
			this.ID = ID;
			this.groupID          = (String)policyState.get("group_id");
			this.maxDaysRent      = ((Integer)policyState.get("max_days_rent")).intValue();
			this.limitRent        = ((Integer)policyState.get("limit_rent")).intValue();
			this.limitRenovation  = ((Integer)policyState.get("limit_renovation")).intValue();
			this.limitReservation = ((Integer)policyState.get("limit_reservation")).intValue();
			this.fineValue        = (String)policyState.get("fine_value");
			this.rangeIPAddress   = (String)policyState.get("range_ip_address");
		}
	}
	
	/** Retorna o identificador da política. */
	public String getID(){
		return ID;
	}
	
	/** Retorna o grupo ao qual a política pertence. */
	public String getGroupID(){
		return groupID;
	}
	
	/** Retorna o número máximo de dias que o exemplar pode ficar locado. */
	public int getMaxDaysRent(){
		return maxDaysRent;
	}
	
	/** Retorna o número máximo de exemplares que podem ser alugados. */
	public int getLimitRent(){
		return limitRent;
	}
	
	/** Retorna o número máximo de renovações que podem ser feitas. */
	public int getLimitRenovation(){
		return limitRenovation;
	}
	
	/** Retorna o número máximo de reservas que podem ser feitas. */
	public int getLimitReservation(){
		return limitReservation;
	}
	
	/** Retorna o valor diário da multa quando ocorre atraso na devolução. */
	public String getFineValue(){
		return fineValue;
	}
	
	/** Retorna a faixa de IP permitida.*/
	public String getRangeIPAddress(){
		return rangeIPAddress;
	}
	
	/** Cria uma nova política. */
	public static void createNewPolicy(String groupID, int maxDaysRent, int limitRent, int limitRenovation, int limitReservation, String rentValue, String fineValue, String rangeIPAddress, DatabaseLayer database){
		try{
			database.update("INSERT INTO policy (policy_id,group_id,max_days_rent,limit_rent,limit_renovation,limit_reservation,fine_value,range_ip_address) "+
								"VALUES ('"+Utility.getNewID(database)+"','"+groupID+"','"+maxDaysRent+"','"+limitRent+"','"+limitRenovation+"','"+limitReservation+"','"+fineValue+"','"+rangeIPAddress+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
}

