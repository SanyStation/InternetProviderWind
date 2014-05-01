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
        <display:table name="sessionScope.links" export="true" sort="list" class="simple" pagesize="30">
            <display:caption media="html">
                <strong>Customer Impact Analisys: Impact Propagation Tree</strong>
            </display:caption>
            <display:setProperty name="export.ciaipt" value="true"/>
            <display:setProperty name="export.ciaipt.label" value="XLS"/>
            <display:setProperty name="export.cciaipt" value="true"/>
            <display:setProperty name="export.cciaipt.label" value="CSV"/>
            <display:column property="deviceId" title="Device ID" sortable="true" headerClass="sortable" group="1" />
            <display:column property="deviceName" title="Device name" sortable="true" headerClass="sortable" group="2" />
            <display:column property="portId" title="Port ID" sortable="true" headerClass="sortable" />
            <display:column property="circuitId" title="Circuit ID" sortable="true" headerClass="sortable" />
            <display:column property="serviceInstanceId" title="Service instance ID" sortable="true" headerClass="sortable" />
        </display:table>
    </body>
</html>