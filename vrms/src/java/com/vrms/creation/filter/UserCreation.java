/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.creation.filter;

import com.google.gson.Gson;
import com.vrms.connection.JDBCConnectionPool;
import com.vrms.util.Permissions;
import com.vrms.util.VRMSUtil;
import com.vrms.validate.UserObjectValidator;
import com.vrms.validate.ValidateEmail;
import com.vrms.validate.ValidateExtensionNo;
import com.vrms.validate.ValidatePhoneNumber;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.vrms.request.constants.CustomErrorsValues;
import net.vrms.request.constants.UserRequestConstants;
import net.vrms.responce.beans.UserCreationErrorBean;

/**
 *
 * @author acts
 */
public class UserCreation implements Filter {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private JDBCConnectionPool pool;

    public UserCreation() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        //name field cannot be left blank and must contain character only 
        String name = request.getParameter(UserRequestConstants.NAME);
        String email = request.getParameter(UserRequestConstants.EMAIL);
        String mobileNO = request.getParameter(UserRequestConstants.MOBILE_NO);
        String extNo = request.getParameter(UserRequestConstants.EXT);
        String roleID = request.getParameter(UserRequestConstants.ROLE_ID);
        String departmentID = request.getParameter(UserRequestConstants.DEPT_ID);
        String managerID = request.getParameter(UserRequestConstants.MANAGER_ID);

        //Print writer object
        PrintWriter out = response.getWriter();
        //JSON Object to send a responce in perticular formate
        UserObjectValidator validator = UserObjectValidator.getInstance();

        UserCreationErrorBean bean = new UserCreationErrorBean();


        //request valid flag
        boolean flag = true;

        if (name.equals("")) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.NAME, CustomErrorsValues.REQUIRED, "Name is mendantory "));

        }
        if (email != null && !ValidateEmail.validate(email)) {
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.EMAIL, CustomErrorsValues.INVALID_PARAMETER, "Email formate is invalid"));
            flag = false;
        }

        if (mobileNO == null || mobileNO.trim().equals("")) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.MOBILE_NO, CustomErrorsValues.REQUIRED, "mobile is mendantory"));
        }

        if (mobileNO != null && !ValidatePhoneNumber.validate(mobileNO)) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.MOBILE_NO, CustomErrorsValues.REQUIRED, "mobile no is not valid "));
        }

        if (extNo != null && !extNo.trim().equals("") && !ValidateExtensionNo.validate(extNo)) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.EXT, CustomErrorsValues.REQUIRED, "ext no is not valid "));
        }
        if (roleID == null) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.ROLE_ID, CustomErrorsValues.REQUIRED, "role is mendatory"));

        }
        if (roleID != null && !VRMSUtil.isInteger(roleID) || !validator.isRoleExist(Integer.parseInt(roleID))) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.ROLE_ID, CustomErrorsValues.INVALID_PARAMETER, "role doest not exist"));
        }

        if (departmentID != null && !departmentID.trim().equals("") && !VRMSUtil.isInteger(departmentID) && !validator.isDepartmentExist(Integer.parseInt(departmentID))) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.DEPT_ID, CustomErrorsValues.REQUIRED, "department does not exist"));
        }

        if (managerID != null&& !managerID.trim().equals("") && !VRMSUtil.isInteger(managerID) && !validator.isUserExist(Integer.parseInt(managerID))) {

            flag = false;
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.MANAGER_ID, CustomErrorsValues.REQUIRED, "Manager does not exist"));
        } else if (managerID != null && !managerID.trim().equals("") && !validator.isUserHasSetOfPermission(Integer.parseInt(managerID), Permissions.REQUEST_APPROVE)) {
            bean.getErrors().add(bean.new ErrorBean(UserRequestConstants.MANAGER_ID, CustomErrorsValues.PERMISSION_NOT_EXIST, "Manager dont have permission to approve request"));
            //Manager dont have rights to approve the request
            //insert code for send responce
        }
        if (flag) {
            chain.doFilter(request, response);
        } else {
            out.write(new Gson().toJson(bean));
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("UserCreation:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("UserCreation()");
        }
        StringBuffer sb = new StringBuffer("UserCreation(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
