package br.ufal.bibliweb.barcode.linear;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class IDAImageCreator{
	
	private Image im;
    public Graphics g;
	
    public IDAImageCreator(){
    }

    public Image getImage(int i, int j)
    {
        int k = i;
        if(j > i)
            k = j;
        im = new BufferedImage(k, k, 13);
        g = ((BufferedImage)im).createGraphics();
        return im;
    }

    public Graphics getGraphics(){
        return g;
    }
}
