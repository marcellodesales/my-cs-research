/*
 * Created on 10/06/2004 13:46:29
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.comparator;

import java.util.Comparator;

/**
 * Now that we can sort on generic JavaBean properties, it is desirable 
 * to join the sorts together. For example, in a large table of employees,
 * sorting the employees by both last name and then by first name may be 
 * desired.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 10/06/2004 
 */
public class CompositeComparator implements Comparator {

	private Comparator major;
	private Comparator minor;
	
	/**
	 * We refer to the two Comparators as the major and minor comparators.
	 * The major comparator has priority over the minor comparator. If the 
	 * major comparator returns < 0 or > 0, then that result is passed 
	 * back. The minor comparator's result is used only if the major 
	 * comparator returns 0.
	 * @param major Used to compare first
	 * @param minor Used to compare if the major is iguals.
	 */
	public CompositeComparator(Comparator major, Comparator minor) {
		this.major = major;
	   	this.minor = minor;
	}
	
	public int compare(Object o1, Object o2) {
	   	int result = major.compare(o1,o2);
	   	if (result != 0) 
	   		return result;
	   	else 
	   		return minor.compare(o1,o2);	   	
	}
}
