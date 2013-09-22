package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.DAOFactory;
import br.uel.database.ProviderDAO;
import br.uel.entity.ProviderSought;
import br.uel.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doSearchProvider extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("show")) {
            Show();
        } else if (m.equalsIgnoreCase("searchByCategory")) {
            searchByCategory();
        } else if (m.equalsIgnoreCase("option3")) {
        }

    }

    private void Show() {
        doCategoryCrud action = new doCategoryCrud(request, response);
        action.list();
        templateView.setContent("searchProvider").setTitle("Buscar Prestadores").setMessage("Faça os filtros necessário para sua busca");
        super.dispatcher();
    }

    private void searchByCategory() {
        int catId = Integer.parseInt(request.getParameter("catId"));
        User u =  (User)request.getSession().getAttribute("user");
        ProviderDAO pDao;
        DAOFactory factory = DAOFactory.getDAOFactory();
        pDao = (ProviderDAO) factory.getDAOObject(DAOFactory.DAODataType.ProviderDAO);
        List<ProviderSought> ps = pDao.searchByCategory(u.getUserId(), catId, 10, 0);
        request.setAttribute("providersSought", ps);
        templateView.setContent("resultsSearchProvider");
        super.dispatcher();
    }
}
