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
public class Actiontemplate extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       super.init(request, response);
       
       super.dispatcher();
        
    }
    
}
