/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Cab;
import com.vrms.model.CabType;
import com.vrms.model.Vehicle;
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
public class CabObjects {

    JDBCConnectionPool pool;

    public CabObjects() {
        pool = new JDBCConnectionPool();
    }

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

    public Vehicle getVehicleForCab(int cabid) {
        Vehicle vehicle = new Vehicle();
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("SELECT vehicles.vehicle_no, vehicles.vehicle_model, vehicles.contractor_name, vehicles.vehicle_description, vehicles.vehicle_status, vehicles.passenger_capacity FROM public.vehicle_for_cabs,public.vehicles,public.cabs WHERE vehicle_for_cabs.vehicle_no = vehicles.vehicle_no AND cabs.cab_id = vehicle_for_cabs.cab_id AND cabs.cab_id = ?")) {
            ps.setInt(1, cabid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicle.setVehicleNo(rs.getString(1));
                vehicle.setModel(rs.getString(2));
                vehicle.setContractorName(rs.getString(3));
                vehicle.setDescription(rs.getString(4));
                vehicle.setActive(rs.getBoolean(5));
                vehicle.setCapacity(rs.getInt(6));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return vehicle;
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

    //Adding a vehicle to the system
    public synchronized boolean registerVehicle(String vehicleNO, String vehicleModel, String contractorName, String vehicleDesc, Integer passengerCapacity){
        Connection con = pool.checkOut();
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
    public synchronized Integer createCabs(String cabName, Integer cabTypeID, String cabDesc){
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO CABS(cab_name, cab_type_id, cab_description, cab_status) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cabName);
            ps.setInt(2, cabTypeID);
            ps.setString(3, cabDesc);
            ps.setBoolean(4, true);
            System.out.println(" values : " + cabName + " ," + cabTypeID + " " + cabDesc);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
           
        } finally {
            pool.checkIn(con);
        }
        return 0;
    }

    //Changing status of cab when no vehicle is added to it or vehicle added to it
    public boolean cabStatusChange(Integer id, String status) throws SQLException {
        Connection con = pool.checkOut();
        con.setAutoCommit(false); // for checking query without any entry in database
        try (PreparedStatement ps = con.prepareStatement("Update CABS SET cab_status= ? where CAB_ID=?")) {
            ps.setBoolean(1, Boolean.parseBoolean(status));
            ps.setInt(2, id);
            int i = ps.executeUpdate();
            System.out.println("output=" + i);
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
    public synchronized boolean assignVehicleToCabs(Integer cabID, String vehicleNO, String reasonForChange) {
        Connection con = pool.checkOut();
       
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO VEHICLE_FOR_CABS(cab_id, vehicle_no, reason_for_change) VALUES (?,?,?)")) {

            ps.setInt(1, cabID);
            ps.setString(2, vehicleNO);
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
}
