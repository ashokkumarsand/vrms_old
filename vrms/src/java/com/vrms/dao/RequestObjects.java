/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.UserInfo;
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
public class RequestObjects {
    
    private JDBCConnectionPool pool;

    public RequestObjects() {
        pool = new JDBCConnectionPool();
    }
    
    //knowing the manager of a particular employee
    
    public synchronized UserInfo getManager(Integer id) throws SQLException
    {
        Connection con = pool.checkOut();
        UserInfo u = new UserInfo();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select * from USERS where user_id=?")) {
            
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
			u.setUserid(rs.getInt(1));
                        u.setUserOfficeID(rs.getInt(2));
                        u.setName(rs.getString(3));
                        u.setMobileNo(rs.getString(4));
                        u.setExtensionNo(rs.getInt(5));
                        u.setEmail(rs.getString(6));
                        u.setManager_id(rs.getInt(7));
                        u.setDept_id(rs.getInt(8));
                        u.setStatus(rs.getBoolean(11));
			}
  
            System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
           
            return u;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }
    
    //checking if user having authority to approve request
    public synchronized boolean isUserApprovingAuthority(Integer id) throws SQLException
    {
        Connection con = pool.checkOut();
        List<Integer> p = new ArrayList<>();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select r.permission_id from USERS u,ROLE_PERMISSIONS r where u.role_id=r.role_id and  u.user_id=?")) {
            
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    
			p.add(rs.getInt("permission_id"));
			}
                if(p.contains(12)) //REQUEST_APPROVE has 12 as a permission ID
                {
                     return true;
                }
                else
                {
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
    
    public synchronized int getApprovingAuthority(Integer id) throws SQLException
    {
         RequestObjects r=new RequestObjects();
        int managerID=id;
        boolean flag=true;
        while(flag)
        {
            UserInfo user = r.getManager(managerID);  
            managerID=user.getManager_id();    
            if(managerID==0) //if user manager id is null then we check for its role id
            {
                int roleID=user.getRole_id();
                if(roleID==3)
                {
                    managerID=user.getUserid();
                    return managerID;
                }
            }
            
            Boolean check=r.isUserApprovingAuthority(managerID);
            if(check)
            {
                flag=false;
                System.out.println("MID"+managerID);
                return managerID;
            }
            else
            {
                flag=true;
            }
        }
        return managerID;
    }
    
    //changing request status
    public boolean requestStatusChange(String id,Integer status) throws SQLException{
		
			
             Connection con = pool.checkOut();
         
             try (PreparedStatement ps = con.prepareStatement("Update REQUEST SET request_status= ? where request_ID=?"))
             {
                ps.setInt(1, status);
                ps.setInt(2, Integer.parseInt(id));
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
    
    // main class to check query is running or not
    public static void main(String args[]) throws SQLException
    {
        RequestObjects r=new RequestObjects();
        int managerID=r.getApprovingAuthority(2);
        System.out.println("Manager ID = "+managerID);

    }
    
}
