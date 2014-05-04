<%-- 
    Document   : index
    Created on : 29.04.2014, 17:22:20
    Author     : oneplayer
--%>

<jsp:include page="../generic/header.jsp" flush="true">
    <jsp:param name="titleText" value="Customer user's dashboard"/>
</jsp:include>

<jsp:include page="../generic/dashboardheader.jsp" flush="true"/>

<div class="container">
    <div class="row">
        <jsp:include page="cu-leftmenu.jsp" flush="true"/>      
        <div class="col-md-9 nopadding">
            <jsp:include page="../generic/gen-customerinfo.jsp" flush="true"/>
            <div class="row divitem nomargin">
                <jsp:include page="../generic/gen-orderinfo.jsp" flush="true"/>
                <jsp:include page="../generic/gen-ordermap.jsp" flush="true"/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../generic/footer.jsp" flush="true"/>