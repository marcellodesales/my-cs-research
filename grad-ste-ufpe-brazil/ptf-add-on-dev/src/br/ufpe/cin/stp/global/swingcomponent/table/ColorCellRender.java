/*
 * @created 20/07/2004 19:41:50
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 20/07/2004 19:41:50
 */
public class ColorCellRender implements ColorProvider {

    TestCaseSuitTableModel model;
    
    final static Color passedColor = new Color(204, 255, 204);
    final static Color failedColor = new Color(255, 153, 153);
    final static Color unknownColor = new Color(255, 204, 0);  
    final static Color notConfigColor = new Color(238,238,238);
    
    private JTable table;
    
    /**
     * @created 21/07/2004 17:12:19
     * @return Returns the model.
     */
    public TestCaseSuitTableModel getModel() {
        return this.model;
    }
    public ColorCellRender(TableModel model){
        this.model = (TestCaseSuitTableModel)model;
        this.table = new JTable(model);
        registerRendererForClass(table,String.class);
        //registerRendererForClass(table,Number.class);            
    }
   
    public JTable getColoredTable(){
        return this.table;
    }
    
    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.global.swingcomponent.table.ColorProvider#getForeground(int, int)
     * @created 20/07/2004 19:41:50
     */
    public Color getForeground(int row, int column) {
       /* if (column == 2)
            return Color.RED;
          else*/
          return Color.BLACK;
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.global.swingcomponent.table.ColorProvider#getBackground(int, int)
     * @created 20/07/2004 19:41:50
     */
    public Color getBackground(int row, int column) {
        if (model.testPassed(row))
            return passedColor;
        else
        if (model.testFailed(row))
            return failedColor;
        else
        if (model.testNotConfigured(row))            
            return notConfigColor;
        else
        if (model.testUnknown(row))            
            return unknownColor;  
        else return unknownColor;
    }
    
    public void registerRendererForClass(JTable table, Class klass) {

        // Get Default Renderer from the table
        DefaultTableCellRenderer defaultRenderer =
            (DefaultTableCellRenderer) 
          table.getDefaultRenderer(klass);

        // Wrap(Decorate) the color renderer around the default renderer
        TableCellRenderer colorRenderer = new ColorRenderer(defaultRenderer, this);

        // Register the color Renderer with the JTable
        table.setDefaultRenderer(klass, colorRenderer);
      }    
}
