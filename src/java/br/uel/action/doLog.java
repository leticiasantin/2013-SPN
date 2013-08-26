/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.entity.User;
import br.uel.log.Logger;
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
        char m = request.getParameter("m").toCharArray()[0];
        switch (m) {
            case 'i': //create or update
                Logger.getInstance().setLog("attempt to save user");
                this.doLogin();
                break;
            case 'r':
                Logger.getInstance().setLog("read from DB");
              
                break;
            case 'd':
                break;
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
