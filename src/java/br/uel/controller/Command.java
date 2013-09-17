/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.controller;

import br.uel.entity.TemplateView;
import br.uel.log.Logger;
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

   

    public abstract void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    protected void init(HttpServletRequest request,
            HttpServletResponse response) {
        this.request = request;
        this.response = response;
        templateView = new TemplateView();
    }

    public void dispatcher(){
        if (templateView.getContent().isEmpty()) {
            templateView.setContent("index").setMessage("Conexão Falhou");
        }
        try {
            request.setAttribute("view",templateView);
            Logger.getInstance().setLog("dispatcher to: "+templateView.getContent());
            request.getRequestDispatcher("templateView.jsp").forward(request, response);
        } catch (Exception ex) {
            templateView.clearAttributes().setContent("error").setMessage("Erro na conexão");
        }
    }
}
