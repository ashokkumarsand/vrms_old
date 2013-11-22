/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.creation.filter;

import com.google.gson.Gson;
import com.vrms.util.VRMSUtil;
import com.vrms.validate.CabObjectsValidator;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.vrms.request.constants.CabRequestConstants;
import net.vrms.request.constants.CustomErrorsValues;
import net.vrms.responce.beans.CreationErrorBean;

/**
 *
 * @author acts
 */
public class CabCreation implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public CabCreation() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("CabCreation:DoBeforeProcessing");
        }

	// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("CabCreation:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
	// For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
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
        HttpSession session = httpRequest.getSession(false);

        //name field cannot be left blank and must contain character only 
        String cabName = request.getParameter(CabRequestConstants.CAB_NAME);
        String cabTypeID = request.getParameter(CabRequestConstants.CAB_TYPE_ID);
        String cabDesc = request.getParameter(CabRequestConstants.CAB_DESC);
        
        //Print writer object
        PrintWriter out = response.getWriter();
        
        //JSON Object to send a response in particular format
        CabObjectsValidator validator = CabObjectsValidator.getInstance();

        CreationErrorBean bean = new CreationErrorBean();


        //request valid flag
        boolean flag = true;

        
        // checking for null values of each field
        if (cabName == null || cabName.trim().equals("")) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(CabRequestConstants.CAB_NAME, CustomErrorsValues.REQUIRED, "Cab Name is mandatory "));

        }
       
        if (cabTypeID==null) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(CabRequestConstants.CAB_TYPE_ID, CustomErrorsValues.REQUIRED, "Cab Type Id is mandatory "));

        }
        
        
        if (cabDesc==null || cabDesc.trim().equals("")) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(CabRequestConstants.CAB_DESC, CustomErrorsValues.REQUIRED, "Cab description is mandatory "));

        }
       
        
        //vehicleNO is a number or not
        if (cabTypeID != null && !VRMSUtil.isInteger(cabTypeID)) {
            flag = false;
            bean.getErrors().add(bean.new ErrorBean(CabRequestConstants.CAB_TYPE_ID, CustomErrorsValues.INVALID_PARAMETER, "Invalid cabType ID"));
        }
        try {
            if (cabTypeID != null && !cabTypeID.trim().equals("") && !VRMSUtil.isInteger(cabTypeID) && !validator.isCabTypeIdExist(Integer.parseInt(cabTypeID))) {
                flag = false;
                bean.getErrors().add(bean.new ErrorBean(CabRequestConstants.CAB_TYPE_ID, CustomErrorsValues.REQUIRED, "Cab Type ID does not exist"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CabCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if (flag) {
            chain.doFilter(request, response);
        } else {
            out.write(new Gson().toJson(bean));
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        if (debug) {
            log("CabCreation:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);

	// If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
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
                log("CabCreation:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("CabCreation()");
        }
        StringBuffer sb = new StringBuffer("CabCreation(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
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