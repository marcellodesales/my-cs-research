/*
 * @created 31/07/2004 18:58:13
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.persistence.memory;

import br.ufpe.cin.stp.mass.persistence.PersistenceLayerException;

/**
 * The memory repository exception for the ordinary commands.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 18:58:13
 */
public class MemoryRepositoryException extends PersistenceLayerException {

    public MemoryRepositoryException(String message){
        super(message);
    }
}
