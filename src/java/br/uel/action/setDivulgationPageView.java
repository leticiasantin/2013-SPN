/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.DivulgationPageDAO;
import br.uel.entity.DivulgationPage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class setDivulgationPageView extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("edit")) {
            Edit();
        }

    }

    private void Edit() {
        int pageId = Integer.parseInt(request.getParameter("profileId"));
        request.setAttribute("profile",this.getPage(pageId));
        templateView.setTitle("Editar Página de Divulgação").setUserAttributes().setContent("divulgationPageCrud").setMessage("Edite seus dados").setFooter(null);
        super.dispatcher();
    }

    private DivulgationPage getPage(int pageId) {
        DivulgationPage dp = new DivulgationPage();
        DAOFactory factory = DAOFactory.getDAOFactory();
        DivulgationPageDAO pageDao = (DivulgationPageDAO) factory.getDAOObject(DAOFactory.DAODataType.DivulgationPageDAO);
        pageDao.read(pageId);
        return dp;
    }
}
