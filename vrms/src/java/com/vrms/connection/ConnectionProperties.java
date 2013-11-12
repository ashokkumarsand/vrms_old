/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.connection;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.jca.GetInstance;

/**
 *
 * @author Ashok
 */
public class ConnectionProperties {

    private static String username;
    private static String password;
    private static String url;
    private static String driver;
    private static ConnectionProperties instance;

    private ConnectionProperties() {
    }

    public static ConnectionProperties getInstance() {
        if (instance == null) {
            instance = new ConnectionProperties();
            Properties properties = new Properties();
            try {
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vrms/properties/connection.properties"));
               } catch (IOException ex) {
                Logger.getLogger(ConnectionProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("protocol") + ":" + properties.getProperty("database") + "://" + properties.getProperty("host") + ":" + properties.getProperty("port") + "/" + properties.getProperty("database_name");
            System.out.println("URL : "+url);
        }
        return instance;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }
}
