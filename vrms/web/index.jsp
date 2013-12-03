<%-- 
    Document   : index
    Created on : Oct 30, 2013, 2:22:44 PM
    Author     : Ashok
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.vrms.connection.JDBCConnectionPool"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.vrms.util.UserMenu"%>
<%@page import="java.util.Collections"%>
<%@page import="com.vrms.util.Permissions"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.vrms.authentication.core.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>vrms</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"  type="text/css" media="screen"/>

        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <!--[if IE 7]>
      <link rel="stylesheet" href="css/font-awesome-ie7.min.css">
      <![endif]-->
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="script/lib/html5.js"></script>
            <script src="script/lib/respond.min.js"></script>
        <![endif]-->


        <link href="css/docs.css" rel="stylesheet" type="text/css">
        <!--<link href="css/bootstrap.min.theme.css" rel="stylesheet" type="text/css" />-->
        <link href="css/bootstrap_yeti.min.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="script/lib/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="script/bootstrap.js"></script>
        <script type="text/javascript" src="script/lib/angular.min.js"></script>
        <script type="text/javascript" src="script/actionscript.js"></script>
        <script type="text/javascript" src="script/lib/jquery.datetimepicker.js"></script>
        <link href="css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/actionscript.css">
    </head>
    <body>
        <jsp:useBean id="constant" scope="session" class="com.vrms.authentication.core.Constants" />

        <jsp:useBean id="userInfo" scope="session" class="com.vrms.model.UserInfo">
            <jsp:setProperty name="userInfo" property="userid" value="${sessionScope[constant.USERID]}"/>
        </jsp:useBean>

        <div class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" style="background-image: url('img/logo.png');background-repeat: no-repeat;background-size: 110px;background-position-x: 20px; background-position-y: 5px; width: 130px; height: 40px;"></a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">

                <ul class="nav navbar-nav navbar-right">
                    <li><a href=""><span class="glyphicon glyphicon-home"></span></a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-bell"></span></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>&nbsp; ${userInfo.name}  <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Settings <span class="glyphicon glyphicon-cog"></span></a></li>
                            <li class="divider"></li>
                            <li><a href="Logout">Logout <span class="glyphicon glyphicon-off"></span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>




        <div class="container row" ng-app="request">
            <div class="col-md-3 bs-docs-sidebar" style="background-color: whitesmoke;">
                <ul class="nav nav-list bs-docs-sidenav"  ng-controller="requestViewCtrl" style="margin-top: 10px; ">
                    <%
                        log("exception block 1");
                        List<Permissions> permission = userInfo.getPermissions();
                        //List<Permissions> menuPermission = UserMenu.USER_MANAGE.getPermissions();
                        log("permissions :: " + permission.size());
                        //menuPermission.retainAll(permission);
                        if (permission.contains(Permissions.ADD_USER) || permission.contains(Permissions.BLOCK_USER) || permission.contains(Permissions.UNBLOCK_USER)) {
                    %>
                    <li id="<%= UserMenu.USER_MANAGE%>" class="myTask"><a href="javascript:void(0)">Manage Users</a></li>
                        <%
                            }
                            log("exception block 2");
                            //menuPermission = UserMenu.CAB_MANAGE.getPermissions();
                            //menuPermission.retainAll(permission);

                            if (permission.contains(Permissions.ADD_CAB) || permission.contains(Permissions.ADD_VEHICLE) || permission.contains(Permissions.ASSIGN_CAB) || permission.contains(Permissions.VIEW_CAB)) {
                        %>
                    <li id="<%= UserMenu.CAB_MANAGE%>" class="myTask"><a href="#">Manage CABS</a></li>
                        <%
                            }
                            log("exception block 3");
                            //menuPermission = UserMenu.REQUEST_CAB.getPermissions();
                            //menuPermission.retainAll(permission);
                            if (permission.contains(Permissions.REQUEST_MAKE) || permission.contains(Permissions.REQUEST_ONLY)) {
                        %>
                    <li id="<%= UserMenu.REQUEST_CAB%>" class="myTask" ng-click="newRquest()"><a href="#" >Request vehicle</a></li>
                        <%
                            }
                            log("exception block 4");
                            if (permission.contains(Permissions.REQUEST_APPROVE)) {
                        %>
                    <li id="<%= Permissions.REQUEST_APPROVE%>" class="myTask" ><a href="#" >Pending Approval</a></li>

                    <%                        }
                        if (permission.contains(Permissions.ASSIGN_CAB)) {
                    %>
                    <li id="<%= Permissions.ASSIGN_CAB%>" class="myTask" ><a href="#" >Assign Cab </a></li>

                    <%                        }
                    %>

                </ul>
            </div>
            <div ></div>
            <div class="col-md-9" id="base-cont" ng-controller="ReqCtrl" style="margin-top: 10px; ">
                <div id="putError"></div>
                <form ng-submit="fetchRequest()">

                    <div id="container">
                        <!--Body content-->
                        <div class="panel">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>S.No</th>
                                        <th>Start Time</th>
                                        <th>End Time</th>
                                        <th>Purpose</th>
                                        <th>Status</th>
                                    </tr>
                                    <%
                                        JDBCConnectionPool pool = new JDBCConnectionPool();
                                        Connection con = pool.checkOut();
                                        PreparedStatement ps = con.prepareStatement("SELECT request.start_time, request.end_time, request.purpose, request_status.request_status_values FROM public.request,   public.request_status WHERE  request.request_status = request_status.request_status_id AND request.user_id = ? order by request.timestamp desc");
                                        ps.setInt(1, Integer.parseInt(session.getAttribute(Constants.USERID).toString()));
                                        ResultSet rs = ps.executeQuery();
                                        int i = 1;
                                        while (rs.next()) {

                                    %>
                                    <tr>
                                        <td><%=i++%></td>
                                        <td><%
                                            Timestamp stsm = rs.getTimestamp("start_time");
                                            Date std = new Date(stsm.getTime());
                                            DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:MM");
                                            String startDate = df.format(std);
                                            log("sdate :: " + startDate);
                                            %>
                                            <%=stsm%> </td>
                                        <td>
                                            <%
                                                Timestamp etsm = rs.getTimestamp("end_time");
                                                Date etd = new Date(etsm.getTime());
                                                String endDate = df.format(etd);
                                                log("edate :: " + endDate);
                                            %>

                                            <%=etsm%> 
                                        </td>
                                        <td><%= rs.getString("purpose")%></td>
                                        <td><%= rs.getString("request_status_values")%></td>
                                    </tr>
                                    <%
                                        }
                                        if(i==1){
                                            %>
                                            <tr>
                                                <td colspan="5">
                                                    <label> No request found </label>
                                                </td>
                                                
                                            </tr>
                                            <%
                                        }
                                    %>
                                </thead>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
