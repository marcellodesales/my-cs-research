package br.ufpe.cin.stp.global.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * A simple class for loading java.util.Properties backed by .properties files
 * deployed as classpath resources. See individual methods for details.
 * 
 * @author Marcello Sales Jr.
 * @author (C) <a href="http://www.javaworld.com/columns/jw-qna-index.shtml">Vlad Roubtsov</a>, 2003
 */
public class PropertiesLoader {
    
    private static final boolean THROW_ON_LOAD_FAILURE = true;
    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
    private static final String SUFFIX = ".properties";
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static PropertiesLoader singleton;

    private PropertiesLoader() {
    }

    /**
     * @created 29/07/2004 11:27:55
     * @return The single instance.
     */
    public synchronized static PropertiesLoader getInstance() {
        if (singleton == null) {
            singleton = new PropertiesLoader();
        }
        return singleton;
    }
    
    /**
     * Looks up a resource named 'name' in the classpath. The resource must map
     * to a file with .properties extention. The name is assumed to be absolute
     * and can use either "/" or "." for package segment separation with an
     * optional leading "/" and optional ".properties" suffix. Thus, the
     * following names refer to the same resource:
     * <pre>
     * some.pkg.Resource
     * some.pkg.Resource.properties
     * some/pkg/Resource
     * some/pkg/Resource.properties
     * /some/pkg/Resource
     * /some/pkg/Resource.properties
     * </pre>
     * 
     * @param name classpath resource name [may not be null]
     * @param loader classloader through which to load the resource [null
     * is equivalent to the application loader]
     * 
     * @return resource converted to java.util.Properties [may be null if the
     * resource was not found and THROW_ON_LOAD_FAILURE is false]
     * @throws IllegalArgumentException if the resource was not found and
     * THROW_ON_LOAD_FAILURE is true
     */
    public Properties loadProperties (String name, ClassLoader loader) {
        if (name == null)
            throw new IllegalArgumentException ("null input: name");
        
        if (name.startsWith ("/"))
            name = name.substring (1);
            
        if (name.endsWith (SUFFIX))
            name = name.substring (0, name.length () - SUFFIX.length ());
        
        Properties result = null;
        
        InputStream in = null;
        try   {
            if (loader == null) loader = ClassLoader.getSystemClassLoader ();
            
            if (LOAD_AS_RESOURCE_BUNDLE) {    
                name = name.replace ('/', '.');

                // throws MissingResourceException on lookup failures:
                final ResourceBundle rb = ResourceBundle.getBundle (name,
                    Locale.getDefault (), loader);
                
                result = new Properties ();
                for (Enumeration keys = rb.getKeys (); keys.hasMoreElements ();) {
                    final String key = (String) keys.nextElement ();
                    final String value = rb.getString (key);
                    
                    result.put (key, value);
                } 
            } else {
                name = name.replace ('.', '/');
                
                if (! name.endsWith (SUFFIX))
                    name = name.concat (SUFFIX);
                                
                // returns null on lookup failures:
                in = loader.getResourceAsStream (name);
                if (in != null)
                {
                    result = new Properties ();
                    result.load (in); // can throw IOException
                }
            }
        } catch (Exception e) {
            result = null;
        } finally {
            if (in != null) try { in.close (); } catch (Throwable ignore) {}
        }
        
        if (THROW_ON_LOAD_FAILURE && (result == null))  {
            throw new IllegalArgumentException ("could not load [" + name + "]" +
                " as " + (LOAD_AS_RESOURCE_BUNDLE
                ? "a resource bundle"
                : "a classloader resource"));
        }
        
        return result;
    }
    
    
    /**
     * 07/06/2004 19:54:01
     * @param name The relative path to the properties file.
     * @param loader The classloader used to open the file.
     * @return The input stream of the opened file.
     * @see java.io.InputStream
     */
    public InputStream loadPropertiesInStream(String name, 
            											ClassLoader loader) {
        if (name == null)
            throw new IllegalArgumentException ("null input: name");
        
        if (name.startsWith ("/"))
            name = name.substring (1);
            
        if (name.endsWith (SUFFIX))
            name = name.substring (0, name.length () - SUFFIX.length ());
        
        InputStream in = null;
        try   {
            if (loader == null) loader = ClassLoader.getSystemClassLoader ();
            
            if (LOAD_AS_RESOURCE_BUNDLE) {    
                name = name.replace ('/', '.');

                // throws MissingResourceException on lookup failures:
                final ResourceBundle rb = ResourceBundle.getBundle (name,
                    Locale.getDefault (), loader);
                
            } else {
                name = name.replace ('.', '/');
                
                if (! name.endsWith (SUFFIX))
                    name = name.concat (SUFFIX);
                                
                // returns null on lookup failures:
                in = loader.getResourceAsStream (name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return in;
    }
    
    /**
     * A convenience overload of {@link #loadProperties(String, ClassLoader)}
     * that uses the current thread's context classloader. A better strategy
     * would be to use techniques shown in
     * http://www.javaworld.com/javaworld/javaqa/2003-06/01-qa-0606-load.html 
     */
    public Properties loadProperties (final String name) {
        return loadProperties (name,
            Thread.currentThread ().getContextClassLoader ());
    }
    
    /**
     * Saves a properties file in the harddisk
     * @created 12/09/2004 02:22:33
     * @param fileName the full path of the file
     * @param values key=value hashtable of the properties values 
     * @throws IOException if an error occor while saving the file.
     */
    public void saveProperties(final String fileName, Hashtable values) throws IOException{
        Enumeration i = values.keys();
        Properties properties = new Properties();
        while (i.hasMoreElements()) {
            String key = (String) i.nextElement();
            properties.setProperty(key,(String)values.get(key));
        }
        properties.store(new FileOutputStream(fileName),null);
    }
    
    /**
     * @created 12/09/2004 02:40:24
     * @param fileName the path to the properties file
     * @param properties
     * @throws IOException if an error occor while saving the file
     */
    public void saveProperties(String fileName, Properties properties) throws IOException{
        properties.store(new FileOutputStream(fileName),null);
    }
    
    /**
     * @created 12/09/2004 02:29:05
     * @param fileName the relative path to the file
     * @return a properties file with the specified path outside the jar file. Don't use slash "/" to indicate the path on the same jar folder
     * @throws IOException
     */
    public Properties loadPropertiesOutsideJar(String fileName) throws IOException{
        Properties properties = new Properties();
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        
        properties.load(fis);
        return properties;
    }
    
    public InputStream loadPropertiesInputStream (final String name) {
        return loadPropertiesInStream(name, Thread.currentThread ().getContextClassLoader ());
    }
}