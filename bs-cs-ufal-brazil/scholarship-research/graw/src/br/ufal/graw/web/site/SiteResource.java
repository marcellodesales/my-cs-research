/**
 * SiteResource.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.site;

import br.ufal.graw.Institute;
import br.ufal.graw.Department;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.AbstractCourse;
import br.ufal.graw.Course;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Student;
import br.ufal.graw.Community;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.CommunityCategory;
import br.ufal.graw.CommunitySubcategory;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.AcademicCourseNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;

import java.util.Vector;
import java.util.Hashtable;

public class SiteResource{
	
	public static Institute[] getInstitutes(DatabaseLayer database){
		Institute[] institutes = {};
    	Vector result = database.query("SELECT instituteID FROM institute ORDER BY abbreviation");
		try {
			if (result.size() > 0){
				Institute[] insts = new Institute[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String instituteID  = (String)((Hashtable)result.get(i)).get("instituteID");
					insts[i] = new Institute(instituteID,database);
				}
				institutes = insts;
        	}
		} catch (InstituteNotFoundException infe){
				infe.printStackTrace();
		}
		return institutes;
	}
	
	public static Department[] getDepartments(String instituteID,DatabaseLayer database){
		Department[] departments = {};
		Vector result  = database.query("SELECT departmentID FROM department WHERE instituteID='"+instituteID+"' ORDER BY departmentCode");
		try {
			if (result.size() > 0){
				Department[] deptos = new Department[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String departmentID = (String)((Hashtable)result.get(i)).get("departmentID");
					deptos[i] = new Department(departmentID,database);
				}
				departments = deptos;
        	}
		} catch (DepartmentNotFoundException dnfe){
				dnfe.printStackTrace();
		}
		return departments;
	}
	
	public static Department[] getUFALDepartments(DatabaseLayer database){
		Vector result  = database.query("SELECT instituteID FROM institute WHERE abbreviation='UFAL'");
		String instituteID = (String)((Hashtable)result.firstElement()).get("instituteID");
		Department[] ufalDepts = SiteResource.getDepartments(instituteID,database);
		return ufalDepts;
	}
	
	public static AcademicCourse[] getAcademicCourses(String departmentID,DatabaseLayer database){
		AcademicCourse[] academicCourses = {};
		try {
			Vector result  = database.query("SELECT academicCourseID FROM academiccourse WHERE departmentID='"+departmentID+"'");
			if (result.size() > 0){
				AcademicCourse[] academics = new AcademicCourse[result.size()];
	    		for (int i=0; i < result.size(); i++){
          			String academicCourseID = (String)((Hashtable)result.get(i)).get("academicCourseID");
					academics[i] = new AcademicCourse(academicCourseID,database);
				}
				academicCourses = academics;
			}
		} catch (AcademicCourseNotFoundException dnfe){ // somente se nao for criado um ca
				dnfe.printStackTrace();
		}
		return academicCourses;
	}
	
	public static CommunityCategory[] getCommunityCategories(DatabaseLayer database){
		CommunityCategory[] academicCategories = {};
		try {
			Vector result  = database.query("SELECT categoryID FROM communitycategory ORDER BY description");
			if (result.size() > 0){
				CommunityCategory[] categories = new CommunityCategory[result.size()];
	    		for (int i=0; i < result.size(); i++){
					String categoryID = (String)((Hashtable)result.get(i)).get("categoryID");
					categories[i] = new CommunityCategory(categoryID,database);
				}
				academicCategories = categories;
        	}
		} catch (ResourceNotFoundException cnfe){
				cnfe.printStackTrace();
		}
		return academicCategories;
	}
	
	public static CommunitySubcategory[] getSubcategories(String categoryID,DatabaseLayer database){
		CommunitySubcategory[] subcategories = {};
    	Vector result  = database.query("SELECT subcategoryID FROM communitysubcategory WHERE categoryID='"+categoryID+"' ORDER BY description");
		try {
			if (result.size() > 0){
				CommunitySubcategory[] sub = new CommunitySubcategory[result.size()];
	    		for (int i=0; i < result.size(); i++){
					String subcategoryID = (String)((Hashtable)result.get(i)).get("subcategoryID");
					sub[i] = new CommunitySubcategory(subcategoryID,database);
				}
				subcategories = sub;
        	}
		} catch (ResourceNotFoundException cnfe){
				cnfe.printStackTrace();
		}
		
		return subcategories;
	}
		
	/*
	public static Vector getCourses(String departmentID,DatabaseLayer database){
		Vector courses = new Vector();
    	Vector result  = database.query("SELECT courseID FROM course WHERE departmentID='"+departmentID+"' ORDER BY name");
		try {
			if (result.size() > 0){
	    		for (int i=0; i < result.size(); i++){
					String courseID = (String)((Hashtable)result.get(i)).get("courseID");
					Course course   = AbstractCourse.getRealCourse(courseID,database);
		  			courses.add(course);
	      		}
        	}
		} catch (CourseNotFoundException cnfe){
				cnfe.printStackTrace();
		}
		return courses;
	}
	 */
	public static void main(String[] args){
		
		CommunityCategory[] cc = SiteResource.getCommunityCategories(new DatabaseLayer());
		for (int i=0; i < cc.length; i++){
			cc[i].printAll();
		}
		
		/*AcademicCourse[] accs = SiteResource.getAcademicCourses("1010362252870",new DatabaseLayer());
		for (int i=0; i < accs.length; i++){
			AcademicCourse acc = (AcademicCourse)accs[i];
			acc.printAll();
		 }*/
		
		/*		Department[] depts = SiteResource.getUFALDepartments(new DatabaseLayer());
		for (int i=0; i < depts.length; i++){
			Department depto = (Department)depts[i];
			depto.printData();
		}
		
		Institute[] institutes = SiteResource.getInstitutes(new DatabaseLayer());
		for (int i=0; i < institutes.length; i++){
			System.out.println(institutes[i].getAbbreviation());
		 }*/
	}
}
