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
public class AddCab extends HttpServlet {

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
        System.out.println("herehreh");
        String typeid = request.getParameter("typeid");
        String name = request.getParameter("name");
        String vno = request.getParameter("vno");
        String desc = request.getParameter("desc");
        CabObjects cabO = new CabObjects();
        PrintWriter out = response.getWriter();
        int id = cabO.createCabs(name, Integer.parseInt(typeid), desc);
        if (id != 0) {
            if (cabO.assignVehicleToCabs(id, vno, "New ADDED")) {
                out.println("{added:true}");
            } else {
                out.println("{added:false,resion:already alloted}");
    }
        } else {
            out.println("{added:false,resion:try again}");
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
