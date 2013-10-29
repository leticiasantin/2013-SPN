/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author leticia
 */
public class ProviderSought {
    private int id;
    private String userName;
    private int nFinishedServices;
    private String state;
    private String city;
    private String catName;
    private int catId;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public int getnFinishedServices() {
        return nFinishedServices;
    }

    public void setnFinishedServices(int nFinishedServices) {
        this.nFinishedServices = nFinishedServices;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    
}
