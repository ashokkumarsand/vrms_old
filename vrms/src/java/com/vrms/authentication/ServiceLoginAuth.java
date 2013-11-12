/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication;

import com.vrms.authentication.core.Constants;
import com.vrms.authentication.core.Database;
import com.vrms.authentication.core.IDatabase;
import com.vrms.model.UserInfo;
import com.vrms.util.Digest;
import com.vrms.util.Permissions;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

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
            
                String password = request.getParameter(Constants.PASSWORD);
                if(!Digest.isEmpty(password)) {
                        String username = request.getParameter(Constants.USERNAME);
                        if(!Digest.isEmpty(username))  {
                                IDatabase db = Database.getInstance();
                                int userId = db.loginAuth(username, password);
                                UserInfo user = new UserInfo();
                                user.setUserid(userId);
                                if(userId >= 0 && !user.getPermissions().contains(Permissions.NONE)) {
                                        HttpSession session = request.getSession();
                                        session.setAttribute(Constants.USERID, userId);
                                        response.sendRedirect(Constants.INDEX_PAGE);
                                        return;
                                }
                        }
                }
                response.sendRedirect(Constants.LOGIN_PAGE+"?st="+ Base64.encodeBase64String("not exist".getBytes()));
        }
        
        @Override
        protected void doPost(HttpServletRequest request, 
                        HttpServletResponse response) 
        throws ServletException, IOException {
                doGet(request, response);
        }
}
