<%-- 
    Document   : mail
    Created on : Apr 11, 2014, 6:00:00 PM
    Author     : Oksana
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SendMail</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form  action="Controller" method="POST">
            <table border="0">
                <thead>
                  SentEmail
                </thead>
                 <tbody>
                    <tr>
                        <td>User id </td>
                        <td><input type="text" name="user_id" value="" /></td>
                        <td></td>
                    </tr>
               
               
                    <tr>
                        <td>subject</td>
                        <td><input type="text" name="subject" value="" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="sent_mail" name="command" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>

