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
public class  doServiceCrud extends Command{
     public doServiceCrud(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response);
    }
     
     public doServiceCrud() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       super.init(request, response);
         String m = request.getParameter("m");
        if (m.equalsIgnoreCase("pendeciesList")) {
            PendenciesList();
        } else if (m.equalsIgnoreCase("option2")) {
        } else if (m.equalsIgnoreCase("option3")) {
        } 
    }

    private void PendenciesList() {
        // Como prestador 
        User u = (User) request.getSession().getAttribute("user");
        ServiceDAO sDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        sDao = (ServiceDAO) factory.getDAOObject(DAOFactory.DAODataType.ServiceDAO);
        List<Service> list = sDao.pendencesListAsProvider(u.getUserId());
        request.setAttribute("providerPendencies", list);
        templateView.setContent("pendencies").setMessage("Você tem algumas pendencias");
        super.dispatcher();
    }
    
}
