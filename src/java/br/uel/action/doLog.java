/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.UserDAO;
import br.uel.entity.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leticia
 */
public class doLog implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
                //limpar objeto User possivelmente existente na session
        this.request = request;
        this.response = response;
        String m = request.getParameter("m");
        if (m.equals("login")){
            doLogin();
        }
        else 
            if (m.equals("logout")){
                
            }
            else{
                
            }
      }

    private void doLogin() throws ServletException, IOException {
       HttpSession session = request.getSession();
        session.removeAttribute("user");
        //instanciar usuario
        User u = new User();
        //setar usuario
        u.setLogin(request.getParameter("login"));
        u.setPassword(request.getParameter("password"));
       
        //autentica usuario e seta a sessao
        if (Authenticator.authenticate(u)) {
            UserDAO uDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            u = uDao.readByLogin(u.getLogin());
            session.setAttribute("user", u);
        }
        //redirecionar
        RequestDispatcher rdisp = request.getRequestDispatcher("welcome.jsp"); //"welcome-scriptlet.jsp"
        rdisp.forward(request, response);
    }
    
}
