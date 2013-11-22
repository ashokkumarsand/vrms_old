/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.viewallocations;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Notifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class AllocationDao {
    
    
    private JDBCConnectionPool pool;
    public AllocationDao() {
         pool = new JDBCConnectionPool();
    }
    
    
     public List<Request> viewAllRequests() throws SQLException
    {
         List<Request> rlist = new ArrayList<Request>();
        Connection con = pool.checkOut();
        Request r = null;
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select * from REQUEST where request_status=2")) {
            
              
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                        r = new Request();
			r.setRequestID(rs.getInt(1));
                        r.setUserID(rs.getInt(2));
                        r.setStartTime(rs.getTimestamp(3));
                        r.setEndTime(rs.getTimestamp(4));
                        r.setMobileNO(rs.getInt(5));
                        r.setPurpose(rs.getString(8));
                        rlist.add(r);
			}
  
           // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
           
            return rlist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }
     
      public synchronized boolean setAllocations(Integer requestID, Integer cabID) throws SQLException
    {
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO ALLOCATIONS(request_id,cab_id) VALUES ( ?, ?)")) {
            
            ps.setInt(1, requestID);
            ps.setInt(2, cabID);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }
     
      public int getNumberOfPersons(Integer requestID) throws SQLException
      {
          
          int person=0;
          Connection con = pool.checkOut();
        
          con.setAutoCommit(false); // for checking query without any entry in database
          try (PreparedStatement ps = con.prepareStatement("Select DISTINCT(P.REQUEST_USER_ID) from REQUEST R,POINT P where R.?=P.request_id")) {
            
                ps.setInt(1, requestID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                       person=person+1;
			}
  
           // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
           
            return person;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return person ;
        } finally {
            pool.checkIn(con);
        }
    }
      public int getCabCapacitywsz(Integer requestID) throws SQLException
      {
          
          int person=0;
          Connection con = pool.checkOut();
        
          con.setAutoCommit(false); // for checking query without any entry in database
          try (PreparedStatement ps = con.prepareStatement("Select DISTINCT(P.REQUEST_USER_ID) from REQUEST R,POINT P where R.?=P.request_id")) {
            
                ps.setInt(1, requestID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                       person=person+1;
			}
  
           // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
           
            return person;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return person ;
        } finally {
            pool.checkIn(con);
        }
    }
     
    
     
     
//     public static void main(String args[]) throws SQLException
//     {
//         AllocationDao dao = new AllocationDao();
//         List<Request> rlist=dao.viewAllRequests();
//         int size=rlist.size();
//       System.out.println(""+size);
//       // iterating through arraylist
//       Iterator<Request> it = rlist.iterator();
//          while(it.hasNext())
//       {
//           System.out.println(""+it.next());
//  
//       }
//     }
}
