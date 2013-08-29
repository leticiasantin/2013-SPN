/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ProviderDAO;
import br.uel.entity.Provider;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author leticia
 */
public class PgProviderDAO extends ProviderDAO{

        public PgProviderDAO(DAOFactory daoFactory_) {
          daoFactory = daoFactory_;
    }
    
    
    @Override
    public void create(Provider p) throws DAOException {
        try {
            String query = "INSERT INTO spn.provider(provider_id, profile_id)"
                    + " VALUES (?, ?)";

            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, p.getUserId());
            ps.setInt(2, p.getProfileId());
        
            ResultSet rs = ps.executeQuery();

            conn.close();
       
        } catch (SQLException ex) {
            Logger.getInstance().setLog(ex.getMessage() + ex.getCause());
        }
   }

    @Override
    public List<User> list(boolean orderByName) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User read(int pid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Provider p) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int uid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean providerExistsById(int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
