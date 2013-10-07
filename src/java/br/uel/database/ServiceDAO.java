/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.CompletedService;
import br.uel.entity.Service;
import java.util.List;

/**
 *
 * @author leticia
 */
public abstract class ServiceDAO {
      protected DAOFactory daoFactory;
      public abstract void create (Service service);
      public abstract List<Service> pendencesListAsProvider(int pId);
      public abstract List<Service> pendencesListAsClient(int cId);
      public abstract void rejectSolicitation(Service service);
      public abstract void acceptSolicitation(Service service);
      public abstract List<Service> listNotAssessedClient(int clientId);
      public abstract List<Service> listNotAssessedProvider(int providerId);
      public abstract List<CompletedService> completedServiceClientList(int clienteId);
      public abstract List<CompletedService> completedServiceProviderList(int providerId);
}
