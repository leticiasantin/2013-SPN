/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

import java.util.List;

/**
 *
 * @author leticia
 */
public class Profile {

    private int profileId;
    private int providerId;
    private String description;
    private List<String> pictures;
    private List<Category> categories;

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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
