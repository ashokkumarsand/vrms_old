/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.viewallocations;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Cab;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class AllocationObjects {

    private JDBCConnectionPool pool;

    public AllocationObjects() {
        pool = new JDBCConnectionPool();
    }

    //this view is for FSG who can see all requests for allocation purpose
    public List<Request> viewAllRequests() throws SQLException {

        List<Request> rlist = new ArrayList<Request>();
        Connection con = pool.checkOut();
        Request r = null;
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select * from REQUEST where request_status=2")) {


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new Request();
                r.setRequestID(rs.getInt(1));
                r.setUserID(rs.getInt(2));
                r.setStartTime(rs.getTimestamp(3));
                r.setEndTime(rs.getTimestamp(4));
                r.setMobileNO(rs.getInt(5));
                r.setPurpose(rs.getString(8));
                int n = getNumberOfPersons(r.getRequestID());
                r.setNoOfPerson(n);
                rlist.add(r);

            }

            // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());

            return rlist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }

    // view all requests by a particular manager
    public List<Request> viewRequestsToManager(Integer userID) throws SQLException
    {
         AllocationObjects dao=new AllocationObjects();
         List<Request> rlist = new ArrayList<Request>();
        Connection con = pool.checkOut();
        Request r = null;
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("SELECT  r.request_id,u.name,r.start_time,r.end_time,r.mobile_no,r.purpose FROM public.request r, public.users u WHERE r.user_id = u.user_id AND u.manager_id = ? AND r.request_status = 1"))
        {       System.out.println("user id ::" + userID);
                ps.setInt(1, userID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                        r = new Request();
			r.setRequestID(rs.getInt(1));
                        r.setName(rs.getString(2));
                        r.setStartTime(rs.getTimestamp(3));
                        r.setEndTime(rs.getTimestamp(4));
                        r.setMobileNO(rs.getInt(5));
                        r.setPurpose(rs.getString(6));
                        int n=dao.getNumberOfPersons(r.getRequestID());
                        r.setNoOfPerson(n);
                        rlist.add(r);
			}
  
           // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
           
            return rlist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }

    //to fill allocation table after allocation is done
    public synchronized boolean setAllocations(Integer requestID, Integer cabID) throws SQLException {
        Connection con = pool.checkOut();
//        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO ALLOCATIONS(request_id,cab_id) VALUES ( ?, ?)")) {

            ps.setInt(1, requestID);
            ps.setInt(2, cabID);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    // to get the number of person for a particular request
    public int getNumberOfPersons(Integer requestID) throws SQLException {

        int person = 0;
        Connection con = pool.checkOut();

        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("select PR.REQUEST_USER_ID from POINT P,REQUEST R,POINT_REQUEST_USER PR where P.request_id=R.request_id AND P.POINT_ID=PR.POINT_ID AND P.request_id=?")) {

            ps.setInt(1, requestID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                person = person + 1;
            }

            // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());

            return person;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return person;
        } finally {
            pool.checkIn(con);
        }
    }

    //getting capacity of a particular vehicle 
    public int getCabCapacity(Integer cabID) throws SQLException {

        int capacity = 0;
        Connection con = pool.checkOut();

        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("select v.passenger_capacity from vehicles v,vehicle_for_cabs vc where v.vehicle_no=vc.vehicle_no and vc.cab_id=?")) {

            ps.setInt(1, cabID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                capacity = rs.getInt(1);
            }

            // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());

            return capacity;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return capacity;
        } finally {
            pool.checkIn(con);
        }
    }

    //getting all cabs which is presently available
    public List<Cab> getAvailableCabs() throws SQLException {
        List<Cab> clist = new ArrayList<Cab>();
        Connection con = pool.checkOut();
        Cab c = null;
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("select vc.cab_id,c.cab_name,ct.cab_type_value,v.passenger_capacity from CABS c,vehicle_for_cabs vc,cab_type ct,vehicles v where c.cab_id=vc.cab_id AND c.cab_status = TRUE AND vc.vehicle_no=v.vehicle_no AND c.cab_type_id=ct.cab_type_id AND vc.cab_id NOT IN\n" +
"(select cab_id from allocations\n" +
"where request_id IN(\n" +
"SELECT rq.request_id FROM public.request r, public.request rq WHERE r.request_id =38 and ( (r.start_time BETWEEN rq.start_time AND rq.end_time) or (r.end_time BETWEEN rq.start_time AND rq.end_time) or (rq.start_time BETWEEN r.start_time AND r.end_time) or (rq.end_time BETWEEN r.start_time AND r.end_time)) order by rq.request_id))")) {


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cab();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setCabTypeValue(rs.getString(3));
                c.setCapacity(rs.getInt(4));
                clist.add(c);
            }

            // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());

            return clist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(AllocationObjects.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }

    public static void main(String args[]) throws SQLException, ParseException {

        AllocationObjects dao = new AllocationObjects();
        List<Request> clist = dao.viewRequestsToManager(2);
        int size = clist.size();
        System.out.println("" + size);
        // iterating through arraylist
        Iterator<Request> it = clist.iterator();
        while (it.hasNext()) {
            System.out.println("" + it.next());

        }
    }
}
