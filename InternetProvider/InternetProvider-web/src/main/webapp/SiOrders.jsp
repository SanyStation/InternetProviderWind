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
        <title>${sessionScope.title}</title>
    </head>
    <body
        <display:table name="sessionScope.orders" export="true" sort="list" pagesize="30">
            <display:caption media="html sio csio">${sessionScope.title}</display:caption>
            <display:setProperty name="export.sio" value="true"/>
            <display:setProperty name="export.sio.label" value="XLS"/>
            <display:setProperty name="export.csio" value="true"/>
            <display:setProperty name="export.csio.label" value="CSV"/>
            <display:column property="id" title="id" sortable="true" headerClass="sortable" />
            <display:column property="providerLocationId" title="Provider location ID" sortable="true" headerClass="sortable" />
            <display:column property="providerLocationName" title="Provider location name" sortable="true" headerClass="sortable" />
            <display:column property="serviceLocationId" title="Service location" sortable="true" headerClass="sortable" />
            <display:column property="serviceName" title="Service name" sortable="true" headerClass="sortable" />
            <display:column property="completeDate" title="Complete date" sortable="true" headerClass="sortable" />
        </display:table>
    </body>
</html>