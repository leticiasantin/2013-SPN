/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.ClientDAO;
import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leticia
 */
public class PgClientDAO extends ClientDAO{

     public PgClientDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }
    
    
    @Override
    public void create(int cid) {
         try {
             String query = "INSERT INTO spn.client(client_id)\n" +
                         "VALUES (?);";
              Connection conn = daoFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query);
                 ps.setInt(1,cid);

                 ResultSet rs = ps.executeQuery();

                 conn.close();
         } catch (SQLException ex) {
             throw new DAOException(ex.getMessage(),ex.getCause());
         }
        
    }

    @Override
    public boolean read(int cid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(int cid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int cid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
