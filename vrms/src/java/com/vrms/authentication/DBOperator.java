/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication;

import com.vrms.authentication.core.Database;
import com.vrms.authentication.core.IDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashok
 */
public class DBOperator extends HttpServlet {

     @Override
        protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                String cmd = request.getParameter("cmd");
                if(cmd !=  null) {
                        if(cmd.equals("clearCookie")) {
                                cleanCookie();
                                response.sendRedirect("/xss-demo/hacker.jsp");
                        }
                }
        }
        
        @Override
        protected void doPost(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                doGet(request, response);
        }
        
        private void cleanCookie() {
                IDatabase db = Database.getInstance();
                db.cleanCookie();
        }
}
