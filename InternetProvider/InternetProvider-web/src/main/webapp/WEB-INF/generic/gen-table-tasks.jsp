<%-- 
    Document   : gen-table-tasks
    Created on : May 5, 2014, 5:17:35 AM
    Author     : Oksana
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("tasks");
    expl.setRequest(request);
%>
<h3>Tasks</h3>
<form role="form">
    <display:table id="row" name="sessionScope.tasks" sort="external"  requestURI="Controller"
                   partialList="true" class="simple"
                   pagesize="${sessionScope.tasks.objectsPerPage}" 
                   size="${sessionScope.tasks.fullListSize}">

        <display:setProperty 
            name="paging.banner.full" 
            value="<br/><ul class=\"pagination\">
            <li><a href=\"{1}\">First</a></li>
            <li><a href=\"{2}\">Prev</a></li>
            {0}
            <li><a href=\"{3}\">Next</a></li>
            <li><a href=\"{4}\">Last</a></li>
            </ul>"/>
        <display:setProperty 
            name="paging.banner.page.link" 
            value="<li><a href=\"{1}\" title=\"Go to
            page {0}\">{0}</a></li>"/>
        <display:setProperty 
            name="paging.banner.first" 
            value="<br/><ul class=\"pagination\">
            <li class=\"active\"><a>First</a></li>
            <li class=\"disabled\"><a>Prev</a></li>
            {0}
            <li><a href=\"{3}\">Next</a></li>
            <li><a href=\"{4}\">Last</a></li>
            </ul>"/>
        <display:setProperty 
            name="paging.banner.last" 
            value="<br/><ul class=\"pagination\">
            <li><a href=\"{1}\">First</a></li>
            <li><a href=\"{2}\">Prev</a></li>
            {0}
            <li class=\"disabled\"><a>Next</a></li>
            <li class=\"active\"><a>Last</a></li>
            </ul>"/>
        <display:setProperty 
            name="paging.banner.onepage" 
            value="<br/><ul class=\"pagination\">
            {0}
            </ul>"/>
        <display:setProperty 
            name="paging.banner.page.selected" 
            value="<li class=\"active\"><a href=\"{1}\"\">{0}</a></li>"/>
        <display:setProperty 
            name="paging.banner.page.separator" 
            value=""/>
        <display:setProperty 
            name="css.table" 
            value="table table-striped table-hover nomargin"/>
        <display:column property="id" title="ID" sortable="true" sortProperty="id" />
        <display:column property="type" title="Type" sortable="true" sortProperty="type" />
        <display:column property="status" title="Status" />
        <display:column title="Action">
            <c:if test="${row.status=='NEW'}">
                <a href="Controller?command=process_task&task_id=${row.id}" class="btn btn-primary">Select task</a>
            </c:if>
            <c:if test="${row.status=='ACTIVE'}">
                <a href="Controller?command=process_task&task_id=${row.id}" class="btn btn-info">Review task</a>
            </c:if>
            <c:if test="${row.status=='COMPLETED'}">
                <a href="Controller?command=process_task&task_id=${row.id}" class="btn btn-success">Review task</a>
            </c:if>
        </display:column>

    </display:table>
</form>
