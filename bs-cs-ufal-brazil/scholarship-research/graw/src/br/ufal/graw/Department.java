package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Date;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Student;
import br.ufal.graw.Utility;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.DepartmentAlreadyExistsException;
import br.ufal.graw.exception.UserNotFoundException;

/**
 * Department.java
 *
 * @author Marcello de Sales
 */
public class Department {
	
	private DatabaseLayer database;
	private Vector result;
	
	private String ID;
	private String instituteID;
	private String name;
	private String webdomain;
	private String departmentCode;
	
	public static String DEPARTMENT_XML_DIR = "\\\\192.168.10.14\\c\\desenv\\noNamePibic\\config\\students-departments\\";
	public static String ERRORLOG = "C:\\ERROR.log";
	public Department(){
		
	}
	
	public Department(String departmentID, DatabaseLayer database) throws DepartmentNotFoundException{
		this.database = database;
		this.result = this.database.query("SELECT * FROM department WHERE departmentID='"+departmentID+"'");
		if (this.result.size() > 0){
			this.initObject((Hashtable)result.firstElement());
		}else{
			throw new DepartmentNotFoundException("Departament not found.", ID);
		 }
	}

	/** Initialize the state of the department */
	private void initObject(Hashtable data){
		this.ID          = (String)data.get("departmentID");
		this.instituteID = (String)data.get("instituteID");
		this.name     	 = (String)data.get("name");
		this.webdomain   = (String)data.get("webdomain");
		this.departmentCode = (String)data.get("departmentCode");
	}
	
    public static Department createNewDepartment(String instituteID, String code, String name, String webdomain, DatabaseLayer database)
	throws DepartmentAlreadyExistsException{
		Vector thisResult = new Vector();
		thisResult = database.query("SELECT departmentID FROM department WHERE departmentCode='"+code+"'");
		if (thisResult.size() > 0){
			throw new DepartmentAlreadyExistsException("Departmento already exists.", code);
		}else {
			Department depto = new Department();
			depto.createInDatabase(instituteID,code, name, webdomain, database);
			return depto;
		}
    }

	/** Creates a new department in a database. */
	private void createInDatabase(String instituteID, String code, String name, String webdomain, DatabaseLayer database){
		this.database = database;
		try{
			this.ID  = (new Date().getTime())+"";
			code =  Utility.transformToDatabase(code).toUpperCase();
			this.database.update("INSERT INTO department VALUES ('"+this.ID+"','"+instituteID+"','"+code+"','"+name+"','"+webdomain+"')");
			this.instituteID    = instituteID;
			this.name           = name;
			this.webdomain      = webdomain;
			this.departmentCode = code;
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
	}
	
	/** @return The department's ID */
	public String getID(){
		return this.ID;
	}
	
	/** @return The department's name */
	public String getName(){
		return this.name;
	}
	
	/** @return The department's domain */
	public String getWebDomain(){
		return this.webdomain;
	}
		
	/** @return The department's code */
	public String getCode(){
		return this.departmentCode;
	}
	
	public String getInstituteID(){
		return this.instituteID;
	}
	
	public Institute getInstitute() throws InstituteNotFoundException{
		return new Institute(this.instituteID,this.database);
	}
	
	
	/** Verifies if a student exists */
	private boolean studentAlreadyExists(String username){
		this.result = this.database.query("SELECT username FROM student WHERE username='"+username+"' AND departmentID='"+this.ID+"'");
		return (this.result.size() == 1);
	}
	
	/** Inserts a new Student in the department in Database */
	private void insertNewDepartmentStudent(String username, String matriculation){
			try {
				if (!this.studentAlreadyExists(username)){
					this.database.update("INSERT INTO student VALUES ('','"+this.ID+"','"+username+"','"+matriculation+"')");
				}
			} catch (SQLException sqle){
					sqle.printStackTrace();
			}
	}
	
	public static String getInstituteID(String departmentID, DatabaseLayer database)
		throws DepartmentNotFoundException{
		
		if (Department.exists(departmentID,database)){
			Vector result = database.query("SELECT instituteID FROM department WHERE departmentID='"+departmentID+"'");
			return (String)((Hashtable)result.firstElement()).get("instituteID");
		} else throw new DepartmentNotFoundException("Departamento inexistente ID="+departmentID,departmentID);
	}
	
	/** Gets the Links of the Discipline. */
    public Vector getStudents(int offset, int limit){
		Vector students = new Vector();
    	this.result  = this.database.query("SELECT userID FROM student WHERE departmentID='"+this.ID+"' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String userID   = (String)((Hashtable)this.result.get(i)).get("userID");
					Student student = new Student(userID,this.database);
		  			students.add(student);
	      		}
        	}
		} catch (UserNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return students;
    }

	/** Gets the Documents of the Discipline. */
    public Vector getAllDocuments(){
		Vector students = new Vector();
    	this.result  = this.database.query("SELECT userID FROM student WHERE departmentID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String userID   = (String)((Hashtable)this.result.get(i)).get("userID");
					Student student = new Student(userID,this.database);
		  			students.add(student);
	      		}
        	}
		} catch (UserNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return students;
    }
	
	/** Verifies if a department with departmentID exists. */
	public static boolean exists(String departmentID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT departmentID FROM department WHERE departmentID='"+departmentID+"'");
		return (thisResult.size() == 1);
	}
	
	public void printData(){
		System.out.println("----------------------------------------------");
		System.out.println("DepartmentID = " + this.getID());
		System.out.println("Code         = " + this.getCode());
		System.out.println("Name         = " + this.getName());
		System.out.println("WebDomain    = " + this.getWebDomain());
		System.out.println("----------------------------------------------");
	}
	
	public static void main(String[] args){
				
		try {
			
			DatabaseLayer database = new DatabaseLayer();
			Department tci = new Department("1010362252870", database);
			tci.printData();
			//tci.createNewXMLDepartmentFile("C:/desenv/students.txt",Department.DEPARTMENT_XML_DIR+tci.getCode()+".xml");
			//tci.insertStudentsFromXML(Department.DEPARTMENT_XML_DIR+tci.getCode()+".xml");
			//Department mat = Department.createNewDepartment("TCI","Departamento de Tecnologia de Informação","tci.ufal.br",database);
			
		//} catch (DepartmentAlreadyExistsException daee){
			//daee.printStackTrace();
		} catch (DepartmentNotFoundException dnfe){
			dnfe.printStackTrace();
		}
	}
}
