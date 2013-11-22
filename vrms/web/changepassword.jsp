<%-- 
    Document   : changepassword
    Created on : Nov 14, 2013, 5:24:51 PM
    Author     : acts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="ChangePasswordServlet" method="post">
<label>
Current Password
</label>

           <input type="text" name="currentPass" />
<label>
New Password
</label>

<input type="text" name="newPass" />
<label><br/>
Confirm New Password
</label>

<input type="text" name="confirmNewPass" /><br/>


<input type="submit" />
<p>${param.message}</p>
    </body>
</html>
