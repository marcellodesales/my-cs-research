package br.com.aulaweb.persistence.antigo;

import org.odmg.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 11:31:41)
 * @author: Administrator
 */
public abstract class AbstractUseCase implements UseCase
{
    protected Implementation odmg;

    /**
     * AbstractUseCase constructor comment.
     */
    public AbstractUseCase(Implementation odmg)
    {
        this.odmg = odmg;
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
