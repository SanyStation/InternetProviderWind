<%-- 
    Document   : map
    Created on : Apr 23, 2014, 12:16:37 AM
    Author     : j_mart
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Service Location select</title>
        <!--script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'>
        </script-->
        <script type='text/javascript' src='js/jquery.1.7.2.min.js'>
        </script>
        <!--link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/result-light.css"-->
        <script type='text/javascript' src="http://maps.google.com/maps/api/js?sensor=false&libraries=places">
        </script>
        <script type='text/javascript' src="js/map.js"></script>
        <link href="css/map.css" rel="stylesheet" media="screen">
        


    </head>
    <body>
        <input id="pac-input" class="controls" type="text" placeholder="Enter your address">
        <div id="map_canvas" ></div>
        <div id="popup">Drag the marker<span id="coord"></span></div>
        <div id="side">
            <h1>Choose desired service:</h1>
            <form id="order_form" action="Controller" method="POST">
                <label style="font-size: 20px;">select your position first...</label>
                <input type="submit" name="send_order" value="Send order" disabled=""/>
                <input type="hidden" name="command" value="proceed_to_order"/>
                <input type="hidden" name="x" value="ProceedToOrder"/>
                <input type="hidden" name="y" value="ProceedToOrder"/>
                <input type="hidden" name="address" value=""/>
            </form></div></div>

</body>


</html>



