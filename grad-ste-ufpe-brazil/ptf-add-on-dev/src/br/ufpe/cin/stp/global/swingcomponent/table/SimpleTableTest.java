/*
 * @created 05/07/2004 11:13:30
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class SimpleTableTest extends JFrame {

  protected JTable table;

  public static void main(String[] args) {
      try {
        UIManager.setLookAndFeel(new com.incors.plaf.kunststoff.KunststoffLookAndFeel());
    } catch (UnsupportedLookAndFeelException e) {
       
        e.printStackTrace();
    }
    SimpleTableTest stt = new SimpleTableTest();
    stt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    stt.setSize(400, 300);
    stt.setVisible(true);
  } 

  public SimpleTableTest() {
      
      Container pane = getContentPane();
      pane.setLayout(new BorderLayout());
      
      String[] lines = {"dsokdoskdosd","#commented=value"," # commentedSpace=roorp","notCommented=br.not.commented","reorkoerieoiroeri","#dsokdokdksd","#######dfodkfodkfodkf"};
      
      PropertiesTableModel tv = new PropertiesTableModel(lines);
      table = new JTable(tv);

      JScrollPane jsp = new JScrollPane(table);
      pane.add(jsp, BorderLayout.CENTER);     
      
      TableColumn column = null;
      for (int i = 0; i < 2; i++) {
          column = table.getColumnModel().getColumn(i);
          if (i == 0) {
              column.setWidth(10); //sport column is bigger
          } else {
              column.setPreferredWidth(100);
          }
      }
      
      table.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
              int col = table.getSelectedColumn();
              int row = table.getSelectedRow();

              TableModel model = table.getModel();
              
              //Adds a new line...
              //((PropertiesTableModel)model).addValue("NEW Key","Values on");
              model = table.getModel();
              
              //removes a line..
              //((PropertiesTableModel)model).removeValue(row);
              
              //repaint the file...
              table.repaint();
              //table.setModel(model);
              
              String[] lines = ((PropertiesTableModel)model).getPropertiesLines();
              for (int i = 0; i < lines.length; i++) {
                    System.out.println(lines[i]);
                }
          }
        });   
  }

} 

