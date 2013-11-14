/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Roles;
import com.vrms.util.Permissions;
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
public class UserObjects {

    private JDBCConnectionPool pool;

    public UserObjects() {
        pool = new JDBCConnectionPool();
    }

    public List<Roles> getAllRole() {
        List<Roles> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT roles.role_id, roles.role_name FROM public.roles")) {
            ResultSet rs = ps.executeQuery();
            Roles role =null;
            while (rs.next()) {
                   role = new Roles();
                   role.setRoleid(rs.getInt("role_id"));
                   role.setRolename(rs.getString("role_name"));
                   //role.getPermission().addAll(getPermissionsOfRole(rs.getInt("role_id")));
                   list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserObjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        pool.checkIn(con);
        return list;
    }
    
    @Deprecated
    private List<Permissions> getPermissionsOfRole(int roleId){
        List<Permissions> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("")) {
        } catch (SQLException ex) {
            Logger.getLogger(UserObjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        pool.checkIn(con);
        return list;
    }
}
