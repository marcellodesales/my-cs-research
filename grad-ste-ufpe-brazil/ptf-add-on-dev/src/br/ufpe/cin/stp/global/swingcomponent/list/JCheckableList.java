/*
 * Created on 01/07/2004 22:53:23
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package br.ufpe.cin.stp.global.swingcomponent.list;

import java.awt.Rectangle;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


/**
 * Creates a new CheckableList with a CheckBox on the beginning of the line
 * with a string beside it. It's implemented via a CheckListRenderer instance.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 01/07/2004
 */
public class JCheckableList extends JList {

    /**
     * @param list is the checkable items.
     * 01/07/2004 23:38:04
     */
    public JCheckableList(CheckableItem[] list){
        super(list);
        this.setCellRenderer(new CheckListRenderer());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setBorder(new EmptyBorder(0,4,0,0));
    }

    public JCheckableList(){
      super();
    }

    /**
     * Checks and item of the list.
     * (01/07/2004 23:37:26)
     * @param index is the index of the list to check its checkbox.
     */
    public void checkItem(int index){
        CheckableItem item = (CheckableItem)this.getModel().getElementAt(index);
        item.setSelected(!item.isSelected());
        Rectangle rect = this.getCellBounds(index, index);
        this.repaint(rect);
    }

    /**
     * (01/07/2004 23:31:54)
     * @return the checkableItem list;
     */
    public CheckableItem[] getCheckedList(){

        ListModel model = this.getModel();
        int n = model.getSize();
        CheckableItem[] items = new CheckableItem[n];

        for (int i=0; i<n; i++)
            items[i] = (CheckableItem)model.getElementAt(i);
        return items;
    }


    public int getCheckedItems(){
        int count = 0;
        CheckableItem[] aux = this.getCheckedList();
        for (int i = 0; i < aux.length; i++)
          if ( !aux[i].isSelected() )
             count++;

        return count;
    }

    /**
     * (01/07/2004 23:31:54)
     * @return an array with the strings with a checked token
     */
    public String[] getCheckedCompleteList(){

        ListModel model = this.getModel();
        int n = model.getSize();
        String[] items = new String[n];

        for (int i=0; i<n; i++)
            items[i] = ((CheckableItem)model.getElementAt(i)).getString();
        return items;
    }

    public void setListData(CheckableItem[] listData){
      super.setListData(listData);
      this.setCellRenderer(new CheckListRenderer());
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.setBorder(new EmptyBorder(0,4,0,0));
    }


}
