/*
 * @created 20/07/2004 21:17:54
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 20/07/2004 21:17:54
 */
public class TableSortDecorator implements TableModel, TableModelListener {

    private TableModel realModel; // We're decorating this model
    private int indexes[];    
    
    public TableSortDecorator(TableModel model) {
        this.realModel = model;   
        realModel.addTableModelListener(this);
        allocate();
     }

     // The following nine methods are defined by the TableModel
     // interface; all of those methods forward to the real model.
     public void addTableModelListener(TableModelListener l) {
        realModel.addTableModelListener(l);
     }
     public Class getColumnClass(int columnIndex) {
        return realModel.getColumnClass(columnIndex);
     }
     public int getColumnCount() {
        return realModel.getColumnCount();   
     }
     public String getColumnName(int columnIndex) {
        return realModel.getColumnName(columnIndex);
     }
     public int getRowCount() {
        return realModel.getRowCount();   
     }
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        return realModel.isCellEditable(rowIndex, columnIndex);
     }
     public void removeTableModelListener(TableModelListener l) {
        realModel.removeTableModelListener(l);
     }
     public Object getValueAt(int row, int column) {
        return getRealModel().getValueAt(indexes[row], column);
     }
     public void setValueAt(Object aValue, int row, int column) {
        getRealModel().setValueAt(aValue, indexes[row], column);
     }

     // The getRealModel method is used by subclasses to
     // access the real model.
     protected TableModel getRealModel() {
        return realModel;
     }

     // tableChanged is defined in TableModelListener, which
     // is implemented by TableSortDecorator.
     public void tableChanged(TableModelEvent e) {
        allocate();   
     }

     // The following methods perform the bubble sort ...

     public void sort(int column) {
        int rowCount = getRowCount();

        for(int i=0; i < rowCount; i++) {
           for(int j = i+1; j < rowCount; j++) {
              if(compare(indexes[i], indexes[j], column) < 0) {
                 swap(i,j);
              }
           }
        }
     }
     private void swap(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
     }
     private int compare(int i, int j, int column) {
        TableModel realModel = getRealModel();
        Object io = realModel.getValueAt(i,column);
        Object jo = realModel.getValueAt(j,column);

        int c = jo.toString().compareTo(io.toString());
        return (c < 0) ? -1 : ((c > 0) ? 1 : 0);
     }
     private void allocate() {
        indexes = new int[getRowCount()];
        for(int i=0; i < indexes.length; ++i) {
           indexes[i] = i;         
        }
     }
}
