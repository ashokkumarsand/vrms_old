/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class LocationObjects {
    private JDBCConnectionPool pool;

    public LocationObjects() {
        pool = new JDBCConnectionPool();
    }
    
    
    public synchronized int fillLocation(String buildingNO, String buildingName, String streetName,String areaName,String city,String state) throws SQLException
    {
       int locationID=0;
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO locations(building_no, building_name, street_name, area, city,state) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            if(buildingNO.equals(null))
            {
                ps.setString(1, null);
            }
            else
            {
            ps.setString(1, buildingNO);
            }
            ps.setString(2, buildingName);
            if(streetName.equals(null))
            {
                ps.setString(3, null);
            }
            else
            {
            ps.setString(3, streetName);
            }
            if(areaName.equals(null))
            {
                ps.setString(1, null);
            }
            else
            {
            ps.setString(4, areaName);
            }
            ps.setString(5, city);
            ps.setString(6, state);
        
            ps.executeUpdate();
           
                
          
           
         //  System.out.println(" values : "+ buildingNO+" ,"+ buildingName+ " "+ streetName+" "+ areaName+" "+ city+","+state);
           
            //Now selecting location ID 
            PreparedStatement ps2=con.prepareStatement("SELECT MAX(location_id) - 1 as MAXVAL from locations");
            ResultSet rs=ps2.executeQuery();
            if(rs.next())
            {
                locationID = rs.getInt("MAXVAL");
	    }
            return locationID;
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return locationID;
        } finally {
            pool.checkIn(con);
        }
       
    }
    public synchronized int fillRequestUser(String userName, String mobileNO) throws SQLException
    {
        int requestUserID=0;
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO request_user(user_name, user_mobile_no) VALUES (? , ?)"))
        {
            ps.setString(1, userName);
            ps.setString(2, mobileNO);
        
            ps.executeUpdate();
           
            //Now selecting location ID 
//            PreparedStatement ps2=con.prepareStatement("SELECT CURRVAL(pg_get_serial_sequence('request_user','request_user_id')) as MAXVAL");
           PreparedStatement ps2=con.prepareStatement("SELECT MAX(request_user_id) - 1 as MAXVAL from request_user");
            ResultSet rs=ps2.executeQuery();
            if(rs.next())
            {
                requestUserID = rs.getInt("MAXVAL");
	    }
            return requestUserID;
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return requestUserID;
        } finally {
            pool.checkIn(con);
        }
    }
    
    //for filling request table
       public synchronized int fillRequest(Integer userID,Timestamp startTime,Timestamp endTime,Integer mobileNO,Integer pointID,String purpose) throws SQLException
    {
        int requestID=0;
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO request(user_id, start_time, end_time, mobile_no, point_id,request_status,purpose) VALUES (?, ?, ?, ?, ?, ?, ?)"))
        {
            ps.setInt(1, userID);
            ps.setTimestamp(2, startTime);
            ps.setTimestamp(3, endTime);
            ps.setInt(4, mobileNO);
            ps.setInt(5, pointID);
            ps.setInt(6, 1);
            ps.setString(7, purpose);
            ps.executeUpdate();
           
           
           // PreparedStatement ps2=con.prepareStatement("SELECT CURRVAL(pg_get_serial_sequence('request_user','request_user_id')) as MAXVAL");
           
//Now selecting request ID 
            PreparedStatement ps2=con.prepareStatement("SELECT MAX(request_id) - 1 as MAXVAL from request_user");
            ResultSet rs=ps2.executeQuery();
            if(rs.next())
            {
                requestID = rs.getInt("MAXVAL");
	    }
            return requestID;
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return requestID;
        } finally {
            pool.checkIn(con);
        }
    }
    
    
    
    
    
    
    
    public static void  main(String args[]) throws SQLException
    {
        LocationObjects obj=new LocationObjects();
        int locID=obj.fillLocation("1", "2","3","4","5","6");
        System.out.println("Location ID ="+locID);
   }
    
}
