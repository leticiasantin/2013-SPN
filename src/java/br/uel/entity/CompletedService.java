/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author leticia
 */
public class CompletedService {
    private Service service;
    private ServiceEvaluation serviceEvaluation;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceEvaluation getServiceEvaluation() {
        return serviceEvaluation;
    }

    public void setServiceEvaluation(ServiceEvaluation serviceEvaluation) {
        this.serviceEvaluation = serviceEvaluation;
    }
    
    
    
}
