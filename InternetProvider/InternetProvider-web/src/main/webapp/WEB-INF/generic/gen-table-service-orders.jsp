<%-- 
    Document   : gen-table-service-orders
    Created on : May 9, 2014, 4:52:09 PM
    Author     : Anatolii
--%>

<%@page import="com.netcracker.wind.entities.Role"%>
<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("orders");
    expl.setRequest(request);
%>
<h3>Orders</h3>
<form role="form">
    <c:if test="${user.roleId == 4}">
        <c:set var="comm" value="cse_review_order"/>
    </c:if>
    <c:if test="${user.roleId == 5}">
        <c:set var="comm" value="review_order"/>
    </c:if>
    <display:table name="sessionScope.orders" sort="external"  requestURI="Controller"
                   partialList="true" class="simple"
                   pagesize="${sessionScope.orders.objectsPerPage}" 
                   size="${sessionScope.orders.fullListSize}">

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
            <li class=\"active\"><a href=\"{1}\">First</a></li>
            <li class=\"disabled\"><a href=\"{2}\">Prev</a></li>
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
            <li class=\"disabled\"><a href=\"{3}\">Next</a></li>
            <li class=\"active\"><a href=\"{4}\">Last</a></li>
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
        <display:column property="id" title="ID" href="Controller?command=${comm}" paramId="order_id" paramProperty="id" sortable="true" sortProperty="id" />
        <display:column property="dateEnter" title="Enter date" sortable="true" sortProperty="enterdate" />
        <display:column property="dateComplete" title="Completed date" sortable="true" sortProperty="completedate" />
        <display:column property="service.name" title="Service" sortable="true" sortProperty="service_id" />
        <display:column property="status" title="Status" sortable="true" sortProperty="status" />
        <display:column property="serviceLocation.address" title="Service Location" sortable="true" sortProperty="service_location_id" />
    </display:table>
</form>