/*
 * @author : muniraj
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.vrms.authentication.core.Constants;
import com.vrms.connection.JDBCConnectionPool;
import com.vrms.dao.UserObjects;
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
import net.vrms.request.constants.UserRequestConstants;

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter(UserRequestConstants.NAME);
        String mobileNO = request.getParameter(UserRequestConstants.MOBILE_NO);
        String email = request.getParameter(UserRequestConstants.EMAIL);
        String _userOfficeID = request.getParameter(UserRequestConstants.OFFICE_ID);
        String _extensionNO = request.getParameter(UserRequestConstants.EXT);
        String _managerID = request.getParameter(UserRequestConstants.MANAGER_ID);
        String _departmentID = request.getParameter(UserRequestConstants.DEPT_ID);
        String _roleID = request.getParameter(UserRequestConstants.ROLE_ID);
        System.out.println("role id "+_roleID);
        Integer userOfficeID = _userOfficeID.trim().equals("") ? null : Integer.parseInt(_userOfficeID);
        Integer extensionNO = _extensionNO.trim().equals("") ? null : Integer.parseInt(_extensionNO);
        Integer managerId = _managerID.trim().equals("") ? null : Integer.parseInt(_managerID);
        Integer departmentId = _departmentID.trim().equals("") ? null : Integer.parseInt(_departmentID);
        Integer roleId = _roleID.trim().equals("") ? null : Integer.parseInt(_roleID);
        System.out.println("after pasre : "+roleId);
        boolean result = new UserObjects().registerUser(userOfficeID, name, mobileNO, extensionNO, email, managerId, departmentId, roleId);
        JsonPrimitive status = null;
        if (result) {
            status = new JsonPrimitive(Boolean.TRUE);
        } else {
            status = new JsonPrimitive(Boolean.FALSE);
        }
        JsonObject obj = new JsonObject();
        obj.add("status", status);
        obj.addProperty("password", Constants.DEFAULT_PASSWORD);
        out.print(obj);
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
