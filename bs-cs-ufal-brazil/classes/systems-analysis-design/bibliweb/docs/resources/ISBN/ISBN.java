// ISBN.java the CMP HTML ISBN Amanuensis

/*

The ISBN 1.4 Amanuensis will help you proofread, validate
and tidy ISBN (International Standard Book Number)
references in your HTML or other documentation.

Home page of ISBN organisation:

 http://www.isbn.spk-berlin.de/

Here are some sample ISBNs correctly formatted:

 0-200-00000-4    1-55000-000-4    2-00000000-2    80-0000000-8
 0-7000-0000-3    1-869800-00-1    2-95000000-2    93-9999999-8
 0-85000-000-9    1-9990000-0-5    3-00000000-3    94-1900000-1
 0-900000-00-7                     3-99999999-3    94-0000000-6
 0-9500000-0-0                     4-00000000-4    94-9999999-X
 0-9999999-9-0                     4-95000000-4    950-000000-8
                                   5-00000000-5    950-999999-7
                                   5-95000000-5    995-999999-8
                                   6-00000000-6    9960-00000-1
                                   6-95000000-6    9989-99999-6
                                   7-00000000-7    99901-9601-X
                                   7-20000000-0    99999-0000-3
                                   7-95000000-7    99999-9999-9

Unfortunately the program cannot correctly place the dash
between publisher and title for groups other than 0 and 1.

The lead 0 is significant. ISBNs always contain 10 digits.

It takes the raw ISBN (which may contain dashes, lead, trail
or embedded spaces or other miscellaneous junk), validates
the check digit and insert the dashes in the proper places
using a set of rules described at
http://www.bowker.com/standards/home/isbn/international/hyphenation-instructions.html.

Just paste the raw ISBN into the left window, click Tidy
then copy the tidied ISBN from the right window.

When you run the ISBN Amanuensis as an application, it is
even more automatic. You don't need to do any manual pasting
and copying. Just click Tidy to tidy the current contents of
the clipboard. The tidied ISBN replaces the old the raw text
clipboard contents. This automatic feature is not available
in the Applet version because the Applet sandbox considers
it a security risk. Version 1.4 no longer attempts to place
the dash between publisher and title other than for groups
0 and 1.  It has more relaxed publisher number validation.
*/

/**
 * @author  copyright (c) 1998-2002 Roedy Green, Canadian Mind Products
 * may be copied and used freely for any purpose but military.
 *
 * Roedy Green
 * Canadian Mind Products
 * #327 - 964 Heywood Avenue
 * Victoria, BC Canada V8V 2Y5
 * tel: (250) 361-9093
 * mailto:roedy@mindprod.com
 * http://mindprod.com
 *
 * version 1.5 2001 Feb 9  - check on group number
 * version 1.4 2000 June 7 - looser publisher number checks on high groups.
 *                           No longer attempt to place dash between
 *                           publisher and title for groups other than 0 and 1.
 * version 1.3 1999 January 7 - check for invalid publisher number.
 *                            - correctly handle group codes 80.. 99990.
 * version 1.2 1998 December 31 - handle variable first dash position.
 * version 1.1 1998 December 28 - handle empty or null clipboard better.
 * version 1.0 1998 December 27 - initial release.
 * FUTURE:
 * Convert more that one ISBN per clipboard full so you might use it to
 * validate a whole file at a pop.  Be cleverer at finding them.
 *
 * See sample files:
 * ISBNTest.bat     - run locally as Applet/Application jar/no jar
 * ISBNTest.html    - run locally as Applet
 * ISBNNative.html  - run from a website
 * ISBNPlugIn.html  - run from a website with Java Plug-in.
 */

package cmp.ISBN;

