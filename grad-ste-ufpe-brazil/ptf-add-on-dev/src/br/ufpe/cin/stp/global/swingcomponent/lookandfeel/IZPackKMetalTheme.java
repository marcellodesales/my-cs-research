package br.ufpe.cin.stp.global.swingcomponent.lookandfeel;

import javax.swing.plaf.ColorUIResource;

/**
 * Developed by IZPackKMetalTheme.
 * @author Marcello de Sales
 */
public class IZPackKMetalTheme extends IZPackMetalTheme {

    private final ColorUIResource primary1 = new ColorUIResource(32, 32, 64);
    private final ColorUIResource primary2 = new ColorUIResource(160, 160, 180);
    private final ColorUIResource primary3 = new ColorUIResource(200, 200, 224);
    private final ColorUIResource secondary1 = new ColorUIResource(130, 130, 130);
    private final ColorUIResource secondary2 = new ColorUIResource(180, 180, 180);
    private final ColorUIResource secondary3 = new ColorUIResource(224, 224, 224);    
    
    public IZPackKMetalTheme() {
    }

    public ColorUIResource getPrimary1()
    {
        return primary1;
    }

    public ColorUIResource getPrimary2()
    {
        return primary2;
    }

    public ColorUIResource getPrimary3()
    {
        return primary3;
    }

    public ColorUIResource getSecondary1()
    {
        return secondary1;
    }

    public ColorUIResource getSecondary2()
    {
        return secondary2;
    }

    public ColorUIResource getSecondary3()
    {
        return secondary3;
    }
}
