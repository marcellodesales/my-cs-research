/*
 * Created on 13/06/2004 12:43:21
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.excelgeneration;

/**
 * Coordinate encapsulate the need to cast the int value to 
 * short used on the cell class.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 13/06/2004 
 */
public class Coordinate {

	/**
	 * <code>x</code> is the column count.
	 */
	private short x;
	/**
	 * <code>y</code> is the row count.
	 */
	private short y;
	
	/**
	 * Creates a new Coordinate. Used to avoid casting int to float 
	 * values to the XLS creator.
	 * @param x is the column counter.
	 * @param y is the row counter.
	 * 08/06/2004 03:35:47
	 */
	public Coordinate(int x, int y){
		this.x = (short)x;
		this.y = (short)y;
	}
	/**
	 * @return the column counter.
	 * 13/06/2004 12:44:43
	 */
	public short getX() {
		return x;
	}

	/**
	 * @return the row counter.
	 * 13/06/2004 12:44:43
	 */
	public short getY() {
		return y;
	}
}
