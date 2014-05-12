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
            <jsp:param name="report" value="orders" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <h3>${title}</h3>

            <form role="form">
                <display:table name="sessionScope.orders" sort="list" export="true" requestURI="Controller"
                               class="simple table table-striped" pagesize="${pageSize}" >

                    <display:caption media="sio csio">${title}</display:caption>
                    <display:setProperty name="export.sio" value="true"/>
                    <display:setProperty name="export.sio.label" value="<button type=\"button\" class=\"btn btn-block btn-link\">XLS</button>"/>
                    <display:setProperty name="export.csio" value="true"/>
                    <display:setProperty name="export.csio.label" value="<button type=\"button\" class=\"btn btn-block btn-link\">CSV</button>"/>

                    <display:setProperty 
                        name="export.banner.sepchar" 
                        value=" "/>
                    <display:setProperty 
                        name="export.banner" 
                        value="<div class=\"btn-group margintop\">
                        <button type=\"button\" class=\"btn btn-info dropdown-toggle\" data-toggle=\"dropdown\">
                        Export <span class=\"caret\"></span>
                        </button>
                        <ul class=\"dropdown-menu\" role=\"menu\">
                        {0}
                        </ul>
                        </div>"
                        />
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

                    <display:column property="name" title="Service order" sortable="true" />
                    <display:column property="providerLocationName" title="Provider location" sortable="true" />
                    <display:column property="serviceLocationName" title="Service location" sortable="true" />
                    <display:column property="serviceName" title="Service name" sortable="true" />
                    <display:column property="completeDate" title="Complete date" sortable="true" />
                </display:table>
            </form>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 leftmenu">

        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="false"/>