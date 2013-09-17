/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.DivulgationPageDAO;
import br.uel.database.ProviderDAO;
import br.uel.entity.DivulgationPage;
import br.uel.log.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doDivulgationPageCrud extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        Logger.getInstance().setLog("m:" + m);
        if (m.equalsIgnoreCase("create")) {
            createPage();
            Logger.getInstance().setLog("succesfully:" + m);
        } else if (m.equalsIgnoreCase("delete")) {
            deleteProfile();
            Logger.getInstance().setLog("succesfully:" + m);
        } else if (m.equalsIgnoreCase("save")) {
            Save();
        }
    }

    private void createPage() throws ServletException, IOException, DAOException {
        /*
         * Gera pagina pelos dados da requisicao
         */
        DivulgationPage dp = new DivulgationPage();
        dp.setProviderId(Integer.parseInt(request.getParameter("pId")));

        /*
         * Cria o Provider no banco
         */
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.create(dp.getProviderId());
        /*
         * Cria o Profile no BD
         */
        DivulgationPageDAO profDao = (DivulgationPageDAO) factory.getDAOObject(DAOFactory.DAODataType.DivulgationPageDAO);
        profDao.create(dp);
        request.getSession().setAttribute("page", dp);
    }

    private DivulgationPage getObjPage() {
        DivulgationPage p = new DivulgationPage();
        //Dados do profile
        if (request.getParameter("pId") != null) {
            p.setProviderId(Integer.parseInt(request.getParameter("pId")));
        }
        if (request.getParameter("pDescription") != null) {
            p.setDescription(request.getParameter("pDescription"));
        }
        if (request.getParameter("pageId") != null) {
            p.setProfileId(Integer.parseInt(request.getParameter("pageId")));
        }
        System.out.println(p.getProfileId() + p.getProviderId() + p.getDescription());
        return p;
    }

    private void deleteProfile() {
        int providerId = Integer.parseInt(request.getParameter("pId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.delete(providerId);
    }

    private void Save() {
        DivulgationPage dp = this.getObjPage();
        try {
            DAOFactory factory = DAOFactory.getDAOFactory();
            DivulgationPageDAO pageDao = (DivulgationPageDAO) factory.getDAOObject(DAOFactory.DAODataType.DivulgationPageDAO);
            if (request.getParameter("delPage") != null) {
                pageDao.delete(dp.getProviderId());
                templateView.setTitle("Home").setUserAttributes().setContent("userWelcome").setMessage("Sua página foi excluida com sucesso").setFooter(null);
            } else {
                pageDao.update(dp);
                request.setAttribute("dPage", dp);
                templateView.setTitle("Página de divulgação").setUserAttributes().setContent("divulgationPage").setMessage("Página Salva com sucesso").setFooter(null);
            }
        } catch (Exception ex) {
            templateView.setTitle("erro").setMessage("Erro ao salvar o cadastro").setMenu(null).setContent("error").setFooter(null);
        }

        super.dispatcher();
    }
}
