package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;

import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.GrawException;

/**
 *
 * @author Created by Marcello de Sales
 */
public class AcademicUser extends AbstractUser{

	/** The matriculation of the user */
	private String matriculation;
	/** The course where the students is at. Ex.: Computer Science */
	private String academicCourseID;
	/** The course where the students is at. Ex.: Tecnologia da Informação */
	private String departmentID;
	/** The course where the students is at. Ex.: Universidade Federal de Alagoas */
	private String instituteID;
	
	private String userName;
	
	public AcademicUser(String userID, DatabaseLayer database)
		throws UserNotFoundException{
		
		super(userID, database);
			
		this.result   = this.database.query("SELECT * from academicuser where userID='"+ID+"'");
		if (result.size() == 0 )
			throw new UserNotFoundException("AcademicUser not Found with "+ID);
		else {
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	private void initObject(Hashtable data){
		this.academicCourseID 	= (String)data.get("academicCourseID");
		this.departmentID      	= (String)data.get("departmentID");
		this.instituteID       	= (String)data.get("instituteID");
		this.userName 			= (String)data.get("userName");
		this.matriculation      = (String)data.get("matriculation");
	}
		
	/* Retorna todas as disciplinas que esse usuario academico esta associado */
	public Vector getDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		/*
		Explicao da query em algebra relacional, onde P = pi e S = sigma:
		
		 R1 <- P(communityID)(discipline * communityInterests)
		 AD <- S(userID=this.ID) R1
		
		 R2 <- (discipline * community)
		 RESPON <- P(communityID)( S(responibleID=this.ID)(R2) )
		 */
		
		/* Associacoes diretas em communityinterests */
		this.result = this.database.query("select discipline.communityID from discipline,communityinterests where "+
 				" communityinterests.communityID = discipline.communityID AND "+
				" communityinterests.status='"+Community.MEMBER_ACTIVED+"' AND "+
 				" communityinterests.userID='"+this.ID+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		/* Associacoes diretas de responsabilidade em community */
		this.result = this.database.query("select community.communityID from community,discipline where "+
				" discipline.communityID = community.communityID AND "+
				" community.status='"+Community.ACTIVED+"' AND "+
 				" community.userID='"+this.ID+"' ");
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		return disciplines;
	}
	
	public String getMatriculation(){
		return this.matriculation;
	}
	
	public String getAcademicUsername(){
		return this.userName;
	}
	
	public String getAcademicCourseID(){
		return this.academicCourseID;
	}
	
	public String getDepartmentID(){
		return this.departmentID;
	}
	
	public String getInstituteID(){
		return this.instituteID;
	}
	
	public AcademicCourse getAcademicCourse() throws GrawException{
		return new AcademicCourse(this.academicCourseID,this.database);
	}
	
	public Department getDepartment() throws GrawException{
		return new Department(this.departmentID,this.database);
	}
	
	public Institute getInstitute() throws GrawException{
		return new Institute(this.instituteID,this.database);
	}
	
	/**
	@return Todas as disciplinas(Disciplines) que possuem visibilidade Privada
	e que sao do mesmo curso que o Aluno.
	 */
	public Vector getPrivateDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		this.result = this.database.query("SELECT discipline.communityID from discipline,community "+
							"WHERE community.communityID=discipline.communityID "+
							"AND discipline.academicCourseID='"+this.academicCourseID+"' "+
							"AND community.visibility='"+Visibility.PRIVATE+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		return disciplines;
	}
	
	
	/**
	@return Todas as disciplinas(Disciplines) que possuem visibilidade Intra-Department e
	que pertencem de uma forma indireta ao mesmo departamento do usuario.
	 */
	public Vector getIntraDepartmentDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		
		
		/* Seleciona todas as disciplinas que possuem visibilidade Intra-Department e
			que pertencem de uma forma indireta ao mesmo departamento do usuario.
		 */
		this.result = this.database.query("SELECT discipline.communityID from discipline,community "+
							"WHERE community.communityID=discipline.communityID "+
							"AND community.visibility='"+Visibility.INTRA_DEPARTMENT+"' "+
							"AND discipline.departmentID='"+this.departmentID+"'  AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		return disciplines;
	}
	
	public Vector getIntraInstituitionDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;

		
		/* Seleciona todas as disciplinas que possuem visibilidade Intra-Instituition e
			que pertencem de uma forma indireta a mesma instiuicao do usuario.
		 */
		this.result = this.database.query("SELECT discipline.communityID from discipline,community "+
							"WHERE community.communityID=discipline.communityID "+
							"AND community.visibility='"+Visibility.INTRA_INSTITUTION+"' "+
							"AND discipline.instituteID='"+this.instituteID+"'  AND community.status='"+Community.ACTIVED+"'");
		
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
		
		return disciplines;
	}
	
	public Vector getInterInstituitionDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		this.result = this.database.query("SELECT discipline.communityID from discipline,community "+
							"WHERE community.communityID=discipline.communityID "+
							"AND community.visibility='"+Visibility.INTER_INSTITUTION+"'  AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		return disciplines;
	}
	
	public void proposeNewGroup(String title, String description, int visibility, int association, String kind, String categoryID,String subcategoryID,String goal,Community course){

		Group.createAGroupInDatabase(this.database,this,title,description,visibility,association,kind,categoryID,subcategoryID,goal,course);
	}
	
	/**
	 */
	public Vector getPrivateExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		this.result = this.database.query("SELECT extracourse.communityID from extracourse,community "+
							"WHERE community.communityID=extracourse.communityID "+
							"AND extracourse.academicCourseID='"+this.academicCourseID+"' "+
							"AND community.visibility='"+Visibility.PRIVATE+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
			}
		}
		
