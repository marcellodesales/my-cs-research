/*
 * Created on 01/07/2004 22:49:32
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.swingcomponent.list;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;


/**
 * The CheckListRenderer is a render with a list of Strings with a
 * checkbox in the beginning of each item. It can be checked and also
 * enables uses to get the checked values of list.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 01/07/2004
 */
class CheckListRenderer extends CheckRenderer implements ListCellRenderer {

  //Icon commonIcon;

    public CheckListRenderer() {
        check.setBackground(UIManager.getColor("List.textBackground"));
        label.setForeground(UIManager.getColor("List.textForeground"));
        //commonIcon = UIManager.getIcon("Tree.leafIcon");
    }

    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     * 02/07/2004 00:25:18
     */
    public Component getListCellRendererComponent(JList list, Object value,
             int index, boolean isSelected, boolean hasFocus) {

	    this.setEnabled(list.isEnabled());
	    this.check.setSelected(!((CheckableItem)value).isSelected());
	    this.label.setFont(list.getFont());
	    this.label.setText(value.toString());
	    this.label.setSelected(isSelected);
	    this.label.setFocus(hasFocus);
	    Icon icon = ((CheckableItem)value).getIcon();
	    if (icon == null) {
	    //    icon = commonIcon;
	    }
	    //this.label.setIcon(icon);
	    return this;
    }
}
