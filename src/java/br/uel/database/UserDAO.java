/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.User;
import java.util.List;

/**
 *
 * @author dskaster
 */
public abstract class UserDAO {

    protected DAOFactory daoFactory;

    public abstract void create(User u) throws DAOException;

    public abstract List<User> list(boolean orderByNome) throws DAOException;

    public abstract User read(int uid) throws DAOException;

    public abstract void update(User u) throws DAOException;

    public abstract void delete(int uid) throws DAOException;

    public abstract boolean userExistsById(int uid);
    
    public abstract boolean userExistsByLogin(String login);

    public abstract boolean userExists(String login, String password);

    public abstract User readByLogin(String login);

    public abstract void deleteByUser(int uid);

    public abstract void updateStatus(int userId);

    public abstract List<User> listDeletedUsers();

   
}
