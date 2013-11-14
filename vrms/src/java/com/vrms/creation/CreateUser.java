/*
 * @author : muniraj
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation;

import com.vrms.connection.JDBCConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class CreateUser extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private JDBCConnectionPool pool;

    public int createUser(int userID, int userOfficeID, String name, String mobileNO, int extensionNO, String email, int managerID, int departmentID, int roleID, String password) {
        Connection con = pool.checkOut();

        try (PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?)")) {
            ps.setInt(1, userID);
            ps.setInt(2, userOfficeID);
            ps.setString(3, name);
            ps.setString(4, mobileNO);
            ps.setInt(5, extensionNO);
            ps.setString(6, email);
            ps.setInt(7, managerID);
            ps.setInt(8, departmentID);
            ps.setInt(9, roleID);
            ps.setString(10, password);
            int n = ps.executeUpdate();
            return n;


        } catch (SQLException ex) {
            System.out.println("i am here with exception ");
            ex.printStackTrace();
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            CreateUser u = new CreateUser();
            int userID = Integer.parseInt(request.getParameter("userID"));
            int userOfficeID = Integer.parseInt(request.getParameter("userOfficeID"));

            String name = request.getParameter("name");
            String mobileNO = request.getParameter("mobileNo");
            int extensionNO = Integer.parseInt(request.getParameter("extensionNo"));
            String email = request.getParameter("email");
            int managerID = Integer.parseInt(request.getParameter("managerID"));
            int departmentID = Integer.parseInt(request.getParameter("departmentID"));
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            String password = request.getParameter("password");
            int result = u.createUser(userID, userOfficeID, name, mobileNO, extensionNO, email, managerID, departmentID, roleID, password);

            if (result != -1) {
                out.println("{CreateUser:true}");
            }


        } catch (Exception e) {
            e.printStackTrace();
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
