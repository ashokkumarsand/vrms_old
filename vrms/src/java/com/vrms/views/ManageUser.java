/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.views;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.vrms.authentication.core.Constants;
import com.vrms.dao.UserObjects;
import com.vrms.util.Permissions;
import com.vrms.util.UserMenu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ashok
 */
public class ManageUser extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        JsonObject json = new JsonObject();
        JsonElement login;
        if (session == null || (int) session.getAttribute(Constants.USERID) < 0) {
            login = new JsonPrimitive(Boolean.FALSE);
        } else {
            login = new JsonPrimitive(Boolean.TRUE);
            List<Permissions> permissions = (List<Permissions>) session.getAttribute(Constants.PERMISSIONS);
            permissions.retainAll(UserMenu.USER_MANAGE.getPermissions());
            if (permissions.contains(Permissions.ADD_USER)) {
                JsonObject elment = new JsonObject();
                elment.addProperty("exist", Boolean.TRUE);
                elment.addProperty("roles",new Gson().toJson(new UserObjects().getAllRole()));
                json.add(Permissions.ADD_USER.toString(), elment);
            }
            if (permissions.contains(Permissions.MANAGE_USER_STATUS)) {
                JsonObject elment = new JsonObject();
                elment.addProperty("exist", Boolean.TRUE);
                json.add(Permissions.MANAGE_USER_STATUS.toString(), elment);
            }
            
        }
        json.add("login", login);
        out.println(json.toString());

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
