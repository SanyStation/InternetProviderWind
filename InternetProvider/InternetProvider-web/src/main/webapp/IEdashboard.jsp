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
        <select name="task_id" >
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
                        <a href="#" onclick="IETasks('get_active_ietasks');">Device</a>
                    </li>
                </ul>
            </li>
        </ul>
        <div id="div1"></div>
            <script>
                    function IETasks(command){
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'json',
                            data: {
                                'command': command
                            },
                            success: function(data) {
                                getForm(data["data"]);
                            },
                            error: function() {
                                alert("AJAX error");
                            }
                        });
                    }
            </script>
            <script>
                function getForm(data) {
                $(document).ready(function() {
                    var form = document.createElement('form');
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "Controller");
                    var input1 = document.createElement('input');
                    input1.setAttribute("type", "hidden");
                    input1.setAttribute("name", "command");
                    input1.setAttribute("value", "new_device");
                    var input2 = document.createElement('input');
                    input2.setAttribute("type", "text");
                    input2.setAttribute("name", "d_name");
                    input2.setAttribute("maxlength", "25");
                    input2.setAttribute("size", "20");
                    var input3 = document.createElement('input');
                    input3.setAttribute("type", "submit");
                    input3.setAttribute("value", "Create Router");
                    var sel = document.createElement('select');
                    sel.setAttribute("name", "task_id");
                    for (var i = 0; i !== 10; i++){
                        var opt = 
                                document.createElement('option');
                        opt.attributes : as="asa";
                        opt.textContent
                        sel.appendChild(opt);
                    }
                    form.appendChild(input1);
                    form.appendChild(input2);
                    form.appendChild(input3);
                    form.appendChild(sel);
                    $("#div1").append(form);
                });
            }
            </script>
                
                        
        </div>
    </body>
</html>
