/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Profile;

/**
 *
 * @author leticia
 */
public abstract class ProfileDAO {

    protected DAOFactory daoFactory;

    public abstract void create(Profile p) throws DAOException;

    public abstract Profile read(int pid) throws DAOException;

    public abstract void update(Profile p) throws DAOException;

    public abstract void delete(int pid) throws DAOException;
    
}
