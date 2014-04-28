<%-- 
    Document   : IEdashboard
    Created on : 27.04.2014, 0:07:25
    Author     : Сашко & myshko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Dashboard IE menu</title>
        <link rel="stylesheet" href="css/menu.css" type="text/css">
            <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
    </head>
    <body>
        <ul id="menu">
            <li><a href="#">Main</a></li>
            <li>
                <a href="#">Tasks</a>
                <ul>
                    <li><a href="#" onclick="IETasks('ie_get_group_tasks');">IE Group Tasks</a>
                    </li>
                    <li>
                        <a href="#">My Tasks</a>				
                        <ul>
                            <li><a href="#" onclick="IETasks('ie_get_group_tasks');">All my tasks</a></li>
                            <li><a href="#" onclick="IETasks('ie_get_group_tasks');">Completed tasks</a></li>
                            <li><a href="#" onclick="IETasks('ie_get_group_tasks');">Taken tasks</a></li>
                        </ul>	
                    </li>
                </ul>
            </li>
        

            <li>
                <a href="#"> Create </a>
                <ul>

                    <li>
                        <a href="#">Cable</a>		
                    </li>
                    <li>
                        <a href="#">Device</a>
                    </li>
                </ul>
            </li>
        </ul>
        <div id="div1">
            <script>
                    function IETasks(command){
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'text',
                            data: {
                                'command': command
                            },
                            success: function(data) {
                                $("#dynamic").html(data);
                            },
                            error: function() {
                                alert("AJAX error");
                            }
                        });
                    }
            </script>
                
                        
        </div>
    </body>
</html>
