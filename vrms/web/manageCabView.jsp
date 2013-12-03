<%-- 
    Document   : manageCabView
    Created on : Nov 16, 2013, 10:11:39 AM
    Author     : Ashok
--%>

<%@page import="com.vrms.model.UserInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.vrms.util.UserMenu"%>
<%@page import="com.vrms.authentication.core.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.vrms.util.Permissions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%
    UserInfo userinfo = new UserInfo();
    userinfo.setUserid(Integer.parseInt(session.getAttribute(Constants.USERID).toString()));

    List<Permissions> permissions = userinfo.getPermissions();

    permissions.retainAll(UserMenu.CAB_MANAGE.getPermissions());

    if (permissions.size() > 0) {

%>







<jsp:useBean id="cabObject" class="com.vrms.dao.CabObjects" scope="page"></jsp:useBean>
<c:forEach var="type" items="${cabObject.cabTypes}">
    <div class="panel">
        <div class="panel-heading">
            <label class=" well-sm label label-primary">${type.type}</label>  
            <label class="well-sm label btn-info pull-right" onclick="javascript:$('#addVehicleToogle').toggle();">
                <span class="glyphicon glyphicon-plus"></span> Vehicle
            </label>                   
        </div>
        <div class="panel col-md-5 pull-right" id="addVehicleToogle" style="display: none;">
            <div class="panel-body" >
                <div class="row">
                    <div class="thumbnail">
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="vehicleNo" class="control-label">Vehicle No</label>
                                        <input name="" id="vehicleNo" type="text" class="form-control" placeholder="Vehicle No.">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="vehicleModel" class="control-label">Vehicle Model</label>
                                        <input name="" id="vehicleModel" type="text" class="form-control" placeholder="Vehicle Model">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="capacity" class="control-label">Capacity</label>
                                        <input name="" id="capacity" type="text" class="form-control" placeholder="Capacity">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="contractorName" class="control-label">Contractor Name</label>
                                        <input name="" id="contractorName" type="text" class="form-control" placeholder="Contractor Name">
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
                                        <button type="submit" id="createCab" onclick="javascript:createVehical()" class="form-control btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp; Vehicle </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>          
        </div>
        <div class="panel-body">
            <div class="row">
                <c:forEach var="cab" items="${cabObject.getCabForTypes(type.id)}">
                    <div class="col-sm-6 col-md-5">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>${cab.name}</h3>
                                <h4>${cabObject.getVehicleForCab(cab.id).vehicleNo}</h4>
                                <p>${cab.description}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-sm-6 col-md-5" id="addCab">
                    <div class="thumbnail">
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="name" class="control-label">Name</label>
                                        <input type="hidden" id="cabTypeId" value="${type.id}"/>
                                        <input name="" id="name" type="text" class="form-control" placeholder="Name">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mgt10">
                            <div class="col-md-10 col-md-offset-1">
                                <div>
                                    <div class="control-group">
                                        <label for="vehicleNo" class="control-label">Vehicle No</label>
                                        <input name="" id="vehicleNo" type="text" class="form-control" placeholder="Vehicle No">
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
                                        <button type="submit" id="createCab" onclick="javascript:CreateCab(${type.id})" class="form-control btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp; Cab </button>
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
