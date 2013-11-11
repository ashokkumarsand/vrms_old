/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication.core;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashok
 */
public class AuthenticationFilter implements Filter {
    
   @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                        FilterChain filterChain) throws IOException, ServletException {
                HttpServletRequest httpRequest = (HttpServletRequest)request;
                HttpServletResponse httpResponse = (HttpServletResponse)response;
                HttpSession session = httpRequest.getSession();
                Object userId = session.getAttribute(Constants.USERID);
                if(userId == null || (Integer)userId < 0) {
                        httpResponse.sendRedirect(Constants.LOGIN_PAGE);
                        return;
                }
                filterChain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
                
        }
}
