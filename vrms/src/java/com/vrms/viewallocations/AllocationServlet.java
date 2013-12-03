/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.viewallocations;

import com.vrms.dao.RequestObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acts
 */
public class AllocationServlet extends HttpServlet {

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
        AllocationObjects dao = new AllocationObjects();
        RequestObject obj = new RequestObject();
        String requestID = request.getParameter("requestID");
        String cabID = request.getParameter("cabID");
        System.out.println(requestID + " , " + cabID);
        int CID = Integer.parseInt(cabID);
        int RID = Integer.parseInt(requestID);
        System.out.println(RID + " , " + CID);
        PrintWriter out = response.getWriter();
        try {
            boolean result = dao.setAllocations(RID, CID);
            System.out.println("Result = " + result);
            if (result) {
                obj.updateRequestStatus(RID, 3);
                out.print("{status : true, message:\"Cab Alloted successfully\"}");
            } else {
                out.print("{status : false, message:\"Cab Allocation Failed\"}");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllocationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
