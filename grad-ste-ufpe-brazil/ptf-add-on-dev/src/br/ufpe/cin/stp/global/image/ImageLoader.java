package br.ufpe.cin.stp.global.image;

/**
 * A class containing static methods which load images -
 * including images within jar files...
 * <p>
 * @author Tim Tyler
 * @version 1.11
 **/

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {
      private static ImageLoader image_loader;
      private static Toolkit toolkit;
    /**
     * ImageLoader, constructor.
     * <p>
     * The constructor is private.
     * There should not be any instances of this
     * class created outside the private one used
     * in the class.
     **/
      private ImageLoader() { 
      } 
   
   
    /**
     * static initialization.
     * <p>
     * Create an instance of this class for later use...
     **/
      static { 
         image_loader = new ImageLoader();
      }
   
   
    /**
     * Get an image.
     * <p>
     * Loads a specified image, either from the currect directory,
     * Or from inside the relevant jar file, whichever is appropriate.
     **/ 
      public static Image getImage(String name) {
         InputStream in;
      
         byte[] _array;
         int _array_size;
      
         toolkit = Toolkit.getDefaultToolkit();
      
      	// debug("Starting to load "+ name + ".");
      
         try {
            in = image_loader.getClass().getResourceAsStream(name); 
         
         /*
            // old, inelegant code...
            byte[] _new_array;
            byte[] _second_array;
            int _second_array_size;
            int _new_array_size;
            int _bytes_read;
            int _more_bytes_read;
            int _old_second_array_size;
            
            _array_size = 0;
            _array = new byte[_array_size];
            _second_array = null;
            _second_array_size = 0;
         
            do {
               _bytes_read = in.read(_array); 
               if (_bytes_read != -1) {
                  // not yet finished, so get a second array...
                  _old_second_array_size = _second_array_size;
                  _second_array_size = in.available();
               
                  if (_second_array_size > _old_second_array_size) {
                     // more memory is needed, allocate it...
                     _second_array = new byte[_second_array_size]; 
                  }
               
                  _more_bytes_read = in.read(_second_array);
               	// calculate size of new array...
                  _new_array_size = _array_size + _second_array_size;
               	// make a new array...
                  _new_array = new byte[_new_array_size];
               	// put all the existing data into it....
                  System.arraycopy(_array, 0, _new_array, 0, _array_size);
                  System.arraycopy(_second_array, 0, _new_array, _array_size, _second_array_size);
               	// transfer the reference back to the original array...
                  _array = _new_array;
               	// and update its file size...
                  _array_size = _new_array_size;
               }
            } while (_bytes_read != -1);
         */
         
            // New, neater method.  Thanks to Karl Schmidt for the followig code...
            ByteArrayOutputStream bytes;
         
            bytes = new ByteArrayOutputStream();
            _array_size = 1024;  // choose a size...
            _array = new byte[_array_size];
         
            int rb;
         
            while ((rb = in.read(_array, 0, _array_size)) > -1) {
               bytes.write(_array, 0, rb);
            }
         
            bytes.close();
         
            _array = bytes.toByteArray();
         
            in.close();
         
            Image image = toolkit.createImage(_array);
         
            // Don't wait here.  If you want to wait for your images to load, you should
            // almost certainly be using the ImageLoadingManager class...
         
            // do { } while(!toolkit.prepareImage(image, -1, -1, null)); // debug("Finished loading: " + name + ".");
         
            return image;
         }
            catch (IOException e) {
               debug(e.getMessage());
               return null;
            }
      }
   
   
   /**
     * Returns the Toolkit used...
     * <p>
     * Allow access to the toolkit used (i.e. access from outside this class)...
     **/ 
      public static Toolkit getToolkit() {
         return toolkit;
      }
   
   
      private static void debug(String o) {
         System.out.println(o);
      }
      
      public static void main(String[] args) {
        ImageLoader.getImage("/res/img/exit.png");
    }
   
   }

