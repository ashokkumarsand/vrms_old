/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication.core;

/**
 *
 * @author Ashok
 */
public class Constants {

    public final static String USERID = "userid";
    public final static String URL_ROOT = "/vrms";
    public final static String LOGIN_PAGE = URL_ROOT + "/login.jsp";
    public final static String INDEX_PAGE = URL_ROOT + "/index.jsp";
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String PERMISSIONS = "permission";
    public final static String RELAM ="";
    public final static String DEFAULT_PASSWORD ="abcd1234!";
    public String getUSERID() {
        return USERID;
    }
}
