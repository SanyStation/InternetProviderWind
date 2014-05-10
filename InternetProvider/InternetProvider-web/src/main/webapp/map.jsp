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
         <!-- Bootstrap -->
        <!--<script src="//code.jquery.com/jquery.js"></script>-->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/wind.css" rel="stylesheet" media="screen">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>



    </head>
    <body>
        <input id="pac-input" class="controls" type="text" placeholder="Enter your address">
        <div id="map_canvas" ></div>
        <div id="popup" class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>Drag the marker to desired service location</div>
        <div id="side">
            <h3 style="margin: 10px auto;">Choose desired service:</h3>
            <form id="order_form" action="Controller" method="POST">
                <label style="font-size: 20px;">select your position first...</label>
                <input type="submit" name="send_order" value="Send order" disabled=""/>
                <input type="hidden" name="command" value="proceed_to_order"/>
                <input type="hidden" name="x" value="ProceedToOrder"/>
                <input type="hidden" name="y" value="ProceedToOrder"/>
                <input type="hidden" name="address" value=""/>
            </form></div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Authentication window</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                </div>
            </div>
        </div>

    </body>


</html>



