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
        <link rel="stylesheet" href="css/iedasb.css" type="text/css">
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
                        <a href="#" onclick="IETasks('get_active_ietasks', 'NEW_CABLE', 'Create Cable', 'new_cable');">Cable</a>		
                    </li>
                    <li>
                        <a href="#" onclick="IETasks('get_active_ietasks', 'NEW_DEVICE', 'Create Device', 'new_dev');">Device</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"> Delete </a>
                <ul>

                    <li>
                        <a href="#" onclick="IETasks('get_active_ietasks', 'DELETE_CABLE', 'Delete Cable', 'del_cable');">Cable</a>		
                    </li>
                </ul>
            </li>
        </ul>
        <div id="div1"></div>
            <script>
                    function IETasks(command, typeT, buttonN, commandN){
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'json',
                            data: {
                                'command': command
                            },
                            success: function(data) {
                                getForm(data["data"], typeT, buttonN, commandN);
                            },
                            error: function() {
                                alert("AJAX error");
                            }
                        });
                    }
            </script>
            <script>
                function getForm(data, typeT, buttonN, commandN) {
                $(document).ready(function() {
                    $("#div1").empty();
                    var form = document.createElement('form');
                    form.setAttribute("method", "POST");
                    form.setAttribute("action", "Controller");
                    var input1 = document.createElement('input');
                    input1.setAttribute("type", "hidden");
                    input1.setAttribute("name", "command");
                    input1.setAttribute("value", commandN);
                    var input3 = document.createElement('input');
                    input3.setAttribute("type", "submit");
                    input3.setAttribute("value", buttonN);
                    var sel = document.createElement('select');
                    for (var i = 1; i !== data.length; i++){
                            if (data[i]["type"] === typeT){
                                var opt = document.createElement('option');
                                opt.innerHTML = data[i]["id"];
                                sel.appendChild(opt);
                        }
                    }
                    if (typeT === 'NEW_DEVICE'){
                        var lab = document.createElement('lable');
                        lab.innerHTML = 'Device Name:';
                        form.appendChild(lab);
                        form.appendChild(document.createElement('br'));
                        var input2 = document.createElement('input');
                        input2.setAttribute("type", "text");
                        input2.setAttribute("name", "d_name");
                        input2.setAttribute("maxlength", "25");
                        input2.setAttribute("size", "20");
                        form.appendChild(input2);
                        form.appendChild(document.createElement('br'));
                    }
                    form.appendChild(input1);
                    var lab = document.createElement('lable');
                    lab.innerHTML = 'Task id:';
                    form.appendChild(lab);
                    form.appendChild(sel);
                    form.appendChild(document.createElement('br'));
                    form.appendChild(input3);
                    $("#div1").append(form);
                });
            }
            </script>
                
                        
        </div>
    </body>
</html>
