<%-- 
    Document   : login
    Created on : 01.04.2014, 16:33:05
    Author     : oneplayer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>JSP Page</title>
    </head>
    <body>
        <div><h1>Log in</h1></div>
            <form action="j_security_check" method="POST">
                <div id="loginBox">
                    <div class="column">
                        <strong>Login:</strong>
                    </div>
                    <div class="column">
                        <input placeholder="login" type="text" size="20" name="j_username">
                    </div>
                    <div class="column clear">
                        <strong>Password:</strong>
                    </div>
                    <div class="column">
                        <input placeholder="password" type="password" size="20" name="j_password">
                    </div>
                    <div class="inputcolumn clear">
                        <input type="submit" value="Sign in">
                    </div>
                </div>
            </form>
    </body>
</html>
