/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceDAO;
import br.uel.entity.Service;
import br.uel.entity.User;
import java.util.List;

/**
 *
 * @author leticia
 */
public class PgServiceDAO extends ServiceDAO{

    public PgServiceDAO(DAOFactory daoFactory_) {
          daoFactory = daoFactory_;
    }
    

    @Override
    public void create(Service s) throws DAOException {
      }

    @Override
    public List<Service> list(int userId) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User read(int sid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Service s) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int sid) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean serviceExistsById(int sid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
