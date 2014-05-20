<%-- 
    Author     : Alexander Kovriga
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<jsp:include page="../generic/header.jsp" flush="false">
    <jsp:param name="titleText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="false">
    <jsp:param name="dashboardText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="pe-leftmenu.jsp" flush="false">
            <jsp:param name="active" value="reports" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <h3>${title}</h3>

            <form role="form">
                <display:table name="sessionScope.links" export="true" sort="list" requestURI="Controller"
                               class="simple" pagesize="${pageSize}" >

                    <display:setProperty name="export.ciaipt" value="true"/>
                    <display:setProperty name="export.ciaipt.label" value="<button type=\"button\" class=\"btn btn-block btn-link\">XLS</button>"/>
                    <display:setProperty name="export.cciaipt" value="true"/>
                    <display:setProperty name="export.cciaipt.label" value="<button type=\"button\" class=\"btn btn-block btn-link\">CSV</button>"/>
                    
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
                        </div>"/>
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

                    <display:column property="deviceId"
                                    title="Device ID"
                                    group="1"
                                    href="Controller?command=pe_review_device"
                                    paramId="id"
                                    paramProperty="deviceId"
                                    sortable="true"
                                    />
                    <display:column property="deviceName"
                                    title="Device" group="1"
                                    href="Controller?command=pe_review_device"
                                    paramId="id"
                                    paramProperty="deviceId"
                                    sortable="true"
                                    />
                    <display:column property="portName" title="Port"
                                    href="Controller?command=pe_review_port"
                                    paramId="id"
                                    paramProperty="portId"
                                    sortable="true"
                                    />
                    <display:column property="portStatus"
                                    title="Port status"
                                    sortable="true"
                                    />
                    <display:column property="circuitName"
                                    title="Circuit"
                                    href="Controller?command=pe_review_circuit"
                                    paramId="id"
                                    paramProperty="circuitId"
                                    sortable="true"
                                    />
                    <display:column property="serviceInstanceName"
                                    title="Service instance"
                                    href="Controller?command=pe_review_service_instance"
                                    paramId="id"
                                    paramProperty="serviceInstanceId"
                                    sortable="true"
                                    />
                </display:table>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="false"/>