package br.com.aulaweb.persistence;

/**
 * Interface for application use cases.
 * @author: thma
 */
public interface UseCase
{
    /** perform this use case*/
    public abstract void apply();

    /** get descriptive information on use case*/
    public abstract String getDescription();
}
