package br.uel.database;

import br.uel.entity.ServiceEvaluation;

/**
 *
 * @author leticia
 */
public abstract class ServiceEvaluationDAO {

    protected DAOFactory daoFactory;

    public abstract void create(ServiceEvaluation se);

    public abstract void update(ServiceEvaluation se);

  
}
