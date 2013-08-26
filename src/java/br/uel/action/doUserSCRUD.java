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
import javax.servlet.RequestDispatcher;
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
        char m = request.getParameter("m").toCharArray()[0];
        switch (m) {
            case 's': //create or update
                Logger.getInstance().setLog("attempt to save user");
                Save();
                Logger.getInstance().setLog("succesfully to save user ");
                break;
            case 'r':
             break;
            case 'd':
                break;
        }
    }

    
        public void Save() {
        try {
            User u = this.getObjUser();
            if (this.ValidateUserData(u)){

            UserDAO uDao;
            
            DAOFactory factory = (DAOFactory) request.getAttribute("conn");
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);

            if (u.getUserId() == null) {
                Logger.getInstance().setLog(" create ");
                uDao.create(u);
            } else {
                Logger.getInstance().setLog(" update " + u.getUserId());
                uDao.update(u);
            }

            //redirecionar
            request.setAttribute("user", u);
            }
            RequestDispatcher rdisp = request.getRequestDispatcher("welcome.jsp"); //"welcome-scriptlet.jsp"
            rdisp.forward(request, response);
        } catch (Exception ex) {
            Logger.getInstance().setLog(ex.getMessage());
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
            u.setState(String.valueOf(request.getParameter("zipcode")));
        }
        if (!request.getParameter("neighborhood").isEmpty()) {
            u.setState(String.valueOf(request.getParameter("neighborhood")));
        }
        if (!request.getParameter("number").isEmpty()) {
            u.setState(String.valueOf(request.getParameter("number")));
        }
        if (!request.getParameter("city").isEmpty()) {
            u.setState(String.valueOf(request.getParameter("city")));
        }
        
        return u;
    }
    


    private boolean ValidateUserData(User u) {
        Logger.getInstance().setLog("validing data");
        return true;
    }

   

}
