/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Notifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class NotificationObjects {
    private JDBCConnectionPool pool;

    public NotificationObjects() {
        pool = new JDBCConnectionPool();
    }
    
    //setting notifications
    public synchronized boolean setNotifications(Integer notificationFrom, Integer notificationTo, String notificationText) throws SQLException
    {
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO NOTIFICATIONS(notification_from, notification_to, notification_text, notification_status) VALUES ( ?, ?, ?, ?)")) {
            
            ps.setInt(1, notificationFrom);
            ps.setInt(2, notificationTo);
            ps.setString(3, notificationText);
            ps.setBoolean(4, false);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(NotificationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }
    
    
    public synchronized boolean notificationStatusChange(Integer id,Boolean status) throws SQLException
    {
             Connection con = pool.checkOut();
             con.setAutoCommit(false); // for checking query without any entry in database
             try (PreparedStatement ps = con.prepareStatement("Update NOTIFICATIONS SET notification_status= ? where notification_ID=?"))
             {
                ps.setBoolean(1, status);
                ps.setInt(2, id);
                ps.executeUpdate();
                 
                return true;
		
             } catch (SQLException ex)
                {
                    System.out.println("Exception Occurred ");
                    ex.printStackTrace();
                    Logger.getLogger(UserObjects.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }   finally{
             pool.checkIn(con);
             }
        
    }
    
    public List<Notifications> getNotifications(Integer notificationTo) throws SQLException
    {
        List<Notifications> nlist=new ArrayList<>();
        Notifications n = new Notifications();
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select * from NOTIFICATIONS where NOTIFICATION_TO= ?")) {
            
            ps.setInt(1, notificationTo);
            ResultSet rs = ps.executeQuery();
                while(rs.next()){
			n.setNotificationID(rs.getInt(1));
                        n.setNotificationFrom(rs.getInt(2));
                        n.setNotificationTo(rs.getInt(3));
                        n.setNotificationText(rs.getString(4));
                        n.setNotificationStatus(rs.getBoolean(5));
                        nlist.add(n);
			}
            return nlist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return nlist;
        } finally {
            pool.checkIn(con);
        }
    }
    public static  void main(String args[]) throws SQLException
    {
        NotificationObjects obj = new NotificationObjects();
        
        boolean b = obj.notificationStatusChange(1, true);
        System.out.println("result"+b);
    }
            
    
}
