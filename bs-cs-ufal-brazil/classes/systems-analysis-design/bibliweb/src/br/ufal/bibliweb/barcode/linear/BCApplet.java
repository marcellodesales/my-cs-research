package br.ufal.bibliweb.barcode.linear;

import java.applet.Applet;
import java.awt.*;

public class BCApplet extends Applet
{

    public BCApplet()
    {
        BC = null;
        isStandalone = false;
        setLayout(new BorderLayout());
    }

    public void start()
    {
        BC.paint(getGraphics());
    }

    public void refresh()
    {
        BC.paint(BC.getGraphics());
        paintAll(getGraphics());
    }

    public void init()
    {
        if(BC == null)
            BC = new BarCode();
        add("Center", BC);
        C0("CODE_TYPE");
        C0("N");
        C0("X");
        C0("I");
        C0("H");
        C0("BAR_HEIGHT");
        C0("CODABAR_START");
        C0("CODABAR_STOP");
        C0("BAR_COLOR");
        C0("FONT_COLOR");
        C0("TEXT_FONT");
        C0("UPCE_SYSTEM");
        C0("BACK_COLOR");
        C0("CODE128_SET");
        C0("LEFT_MARGIN");
        C0("TOP_MARGIN");
        C0("CHECK_CHAR");
        C0("BARCODE");
        C0("GUARDBARS");
        C0("ROTATE");
        C0("SUPPLEMENT");
        C0("SUPPLEMENT_CODE");
        C0("SUPPLEMENT_HEIGHT");
        C0("SUPPLEMENT_SEPARATION");
        C0("POSTNET_TALL");
        C0("POSTNET_SHORT");
        C0("CHECK_CHARINTEXT");
    }

    private void C0(String s)
    {
        String s1 = getStringParam(s, "");
        if(s1.length() == 0)
        {
            return;
        } else
        {
            setParameter(s, s1);
            return;
        }
    }

    public void setParameter(String s, String s1)
    {
        if(s1 == null)
            return;
        if(s.compareTo("CODE_TYPE") == 0)
        {
            if(s1.compareTo("CODE39") == 0)
                BC.barType = 0;
            if(s1.compareTo("CODE39EXT") == 0)
                BC.barType = 1;
            if(s1.compareTo("CODE93") == 0)
                BC.barType = 9;
            if(s1.compareTo("CODE11") == 0)
                BC.barType = 3;
            if(s1.compareTo("CODABAR") == 0)
                BC.barType = 4;
            if(s1.compareTo("CODE93EXT") == 0)
                BC.barType = 14;
            if(s1.compareTo("CODE128") == 0)
                BC.barType = 13;
            if(s1.compareTo("MSI") == 0)
                BC.barType = 5;
            if(s1.compareTo("IND25") == 0)
                BC.barType = 7;
            if(s1.compareTo("MAT25") == 0)
                BC.barType = 8;
            if(s1.compareTo("INTERLEAVED25") == 0)
                BC.barType = 2;
            if(s1.compareTo("EAN13") == 0)
                BC.barType = 10;
            if(s1.compareTo("EAN8") == 0)
                BC.barType = 11;
            if(s1.compareTo("UPCA") == 0)
                BC.barType = 6;
            if(s1.compareTo("UPCE") == 0)
                BC.barType = 12;
            if(s1.compareTo("POSTNET") == 0)
                BC.barType = 15;
            if(s1.compareTo("PLANET") == 0)
                BC.barType = 16;
            if(s1.compareTo("UCC128") == 0)
                BC.barType = 17;
        }
        if(s.compareTo("N") == 0)
            BC.N = (new Double(s1)).doubleValue();
        if(s.compareTo("SUPPLEMENT_CODE") == 0)
            BC.supplement = s1;
        if(s.compareTo("SUPPLEMENT_SEPARATION") == 0)
            BC.supSeparationCM = (new Double(s1)).doubleValue();
        if(s.compareTo("SUPPLEMENT_HEIGHT") == 0)
            BC.supHeight = (new Double(s1)).doubleValue();
        if(s.compareTo("SUPPLEMENT") == 0)
        {
            BC.UPCEANSupplement2 = false;
            BC.UPCEANSupplement5 = false;
            if(s1.compareTo("2") == 0)
                BC.UPCEANSupplement2 = true;
            if(s1.compareTo("5") == 0)
                BC.UPCEANSupplement5 = true;
        }
        if(s.compareTo("ROTATE") == 0)
            BC.rotate = (int)(new Double(s1)).doubleValue();
        if(s.compareTo("POSTNET_TALL") == 0)
            BC.postnetHeightTallBar = (int)(new Double(s1)).doubleValue();
        if(s.compareTo("POSTNET_SHORT") == 0)
            BC.postnetHeightShortBar = (int)(new Double(s1)).doubleValue();
        if(s.compareTo("X") == 0)
            BC.X = (new Double(s1)).doubleValue();
        if(s.compareTo("I") == 0)
            BC.I = (new Double(s1)).doubleValue();
        if(s.compareTo("LEFT_MARGIN") == 0)
            BC.leftMarginCM = (new Double(s1)).doubleValue();
        if(s.compareTo("TOP_MARGIN") == 0)
            BC.topMarginCM = (new Double(s1)).doubleValue();
        if(s.compareTo("BAR_COLOR") == 0)
            BC.barColor = convertColor(s1);
        if(s.compareTo("FONT_COLOR") == 0)
            BC.fontColor = convertColor(s1);
        if(s.compareTo("BACK_COLOR") == 0)
            BC.backColor = convertColor(s1);
        if(s.compareTo("GUARDBARS") == 0)
            BC.guardBars = s1.compareTo("Y") == 0;
        if(s.compareTo("UPCE_SYSTEM") == 0)
            BC.UPCESytem = (new String(s1 + "1")).charAt(0);
        if(s.compareTo("CODABAR_START") == 0)
            BC.CODABARStartChar = (new String(s1 + "A")).charAt(0);
        if(s.compareTo("CODABAR_STOP") == 0)
            BC.CODABARStopChar = (new String(s1 + "A")).charAt(0);
        if(s.compareTo("TEXT_FONT") == 0)
            BC.textFont = convertFont(s1);
        if(s.compareTo("H") == 0)
            BC.H = (new Double(s1)).doubleValue();
        if(s.compareTo("BARCODE") == 0)
            BC.setDataToEncode(s1);
        if(s.compareTo("CHECK_CHAR") == 0)
            BC.checkCharacter = s1.compareTo("Y") == 0;
        if(s.compareTo("CHECK_CHARINTEXT") == 0)
            BC.checkCharacterInText = s1.compareTo("Y") == 0;
        if(s.compareTo("CODE128_SET") == 0)
            BC.Code128Set = (new String(s1 + "B")).charAt(0);
        if(s.compareTo("BAR_HEIGHT") == 0)
            BC.barHeightCM = (new Double(s1)).doubleValue();
    }

