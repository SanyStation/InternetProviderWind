<%-- 
    Document   : IEdashboard
    Created on : 27.04.2014, 0:07:25
    Author     : Сашко & myshko
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Dashboard IE menu</title>
        <script>
            
        </script>
        <link rel="stylesheet" href="css/menu.css" type="text/css">
            <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
    </head>
    <body>
        <form method="POST" action="Controller">
                <input type="hidden" name="command" value="new_device"/>
                <input type="text" name="d_name" maxlength="25" size="20">
                <input type="submit" value="Create Router">
            </form>
        
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="new_cable">
        <select id="mysel" name="task_id" >
            <c:forEach items="${tasks}" var="task">
                <option value="${task.id}">${task.id}</option>
            </c:forEach>
                </select>
                <input type="submit" value="Create Cable">
        </form>
 
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
                            <li><a href="#" onclick="IETasks('ie_get_tasks');">All my tasks</a></li>
                            <li><a href="#" onclick="IETasks('ie_get_completed_tasks');">Completed tasks</a></li>
                            <li><a href="#">Taken tasks</a></li>
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
