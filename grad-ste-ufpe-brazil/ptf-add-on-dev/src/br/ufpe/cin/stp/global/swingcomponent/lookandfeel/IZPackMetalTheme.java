package br.ufpe.cin.stp.global.swingcomponent.lookandfeel;

import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * @author Marcello de Sales
 */
public class IZPackMetalTheme extends DefaultMetalTheme {

    private ColorUIResource color;
    private FontUIResource controlFont;
    private FontUIResource menuFont;
    private FontUIResource windowTitleFont;
    private FontUIResource monospacedFont;    
    
    public IZPackMetalTheme() {
        color = new ColorUIResource(0, 0, 0);
        Font font = createFont("Tahoma", 0, 11);
        Font font1 = createFont("Tahoma", 1, 11);
        menuFont = new FontUIResource(font);
        controlFont = new FontUIResource(font);
        windowTitleFont = new FontUIResource(font1);
        monospacedFont = new FontUIResource(font);
    }

    private Font createFont(String s, int i, int j)
    {
        Font font = new Font(s, i, j);
        return font != null ? font : new Font("Dialog", i, j);
    }

    public ColorUIResource getControlTextColor()
    {
        return color;
    }

    public ColorUIResource getMenuTextColor()
    {
        return color;
    }

    public ColorUIResource getSystemTextColor()
    {
        return color;
    }

    public ColorUIResource getUserTextColor()
    {
        return color;
    }

    public FontUIResource getControlTextFont()
    {
        return controlFont;
    }

    public FontUIResource getMenuTextFont()
    {
        return menuFont;
    }

    public FontUIResource getSystemTextFont()
    {
        return controlFont;
    }

    public FontUIResource getUserTextFont()
    {
        return controlFont;
    }

    public FontUIResource getWindowTitleFont()
    {
        return windowTitleFont;
    }
}
