/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Category;
import java.util.List;

/**
 *
 * @author leticia
 */
public abstract class CategoryDAO {
    
    protected DAOFactory daoFactory;
    
    public abstract void create(Category c) throws DAOException;
  
    public abstract Category read(int cid) throws DAOException;

    public abstract void update(Category c) throws DAOException;

    public abstract void delete(int cid) throws DAOException;
    
    public abstract List<Category> readByIdProvider(int pid) throws DAOException;
    
    public abstract List<Category> list() throws DAOException;
    
    public abstract List<Category> listOfAvailableCategories() throws DAOException;

    public  abstract List<Category> list(int userId) ;

    public abstract List<Category> listOfAvailableCategories(int userId);
    
}
