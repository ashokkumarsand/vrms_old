/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation;

import com.vrms.dao.CabObjects;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ashok
 */
public class AddVehicle extends HttpServlet {

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
       
        PrintWriter out = response.getWriter();
        String vehicleNo = request.getParameter("vehicleNo");
        String vehicleModel = request.getParameter("vehicleModel");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String contractorName = request.getParameter("contractorName");
        String desc = request.getParameter("desc");
        CabObjects obj = new CabObjects();
        if(obj.registerVehicle(vehicleNo, vehicleModel, contractorName, desc, capacity)){
            out.print("{added:true}");
        }else{
            out.print("{added:false}");
        }
                    
        
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
