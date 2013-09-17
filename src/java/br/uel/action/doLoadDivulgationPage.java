/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.CategoryDAO;
import br.uel.database.DAOFactory;
import br.uel.database.DivulgationPageDAO;
import br.uel.entity.Category;
import br.uel.entity.DivulgationPage;
import br.uel.entity.User;
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
        int userId;
        /*
         * manda o id usuario a ser visitado o profile
         */
        if (!request.getParameter("uid").isEmpty()) {
            userId = Integer.parseInt(request.getParameter("uid"));
            request.setAttribute("owner",false);
        } /*
         * carregando próprio profile
         */ else {
            userId = u.getUserId();
            request.setAttribute("owner", true);
        }
        
        DivulgationPageDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();

        pDao = (DivulgationPageDAO) factory.getDAOObject(DAOFactory.DAODataType.DivulgationPageDAO);
        DivulgationPage profile = pDao.read(userId);
       
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
            if (u.getLogin().equalsIgnoreCase("admin")){
                templateView.setAdminAttributes();
            }
            else{
                templateView.setUserAttributes();
            }
        }
        templateView.setTitle("Perfil de Usuário").setContent("divulgationPageCrud").setFooter(null); 
        request.setAttribute("profile", profile);
        super.dispatcher(); 
    
    }
}
