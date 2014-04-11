<%-- 
    Document   : sentMail
    Created on : Apr 12, 2014, 2:44:09 AM
    Author     : Oksana
--%>

<%@page import="com.netcracker.wind.mail.MailSendler"%>
<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String[] email = {request.getParameter("email")};
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            MailSendler send = new MailSendler();
        %>
        <h1>Sending...</h1>

        <%=send.sendEmail(email, subject, message)%>

        <form action="index.jsp" method="POST">
            <input type="submit" value="back"  />
        </form>
    </body>
</html>
