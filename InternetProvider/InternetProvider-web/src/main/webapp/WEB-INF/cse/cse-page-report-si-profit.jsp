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

        <div class="col-md-9 divitem">
            <h3>${title}</h3>

            <form action="Controller" method="POST">
                <table>
                    <tr>
                        <td>
                            Month:
                        </td>
                        <td>
                            <input type="text" id="vdByMonth" name="vdByMonth" value="${date}">
                        </td>
                        <td>
                            <input type="hidden" value="cse_get_report_si_profit" name="command" />
                            <input type="submit" value="Generate">
                        </td>
                    </tr>
                </table>
            </form>

            <form role="form">
                <display:table name="sessionScope.profits" export="true" requestURI="Controller"
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

                    <display:column property="providerLocationName" title="Provider location name" />
                    <display:column property="serviceName" title="Service name" />
                    <display:column property="sum" title="Profit, $" />
                </display:table>
            </form>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="false"/>