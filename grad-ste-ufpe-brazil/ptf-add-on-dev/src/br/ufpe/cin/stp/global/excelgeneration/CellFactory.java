/*
 * Created on 13/06/2004 01:22:06
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.excelgeneration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * The CellFactory is responsible to create Cell objects according
 * to a given type of cell.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 13/06/2004 
 */
public class CellFactory {

	/**
	 * <code>singleton</code> is the single instance of this class.
	 */
	private static CellFactory singleton;
	
	/**
	 * Creates the single instance.
	 * 08/06/2004 03:33:20
	 */
	private CellFactory(){
	}
	
	/**
	 * (08/06/2004 03:33:37)
	 * @return Gets the single instance of this factory.
	 */
	public static CellFactory getInstance(){
		if (singleton == null)
			singleton = new CellFactory();
		return singleton;	
	}

	/**
	 * 13/06/2004 01:38:31
	 * @param cellType The type of a cell. This value is defined on constants
	 * on the Cell class.
	 * @param workBook The workbook to be used.
	 * @return an abstract Cell based on the type.
	 */
	public Cell createCell(byte cellType, HSSFWorkbook workBook){
		return new Cell(new Coordinate(0,0),cellType,workBook);
	}
}
