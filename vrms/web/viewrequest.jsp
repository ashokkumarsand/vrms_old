<%-- 
    Document   : viewrequest
    Created on : Nov 21, 2013, 3:06:01 PM
    Author     : acts
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.vrms.model.UserInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="userInfo" class="com.vrms.model.UserInfo"></jsp:useBean>
<jsp:useBean id="r" class="com.vrms.viewallocations.AllocationObjects" scope="page"></jsp:useBean>
<jsp:useBean id="c" class="com.vrms.viewallocations.AllocationObjects" scope="page"></jsp:useBean>
    <div class="panel">
        <div class="panel-heading well-sm label label-primary">
            <strong>Pending Requests</strong>
        </div>
        <div class="panel-body panel-collapse">
            <div class="row">
            <c:set var="count" value="1" scope="page" />
            <c:forEach  var="requests" items="${r.viewAllRequests()}">

                <div class="col-sm-6 col-md-5" >
                    <div class="thumbnail form-inline" ng-repeat="person in persons">
                        <label class=" well-sm label label-primary">${count}</label>
                        <div class="caption">
                            <jsp:setProperty name="userInfo" property="userid" value="${requests.userID}"/>
                            <h4>Name : ${userInfo.name}</h4>
                            <p>Mobile : ${userInfo.mobileNo}</p>
                            <p>Start : ${requests.getFormattedStartDate()}</p>
                            <p>End : ${requests.getFormattedEndDate()}</p>
                            <p>No of Person : ${requests.noOfPerson}</p>
                            <p>Purpose : ${requests.purpose} </p>
                            <hr />
                            Select Cab :<select class="form-control" id='avilCab${requests.requestID}'>
                                <option value>select</option> 
                                <c:forEach var="cabs" items="${c.getAvailableCabs()}">
                                    <option value="${cabs.id}">
                                        ${cabs.name}
                                    </option>
                                </c:forEach>
                            </select>

                            <button class="btn btn-primary btn-sm mgt10" type="button" onclick="assignCab(${requests.requestID})">Assign Cab</button>
                        </div>
                    </div>
                </div>
                <c:set var="count" value="${count + 1}" scope="page"/>  
            </c:forEach>
        </div>
    </div>
</div>


