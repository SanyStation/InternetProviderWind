<%-- 
    Author     : Alexander Kovriga
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="reports/css/displaytag.css" type="text/css">
        <link rel="stylesheet" href="reports/css/screen.css" type="text/css">
        <link rel="stylesheet" href="reports/css/site.css" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Routers' utilization and capacity</title>
    </head>
    <body>
        <display:table name="sessionScope.devices" export="true" sort="list" class="its" pagesize="30">
            <display:caption media="html">Routers' utilization and capacity</display:caption>
            <display:setProperty name="export.riunc" value="true"/>
            <display:setProperty name="export.riunc.label" value="XLS"/>
            <display:setProperty name="export.criunc" value="true"/>
            <display:setProperty name="export.criunc.label" value="CSV"/>
            <display:column property="id" title="ID" sortable="true" headerClass="sortable" />
            <display:column property="name" title="Name" sortable="true" headerClass="sortable" />
            <display:column property="capacity" title="Capacity, ports" sortable="true" headerClass="sortable" />
            <display:column property="utilization" title="Utilization, ports" sortable="true" headerClass="sortable" />
            <display:column property="utilizationPercent" title="Utilization, %" sortable="true" headerClass="sortable" format="{0, number, 0.00}"/>
        </display:table>
    </body>
</html>