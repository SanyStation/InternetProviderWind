<%-- 
    Document   : cse-page-tasks-list
    Created on : May 5, 2014, 5:13:08 AM
    Author     : Oksana
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="pe-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="tasks" />
        </jsp:include>

        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-table-tasks.jsp" flush="true"/>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>