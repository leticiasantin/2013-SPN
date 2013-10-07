package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceDAO;
import br.uel.database.ServiceEvaluationDAO;
import br.uel.entity.Service;
import br.uel.entity.ServiceEvaluation;
import br.uel.entity.User;
import br.uel.log.Logger;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doServiceEvaluation extends Command {
    
    public doServiceEvaluation(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response);
    }
    
    public doServiceEvaluation() {
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("list")) {
            listPendingEvaluation();
        } else if (m.equalsIgnoreCase("clientEvaluation")) {
            setClientEvaluation();
        } else if (m.equalsIgnoreCase("providerEvaluation")) {
            setProviderEvaluation();
        }
        else if(m.equals("form")) {
            setForm();
        }
    }
    
    private void listPendingEvaluation() {
        User u = (User) request.getSession().getAttribute("user");
        ServiceDAO sDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sDao = (ServiceDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceDAO);
        List<Service> asClient = sDao.listNotAssessedClient(u.getUserId());
        List<Service> asProvider = sDao.listNotAssessedProvider(u.getUserId());
        request.setAttribute("asClient", asClient);
        request.setAttribute("asProvider", asProvider);
        templateView.setContent("evaluationPendencies");
        super.dispatcher();
    }
    
    private void setClientEvaluation() {
        User u = (User) request.getSession().getAttribute("user");
        ServiceEvaluation se = this.getObjServiceEvaluaton();
                Logger.getInstance().setLog("teste date start:"+se.getcRealStartDate() + " finish: "
                        + se.getcRealFinishDate());
        ServiceEvaluationDAO sEvDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sEvDao = (ServiceEvaluationDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceEvaluationDAO);
        sEvDao.create(se);
    }
    
    private void setProviderEvaluation() {
        User u = (User) request.getSession().getAttribute("user");
        ServiceEvaluation se = this.getObjServiceEvaluaton();
        ServiceEvaluationDAO sEvDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sEvDao = (ServiceEvaluationDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceEvaluationDAO);
        sEvDao.update(se);
    }
    
    private ServiceEvaluation getObjServiceEvaluaton() {
        ServiceEvaluation sEv = new ServiceEvaluation();
        if (request.getParameter("serviceId") != null) {
            sEv.setServiceId(Integer.parseInt(request.getParameter("serviceId")));
        }
        if (request.getParameter("cPrice") != null) {
            sEv.setcPrice(Integer.parseInt(request.getParameter("cPrice")));
        }
        if (request.getParameter("cRespectForDeadlines") != null) {
            sEv.setcRespectForDeadlines(Integer.parseInt(request.getParameter("cRespectForDeadlines")));
        }
        if (request.getParameter("cQualityOfService") != null) {
            sEv.setcQualityOfService(Integer.parseInt(request.getParameter("cQualityOfService")));
        }
        if (request.getParameter("cQualityOfCare") != null) {
            sEv.setcQualityOfCare(Integer.parseInt(request.getParameter("cQualityOfCare")));
        }
        if (request.getParameter("cComment") != null) {
            sEv.setcComment(request.getParameter("cComment"));
        }
        if (request.getParameter("cRealStartDate") != null) {
            sEv.setcRealStartDate(request.getParameter("cRealStartDate"));
        }
        if (request.getParameter("cRealFinishDate") != null) {
            sEv.setcRealFinishDate(request.getParameter("cRealFinishDate"));
        }
        if (request.getParameter("pAppropriatePayment") != null) {
            sEv.setpAppropriatePayment(Integer.parseInt(request.getParameter("pAppropriatePayment")));
        }
        if (request.getParameter("pMaterialsSupply") != null) {
            sEv.setpMaterialsSupply(Integer.parseInt(request.getParameter("pMaterialsSupply")));
        }
        if (request.getParameter("pComunicationWithClient") != null) {
            sEv.setpComunicationWithClient(Integer.parseInt(request.getParameter("pComunicationWithClient")));
        }
        if (request.getParameter("pComment") != null) {
            sEv.setpComment(request.getParameter("pComment"));
        }
        return sEv;
    }

    private void setForm() {
        templateView.setContent("evaluation");
        super.dispatcher();
        
      }
}
