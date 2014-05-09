<%-- 
    Document   : cu-orders-list
    Created on : May 5, 2014, 9:38:26 AM
    Author     : Anatolii
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer user's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer user's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cu-leftmenu.jsp" flush="true">
            <jsp:param name="active" value="orders" />
        </jsp:include>
        <div class="col-md-9 divitem">
            <jsp:include page="../generic/gen-table-service-orders.jsp" flush="true"/>
                <%--<jsp:param name="command" value="review_order"/>--%>
            <%--</jsp:include>--%>
        </div>
        <div class="col-md-offset-3">
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>
