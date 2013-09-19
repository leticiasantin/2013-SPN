/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.DivulgationPageDAO;
import br.uel.entity.DivulgationPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leticia
 */
public class PgDivulgationPageDAO extends DivulgationPageDAO {

    public PgDivulgationPageDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void create(DivulgationPage p) throws DAOException {
        try {
            String query = "INSERT INTO spn.divulgation_page(provider_id)"
                    + " VALUES (?) RETURNING profile_id,description,provider_id;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, p.getProviderId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = this.getObjProfile(rs);
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public DivulgationPage read(int pid) throws DAOException {
        String query = "SELECT * FROM spn.divulgation_page prof NATURAL JOIN spn.provider prov "
                + "WHERE prov.status=true AND prof.provider_id=? LIMIT 1";
        DivulgationPage p = null;
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
    public void update(DivulgationPage p) throws DAOException {
        try {
            String query = "UPDATE spn.divulgation_page SET description=? WHERE profile_id = ?;";

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
        try {
            String query = "DELETE FROM spn.provider WHERE provider_id=?";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
           throw new DAOException(ex.getMessage(),ex.getCause());
        }
    }

    private DivulgationPage getObjProfile(ResultSet rs) throws SQLException {
        DivulgationPage p = new DivulgationPage();
        p.setProfileId(rs.getInt("profile_id"));
        p.setDescription(rs.getString("description"));
        p.setProviderId(rs.getInt("provider_id"));
        return p;
    }
}
