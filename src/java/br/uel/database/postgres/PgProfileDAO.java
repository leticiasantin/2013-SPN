/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ProfileDAO;
import br.uel.entity.Profile;
import br.uel.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leticia
 */
public class PgProfileDAO extends ProfileDAO {

    public PgProfileDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void create(Profile p) throws DAOException {
        try {
            String query = "INSERT INTO spn.profile("
                    + "description, picture, provider_id)"
                    + " VALUES ( ?, ?, ?) RETURNING profile_id;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getDescription());
            ps.setString(2, p.getPicture());
            ps.setInt(3, p.getProviderId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setProfileId(rs.getInt("profile_id"));
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Profile read(int pid) throws DAOException {
        String query = "SELECT * FROM spn.user u INNER JOIN "
                + " (SELECT * FROM spn.profile prof NATURAL JOIN spn.provider prov WHERE prov.status=true) p "
                + " ON u.user_id=p.provider_id AND u.user_id=? LIMIT 1";
        Profile p = null;
        try {
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = this.getObjProfile(rs);
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return p;

    }

    @Override
    public void update(Profile p) throws DAOException {
        try {
            String query = "UPDATE spn.profile SET description=? WHERE profile_id = ?;";

            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, p.getDescription());
            ps.setInt(2, p.getProfileId());
            
            int rowCount = ps.executeUpdate();
            if (rowCount < 1) {
                throw new DAOException("Perfil inexistente");
            }

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public void delete(int pid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Profile getObjProfile(ResultSet rs) throws SQLException {
        Profile p = new Profile();
        p.setProfileId(rs.getInt("profile_id"));
        p.setDescription(rs.getString("description"));
        p.setPicture(rs.getString("picture"));
        User u = new User();
        u.setCity(rs.getString("city"));
        u.setComplement(rs.getString("complements"));
        u.setDtOfBirth(rs.getString("dt_of_birth"));
        u.setName(rs.getString("name"));
        u.setNeighborhood(rs.getString("neighborhood"));
        u.setNumber(rs.getInt("number"));
        u.setState(rs.getString("state"));
        u.setStreet(rs.getString("street"));
        u.setZipCode(rs.getString("zip_code"));
        u.setUserId(rs.getInt("user_id"));
        p.setUser(u);
        return p;
    }
}
