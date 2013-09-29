package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceDAO;
import br.uel.entity.Service;
import br.uel.entity.User;
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
            ListPendingEvaluation();
        } else if (m.equalsIgnoreCase("option2")) {
        } else if (m.equalsIgnoreCase("option3")) {
        }

    }

    private void ListPendingEvaluation() {
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
}
