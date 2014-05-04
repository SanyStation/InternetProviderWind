<%-- 
    Document   : cse-table-users
    Created on : 03.05.2014, 13:54:34
    Author     : oneplayer
--%>
<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("users");
    expl.setRequest(request);
%>
<h3>Customer users list</h3>
<form role="form">
    <display:table name="sessionScope.users" sort="external"  requestURI="Controller"
                   partialList="true" class="simple"
                   pagesize="${sessionScope.users.objectsPerPage}" 
                   size="${sessionScope.users.fullListSize}">
        
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
        <display:column property="id" title="ID" />
        <display:column property="name" title="Name" />
        <display:column property="email" title="e-mail" />
        <display:column property="blocked" title="blocked" />
    </display:table>
    
    

    <!--button type="button" class="btn btn-default margin"><span class="glyphicon glyphicon-remove-circle"></span> Apply filter</button>
    <button type="button" class="btn btn-default margin"><span class="glyphicon glyphicon-remove-circle"></span> Clean filter</button-->
</form>
