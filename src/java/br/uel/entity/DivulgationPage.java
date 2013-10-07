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
public class DivulgationPage {

    private int profileId;
    private int providerId;
    private String description;
    private User user;
    private List<Picture> pictures;
    private List<Category> categories;

    public DivulgationPage() {
        this.profileId = 0;
        this.pictures = new ArrayList<Picture>();
        this.categories = new ArrayList<Category>();
    }
    
    

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