    public Color convertColor(String s)
    {
        if(s.compareTo("NULL") == 0)
            return null;
        if(s.compareTo("RED") == 0)
            return Color.red;
        if(s.compareTo("BLACK") == 0)
            return Color.black;
        if(s.compareTo("BLUE") == 0)
            return Color.blue;
        if(s.compareTo("CYAN") == 0)
            return Color.cyan;
        if(s.compareTo("DARKGRAY") == 0)
            return Color.darkGray;
        if(s.compareTo("GRAY") == 0)
            return Color.gray;
        if(s.compareTo("GREEN") == 0)
            return Color.green;
        if(s.compareTo("LIGHTGRAY") == 0)
            return Color.lightGray;
        if(s.compareTo("MAGENTA") == 0)
            return Color.magenta;
        if(s.compareTo("ORANGE") == 0)
            return Color.orange;
        if(s.compareTo("PINK") == 0)
            return Color.pink;
        if(s.compareTo("WHITE") == 0)
            return Color.white;
        if(s.compareTo("YELLOW") == 0)
            return Color.yellow;
        try
        {
            return Color.decode(s);
        }
        catch(Exception exception)
        {
            return Color.black;
        }
    }

    public Font convertFont(String s)
    {
        String as[] = C2(s);
        if(as == null)
            return null;
        if(as.length < 3)
            return null;
        byte byte0 = 0;
        if(as[1].compareTo("BOLD") == 0)
            byte0 = 1;
        if(as[1].compareTo("ITALIC") == 0)
            byte0 = 2;
        try
        {
            return new Font(as[0], byte0, (new Integer(as[2])).intValue());
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    protected String getStringParam(String s, String s1)
    {
        return C1(s, s1);
    }

    private String C1(String s, String s1)
    {
        if(isStandalone)
            return s1;
        if(getParameter(s) != null)
            return getParameter(s);
        else
            return s1;
    }

    private String[] C2(String s)
    {
        String as[] = new String[500];
        int i = 0;
        for(int j = s.indexOf("|"); j >= 0; j = s.indexOf("|"))
        {
            as[i++] = s.substring(0, j);
            s = s.substring(j + 1, s.length());
        }

        if(s.compareTo("") != 0)
            as[i++] = s;
        if(i == 0)
            return null;
        String as1[] = new String[i];
        for(int k = 0; k < i; k++)
            as1[k] = as[k];

        return as1;
    }

    public BarCode BC;
    public boolean isStandalone;
}
