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
        <title>${sessionScope.title}</title>
    </head>
    <body>
        <display:table name="sessionScope.profits" export="true" sort="list">
            <display:caption media="html sip">${sessionScope.title}</display:caption>
            <display:setProperty name="export.sip" value="true"/>
            <display:setProperty name="export.sip.label" value="XLS"/>
            <display:setProperty name="export.csip" value="true"/>
            <display:setProperty name="export.csip.label" value="CSV"/>
            <display:column property="providerLocationId" title="Provider location" sortable="true" headerClass="sortable" />
            <display:column property="providerLocationName" title="Provider location name" sortable="true" headerClass="sortable" />
            <display:column property="serviceId" title="Service ID" sortable="true" headerClass="sortable" />
            <display:column property="serviceName" title="Service name" sortable="true" headerClass="sortable" />
            <display:column property="sum" title="Profit, $" sortable="true" headerClass="sortable" />
        </display:table>
    </body>
</html>