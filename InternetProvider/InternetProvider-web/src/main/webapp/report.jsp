<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report generator</title>
    </head>
    <body>
        <form method="GET" action="Controller">
            <table border="0">
                <tr>
                    <td>
                        <input type="radio" name="command" value="riUtilNCap">
                        Routers utilization and capacity
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="riMostProf">
                        Most profitable router
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="siNewOrders">
                        New orders per period
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="siProfByMonth">
                        Profitability by month
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="siDiscOrders">
                        Disconnected orders per period
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="ciaIPT">
                        Impact Propagation Tree
                        </input>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Generate">
        </form>
    </body>
</html>