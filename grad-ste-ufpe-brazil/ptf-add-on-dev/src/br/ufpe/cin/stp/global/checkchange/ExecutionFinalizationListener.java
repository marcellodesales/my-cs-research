/*
 * @created 13/08/2004 14:27:08
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.global.checkchange;

import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;

/**
 * IT's the listener after an execution process has finished.
 * @created 13/08/2004 14:27:08
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public interface ExecutionFinalizationListener {

    /**
     * Process the finalized TestCaseSuite after an execution
     * @created 13/08/2004 14:28:01
     * @param tcs
     */
    public void processFinalization(TestCaseSuite tcs);
}
