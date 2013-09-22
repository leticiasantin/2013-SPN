package br.uel.entity;

/**
 *
 * @author leticia
 */
public class Service {
    private int serviceId;
    private int clientId;
    private int providerId;
    private int categoryId;
    private String clientRequestDat;
    private String providerResponseDat;
    private String startDate;
    private String finishDate;
    private String description;
    private boolean status;
    private String reasonForCancellation;
    private String price;
    private String clientName;
    private String clientAddress;
    private String providerName;
    private String providerAddress;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

   
    
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getClientRequestDat() {
        return clientRequestDat;
    }

    public void setClientRequestDat(String clientRequestDat) {
        this.clientRequestDat = clientRequestDat;
    }

    public String getProviderResponseDat() {
        return providerResponseDat;
    }

    public void setProviderResponseDat(String providerResponseDat) {
        this.providerResponseDat = providerResponseDat;
    }
 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public void setReasonForCancellation(String reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Float getFloatPrice() {
        return Float.parseFloat(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
      public String getPrice() {
        return price;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    
    
    
    
}
