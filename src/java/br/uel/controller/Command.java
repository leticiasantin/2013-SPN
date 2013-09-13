/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.controller;

import br.uel.entity.TemplateView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public abstract class Command {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected TemplateView templateView;

    public Command() {
        templateView = new TemplateView();
    }

    public abstract void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    protected void init(HttpServletRequest request,
            HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void dispatcher(){
        if (templateView.getContent().isEmpty()) {
            templateView.setContent("index").setMessage("Conexão Falhou");
        }
        try {
            request.setAttribute("view",templateView);
            request.getRequestDispatcher("templateView.jsp").forward(request, response);
        } catch (Exception ex) {
            templateView.clearAttributes().setContent("error").setMessage("Erro na conexão");
        }
    }
}