import cmp.business.Misc;
import java.applet.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ISBN extends Applet implements java.awt.datatransfer.ClipboardOwner
   {

   private static final String EmbeddedCopyright =
   "copyright (c) 1998-2000 Roedy Green, Canadian Mind Products, http://mindprod.com";

   /* constructors */
   public ISBN()
      {

      }

   private ISBN (boolean asApplet)
      {
      this.asApplet = asApplet;
      }

   /* true if being run as an Applet, false as an Application */
   private boolean asApplet = true;

   // Use our own colours so Symantec won't mess with them or create dups.
   private static final Color black = Color.black;
   private static final Color blue =  Color.blue;
   private static final Color darkGreen = new Color(0, 128, 0);
   private static final Color red =   Color.red;
   private static final Color white = Color.white;

   /**
    * Standard method to initialise the applet
    */
   public void init()
      {

      // hide everything while we prepare the screen
      setVisible(false);

      setBackground(white);

      if ( ! Misc.isJavaVersionOK(1, 1, 0) )
         {
         System.out.println("You need Java 1.1.0 or later to run this Applet.");
         System.out.println("You are running under " + System.getProperty("java.version"));
         System.exit(1);
         }
      // Applet Gridbag:
      //      0          1
      // --------title--------    0
      // --------      -------    1
      //     Clear       Tidy     2
      // -----instructions-----   3

      // Application Gridbag:
      //      0           1
      // --------title--------    0
      //                          1
      //                Tidy      2
      // -----instructions-----   3

      GridBagLayout gridBagLayout = new GridBagLayout();
      GridBagConstraints gbc;

      setLayout(gridBagLayout);

      titleLabel = new java.awt.Label("CMP ISBN Amanuensis 1.5",Label.CENTER);
      titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
      titleLabel.setForeground(red);
      gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      gbc.fill = GridBagConstraints.NONE;
      gbc.insets = new Insets(10,10,10,10);
      ((GridBagLayout)getLayout()).setConstraints(titleLabel, gbc);
      add(titleLabel);

      if ( asApplet )
         {
         // With applet we have two text windows you must manually cut/paste,
         // with Clear and Tidy Button.
         // With app[ication, you have no windows, just the Tidy Button.
         rawTextArea = new java.awt.TextArea("", 2, 25, TextArea.SCROLLBARS_NONE);
         rawTextArea.setEditable(true);
         rawTextArea.setFont(new Font("Dialog", Font.PLAIN, 15));
         rawTextArea.setForeground(black);
         rawTextArea.setBackground(white);
         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.gridwidth = 1;
         gbc.gridheight = 1;
         gbc.weightx = 10;
         gbc.weighty = 20.;
         gbc.ipadx = 10;
         gbc.ipady = 20;
         gbc.fill = GridBagConstraints.BOTH;
         // increase inset on left side  Top, Left, Bottom, Right
         gbc.insets = new Insets(5,10,5,5);
         ((GridBagLayout)getLayout()).setConstraints(rawTextArea, gbc);
         add(rawTextArea);

         cookedTextArea = new java.awt.TextArea("", 2, 25, TextArea.SCROLLBARS_NONE);
         cookedTextArea.setEditable(false);
         cookedTextArea.setFont(new Font("Dialog", Font.PLAIN, 15));
         cookedTextArea.setForeground(black);
         cookedTextArea.setBackground(white);
         gbc = new GridBagConstraints();
         gbc.gridx = 1;
         gbc.gridy = 1;
         gbc.gridwidth = 1;
         gbc.gridheight = 1;
         gbc.weightx = 10.;
         gbc.weighty = 20.;
         gbc.ipadx = 10;
         gbc.ipady = 20;
         gbc.fill = GridBagConstraints.BOTH;
         // increase inset on right side  Top, Left, Bottom, Right
         gbc.insets = new Insets(5,5,5,10);
         ((GridBagLayout)getLayout()).setConstraints(cookedTextArea, gbc);
         add(cookedTextArea);

         clearButton = new java.awt.Button("Clear");
         // leave colours the default
         clearButton.setFont(new Font("Dialog", Font.BOLD, 16));
         gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 1;
         gbc.gridheight = 1;
         gbc.weightx = 10.;
         gbc.weighty = 0.;
         gbc.ipadx = 20;
         gbc.ipady = 5;
         gbc.anchor = GridBagConstraints.CENTER;
         gbc.fill = GridBagConstraints.NONE;
         gbc.insets = new Insets(5,5,5,5);
         ((GridBagLayout)getLayout()).setConstraints(clearButton, gbc);
         add(clearButton);
         } // end if Applet

      tidyButton = new java.awt.Button("Tidy");
      // leave colours the default
      tidyButton.setFont(new Font("Dialog", Font.BOLD, 16));
      gbc = new GridBagConstraints();
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.weightx = 10.;
      gbc.weighty = 0.;
      gbc.ipadx = 20;
      gbc.ipady = 5;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.fill = GridBagConstraints.NONE;
      gbc.insets = new Insets(5,5,5,5);
      ((GridBagLayout)getLayout()).setConstraints(tidyButton, gbc);
      add(tidyButton);

      instructions = new java.awt.Label("",Label.CENTER);
      if ( asApplet )
         {
         instructions.setText("Paste raw ISBN to left; click Tidy; then Copy tidied ISBN from right.");
         }
      else
         {
         instructions.setText("Copy raw ISBN to the clipboard; click Tidy; then Paste tidied ISBN.");
         }
      instructions.setBackground(white);
      instructions.setForeground(darkGreen);
      gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.gridwidth = 2;
      gbc.ipadx = 0;
      gbc.ipady = 5;
      gbc.fill = GridBagConstraints.NONE;
      gbc.insets = new Insets(5,5,5,5);
      ((GridBagLayout)getLayout()).setConstraints(instructions, gbc);
      add(instructions);

      // REGISTER LISTENER

      TheListener theListener = new TheListener();
      if ( asApplet )
         {
         clearButton.addActionListener(theListener);
         }
      tidyButton.addActionListener(theListener);

      this.validate();
      this.setVisible(true);

      } // end init

   // DECLARE CONTROLS
   java.awt.Label    titleLabel;
   java.awt.TextArea rawTextArea;
   java.awt.TextArea cookedTextArea;
   java.awt.Button   clearButton;
   java.awt.Button   tidyButton;
   java.awt.Label    instructions;

   /**
    * Class to handle button events
    */
   class TheListener implements java.awt.event.ActionListener
      {
      public void actionPerformed(java.awt.event.ActionEvent event)
         {
         Object object = event.getSource();
         if ( object == tidyButton )
            tidyButton_ActionPerformed(event);
         else if ( object == clearButton )
            {
            clearButton_ActionPerformed(event);
            }
         }
      } // end TheListener

   /**
    * action when Tidy button pressed
    */
   void tidyButton_ActionPerformed(java.awt.event.ActionEvent event)
      {

      /**
       * The original format of the clipboard before conversion.
       */
      String raw;

      /**
       * The converted form of the clipboard after conversion.
       */
      String cooked;

      if ( asApplet )
         {
         // as Applet, must rely on manual cut paste to raw and cooked TextAreas.
         raw = rawTextArea.getText();
         // Tidy the ISBN.  Errors will appear inline in the text
         cooked = ISBNValidate.tidyWithDashes(raw);
         cookedTextArea.setText(cooked);
         }
      else
         {
         // as application, grab clipboard automatically
         raw = ClipboardPoker.getClip(this);
         // tidy the ISBN
         cooked = ISBNValidate.tidyWithDashes(raw);
         ClipboardPoker.setClip(cooked, this);
         }
      } // end tidyButton_ActionPerformed

   /**
    * Action when Clear button pressed.
    */
   void clearButton_ActionPerformed(java.awt.event.ActionEvent event)
      {
      rawTextArea.setText(null);
      cookedTextArea.setText(null);
      } // end clearButton_ActionPerformed

   /**
    * If we put data in the Clipboard, and somebody else
    * changes the clipboard, we lose ownership and are notified
    * here.
    * Satisfies the requirement of this class
    * to implement java.awt.datatransfer.ClipboardOwner.
    */
   public void lostOwnership(Clipboard clipboard,
                             Transferable contents)
      {

      }

   private static final boolean debugging = false;

   /**
    * Allow this applet to run as as application as well.
    *
    * @param args command line arguments ignored.
    */
   static public void main(String args[])
      {

      if ( debugging )
         {

         String [] tests = {
            "000000000",
            "020000000",
            "070000000",
            "085000000",
            "090000000",
            "095000000",
            "099999999",
            "100000000",
            "155000000",
            "186980000",
            "199900000",
            "200000000",
            "295000000",
            "300000000",
            "399999999",
            "400000000",
            "495000000",
            "500000000",
            "595000000",
            "600000000",
            "695000000",
            "700000000",
            "720000000",
            "795000000",
            "800000000",
            "939999999",
            "941900000",
            "940000000",
            "949999999",
            "950000000",
            "950999999",
            "995999999",
            "996000000",
            "998999999",
            "999019601",
            "999990000",
            "999999999"
         };
         for ( int i=0; i<tests.length; i++ )
            {
            System.out.println(ISBNValidate.addCheckDigit(tests[i]));
            }
         } // end if debugging

      final ISBN applet = new ISBN(false);
      Frame frame = new Frame("ISBN Amanuensis");
      frame.setSize(440, 200);
      applet.init();
      frame.add(applet);
      frame.validate();
      frame.setVisible(true);
      applet.start();
      frame.addWindowListener
      (
      new java.awt.event.WindowAdapter()
         {
         /**
          * Handle request to shutdown.
          *
          * @param e event giving details of closing.
          */
         public void windowClosing(java.awt.event.WindowEvent e)
            {
            applet.stop();
            applet.destroy();
            System.exit(0);
            } // end WindowClosing
         } // end anonymous class
      ); // end addWindowListener line
      } // end main

   } // end class ISBN

