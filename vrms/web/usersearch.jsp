<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Search</title>
</head>
<body>

<form action="SearchUserServlet" method="post">
<label>
Enter your query
</label>
<br/>
<input type="text" name="search" />
<select name="criteria">
<option value="uid">userid</option>
<option value="uname">username</option>
<option value="mobile">mobileno</option>

</select>

<input type="submit" />

</body>
</html>