/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ProviderDAO;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doCategoryConnect extends Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("add")) {
            Logger.getInstance().setLog("entrou no add");
            Add();
            Logger.getInstance().setLog("saiu do add");
            
        } else if (m.equalsIgnoreCase("del")) {
             Logger.getInstance().setLog("entrou no add");
            Del();
             Logger.getInstance().setLog("entrou no add");
        }
    }

    private void Add() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int catId = Integer.parseInt(request.getParameter("catId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.connect(user.getUserId(), catId);
//        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }

    private void Del() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int catId = Integer.parseInt(request.getParameter("catId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.disconnect(user.getUserId(), catId);
//        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }
}