		return extraCourses;
	}
	
	public Vector getIntraDepartmentExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		/* Seleciona todas as disciplinas que possuem visibilidade Intra-Department e
			que pertencem de uma forma indireta ao mesmo departamento do usuario.
		 */
		this.result = this.database.query("SELECT extracourse.communityID from extracourse,community "+
							"WHERE community.communityID=extracourse.communityID "+
							"AND community.visibility='"+Visibility.INTRA_DEPARTMENT+"'"+
							"AND extracourse.departmentID='"+this.departmentID+"'  AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
			}
		}
		
		return extraCourses;
	}
	
	public Vector getIntraInstituitionExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		
		/* Seleciona todas as disciplinas que possuem visibilidade Intra-Instituition e
			que pertencem de uma forma indireta a mesma instiuicao do usuario.
		 */
		this.result = this.database.query("SELECT extracourse.communityID from extracourse,community "+
							"WHERE community.communityID=extracourse.communityID "+
							"AND community.visibility='"+Visibility.INTRA_INSTITUTION+"' "+
							"AND extracourse.instituteID='"+this.instituteID+"'  AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
			}
		}
		
		return extraCourses;
	}
	
	public Vector getInterInstituitionExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		this.result = this.database.query("SELECT extracourse.communityID from extracourse,community "+
							"WHERE community.communityID=extracourse.communityID "+
							"AND community.visibility='"+Visibility.INTER_INSTITUTION+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
			}
		}
		
		return extraCourses;
	}
	
	public Vector getPrivateGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		Community course;
		String academicCourseID = null;
		
		/* Seleciona todos os cursos privados que sao subordinados a algum curso extra curricular ou disciplina */
		this.result = this.database.query("SELECT grouping.groupingID FROM grouping,community WHERE grouping.groupingID=community.communityID AND community.visibility='"+Visibility.PRIVATE+"' AND grouping.courseID IS NOT NULL  AND community.status='"+Community.ACTIVED+"'");
		/* Para cada grupo */
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				group = new Group( communityID ,this.database);
				/* Pega o curso ao qual ele esta subordinado */
				course = group.getCourse();
				/* Verifica se esse curso eh academico , se for entao pega o ID */
				
				if (course instanceof Discipline)
					academicCourseID = ((Discipline)course).getAcademicCourseID();
				else
				if (course instanceof ExtraCourse)
					academicCourseID = ((ExtraCourse)course).getAcademicCourseID();
				
				if (academicCourseID != null){
					/* Verifica se o grupo pertence ao mesmo curso academico do usuario */
					if (academicCourseID.equals(this.academicCourseID)){
						/* Se pertence entaum eh um grupo privado e do mesmo curso que o usuario */
						groups.add( group );
					}
				}
			}catch(CommunityException cnfe){
			}
		}
		
		return groups;
	}
	
	public Vector getIntraDepartmentGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		Community course;
		String academicCourseID = null;
		
		/* Seleciona todos os cursos com visibilidade INTRA_DEPARTMENT que sao subordinados a
		 algum curso extra curricular ou disciplina */
		this.result = this.database.query("SELECT grouping.groupingID FROM grouping,community WHERE "+
											  "grouping.groupingID=community.communityID AND "+
											  "community.visibility='"+Visibility.INTRA_DEPARTMENT+"' AND "+
											  "grouping.courseID IS NOT NULL AND community.status='"+Community.ACTIVED+"'");
		/* Para cada grupo */
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				group = new Group( communityID ,this.database);
				/* Pega o curso ao qual ele esta subordinado */
				course = group.getCourse();
				/* Verifica se esse grupo esta associado a um curso que eh academico , se for entao pega o ID */
				
				if (course instanceof Discipline)
					academicCourseID = ((Discipline)course).getAcademicCourseID();
				else
				if (course instanceof ExtraCourse)
					academicCourseID = ((ExtraCourse)course).getAcademicCourseID();

				
				if (academicCourseID != null){
					AcademicCourse academicCourse = new AcademicCourse(academicCourseID,this.database);
					
					
					
					/* Verifica se o grupo pertence ao mesmo departamento que o usuario */
					if (academicCourse.getDepartmentID().equals(this.departmentID)){
						/* Se pertence entaum eh um grupo Intra-Departamento e do mesmo departamento que o usuario */
						groups.add( group );
					}
				}
			}catch(GrawException cnfe){
			}
		}
		
		return groups;
	}
	
	public Vector getIntraInstituitionGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		Community course;
		String academicCourseID = null;
		
		/* Seleciona todos os cursos com visibilidade INTRA_INSTITUITION que sao subordinados
		 a algum curso.*/
		this.result = this.database.query("SELECT grouping.groupingID FROM grouping,community WHERE "+
											  "grouping.groupingID=community.communityID AND "+
											  "community.visibility='"+Visibility.INTRA_INSTITUTION+"' AND "+
											  "grouping.courseID IS NOT NULL  AND community.status='"+Community.ACTIVED+"'");
		/* Para cada grupo */
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				group = new Group(communityID,this.database);
				/* Pega o curso ao qual ele esta subordinado */
				course = group.getCourse();
				
				/* Verifica se esse grupo esta associado a um curso que eh academico , se for entao pega o ID */
				if (course instanceof Discipline)
					academicCourseID = ((Discipline)course).getAcademicCourseID();
				else
				if (course instanceof ExtraCourse)
					academicCourseID = ((ExtraCourse)course).getAcademicCourseID();
				
				if (academicCourseID != null){
					
					
					AcademicCourse academicCourse = new AcademicCourse(academicCourseID,this.database);
					
					/* Verifica se o grupo pertence ao mesmo instituto que o usuario */
					if (academicCourse.getDepartment().getInstituteID().equals(instituteID)){
						/* Se pertence entaum eh um grupo Intra-Departamento e do mesmo departamento que o usuario */
						groups.add( group );
					}
				}
			}catch(GrawException cnfe){
			}
		}
		return groups;
	}
	
	public Vector getInterInstituitionGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		Course course;
		String academicCourseID;
		
		/* Seleciona todos os cursos com visibilidade INTER_INSTITUITION que sao subordinados
		 a algum curso.*/
		this.result = this.database.query("SELECT grouping.groupingID FROM grouping,community WHERE "+
											  "grouping.groupingID=community.communityID AND "+
											  "community.visibility='"+Visibility.INTER_INSTITUTION+"' AND "+
											  "grouping.courseID IS NOT NULL AND community.status='"+Community.ACTIVED+"'");
		/* Para cada grupo */
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				group = new Group( communityID ,this.database);
				groups.add( group );
			}catch(GrawException ge){
				ge.printStackTrace();
			}
		}
		return groups;
	}
	
	/** Verifies if an academic user with userID exists */
	public static boolean exists(String academicUserID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM academicuser WHERE userID='"+academicUserID+"'");
		return (thisResult.size() == 1);
	}
	
	
