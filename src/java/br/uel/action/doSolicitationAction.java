package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceDAO;
import br.uel.entity.Service;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doSolicitationAction extends Command {

    doSolicitationAction(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response);
    }

    public doSolicitationAction() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("request")) {
            ClientRequest();
        } else if (m.equalsIgnoreCase("reject")) {
            ProviderReject();
        } else if (m.equalsIgnoreCase("accept")) {
           
        }
    }

    private void ClientRequest() {
        Service service = this.getObjService();
        User u = (User) request.getSession().getAttribute("user");
        service.setClientId(u.getUserId());
        ServiceDAO sDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sDao = (ServiceDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceDAO);
        sDao.create(service);
    }

    private Service getObjService() {
        Service service = new Service();
        if (request.getParameter("serviceId") != null) {
            service.setServiceId(Integer.parseInt(request.getParameter("serviceId")));
        }
        if (request.getParameter("clientId") != null) {
            service.setClientId(Integer.parseInt(request.getParameter("clientId")));
        }
        if (request.getParameter("providerId") != null) {
            service.setProviderId(Integer.parseInt(request.getParameter("providerId")));
        }
        if (request.getParameter("description") != null) {
            service.setDescription(request.getParameter("description"));
        }
        if (request.getParameter("reason") != null) {
            service.setReasonForCancellation(request.getParameter("reason"));
        }
          if (request.getParameter("price") != null) {
            String price = request.getParameter("price").replace(',', '.');
            service.setPrice(price);
        }
        
        try {
            if (request.getParameter("startDate") != null) {
                Date DataNascFr = new SimpleDateFormat("dd/MM/yyyy ").parse(request.getParameter("startDate"));  
                service.setStartDate(new SimpleDateFormat("yyyy-mm-dd 00:00:00").format(DataNascFr)); 
            }
           if (request.getParameter("finishDate") != null) {
                Date DataNascFr = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("finishDate"));  
                service.setFinishDate(new SimpleDateFormat("yyyy-mm-dd 00:00:00").format(DataNascFr)); 
            }
        } catch (ParseException ex) {
            Logger.getInstance().setLog("Erro no parser de date");
        }
        
        return service;

    }

    private void ProviderReject() {
        Service service = this.getObjService();
        ServiceDAO sDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sDao = (ServiceDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceDAO);
        sDao.rejectSolicitation(service);
    }

    private void ProviderAccept() {
        Service service = this.getObjService();
        ServiceDAO sDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sDao = (ServiceDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceDAO);
        sDao.acceptSolicitation(service);
       
    }
}