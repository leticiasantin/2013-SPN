/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.database.postgres.PgDAOFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dskaster
 */
public abstract class DAOFactory {

    protected static String dbDriver = "Undefined";
    protected static String dbHost;
    protected static String dbPort;
    protected static String dbUser;
    protected static String dbPassword;
    protected static String dbName;
    
    private static DAOFactory daoFactory = null;

    public enum DAODataType {

        UserDAO, ServiceDAO, DivulgationPageDAO, CategoryDAO, ProviderDAO, ServiceEvaluationDAO,
        PictureDAO
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            Properties prop = new Properties();
            
            try {
                //load a properties file
                String pathRoot = System.getProperty("user.dir"); // $TOMCAT_HOME/bin
                System.out.println(pathRoot);
                prop.load(new FileInputStream(pathRoot + "/" + "bd.config.properties"));

                //get the property value and print it out
                dbDriver = prop.getProperty("dbdriver");
                dbHost = prop.getProperty("dbhost");
                dbPort = prop.getProperty("dbport");
                dbUser = prop.getProperty("dbuser");
                dbPassword = prop.getProperty("dbpassword");
                dbName = prop.getProperty("dbname");
                
                if (dbDriver.equalsIgnoreCase("PostgreSQL"))
                    daoFactory = new PgDAOFactory();
                else
                    throw new DAOException("Unsupported DBMS: " + dbDriver);

            } catch (IOException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException(ex.getMessage(), ex.getCause());
            }
        }
        return daoFactory;
    }    

    public abstract Connection getConnection();

    public abstract Object getDAOObject(DAODataType dType);
}
