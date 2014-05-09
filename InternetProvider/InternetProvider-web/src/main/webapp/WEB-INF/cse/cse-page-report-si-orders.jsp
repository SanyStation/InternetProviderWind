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
        </jsp:include>

        <div class="col-md-4 divitem">
            <form id="validation" action="Controller" method="POST">
                <div class="input-group paddingtop">
                    <span class="input-group-addon">Date from</span>
                    <input type="text" id="vdFrom" class="form-control" name="vdFrom" value="${dateFrom}" placeholder="Enter date from">
                    <label for="vdFrom" class="error" style="display: none;"></label>
                </div>
                <div class="input-group paddingtop">
                    <span class="input-group-addon">Date to</span>
                    <input type="text" id="vdTo" class="form-control" name="vdTo" value="${dateTo}" placeholder="Enter date to">
                    <label for="vdTo" class="error" style="display: none;"></label>
                </div>
                <hr>
                <div>
                    <button type="submit" name="command" value="${command}" class="btn btn-default paddingtop"><span class="glyphicon glyphicon-refresh"></span> Generate</button>
                </div>
            </form>
        </div>

        <div class="col-md-9 divitem">
            <h3>${title}</h3>

            <form role="form">
                <display:table name="sessionScope.orders" export="true" requestURI="Controller"
                               class="simple table table-striped" pagesize="${pageSize}" >

                    <display:caption media="sio csio">${title}</display:caption>
                    <display:setProperty name="export.sio" value="true"/>
                    <display:setProperty name="export.sio.label" value="XLS"/>
                    <display:setProperty name="export.csio" value="true"/>
                    <display:setProperty name="export.csio.label" value="CSV"/>

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

                    <display:column property="name" title="Service order" />
                    <display:column property="providerLocationName" title="Provider location" />
                    <display:column property="serviceLocationName" title="Service location" />
                    <display:column property="serviceName" title="Service name" />
                    <display:column property="completeDate" title="Complete date" />
                </display:table>
            </form>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="false"/>