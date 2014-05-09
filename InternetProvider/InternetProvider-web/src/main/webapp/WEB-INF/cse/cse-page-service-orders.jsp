<%-- 
    Document   : cse-page-service-orders
    Created on : May 9, 2014, 4:48:44 PM
    Author     : Anatolii
--%>
<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer Support Engineer's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cse-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="orders" />
        </jsp:include>
        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-table-service-orders.jsp" flush="true"/>
                <%--<jsp:param name="command" value="cse_review_order"/>--%>
            <%--</jsp:include>--%>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
