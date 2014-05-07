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
        <title>Start page</title>
    </head>
    <body>
        <form action="mail.jsp" method="POST">
            <input type="submit" value="show_sentmail"  />
        </form>
        <form action="map.jsp" method="POST">
            <input type="submit" value="map"  />
        </form>
        <form action="Registration.jsp" method="POST">
            <input type="submit" value="Registration" />
        </form>
        <div>
            <a href="profile">Login</a>
        </div>
        <div>
            <a href="logout">Logout</a>
        </div>
    </body>
</html>
