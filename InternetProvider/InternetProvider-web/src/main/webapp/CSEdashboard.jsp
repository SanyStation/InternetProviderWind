<%-- 
    Document   : CSEdashboard
    Created on : Apr 20, 2014, 3:07:21 PM
    Author     : Anna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title> Dashboard CSE menu</title>
        <link rel="stylesheet" href="css/menu.css" type="text/css">
            <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
            <script type="text/javascript" src="js/csemenu.js"></script>
            <script>
                function getCommand(command) {
                    $(document).ready(function() {
//                    $('input[type=submit]').click(function() {
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'text',
                            data: {
                                'command': command,
                            },
                            success: function(data) {
                                var tbl = $("<table/>").attr("id", "mytable");
                                tbl.attr("border",1)
                                 var element = $.parseJSON(data);
                                $("#div1").append(tbl);
                                for (var i = 0; i < 20; i++)
                                {
                                    var tr = "<tr>";
                                    var td1 = "<td>" + element[i]["id"] + "</td>";
                                    var td2 = "<td>" + element[i]["type"] + "</td>";
                                    var td3 = "<td>" + element[i]["status"] + "</td></tr>";

                                    $("#mytable").append(tr + td1 + td2 + td3);

                                }
                            },
                            error: function() {
                                alert("AJAX error");
                            }
                        });
                    });
//                });
                }
            </script>

    </head>
    <body>

        <ul id="menu">
            <li><a href="#">Main</a></li>
            <li>
                <a href="#">Tasks</a>
                <ul>
                    <li>
                          <
                          <a href="#" onclick="getCommand('cse_group_task')">CSE Group Tasks</a>
                      <!--  <input type="submit" value="CSE Group Tasks" onclick="getCommand('cse_group_task')">-->

                    </li>
                    <li>
                        <a href="#">My Tasks</a>				
                        <ul>
                            <li><a href="#">All my tasks</a></li>
                            <li><a href="#">Completed tasks</a></li>
                            <li><a href="#">Taken tasks</a></li>
                        </ul>	
                    </li>
                </ul>
            </li>

            <li>
                <a href="#"> Review SI </a>
                <ul>

                    <li>
                        <a href="#">List of Customer Accounts</a>		
                    </li>
                    <li>
                        <a href="#">List of Provider Location</a>
                    </li>
                    <li>
                        <a href="#">List of SI Status</a>
                        <ul>
                            <li><a href="#">Active</a></li>
                            <li><a href="#">Disconnected</a></li>
                            <li><a href="#">Planned</a></li>
                        </ul>				
                    </li>
                    <li>
                        <a href="#">List of Services</a>				
                        <ul>
                            <li><a href="#">All services</a></li>
                            <li><a href="#">Silver</a></li>
                            <li><a href="#">Gold</a></li>
                            <li><a href="#">Platinum</a></li>
                        </ul>	
                    </li>
                </ul>
            </li>
            <li>

                <a href="#"> Review SO </a>
                <ul>

                    <li>
                        <a href="#">List of Customer Accounts</a>
                    </li>
                    <li>
                        <a href="#">List of Provider Location</a>
                    </li>
                    <li>
                        <a href="#">List of Services</a>
                        <ul>
                            <li><a href="#">All services</a></li>
                            <li><a href="#">Silver</a></li>
                            <li><a href="#">Gold</a></li>
                            <li><a href="#">Platinum</a></li>
                        </ul>				
                    </li>
                    <li>
                        <a href="#">List of SO Status</a>				
                        <ul>
                            <li><a href="#">Entering</a></li>
                            <li><a href="#">Processing</a></li>
                            <li><a href="#">Completed</a></li>
                        </ul>	
                    </li>
                </ul>
            </li>

            <li>
                <a href="#"> Add new customer</a>	
            </li>
            <li>
                <a href="#"> Review customer account </a>	
            </li>
        </ul>

        <div id="div1"></div>
    </body>
</html>