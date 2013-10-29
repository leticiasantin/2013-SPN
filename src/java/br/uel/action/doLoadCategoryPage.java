package br.uel.action;

import br.uel.controller.Command;
import br.uel.database.CategoryDAO;
import br.uel.database.DAOFactory;
import br.uel.entity.Category;
import br.uel.entity.ProviderSought;
import br.uel.log.Logger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class doLoadCategoryPage extends Command {

    public doLoadCategoryPage(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response);
    }

    public doLoadCategoryPage() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        int catId = Integer.parseInt(request.getParameter("catId"));
        if (catId > 0) {  
            DAOFactory factory = DAOFactory.getDAOFactory();
            CategoryDAO catDao = (CategoryDAO) factory.getDAOObject(DAOFactory.DAODataType.CategoryDAO);
          
            Category category = catDao.read(catId);
            if (category != null){
                //Carrega as Listas
                ArrayList<ProviderSought> topsByPrice = (ArrayList<ProviderSought>) 
                        catDao.listOfTopsByPrice(catId, 5);
                ArrayList<ProviderSought> topsByDeadlines = (ArrayList<ProviderSought>) 
                        catDao.listOfTopsByDeadlines(catId, 5);
                ArrayList<ProviderSought> topsByQoS = (ArrayList<ProviderSought>) 
                        catDao.listOfTopsByQoS(catId, 5);
                ArrayList<ProviderSought> topsByQoC = (ArrayList<ProviderSought>) 
                        catDao.listOfTopsByQoC(catId, 5);
                ArrayList<ProviderSought> topsLastDays = (ArrayList<ProviderSought>) 
                        catDao.listOfTopsByLastDays(catId, 7,5);
                ArrayList<ProviderSought> topsByLinComb = (ArrayList<ProviderSought>) catDao.listOfTopsByLinearComb(catId,5,this.getWeights());
                request.setAttribute("nProviders", catDao.getNProviders(catId));
                
                request.setAttribute("category", category);
                request.setAttribute("topsByPrice", topsByPrice);
                request.setAttribute("topsByDeadlines", topsByDeadlines);
                request.setAttribute("topsByQoS", topsByQoS);
                request.setAttribute("topsByQoC", topsByQoC);
                request.setAttribute("topsLastDays", topsLastDays);
                request.setAttribute("topsByLinComb", topsByLinComb);
                
                templateView.setContent("categoryPage");
            }
            else {
                templateView.setContent("index");
            }
        }
     
        super.dispatcher();


    }

    private int[] getWeights() {
        Logger.getInstance().setLog("getWeights : " + request.getParameter("w1"));
        int w[];
        w = new int[4];
        for (int i=1; i<=4; i++){
            String param = "w"+i;
            if (request.getParameter(param) == null || request.getParameter(param).isEmpty()){
                w[i-1] = 1;
            } 
        
            else{
                w[i-1] = Integer.parseInt(request.getParameter(param));
            }
        }
        return w;
    }
}
