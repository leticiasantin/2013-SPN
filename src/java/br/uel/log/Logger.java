/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;


/**
 *
 * @author leticia
 */
public class Logger {

    String logFile;
    
    public String getName(){
        return this.logFile;
    }

    private Logger() {
    }

    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    private static class LoggerHolder {

        private static final Logger INSTANCE = new Logger();
    }

    public void setLog(String append)  {
        BufferedReader br = null;
        try {
            if (logFile == null) {
                   
                this.logFile = "/home/leticia/Desktop/spn_log.txt";
            }
               
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
                out.println(new Date() + "\t msg:" + append);
                out.close();
            } catch (Exception e) {
                System.out.println("erro  com o Log");
                //oh noes!
            }

        }
    
    public void print(Object obj){
        System.out.println(obj);
    }
    }
