package br.ufpe.cin.stp.global.image;

/*
 ImageLoadingManager - a wrapper around the ImageLoader class...
 
 This class keeps track of which images have been loaded -
 and provides a method to check to see if they have all been loaded...

 To use it, repeatedly (once for each image) call:

 ImageLoadingManager.getImage("graphics/imagename.gif");

 ...as early as possible during your setup.

 Then, in your main thread, just before you need to use the images
 you can make sure they have all loaded using code like:

 do {
   try {
     thread.sleep(50);
   }           
   catch (InterruptedException e) { 
   }
 } while (!ImageLoadingManager.allImagesAreLoaded());

 This method of loading the images is used because it is fast -
 much faster than waiting for individual images to load.

 If you don't need to ensure your images have loaded, use the
 ImageLoader class directly.
*/

/**
 * A wrapper around the ImageLoader class...<P>
 *
 * This class keeps track of which images have been loaded -
 * and provides a method to check to see if they have all been loaded...<P>
 * 
 * To use it, repeatedly (once for each image) call:<BR>
 * 
 * <CODE>
 * ImageLoadingManager.getImage("graphics/imagename.gif");<BR>
 * </CODE>
 * 
 * ...as early as possible during your setup.<P>
 * Then, in your main thread, just before you need to use the images
 * you can make sure they have all loaded using code like:<P>
 * 
 * <code>
 * <BLOCKQUOTE>
 * do {<BR>
 * &nbsp;&nbsp;try {<BR>
 * &nbsp;&nbsp;&nbsp;&nbsp;thread.sleep(50);<BR>
 * &nbsp;&nbsp;}<BR>
 * &nbsp;&nbsp;catch (InterruptedException e) { <BR>
 * &nbsp;&nbsp;}<BR>
 * } while (!ImageLoadingManager.allImagesAreLoaded());<BR>
 * 
 * <P>
 * 
 * </BLOCKQUOTE>
 * </CODE>
 * 
 * This method of loading the images is used because it is fast -
 * much faster than waiting for individual images to load.<P>
 * If you don't need to ensure your images have loaded, use the
 * <a href="ImageLoader.html">ImageLoader class</a> directly.<P>
 *
 * @author Tim Tyler
 * @version 1.0
 **/

   import java.awt.Image;

   public class ImageLoadingManager {
      static int number;
      static int max_number;
   
      static boolean[] has_loaded;
      // static boolean[] new_has_loaded;
   
      static Image[] image;
      // static Image[] new_image;
   
      // static Toolkit toolkit;
      // static ImageLoadingManager image_loading_manager;
   
      final static int INCREMENT = 10;
   
    /**
     * ImageLoadingManager, constructor.
     * <p>
     * The constructor is private.
     * There should not be any instances of this
     * class created outside the private one used
     * in the class.
     **/
      private ImageLoadingManager() { 
      } 
   
   
    /**
     * static initialization.
     * <p>
     * Sets up initial images...
     **/
      static { 
         number = 0;
      }
   
   
    /**
     * Get an image.
     * <p>
     * Loads a specified image, either from the currect directory -
     * or from inside the relevant jar file, whichever is appropriate...
     **/ 
      public static Image getImage(String name) {
         if (number >= max_number) {
            makeMore();
         }
      
         image[number] = ImageLoader.getImage(name);
      
         has_loaded[number] = false; // flag to say image not loaded yet...
      
         return image[number++];
      }
   
   
   /** Make more space in the arrays...
     * <P>
     * Expand arrays...
     **/ 
      private static void makeMore() {
         boolean[] new_has_loaded;
         Image[] new_image;
      
         // set up some arrays if they are null...
         if (image == null) {
            image = new Image[number];
         }
      
         if (has_loaded == null) {
            has_loaded = new boolean[number];
         }
      
      	// expand array of images...
         new_image = new Image[max_number + INCREMENT];
      
         System.arraycopy(image, 0, new_image, 0, max_number);
      
         image = new_image;
      
         // ...and array of boolean flags...
         new_has_loaded = new boolean[max_number + INCREMENT];
      
         System.arraycopy(has_loaded, 0, new_has_loaded, 0, max_number);
      
         has_loaded = new_has_loaded;
      
         max_number += INCREMENT;
      }
   
   
   /**
     * Checks to see if all the images have been loaded...
     * <p>
     * Returns true if all the images loaded through the getImage method have been loaded...
     **/ 
      public static boolean allImagesAreLoaded() {
         boolean all_loaded;
      
         all_loaded = true;
      
         if (image != null) {
            for (int i = 0; i < number; i++) {
               if (!has_loaded[i]) {
                  // image has not yet been recorded as having completed loading...
                  if (ImageLoader.getToolkit().prepareImage(image[i], -1, -1, null)) {
                     // this image has now been loaded - no need to check it again...
                     has_loaded[i] = true;
                  }
                  else
                  {
                     // this image has not yet been loaded.  Some work remains to be done...
                     all_loaded = false;
                  }
               }
            }
         
            if (all_loaded) {
               // Free up image references in order to try to tidy up as best as possible....
               number = 0;
               image = null;
               has_loaded = null;
            }
         }
      
         // debug("" + (all_loaded ? "Now ready: all images have been loaded." : "Not ready yet..."));
      
         return all_loaded;
      }
   
   
      private static void debug(String o) {
         System.out.println(o);
      }
   
   }

