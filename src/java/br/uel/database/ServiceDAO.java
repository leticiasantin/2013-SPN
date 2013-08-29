/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Service;
import br.uel.entity.User;
import java.util.List;

/**
 *
 * @author leticia
 */
public abstract class ServiceDAO {

    protected DAOFactory daoFactory;

    public abstract void create(Service s) throws DAOException;

    public abstract List<Service> list(int userId) throws DAOException;

    public abstract User read(int sid) throws DAOException;

    public abstract void update(Service s) throws DAOException;

    public abstract void delete(int sid) throws DAOException;

    public abstract boolean serviceExistsById(int sid);
}
