<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Notification ID</th>
            <th>Notification From</th>
            <th>Notification To</th>
            <th>Notification Text</th>
            <th>Notification Status</th>
        </tr>
 
        <c:forEach var="notification" items="${nList}">
            <tr>
                <td>${notification.notificationId}</td>
                <td>${notification.notificationFrom}</td>
                <td>${notification.notificationTo}</td>
                <td>${notification.notificationText}</td>
                <td>${notification.notificationStatus}</td>
            </tr>
        </c:forEach>
    </table>
 
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="notification.do?page=${currentPage - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="notification.do?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
 
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="notification.do?page=${currentPage + 1}">Next</a></td>
    </c:if>
 
</body>
</html>