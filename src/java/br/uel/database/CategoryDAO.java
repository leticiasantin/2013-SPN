/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Category;
import br.uel.entity.ProviderSought;
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

    public abstract List<Category> list(int userId);

    public abstract List<Category> listOfAvailableCategories(int userId);

    public abstract List<ProviderSought> listOfTopsByPrice(int catId, int nRows);

    public abstract List<ProviderSought> listOfTopsByDeadlines(int catId, int nRows);

    public abstract List<ProviderSought> listOfTopsByQoS(int catId, int nRows);

    public abstract List<ProviderSought> listOfTopsByQoC(int catId, int nRows);

    public abstract List<ProviderSought> listOfTopsByLastDays(int catId, int days, int nRows);
    
     public abstract List<ProviderSought> listOfTopsByLinearComb(int catId, int nRows, int w[]);

    public abstract int getNProviders(int catId);
}
