package br.ufal.graw;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.sql.SQLException;

import br.ufal.graw.Utility;
import br.ufal.graw.exception.GrawException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.AcademicCourseNotFoundException;

public class AcademicCourse{
		
	private DatabaseLayer database ;
	private Vector result;
	private String ID;
	private String instituteID;
	private String departmentID;
	private String name;
	private String code;
	
	//to create the xml file
	private FileReader configurationFile;
	private BufferedReader readerOfFile;
	
	public AcademicCourse(String academicCourseID, DatabaseLayer database)
		throws AcademicCourseNotFoundException{
		this.database = database;
		this.result = this.database.query("SELECT * FROM academiccourse WHERE academicCourseID='"+academicCourseID+"'");
		if (this.result.size() > 0){
			this.initObject((Hashtable)result.firstElement());
		} else throw new AcademicCourseNotFoundException("Curso Acadêmico não encontrado com ID = "+academicCourseID,academicCourseID);
	}
	
	/** Initialize the state of the department */
	private void initObject(Hashtable data){
		this.instituteID  = (String)data.get("instituteID");
		this.departmentID = (String)data.get("departmentID");
		this.ID           = (String)data.get("academicCourseID");
		this.name         = (String)data.get("name");
		this.code         = (String)data.get("code");
	}
		
	/**
	@return Todas as disciplinas do curso em questao.
	 */
	public Enumeration getDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		this.result = this.database.query("SELECT communityID from discipline WHERE academicCourseID='"+this.ID+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		return disciplines.elements();
	}
		
	/** Verifies if an academic course with academiccCouseID exists. */
	public static boolean exists(String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT academicCourseID FROM academiccourse WHERE academicCourseID='"+academicCourseID+"'");
		return (thisResult.size() == 1);
	}
	
