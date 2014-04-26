<%-- 
    Document   : listOfTasksForPE
    Created on : Apr 26, 2014, 4:24:44 PM
    Author     : Anatolii
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <STYLE type="text/css">
            .column {
                float:left;
                border:1px black;
                margin: auto;
            }            
        </STYLE>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User</th>
                    <th>Status</th>
                    <th>Service Order</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>
                            ${task.id} 
                        </td>
                        <td>
                            ${task.user.name} 
                        </td>
                        <td>
                            ${task.status} 
                        </td>
                        <td>
                            ${task.serviceOrder} 
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="loginBox">
            <div class="column">
                <c:if test="${last_number - 25 > 0}">
                    <form method="POST" action="Controller">
                        <input type="hidden" name="command" value="pe_tasks"/>
                        <input type="hidden" name="from" value="${last_number - 25 * 2}"/>
                        <input type="hidden" name="number" value="25"/>
                        <input type="submit" value="Prev">
                    </form>
                </c:if>
            </div>
            <div class="column">
                <c:if test="${size == 25}">
                    <form method="POST" action="Controller">
                        <input type="hidden" name="command" value="pe_tasks"/>
                        <input type="hidden" name="from" value="${last_number}"/>
                        <input type="hidden" name="number" value="25"/>
                        <input type="submit" value="Next">
                    </form>
                </c:if>
            </div>
        </div>
    </body>
</html>
