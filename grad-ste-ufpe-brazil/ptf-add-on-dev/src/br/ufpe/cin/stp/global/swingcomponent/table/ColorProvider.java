/*
 * @created 20/07/2004 19:27:32
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.swingcomponent.table;

import java.awt.Color;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 20/07/2004 19:27:32
 */
public interface ColorProvider {
    public Color getForeground(int row, int column);
    public Color getBackground(int row, int column);

}
