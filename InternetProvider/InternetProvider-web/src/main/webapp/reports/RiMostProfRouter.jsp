<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="reports/css/rireport.css" type="text/css">
        <link rel="stylesheet" href="reports/css/riscreen.css" type="text/css">
        <link rel="stylesheet" href="reports/css/site.css" type="text/css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Routers utilization and capacity</title>
    </head>
    <body>

        <display:table name="sessionScope.devices" pagesize="0"
                       export="true" sort="list">
            <display:column property="id" title="Id"
                            sortable="true" headerClass="sortable" />
            <display:column property="profit" title="Profit, $"
                            sortable="true" headerClass="sortable" />
        </display:table>

    </body>
</html>