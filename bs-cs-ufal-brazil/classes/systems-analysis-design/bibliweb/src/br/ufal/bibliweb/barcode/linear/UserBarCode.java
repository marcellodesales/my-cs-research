package br.ufal.bibliweb.barcode.linear;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserBarCode extends HttpServlet{
	
	private boolean debug;
	
    public UserBarCode(){
        debug = false;
    }

    public void init() throws ServletException{
    }

    private BarCode getChart(HttpServletRequest httpservletrequest){
        BCApplet bcapplet = new BCApplet();
        bcapplet.isStandalone = true;
        bcapplet.init();
        if(httpservletrequest != null)
        {
            if(httpservletrequest.getParameter("DEBUG") != null && httpservletrequest.getParameter("DEBUG").toUpperCase().compareTo("ON") == 0)
                debug = true;
            for(Enumeration enumeration = httpservletrequest.getParameterNames(); enumeration.hasMoreElements();)
            {
                String s = (String)enumeration.nextElement();
                bcapplet.setParameter(s, httpservletrequest.getParameter(s));
                if(debug)
                    System.out.println("PARAM: " + s + "=" + httpservletrequest.getParameter(s));
            }

        }
        return bcapplet.BC;
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException{
        String s = "jpeg";
        if(httpservletrequest != null){
            if(httpservletrequest.getParameter("FORMAT") != null)
                s = httpservletrequest.getParameter("FORMAT").toLowerCase();
            if(s.compareTo("gif") != 0)
                s = "jpeg";
        }
        httpservletresponse.setContentType("image/" + s);
        javax.servlet.ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        httpservletresponse.setHeader("Pragma", "no-cache");
        httpservletresponse.setHeader("Cache-Control", "no-cache");
        httpservletresponse.setDateHeader("Expires", 0L);
        try{
            int i = 200;
            int j = 80;
            if(httpservletrequest != null)
            {
                if(httpservletrequest.getParameter("WIDTH") != null)
                    i = (new Integer(httpservletrequest.getParameter("WIDTH"))).intValue();
                if(httpservletrequest.getParameter("HEIGHT") != null)
                    j = (new Integer(httpservletrequest.getParameter("HEIGHT"))).intValue();
            }
            BufferedImage bufferedimage = new BufferedImage(i, j, 1);
            java.awt.Graphics2D graphics2d = bufferedimage.createGraphics();
            BarCode barcode = getChart(httpservletrequest);
            if(debug)
                System.out.println("Size: " + i + " " + j);
            barcode.setSize(i, j);
            barcode.paint(graphics2d);
            if(s.compareToIgnoreCase("gif") != 0)
            {
                JPEGImageEncoder jpegimageencoder = JPEGCodec.createJPEGEncoder(servletoutputstream);
                jpegimageencoder.encode(bufferedimage);
            }
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException{
		try{
            doGet(httpservletrequest, httpservletresponse);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
