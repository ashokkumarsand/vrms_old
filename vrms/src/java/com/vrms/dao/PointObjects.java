/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.dao;

import com.vrms.connection.JDBCConnectionPool;
import com.vrms.model.Location;
import com.vrms.model.Person;
import com.vrms.model.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acts
 */
public class PointObjects {

    private JDBCConnectionPool pool;

    public PointObjects() {
        pool = new JDBCConnectionPool();
    }

    public synchronized int addLocation(Location location) {
        Connection con = pool.checkOut();

        try (PreparedStatement ps = con.prepareStatement("INSERT INTO locations(building_no, building_name, street_name, area, city,state) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            if (location.getNo() == null) {
                ps.setNull(1, Types.NULL);
            } else {
                ps.setString(1, location.getNo());
            }
            ps.setString(2, location.getName());
            if (location.getStreet() == null) {
                ps.setNull(3, Types.NULL);
            } else {
                ps.setString(3, location.getStreet());
            }
            if (location.getArea() == null) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setString(4, location.getArea());
            }
            if (location.getCity() == null) {
                ps.setNull(5, Types.NULL);
            } else {
                ps.setString(5, location.getCity());
            }
            if (location.getState() == null) {
                ps.setNull(6, Types.NULL);
            } else {
                ps.setString(6, location.getState());
            }
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Exception Occurred ");
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return -1;
    }

    public synchronized int addRequestUser(Person person) {
        Connection con = pool.checkOut();
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO request_user(user_name, user_mobile_no) VALUES (? , ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getMobileNo());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("Exception Occurred ");
            ex.printStackTrace();
            Logger.getLogger(CabObjects.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.checkIn(con);
        }
        return -1;
    }

    public void insertLocationInDB(List<Location> listLoc, int asInt) {
        if (listLoc.get(asInt).getId() == null) {
            listLoc.get(asInt).setId(addLocation(listLoc.get(asInt)));
        }
    }

    public void insertPersonsInDB(List<Person> listPrsn, int asInt) {
        if (listPrsn.get(asInt).getId() == null) {
            listPrsn.get(asInt).setId(addRequestUser(listPrsn.get(asInt)));
        }
    }

    /**
     *
     * @param listPoint
     * @return Start point id
     */
    public int processPoints(List<Point> listPoint) {
        Collections.reverse(listPoint);
        int stPointId = -1;
        for (Point p : listPoint) {
            Connection con = pool.checkOut();
            try (PreparedStatement ps = con.prepareStatement("insert into point(from_location_id,request_id,point_type_id,to_location_id) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, p.getFromId());
                ps.setInt(2, p.getRequestId());
                ps.setInt(3, p.getPointType().getDBValue());
                if (p.getNextId() == null) {
                    ps.setNull(4, Types.NULL);
                } else {
                    ps.setInt(4, p.getNextId());
                }
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
               
                if (rs.next()) {
                    stPointId = rs.getInt(1);
                }
                if (stPointId != -1) {
                    for(int requestUserId : p.getRequestUserId()){
                    PreparedStatement ps1 = con.prepareStatement("insert into point_request_user(point_id,request_user_id) values(?,?)");
                    ps1.setInt(1, stPointId);
                    ps1.setInt(2, requestUserId);
                    ps1.executeUpdate();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Exception occur :: "+ ex);
                ex.printStackTrace();
                Logger.getLogger(PointObjects.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stPointId;
    }
}
