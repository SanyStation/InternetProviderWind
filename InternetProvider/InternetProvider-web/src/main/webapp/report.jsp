<%-- 
    Author     : Alexander Kovriga
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report generator</title>
        <!--Tabs-->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <!--DatePicker-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <script src="reports/js/jquery.validate.min.js"></script>
        <script src="reports/js/jquery.ui.datepicker.validation.min.js"></script>
        <!--functions-->
        <script src="reports/js/functions.js"></script>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <form action="Controller" method="POST">
            <input type="submit" value="test" name="command" />
        </form>
        <div id="tabs">
            <ul>
                <li><a href="#tabs-ri1">RI report: Routers' profitability</a></li>
                <li><a href="#tabs-ri2">RI report: Routers' utilization and capacity</a></li>
                <li><a href="#tabs-si1">SI report: New orders</a></li>
                <li><a href="#tabs-si2">SI report: Disconnected orders</a></li>
                <li><a href="#tabs-si3">SI report: Profitability</a></li>
                <li><a href="#tabs-cia1">CIA report: Impact Propagation Tree</a></li>
            </ul>
            <div id="tabs-ri1">
                <p>Generates report of profitability of all routers in descending order.</p>
                <form action="Controller" method="POST">
                    <input type="hidden" value="ri_most_prof" name="command" />
                    <input type="submit" value="Generate">
                </form>
            </div>
            <div id="tabs-ri2">
                <p>Generates report of utilization and capacity of all routers.</p>
                <form action="Controller" method="POST">
                    <input type="hidden" value="ri_util_n_cap" name="command" />
                    <input type="submit" value="Generate">
                </form>
            </div>
            <div id="tabs-si1">
                <p>Generates report of new orders per certain period.</p>
                <form id="NewValidation" action="Controller" method="POST">
                    <table>
                        <tr>
                            <td>
                                From date:
                            </td>
                            <td>
                                <input type="text" id="vdNewFrom" name="vdNewFrom">
                                <label for="vdNewFrom" class="error" style="display: none;"></label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                To date: 
                            </td>
                            <td>
                                <input type="text" id="vdNewTo" name="vdNewTo">
                                <label for="vdNewTo" class="error" style="display: none;"></label>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" value="si_new_orders" name="command" />
                    <input type="submit" value="Generate">
                </form>
            </div>
            <div id="tabs-si2">
                <p>Generates report of disconnected orders per certain period.</p>
                <form id="DiscValidation" action="Controller" method="POST">
                    <table>
                        <tr>
                            <td>
                                From date:
                            </td>
                            <td>
                                <input type="text" id="vdDiscFrom" name="vdDiscFrom">
                                <label for="vdDiscFrom" class="error" style="display: none;"></label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                To date: 
                            </td>
                            <td>
                                <input type="text" id="vdDiscTo" name="vdDiscTo">
                                <label for="vdDiscTo" class="error" style="display: none;"></label>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" value="si_disc_orders" name="command" />
                    <input type="submit" value="Generate">
                </form>
            </div>
            <div id="tabs-si3">
                <p>Generates report of profitability per certain month.</p>
                <form action="Controller" method="POST">
                    <p>
                        <input type="text" id="vdByMonth" name="vdByMonth" />
                        <input type="hidden" value="si_prof_by_month" name="command" />
                    </p>
                    <input type="submit" value="Generate">
                </form>
            </div>
            <div id="tabs-cia1">
                <p>Generates "Customer Impact Analysis" report: Impact Propagation Tree.</p>
                <form action="Controller" method="POST">
                    <p>
                        <input type="hidden" value="cia_ipt" name="command" />
                    </p>
                    <input type="submit" value="Generate">
                </form>
            </div>
        </div>
    </body>
</html>