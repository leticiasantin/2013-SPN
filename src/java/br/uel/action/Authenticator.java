package br.uel.action;

import br.uel.database.DAOFactory;
import br.uel.database.UserDAO;
import br.uel.entity.User;

/**
 * @author Vanessa
 */
public class Authenticator {
    public static boolean authenticate(User u) {
            UserDAO uDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            return uDao.userExists(u.getLogin(),u.getPassword());
    }
}
