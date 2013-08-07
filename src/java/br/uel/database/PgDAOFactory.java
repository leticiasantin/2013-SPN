/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import br.uel.log.Logger;
import java.util.logging.Level;

public class PgDAOFactory extends DAOFactory {

    @Override
    public void connect() {

        try {
            // Este é um dos meios para registrar um driver
            Class.forName("org.postgresql.Driver");

            // Registrado o driver, vamos estabelecer uma conexão
            this.conn = DriverManager.getConnection("jdbc:postgresql://189.90.66.11:5432/leticiacsantin", "leticiacsantin", "3etS");
            Logger.getInstance().setLog("Connection succesfully with: "+ conn.getSchema());
        } catch (SQLException ex) {
            try {
                Logger.getInstance().setLog("error when connecting to the database in Class SQLException " + PgDAOFactory.class.getName() + ex.getErrorCode());
                this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SPN_DB", "postgres", "postgres");
            } catch (SQLException ex1) {
                java.util.logging.Logger.getLogger(PgDAOFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getInstance().setLog(ex.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
        }
    }

    @Override
    public Object getDAOObject(DAOFactory.DAODataType dType) {

        switch (dType) {
            case UserDAO:
                return new PgUserDAO(this.conn);
//            case ServiceDAO:
//                return new PgServiceDAO(this.conn);
            default:
                return null;
        }
    }
}
