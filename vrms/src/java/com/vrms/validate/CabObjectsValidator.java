/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.validate;

import com.vrms.connection.JDBCConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class CabObjectsValidator {
    JDBCConnectionPool pool;
    private static CabObjectsValidator instance;

    private CabObjectsValidator() {
        pool = new JDBCConnectionPool();
    }

    public static CabObjectsValidator getInstance() {
        if (instance == null) {
            instance = new CabObjectsValidator();
        }
        return instance;
    }

    //for checking cabTypeID exists or not while inserting an ID in any of the dependent table
    public boolean isCabTypeIdExist(int id) throws SQLException {
        boolean exists = false;
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("SELECT CAB_TYPE_ID FROM CAB_TYPE where CAB_TYPE_ID=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CabObjectsValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }
    
    
    //for checking cabID exists or not while inserting an ID in any of the dependent table
    public boolean isCabIdExist(int id) throws SQLException {
        boolean exists = false;
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("SELECT CAB_ID FROM CABS where CAB_ID=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CabObjectsValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }
    
    //for checking cabID exists or not while inserting an ID in any of the dependent table
    public boolean isVehicleNoExist(int id) throws SQLException {
        boolean exists = false;
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("SELECT VEHICLE_NO FROM VEHICLES where VEHICLE_NO=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CabObjectsValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }
    
    
    //main function for cheching query without any data entry in database
    public static void main(String args[]) throws SQLException
    {
        CabObjectsValidator c=new CabObjectsValidator();
       boolean result= c.isVehicleNoExist(100);
       if(result)
       {
           System.out.println("No exists");
       }
       else
       {
           System.out.println("No does not exists");
       }
    }
}
