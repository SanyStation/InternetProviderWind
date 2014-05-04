<%--
    Author     : Alexander Kovriga
--%>

<%@page import="com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO"%>
<%@page import="com.netcracker.wind.entities.Role"%>
<%@page import="com.netcracker.wind.paging.TasksPaginatedList"%>
<%@page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("tasks");
    if (expl == null) {
        expl = new TasksPaginatedList(request, Role.PE_GROUP_ID, AbstractOracleDAO.DEFAULT_PAGE_SIZE);
        session.setAttribute("tasks", expl);
    }
    expl.setRequest(request);
%>
<html>
    <head>
        <link rel="stylesheet" href="../css/displaytag.css" type="text/css">
        <link rel="stylesheet" href="../css/screen.css" type="text/css">
        <link rel="stylesheet" href="../css/site.css" type="text/css">
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/displayTagAjax.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PE dashboard: All tasks</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" flush="true" />
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
