<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report generator</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
            $(function() {
                $("#dp1").datepicker({dateFormat: 'yy-mm-dd'});
                $("#dp2").datepicker({dateFormat: 'yy-mm-dd'});
                $("#dp3").datepicker({dateFormat: 'yy-mm-dd'});
                $("#dp4").datepicker({dateFormat: 'yy-mm-dd'});
            });
        </script>
    </head>
    <body>
        <form method="GET" action="Controller">
            <table border="0">
                <tr>
                    <td>
                        <input type="radio" name="command"
                               value="ri_util_n_cap">
                        Routers utilization and capacity
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command"
                               value="ri_most_prof">
                        Most profitable router
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command"
                               value="si_new_orders">
                        New orders per period
                        </input>
                    </td>
                    <td>
                        From: 
                        <input type="text" id="dp1" name="validDp1">
                        <!-- error message placeholder -->
                        <label for="validDp1" generated="true" class="error" style="display: none;"></label>
                    </td>
                    <td>
                        To: 
                        <input type="text" id="dp2" name="validDp2">
                        <!-- error message placeholder -->
                        <label for="validDp2" generated="true" class="error" style="display: none;"></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command"
                               value="si_prof_by_month">
                        Profitability by month
                        </input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command"
                               value="si_disconn_orders">
                        Disconnected orders per period
                        </input>
                    </td>
                    <td>
                        From: 
                        <input type="text" id="dp3" name="validDp3">
                        <!-- error message placeholder -->
                        <label for="validDp3" generated="true" class="error" style="display: none;"></label>
                    </td>
                    <td>
                        To: 
                        <input type="text" id="dp4" name="validDp4">
                        <!-- error message placeholder -->
                        <label for="validDp4" generated="true" class="error" style="display: none;"></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="command" value="cia_ipt">
                        Impact Propagation Tree
                        </input>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Generate">
        </form>
    </body>
</html>