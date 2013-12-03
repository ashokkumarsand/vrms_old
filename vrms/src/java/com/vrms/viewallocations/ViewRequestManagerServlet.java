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
public class ViewRequestManagerServlet extends HttpServlet {

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
        RequestObject dao = new RequestObject();
        PrintWriter out = response.getWriter();
        String status = request.getParameter("status");
        Integer requestID = Integer.parseInt(request.getParameter("requestID"));
        System.out.println("status :: "+ status+" req : "+ requestID);
        try {
            boolean result = false;
            if (status.equalsIgnoreCase("true")) {
                result = dao.updateRequestStatus(requestID, 2);
            }
            if (status.equalsIgnoreCase("false")) {
                result = dao.updateRequestStatus(requestID, 4);
            }

            if (result) {
                out.println("{status:true,message:\"Status Changed succesfully \"}");
                out.flush();
                out.close();
            } else {
                out.println("{status:false,message:\"Request Status Change failed\"");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewRequestManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
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