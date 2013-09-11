/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leticia
 */
public class Profile {

    private User user;
    private int profileId;
    private int providerId;
    private String description;
    private String picture;
    
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    
    public int getProfileId() {
        return profileId;
    }

    

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
    
    
}
