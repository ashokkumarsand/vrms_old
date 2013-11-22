/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.changepassword;

import com.vrms.authentication.core.Constants;
import com.vrms.dao.UserObjects;
import com.vrms.model.UserInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Muniraj
 */
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("In servlet");
        UserObjects u=new UserObjects();
        HttpSession session = request.getSession(false);
        PrintWriter out=response.getWriter();
                 String currentPass=request.getParameter("currentPass");
                 String newPass=request.getParameter("newPass");
                 String confirmNewPass=request.getParameter("confirmNewPass");
                 
                 if(newPass.equals(confirmNewPass))
                 {
                     System.out.println("Inside second if");
                     String userID= (String)session.getAttribute(Constants.USERID);
                     System.out.println("User ID"+userID);
                     try {
                             if(u.userPasswordChange(userID, newPass))
                             {
                                String message = "Password Changed Successfully";
                                response.sendRedirect("changepassword.jsp?" + URLEncoder.encode(message, "UTF-8"));
                             }
                          } catch (SQLException ex)
                          {
                                 Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                          }
                 }
                 else
                 {
                     String message = "Password mismatch";
                     response.sendRedirect("changepassword.jsp?" + URLEncoder.encode(message, "UTF-8"));
                 }
    }
}

