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
        <title>vrms:login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel=stylesheet href=css/bootstrap.css  type=text/css/>
        <link rel="stylesheet" href="css/bootstrap-responsive.css">
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="script/lib/html5.js" type="text/javascript"></script>
        <![endif]-->
        <script src="script/lib/jquery-1.10.2.js"  type=text/javascript></script>
        <script src="script/bootstrap.min.js" type=text/javascript></script>
    </head>
    <body>
        <div class="navbar">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-th-list"></span></a>
                    <a href="#" class="brand">VRMS</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="container ">
            <div class="row-fluid span3" >
                <form action="ServiceLoginAuth" method="post" class="form-actions">
                    <div>
                        <div class="control-group">
                            <label class="control-label" for="mobileno">Mobile No</label>
                            <div class="controls">
                                <input type="text" name="<%=Constants.USERNAME%>" id="mobileno" placeholder="mobileno">
                            </div>
                        </div>
                        <div class="control-group" style="display: block">
                            <label class="control-label" for="inputPassword">Password</label>
                            <div class="controls">
                                <input type="password" name="<%=Constants.PASSWORD%>" id="inputPassword" placeholder="Password">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <label class="checkbox">
                                    <input type="checkbox"> Remember me
                                </label>
                                <button type="submit" class="btn">Sign in</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
