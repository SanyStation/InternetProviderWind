<%-- 
    Document   : private_article
    Created on : 01.04.2014, 15:58:13
    Author     : oneplayer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Private Page</title>
    </head>
    <body>
        <h1>Welcome to user menu, ${name}!</h1>
        <form action="Controller" method="POST">
            <input type="submit" name="command" value="fdf"/>
        </form>
    </body>
</html>
