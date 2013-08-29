/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDAOFactory extends DAOFactory {
    
    @Override
    public Connection getConnection() throws DAOException {

        Connection conn = null;
        
        try {
            // Este é um dos meios para registrar um driver
            Class.forName("org.postgresql.Driver");

            String connString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbUser;
            // Registrado o driver, vamos estabelecer uma conexão
            conn = DriverManager.getConnection(connString, dbUser, dbPassword);
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        } catch (ClassNotFoundException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        
        return conn;
    }

    @Override
    public Object getDAOObject(DAOFactory.DAODataType dType) {

        switch (dType) {
            case UserDAO:
                return new PgUserDAO(this);
//            case ServiceDAO:
//                return new PgServiceDAO(this.conn);
            default:
                return null;
        }

    }
}
