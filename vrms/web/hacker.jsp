<%@page import="com.vrms.authentication.core.Database"%>
<%@page import="com.vrms.authentication.core.IDatabase"%>
<%@page import="java.util.Map" language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%
IDatabase db = Database.getInstance();
Map<Integer, String> cookies  = db.getAllStolenCookies();
%>

<a href='/xss-demo/db?cmd=clearCookie'">CleanDB</a>
<table border="1">
<thead>
<tr>
<th>ID</th>
<th>Cookies</th>
</tr>
</thead>
<% for(Integer id : cookies.keySet()) { %>
<tr><th><%=id%></th><th><%=cookies.get(id)%></th></tr>
<%}%>
</table>