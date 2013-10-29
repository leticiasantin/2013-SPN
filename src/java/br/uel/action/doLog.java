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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leticia
 */
public class doLog extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       super.init(request, response);
        String m = request.getParameter("m");
        if (m.equals("login")){
            doLogin();
        } else if (m.equals("logout")) {
            doLogout();
        } else {
            doLogout();
        }
        
    }

    private void doLogin() throws ServletException, IOException {
        super.init(request, response);
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //instanciar usuario
        User u = new User();
        //setar usuario
        u.setLogin(request.getParameter("login"));
        u.setPassword(request.getParameter("password"));
        String message = null;
        //autentica usuario e seta a sessao
        if (Authenticator.authenticate(u)) {
            UserDAO uDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            u = uDao.readByLogin(u.getLogin());
            if (!u.getStatus()){
                uDao.updateStatus(u.getUserId());
                message = "Que bom que voltou";
            }
            String content = "userWelcome";
            String menu = "userMenu";
            String header = "userHeader";
              if (u.getLogin().equalsIgnoreCase("admin")){
                content = "adminWelcome";
                menu = "adminMenu";
            }
              Logger.getInstance().setLog("cotent = "+content + " menu : "+menu+ " header: "+header);
            session.setAttribute("user", u);
            session.setAttribute("view", templateView);
            //set template view           
            this.templateView.setTitle("Página Inicial").setMenu(menu).setHeader(header).setContent(content).setMessage(message);
        }
        else {
            this.templateView.setTitle("error").setMenu(null).setContent("error").setMessage("Erro no Login").setFooter("");
        }
        super.dispatcher();
    }

    private void doLogout() throws ServletException, IOException {
        request.getSession().invalidate();
        templateView.clearAttributes().setGuestAttributes().setContent("index");
        super.dispatcher();
    }
    
}
