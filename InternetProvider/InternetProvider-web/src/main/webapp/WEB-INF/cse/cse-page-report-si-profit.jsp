<%-- 
    Author     : Alexander Kovriga
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<jsp:include page="../generic/header.jsp" flush="false">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="false">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="false">
            <jsp:param name="active" value="reports" />
            <jsp:param name="report" value="profit" />
        </jsp:include>
        
        <div class="col-md-9 divitem">
            <h3>${title}</h3>

            <form role="form">
                <display:table name="sessionScope.profits" sort="list" export="true" requestURI="Controller"
                               class="simple" pagesize="${pageSize}" >

                    <display:caption media="sip csip">${title}</display:caption>
                    <display:setProperty name="export.sip" value="true"/>
                    <display:setProperty name="export.sip.label" value="XLS"/>
                    <display:setProperty name="export.csip" value="true"/>
                    <display:setProperty name="export.csip.label" value="CSV"/>

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

                    <display:column property="providerLocationName" title="Provider location name" sortable="true" />
                    <display:column property="serviceName" title="Service name" sortable="true" />
                    <display:column property="sum" title="Profit, $" sortable="true" />
                </display:table>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="false"/>