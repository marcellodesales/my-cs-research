package br.ufal.tci.interpreter;

// JAVA DOC COMMENTING INCLUDED
/**
 * Represents errors that may occur when dealing with a Stack.
 *
 * @author Martin Smith - Template structure of stack<BR>
 *         Martin Smith - Completed constructors
 * @see java.lang.Exception
 * @see Calculator
 * @see Stack
 */
public class StackException extends Exception
{
    /**
     * Construct a StackException with no error message.
     */
    public StackException()
    {
        super();
    }

    /**
     * Construct a StackException with the given error message
     */
    public StackException(String msg)
    {
        super(msg);
    }
}
