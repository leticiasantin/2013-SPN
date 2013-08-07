/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.database.DAOFactory;
import br.uel.database.UserDAO;
import br.uel.entity.User;
import br.uel.log.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author leticia
 */
public class doUserSCRUD extends doSCRUD {

    @Override
    public void save() {

        try {
            User u = this.getObjUser();

            UserDAO uDao;

            DAOFactory factory = (DAOFactory) request.getAttribute("conn");
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);

            if (u.getUid() == null) {
                Logger.getInstance().setLog(" create ");
                uDao.create(u);
            } else {
                Logger.getInstance().setLog(" update " + u.getUid());
                uDao.update(u);
            }

            //redirecionar
            request.setAttribute("user", u);
//            request.getSession().setAttribute("user", u);
            RequestDispatcher rdisp = request.getRequestDispatcher("userCrud.jsp"); //"welcome-scriptlet.jsp"
            rdisp.forward(request, response);
        } catch (Exception ex) {
            Logger.getInstance().setLog(ex.getMessage());
        }

    }

    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private User getObjUser() {
        User u = new User();
        if (!request.getParameter("login").isEmpty()) {
            u.setLogin(request.getParameter("login"));
        }
        if (!request.getParameter("name").isEmpty()) {
            u.setName((String) request.getParameter("name"));
        }
        if (!request.getParameter("password").isEmpty()) {
            u.setPassword((String) request.getParameter("password"));
        }
        if (!request.getParameter("uid").isEmpty()) {
            u.setUid(Integer.parseInt(request.getParameter("uid")));
        }
        return u;
    }
}
