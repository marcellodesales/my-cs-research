// Copyright 2003 Nokia Corporation.
//
// THIS SOURCE CODE IS PROVIDED 'AS IS', WITH NO WARRANTIES WHATSOEVER,
// EXPRESS OR IMPLIED, INCLUDING ANY WARRANTY OF MERCHANTABILITY, FITNESS
// FOR ANY PARTICULAR PURPOSE, OR ARISING FROM A COURSE OF DEALING, USAGE
// OR TRADE PRACTICE, RELATING TO THE SOURCE CODE OR ANY WARRANTY OTHERWISE
// ARISING OUT OF ANY PROPOSAL, SPECIFICATION, OR SAMPLE AND WITH NO
// OBLIGATION OF NOKIA TO PROVIDE THE LICENSEE WITH ANY MAINTENANCE OR
// SUPPORT. FURTHERMORE, NOKIA MAKES NO WARRANTY THAT EXERCISE OF THE
// RIGHTS GRANTED HEREUNDER DOES NOT INFRINGE OR MAY NOT CAUSE INFRINGEMENT
// OF ANY PATENT OR OTHER INTELLECTUAL PROPERTY RIGHTS OWNED OR CONTROLLED
// BY THIRD PARTIES
//
// Furthermore, information provided in this source code is preliminary,
// and may be changed substantially prior to final release. Nokia Corporation
// retains the right to make changes to this source code at
// any time, without notice. This source code is provided for informational
// purposes only.
//
// Nokia and Nokia Connecting People are registered trademarks of Nokia
// Corporation.
// Java and all Java-based marks are trademarks or registered trademarks of
// Sun Microsystems, Inc.
// Other product and company names mentioned herein may be trademarks or
// trade names of their respective owners.
//
// A non-exclusive, non-transferable, worldwide, limited license is hereby
// granted to the Licensee to download, print, reproduce and modify the
// source code. The licensee has the right to market, sell, distribute and
// make available the source code in original or modified form only when
// incorporated into the programs developed by the Licensee. No other
// license, express or implied, by estoppel or otherwise, to any other
// intellectual property rights is granted herein.


import java.io.InputStream;
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


// This is the main midlet class. It sets the basic screen,
// and handles transitions to other screens.
public class BookReaderMIDlet
    extends MIDlet
{
    private final Image logo;
    private final BookScreen bookScreen = new BookScreen(this);

    private Book book;


    public BookReaderMIDlet()
    {
        logo = makeImage("/logo.png");
        ErrorScreen.init(logo, Display.getDisplay(this));
    }


    // loads a given image by name
    static Image makeImage(String filename)
    {
        Image image = null;

        try
        {
            image = Image.createImage(filename);
        }
        catch (Exception e)
        {
            // use a null image instead
        }

        return image;
    }


    public void startApp()
    {
        Displayable current = Display.getDisplay(this).getCurrent();

        if (current == null)
        {
            // the first time we are called

            String text = getAppProperty("MIDlet-Name") + "\n" +
                          getAppProperty("MIDlet-Vendor");
            Alert splashScreen = new Alert(null,
                                           text,
                                           logo,
                                           AlertType.INFO);
            splashScreen.setTimeout(3000);

            // create a 'Book' using an appropriate XML parser
            try
            {
                // Read the XML file from the JAR file. (The XML data could also
                // have easily been loaded using an HttpConnection instead.)
                InputStream in = getClass().getResourceAsStream("/book.xml");
                // You can choose here which implementation/library to use
                // e.g. NanoXML
                //book = new NanoBook();
                // e.g. kXML
                book = new kXMLBook();
                book.parse(in);
                in.close();

                // set BookScreen contents and display it
                bookScreen.setBook(book);
                Display.getDisplay(this).setCurrent(splashScreen, bookScreen);
            }
            catch (Exception e)
            {
                ErrorScreen.showError("Error parsing the book", bookScreen);
            }
        }
        else
        {
            Display.getDisplay(this).setCurrent(current);
        }
    }


    public void pauseApp()
    {
    }


    public void destroyApp(boolean unconditional)
    {
        notifyDestroyed();
    }


    // Screen callbacks


    void displayBook()
    {
        Display.getDisplay(this).setCurrent(bookScreen);
    }


    void displayChapter(int index)
    {
        Vector chapters = book.getChapters();
        // normalize the chapter's index
        index = Math.max(Math.min(index, (chapters.size() - 1)), 0);

        Chapter chap = (Chapter) chapters.elementAt(index);
        ChapterScreen screen = new ChapterScreen(this, chap);

        Display.getDisplay(this).setCurrent(screen);
    }


    void displayBookInfo()
    {
        Hashtable properties = book.getProperties();

        if (properties.size() > 0)
        {
            PropertiesScreen props = new PropertiesScreen(this, properties);

            Display.getDisplay(this).setCurrent(props);
        }
        else
        {
            // first time we've been called
            Alert splashScreen = new Alert(null,
                                           "No information available for this book",
                                           logo,
                                           AlertType.INFO);
            splashScreen.setTimeout(3000);

            Display.getDisplay(this).setCurrent(splashScreen, bookScreen);
        }
    }
}

