package br.uel.database;

import br.uel.entity.Category;
import br.uel.entity.Provider;
import br.uel.entity.ProviderSought;
import br.uel.entity.User;
import java.util.List;

/**
 * @author leticia
 */
public abstract class ProviderDAO {
     protected DAOFactory daoFactory;
    
    public abstract void create(int pId) throws DAOException;
    
    public abstract List<User> list(boolean orderByName) throws DAOException;
   
    public abstract List<Category> listCat(int provId) throws DAOException;
    
    public abstract User read(int pid) throws DAOException;
    
    public abstract void update(Provider p) throws DAOException;
    
    public abstract void delete(int uid) throws DAOException;
    
   public abstract boolean providerExistsById(int uid);

    public abstract void connect(Integer userId, int catId);

    public abstract void disconnect(Integer userId, int catId) ;
    
    public abstract List<ProviderSought> searchByCategory(int catId, int limit, int offset);

    public abstract List<ProviderSought> searchByKeyWord(String keyword, int limit, int offset);
    
    
}
