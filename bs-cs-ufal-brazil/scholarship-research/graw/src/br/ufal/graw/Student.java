/**
 *@author Project: noNamePibic - UFAL
 *@serialdata
 */
package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Discipline;

import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserLoginWrongPasswordException;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.DepartmentNotFoundException;

/**
 * 	Student is an entity composed by various courses which
 * 	them itself interests or/and monitors.
 * @author Rodrigo Paes
 *
 */
public class Student extends AcademicUser{
	/* In this class , the login attribute (inherited) means the student´s matriculation */
	
	public Student(String ID, DatabaseLayer database) throws UserNotFoundException{
		super(ID,database);
		
		this.result = this.database.query("SELECT * from student where userID='"+ID+"'");
		if (result.size() == 0 ){
			throw new UserNotFoundException("Student not Found with "+ID);
		}
	}
	
	/** @return True if the user monitors the Discipline */
	public boolean isMonitor(String disciplineID){
		this.result = this.database.query("SELECT disciplineID FROM monitors WHERE disciplineID='"+disciplineID+"' AND userID='"+this.ID+"'");
		return (this.result.size() ==1 );
	}
	
	/** @return True if the user monitors the Discipline */
	public boolean isMonitor(Discipline discipline){
		return this.isMonitor(discipline.getID());
	}
	
	/** Verifies if a student with professorID exists */
	public static boolean exists(String studentID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM student WHERE userID='"+studentID+"'");
		return (thisResult.size() == 1);
	}
		
	public boolean isMember(Community community){
		if (super.isMember(community)==false){
			return isMonitor(community.getID());
		}else {
			return true;
		}
	}
	 
	
	public Vector getDisciplines(){
		/* Verifica as associacoes que sao communs aos academicos */
		Vector commonDisciplines = super.getDisciplines();
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		/*
		Note que essa query exclui os usuarios que monitoram uma disciplina e sao ao mesmo tempo
		responsavel por ela, pois se eles forem responsaveis a disciplina ja esta inserida no
		vector commonDisciplines.
		 */
		/* Associacoes de monitoria */
		this.result = this.database.query("select discipline.communityID from discipline,monitors,community where "+
				" discipline.communityID = monitors.disciplineID AND "+
				" discipline.communityID = community.communityID AND "+
				" community.userID != '"+this.ID+"' AND "+
 				" monitors.userID='"+this.ID+"' ");
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
				cnfe.printStackTrace();
			}
		}
		
		/* Unindo */
		for (int i=0 ; i< commonDisciplines.size() ; i++ ){
			disciplines.add(commonDisciplines.get(i));
		}
		
		return disciplines;
	}
	
	/** @return True if the user monitors the Course */
	/*
	public boolean isMonitor(Course course){
		String courseID = course.getID();
		try{
			return ( ((Boolean)(this.courses.get(courseID))).booleanValue()) ;
		}catch(Exception e){
			return false;
		}
	}
	
	 */
	/*
	private void printData(){
		try{
			System.out.println("----------------------------------------------");
			System.out.println("Login    = "+this.login);
			System.out.println("Nome     = "+this.name);
			System.out.println("email    = "+this.email);
			System.out.println("password = "+this.password);
			System.out.println("----------------------------------------------");
			
			if (this.courses.size() > 0 ){
				System.out.println("Disciplinas: ");
				Enumeration keys = this.getCourses();
				Course cour;
				while (keys.hasMoreElements()){
					cour = (Discipline)keys.nextElement();
					System.out.print("	-> Codigo = " +cour.getID());
					System.out.println("	Monitor= "+this.courses.get(cour.getID()));
				}
			}else System.out.println("No interests to any courses");
			
			System.out.println("----------------------------------------------");
			System.out.println();
		}catch(Exception e){
			System.out.println(e.getMessage());
		 }
	}

	/** Prints an error message for debug */
	private void error(String msg){
		System.out.println(msg);
	}
	
	public static void main(String args[]){
		try{
		DatabaseLayer data = new DatabaseLayer();
		Student student = new Student("1015331789194",data);
		System.out.println(student.isPassword("barros"));
			/*
		System.out.println(student.getPrivateDisciplines().size());
		System.out.println(student.getIntraDepartmentDisciplines().size());
		System.out.println(student.getIntraInstituitionDisciplines().size());
		System.out.println(student.getInterInstituitionDisciplines().size());
			
			 */
			
			/** Recuperar do Banco de Dados */
	/*
			Student s = new Student("1005657146070",data);
			
			Enumeration en;
						Discipline discipline;
			en = s.getCourses();
			while  (en.hasMoreElements()){
					discipline = (Discipline)en.nextElement();
					System.out.println("Nome= "+discipline.getName());
					System.out.println("Descr= "+discipline.getDescription());
			 }
			s.printData();
	
			//s.setData("Carlinha Perez","carla@duralex.com.br",s.getPassword());
			//s.printData();
			//Hashtable d = new Hashtable();
			//d.put("SE-254",new Boolean(true));
			//d.put("PG-234",new Boolean(true));
			//d.put("IA-555",new Boolean(false));

			//s.setInterests(d);
			//s.printData();


			/*Student s = Student.createNewStudent("1999g55d042v",data);
			s.printData();
			s.setData("Rodrigo Mosca","mosca@mosca.mosca","mosca");
			s.printData();
			
			 */
	
		}catch(Exception e){
			e.printStackTrace();
		 }
			/*s1.doLogin("1998g55d041v","mesma");
		//Student s2 	= new Student("2000g55d041v","vixe");
		//Student s3 			= new Student("2001g55d041v","perere");
		Hashtable teste = new Hashtable();
		teste.put("IA-234",new Boolean(true));
		teste.put("PG-234",new Boolean(true));
		teste.put("ES-234",new Boolean(false));
		teste.put("IA-555",new Boolean(false));

		System.out.println("Student Recuperado...");
		s1.printData();

		s1.setData("Rodrigo Paes","mosca@yahoo.com.br","outro");
		System.out.println("Info setadas...");
		s1.printData();

		s1.setInterests(teste);
		System.out.println("Interesses setados...");
		s1.printData();

		s1.setData("1999g55d041v","Rodrigo Paes","r0drigopaes@yahoo.com.br","mesma");
		System.out.println("Info com login setadas...");
		s1.printData();
		//s2.printData();
		//s3.printData();
		}catch(Exception e){
			e.printStackTrace();
			 */
		}
}
