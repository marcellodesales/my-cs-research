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

import org.kxml2.io.*;
import java.io.*;
import java.util.*;


// Class kXMLBook extends Book and implements the load method
// using the kXML 2 parser
class kXMLBook
    extends Book
{
    void parse(InputStream in)
        throws Exception
    {
        KXmlParser parser = new KXmlParser();
        parser.setInput(new InputStreamReader(in));

        int eventType = KXmlParser.START_TAG;

        while (eventType != KXmlParser.END_DOCUMENT)
        {
            eventType = parser.next();
            if (eventType == KXmlParser.START_TAG)
            {
                if (parser.getName().equals("title"))
                {
                    parser.next();
                    this.title = parser.getText();
                }
                else if (parser.getName().equals("metadata"))
                {
                    String key = parser.getAttributeValue(null, "key");
                    parser.next();
                    String value = parser.getText();
                    properties.put(key, value);
                }
                else if (parser.getName().equals("chapter"))
                {
                    parseChapter(parser);
                }
            }

        }

    }


    // parse a chapter
    void parseChapter(KXmlParser parser)
        throws Exception
    {
        parser.require(KXmlParser.START_TAG, "", "chapter");

        String title = "[No title]";
        String content = "[No content]";

        // get the chapter number
        int location = Integer.parseInt(parser.getAttributeValue(null, "index"));
        int eventType = parser.next();

        // parse until a chapter tag is hit
        while (!"chapter".equals(parser.getName()))
        {
            if (eventType == KXmlParser.START_TAG)
            {
                if (parser.getName().equals("title"))
                {
                    parser.next();
                    title = parser.getText();
                }
                else if (parser.getName().equals("content"))
                {
                    parser.next();
                    content = parser.getText();
                }
            }
            eventType = parser.next();
        }

        Chapter chap = new Chapter(title, content, location);

        chapters.insertElementAt(chap, (location >= 0) ? location : chapters.size() - 1);
        parser.require(parser.END_TAG, "", "chapter");
    }
}

