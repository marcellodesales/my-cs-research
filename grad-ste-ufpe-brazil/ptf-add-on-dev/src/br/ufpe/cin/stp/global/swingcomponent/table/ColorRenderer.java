/*
 * @created 20/07/2004 19:22:27
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 20/07/2004 19:22:27
 */
public class ColorRenderer implements TableCellRenderer {

    protected TableCellRenderer delegate;
    protected ColorProvider provider;
    
    public ColorRenderer(TableCellRenderer anotherRenderer,  ColorProvider provider) {
        this.provider = provider;
        this.delegate = anotherRenderer;
    }    
    
    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     * @created 20/07/2004 19:22:27
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub

        Color bgrd = null;
        Color fgrd = null;

        if (isSelected) {
            // Preserve selection colors
            fgrd = table.getSelectionForeground();
            bgrd = table.getSelectionBackground();

        } else {
            // Set our colours
            fgrd = provider.getForeground(row,column);
            bgrd = provider.getBackground(row,column);
        }
        Component c = delegate.getTableCellRendererComponent(table, value, isSelected,                 hasFocus, row, column);
        // Set the component colours
        c.setBackground(bgrd);
        c.setForeground(fgrd);

        return c;        
    }
}
