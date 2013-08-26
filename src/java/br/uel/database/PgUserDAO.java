/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.log.Logger;
import br.uel.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author dskaster
 */
public class PgUserDAO extends UserDAO {

    public PgUserDAO(Connection _conn) {
        conn = _conn;
    }

    @Override
    public boolean create(User u) {
        Logger.getInstance().setLog("creating User...");


        String query = "INSERT INTO spn.user(name, login, password, dt_of_birth,"
                + " city, state, street, number, complements, neighborhood, zip_code)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id";
        try {
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
            if (rs.next()) {
                u.setUserId(rs.getInt("user_id"));
                Logger.getInstance().setLog("User (id " + u.getUserId() + ") created successfully");
                return true;
            }
        } catch (Exception ex) {
            Logger.getInstance().setLog("SQL Exception in:" + PgUserDAO.class.getName() + ' ' + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(User u) {
        Logger.getInstance().setLog("updating User...");

        String query = "UPDATE  spn.user "
                        + "SET login=?, password=?, name=? "
                        + "WHERE user_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            Logger.getInstance().setLog("hello");
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getName());
            ps.setInt(4, u.getUserId());
            Logger.getInstance().setLog("Attempt User (id " + u.getUserId() + ") updated successfully");
            //ps.executeQuery();
            ps.executeUpdate();
            Logger.getInstance().setLog("User (id " + u.getUserId() + ") updated successfully");
            return true;
        } catch (Exception ex) {
            Logger.getInstance().setLog("--SQL Exception in:" + PgUserDAO.class.getName() + ' ' + ex.getMessage());
        }
        return false;
    }
}