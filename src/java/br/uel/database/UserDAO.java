/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.User;
import java.sql.Connection;

/**
 *
 * @author dskaster
 */
public abstract class UserDAO {
    
    Connection conn;
    
    public abstract boolean create(User u);
    public abstract boolean update(User u);

    public boolean readByLogin(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
