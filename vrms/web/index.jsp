<%-- 
    Document   : index
    Created on : Oct 30, 2013, 2:22:44 PM
    Author     : Ashok
--%>

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
        <script type="text/javascript" src="script/lib/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="script/bootstrap.js"></script>
        <script type="text/javascript" src="script/lib/angular.min.js"></script>
        <script type="text/javascript" src="script/actionscript.js"></script>
        <script type="text/javascript" src="script/lib/bootstrap-datetimepicker.min.js"></script>
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
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
                <a class="navbar-brand" href="">VRMS</a>
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

            <div class="col-md-3 bs-docs-sidebar">
                <ul class="nav nav-list bs-docs-sidenav affix"  ng-controller="requestViewCtrl">
                    <%
                        List<Permissions> permission = userInfo.getPermissions();
                        List<Permissions> menuPermission = UserMenu.USER_MANAGE.getPermissions();
                        menuPermission.retainAll(permission);
                        if (menuPermission.size() > 0) {
                    %>
                    <li id="<%= UserMenu.USER_MANAGE%>" class="myTask"><a href="javascript:void(0)">Manage Users</a></li>
                        <%
                            }
                            menuPermission = UserMenu.CAB_MANAGE.getPermissions();
                            menuPermission.retainAll(permission);
                            if (menuPermission.size() > 0) {
                        %>
                    <li id="<%= UserMenu.CAB_MANAGE%>" class="myTask"><a href="#">Manage CABS</a></li>
                        <%
                            }
                            menuPermission = UserMenu.REQUEST_CAB.getPermissions();
                            menuPermission.retainAll(permission);
                            if (menuPermission.size() > 0) {
                        %>
                    <li id="<%= UserMenu.REQUEST_CAB%>" class="myTask" ng-click="newRquest()"><a href="#" >Request vehicle</a></li>
                        <%
                            }

                        %>

                </ul>
            </div>
            <div ></div>
            <div class="col-md-9" id="base-cont" ng-controller="ReqCtrl">
                <div id="putError"></div>
                <form ng-submit="fetchRequest()">
                    
                    <div id="container">
                        <!--Body content-->
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
