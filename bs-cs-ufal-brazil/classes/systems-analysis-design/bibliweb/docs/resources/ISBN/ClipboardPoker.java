package cmp.ISBN;

import java.applet.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Lets us get and put the contents of the clipboard.
 * Class should never even be loaded if we are running as an Applet.
 * Users of this class must:
 * import java.awt.datatransfer.*;
 * and implement java.awt.datatransfer.ClipboardOwner
 * with at least a dummy lostOwnership method.
 */
public class ClipboardPoker
   {

   /**
    * handle to the system clipboard for cut/paste
    * Avoid init, unless method actually called.
    */
   private static Clipboard clipboard;

   /**
    * get current contents of the Clipboard as a String.
    * Returns null if any trouble, or if clip is empty.
    * Not legal in a an Applet.
    */
   public static String getClip(Applet owner)
      {
      initClipboardHook(owner);
      if ( clipboard == null )
         {
         return null;
         }
      try
         {
         try
            {
            Transferable contents = clipboard.getContents(owner);
            if ( contents == null )
               {
               return null;
               }
            // stringFlavor is when you want an ordinary String.
            // plainTextFlavor is for when you want a StringReader instead of a String
            if ( contents.isDataFlavorSupported(DataFlavor.stringFlavor) )
               {
               // Don't need to worry about a ClassCastException even if result is null.
               String s = (String) contents.getTransferData(DataFlavor.stringFlavor);
               if ( s == null )
                  {
                  return null;
                  }
               if ( s.length() == 0 )
                  {
                  return null;
                  }
               return s;
               } // end if
            }
         catch ( java.awt.datatransfer.UnsupportedFlavorException e )
            {

            }
         }
      catch ( java.io.IOException e )
         {

         }
      return null;
      } // end getClip

   /**
    * Copy data into the Clipboard.  We become its owner
    * until somebody else changes the value.
    * @param s new contents of clipboard, may be null or "".
    * Not legal in an Applet.
    */
   public static void setClip( String s, Applet owner)
      {
      initClipboardHook(owner);
      if ( clipboard == null )
         {
         return;
         }
      if ( s == null )
         {
         s = "";
         }
      StringSelection contents = new StringSelection(s);
      clipboard.setContents(contents, (ClipboardOwner) owner);
      } // end setClip

   /**
    * initialises hook to the system clipboard
    */
   private static void initClipboardHook (Applet owner)
      {
      if ( clipboard == null )
         {
         Toolkit t = owner.getToolkit();
         if ( t != null )
            {
            clipboard = t.getSystemClipboard();
            } // end if
         } // end if
      } // end initClipBoard

   } // end class ClipBoardPoker

