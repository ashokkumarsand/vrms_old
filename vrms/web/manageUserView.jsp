<%-- 
    Document   : CreateUserView
    Created on : Nov 13, 2013, 6:33:37 PM
    Author     : Ashok
--%>

<%@page import="com.vrms.model.Roles"%>
<%@page import="com.vrms.util.UserMenu"%>
<%@page import="com.vrms.authentication.core.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.vrms.util.Permissions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="userObject" scope="session" class="com.vrms.dao.UserObjects"></jsp:useBean>

        <div class="panel panel-default">
            <div class="panel-heading navbar">

            <%
                List<Permissions> permissions = (List<Permissions>) session.getAttribute(Constants.PERMISSIONS);
                permissions.retainAll(UserMenu.USER_MANAGE.getPermissions());
                if (permissions.contains(Permissions.ADD_USER)) {
            %>

            <form class="navbar-form navbar-left">
                <input type="text" class="form-control col-lg-3" placeholder="Search">
            </form>
            <ul class="nav pull-right">
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-plus glyphicon-user"> </span>
                    </a>
                </li>
            </ul>
            <%                        }
            %>

        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-5">
                    <div>
                        <div class="control-group col-md-offset-1">
                            <label for="officeId" class="control-label">Official ID</label>
                            <input id="officeId" type="textbox" class="form-control col-md-4" placeholder="Official Id">
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <div>
                        <div class="control-group">
                            <label for="name" class="control-label">Name</label>
                            <input name="" id="name" type="textbox" class="form-control col-md-4" placeholder="Name">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mgt10">
                <div class="col-md-5">
                    <div>
                        <div class="control-group col-md-offset-1">
                            <label for="email" class="control-label">Email</label>
                            <input name="" id="email" type="textbox" class="form-control col-md-4" placeholder="Email">
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <div>
                        <div class="control-group">
                            <label for="mobile" class="control-label">Mobile No</label>
                            <input name="" id="mobile" type="textbox" class="form-control col-md-4" placeholder="Mobile No">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mgt10">
                <div class="col-md-5">
                    <div>
                        <div class="control-group col-md-offset-1">
                            <label for="ext" class="control-label">Extension No</label>
                            <input name="" id="ext" type="textbox" class="form-control col-md-4" placeholder="Extension No">
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <div>
                        <div class="control-group">
                            <label for="dept" class="control-label">Department</label>
                            <input name="" id="dept" type="textbox" class="form-control col-md-4" placeholder="Department">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mgt10">
                <div class="col-md-5">
                    <div>
                        <div class="control-group col-md-offset-1">
                            <label for="manager" class="control-label">Manager</label>
                            <input name="" id="manager" type="textbox" class="form-control col-md-4" placeholder="manager">
                        </div>
                    </div>
                </div>
                <div class="col-md-5 col-md-offset-1">
                    <div>
                        <div class="control-group">
                            <label for="roles" class="control-label">Role</label>
                            <div class="control">
                                <select id="roles" class="form-control col-md-4">
                                    <option value="">SELECT</option>


                                    <c:forEach var="role" items="${userObject.allRole}">
                                        <option value="${role.roleid}">${role.rolename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mgt10" style="margin-top: 15px;">

                <div class="col-md-5 col-md-offset-6">
                    <div>
                        <div class="control-group col-md-offset-5">

                            <button type="submit" id="createUser" onclick="javascript:CreateUser()" class="form-control col-md-3 btn btn-primary">Create User</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

