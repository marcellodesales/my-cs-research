package pl;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.Enumeration;

import pl.*;
import pl.map.*;

/**
 * This class represents result of the RetrieveCriteria's perform method.
 *
 * @author: Artyom Rudoy
 */
public class Cursor implements Enumeration
{
    private static Class[] constructorParameters = new Class[] {PersistenceManager.class};

    private java.sql.ResultSet rs = null;
    private pl.map.ClassMap classMap = null;
    private pl.sql.SqlStatement statement = null;
    private boolean proxy = false;
    private PersistenceManager manager = null;
    private Constructor constructor = null;

    /**
     * Cursor constructor.
     */
    public Cursor(pl.sql.SqlStatement statement, ResultSet rs, ClassMap classMap, boolean proxy, PersistenceManager manager) throws PlException
    {
        super();

        this.statement = statement;
        this.rs = rs;
        this.classMap = classMap;
        this.proxy = proxy;
        this.manager = manager;

        try
        {
            constructor = classMap.getMapObjectClass().getConstructor(constructorParameters);
        }
        catch(NoSuchMethodException e)
        {
            throw new PlException("Constructor " + classMap.getName() + "(PersistenceManager manager) not fount in class " +
                classMap.getName());
        }
    }

    /**
     * Close cursor.
     */
    public void close() throws PlException
    {
        try
        {
            rs.close();
            statement.close();
        }
        catch(SQLException e)
        {
            throw new PlException(e);
        }
    }

    /**
     * This method implicitly closes this cursor.
     * @exception java.lang.Throwable
     */
    public void finalize() throws java.lang.Throwable
    {
        close();
    }

    /**
     * Return class map for this cursor.
     *
     * @return pl.map.ClassMap
     */
    public ClassMap getClassMap()
    {
        return classMap;
    }

    /**
     * Return the current object.
     *
     * @return pl.PersistentObject
     */
    public PersistentObject getObject()
    {
		
        try
        {
            PersistentObject object = (PersistentObject)constructor.newInstance(new Object[] {manager});
            if(proxy)
                classMap.retrieveProxyObject(object, rs);
            else
                classMap.retrieveObject(object, rs);

/* As linhas abaixo estao comentadas pelo seguinte motivo:
 * Suponha 3 classes A ,B e C, onde A esta relacionada a B e C herda de A.
 * Como o objeto C nao tinha nenhum relacionamento ele nao tentava recuperar as associacoes e
 * portanto nao herda o relacionamento que a sua superClasse (A) possuia com C.
 * Modificado por Rodrigo Paes.
 */
//            // Retrieve object if it has any associations.
//            // TODO Retrieve only if it has one or more associations with retrieveAutomatic = true
//            if (classMap.getAssociationMaps().size() > 0)
//            {
//                manager.retrieveAssociations(object);
//            }
			/** Essa linha foi eu que coloquei. */
			manager.retrieveAssociations(object);
            return object;
        }
		catch(Exception e){
			System.out.println("Cursor : public PersistentObject getObject() - 118 : "+e.getMessage());
			System.out.println("Dicas: ");
			System.out.println("1- A sua classe nao pode ser abstrata. Verifique se a sua eh. - Rodrigo Paes");
			System.out.println("2- Verifique se sua classe tem o construtor padrao da camada , com o PersistenceManager como parametro - Rodrigo Paes");
			System.out.println("3- Verifique o atributo target do seu schema.xml - Rodrigo Paes");
			System.out.println("4- Verifique o mapeamento das suas associacoes, pode ser que exista alguma recursividade - Rodrigo Paes");
			System.out.println("5- Alguma inconsistencia nos seus dados - Glauber Vinicius");
			e.printStackTrace();
		}
		
        return null;
    }

    /**
     * Returns the current object.
     *
     * @return pl.PersistentObject
     */
    public PersistentObject getObject(Connection conn)
    {
        try
        {
			/* Totalmente modificado.
			 * By: Rodrigo
			 */
			String className = classMap.getName();
			ClassLoader loader = classMap.getClassLoader();
			Class classe = Class.forName(className, true,loader);
			Constructor construtor = classe.getConstructor(constructorParameters);
			
			
			PersistentObject object = (PersistentObject)construtor.newInstance(new Object[] {manager});
            classMap.retrieveObject(object, rs);

            // Retrieve object if it has any associations.
            // TODO Retrieve only if it has one or more associations with retrieveAutomatic = true
//            if (classMap.getAssociationMaps().size() > 0)
//            {
               manager.retrieveAssociations(object, classMap, conn);
//            }
            return object;
        }catch(Exception e){
			e.printStackTrace();
		}

        return null;
    }

    /**
     * This method sets Cursor's position to the next object. Returns true if the object exists.
     *
     * @return boolean
     */
    public boolean next()
    {
        try
        {
            return rs.next();
        }
        catch(Throwable e)
        {
            return false;
        }
    }

    public boolean hasMoreElements()
    {
        return next();
    }

    public Object nextElement()
    {
        return getObject();
    }
}
