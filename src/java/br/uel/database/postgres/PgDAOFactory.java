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

//            String connString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbUser;
//            String connString = "jdbc:postgresql://10.90.67.124:" + dbPort + "/" + dbUser;
            String connString = "jdbc:postgresql://localhost:5432/SPN_DB";
            // Registrado o driver, vamos estabelecer uma conexão
            conn = DriverManager.getConnection(connString, "postgres", "postgres");
//            conn = DriverManager.getConnection(connString, dbUser, dbPassword);
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
            case DivulgationPageDAO:
                return new PgDivulgationPageDAO(this);
            case CategoryDAO:
                return new PgCategoryDAO(this);
            case ProviderDAO:
                return new PgProviderDAO(this);
            case ServiceDAO:
                return new PgServiceDAO(this);
            case ServiceEvaluationDAO:
                return new PgServiceEvaluationDAO(this);
            case PictureDAO:
                return new PgPictureDAO(this);
            default:
                return null;
        }

    }
}
