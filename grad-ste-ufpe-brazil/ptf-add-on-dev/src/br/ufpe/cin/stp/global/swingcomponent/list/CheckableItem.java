/*
 * Created on 01/07/2004 22:50:35
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.swingcomponent.list;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;



/**
 * CheckableItem is a class that encapsulates the business about a checkable item
 * that has a string to describe it, the state of being selected or not, the
 * token to identify if the string determines if it's selected, and a flag to
 * determine if the component should delete the token from the description.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 01/07/2004
 */
public class CheckableItem {

    /**
     * <code>str</code> is the string that represents the checkanle item.
     */
    private String  str;
    /**
     * <code>isSelected</code> verifies if the instance is checked.
     */
    private boolean isSelected;
    /**
     * <code>icon</code> is an Icon that could be used.
     */
    private Icon icon;
    /**
     * <code>SELECTION_TOKEN</code> is the selection mark of the Item. '#' is used.
     */
    private char selectionToken = '#';
    /**
     * <code>REMOVE_TOKEN</code> verifies if the token should be removed from the title.
     */
    private boolean removeToken = true;

    /**
     * Create a new CheckableItem with a given String. The string can be parsed
     * at the first character if it contains a token. #String = String
     * @param str
     * 02/07/2004 08:41:58
     */
    public CheckableItem(String str) {
        if (str == null || str.equals("")){
            throw new IllegalArgumentException("Only not-null and not-empty string is received!");
        }
        if (str.charAt(0) == this.selectionToken){
            this.isSelected = true;
            if (this.removeToken){
                str = str.substring(1);
            }
        }
        this.str = str;
    }

    /**
     * (02/07/2004 08:49:57)
     * @param b if the item is selected.
     */
    public void setSelected(boolean b) {
      this.isSelected = b;
    }

    /**
     * (02/07/2004 00:34:02)
     * @return true whether the item is checked or not.
     */
    public boolean isSelected() {
      return isSelected;
    }

    /* (non-Javadoc)
     * Returns the identification of the String without the token.
     * @see java.lang.Object#toString()
     * 02/07/2004 00:34:31
     */
    public String toString() {
      return this.str;
    }

    /**
     * (02/07/2004 00:34:41)
     * @return The String representation of CheckedItem with the token.
     */
    public String getString(){
        return (this.isSelected) ? this.selectionToken + str : str;
    }

    /**
     * Sets the icon for the item.
     * (02/07/2004 00:35:26)
     * @param icon
     */
    public void setIcon(Icon icon) {
      this.icon = icon;
    }

    /**
     * (02/07/2004 00:38:33)
     * @return The icon of the item.
     */
    public Icon getIcon() {
      return icon;
    }

    /**
     * Creates a checkable array from a String. The strings on the array
     * can optionally have a token specified on this class.
     * (02/07/2004 00:35:46)
     * @param strs
     * @return
     */
    public static CheckableItem[] getCheckableItems(String[] strs) {
        String[] newStrs = CheckableItem.checkListNull(strs);
        int n = newStrs.length;
        CheckableItem[] items = new CheckableItem[n];
        for (int i = 0; i < n; i++) {
          items[i] = new CheckableItem(newStrs[i]);
        }
        return items;
    }

    /**
     * Creates a checkableItem array from a List. The strings on the List
     * should have a token specified on this class.
     * (02/07/2004 00:35:46)
     * @param strs
     * @return
     */
    public static CheckableItem[] getCheckableItems(List strs) {

        List l = new Vector();

        for (int i=0; i < strs.size(); i++){
            String line = (String)strs.get(i);
          if (!(line.equals(""))){
              if ((line.charAt(0) == '#' && line.charAt(1) != '#' && (line.indexOf("(") == -1 )) || (line.charAt(0) != '#'))
                 if (line.indexOf("com.motorola.testcase") != -1 || line.indexOf("synergyLite.") != -1)
                   l.add(strs.get(i));
          }
        }
        CheckableItem[] items = new CheckableItem[l.size()];

       for (int i = 0; i < l.size(); i++) {
          items[i] = new CheckableItem( (String)l.get(i) );
        }
        return items;
    }

    /**
     * @return Returns the Remove token verification.
     */
    public boolean removeToken() {
        return this.removeToken;
    }
    /**
     * @param remove_token The Remove token to set.
     */
    public void setRemoveToken(boolean remove_token) {
        this.removeToken = remove_token;
    }
    /**
     * @return Returns the setRemoveToken.
     */
    public char getSelectionToken() {
        return this.selectionToken;
    }
    /**
     * @param selection_token The selectionToken to set.
     */
    public void setSelectionToken(char selection_token) {
        this.selectionToken = selection_token;
    }

//    private static String[] checkListNull(Object[] list){
    private static String[] checkListNull(String[] list){
      boolean endBlank = true;

      LinkedList vectorList = new LinkedList();
      for (int i=0; i < list.length; i++)
          vectorList.add(list[i]);

      while (endBlank) {
        for (int i = 0; i < vectorList.size(); i++) {
          if (((String)vectorList.get(i)).equals("")) {
            vectorList.remove(i);
            break;
          }
          if (i == vectorList.size()-1)
            endBlank = false;
        }
      }
      String[] checked = new String[vectorList.size()];
      for (int i = 0; i < vectorList.size(); i++)
        checked[i] = (String)vectorList.get(i);
      return checked;
    }
}
