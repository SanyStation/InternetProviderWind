<%-- 
    Document   : index
    Created on : Apr 6, 2014, 3:23:37 PM
    Author     : Anatolii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            $(document).ready(function() {
                $('input[type=submit]').click(function() {
                    //alert("44");
                    $.ajax({
                        type: 'POST',
                        url: "Controller",
                        data: {
                            'command': $('input[name=command]').val()
                        },
                        success: function(data) {
                            $("#dynamic").append(data);
                        },
                        error: function() {
                            alert("AJAX error");
                        }
                    });
                });
            })
        </script>
        <h1 align="center">Test AJAX</h1>
        <div id="dynamic" align="center">

            <input type="hidden" name="command" value="test_ajax"/>

            <input type="submit" name="b" value="Test"/>
        </div>
    </body>
    <form action="Controller" method="POST">
    </form>
</html>
