/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.UserDAO;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author dskaster
 */
public class PgUserDAO extends UserDAO {

    public PgUserDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void create(User u) {
        try {

            String query = "INSERT INTO spn.user(name, login, password, dt_of_birth, city, state, street, "
                    + "number, complements, neighborhood, zip_code)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id";

            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getDtOfBirth());
            ps.setString(5, u.getCity());
            ps.setString(6, u.getState());
            ps.setString(7, u.getStreet());
            ps.setInt(8, u.getNumber());
            ps.setString(9, u.getComplement());
            ps.setString(10, u.getNeighborhood());
            ps.setString(11, u.getZipCode());
            ResultSet rs = ps.executeQuery();
            Logger.getInstance().setLog("resultSet");
            if (rs.next()) {
                u.setUserId(rs.getInt("user_id"));
                Logger.getInstance().setLog("User (id " + u.getUserId() + ") created successfully");
                PgClientDAO pDAO = new PgClientDAO(daoFactory);
                pDAO.create(u.getUserId());
            }
            PgClientDAO cDao = new PgClientDAO(daoFactory);
            cDao.create(u.getUserId());
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getInstance().setLog(ex.getMessage() + ex.getCause());
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<User> list(boolean orderByNome) throws DAOException {
        String query = "SELECT user_id, login, name FROM spn.user";
        if (orderByNome) {
            query += " ORDER BY name";
        } else {
            query += " ORDER BY login";
        }

        List<User> users = new ArrayList<User>();

        try {
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();

                u.setUserId(rs.getInt("user_id"));
                u.setLogin(rs.getString("login"));
                u.setName(rs.getString("name"));
                users.add(u);
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }

        return users;
    }

    @Override
    public User read(int uid) throws DAOException {
        String query = "SELECT * FROM spn.user WHERE user_id = ?";
        User u = null;
        try {
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, uid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = this.setObjUser(rs);
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return u;

    }

    @Override
    public void update(User u) throws DAOException {
        try {
            String query = "UPDATE spn.user SET name=?, login=?, password=?, dt_of_birth=?,"
                    + " city=?, state=?, street=?, number=?, complements=?, "
                    + " neighborhood=?, zip_code=?  WHERE user_id = ?;";
            Logger.getInstance().setLog("query:" + query);
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getDtOfBirth());
            ps.setString(5, u.getCity());
            ps.setString(6, u.getState());
            ps.setString(7, u.getStreet());
            ps.setInt(8, u.getNumber());
            ps.setString(9, u.getComplement());
            ps.setString(10, u.getNeighborhood());
            ps.setString(11, u.getZipCode());
            ps.setInt(12, u.getUserId());

            int rowCount = ps.executeUpdate();
            if (rowCount < 1) {
                throw new DAOException("Usuário inexistente");
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void deleteByUser(int uid) throws DAOException {
        String query = "UPDATE spn.user SET status=false WHERE user_id = ?";

        try {
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, uid);

            int rowCount = ps.executeUpdate();
            if (rowCount < 1) {
                throw new DAOException("Usuário inexistente");
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    public User setObjUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setCity(rs.getString("city"));
        u.setComplement(rs.getString("complements"));
        u.setDtOfBirth(rs.getString("dt_of_birth"));
        u.setLogin(rs.getString("login"));
        u.setName(rs.getString("name"));
        u.setNeighborhood(rs.getString("neighborhood"));
        u.setNumber(rs.getInt("number"));
        u.setState(rs.getString("state"));
        u.setStreet(rs.getString("street"));
        u.setUserId(rs.getInt(("user_id")));
        u.setZipCode(rs.getString(("zip_code")));
        u.setStatus(rs.getBoolean("status"));
        return u;
    }

    @Override
    public boolean userExistsById(int uid) {
        String query = "SELECT user_id FROM spn.user WHERE user_id = ? LIMIT 1";
        try {
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return false;
    }

    @Override
    public boolean userExists(String login, String password) {
        String query = "SELECT user_id FROM spn.user WHERE login = ? AND password=? LIMIT 1";
        try {
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return false;

    }

    @Override
    public User readByLogin(String login) {
        String query = "SELECT * FROM spn.user WHERE login = ?";
        User u = null;
        try {
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = this.setObjUser(rs);
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return u;

    }

    @Override
    public void updateStatus(int userId) {
        try {
            String query = "UPDATE spn.user SET status=?  WHERE user_id = ?;";
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, true);
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public void delete(int uid) throws DAOException {
        try {
            String query = "DELETE FROM spn.user WHERE user_id = ?;";
                Connection conn = daoFactory.getConnection();

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, uid);

                int rowCount = ps.executeUpdate();
                if (rowCount < 1) {
                    throw new DAOException("Usuário inexistente");
                }

                conn.close();
        } catch (SQLException ex) {
           throw new DAOException(ex.getMessage(),ex.getCause());
        }
    }

    @Override
    public List<User> listDeletedUsers() {
         List<User> users = new ArrayList<User>();
        try {
            String query = "SELECT * FROM spn.user WHERE status=false";
           
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = this.setObjUser(rs);
                users.add(u);
            }
            conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;

    }

    @Override
    public boolean userExistsByLogin(String login) {
        try {
            String query = "SELECT user_id FROM spn.user WHERE login = ? LIMIT 1";
               Connection conn = daoFactory.getConnection();
               PreparedStatement ps = conn.prepareStatement(query);
               ps.setString(1, login);
               ResultSet rs = ps.executeQuery();
               if (rs.next()) {
                   conn.close();
                   return true;
               }
               
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
}