package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;

import br.ufal.bibliweb.exception.AcademicCourseNotFoundException;
import br.ufal.bibliweb.exception.CPFIncorrectFormException;
import br.ufal.bibliweb.exception.ResourceNotFoundException;
import br.ufal.bibliweb.exception.UserNotFoundException;
import br.ufal.bibliweb.exception.UserAlreadyExistsException;
import br.ufal.bibliweb.exception.ExemplarNotFoundException;

/**
 * ComponentSet.java
 *
 * @author Marcello de Sales
 */
public class ComponentSet{
	
	/** Retorna todos os grupos de usuários existentes no ambiente. */
	public static Group[] getGroups(DatabaseLayer database){
		Group[] groups = {};
    	Vector result = database.query("SELECT group_id FROM grouping ORDER BY description");
		try {
			if (result.size() > 0){
				Group[] grps = new Group[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String groupID  = (String)((Hashtable)result.get(i)).get("group_id");
					grps[i] = new Group(groupID,database);
				}
				groups = grps;
        	}
		} catch (ResourceNotFoundException gnfe){
				gnfe.printStackTrace();
		}
		return groups;
	}
	
	/** Retorna todos os cursos acadêmicos existentes no ambiente. */
	public static AcademicCourse[] getAcademicCourses(DatabaseLayer database){
		AcademicCourse[] groups = {};
    	Vector result = database.query("SELECT academic_course_id FROM academic_course ORDER BY description");
		try {
			if (result.size() > 0){
				AcademicCourse[] acCrs = new AcademicCourse[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String acID  = (String)((Hashtable)result.get(i)).get("academic_course_id");
					acCrs[i] = new AcademicCourse(acID,database);
				}
				groups = acCrs;
        	}
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return groups;
	}
	
	/** Retorna todas categorias de exemplares no ambiente. */
	public static Category[] getCategories(String academicCourseID, DatabaseLayer database){
		Category[] categories = {};
    	Vector result = database.query("SELECT category_id FROM category WHERE academic_course_id = "+academicCourseID+" ORDER BY description");
		try {
			if (result.size() > 0){
				Category[] categs = new Category[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String categoryID  = (String)((Hashtable)result.get(i)).get("category_id");
					categs[i] = new Category(categoryID,database);
				}
				categories = categs;
        	}
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return categories;
	}
	
	/** Retorna todas categorias de exemplares no ambiente. */
	public static PhysicalPlace[] getPhysicalPlaces(DatabaseLayer database){
		PhysicalPlace[] physicalPlaces = {};
    	Vector result = database.query("SELECT physical_place_id FROM physical_place ORDER BY description");
		try {
			if (result.size() > 0){
				PhysicalPlace[] physicalPs = new PhysicalPlace[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String categoryID  = (String)((Hashtable)result.get(i)).get("physical_place_id");
					physicalPs[i] = new PhysicalPlace(categoryID,database);
				}
				physicalPlaces = physicalPs;
        	}
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return physicalPlaces;
	}
	
	/** Retorna todos os idiomas dos exemplares. */
	public static Language[] getLanguages(DatabaseLayer database){
		Language[] languages = {};
    	Vector result = database.query("SELECT language_id FROM language ORDER BY description");
		try {
			if (result.size() > 0){
				Language[] categs = new Language[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String languageID = (String)((Hashtable)result.get(i)).get("language_id");
					categs[i] = new Language(languageID,database);
				}
				languages = categs;
        	}
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return languages;
	}
	
	/** Retorna todos os tipos de exemplare disponíveis. */
	public static ExemplarType[] getExemplarTypes(DatabaseLayer database){
		ExemplarType[] exemplarTypes = {};
    	Vector result = database.query("SELECT exemplar_type_id FROM exemplar_type ORDER BY description");
		try {
			if (result.size() > 0){
				ExemplarType[] extys = new ExemplarType[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String extysID = (String)((Hashtable)result.get(i)).get("exemplar_type_id");
					extys[i] = new ExemplarType(extysID,database);
				}
				exemplarTypes = extys;
        	}
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return exemplarTypes;
	}
	
	/** Retorna todos os usuários de um grupo. */
	public static User[] getAllUsers(String groupID, DatabaseLayer database){
		User[] languages = {};
    	Vector result = database.query("SELECT user_id FROM \"user\" WHERE group_id = '"+groupID+"' ORDER BY name");
		try {
			if (result.size() > 0){
				User[] users = new User[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String userID = (String)((Hashtable)result.get(i)).get("user_id");
					users[i] = AbstractUser.getRealUser(userID,database);
				}
				languages = users;
        	}
		} catch (UserNotFoundException rnfe){
				rnfe.printStackTrace();
		} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return languages;
	}
	
	/** Retorna todos os livros de um determinado curso acadêmico. */
	public static Book[] getBooks(String academicCourseID, DatabaseLayer database){
		Book[] books = {};
		Vector result = database.query("SELECT book.exemplar_id "+
									   "FROM academic_course, category, book "+
									   "WHERE ((book.category_id =category.category_id)"+
									   "AND(category.academic_course_id =academic_course.academic_course_id)"+
									   "AND(academic_course.academic_course_id = "+academicCourseID+")) ORDER BY title");
		try {
			if (result.size() > 0){
				Book[] bookss = new Book[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String booksID = (String)((Hashtable)result.get(i)).get("exemplar_id");
					bookss[i] = new Book(booksID,database);
				}
				books = bookss;
        	}
		} catch (ExemplarNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return books;
	}
	
	/** Retorna todos os livros de uma determinada categoria. */
	public static Book[] getBooks(DatabaseLayer database,String categoryID){
		Book[] books = {};
		Vector result = database.query("SELECT exemplar_id FROM book WHERE category_id = "+categoryID+" ORDER BY title");
		try {
			if (result.size() > 0){
				Book[] bookss = new Book[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String booksID = (String)((Hashtable)result.get(i)).get("exemplar_id");
					bookss[i] = new Book(booksID,database);
				}
				books = bookss;
        	}
		} catch (ExemplarNotFoundException rnfe){
				rnfe.printStackTrace();
		}
		return books;
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		/*Group[] groups = ComponentSet.getGroups(db);
		for (int i=0; i < groups.length; i++){
			groups[i].printAll();
		 }*/
		
		/*AcademicCourse[] acs = ComponentSet.getAcademicCourses(db);
		for (int i=0; i < acs.length; i++){
			acs[i].printAll();
		 }*/
		
		/*Language[] langs = ComponentSet.getLanguages(db);
		for (int i=0; i < langs.length; i++){
			langs[i].printAll();
		 }*/
		
		/*	ExemplarType[] exemplarTypes = ComponentSet.getExemplarTypes(db);
		for (int i=0; i < exemplarTypes.length; i++){
			exemplarTypes[i].printAll();
		 }*/

		
		try{
//			AcademicCourse acc = new AcademicCourse("32",db);
			//		Book[] books = ComponentSet.getBooks(acc.getID(),db);
			/*Category c = new Category("43",db);
			c.printAll();
			Book[] books = ComponentSet.getBooks(db,c.getID());
			for (int i=0; i < books.length; i++){
				books[i].printObject();
			 }*/
			
			/*AcademicCourse a = new AcademicCourse("32",db);
			Category[] cats = ComponentSet.getCategories(a.getID(),db);
			for (int i=0; i < cats.length; i++){
				cats[i].printAll();
			}
			
			 */
			PhysicalPlace[] physicalPlaces = ComponentSet.getPhysicalPlaces(db);
			for (int i=0; i < physicalPlaces.length; i++){
				physicalPlaces[i].printAll();
			 }
						
			/*Group a = new Group("36",db);
			User[] users = ComponentSet.getAllUsers(a.getID(),db);
			for (int i=0; i < users .length; i++){
				System.out.println(users[i].getName());
			 }*/
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

