// Decompiled by DJ v2.9.9.61 Copyright 2000 Atanas Neshkov  Date: 19/02/2002 15:07:47
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   barCodeEncoder.java

package br.ufal.bibliweb.barcode.linear.encoder;

import br.ufal.bibliweb.barcode.linear.BarCode;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

// Referenced classes of package com.idautomation.linear.encoder:
//            GifEncoder, ImageEncoder

public class BarCodeEncoder
{

    public BarCodeEncoder(BarCode barcode, String s, String s1)
    {
        sFormat = s;
        sFile = s1;
        bc = barcode;
        result = encode();
    }

    private boolean encode()
    {
        if(sFormat.toUpperCase().compareTo("GIF") == 0)
            return saveToGIF();
        if(sFormat.toUpperCase().compareTo("JPEG") == 0)
            return saveToJPEG();
        else
            return false;
    }

    private boolean saveToGIF()
    {
        String s = System.getProperty("java.version");
        if(s.indexOf("1.1") == 0)
            return false;
        try
        {
            BufferedImage bufferedimage = new BufferedImage(bc.getSize().width, bc.getSize().height, 13);
            java.awt.Graphics2D graphics2d = bufferedimage.createGraphics();
            bc.paint(graphics2d);
            File file = new File(sFile);
            file.delete();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            GifEncoder gifencoder = new GifEncoder(bufferedimage, fileoutputstream);
            gifencoder.encode();
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            return false;
        }
        return true;
    }

    private boolean saveToJPEG()
    {
        String s = System.getProperty("java.version");
        if(s.indexOf("1.1") == 0)
            return false;
        try
        {
            BufferedImage bufferedimage = new BufferedImage(bc.getSize().width, bc.getSize().height, 1);
            java.awt.Graphics2D graphics2d = bufferedimage.createGraphics();
            bc.paint(graphics2d);
            File file = new File(sFile);
            file.delete();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            JPEGImageEncoder jpegimageencoder = JPEGCodec.createJPEGEncoder(fileoutputstream);
            jpegimageencoder.encode(bufferedimage);
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            return false;
        }
        return true;
    }

    String sFile;
    String sFormat;
    BarCode bc;
    public boolean result;
}
