/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.CategoryDAO;
import br.uel.database.DAOException;
import br.uel.entity.Category;
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
public class PgCategoryDAO extends CategoryDAO {

    PgCategoryDAO(PgDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Category c) throws DAOException {
        try {
            if (c.getParentId() != 0) {
                String query = "INSERT INTO spn.category("
                        + " parent_id, name)"
                        + " VALUES (?, ?)";

                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, c.getParentId());
                ps.setString(2, c.getName());
                ps.execute();
                conn.close();
            } else {
                String query = "INSERT INTO spn.category("
                        + " name)"
                        + " VALUES (?)";
                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, c.getName());
                ps.execute();
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Category read(int cid) throws DAOException {
        Category c = null;
        try {

            String query = "SELECT * FROM spn.category"
                    + " WHERE cat_id=?";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = this.getObjCategory(rs);
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return c;

    }

    @Override
    public void update(Category c) throws DAOException {
        try {
            PreparedStatement ps;
            if (c.getParentId() == 0) {

                String query = "UPDATE spn.category "
                        + "SET  name=? "
                        + "WHERE cat_id=?";
                Connection conn = daoFactory.getConnection();

                ps = conn.prepareStatement(query);

                ps.setString(1, c.getName());
                ps.setInt(2, c.getCatId());

            } else {
                String query = "UPDATE spn.category "
                        + "SET parent_id=?, name=? "
                        + "WHERE cat_id=?";
                Connection conn = daoFactory.getConnection();
                ps = conn.prepareStatement(query);
                ps.setInt(1, c.getParentId());
                ps.setString(2, c.getName());
                ps.setInt(3, c.getCatId());
            }


            int rowCount = ps.executeUpdate();
            if (rowCount < 1) {
                throw new DAOException("Usuário inexistente");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void delete(int cid) throws DAOException {
        try {
            String query = "DELETE FROM spn.category WHERE cat_id = ?;";
            Connection conn = daoFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cid);

            int rowCount = ps.executeUpdate();
            if (rowCount < 1) {
                throw new DAOException("Usuário inexistente");
            }

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Category getObjCategory(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setCatId(rs.getInt("cat_id"));
        c.setParentId(rs.getInt("parent_id"));
        c.setName(rs.getString("name"));
        return c;
    }

    /**
     *
     * @param pid
     * @return retorna uma lista contendo todas as categorias do Prestador
     * @throws DAOException
     */
    @Override
    public List<Category> readByIdProvider(int pid) throws DAOException {
        List<Category> categories = new ArrayList();
        try {
            String query = "SELECT * FROM spn.category NATURAL JOIN "
                    + " (SELECT * FROM spn.provider NATURAL JOIN spn.prov_has_cat ) AS pro"
                    + " WHERE provider_id=?";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(this.getObjCategory(rs));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n\n\n\n\n categorias size:"+categories.size()+"<");
        return categories;
    }

    @Override
    public List<Category> list() throws DAOException {
        List<Category> categories = new ArrayList();
        try {
            String query = "SELECT * FROM spn.category ORDER BY name asc;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getObjCategory(rs);
                categories.add(c);
            }
            conn.close();


        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }

    @Override
    public List<Category> listOfAvailableCategories() throws DAOException {
        List<Category> categories = new ArrayList();
        try {
            String query = "SELECT * FROM spn.category "
                    + " WHERE cat_id NOT IN "
                    + " (SELECT DISTINCT parent_id FROM spn.category"
                    + " WHERE parent_id IS NOT NULL) ORDER BY name;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getObjCategory(rs);
                categories.add(c);
            }
            conn.close();


        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public List<Category> list(int userId) {
        List<Category> categories = new ArrayList();
        try {
            String query = "SELECT * FROM spn.provider NATURAL JOIN "
                    + " (spn.prov_has_cat NATURAL JOIN spn.category)"
                    + " WHERE provider_id=? ORDER BY name;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getObjCategory(rs);
                categories.add(c);
            }
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public List<Category> listOfAvailableCategories(int userId) {

        List<Category> categories = new ArrayList();
        try {
            String query = "SELECT * FROM spn.category"
                    + " WHERE cat_id NOT IN "
                    + " (SELECT cat_id FROM spn.provider NATURAL JOIN "
                    + " (spn.prov_has_cat NATURAL JOIN spn.category) "
                    + " WHERE provider_id=?)";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = getObjCategory(rs);
                categories.add(c);
            }
            conn.close();


        } catch (SQLException ex) {
            Logger.getLogger(PgCategoryDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
}
