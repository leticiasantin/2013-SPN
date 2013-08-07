/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import java.sql.Connection;

/**
 *
 * @author dskaster
 */
public abstract class DAOFactory {
    
    protected Connection conn;

    public Connection getConn() {
        return conn;
    }

    
    public enum DAODataType {
        UserDAO, ServiceDAO, OfferDAO 
    }
    
    public abstract void connect();
    
    public abstract void close();
    
    public abstract Object getDAOObject(DAODataType dType);
    
  

}
