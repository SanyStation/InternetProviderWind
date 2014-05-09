<%-- 
    Document   : index
    Created on : 29.04.2014, 17:22:20
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer user's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true">
    <jsp:param name="dashboardText" value="Customer user's dashboard"/>
</jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="cu-leftmenu.jsp" flush="true"/>      
        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-userinfo.jsp" flush="true"/>
            <div class="row divitem nomargin">
            </div>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>