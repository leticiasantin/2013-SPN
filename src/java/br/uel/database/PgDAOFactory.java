/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import br.uel.log.Logger;

public class PgDAOFactory extends DAOFactory {

    @Override
    public void connect() {
        try {
            // Este é um dos meios para registrar um driver
            Class.forName("org.postgresql.Driver");
            // Registrado o driver, vamos estabelecer uma conexão
//            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SPN_DB", "postgres", "postgres");
            this.conn = DriverManager.getConnection("jdbc:postgresql://189.90.66.11:5432/leticiacsantin", "leticiacsantin", "3etS");
            Logger.getInstance().setLog("Connection succesfully with DB");
        } catch (SQLException ex) {
            Logger.getInstance().setLog(PgDAOFactory.class.getName());
        }  catch (ClassNotFoundException ex) {
            Logger.getInstance().setLog(PgDAOFactory.class.getName());
        }
//            ;

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
