/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ProfileDAO;
import br.uel.database.ProviderDAO;
import br.uel.database.UserDAO;
import br.uel.entity.Profile;
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
public class doProfileCrud implements Command {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        String m = request.getParameter("m");
        Logger.getInstance().setLog("m:" + m);
        if (m.equalsIgnoreCase("create")) {
            createProfile();
             Logger.getInstance().setLog("succesfully:" + m);
        } else if (m.equalsIgnoreCase("delete")) {
            deleteProfile();
             Logger.getInstance().setLog("succesfully:" + m);
        } else if (m.equalsIgnoreCase("updateAll")) {
            updateAllData();
             Logger.getInstance().setLog("succesfully:" + m);
        }
    }

    private void createProfile() throws ServletException, IOException {
        /*
         * Gera profile pelos dados da requisicao
         */
        Profile profile = getObjProfile();
        /*
         * Cria o Provider no banco
         */
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.create(profile.getProviderId());
        /*
         * Cria o Profile no BD
         */
        ProfileDAO profDao = (ProfileDAO) factory.getDAOObject(DAOFactory.DAODataType.ProfileDAO);
        profDao.create(profile);
        request.getSession().setAttribute("profile", profile);
        request.getRequestDispatcher("profileCrud.jsp").forward(request, response);
    }

    private Profile getObjProfile() {
        Profile p = new Profile();
        //Dados do profile
        if (request.getParameter("pId") != null) {
            p.setProviderId(Integer.parseInt(request.getParameter("pId")));
        }
        if (request.getParameter("description") != null) {
            p.setDescription(request.getParameter("description"));
        }
        if (request.getParameter("profileId") != null) {
            p.setProfileId(Integer.parseInt(request.getParameter("profileId")));
        }
        System.out.print(p.getProfileId() + " " +p.getProviderId() + " " + p.getDescription());
        return p;
    }

    private void deleteProfile() {
        int providerId = Integer.parseInt(request.getParameter("pId"));
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        pDao.delete(providerId);
    }

    private void updateAllData() throws ServletException, IOException {
        int profileId = Integer.parseInt(request.getParameter("profileId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
        /*
         * Verifica se o usuario quer desativar a conta
         */
        if (request.getParameter("delUser") != null) {
            Logger.getInstance().setLog("usuario "+userId+" excluiu sua conta;");
            uDao.deleteByUser(userId);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            /*
             * Se o usuario for prestador de serviços
             */
            if (profileId != 0) {
                Profile p = this.getObjProfile();
                ProfileDAO pDao = (ProfileDAO) factory.getDAOObject(DAOFactory.DAODataType.ProfileDAO);
                pDao.update(p);
            }
            //Carrega o usuario
            User u = new User();
            if (!request.getParameter("login").isEmpty()) {
                u.setLogin(request.getParameter("login"));
            }
            if (!request.getParameter("name").isEmpty()) {
                u.setName((String) request.getParameter("name"));
            }
            if (!request.getParameter("password").isEmpty()) {
                u.setPassword((String) request.getParameter("password"));
            }
            if (!request.getParameter("userId").isEmpty()) {
                u.setUserId(Integer.parseInt(request.getParameter("userId")));
            }
            if (!request.getParameter("complement").isEmpty()) {
                u.setComplement(String.valueOf(request.getParameter("complement")));
            }
            if (!request.getParameter("state").isEmpty()) {
                u.setState(String.valueOf(request.getParameter("state")));
            }
            if (!request.getParameter("zipcode").isEmpty()) {
                u.setZipCode(String.valueOf(request.getParameter("zipcode")));
            }
            if (!request.getParameter("neighborhood").isEmpty()) {
                u.setNeighborhood(String.valueOf(request.getParameter("neighborhood")));
            }
            if (!request.getParameter("number").isEmpty()) {
                u.setNumber(Integer.parseInt(request.getParameter("number")));
            }
            if (!request.getParameter("city").isEmpty()) {
                u.setCity(String.valueOf(request.getParameter("city")));
            }
            if (!request.getParameter("street").isEmpty()) {
                u.setStreet(String.valueOf(request.getParameter("street")));
            }
            if (!request.getParameter("dtOfBirth").isEmpty()) {
                u.setDtOfBirth(String.valueOf(request.getParameter("dtOfBirth")));
            }


            uDao.update(u);
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
          //  request.getRequestDispatcher("profileCrud.jsp").forward(request, response);
        }
    }
}
