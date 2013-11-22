/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.pagination;

import com.google.gson.Gson;
import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Notifications;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author acts
 */
public class NotificationDao {
     private JDBCConnectionPool pool;

    public NotificationDao() {
        pool = new JDBCConnectionPool();
    }
    
    private int noOfRecords;
    public List<Notifications> viewAllNotifications(int notificationTo,int noOfRecords) throws SQLException
    {
        String query = "select * from notifications where notification_to="+notificationTo+" limit "+ noOfRecords;
        List<Notifications> nlist = new ArrayList<Notifications>();
        Notifications n = null;
        Statement stmt=null;
        Connection con=null;
        try {
            con = pool.checkOut();
            con.setAutoCommit(false);// for checking query without any entry in database
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                        n=new Notifications();
                        n.setNotificationID(rs.getInt(1));
                        n.setNotificationFrom(rs.getInt(2));
                        n.setNotificationTo(rs.getInt(3));
                        n.setNotificationText(rs.getString(4));
                        n.setNotificationStatus(rs.getBoolean(5));
                        nlist.add(n);
                        
            }
            System.out.println("No of records"+nlist.size());
          
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            if(stmt != null)
                stmt.close();
            pool.checkIn(con);
        }
        return nlist;
    }
 
    
  public static void main(String args[]) throws SQLException
{
        NotificationDao dao = new NotificationDao();
       List<Notifications> n= dao.viewAllNotifications(2,4);
       int size=n.size();
       System.out.println(""+size);
      
       //converting to JSON Object
        Gson gson = new Gson();
        String json = new Gson().toJson(n);
        System.out.println(json);
          
        //iterating through arraylist
//       Iterator<Notifications> it = n.iterator();
//          while(it.hasNext())
//       {
//           System.out.println(""+it.next());
//  
//       }

  }
}
