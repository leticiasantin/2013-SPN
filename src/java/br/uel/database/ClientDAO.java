/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

/**
 *
 * @author leticia
 */
public abstract class ClientDAO {
     protected DAOFactory daoFactory;
    public abstract void create (int cid);
    
    public abstract boolean read(int cid);
    
    public abstract void update (int cid);
    
    public abstract void delete (int cid);
    
}
