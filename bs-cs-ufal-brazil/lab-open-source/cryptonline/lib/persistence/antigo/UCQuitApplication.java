package br.com.aulaweb.persistence.antigo;

import org.odmg.Implementation;

/**
 * implements Use Case "Quit Application".
 * @author: thma
 */
public class UCQuitApplication extends AbstractUseCase
{
    /**
     * UCQuitApplication constructor comment.
     */
    public UCQuitApplication(Implementation impl)
    {
        super(impl);
    }

    /**
     * apply method comment.
     */
    public void apply()
    {
        // no OJB API for quitting the application ;-)
        System.out.println("bye...");
        System.exit(0);
    }

    /**
     * getDescription method comment.
     */
    public String getDescription()
    {
        return "Quit Application";
    }
}
