/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.validate;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.exception.NoUserFoundException;
import com.vrms.model.UserInfo;
import com.vrms.util.Permissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashok
 */
public class UserObjectValidator {

    JDBCConnectionPool pool;
    private static UserObjectValidator instance;

    private UserObjectValidator() {
        pool = new JDBCConnectionPool();
    }

    public static UserObjectValidator getInstance() {
        if (instance == null) {
            instance = new UserObjectValidator();
        }
        return instance;
    }

    public boolean isRoleExist(int id) {
        boolean exists = false;
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT ROLE_ID FROM ROLES where role_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserObjectValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }

    public boolean isDepartmentExist(int id) {
        boolean exists = false;
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT DEPARTMENT_ID FROM departments where department_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserObjectValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }
    
    public boolean isUserExist(int id) {
        boolean exists = false;
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT user_ID FROM users where user_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserObjectValidator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return exists;
    }

    public boolean isUserHasSetOfPermission(int userid, Permissions... permissionses) {
        UserInfo uinfo = new UserInfo();
        uinfo.setUserid(userid);
        boolean flag = false;
        try {
           flag = uinfo.getPermissions().containsAll(Arrays.asList(permissionses));
        } catch (NoUserFoundException ex) {
            
            Logger.getLogger(UserObjectValidator.class.getName()).log(Level.SEVERE, "User not exist in system for permissions", ex);
        }
        return flag;
    }
}
