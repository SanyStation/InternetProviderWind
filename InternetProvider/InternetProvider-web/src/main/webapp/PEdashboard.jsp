<%-- 
    Author     : Alexander Kovriga
--%>

<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("tasks");
    System.out.println("### DEBUG expl " + expl);
    expl.setRequest(request);
%>
<html>
    <head>
        <link rel="stylesheet" href="reports/css/displaytag.css" type="text/css">
        <link rel="stylesheet" href="reports/css/screen.css" type="text/css">
        <link rel="stylesheet" href="reports/css/site.css" type="text/css">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/displayTagAjax.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Provisioning Engineer Dashboard</title>
    </head>
    <body>
        <div id="ajxDspId">
            <display:table name="sessionScope.tasks" sort="external" 
                           partialList="true" class="simple"
                           pagesize="${sessionScope.tasks.objectsPerPage}" 
                           size="${sessionScope.tasks.fullListSize}">
                <display:column property="type" title="Type" />
                <display:column property="status" title="Status" />
                <display:column property="user.name" title="User name" />
                <display:column property="serviceOrderId" title="Service order id" />
                <display:column property="role.name" title="Role" />
            </display:table>
        </div>
    </body>
</html>
