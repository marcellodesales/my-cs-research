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


import javax.microedition.lcdui.*;

// The chapter screen displays one chapter and allows one to navigate
// to other chapters.
class ChapterScreen
    extends Form
    implements CommandListener
{
    private final BookReaderMIDlet midlet;
    private final Command exitCommand = new Command("Exit", Command.EXIT, 1);
    private final Command backCommand = new Command("Back", Command.BACK, 1);
    private final Command nextCommand = new Command("Next", Command.SCREEN, 1);
    private final Command prevCommand = new Command("Previous", Command.SCREEN, 2);
    private final int index;


    ChapterScreen(BookReaderMIDlet midlet, Chapter chapter)
    {
        super(chapter.getTitle());

        this.midlet = midlet;

        append(new StringItem("", chapter.getContent()));
        index = chapter.getIndex();

        setCommandListener(this);
        addCommand(exitCommand);
        addCommand(backCommand);
        addCommand(nextCommand);
        addCommand(prevCommand);
    }


    public void commandAction(Command command, Displayable displayable)
    {
        if (command == exitCommand)
        {
            midlet.destroyApp(true);
        }
        else if (command == backCommand)
        {
            midlet.displayBook();
        }
        else if (command == prevCommand)
        {
            midlet.displayChapter(index - 1);
        }
        else if (command == nextCommand)
        {
            midlet.displayChapter(index + 1);
        }
    }
}
