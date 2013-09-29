/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author leticia
 */
public class ServiceEvaluation {
    private int serviceId;
    private int cPrice;
    private int cRespectForDeadlines;
    private int cQualityOfService;
    private int cQualityOfCare;
    private String cComment;
    private int pAppropriatePayment;
    private int pMaterialsSupply;
    private int pComunicationWithClient;
    private String pComment;

    public int getcPrice() {
        return cPrice;
    }

    public void setcPrice(int cPrice) {
        this.cPrice = cPrice;
    }

    public int getcRespectForDeadlines() {
        return cRespectForDeadlines;
    }

    public void setcRespectForDeadlines(int cRespectForDeadlines) {
        this.cRespectForDeadlines = cRespectForDeadlines;
    }

    public int getcQualityOfService() {
        return cQualityOfService;
    }

    public void setcQualityOfService(int cQualityOfService) {
        this.cQualityOfService = cQualityOfService;
    }

    public int getcQualityOfCare() {
        return cQualityOfCare;
    }

    public void setcQualityOfCare(int cQualityOfCare) {
        this.cQualityOfCare = cQualityOfCare;
    }

    public String getcComment() {
        return cComment;
    }

    public void setcComment(String cComment) {
        this.cComment = cComment;
    }

    public int getpAppropriatePayment() {
        return pAppropriatePayment;
    }

    public void setpAppropriatePayment(int pAppropriatePayment) {
        this.pAppropriatePayment = pAppropriatePayment;
    }

    public int getpMaterialsSupply() {
        return pMaterialsSupply;
    }

    public void setpMaterialsSupply(int pMaterialsSupply) {
        this.pMaterialsSupply = pMaterialsSupply;
    }

    public int getpComunicationWithClient() {
        return pComunicationWithClient;
    }

    public void setpComunicationWithClient(int pComunicationWithClient) {
        this.pComunicationWithClient = pComunicationWithClient;
    }

    public String getpComment() {
        return pComment;
    }

    public void setpComment(String pComment) {
        this.pComment = pComment;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    
    
    
    
}
