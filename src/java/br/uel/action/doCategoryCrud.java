/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.CategoryDAO;
import br.uel.database.DAOFactory;
import br.uel.entity.Category;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doCategoryCrud extends Command {

    public doCategoryCrud() {
    }

    
    public doCategoryCrud(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("save")) {
            save(); 
        } else if (m.equalsIgnoreCase("update")) {
            /*
             * Faz o update do nome da categoria
             */
        } else if (m.equalsIgnoreCase("list")) {
            list();
             super.dispatcher();
        } else if (m.equalsIgnoreCase("delete")) {
            delete();

        } else if (m.equalsIgnoreCase("listUserCategories")) {
            listUserCategory();
        }
         else if (m.equalsIgnoreCase("show")) {
            show();
        }
    }

    private void listUserCategory() throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        int userId = u.getUserId();
        CategoryDAO cDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
        /*
         * Categorias do usuario
         */
        List<Category> categories = null;
        categories = cDao.list(userId);
        request.setAttribute("userCategories", categories);
        /*
         * categorias disponiveis para adicionar
         */
        List<Category> availableCategories = null;
        availableCategories = cDao.list();
        request.setAttribute("availableCategories", availableCategories);
        templateView.setTitle("Minhas Categorias").setUserAttributes().setContent("categories").setFooter(null);
        super.dispatcher();

    }

    public void listUserCategory(int userId) throws ServletException, IOException {
        CategoryDAO cDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
        /*
         * Categorias do usuario
         */
        List<Category> categories = null;
        categories = cDao.list(userId);
        request.setAttribute("userCategories", categories);
        /*
         * categorias disponiveis para adicionar
         */

        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }

    private void save() {
        Category category = new Category();
        category.setName(request.getParameter("name"));
        category.setParentId(Integer.parseInt(request.getParameter("parentId")));
        if (request.getParameter("catId") != null && !request.getParameter("catId").isEmpty()){
            category.setCatId(Integer.parseInt(request.getParameter("catId")));
        }
        CategoryDAO cDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
        Logger.getInstance().setLog("category cat id:" + category.getCatId());
        if (category.getCatId() == 0) {
            cDao.create(category);
        } else {
            cDao.update(category);
        }
    }

    public void list() {
            CategoryDAO cDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
            List list = cDao.list();
            request.getSession().setAttribute("catList", list);
            templateView.setContent("categoryCrud").setTitle("Editar Categorias");
    }
    
  
    private void delete() {
        int cId = Integer.parseInt(request.getParameter("catId"));
        CategoryDAO cDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        cDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
        cDao.delete(cId);
    }

    private void show() {
       this.list();
       templateView.setContent("categoriesPage");
       super.dispatcher();
    }
}
