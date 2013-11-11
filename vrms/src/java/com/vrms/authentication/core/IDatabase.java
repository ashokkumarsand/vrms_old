/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication.core;

import java.util.Map;

/**
 *
 * @author Ashok
 */
public interface IDatabase {

    public void saveCookies(Map cookies);

    public Map<Integer, String> getAllStolenCookies();

    public void cleanCookie();

    public int loginAuth(String username, String password);
}