	public String getID(){
		return this.ID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getInstituteID(){
		return this.instituteID;
	}
	
	public String getDepartmentID(){
		return this.departmentID;
	}
	
	public Department getDepartment() throws DepartmentNotFoundException{
		return new Department(this.departmentID,database);
	}
	
	public Institute getInstitute() throws InstituteNotFoundException, DepartmentNotFoundException{
		return new Department(this.departmentID,database).getInstitute(); //returns an Institute from department
	}
	
	public String getCode(){
		return this.code;
	}
	
	
	public static String getDepartmentID(String academicCourseID, DatabaseLayer database)
		throws AcademicCourseNotFoundException{
		
		if (AcademicCourse.exists(academicCourseID,database)){
			Vector result = database.query("SELECT departmentID FROM academiccourse WHERE academicCourseID='"+academicCourseID+"'");
			return (String)((Hashtable)result.firstElement()).get("departmentID");
		} else throw new AcademicCourseNotFoundException("Curso Acadêmico inexistente ID="+academicCourseID,academicCourseID);
	}
	
	public static String getInstituteID(String academicCourseID, DatabaseLayer database)
		throws AcademicCourseNotFoundException{
		
		if (AcademicCourse.exists(academicCourseID,database)){
			Vector result = database.query("SELECT instituteID FROM academiccourse WHERE academicCourseID='"+academicCourseID+"'");
			return (String)((Hashtable)result.firstElement()).get("instituteID");
		} else throw new AcademicCourseNotFoundException("Curso Acadêmico inexistente ID="+academicCourseID,academicCourseID);
	}
	
	public void printAll(){
		System.out.println(this.ID);
		System.out.println(this.name);
		System.out.println(this.code);
		System.out.println(this.departmentID);
		System.out.println(this.instituteID);
	}
	
	/** Creates a new XML Students from Department file */
	public void createNewXMLDepartmentFile(BufferedReader readerOfFile){
			try {
		 		this.readerOfFile = readerOfFile;
				this.insertAllStudents(readerOfFile);
			} catch(Exception e) {
				Utility.log(Department.ERRORLOG,e);
				System.err.println(e.getMessage());
			}
	}
		
	/** Creates a new XML Students from Department file */
	public void createNewXMLDepartmentFile(String filePath, String xmlFile){
			try {
				this.configurationFile = new FileReader(filePath);
		 		this.readerOfFile      = new BufferedReader(configurationFile);
				this.createXMLFile(this.getTXTStudents(),xmlFile);
				
			} catch(Exception e) {
				System.err.println(e.getMessage());
				
			} finally {
				
				try {
					this.readerOfFile.close();
					this.configurationFile.close();
				} catch(IOException a) {
				}
			}
	}
	
	/**
	 * Gets a vector of Hashtable with keys:
	 * username, name, matriculation
	 */
	private Vector getTXTStudents() {
		String linha = new String();
		Vector students = new Vector();
		try {
			while((linha = this.readerOfFile.readLine()) != null){
				StringTokenizer token = new StringTokenizer(linha,",");
				String username = token.nextToken().toLowerCase();
				String name     = token.nextToken();
				String matri    = token.nextToken().toUpperCase();
				Hashtable student = new Hashtable();
				student.put("username", username);
				student.put("name",name);
				student.put("matriculation",matri);
				students.add(student);
			}
		} catch(IOException f) {
			System.err.println("Arquivo "+this.configurationFile + " não encontrado!");
		}
		return students;
	}
	
	/** Creates an xml file of students */
	private void createXMLFile(Vector students, String xmlFile){
		Enumeration e = students.elements();
		try {
			FileWriter xml = new FileWriter(xmlFile);
			xml.write("<tci>\n");
			while(e.hasMoreElements()){
				Hashtable student = (Hashtable)e.nextElement();
				String name = (String)student.get("name");
				String matr = (String)student.get("matriculation");
				String user = (String)student.get("username");
				
				xml.write("	<student id="+matr+">\n");
				xml.write("		<name>"+name+"</name>\n");
				xml.write("		<username>"+user+"</username>\n");
				xml.write("	</student>\n");
				xml.flush();
			}
			xml.write("</tci>");
			xml.flush();
			xml.close();
		} catch (IOException ioe){
			Utility.log(Department.ERRORLOG,ioe);
			System.err.println(ioe.getMessage());
		}
	}
	
	/** Saves all students from XML file to the database */
	public void insertStudentsFromXML(String xmlconf){
		try {
			SAXBuilder saxBuilder = new SAXBuilder(false);
			org.jdom.Document studentXML = saxBuilder.build(new File(xmlconf));
			this.parseStudentsFile(studentXML);
		} catch (JDOMException jde){
			System.err.println("Problemas com JDOM: ");
			jde.printStackTrace();
		}
	}
	
	/** Parses the XML student file */
	private void parseStudentsFile(org.jdom.Document configFile){
		Element root    = configFile.getRootElement();
		List students   = root.getChildren("student");
		Iterator iter   = students.iterator();
		while (iter.hasNext()){
			Element student = (Element)iter.next();
			
			String matri    = student.getAttributeValue("matriculation");
			String username = student.getChild("username").getText();
			System.out.println(matri+" "+username);
			//this.insertNewDepartmentStudent(username,matri);
		}
	}
	
	public void insertAllStudents(BufferedReader reader){
		String line = null;
		try {
			// read past the header information:
			// read lines until one contains "Content-Type:"
			while ((line = reader.readLine()) != null)
				if (line.indexOf("Content-Type:") != -1)
					break;
			// read one more line
			reader.readLine();
			// now we are at the content of the file,
			// so we read lines until we get to the one with "-----------------------------"
			// which marks the end of the content of the file

			while((line = reader.readLine()) != null){
				if (line.indexOf("-----------------------------") == -1){
					StringTokenizer token = new StringTokenizer(line,",");
					try {
						String username = token.nextToken().toLowerCase();
						String name     = token.nextToken();
						String matri    = token.nextToken().toUpperCase();
						this.insertNewAcademicStudent(username,matri);
						Utility.log("C:/errorUploadStudents.log","Depois de criar");
					} catch (java.util.NoSuchElementException e){
					}
				} else break;
			}
		} catch(IOException f) {
			Utility.log("C:/errorUploadStudents.log","Arquivo "+this.configurationFile + " não encontrado!");
		}
	}
	
	/** Verifies if a student exists */
	private boolean studentAlreadyExists(String username){
		this.result = this.database.query("SELECT userName FROM academicuser WHERE userName='"+username+"' AND academicCourseID='"+this.ID+"'");
		return (this.result.size() == 1);
	}
	
	/** Inserts a new Student in the department in Database */
	public void insertNewAcademicStudent(String username, String matriculation){
		try {
			if (!this.studentAlreadyExists(username)){
				this.database.update("INSERT INTO academicuser (userID, instituteid, departmentid, academiccourseid,username,matriculation,kindofuserid) "+
									 " VALUES ('','"+this.instituteID+"','"+this.departmentID+"','"+this.ID+"','"+username+"','"+matriculation+"','"+User.STUDENT+"')");
			}
		} catch (SQLException sqle){
				sqle.printStackTrace();
		}
	}
	
	/** Inserts a new Student in the department in Database */
	public void insertNewAcademicProfessor(String username, String matriculation){
		try {
			if (!this.studentAlreadyExists(username)){
				this.database.update("INSERT INTO academicuser (userID, instituteid, departmentid, academiccourseid,username,matriculation,kindofuserid) "+
									 " VALUES ('','"+this.instituteID+"','"+this.departmentID+"','"+this.ID+"','"+username+"','"+matriculation+"','"+User.PROFESSOR+"')");
			}
		} catch (SQLException sqle){
				sqle.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			AcademicCourse acc = new AcademicCourse("1010426419860",new DatabaseLayer());
			//System.out.println(AcademicCourse.exists("1010426419860",new DatabaseLayer()));
			acc.printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
