/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Controller;
import br.uel.entity.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leticia
 */
public class doLogin implements Controller{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
                //limpar objeto User possivelmente existente na session
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        
        //instanciar usuario
        User u = new User();
        
        //setar usuario
        u.setLogin(request.getParameter("login"));
        u.setPassword(request.getParameter("password"));
        br.uel.log.Logger.getInstance().setLog("attempted to login as login:" + u.getLogin() + " and pass: " + u.getPassword());
        //autenticar usuario
        if (Authenticator.authenticate(u)) {
            session.setAttribute("user", u);
        }
        
        //redirecionar
        RequestDispatcher rdisp = request.getRequestDispatcher("welcome.jsp"); //"welcome-scriptlet.jsp"
        rdisp.forward(request, response);
    
    }
    
}
