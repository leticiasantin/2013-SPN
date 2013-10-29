/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author leticia
 */
public class Category {
    private int catId;
    private int parentId;
    private String name;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
