<%-- 
    Author     : Alexander Kovriga
--%>

<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("ports");
    expl.setRequest(request);
%>
<h3>Ports</h3>
<form role="form">
    <display:table name="sessionScope.ports" sort="external"  requestURI="Controller"
                   partialList="true" class="simple"
                   pagesize="${sessionScope.ports.objectsPerPage}" 
                   size="${sessionScope.ports.fullListSize}">

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
        
        <display:column property="id" title="ID" href="Controller?command=pe_review_port" paramId="id" paramProperty="id" />
        <display:column property="name" title="Name" href="Controller?command=pe_review_port" paramId="id" paramProperty="id" />
        <display:column property="status" title="Status" />

    </display:table>
</form>
