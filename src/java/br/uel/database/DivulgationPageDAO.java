/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.DivulgationPage;

/**
 *
 * @author leticia
 */
public abstract class DivulgationPageDAO {

    protected DAOFactory daoFactory;

    public abstract void create(DivulgationPage p) throws DAOException;

    public abstract DivulgationPage read(int pid) throws DAOException;

    public abstract void update(DivulgationPage p) throws DAOException;

    public abstract void delete(int pid) throws DAOException;
    
}
