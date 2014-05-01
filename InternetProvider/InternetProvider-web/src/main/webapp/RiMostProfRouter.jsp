<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="reports/css/displaytag.css" type="text/css">
        <link rel="stylesheet" href="reports/css/screen.css" type="text/css">
        <link rel="stylesheet" href="reports/css/site.css" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Routers' profitability</title>
    </head>
    <body>
        <display:table name="sessionScope.devices" export="true" sort="list" class="its" pagesize="30">
            <display:caption media="html">Routers' profitability</display:caption>
            <display:setProperty name="export.rimpr" value="true"/>
            <display:setProperty name="export.rimpr.label" value="XLS"/>
            <display:setProperty name="export.crimpr" value="true"/>
            <display:setProperty name="export.crimpr.label" value="CSV"/>
            <display:column property="id" title="ID" sortable="true" headerClass="sortable" />
            <display:column property="name" title="Router name" sortable="true" headerClass="sortable" />
            <display:column property="profit" title="Profit by router, $" sortable="true" headerClass="sortable" />
        </display:table>
    </body>
</html>