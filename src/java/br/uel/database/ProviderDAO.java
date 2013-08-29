/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Provider;
import br.uel.entity.User;
import java.util.List;

/**
 *
 * @author leticia
 */
public abstract class ProviderDAO {
     protected DAOFactory daoFactory;
    
    public abstract void create(Provider p) throws DAOException;
    
    public abstract List<User> list(boolean orderByName) throws DAOException;
    
    public abstract User read(int pid) throws DAOException;
    
    public abstract void update(Provider p) throws DAOException;
    
    public abstract void delete(int uid) throws DAOException;
    
   public abstract boolean providerExistsById(int uid);
    
}
