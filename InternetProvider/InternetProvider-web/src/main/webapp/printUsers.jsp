<%-- 
    Document   : printUsers
    Created on : Apr 14, 2014, 3:39:04 PM
    Author     : Oksana
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form action="Controller" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>email</th>
                        <th>password</th
                        <th>blocked</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <th>${user.password}</th>
                            <th>${user.blocked}</th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </body>
</html>
