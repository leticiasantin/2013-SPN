/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.CategoryDAO;
import br.uel.database.DAOFactory;
import br.uel.database.ProfileDAO;
import br.uel.entity.Category;
import br.uel.entity.Profile;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doLoadProfile extends Command {

 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        /*
         * manda o id usuario a ser visitado o profile
         */
        if (!request.getParameter("uid").isEmpty()) {
            userId = Integer.parseInt(request.getParameter("uid"));
        } /*
         * carregando próprio profile
         */ else {
            User u = (User) request.getSession().getAttribute("user");
            userId = u.getUserId();
        }

        ProfileDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();

        pDao = (ProfileDAO) factory.getDAOObject(DAOFactory.DAODataType.ProfileDAO);
        Profile profile = pDao.read(userId);
        /*
         * Usuario é também prestador
         */
        if (profile != null) {
            /*
             * Carrega as categorias do provider
             */
            CategoryDAO cDao;
            cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
            List<Category> list = cDao.readByIdProvider(userId);
            if (!list.isEmpty()) {
                profile.setCategories(list);
            }
            request.setAttribute("profile", profile);
        }


        /*
         * redirecionando para a págine de profiles
         */
        RequestDispatcher rdisp = request.getRequestDispatcher("profile.jsp");
        rdisp.forward(request, response);
    }
}
