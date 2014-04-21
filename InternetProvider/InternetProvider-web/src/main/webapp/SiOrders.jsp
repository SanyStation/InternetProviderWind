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
        <title>${sessionScope.title}</title>
    </head>
    <body>

        <display:table name="sessionScope.orders" pagesize="0"
                       export="true" sort="list">
            <display:column property="id" title="id"
                            sortable="true" headerClass="sortable" />
            <display:column property="providerLocationId"
                            title="Provider location" sortable="true"
                            headerClass="sortable" />
            <display:column property="serviceLocationId"
                            title="Service location" sortable="true"
                            headerClass="sortable" />
            <display:column property="serviceId"
                            title="Service" sortable="true"
                            headerClass="sortable" />
            <display:column property="status" title="Status"
                            sortable="true" headerClass="sortable" />
            <display:column property="completeDate" title="Complete date"
                            sortable="true" headerClass="sortable" />
        </display:table>

    </body>
</html>