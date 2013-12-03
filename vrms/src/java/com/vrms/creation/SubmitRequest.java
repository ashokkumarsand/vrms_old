/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vrms.authentication.core.Constants;
import com.vrms.dao.PointObjects;
import com.vrms.dao.RequestObject;
import com.vrms.model.Location;
import com.vrms.model.Person;
import com.vrms.model.Point;
import com.vrms.model.UserInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashok
 */
public class SubmitRequest extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("I am here needed something");
        PrintWriter out = response.getWriter();
        String[] locations = request.getParameterValues("location");
        if (locations == null || locations.length < 1) {
            out.print("{status:false,error:\" add some location \"}");
            return;
        }
        
        String[] persons = request.getParameterValues("person");
        if (persons == null || persons.length < 1) {
            out.print("{status:false,error:\" add some person \"}");
            return;
        }
        String[] points = request.getParameterValues("points");
        if (points == null || points.length < 1) {
            out.print("{status:false,error:\" add some point \"}");
            return;
        }
        String st = request.getParameter("st");
        if (st == null) {
            out.print("{status:false,error:\" put start date \"}");
            return;
        }
        String et = request.getParameter("et");
        if (et == null) {
            out.print("{status:false,error:\" put end date \"}");
            return;
        }
        String purpose = request.getParameter("purpose");
        if (purpose == null) {
            out.print("{status:false,error:\" write your purpose \"}");
            return;
        }
        HttpSession session = request.getSession(false);
        int uid = Integer.parseInt(session.getAttribute(Constants.USERID).toString());
        System.out.println("uid :: "+uid);
        UserInfo ui = new UserInfo();
        ui.setUserid(uid);

        JsonParser parser = new JsonParser();
        List<Location> listLoc = new ArrayList<>();
        List<Person> listPrsn = new ArrayList<>();
        List<Point> listPoint = new ArrayList<>();
        RequestObject ro = new RequestObject();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date stDate = null;
        Date etDate = null;
        try {
            
            stDate = df.parse(st);
            etDate = df.parse(et);
            log(stDate + " :: " + etDate);
        } catch (ParseException ex) {
            log(ex.toString());
            Logger.getLogger(SubmitRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int requestId = ro.fillRequest(ui.getUserid(), new Timestamp(stDate.getTime()), new Timestamp(etDate.getTime()), Long.parseLong(ui.getMobileNo()), purpose);
        
        //log("request id :: " + requestId);
        for (String loc : locations) {
            System.out.println("Loc : " + loc);
            JsonObject jo = parser.parse(loc).getAsJsonObject();
            Location l = new Location();
            l.setName(jo.get("locName") != null ? jo.get("locName").getAsString() : null);
            l.setStreet(jo.get("streetName") != null ? jo.get("streetName").getAsString() : " ");
            l.setArea(jo.get("areaName") != null ? jo.get("areaName").getAsString() : " ");
            l.setCity(jo.get("city") != null ? jo.get("city").getAsString() : " ");
            l.setState(jo.get("state") != null ? jo.get("state").getAsString() : " ");
            listLoc.add(l);
        }
        //log("list size ::" +listLoc.size());
        for (String person : persons) {
            Person p = new Person();
            JsonObject jo = parser.parse(person).getAsJsonObject();
            p.setName(jo.get("name").getAsString());
            p.setMobileNo(jo.get("mobile").getAsString());
            listPrsn.add(p);
        }
        
        //log("person :: "+ listPrsn.size());
        int nodeCount = 0;
        PointObjects po = new PointObjects();
        for (String point : points) {
            Point p = new Point();
            p.setRequestId(requestId);
            JsonObject jo = parser.parse(point).getAsJsonObject();
            //log("String :: "+ jo.toString());
            po.insertLocationInDB(listLoc, jo.get("loc").getAsInt());
            p.setFromId(listLoc.get(jo.get("loc").getAsInt()).getId());
            JsonArray ja = jo.get("prsn").getAsJsonArray();
            for (JsonElement je : ja) {
                po.insertPersonsInDB(listPrsn, je.getAsInt());
                p.getRequestUserId().add(listPrsn.get(je.getAsInt()).getId());
            }
            //log("out :: " + jo.get("type"));
            System.out.print("out :: " + jo.get("type"));

            p.setPointType(Point.PointType.valueOf(jo.get("type").getAsString().toUpperCase()));
            listPoint.add(p);
            if (nodeCount != 0) {
                listPoint.get(nodeCount - 1).setNextId(p.getFromId());
            }
            nodeCount++;
        }
        int stPoint = po.processPoints(listPoint);
        System.out.println("4");
        ro.updatePointId(requestId, stPoint);
        System.out.println("5");
        ro.logRequestSatus(requestId, 1, uid);
        System.out.println("6");
        response.setContentType("text/html");
        
        out.println("done");
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
