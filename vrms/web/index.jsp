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
        <link rel=stylesheet href=css/bootstrap.css  type=text/css/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css">
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="script/lib/html5.js" type="text/javascript"></script>
        <![endif]-->
    
        <script type="text/javascript" src="script/lib/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="script/bootstrap.js"></script>
        <script type="text/javascript" src="script/actionscript.js"></script>
        
        <link rel="stylesheet" href="css/actionscript.css">
    </head>
    <body>
        <jsp:useBean id="constant" scope="session" class="com.vrms.authentication.core.Constants" />

        <jsp:useBean id="userInfo" scope="session" class="com.vrms.model.UserInfo">
            <jsp:setProperty name="userInfo" property="userid" value="${sessionScope[constant.USERID]}"/>
        </jsp:useBean>



        <div class="row-fluid">
            <div class="navbar navbar-default navbar-fixed-top">
                <div class="navbar-inner">
                    <a href="#" class="brand">VRMS</a>
                    <ul role="navigation" class="nav">
                        <li><a href="#" >Home</a></li>
                        <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">Messages <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Inbox</a></li>
                                <li><a href="#">Drafts</a></li>
                                <li><a href="#">Sent Items</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Trash</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav pull-right">
                        <li class="dropdown">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">${userInfo.name} <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="Logout">Logout</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Settings</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="container-fluid" style="margin-top: 50px;">
                <div class="row-fluid">
                    <div class="span2">
                        <ul class="nav nav-pills nav-stacked ">
                            <%
                                List<Permissions> permission = userInfo.getPermissions();
                                List<Permissions> menuPermission = UserMenu.USER_MANAGE.getPermissions();
                                menuPermission.retainAll(permission);
                                if (menuPermission.size() > 0) {
                            %>
                            <li id="<%= UserMenu.USER_MANAGE%>"><a href="javascript:void(0)" >Manage Users</a></li>
                                <%
                                    }
                                    menuPermission = UserMenu.CAB_MANAGE.getPermissions();
                                    menuPermission.retainAll(permission);
                                    if (menuPermission.size() > 0) {
                                %>
                            <li id="<%= UserMenu.CAB_MANAGE%>"><a href="#" >Manage CABS</a></li>
                                <%
                                    }

                                %>

                        </ul>
                    </div>
                    <div class="span10">
                        <div class="alert alert-error" id="alert-error" style="display: none">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong style="display: inline-block">Error !!</strong> 
                            <div id="msg" style="display: inline-block"></div>
                        </div>
                        <div id="container">
                        <!--Body content-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
