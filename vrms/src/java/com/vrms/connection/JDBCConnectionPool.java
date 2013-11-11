/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.connection;

import com.vrms.util.ObjectPool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ashok
 */
public class JDBCConnectionPool extends ObjectPool<Connection>{

   private String url;
   private String username;
   private String password;

  public JDBCConnectionPool() {
    super();
    ConnectionProperties properties = ConnectionProperties.getInstance();
    try {
      Class.forName(properties.getDriver()).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.url = properties.getUrl();
    this.username = properties.getUsername();
    this.password = properties.getPassword();
  }

  @Override
  protected Connection create() {
    try {
      return (DriverManager.getConnection(url, username, password));
    } catch (SQLException e) {
      e.printStackTrace();
      return (null);
    }
  }

  @Override
  public void expire(Connection o) {
    try {
      ((Connection) o).close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean validate(Connection o) {
    try {
      return (!((Connection) o).isClosed());
    } catch (SQLException e) {
      e.printStackTrace();
      return (false);
    }
  }
    
}
