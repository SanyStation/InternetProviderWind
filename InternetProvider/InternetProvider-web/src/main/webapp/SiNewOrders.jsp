<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="css/sireport.css" type="text/css">
        <link rel="stylesheet" href="css/siscreen.css" type="text/css">
        <link rel="stylesheet" href="css/site.css" type="text/css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New orders per period</title>
    </head>
    <body>

        <display:table name="sessionScope.orders" pagesize="20"
                       export="true" sort="list">
            <display:column property="service" title="Service"
                            sortable="true" headerClass="sortable" />
            <display:column property="date" title="Date"
                            sortable="true" headerClass="sortable" />
            <display:column property="cost" title="Cost, $"
                            sortable="true" headerClass="sortable" />
        </display:table>

    </body>
</html>