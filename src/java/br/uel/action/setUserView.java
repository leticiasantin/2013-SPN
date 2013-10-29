/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public class setUserView extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.init(request, response);
        String m = request.getParameter("m");
        if (m.equalsIgnoreCase("edit")) {
            edit();
        } else if (m.equalsIgnoreCase("welcome")) {
            goWelcome();
        }
    }

    private void edit() {
        templateView.setTitle("EditarCadastro").setUserAttributes().setContent("userCrud").setMessage("Edite seus dados").setFooter(null);
        super.dispatcher();
    }

    private void goWelcome() {
        templateView.setTitle("Página Incial");
        super.dispatcher();
    }
}
