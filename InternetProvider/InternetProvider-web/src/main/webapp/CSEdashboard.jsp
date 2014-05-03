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


                function makeUL(length, command, type) {
                    // Create the list element:
                    var list = document.createElement('ul');
                    list.setAttribute("class", "pagination");

                    for (var i = 0; i < length; i++) {
                        // Create the list item:
                        var item = document.createElement('li');

                        // Set its contents:
                        var refItem = document.createElement('a');
                        refItem.setAttribute("href", "#");
                        refItem.setAttribute("onclick", command + "'," + i + "," +type+")");

                        refItem.appendChild(document.createTextNode(i));
                        item.appendChild(refItem);

                        // Add it to the list:
                        list.appendChild(item);

                    }
                    $("#paging").append(list);

                }

                function drawTable(data) {
                    var table = document.createElement('table');
                    table.setAttribute("id", "taskTable");
                    table.setAttribute("border", 1);

                    for (var i = 0; i < data.length; i++) {
                        var tr = document.createElement('tr');

                        var td1 = document.createElement('td').appendChild(document.createTextNode(data[i]["id"]));

                        var td2 = document.createElement('td').appendChild(document.createTextNode(data[i]["type"]));

                        var td3 = document.createElement('td').appendChild(document.createTextNode(data[i]["status"]));

                        tr.appendChild(td1);

                        tr.appendChild(td2);

                        tr.appendChild(td3);
                        table.appendChild(tr);
                    }

                    $("#forTable").append(table);
                }
                
                
                    function drawUserTable(data) {
                    var table = document.createElement('table');
                    table.setAttribute("id", "userTable");
                    table.setAttribute("border", 1);

                    for (var i = 0; i < data.length; i++) {
                        var tr = document.createElement('tr');

                        var td1 = document.createElement('td').appendChild(document.createTextNode(data[i]["id"]));

                        var td2 = document.createElement('td').appendChild(document.createTextNode(data[i]["name"]));

                        var td3 = document.createElement('td').appendChild(document.createTextNode(data[i]["email"]));
                        var td4 = document.createElement('td').appendChild(document.createElement('button').setAttribute("value","change password"));
                        var td5 = document.createElement('td').appendChild(document.createElement('button').setAttribute("value","review service instance"));
                        var td6 = document.createElement('td').appendChild(document.createElement('button').setAttribute("value","review service order"));
                        

                        tr.appendChild(td1);

                        tr.appendChild(td2);

                        tr.appendChild(td3);
                        tr.appendChild(td4);
                        tr.appendChild(td5);
                        tr.appendChild(td6);
                        table.appendChild(tr);
                    }

                    $("#forTable").append(table);
                }
                /*   function getSize(command){
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
                 return data;
                 },
                 error: function() {
                 alert("AJAX error");
                 }
                 });
                 });
                 }*/


                function getTasks(command, number, type) {
                    $(document).ready(function() {
//                    $('input[type=submit]').click(function() {
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'json',
                            data: {
                                'command': command,
                                'size': 25,
                                'from': number * 25

                            },
                            success: function(data) {
                                var myNode = document.getElementById("paging");
                                while (myNode.firstChild) {
                                    myNode.removeChild(myNode.firstChild);
                                }
                                var myNo = document.getElementById("forTable");
                                while (myNo.firstChild) {
                                    myNo.removeChild(myNo.firstChild);
                                }

                                switch (type) {
                                    case 'task':
                                        drawTable(data["data"]);
                                        break;
                                    case 'user':
                                         drawUserTable(data["data"]);
                                        break;
                                }
                                makeUL(data["size"] / 25, "getTasks('" + command,type);

                            },
                            error: function() {
                                alert("AJAX error");
                            }
                        });
                    });
//                });
                }
                /*$(document).ready(function() {
                 
                 var cseDashboard = new CSEDashboard();
                 cseDashboard.setElementCount("TABLE_NAME", cseDashboard.getElementCount("COMMAND", "TABLE_NAME"));
                 cseDashboard.drawPaginationTable("test");
                 cseDashboard.setPageEventHanlers("test");
                 
                 });*/


            </script>

    </head>
    <body>
        <ul id="menu">
            <li><a href="#">Main</a></li>
            <li>
                <a href="#">Tasks</a>
                <ul>
                    <li>


                        <a  href="#" onclick="getTasks('cse_group_task', 0,'task')">CSE Group Tasks</a>


                    </li>
                    <li>
                        <a href="#">My Tasks</a>				
                        <ul>

                            <% request.setAttribute("user", 1002);%>
                            <li><a href="#" onclick="getTasks('cse_get_tasks', 0,'task')">All my tasks</a></li>
                            <li><a href="#" onclick="getTasks('cse_get_completed_tasks',0,'task')">Completed tasks</a></li>
                            <li><a href="#" onclick="getTasks('cse_get_uncompleted_tasks',0,'task')">Uncompleted tasks</a></li>
                        </ul>	
                    </li>
                </ul>
            </li>

            <li>
                <a href="#"> Review SI </a>
                <ul>

                    <li>
                        <a href="#"  onclick="getTasks('customer_list', 0,'user')">List of Customer Accounts </a>		
                    </li>
                    <li>
                        <a href="#" onclick="getTasks('provider_location_list', 0)" >List of Provider Location  </a>
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

        <div id ="forTable">

        </div>

        <div id="paging" class="pagination">
            <!--    <ul class="pagination">
      <li><a href="#">&laquo;</a></li>
      <li><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><a href="#">&raquo;</a></li>
    </ul>-->
        </div>

    </body>
</html>