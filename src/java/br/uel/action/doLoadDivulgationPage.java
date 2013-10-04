/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.CategoryDAO;
import br.uel.database.DAOFactory;
import br.uel.database.DivulgationPageDAO;
import br.uel.database.UserDAO;
import br.uel.entity.Category;
import br.uel.entity.DivulgationPage;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doLoadDivulgationPage extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        super.init(request, response);
        User u = (User) request.getSession().getAttribute("user");
        int userId = 0;
        /*
         * manda o id usuario a ser visitado o profile
         */
        if (request.getParameter("uid") != null) {
            userId = Integer.parseInt(request.getParameter("uid"));
            if (userId == u.getUserId()) {
                Logger.getInstance().setLog("Pagina do propio usuário");
                request.setAttribute("owner", "true");
            } else {
                Logger.getInstance().setLog("tem parametro");
                templateView.setExtra("extraDivulgationPage");
            }
        }

        DivulgationPageDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();

        pDao = (DivulgationPageDAO) factory.getDAOObject(DAOFactory.DAODataType.DivulgationPageDAO);
        DivulgationPage profile = pDao.read(userId);

        if (profile != null) {
            /*
             * Carrega o usuario dono da pagina
             */
            UserDAO uDao;
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            profile.setUser(uDao.read(userId));
  
            /*
             * Carrega as categorias do provider
             */
            CategoryDAO cDao;
            cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
            List<Category> list = cDao.readByIdProvider(userId);
            if (!list.isEmpty()) {
                profile.setCategories(list);
            }
        }
        templateView.setTitle("Perfil de Usuário").setContent("divulgationPage").setFooter(null);
        request.setAttribute("dPage", profile);
        super.dispatcher();

    }
}
