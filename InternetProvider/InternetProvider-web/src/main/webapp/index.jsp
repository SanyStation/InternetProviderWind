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
        <script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
        <title>NameGenerator</title>
    </head>
    <body>
        <form action="CSEdashboard.jsp" method="POST">
            <input type="submit" value="CSE dashboard"  />
        </form>
        
        <form method="POST" action="Controller">
            <input type="hidden" name="command" value="get_active_ietasks"/>
            <input type="submit" value="IE dashboard"  />
        </form>

        <form action="mail.jsp" method="POST">
            <input type="submit" value="show_sentmail"  />
        </form>

        <h3>Reports</h3>
        <form action="report.jsp" method="POST">
            <input type="submit" value="Go to reports" name="report" />
        </form>
        <br>
        <form action="map.jsp" method="POST">
            <input type="submit" value="map"  />
        </form>

        <div>
            <a href="profile">Login</a>
        </div>

        <div>
            <a href="logout">Logout</a>
        </div>

        <div>
            <form method="POST" action="Controller">
                <input type="hidden" name="command" value="pe_tasks"/>
                <input type="hidden" name="from" value="0"/>
                <input type="hidden" name="number" value="25"/>
                <input type="submit" value="Show List Tasks For PE">
            </form>
        </div>
        
    </body>
</html>
