/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vrms.model.Location;
import com.vrms.model.Person;
import com.vrms.model.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashok
 */
public class SubmitRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

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
       String[] locations = request.getParameterValues("location");
       String[] persons = request.getParameterValues("person");
       String[] points = request.getParameterValues("points");
       String st = request.getParameter("st");
       String et = request.getParameter("et");
       String purpose = request.getParameter("purpose");
       JsonParser parser = new JsonParser();
       List<Location> listLoc = new ArrayList<>();
       List<Person> listPrsn = new ArrayList<>();
       List<Point> listPoint = new ArrayList<>();
       for(String loc : locations){
           System.out.println("Loc : "+loc);
           JsonObject jo = parser.parse(loc).getAsJsonObject();
           
       }
       for(String person : persons){
           System.out.println("person : "+person);
       }
       for(String point : points){
           System.out.println("point : "+point);
       }
        System.out.println("print all : "+locations+" : "+ persons+" : "+ points+" : "+ st+" : "+ et+" : "+purpose);

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
