package br.uel.entity;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leticia
 */
public class TemplateView {
    private String title;
    private String header;
    private String footer;
    private String menu;
    private String content;
    private String message;

   
    public TemplateView() {
    }

    public String getHeader() {
        return header;
    }

    public TemplateView setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;

    }

    public String getMenu() {
        return menu;
        
    }

    public TemplateView setMenu(String menu) {
        this.menu = menu;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TemplateView setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TemplateView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TemplateView setMessage(String message) {
        this.message = message;
          return this;
    }

    public TemplateView setAdminAttributes() {
        this.header = "adminHeader";
        this.menu = "adminMenu";
        return this;
       
    }
    
    public TemplateView setUserAttributes() {
        this.header = "userHeader";
        this.menu = "userMenu";
        return this;
    }

    public TemplateView clearAttributes() {
        this.header = null;
        this.content = null;
        this.footer = null;
        this.menu = null;
        this.message = null;
        this.title = null;
        return this;
    }

    public TemplateView setGuestAttributes() {
        this.clearAttributes();
        this.content = "index";
        return this;
    }

    public void imprime() {
        System.out.println("\nTemplateView\nheader: "+this.header+"\nmenu: "+this.menu +
                "\ncontent: "+this.content+"\nfooter: "+this.footer);
    
    }
    
    

    
    }

