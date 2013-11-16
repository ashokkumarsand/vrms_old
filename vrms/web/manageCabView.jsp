<%-- 
    Document   : manageCabView
    Created on : Nov 16, 2013, 10:11:39 AM
    Author     : Ashok
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.vrms.util.UserMenu"%>
<%@page import="com.vrms.authentication.core.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.vrms.util.Permissions"%>
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
        <link href="css/bootstrap.min.theme.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="script/lib/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="script/bootstrap.js"></script>
        <script type="text/javascript" src="script/actionscript.js"></script>
        <script type="text/javascript" src="script/lib/angular.min.js"></script>
        <link rel="stylesheet" href="css/actionscript.css">
    </head>
    <body>



        <%
            List<Permissions> permissions = (List<Permissions>) session.getAttribute(Constants.PERMISSIONS);
            permissions.retainAll(UserMenu.USER_MANAGE.getPermissions());
            if (permissions.size() > 0) {
        %>







        <jsp:useBean id="cabObject" class="com.vrms.dao.CabObjects" scope="page"></jsp:useBean>
        <c:forEach var="type" items="${cabObject.cabTypes}">
            <div class="panel">
                <div class="panel-heading">
                    <label class=" well-sm label label-primary">${type.type}</label>                    
                </div>
                <div class="panel-body">
                    <div class="row">
                        <c:forEach var="cab" items="${cabObject.getCabForTypes(type.id)}">
                            <div class="col-sm-6 col-md-5">
                                <div class="thumbnail">
                                    <div class="caption">
                                        <h3>${cab.name}</h3>
                                        <p>${cab.description}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="col-sm-6 col-md-5">
                            <div class="thumbnail">
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">Name</label>
                                                <input type="hidden" id="cabTypeId" value="${type.id}}"/>
                                                <input name="" id="name" type="text" class="form-control" placeholder="Name">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="desc" class="control-label">Description</label>
                                                <textarea style="resize: vertical;" maxlength="200" id="desc" type="" class="form-control" placeholder="Description"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" style="margin-top: 15px;">
                                    <div class="col-md-7 col-md-offset-4">
                                        <div>
                                            <div class="control-group">
                                                <button type="submit" id="createCab" onclick="javascript:CreateCab(${type.id})" class="form-control btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp; Cab </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>

        </div>
        <%                        }
        %>
    </body>
</html>