//	public static boolean exists(String academicCourseID, String matriculation, String email, DatabaseLayer database){
//		Vector thisResult = database.query("SELECT * FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND ( matriculation='"+matriculation+"' OR email='"+email+"' )");
//		return (thisResult.size() != 0);
//	}
	
	
//	public static Vector existsEmail(String email, String academicCourseID, DatabaseLayer database){
//		return database.query("SELECT * FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND email='"+email+"'");
//	}
	
	/** Verifies if the matriculation is valid in an academicCourseID */
	public static boolean matriculationExists(String matriculation, String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT matriculation FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND matriculation='"+matriculation+"'");
		return (thisResult.size() == 1);
	}
	
	public static Vector getAcademicUserFields(String matriculation, String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT * FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND matriculation='"+matriculation+"'");
		return thisResult;
	}
	
	
	/** Verifies if the matriculation is valid in an academicCourseID */
	public static boolean matriculationExists(String matriculation, String login, String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT matriculation FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND matriculation='"+matriculation+"' AND login='"+login+"'");
		return (thisResult.size() == 1);
	}
	
	private static String getKindOfUserByMatriculation(String matriculation, DatabaseLayer database){
		Vector thisResult = database.query("SELECT kindofuserID FROM academicuser WHERE matriculation='"+matriculation+"'");
		return (String)((Hashtable)thisResult.firstElement()).get("kindofuserID");
	}
	/** Verifies if a the matriculation is of a professor */
	public static boolean isStudent(String matriculation, DatabaseLayer database){
		String kindOfUser = AcademicUser.getKindOfUserByMatriculation(matriculation,database);
		return (kindOfUser.equals(User.STUDENT));
	}
	
	/** Verifies if a the matriculation is of a professor */
	public static boolean isProfessor(String matriculation, DatabaseLayer database){
		try{
			String kindOfUser = AcademicUser.getKindOfUserByMatriculation(matriculation,database);
			return (kindOfUser.equals(User.PROFESSOR));
		}catch(Exception e){
			return false;
		}
	}
	
	/** Verifies if an academic user has already signed in on graW with the given matriculation */
	public static boolean alreadySigned(String matriculation, String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM academicuser WHERE matriculation='"+matriculation+"' AND academicCourseID='"+academicCourseID+"'");
		String userID = (String)((Hashtable)thisResult.firstElement()).get("userID");
		return (!userID.equals(""));
	}
	
	public static String getAcademicUserEmail(String matriculation, String academicCourseID, DatabaseLayer database){
		Vector result = database.query("SELECT userName FROM academicuser WHERE academicCourseID="+academicCourseID+" AND matriculation ='"+matriculation+"'");
		String userName = (String)((Hashtable)result.firstElement()).get("userName");
		String email = userName;
			
		String userID = "";
		try {
			AcademicCourse ac = new AcademicCourse(academicCourseID,database);
			email += "@"+ac.getDepartment().getWebDomain();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return email;
	}
	
	public static void main(String args[]){
		try{
			String userID = "1011805413930";
			DatabaseLayer database = new DatabaseLayer();
			/*AcademicUser user = new AcademicUser(userID,database);
			Vector disciplines = user.getIntraInstituitionGroups();
			//Discipline d ;
			Group d;
			System.out.println(disciplines.size());
			for (int i=0 ; i< disciplines.size() ; i++){
				d = (Group)disciplines.get(i);
				System.out.println(d.getTitle());
			 }
			 */
			System.out.println(AcademicUser.getAcademicUserEmail("1998G55D001T","1010426419860",database));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
