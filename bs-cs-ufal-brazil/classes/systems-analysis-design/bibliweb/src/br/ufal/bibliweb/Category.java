/**
 * Category.java
 *
 * @author Ricardo Oliveira
 */
// Criado em 15/04/2002

package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

public class Category{
	
	/** A identificação da categoria*/
	private String ID;
	/** A descrição  da categoria*/
	private String description;
	/** A identificação do curso acadêmico no qual o usuário faz parte.*/
	private String academicCourseID;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	private DatabaseLayer database;
	
	public Category(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM category WHERE category_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Categoria não encontrada com ID="+ID+" !");
		} else {
			Hashtable categoryState = (Hashtable)result.firstElement();
			this.ID 				= ID;
			this.description 		= (String)categoryState.get("description");
			this.academicCourseID 	= (String)categoryState.get("academic_course_id");
			this.database = database;
		}
	}
	
	/** Retorna o identificador da categoria. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descrição da categoria . */
	public String getDescription(){
		return this.description;
	}
	
	/** Retorna o curso acadêmico ao qual a categoria pertence. */
	public String getAcademicCourseID(){
		return this.academicCourseID;
	}
	
	/** Cria uma nova categoria. */
	public static String createNewCategory(String description, String academicCourseID, DatabaseLayer database){
		String newCategoryID = null;
		try{
			newCategoryID = Utility.getNewID(database);
			database.update("INSERT INTO category (category_id,academic_course_id,description) VALUES ('"+newCategoryID+"','"+academicCourseID+"','"+description+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newCategoryID;
	}
	
	public void printAll(){
		System.out.println("############  Informacoes da categoria   %%%%%%%%%%");
		System.out.println("ID:"+this.getID());
		System.out.println("Descricao: "+this.getDescription());
		System.out.println("Curso academico: "+this.getAcademicCourseID());
	}
	
	public AcademicCourse getAcademicCourse(){
		AcademicCourse ac = null;
		try{
			ac =  new AcademicCourse(this.academicCourseID,this.database);
		} catch (Exception e){
			e.printStackTrace();
		}
		return ac;
	}
	
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			/*AcademicCourse ac = new AcademicCourse("32",db);
			ac.printAll();
			String newCategory = Category.createNewCategory("Banco de Dados",ac.getID(),db);
			Category ca = new Category(newCategory,db);
			ca.printAll();
			 */
			Exemplar ex = AbstractExemplar.getRealExemplar("1019621139580",db);
			System.out.println(ex);
		} catch (Exception e){
			e.printStackTrace();
		}
	 }
}

