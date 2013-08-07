/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.filter;

import br.uel.database.DAOFactory;
import br.uel.database.PgDAOFactory;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author leticia
 */
public class ConnectionFilter extends HttpServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        DAOFactory conn;
//            if (Database == "PostgreSQL")
        conn = new PgDAOFactory();
        conn.connect();

        request.setAttribute("conn", conn);
       
        chain.doFilter(request, response);
        
        conn.close();
    }
}
