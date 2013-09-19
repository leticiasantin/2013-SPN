/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ProviderDAO;
import br.uel.entity.Category;
import br.uel.entity.Provider;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author leticia
 */
public class PgProviderDAO extends ProviderDAO {

    public PgProviderDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void create(int pId) throws DAOException {
        try {
            String query = "INSERT INTO spn.provider(provider_id)"
                    + " VALUES (?);";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pId);
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
    public void delete(int pId) throws DAOException {
        try {
            String query = "DELETE FROM spn.provider WHERE provider_id=?";
             Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, pId);
                ps.executeUpdate();
                conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(),ex.getCause());
        }
        
    }

    @Override
    public boolean providerExistsById(int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> listCat(int provId) throws DAOException {
        List<Category> list = null;
        try {

            String query = "SELECT * FROM spn.provider NATURAL JOIN ("
                    + "SELECT * FROM spn.prov_has_cat NATURAL JOIN spn.category) AS FOO\n"
                    + "WHERE provider_id=?";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, provId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCatId(rs.getInt("cat_id"));
                c.setParentId(rs.getInt("parent_id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }
            conn.close();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void connect(Integer userId, int catId) {
        try {
            String query = "INSERT INTO spn.prov_has_cat VALUES(?,?);";
                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1,userId);
                ps.setInt(2, catId);
                 ps.execute();
                conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void disconnect(Integer userId, int catId) {
        try {
            String query = "DELETE FROM spn.prov_has_cat WHERE provider_id=? AND cat_id=?;";
                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, userId);
                ps.setInt(2, catId);
               ps.execute();
                conn.close();
                
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
