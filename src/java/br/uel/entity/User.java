/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.entity;

/**
 *
 * @author Vanessa
 */
public class User {

    private String login;
    private Integer uid;
    private String fname;
    private String password;

    public String getLogin() {
        return login;
    }

    public Integer getUid() {
        return uid;
    }

    public String getName() {
        return fname;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.fname = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
