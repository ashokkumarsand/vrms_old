/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ashok
 */
public class JDBCConnectionPoolTEST {
    public static void  main(String args[]) throws SQLException{
        JDBCConnectionPool pool = new JDBCConnectionPool();
        Connection con =  pool.checkOut();
        System.out.println("connected :: "+con.getMetaData());
    }
}
