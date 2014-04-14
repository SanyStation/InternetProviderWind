<%-- 
    Document   : login
    Created on : Apr 14, 2014, 12:04:17 PM
    Author     : Anatolii
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <STYLE type="text/css">
            .column {
                float:left;
                width:160px;
                border:1px black;
            }            
            .inputcolumn {
                float:left;
                width:320px;
                text-align: center;
                margin:10px 0px 0px 0px;
            }
            .clear {
                clear:left;
            }
            .center
            {
                margin:auto;
            }
        </STYLE>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div><h1>Please login</h1></div>
        <form action="Controller" method="POST">
            <div id="loginBox">
                <div class="column">
                    <strong>Email:</strong>
                </div>
                <div class="column">
                    <input placeholder="Your email" type="text" size="20" name="email">
                </div>
                <div class="column clear">
                    <strong>Password:</strong>
                </div>
                <div class="column">
                    <input placeholder="Password" type="password" size="20" name="password">
                </div>
                <div class="inputcolumn clear">
                    <input type="submit" value="Login">
                    <input type="hidden" name="command" value="login"/>
                </div>
            </div>
        </form>
    </body>
</html>
