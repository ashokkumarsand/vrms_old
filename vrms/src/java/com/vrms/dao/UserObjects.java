/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.authentication.core.Constants;
import com.vrms.connection.JDBCConnectionPool;
import com.vrms.creation.CreateUser;
import com.vrms.model.Roles;
import com.vrms.util.Digest;
import com.vrms.util.Permissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
            Roles role = null;
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

    public synchronized boolean registerUser(Integer userOfficeID, String name, String mobileNO, Integer extensionNO, String email, Integer managerID, Integer departmentID, Integer roleID) {
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("insert into users(user_office_id, name,mobile_no, extension_no,email,manager_id,role_id,department_id,password,user_status) values(?,?,?,?,?,?,?,?,?,?)")) {
            if (userOfficeID == null) {
                ps.setNull(1, Types.NULL);
            } else {
                ps.setInt(1, userOfficeID);
            }
            ps.setString(2, name);
            ps.setString(3, mobileNO);
            if (extensionNO == null) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setInt(4, extensionNO);
            }
            if (email.equals("")) {
                ps.setNull(5, Types.NULL);
            } else {
                ps.setString(5, email);
            }
            if (managerID == null) {
                ps.setNull(6, Types.NULL);
            } else {
                ps.setInt(6, managerID);
            }
           
            ps.setInt(7, roleID);
            if (departmentID == null) {
                ps.setNull(8, Types.NULL);
            } else {
                ps.setInt(8, departmentID);
            }
            ps.setString(9, Digest.md5(Constants.RELAM + Constants.DEFAULT_PASSWORD));
            ps.setBoolean(10, true);
            System.out.println(" values : "+ email+" ,"+ mobileNO+ " "+ name+" "+departmentID+" "+ extensionNO+" "+ managerID+" "+ roleID+" "+ userOfficeID);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("i am here with exception ");
            ex.printStackTrace();
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    @Deprecated
    private List<Permissions> getPermissionsOfRole(int roleId) {
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
