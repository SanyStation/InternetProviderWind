<%-- 
    Document   : index
    Created on : Apr 6, 2014, 3:23:37 PM
    Author     : Anatolii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
        <script>
            function getName(command) {
                $(document).ready(function() {
//                    $('input[type=submit]').click(function() {
                    $.ajax({
                        type: 'POST',
                        url: 'Controller',
                        dataType: 'text',
                        data: {
                            'command': command
                        },
                        success: function(data) {
                            var element = $.parseJSON(data);//JSON.parse(data);
                            $("#dynamic").html(element.name);
                        },
                        error: function() {
                            alert("AJAX error");
                        }
                    });
                });
//                });
            }
        </script>
        <title>NameGenerator</title>
    </head>
    <body>
        <h1 align="center">Name generator</h1>
        <div align="center">
            <input type="submit" value="Generate" onclick="getName('name_generator')"/>
            <div id="dynamic" >
            </div>
        </div>
    </body>
</html>
