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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doUserSCRUD extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("save")) {
            Save();
        } else if (m.equalsIgnoreCase("read")) {
            Read();
        } else if (m.equalsIgnoreCase("delete")) {
            Delete();
        } else if (m.equalsIgnoreCase("deletedUsers")) {
            listDeletedUsers();
        } else if (m.equalsIgnoreCase("loginExist")) {
            loginExist();
        }


    }

    public void Save() throws ServletException, IOException {
        User u = this.getObjUser();
        request.setAttribute("user", u);
        if (this.ValidateUserData(u)) {
            UserDAO uDao;
            try {
                DAOFactory factory = DAOFactory.getDAOFactory();
                uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
                if (u.getUserId() == null) {
                    Logger.getInstance().setLog(" create ");
                    uDao.create(u);
                    Logger.getInstance().setLog("created");
                } else {
                    //marcou a opção para desativar a conta
                  if (request.getParameter("disable") != null){
                      uDao.deleteByUser(u.getUserId());
                      templateView.setGuestAttributes();
                      super.dispatcher();
                  }
                  else {
                    Logger.getInstance().setLog(" update " + u.getUserId());
                    uDao.update(u);
                  }
                }
                templateView.setTitle("Perfil").setUserAttributes().setContent("userWelcome").setMessage("Usuário Salvo com sucesso").setFooter(null);
                super.dispatcher();
            } catch (Exception ex) {
                templateView.setTitle("erro").setMessage("Erro ao salvar o cadastro").setMenu(null).setContent("error").setFooter(null);
            }
        } 
    }

    private User getObjUser() {
        User u = new User();
        if (request.getParameter("login") != null) {
            u.setLogin(request.getParameter("login"));
        }
        if (request.getParameter("name") != null) {
            u.setName((String) request.getParameter("name"));
        }
        if (request.getParameter("password") != null) {
            u.setPassword((String) request.getParameter("password"));
        }
        if (request.getParameter("userId") != null) {
            u.setUserId(Integer.parseInt(request.getParameter("userId")));
        }
        if (request.getParameter("complement") != null) {
            u.setComplement(String.valueOf(request.getParameter("complement")));
        }
        if (request.getParameter("state") != null) {
            u.setState(String.valueOf(request.getParameter("state")));
        }
        if (request.getParameter("zipcode") != null) {
            u.setZipCode(String.valueOf(request.getParameter("zipcode")));
        }
        if (request.getParameter("neighborhood") != null) {
            u.setNeighborhood(String.valueOf(request.getParameter("neighborhood")));
        }
        if (request.getParameter("number") != null) {
            u.setNumber(Integer.parseInt(request.getParameter("number")));
        }
        if (request.getParameter("city") != null) {
            u.setCity(String.valueOf(request.getParameter("city")));
        }
        if (request.getParameter("street") != null) {
            u.setStreet(String.valueOf(request.getParameter("street")));
        }
        if (request.getParameter("dtOfBirth") != null) {
            u.setDtOfBirth(String.valueOf(request.getParameter("dtOfBirth")));
        }

        return u;
    }

    private boolean ValidateUserData(User u) {
        if (u.getName() == null) {
            return false;
        }
        if (u.getLogin() == null) {
            return false;
        }
        if (u.getPassword() == null) {
            return false;
        }
//        if (u.getState() == null) {
//            return false;
//        }
//        if (u.getNumber().toString() == null) {
//            return false;
//        }
//        if (u.getCity() == null) {
//            return false;
//        }
//        if (u.getStreet() == null) {
//            return false;
//        }
//        if (u.getDtOfBirth() == null) {
//            return false;
//        } else {
       // u.setDtOfBirth(u.getDtOfBirth().replaceAll("/", "-"));
//        }
        return true;
    }

    private void Read() {
        //Auditoria
//        Logger.getInstance().setLog("reading user");
//
//        UserDAO uDao;
//        int userId = Integer.parseInt(request.getParameter("userId"));
//
//        DAOFactory factory = (DAOFactory) request.getAttribute("conn");
//        uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
//
//        uDao.readById(userId);
    }

    private void Delete() {
        //permissao apenas para admin
        User user = (User) request.getSession().getAttribute("user");
        if (user.getLogin().equalsIgnoreCase("admin")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserDAO uDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            uDao.delete(userId);
        }
    }

    private void listDeletedUsers() throws ServletException, IOException {
        //permissao apenas para admin
        User user = (User) request.getSession().getAttribute("user");

        if (user.getLogin().equalsIgnoreCase("admin")) {
            UserDAO uDao;
            DAOFactory factory = DAOFactory.getDAOFactory();
            uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
            List<User> delUsers = uDao.listDeletedUsers();
            request.setAttribute("delUsers", delUsers);
            templateView.setAdminAttributes().setTitle("Usuarios Excluidos").setContent("deletedUsers").setFooter(null);
            super.dispatcher();
        }


    }

    private void loginExist() throws Exception {
        String login = request.getParameter("login");
        UserDAO uDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        uDao = (UserDAO) factory.getDAOObject(DAOFactory.DAODataType.UserDAO);
        if (uDao.userExistsByLogin(login)){
            throw new Exception();
        }
    }
}
