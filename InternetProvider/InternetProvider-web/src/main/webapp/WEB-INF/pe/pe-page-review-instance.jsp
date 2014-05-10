<%-- 
    Author     : Alexander Kovriga
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Provisioning Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="pe-leftmenu.jsp" flush="true" />

        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-table-service-instance.jsp" flush="true" />
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
