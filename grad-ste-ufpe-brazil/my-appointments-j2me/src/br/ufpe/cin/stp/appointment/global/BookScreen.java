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


import java.util.*;
import javax.microedition.lcdui.*;


// This class displays the contents of a book as a list of chapters
class BookScreen
    extends List
    implements CommandListener
{
    private final BookReaderMIDlet midlet;
    private final Command exitCommand = new Command("Exit", Command.EXIT, 1);
    private final Command infoCommand = new Command("Info", Command.SCREEN, 2);


    // sets the basic properties of the screen
    BookScreen(BookReaderMIDlet midlet)
    {
        super("Book", List.IMPLICIT);

        this.midlet = midlet;

        setCommandListener(this);
        addCommand(exitCommand);
        addCommand(infoCommand);
    }


    // when a book object is passed to the method, the list of chapters' titles
    // are added to the list
    void setBook(Book book)
    {
        setTitle(book.getTitle());

        Vector chapters = book.getChapters();
        for (int i = 0; i < chapters.size(); i++)
        {
            Chapter c = (Chapter) chapters.elementAt(i);
            append(c.getTitle(), null);
        }
    }


    public void commandAction(Command command, Displayable displayable)
    {
        if (command == List.SELECT_COMMAND)
        {
            midlet.displayChapter(getSelectedIndex());
        }
        else if (command == infoCommand)
        {
            midlet.displayBookInfo();
        }
        else  if (command == exitCommand)
        {
            midlet.destroyApp(true);
        }
    }

}

