/*
 * @created 05/07/2004 11:14:13
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.swingcomponent.table;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;

/**
 * The ConfigFileModel represents the properties file mapping to a JTable.
 * It contains three columns: Commented, key and value.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 12/09/2004 09:31:26
 */
public class ConfigPropertiesTableModel extends AbstractTableModel {

    /**
     * <code>KEY</code> is the key colums
     */
    public final static int KEY = 0;
	/**
	 * <code>VALUE</code> is the value column.
	 */
	public final static int VALUE = 1;
   /**
	* <code>columnNames</code> is the array of the columns
 	*/
	public static String[] columnNames = {"KEY", "VALUE"};
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
	public ConfigPropertiesTableModel(Hashtable lines){
	    this.values = this.getFormattedLines(lines);
	}

	/**
	 * @param lines is the lines of a properties file with a token '=' to separate
	 * the key and value. The line can optionally be commented with the token '#'.
	 * @return an array to populate the model.
	 * @created 06/07/2004 09:49:27
	 */
	private Object[][] getFormattedLines(Hashtable lines){
	    StringTokenizer tokenizer;
	    boolean commented;
	    int n = lines.size();

	    Object[][] values = new Object[n][2];
	    String line;
	    
	    Enumeration enum = lines.keys();
	    int i = 0;
	    while (enum.hasMoreElements()) {
            String key = (String) enum.nextElement();
            values[i][0] = key;
            values[i][1] = (String)lines.get(key);
            i++;
        }
	    return values;
	}

	/**
	 * @return Gets the properties lines each the following semantic:
	 * #key=value for commented lines and key=value for uncommented ones.
	 * @created 06/07/2004 16:15:43
	 */
	public Hashtable getPropertiesLines(){
	    int size = this.values.length;
	    
	    Hashtable theValues = new Hashtable();
	    
	    for (int i = 0; i < size; i++) {
	        String key = (String)this.values[i][0];
	        String value = (String)(this.values[i][1]);
	        theValues.put(key,value);
	    }
	    return theValues;
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
	    return dataType;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 * @created 06/07/2004 16:22:48
	 */
	public boolean isCellEditable(int row, int column) {
	    return (column == VALUE);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 * @created 06/07/2004 16:22:46
	 */
	public void setValueAt(Object value, int row, int column) {
	    this.values[row][column] = value;
       }
}

