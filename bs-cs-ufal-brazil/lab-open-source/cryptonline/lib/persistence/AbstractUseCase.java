package br.com.aulaweb.persistence;

import org.apache.ojb.broker.PersistenceBroker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 11:31:41)
 * @author: Administrator
 */
public abstract class AbstractUseCase implements UseCase
{
    protected PersistenceBroker broker;

    /**
     * AbstractUseCase constructor comment.
     */
    public AbstractUseCase(PersistenceBroker broker)
    {
        this.broker = broker;
    }

    /** perform this use case*/
    public abstract void apply();

    /** get descriptive information on use case*/
    public abstract String getDescription();

    /**
     * read a single line from stdin and return as String
     */
    protected String readLineWithMessage(String message)
    {
        System.out.print(message + " ");
        try
        {
            BufferedReader rin = new BufferedReader(new InputStreamReader(System.in));
            return rin.readLine();
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
