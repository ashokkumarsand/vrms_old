<%-- 
    Document   : index
    Created on : Oct 30, 2013, 2:22:44 PM
    Author     : Ashok
--%>

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
        <script type="text/javascript" src="script/lib/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="script/bootstrap.js"></script>
    </head>
    <body>
        <jsp:useBean id="constant" scope="session" class="com.vrms.authentication.core.Constants" />

        <jsp:useBean id="UserInfo" scope="session" class="com.vrms.model.UserInfo">
            <jsp:setProperty name="UserInfo" property="userid" value="${sessionScope[constant.USERID]}"/>
        </jsp:useBean>
        <c:forEach var="per" items="${UserInfo.permissions}">
            <c:out value="${per}"/>
        </c:forEach>


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
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">${UserInfo.name} <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                
                                <li><a href="Logout">Logout</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Settings</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span2">
                        <ul class="nav-pills nav-stacked">

                        </ul>
                    </div>
                    <div class="span10" id="container">
                        <!--Body content-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
