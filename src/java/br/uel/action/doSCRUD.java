/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.controller.Controller;
import br.uel.log.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public abstract class doSCRUD  implements Controller {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
  
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        char m = request.getParameter("m").toCharArray()[0];
        switch (m) {
            case 's': //create or update
                Logger.getInstance().setLog("attempt to save user");
                save();
                break;
            case 'r':
                break;
            case 'd':
                break;
        }
    }
    
    public abstract void save();
    public abstract Object read(int id);
    public abstract boolean delete (int id);
    
    
    
    
    
    
    
    
}
