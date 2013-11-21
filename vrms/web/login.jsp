<%-- 
    Document   : login
    Created on : Nov 11, 2013, 4:48:25 PM
    Author     : Ashok
--%>

<%@page import="com.vrms.authentication.core.Constants"%>
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

        <script type="text/javascript" src="script/lib/angular.min.js"></script>
        <script type="text/javascript" src="script/actionscript.js"></script>
        <link rel="stylesheet" href="css/actionscript.css">
    </head>
    <body>

        <div class="col-md-3 col-md-offset-4 well" style="background: whitesmoke">
            <div class="center-block">
                <h2>Login</h2>
            </div>

            <div class="center-block" >
                <form action="ServiceLoginAuth" method="post" class="form-actions">
                    <div>
                        <div class="control-group">
                            <label class="control-label" for="mobileno">Mobile No</label>
                            <div class="controls">
                                <input type="text" class="form-control" name="<%=Constants.USERNAME%>" id="mobileno" placeholder="mobileno">
                            </div>
                        </div>
                        <div class="control-group" style="display: block">
                            <label class="control-label" for="inputPassword">Password</label>
                            <div class="controls">
                                <input type="password" class="form-control" name="<%=Constants.PASSWORD%>" id="inputPassword" placeholder="Password">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <label class="checkbox">
                                    <input type="checkbox"> Remember me
                                </label>
                                <button type="submit" class="btn btn-primary">Sign in</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
