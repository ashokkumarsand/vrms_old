<%-- 
    Document   : viewrequest
    Created on : Nov 21, 2013, 3:06:01 PM
    Author     : acts
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Allocation</title>
    </head>
    <body>
        

        <jsp:useBean id="r" class="com.vrms.viewallocations.AllocationDao" scope="page"></jsp:useBean>
        <h4>Pending Requests
        </h4>
         <table border="1">
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>User ID</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Mobile No</th>
                    <th>Purpose</th>
                </tr>
            </thead>
            <tbody>
                
            <c:forEach var="requests" items="${r.viewAllRequests()}">
                <tr>
                    <td><c:out value="${requests.requestID}"  /></td>
                    <td><c:out value="${requests.userID}" /></td>
                    <td><c:out value="${requests.startTime}" /></td>
                    <td><c:out value="${requests.endTime}"  /></td>
                    <td><c:out value="${requests.mobileNO}"  /></td>
                    <td><c:out value="${requests.purpose}"  /></td>
                   
                </tr>
                </c:forEach>
                
            
                


                
            </tbody>
        </table>
      
    </body>
</html>
