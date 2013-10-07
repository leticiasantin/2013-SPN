/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.PictureDAO;
import br.uel.entity.Picture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leticia
 */
public class PgPictureDAO extends PictureDAO {

    public PgPictureDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void createClientPicture(Picture picture) {
        try {
            String query = "INSERT INTO spn.service_client_picture(service_id, path)"
                    + " VALUES (?,?) RETURNING picture_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, picture.getServiceId());
            ps.setString(2, picture.getImage());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                picture.setPictureId(rs.getInt("picture_id"));
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void createProviderPicture(Picture picture) {
        try {
            String query = "INSERT INTO spn.service_provider_picture(service_id, path)"
                    + " VALUES (?,?) RETURNING picture_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, picture.getServiceId());
            ps.setString(2, picture.getImage());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                picture.setPictureId(rs.getInt("picture_id"));
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void createDivulgationPagePicture(Picture picture) {
        try {
            String query = "INSERT INTO spn.divulgation_picture (page_id, path)"
                    + " VALUES ( ?, ?) RETURNING picture_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, picture.getServiceId());
            ps.setString(2, picture.getImage());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                picture.setPictureId(rs.getInt("picture_id"));
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public List<Picture> divulgationPagePictureList(int pageId) {
        List<Picture> pictures = new ArrayList();
        try {
            String query = "SELECT page_id,picture_id"
                    + " FROM spn.divulgation_picture"
                    + " WHERE page_id=?;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Picture pic = new Picture();
                pic.setPageId(rs.getInt("page_id"));
                pic.setPictureId(rs.getInt("picture_id"));
                pictures.add(pic);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PgPictureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pictures;
    }

    @Override
    public void clientPictureList(int serviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void providerPictureList(int serviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
