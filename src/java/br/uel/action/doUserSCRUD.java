/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.UserDAO;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doUserSCRUD implements Command {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        
        Logger.getInstance().setLog("execute do doUserSCRUD");
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("save")){
               Save();
        }
        else 
            if (m.equalsIgnoreCase("read")){
                Read();
            }
            else {
             
        }
    }

    public void Save() {
        User u = this.getObjUser();
        if (this.ValidateUserData(u)) {

            UserDAO uDao;

            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);

            if (u.getUserId() == null) {
                Logger.getInstance().setLog(" create ");
                uDao.create(u);
                Logger.getInstance().setLog("created");
            } else {
                Logger.getInstance().setLog(" update " + u.getUserId());
                uDao.update(u);
            }

            //redirecionar
            request.setAttribute("user", u);
        }
        RequestDispatcher rdisp = request.getRequestDispatcher("welcome.jsp"); //"welcome-scriptlet.jsp"
        try {
            rdisp.forward(request, response);
        } catch (ServletException ex) {
            Logger.getInstance().setLog("SErveletException message:" + ex.getMessage() + " cause:" + ex.getCause());
        } catch (IOException ex) {
            Logger.getInstance().setLog("IOException message:" + ex.getMessage() + " cause:" + ex.getCause());

        }

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
        if (!request.getParameter("userId").isEmpty()) {
            u.setUserId(Integer.parseInt(request.getParameter("userId")));
        }
        if (!request.getParameter("complement").isEmpty()) {
            u.setComplement(String.valueOf(request.getParameter("complement")));
        }
        if (!request.getParameter("state").isEmpty()) {
            u.setState(String.valueOf(request.getParameter("state")));
        }
        if (!request.getParameter("zipcode").isEmpty()) {
            u.setZipCode(String.valueOf(request.getParameter("zipcode")));
        }
        if (!request.getParameter("neighborhood").isEmpty()) {
            u.setNeighborhood(String.valueOf(request.getParameter("neighborhood")));
        }
        if (!request.getParameter("number").isEmpty()) {
            u.setNumber(Integer.parseInt(request.getParameter("number")));
        }
        if (!request.getParameter("city").isEmpty()) {
            u.setCity(String.valueOf(request.getParameter("city")));
        }
        if (!request.getParameter("street").isEmpty()) {
            u.setStreet(String.valueOf(request.getParameter("street")));
        }
        if (!request.getParameter("dtOfBirth").isEmpty()) {
            u.setDtOfBirth(String.valueOf(request.getParameter("dtOfBirth")));
        }

        return u;
    }

    private boolean ValidateUserData(User u) {
        u.setDtOfBirth(u.getDtOfBirth().replaceAll("/", "-"));
        return true;
    }

    private void Read() {
        //Auditoria
//        Logger.getInstance().setLog("reading user");
//
//        UserDAO uDao;
//        int userId = Integer.parseInt(request.getParameter("userId"));
//
//        DAOFactory factory = (DAOFactory) request.getAttribute("conn");
//        uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
//
//        uDao.readById(userId);

    }
}
