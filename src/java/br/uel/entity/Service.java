/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author leticia
 */
public class Service {
    private int serviceId;
    private int clientId;
    private int providerId;
    private String cliFbComment;
    private String proFbComment;
    private int cliFbNote;
    private int proFbNote;
    private boolean isCompleted;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getCliFbComment() {
        return cliFbComment;
    }

    public void setCliFbComment(String cliFbComment) {
        this.cliFbComment = cliFbComment;
    }

    public String getProFbComment() {
        return proFbComment;
    }

    public void setProFbComment(String proFbComment) {
        this.proFbComment = proFbComment;
    }

    public int getCliFbNote() {
        return cliFbNote;
    }

    public void setCliFbNote(int cliFbNote) {
        this.cliFbNote = cliFbNote;
    }

    public int getProFbNote() {
        return proFbNote;
    }

    public void setProFbNote(int proFbNote) {
        this.proFbNote = proFbNote;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    
}
