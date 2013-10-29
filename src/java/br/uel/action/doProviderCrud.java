package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ProviderDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leticia
 */
public class doProviderCrud extends Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;

        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("add")) {
            addProfile();
        } else if (m.equalsIgnoreCase("del")) {
            deleteProfile();
        } 
    }

    private void addProfile() {
        int pId = Integer.parseInt(request.getParameter("pId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.create(pId);
    }

    private void deleteProfile() {
        int pId = Integer.parseInt(request.getParameter("pId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.delete(pId);
    }

}
