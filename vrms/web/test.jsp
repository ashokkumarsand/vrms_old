<%-- 
    Document   : test
    Created on : Nov 20, 2013, 5:57:34 PM
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
      <div id="loader">Loading / GIF animation</div>
<div id="result" style="display:none;">
    <p>Lots of data.
  Should be flushed to the browser every now and then.
  This will take seconds...</p>
</div>
<script type="text/javascript">
  $("#loader").hide();
  $("#result").show();
</script>
    </body>
</html>
