/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Cab;
import com.vrms.model.CabType;
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
 * @author Ashok
 */
public class CabObjects {
    JDBCConnectionPool pool;

    public CabObjects() {
        pool = new JDBCConnectionPool();
    }
    
    
    public List<CabType> getCabTypes(){
       List<CabType> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try(PreparedStatement ps = con.prepareStatement("SELECT cab_type.cab_type_id, cab_type.cab_type_value, cab_type.cab_type_description FROM public.cab_type")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CabType type = new CabType();
                type.setId(rs.getInt(1));
                type.setType(rs.getString(2));
                type.setDescription(rs.getString(3));
                list.add(type);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pool.checkIn(con);
        }
       return list;
    }
    public List<Cab> getCabForTypes(int cabTypeId){
       List<Cab> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try(PreparedStatement ps = con.prepareStatement("SELECT cabs.cab_id, cabs.cab_name, cabs.cab_description, cabs.cab_status FROM public.cabs WHERE cabs.cab_type_id = ?")){
            ps.setInt(1, cabTypeId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cab type = new Cab();
                type.setId(rs.getInt(1));
                type.setName(rs.getString(2));
                type.setDescription(rs.getString(3));
                type.setStatus(rs.getBoolean(4));
                type.setTypeid(cabTypeId);
                list.add(type);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pool.checkIn(con);
        }
       return list;
    }
}
