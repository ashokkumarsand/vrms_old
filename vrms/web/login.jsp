<%-- 
    Document   : login
    Created on : Nov 11, 2013, 4:48:25 PM
    Author     : Ashok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>vrms:login</title>
    </head>
    <body>
        <form action="/ServiceLoginAuth" method="post">
            <table>
                <tr>
                    <td> Username  : </td><td> <input type="text" name="username" size=15 type="text" /> </td> 
                </tr>
                <tr>
                    <td> Password  : </td><td> <input type="password" name="password" size=15 type="text" /> </td> 
                </tr>
            </table>
            <input type="submit" value="login" />
        </form>
    </body>
</html>
