/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.log.Logger;

/**
 *
 * @author dskaster
 */
public class DAOException extends RuntimeException {
    
    public DAOException(String message) {
        super(message);
        Logger.getInstance().setLog(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        Logger.getInstance().setLog(message+":"+cause);
    }    
}
