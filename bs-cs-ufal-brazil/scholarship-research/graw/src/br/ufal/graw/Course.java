package br.ufal.graw;

import java.util.Vector;
import java.util.Enumeration;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.GroupNotFoundException;
import br.ufal.graw.exception.GrawException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.InstituteNotFoundException;

/* Operações realizadas por todos os Cursos */
public interface Course extends Community{
	
	public String getProgram();
	
	public String getHours();
	
	public String getBibliography();
	
	public void setProgram(String newProgram)
	throws PersistentInformationException;
	
	public void setHours(String newHours)
	throws PersistentInformationException;
	
	public void setBibliography(String newBibliografy)
	throws PersistentInformationException;
	
	
	
	public AcademicCourse getAcademicCourse() throws GrawException;
	
	public Department getDepartment() throws DepartmentNotFoundException;
	
	public Institute getInstitute() throws InstituteNotFoundException;
	
	/*
    public Vector getMonitors();
	
	/** Gets the Students that are interested in this Course. */
	/*    public Vector getStudents();
	
	public int getQuantStudent();
	public int getQuantDocuments();
	public int getQuantLinks();
	public int getQuantMessages();
	 */
	
	/** Gets the Professors that teach the Course. */
	/*
    public Vector getProfessors();
	
	public Enumeration getMembers() throws UserNotFoundException;
	
	/**
	@return All groups of this Course.
	 */
	
	public Vector getGroups() throws GroupNotFoundException;
	
	/*
	public Enumeration getVisibleGroups() throws GroupNotFoundException;
	 */
}
