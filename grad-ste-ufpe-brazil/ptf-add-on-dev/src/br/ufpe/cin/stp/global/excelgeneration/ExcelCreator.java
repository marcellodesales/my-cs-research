/*
 * Created on 12/06/2004 19:32:18
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.global.excelgeneration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * ExcelCreator is the main component of this package. It's responsible
 * for creating the XLS file based on a log resport. Save the file,
 * create cells on it and other operations is available to manage an
 * xls file.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 12/06/2004 
 */
public class ExcelCreator {

	/**
	 * <code>wb</code> is the work book that represents the whole XLS file
	 */
	private HSSFWorkbook wb;
	/**
	 * <code>fileOut</code> is the output stream of the file to be used.
	 */
	private FileOutputStream fileOut;
	/**
	 * <code>fileNamePath</code> is the relative .xls file name path 
	 * to be used to create the new file.
	 */
	private String fileNamePath;
	
	/**
	 * Creates an XLS file based on a log file and a given workbook.
	 * @param fileNamePath is the relative path to where a .xls file 
	 * will be created.
	 * @param workBook is the default workbook to be used to create a new
	 * xls file.
	 * @throws FileNotFoundException.
	 */
	public ExcelCreator(String fileNamePath, HSSFWorkbook workBook) throws FileNotFoundException{
		this.fileNamePath = fileNamePath;
		this.wb = workBook;
	}	

	/**
	 * 12/06/2004 21:59:26
	 * Saves the file on the fileName, that also describes the path of 
	 * the file.
	 * @throws FileNotFoundException if the file could not be saved.
	 * @throws IOException if an error occur while saving the file.
	 */
	public void saveFile() throws FileNotFoundException, IOException{
		this.fileOut = new FileOutputStream(this.fileNamePath);
		this.wb.write(fileOut);
		this.fileOut.close();
	}
	
	/**
	 * @param sheetTitle The title of the sheet
	 * @return A new sheet of the excel file
	 * 12/06/2004 21:59:30
	 */
	public HSSFSheet createSheet(String sheetTitle){
		return this.wb.createSheet(sheetTitle);
	}
	
	/**
	 * @param sheet A given sheet 
	 * @param cell The representation of a cell.
	 * @return A formated cell.
	 * 12/06/2004 21:59:59
	 */
	public HSSFCell createCell(HSSFSheet sheet, Cell cell){
		// Create a cell and put a value in it.
		HSSFCell excell = sheet.createRow(cell.getY()).createCell(cell.getX());
		excell.setCellValue(cell.getValue());	
		
		if (cell.getStyle() != null){
			excell.setCellStyle(cell.getStyle());			
		}		
		return excell;
	}
	
	public static void main(String[] args) {
		try {
			HSSFWorkbook ws = new HSSFWorkbook();
			ExcelCreator excel = new ExcelCreator("C:/ptfTests.xls",ws);
			HSSFSheet sheet = excel.createSheet("PTF Add-on Tests");
			
			Cell a = new Cell(new Coordinate(0,2),Cell.CELL_RESULT_HEADER,excel.wb);
			a.setValue("New Value");
			
			excel.createCell(sheet,a);
			
			excel.saveFile();
			
			System.out.println("Saved!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
