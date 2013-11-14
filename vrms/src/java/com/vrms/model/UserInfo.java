/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.model;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.exception.NoUserFoundException;
import com.vrms.util.Permissions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This call provide all information of user work as user info bean
 *
 * @author Ashok
 */
public class UserInfo {

    private Integer userid;
    private Integer userOfficeID;
    private String name;
    private String mobileNo;
    private Integer extensionNo;
    private String email;
    private Integer manager_id;
    private Integer dept_id;
    private Integer role_id;
    private boolean status;
    private boolean exists;
    private JDBCConnectionPool pool;
    private List<Permissions> permissionsList;
    public UserInfo() {
        pool = new JDBCConnectionPool();
    }
    /**
     * Retrieve user information from database if user exist  
     */
    private void fatchUserInfo() {
        Connection con = pool.checkOut();
        try(PreparedStatement ps = con.prepareStatement("SELECT users.user_office_id,"
                + "  users.name,"
                + "  users.mobile_no,"
                + "  users.extension_no,"
                + "  users.email,"
                + "  users.manager_id,"
                + "  users.department_id,"
                + "  users.role_id,"
                + "  users.user_status"
                + "  FROM "
                + "  public.users "
                + "  WHERE "
                + "  users.user_id =?")){
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userOfficeID = rs.getInt("user_office_id");
                name = rs.getString("name");
                mobileNo = rs.getString("mobile_no");
                email = rs.getString("email");
                manager_id = rs.getInt("manager_id");
                dept_id = rs.getInt("department_id");
                role_id = rs.getInt("role_id");
                status = rs.getBoolean("user_status");
                exists = true;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        pool.checkIn(con);
    }
    
    
    public List<Permissions> getPermissions() throws NoUserFoundException{
        if(userid==null||userid<0){
            throw new NoUserFoundException();
        }
        permissionsList = new ArrayList<>();
        Connection con = pool.checkOut();
        try(PreparedStatement ps = con.prepareStatement("SELECT permissions.permission_value FROM public.permissions, public.role_permissions, public.users WHERE permissions.permission_id = role_permissions.permission_id AND role_permissions.role_id = users.role_id AND users.user_id = ?")){
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(Permissions.valueOf(rs.getString("permission_value")).equals(Permissions.ALL)){
                    permissionsList.clear();
                    permissionsList.addAll(Arrays.asList(Permissions.values()));
                    permissionsList.remove(Permissions.NONE);
                    break;
                }
                permissionsList.add(Permissions.valueOf(rs.getString("permission_value")));
            }
            if(permissionsList.size()>1&&permissionsList.contains(Permissions.NONE)){
                permissionsList.remove(Permissions.NONE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permissionsList;    
    }
    

    
    /**
     * User identification number for information retrive
     * @return 
     */
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        exists = false;
        this.userid = userid;
        fatchUserInfo();
    }

    public Integer getUserOfficeID() {
        return userOfficeID;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Integer getExtensionNo() {
        return extensionNo;
    }

    public String getEmail() {
        return email;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isExists() {
        return exists;
    }
    
}
