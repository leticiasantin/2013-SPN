/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database;

import br.uel.entity.Picture;
import java.util.List;

/**
 *
 * @author leticia
 */
public abstract class PictureDAO {
      protected DAOFactory daoFactory;
    public abstract void createClientPicture(Picture picture);
    public abstract void createProviderPicture(Picture picture);
    public abstract void createDivulgationPagePicture(Picture picture);
    public abstract List<Picture> divulgationPagePictureList(int pageId);
    public abstract void clientPictureList(int serviceId);
    public abstract void providerPictureList(int serviceId);
   
}
