/*
 * @created 20/07/2004 19:33:42
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;

import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 20/07/2004 19:33:42
 */
public class TestCaseSuitTableModel extends AbstractTableModel {
    
	/**
	 * <code>TestCaseSuite</code> is the main source of values.
	 */
    private TestCaseSuite testCaseSuite;    
    public final static String TESTCASE_ID = "TestCaseID";
    public final static String STARTED = "STARTED";
    public final static String FINISHED = "FINISHED";
    public final static String ELLAPSED = "ELLAPSED";
    public final static String RESULT = "RESULT";
     
     // Column titles
     String[] columnName = 
         new String[] { "Test Case ID", "Started", "Finished", "Elapsed", "Result" };
    
     // Column types
     Class[] columnClass =
         new Class[] { String.class, String.class, String.class, String.class, String.class };

 	/**
 	 * <code>values</code> is the values of the lines. The first colums is the row
 	 * and the second is the column.
 	 */
 	public Object[][] rows;
 	
 	public TestCaseSuitTableModel(TestCaseSuite tcs) {
	    this.testCaseSuite = tcs;
	    this.rows = this.getFormatedLines(tcs);
	}
	
	/**
	 * @param tcs is the suite with the results.
	 * @return [row][column] the values of the jtable (TEST_CASE_ID, STARTED, FINISHED, TOTAL, RESULT)
	 * @created 19/07/2004 21:47:18
	 */
	private Object[][] getFormatedLines(TestCaseSuite tcs){
	    Iterator it = tcs.getTestCaseIterator();
	    Object[][] values = new Object[tcs.size()][5];
	    int line = 0;
	    while (it.hasNext()) {
            TestCaseInstruction tci = (TestCaseInstruction) it.next();
            values[line][0] = tci.getTestCaseID();
            values[line][1] = tci.getStartInstruction().getFormatedTime();
            values[line][2] = tci.getEndInstruction().getFormatedTime();
            values[line][3] = tci.getElapsedTime();
            values[line][4] = tci.getResult();                
            line++;
        }
	    return values;
	}

     public TestCaseSuitTableModel(Object[][] orders) {
       this.rows = orders;
     }

     public int getColumnCount() {
       return columnName.length;
     }

     public boolean testPassed(int row) {
       return rows[row][4].equals(TestCaseSuite.RESULT_PASSED);
     }

     public boolean testFailed(int row) {
       return rows[row][4].equals(TestCaseSuite.RESULT_FAILED);
     }

     public boolean testNotConfigured(int row) {
       return rows[row][4].equals(TestCaseSuite.RESULT_NOTCONFIGURED);
     }

     public boolean testUnknown(int row) {
         return rows[row][4].equals(TestCaseSuite.RESULT_UNKNOWN);
     }
     /*
     public void updateOrderPrice(int row, Double newPrice) {

       Object[] order = rows[row];
       order[2] = newPrice;
       super.fireTableRowsUpdated(row, row);
     }

     public void updateOrderStatus(int row, String status) {

       Object[] order = rows[row];
       order[4] = status;
       super.fireTableRowsUpdated(row, row);

     }
*/
     public Object getValueAt(int row, int col) {
       return rows[row][col];
     }

     public int getRowCount() {
       return rows.length;
     }

     public String getColumnName(int col) {
       return columnName[col];
     }

     public Class getColumnClass(int col) {
       return columnClass[col];
     } 
     
 	/**
 	 * @param rowIndex
 	 * @return the TestCaseInstruction on the given rowIndex.
 	 * @created 20/07/2004 09:42:52
 	 */
 	public TestCaseInstruction getLineValueAt(int rowIndex) {
 	    return (TestCaseInstruction)this.testCaseSuite.getTestCases().get(rowIndex);
 	}     
}
