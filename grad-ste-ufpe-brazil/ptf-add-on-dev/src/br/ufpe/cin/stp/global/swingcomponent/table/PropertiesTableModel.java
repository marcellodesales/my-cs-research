/*
 * @created 05/07/2004 11:14:13
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.swingcomponent.table;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * The ConfigFileModel represents the properties file mapping to a JTable.
 * It contains three columns: Commented, key and value.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 06/07/2004 09:31:26
 */
public class PropertiesTableModel extends AbstractTableModel {

    /**
     * <code>COMMENTED</code> is the column to indicate the comment of a line with #
     */
    public final static int COMMENTED = 0;
    /**
     * <code>KEY</code> is the key colums
     */
    public final static int KEY = 1;
	/**
	 * <code>VALUE</code> is the value column.
	 */
	public final static int VALUE = 2;
   /**
	* <code>columnNames</code> is the array of the columns
 	*/
	public static String[] columnNames = {"#", "KEY", "VALUE"};
	/**
	 * <code>values</code> is the values of the lines.
	 */
	public Object[][] values;

	/**
	 * Constructs a new PropertiesTableModel with an array of lines of
	 * (Boolean,String,String) where the boolean value indicates whether
	 * the line is commented or not.
	 * @param lines is the lines of a property files where each line can be
	 * #key = value | key = value
	 * @created 06/07/2004 16:13:02
	 */
	public PropertiesTableModel(String[] lines){
	    this.values = this.getFormattedLines(lines);
	}

        public PropertiesTableModel(List lines){
          String[] linesTemp = new String[lines.size()];
          for (int i=0; i<linesTemp.length; i++)
              linesTemp[i] = (String)lines.get(i);
          this.values = this.getFormattedLines(linesTemp);
        }


	/**
	 * @param lines is the lines of a properties file with a token '=' to separate
	 * the key and value. The line can optionally be commented with the token '#'.
	 * @return an array to populate the model.
	 * @created 06/07/2004 09:49:27
	 */
	private Object[][] getFormattedLines(String[] lines){
	    StringTokenizer tokenizer;
	    boolean commented;
	    int n = lines.length;

	    List validLines = new Vector();

	    for (int i = 0; i < n; i++) {
            if (lines[i].indexOf("=") == -1) continue;
            validLines.add(lines[i]);
        }

	    n = validLines.size();
	    Object[][] values = new Object[n][3];
	    String line;
	    for (int i = 0; i < n; i++) {
	        line = (String)validLines.get(i);
            tokenizer = new StringTokenizer(line,"=");
            values[i][0] = new Boolean(line.charAt(0) == '#');
            values[i][1] = tokenizer.nextToken().substring(line.indexOf("#")+1).trim();
            values[i][2] = tokenizer.nextToken().trim();
        }
	    return values;
	}

	/**
	 * @return Gets the properties lines each the following semantic:
	 * #key=value for commented lines and key=value for uncommented ones.
	 * @created 06/07/2004 16:15:43
	 */
	public String[] getPropertiesLines(){
	    int size = this.values.length;
	    String[] lines = new String[size];
	    for (int i = 0; i < size; i++) {
	        lines[i] = ((Boolean)this.values[i][0]).booleanValue() ? "#" : "";
            lines[i] = lines[i] + this.values[i][1] + "=";
            lines[i] = lines[i] + this.values[i][2];
	    }
	    return lines;
	}

	/**
	 * Adds a new value to the model
	 * @param key is a given key of the property file.
	 * @param value is the value mapped to the key.
	 * @created 06/07/2004 16:20:25
	 */
	public void addValue(String key, String value){
	    int newSize = this.values.length + 1;
	    Object[][] newValues = new Object[newSize][this.values[0].length];
	    for (int i = 0; i < newValues.length-1; i++)
	        for (int j = 0; j < newValues[0].length; j++)
	            newValues[i][j] = this.values[i][j];

	    newValues[newSize-1][0] = new Boolean(false);
	    newValues[newSize-1][1] = key;
	    newValues[newSize-1][2] = value;
	    this.values = newValues;
	}

	/**
	 * Removes an element of the model.
	 * @param linePosition is the position of the line.
	 * @created 06/07/2004 16:29:54
	 */
	public void removeValue(int linePosition){
	    if (linePosition <0) throw new IllegalArgumentException("Line position must be greater than 0");
	    String lines[] = this.getPropertiesLines();
	    String linesN[] = new String[lines.length-1];
	    Vector linesV = new Vector(lines.length-1);
	    for (int i = 0; i < lines.length; i++) {
	        if (i == linePosition) continue;
	        linesV.add(lines[i]);
        }
	    for (int i = 0; i < linesV.size(); i++) {
	        linesN[i] = (String)linesV.get(i);
        }
	    this.values = this.getFormattedLines(linesN);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 * @created 06/07/2004 16:21:21
	 */
	public int getRowCount() {
	    return values.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 * @created 06/07/2004 16:21:23
	 */
	public int getColumnCount() {
	    return values[0].length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 * @created 06/07/2004 16:21:31
	 */
	public Object getValueAt(int row, int column) {
	    return values[row][column];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 * @created 06/07/2004 16:21:44
	 */
	public String getColumnName(int column) {
	    return columnNames[column];
	}

	  /* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 * @created 05/07/2004 11:39:19
	 */
	public Class getColumnClass(int column) {
	    Class dataType = super.getColumnClass(column);
	    if (column == COMMENTED)
	        dataType = Boolean.class;
	    return dataType;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 * @created 06/07/2004 16:22:48
	 */
	public boolean isCellEditable(int row, int column) {
	    if (column == COMMENTED)
	        return true;
	    else return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 * @created 06/07/2004 16:22:46
	 */
	public void setValueAt(Object value, int row, int column) {
	    this.values[row][column] = value;
       }
}

