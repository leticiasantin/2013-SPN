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

        String query = "INSERT INTO spn.user(login, password, fname) VALUES (?, ?, ?) RETURNING uid";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                u.setUid(rs.getInt("uid"));
                Logger.getInstance().setLog("User (id " + u.getUid() + ") created successfully");
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
                        + "SET login=?, password=?, fname=? "
                        + "WHERE uid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            Logger.getInstance().setLog("hello");
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getName());
            ps.setInt(4, u.getUid());
            Logger.getInstance().setLog("Attempt User (id " + u.getUid() + ") updated successfully");
            //ps.executeQuery();
            ps.executeUpdate();
            Logger.getInstance().setLog("User (id " + u.getUid() + ") updated successfully");
            return true;
        } catch (Exception ex) {
            Logger.getInstance().setLog("--SQL Exception in:" + PgUserDAO.class.getName() + ' ' + ex.getMessage());
        }
        return false;
    }
}