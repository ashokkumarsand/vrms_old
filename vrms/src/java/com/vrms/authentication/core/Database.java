/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.authentication.core;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.util.Digest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashok
 */
public class Database implements IDatabase {
    
    private static Database instance = null;
    private JDBCConnectionPool pool = null;
    private final static String COOKIE_TABLE_NAME = "cookies";
    
    private Database() {
        pool = new JDBCConnectionPool();
    }
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    @Override
    public void saveCookies(Map cookies) {
        StringBuffer strCookie = new StringBuffer();
        for (Object key : cookies.keySet()) {
            strCookie.append(key.toString());
            strCookie.append("=");
            String[] value = (String[]) cookies.get(key);
            for (String s : value) {
                strCookie.append(s);
            }
            strCookie.append(";");
        }
        Connection con = pool.checkOut();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("insert into cookies(cookie) values(?)");
            ps.setString(1, strCookie.toString());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        
    }
    
    
    @Override
    public Map<Integer, String> getAllStolenCookies() {
        Map<Integer, String> result = new Hashtable<Integer, String>();
        Connection con = pool.checkOut();
        
        try {
            PreparedStatement ps = con.prepareStatement("select * from cookies");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            pool.checkIn(con);
        }
        return result;
    }
    
    @Override
    public void cleanCookie() {
        Connection con = pool.checkOut();
         try {
            PreparedStatement ps = con.prepareStatement("delete from cookies");   
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            pool.checkIn(con);
        }
    }
    private final static String REALM = "";
    
    @Override
    public int loginAuth(String username, String password) {
        int userId = -1;
        
        try {
            Connection con = pool.checkOut();
            PreparedStatement ps = con.prepareStatement("select user_id,password from users where mobile_no=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ck = rs.getString("password");
                System.out.println("user password : "+ck +" syso pass : "+Digest.md5(REALM + password) );
                if (ck.equals(Digest.md5(REALM + password))) {
                    userId = rs.getInt("user_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
}
