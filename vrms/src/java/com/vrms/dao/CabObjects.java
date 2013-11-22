package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Cab;
import com.vrms.model.CabType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muniraj
 */
public class CabObjects {

    private JDBCConnectionPool pool;

    public CabObjects() {
        pool = new JDBCConnectionPool();
    }

    //Adding a vehicle to the system
    public synchronized boolean registerVehicle(String vehicleNO, String vehicleModel, String contractorName, String vehicleDesc, Integer passengerCapacity) throws SQLException {
        Connection con = pool.checkOut();
        con.setAutoCommit(false);// for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO VEHICLES(vehicle_no, vehicle_model, contractor_name, vehicle_description,vehicle_status,passenger_capacity) VALUES (?,?,?,?,?,?)")) {

            ps.setString(1, vehicleNO);
            ps.setString(2, vehicleModel);
            ps.setString(3, contractorName);
            ps.setString(4, vehicleDesc);
            ps.setBoolean(5, true);
            ps.setInt(6, passengerCapacity);

            System.out.println(" values : " + vehicleNO + " ," + vehicleModel + " " + contractorName + " " + vehicleDesc + " " + passengerCapacity);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    //creating cabs for the system
    public synchronized boolean createCabs(String cabName, Integer cabTypeID, String cabDesc) throws SQLException {
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO CABS(cab_name, cab_type_id, cab_description, cab_status) VALUES (?,?,?,?)")) {

            ps.setString(1, cabName);
            ps.setInt(2, cabTypeID);
            ps.setString(3, cabDesc);
            ps.setBoolean(4, true);

            System.out.println(" values : " + cabName + " ," + cabTypeID + " " + cabDesc);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    //Changing status of cab when no vehicle is added to it or vehicle added to it
    public boolean cabStatusChange(Integer id, Boolean status) throws SQLException {

        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Update CABS SET cab_status= ? where CAB_ID=?")) {
            ps.setBoolean(1, status);
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

    //assigning vehicles to a particular cab so that we can use it in the system
    public synchronized boolean assignVehicleToCabs(Integer cabID, Integer vehicleNO, String reasonForChange) throws SQLException {
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO VEHICLE_FOR_CABS(cab_id, vehicle_no, reason_for_change) VALUES (?,?,?)")) {

            ps.setInt(1, cabID);
            ps.setInt(2, vehicleNO);
            ps.setString(3, reasonForChange);

            System.out.println(" values : " + cabID + " ," + vehicleNO + " ," + reasonForChange);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pool.checkIn(con);
        }
    }

    public synchronized List<Cab> getAllCabs() throws SQLException {
        Connection con = pool.checkOut();
        Cab c = null;
        List<Cab> clist = new ArrayList<Cab>();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Select * from CABS where cab_status=true")) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cab();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setTypeid(rs.getInt(3));
                c.setDescription(rs.getString(4));
                c.setStatus(rs.getBoolean(5));
                clist.add(c);
            }

            // System.out.println(" values : "+ u.getUserid()+" ,"+ u.getUserOfficeID()+ ", "+ u.getName()+ "," +u.getEmail()+","+u.getManager_id());
            return clist;

        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            pool.checkIn(con);
        }
    }

    // main class to check query is running or not
//    public static void main(String args[]) throws SQLException
//    {
//        CabObjects c=new CabObjects();
//       List<Cab> list= c.getAllCabs();
//       Iterator<Cab> it = list.iterator();
//          while(it.hasNext())
//       {
//           System.out.println(""+it.next());
//  
//       }
//      
//    }
    public List<CabType> getCabTypes() {
        List<CabType> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT cab_type.cab_type_id, cab_type.cab_type_value, cab_type.cab_type_description FROM public.cab_type")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CabType type = new CabType();
                type.setId(rs.getInt(1));
                type.setType(rs.getString(2));
                type.setDescription(rs.getString(3));
                list.add(type);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return list;
    }

    public List<Cab> getCabForTypes(int cabTypeId) {
        List<Cab> list = new ArrayList<>();
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT cabs.cab_id, cabs.cab_name, cabs.cab_description, cabs.cab_status FROM public.cabs WHERE cabs.cab_type_id = ?")) {
            ps.setInt(1, cabTypeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        } finally {
            pool.checkIn(con);
        }
        return list;
    }

}
