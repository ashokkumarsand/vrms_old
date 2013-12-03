/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashok
 */
public class RequestObject {

    JDBCConnectionPool pool;

    public RequestObject() {
        pool = new JDBCConnectionPool();
    }

    /**
     *
     * @param userID
     * @param startTime
     * @param endTime
     * @param mobileNO
     * @param pointID
     * @param purpose
     * @return
     * @throws SQLException
     */
    public synchronized int fillRequest(Integer userID, Timestamp startTime, Timestamp endTime, long mobileNO, String purpose) {
        System.out.println("uid 1 :: "+ userID);
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO request(user_id, start_time, end_time, mobile_no, request_status,purpose) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userID);
            ps.setTimestamp(2, startTime);
            ps.setTimestamp(3, endTime);
            ps.setLong(4, mobileNO);
            ps.setInt(5, 1);
            ps.setString(6, purpose);
            ps.executeUpdate();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, "executed");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {

            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            pool.checkIn(con);
        }
        return -1;
    }

    public boolean updatePointId(int requestId, int pointId) {
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("update request set point_id = ? where request_id = ?")) {
            ps.setInt(1, pointId);
            ps.setInt(2, requestId);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            pool.checkIn(con);
        }
        return false;

    }
    //knowing the manager of a particular employee

    public synchronized UserInfo getManager(Integer id) throws SQLException {
        Connection con = pool.checkOut();
        UserInfo user = new UserInfo();
        user.setUserid(id);
        return user;


    }

    //checking if user having authority to approve request
    public synchronized boolean isUserApprovingAuthority(Integer id) throws SQLException {
        Connection con = pool.checkOut();
        List<Integer> p = new ArrayList<>();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select r.permission_id from USERS u,ROLE_PERMISSIONS r where u.role_id=r.role_id and  u.user_id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                p.add(rs.getInt("permission_id"));
            }
            if (p.contains(12)) //REQUEST_APPROVE has 12 as a permission ID
            {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    public synchronized int getApprovingAuthority(Integer id) throws SQLException {

        int managerID = id;
        boolean flag = true;
        while (flag) {
            UserInfo user = getManager(managerID);
            managerID = user.getManager_id();
            if (managerID == 0) //if user manager id is null then we check for its role id
            {
                int roleID = user.getRole_id();
                if (roleID == 3) {
                    managerID = user.getUserid();
                    return managerID;
                }
            }

            Boolean check = isUserApprovingAuthority(managerID);
            if (check) {
                flag = false;
                System.out.println("MID" + managerID);
                return managerID;
            } else {
                flag = true;
            }
        }
        return managerID;
    }

    /**
     *
     * @param id request id
     * @param status status of request
     * @return
     * @throws SQLException
     */
    //changing request status
    public boolean updateRequestStatus(int id, Integer status) throws SQLException {
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("update request SET request_status= ? where request_ID=?")) {
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(UserObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }
    
    /**
     *
     * @param id request id
     * @param status status of request
     * @return
     * @throws SQLException
     */
    //changing request status
    public boolean logRequestSatus(int id, Integer status,int userid) {
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("insert into request_status_log(request_id,request_status_id,user_id) values(?,?,?)")) {
            ps.setInt(1, id);
            ps.setInt(2, status);
            ps.setInt(3, userid);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(UserObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }
    
}
