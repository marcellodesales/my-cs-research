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


import java.io.*;
import java.util.*;
import nanoxml.kXMLElement;


// Class NanoBook extends Book and implements the load method
// using the NanoXML parser
class NanoBook
    extends Book
{
    void parse(InputStream in)
        throws Exception
    {
        // nano xml uses the kXMLElement to do the parsing and traversing
        kXMLElement xml = new kXMLElement();
        xml.parseFromReader(new InputStreamReader(in));

        // traverse the children
        Enumeration enum = xml.enumerateChildren();
        while (enum.hasMoreElements())
        {
            kXMLElement current = (kXMLElement) enum.nextElement();
            String tagname = current.getTagName();

            // set the title
            if ("title".equals(tagname))
            {
                title = current.getContents();
            }
            // set any metadata key/value pair
            else if ("metadata".equals(tagname))
            {
                String key = current.getProperty("key");

                properties.put(key, current.getContents());
            }
            // If a chapter is found continue the parsing down the tree
            else if ("chapter".equals(tagname))
            {
                Enumeration chapterEnum = current.enumerateChildren();
                String title = "[No title]";
                String content = "[No content]";
                int location = current.getProperty("index", -1);
                // parse the chapter
                while (chapterEnum.hasMoreElements())
                {
                    kXMLElement currentChapter = (kXMLElement) chapterEnum.nextElement();

                    tagname = currentChapter.getTagName();
                    if ("title".equals(tagname))
                    {
                        title = currentChapter.getContents();
                    }
                    else if ("content".equals(tagname))
                    {
                        content = currentChapter.getContents();
                    }
                }
                
                // create the chapter object and insert it at the right location
                Chapter chap = new Chapter(title, content, location);
                chapters.insertElementAt(chap,
                                         (location >= 0) ? location : chapters.size() - 1);
            }
        }
    }
}

