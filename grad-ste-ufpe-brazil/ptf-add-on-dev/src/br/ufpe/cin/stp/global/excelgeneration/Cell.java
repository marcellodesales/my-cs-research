/*
 * Created on 12/06/2004 21:31:09
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.excelgeneration;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * The representation of a cell used on the creation of a cell
 * in the XLS report. The cell may be filled with a given color, 
 * according to the type.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 12/06/2004 
 */
public class Cell {

	public static final byte CELL_TITLE_REPORT   = 0;
	public static final byte CELL_RESULT_HEADER  = 1;
	public static final byte CELL_RESULT_PASSED  = 2;
	public static final byte CELL_RESULT_FAILED  = 3;	
	public static final byte CELL_RESULT_NOTCONF = 4;
	public static final byte CELL_RESULT_UNKNOWN = 5;
	
	/**
	 * <code>coordinate</code> represents the x and y coordenates (column,row).
	 */
	private Coordinate coordinate;
	/**
	 * <code>value</code> is the value of the cell.
	 */
	private String value;
	/**
	 * <code>style</code> holds the style to format the cell.
	 * @see HSSFCellStyle. 
	 */
	private HSSFCellStyle style;
	/**
	 * <code>workBook</code> represents the whole book (excel)
	 */
	private HSSFWorkbook workBook;
	/**
	 * <code>type</code> defines the type of cell based on the constants.
	 */
	private byte type; 
	/**
	 * <code>width</code> defines the width of the cell.
	 */
	private short width;
	
	/**
	 * Creates a new Cell with x representing the column and y the row.
	 * Furthermore, the type must be specified using the constants provided
	 * by this class. As a result, the cell will be created with the 
	 * formated style for each type.
	 * @param x The number of the column. 
	 * @param y The number of the line. 
	 */
	public Cell (Coordinate coords, byte type, HSSFWorkbook wb){
		this.coordinate = coords;
		this.type = type;
		this.workBook = wb;
		this.processStyle();
		this.width = -1;
	 }	
	
	/**
	 * @return
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public HSSFCellStyle getStyle() {
		return this.style;
	}

	/**
	 * @return
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public String getValue() {
		return this.value;
	}
	
	public void setCoordinates(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	/**
	 * @return
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public short getX() {
		return this.coordinate.getX();
	}

	/**
	 * @return
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public short getY() {
		return this.coordinate.getY();
	}

	/**
	 * @param style
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public void setStyle(HSSFCellStyle style) {
		this.style = style;
	}

	/**
	 * @param string
	 * 12/06/2004 21:38:23
	 * Marcello
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Process the style of the title.
	 * 13/06/2004 00:21:36
	 * Marcello
	 */
	private void processStyle(){
		this.style = this.workBook.createCellStyle();
		
		switch (this.type){
			case (CELL_RESULT_HEADER):
			case (CELL_TITLE_REPORT):
				this.style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);			
				break;
				
			case (CELL_RESULT_PASSED):
				this.style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);		
				break;
				
			case (CELL_RESULT_FAILED):
				this.style.setFillForegroundColor(HSSFColor.CORAL.index);		
				break;
			
			case (CELL_RESULT_NOTCONF):
				this.style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);		
				break;
			case (CELL_RESULT_UNKNOWN):
				this.style.setFillForegroundColor(HSSFColor.GOLD.index);
				break;
		}
		
		this.style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		this.style.setFont(this.getNewFont());
	}
	
	/**
	 * @return A new font based to the type of the cell.
	 * 13/06/2004 00:21:14
	 * Marcello
	 */
	private HSSFFont getNewFont(){
		HSSFFont font = this.workBook.createFont();
		switch (this.type) {
			case CELL_TITLE_REPORT  :
			case CELL_RESULT_HEADER :	
				font.setFontName(HSSFFont.FONT_ARIAL);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				break;
				
			default :
				font.setFontName(HSSFFont.FONT_ARIAL);
				break;
		}
		return font;
	}
	/**
	 * @return
	 * 13/06/2004 02:24:12
	 * Marcello
	 */
	public byte getType() {
		return type;
	}

	/**
	 * @return
	 * 13/06/2004 02:24:12
	 * Marcello
	 */
	public short getWidth() {
		return width;
	}

	/**
	 * @param s
	 * 13/06/2004 02:24:12
	 * Marcello
	 */
	public void setWidth(short s) {
		width = s;
	}

}
