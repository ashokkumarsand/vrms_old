/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication;

import com.vrms.authentication.core.Constants;
import com.vrms.authentication.core.Database;
import com.vrms.authentication.core.IDatabase;
import com.vrms.util.Digest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashok
 */
public class ServiceLoginAuth extends HttpServlet {

    private final static String USER_NAME="username";
        private final static String PASSWORD="password";
        private final static String REMEMBER_ME="rememberme";
        private final static String SERIES_ID="sid";
        private final static String TOKEN="token";
        
        @Override
        protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                String password = request.getParameter(PASSWORD);
                if(!Digest.isEmpty(password)) {
                        String username = request.getParameter(USER_NAME);
                        if(!Digest.isEmpty(username))  {
                                IDatabase db = Database.getInstance();
                                int userId = db.loginAuth(username, password);
                                if(userId >= 0) {
                                        HttpSession session = request.getSession();
                                        session.setAttribute(Constants.USERID, userId);
                                        response.sendRedirect(Constants.INDEX_PAGE);
                                        return;
                                }
                        }
                }
                response.sendRedirect(Constants.LOGIN_PAGE);
        }
        
        @Override
        protected void doPost(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                doGet(request, response);
        }
}
