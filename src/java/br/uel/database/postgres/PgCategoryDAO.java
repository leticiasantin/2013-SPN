/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.CategoryDAO;
import br.uel.database.DAOException;
import br.uel.entity.Category;
import br.uel.entity.ProviderSought;
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
            throw new DAOException(ex.getMessage(), ex.getCause());
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
                    + " (SELECT * FROM spn.provider NATURAL JOIN spn.prov_has_cat ) "
                    + "AS pro WHERE provider_id=?";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(this.getObjCategory(rs));
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        System.out.println("\n\n\n\n\n categorias size:" + categories.size() + "<");
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
            throw new DAOException(ex.getMessage(), ex.getCause());
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
            throw new DAOException(ex.getMessage(), ex.getCause());
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
            throw new DAOException(ex.getMessage(), ex.getCause());
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
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return categories;
    }

    @Override
    public List<ProviderSought> listOfTopsByPrice(int catId, int nRows) {
        return this.executeQuery("price", catId, nRows);
    }

    @Override
    public List<ProviderSought> listOfTopsByDeadlines(int catId, int nRows) {
        return this.executeQuery("deadlines", catId, nRows);
    }

    @Override
    public List<ProviderSought> listOfTopsByQoS(int catId, int nRows) {
        return this.executeQuery("qos", catId, nRows);
    }

    @Override
    public List<ProviderSought> listOfTopsByQoC(int catId, int nRows) {
        return this.executeQuery("qoc", catId, nRows);
    }

    @Override
    public List<ProviderSought> listOfTopsByLastDays(int catId, int days, int nRows) {
        List<ProviderSought> list = new ArrayList();
        try {
            String query = "SELECT top.*, u.name, u.city, u.state FROM "
                    + "spn.gettopcategorybydate(?,?,?) top INNER JOIN "
                    + "spn.user u ON provider=user_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, catId);
            ps.setInt(2, days);
            ps.setInt(3, nRows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProviderSought prS = new ProviderSought();
                prS.setId(rs.getInt("provider"));
                prS.setName(rs.getString("name"));
                prS.setCity(rs.getString("city"));
                prS.setState(rs.getString("state"));
                list.add(prS);
            }
            conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<ProviderSought> listOfTopsByLinearComb(int catId, int nRows, int w[]) {
        ArrayList<ProviderSought> list = new ArrayList();
        try {
           
            String query = "SELECT top.*, u.name, u.city, u.state FROM "
                        + "spn.gettopcategorybylinearcombination(?,?,?,?,?,?) top INNER JOIN "
                        + "spn.user u ON provider=user_id  ORDER BY lin_comb DESC";
                Connection conn = daoFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, catId);
                ps.setInt(2, nRows);
                ps.setInt(3, w[0]);
                ps.setInt(4, w[1]);
                ps.setInt(5, w[2]);
                ps.setInt(6, w[3]);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProviderSought prS = new ProviderSought();
                    prS.setId(rs.getInt("provider"));
                    prS.setName(rs.getString("name"));
                    prS.setCity(rs.getString("city"));
                    prS.setState(rs.getString("state"));
                    list.add(prS);
                }
                conn.close();
         
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           return list;
        
            }

    /**
     *
     * @param catId
     * @param criterion
     * @param nRows
     * @return Query para os metodos que lista tops;
     */
    private String generateQuery(String criterion) {
        if (criterion.equalsIgnoreCase("price")) {
            return "SELECT top.*, u.name, u.city, u.state FROM "
                    + "spn.gettopcategorybycriterion(?,?,1) top INNER JOIN "
                    + "spn.user u ON provider=user_id";
        } else if (criterion.equalsIgnoreCase("deadlines")) {
            return "SELECT top.*, u.name, u.city, u.state FROM "
                    + "spn.gettopcategorybycriterion(?,?,2) top INNER JOIN "
                    + "spn.user u ON provider=user_id";
        } else if (criterion.equalsIgnoreCase("qos")) {
            return "SELECT top.*, u.name, u.city, u.state FROM "
                    + "spn.gettopcategorybycriterion(?,?,3) top INNER JOIN "
                    + "spn.user u ON provider=user_id";
        } else if (criterion.equalsIgnoreCase("qoc")) {
            return "SELECT top.*, u.name, u.city, u.state FROM "
                    + "spn.gettopcategorybycriterion(?,?,4) top INNER JOIN "
                    + "spn.user u ON provider=user_id";
        }
        return null;
    }

    private List<ProviderSought> executeQuery(String criterion, int catId, int nRows) {
        List<ProviderSought> list = new ArrayList();
        try {
            String query = this.generateQuery(criterion);
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, catId);
            ps.setInt(2, nRows);
        
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProviderSought prS = new ProviderSought();
                prS.setId(rs.getInt("provider"));
                prS.setName(rs.getString("name"));
                prS.setCity(rs.getString("city"));
                prS.setState(rs.getString("state"));
                prS.setCatId(rs.getInt(criterion));
                list.add(prS);
            }
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
        return list;
    }

    @Override
    public int getNProviders(int catId) {
           int n =0;
        try {
         
             String query = "SELECT COUNT(*) FROM spn.prov_has_cat WHERE cat_id=?";
                    Connection conn = daoFactory.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setInt(1, catId);
                    ResultSet rs = ps.executeQuery();
                    if  (rs.next()){ 
                      n = rs.getInt(1);
                    }
                    conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                return n;
    }
}
