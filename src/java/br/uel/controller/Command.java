/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leticia
 */
public interface Command {
       void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception;
}
