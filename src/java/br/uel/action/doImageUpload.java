/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.action;

import br.uel.database.DAOFactory;
import br.uel.database.PictureDAO;
import br.uel.entity.Picture;
import br.uel.log.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author leticia
 */
public class doImageUpload extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            //Verifica se possui um fileUpload request
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            File directory = null;
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();

                //handler de uploads
                ServletFileUpload upload = new ServletFileUpload(factory);

                try {
                    List items = upload.parseRequest(request);
                    Iterator iter = items.iterator();
                    FileItem item;
                    String type = null, id = null, userType = null;
                    Picture pic = new Picture();
                    PictureDAO pDao;
                    DAOFactory dFactory = DAOFactory.getDAOFactory();
                    pDao = (PictureDAO) dFactory.getDAOObject(DAOFactory.DAODataType.PictureDAO);

                    while (iter.hasNext()) {
                        item = (FileItem) iter.next();

                        if (item.isFormField()) {
                            if (item.getFieldName().equals("type")) {
                                type = item.getString();
                                if (type.equalsIgnoreCase("p")) {
                                    type = "/profiles/";
                                } else if (type.equalsIgnoreCase("s")) {
                                    type = "/services/";

                                } else if (type.equalsIgnoreCase("page")) {
                                    type = "/pages/";
                                }
                            } else if (item.getFieldName().equals("id")) {
                                id = item.getString();

                            } else if (item.getFieldName().equals("userType")) {
                                userType = item.getString();
                                type += userType + "/";
                                if (userType.equals("c")) {

                                    pic.setServiceId(Integer.parseInt(id));
                                    pic.setImage(type);
                                    pDao.createClientPicture(pic);
                                } else if (userType.equals("p")) {

                                    pic.setServiceId(Integer.parseInt(id));
                                    pic.setImage(type);
                                    pDao.createProviderPicture(pic);
                                }
                            }
                        }

                        if (!item.isFormField()) {
                            if (item.getName().length() > 0) {
                                //aponta para diretorio imagens a partir do diretório atual
                                String path = getServletContext().getRealPath("imagens") + "/";
                                path = path.replace("/build/web", "");
                                System.out.println("\n\npath: " + path + "\n\n");
                                directory = new File(path + type);

                                //se o diretorio não existir, cria-se o diretório
                                if (!directory.exists()) {
                                    directory.mkdir();
                                }
                                if (type.contains("services") || type.contains("page")) {

                                    directory = new File(directory.getAbsolutePath());
                                   
                                    //se o diretorio não existir, cria-se o diretório
                                    if (!directory.exists()) {
                                        directory.mkdir();
                                    }


                                }
                                
                                  if (type.contains("page")) {
                                        pic.setServiceId(Integer.parseInt(id));
                                        pic.setImage(type);
                                        pDao.createDivulgationPagePicture(pic);
                                        id += "-"+pic.getPictureId();
                                    }


                                /* para inserir o nome das imagens de serviço é 
                                 *  preciso saber o id da imagem então esse é o 
                                 * proximo passo por enquanto fica como o id 
                                 */

                                String name = id + ".jpg";
                                //Split com escape \\ para \\
                                String split[] = name.split("\\\\");

                                for (int i = 0; i < split.length; i++) {
                                    name = split[i];
                                }

                                //ex: c:\temp\foto.jpeg
                                File file = new File(directory, name);
                                //para escrever no arquivo criado
                                FileOutputStream output = new FileOutputStream(file);
                                //para ler conteudo da img
                                InputStream input = item.getInputStream();
                                byte[] buffer = new byte[2048];
                                int nLidos;

                                while ((nLidos = input.read(buffer)) >= 0) {
                                    output.write(buffer, 0, nLidos);
                                }

                                output.flush();
                                output.close();

                            }
                            //      out.println("<h1>Setou imagem: </h1>");
                        }
                    }
//                    usuarioDAO.create(usuario);
                    //  out.println(" id"+usuario.getIdUsuario());
                } catch (FileUploadException ex) {
                }

            }
            /*
             * Em vez de redirecionar a pop-up é fechada
             */
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><body>Imagem enviada com sucesso</body></html>  ");

        } finally {
//            out.close();
        }
        Logger.getInstance().setLog(" upload image ok.");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
